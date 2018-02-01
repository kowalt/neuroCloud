package com.mycompany.nncloudrestservice.training.ebp;

import com.mycompany.nncloudrestservice.comparators.LayerAscendingComparator;
import com.mycompany.nncloudrestservice.comparators.LayerDescendingComparator;
import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.localcalculations.RunManager;
import com.mycompany.nncloudrestservice.localcalculations.ValueCalculator;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.utils.MailSender;

import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EBP implements Runnable {
    private final Network network;
    private final Double learning_factor;
    private final Integer iterations;
    private final List<Double[]> learning_set;
    private final List<Double[]> training_set;
    private final Logger LOGGER = LogManager.getLogger(EBP.class);

    public EBP(Network network, Double learning_factor, Integer iterations, List<Double[]> learning_set, List<Double[]> training_set)
    {
        this.network = network;
        this.learning_factor = learning_factor;
        this.iterations = iterations;
        this.learning_set = learning_set;
        this.training_set = training_set;
    }

    private void calculateAndSetOutputErrors(double[] output_vector, double[] expected_vector)
    {
        List<Layer> layers = network.getLayers();
        
        Layer output_layer = layers.get(layers.size()-1);

        int i = 0;
        for(Neuron neuron: output_layer.getNeurons())
        {
            neuron.setError_value(expected_vector[i] - output_vector[i]);
            LOGGER.trace("neuron id="+String.valueOf(neuron.getId())+" error_value="+String.valueOf(neuron.getError_value()));
            i++;
        }
    }
    
    private void calculateInnerErrors()
    {
        List<Layer> layers = network.getLayers();
        Collections.sort(layers, new LayerDescendingComparator());
        LOGGER.debug("Calculating inner errors");
        
        for(Layer l: layers)
        {
            if(l.getRelative_number() == layers.size())
            {
                LOGGER.trace("Last layer, no inner error calculation");
                continue;
            }

            for(Neuron neuron: l.getNeurons())
            {
                Double error_value = 0.0;
                List<Synapse> synapses_out = neuron.getSynapses_out();
                for(Synapse synapse: synapses_out)
                    error_value += synapse.getNeuron_out().getError_value() * synapse.getWeight();
                neuron.setError_value(error_value);
                LOGGER.trace("Error value for neuron with id="+String.valueOf(neuron.getId())+" is "+String.valueOf(error_value));
            }
        }
        Collections.sort(layers, new LayerAscendingComparator()); // Concurrency issue?
    }
    
    private void updateWeights()
    {
        ValueCalculator vc = new ValueCalculator();

        for(Layer layer: network.getLayers())
        {
            LOGGER.debug("Processing layer id="+String.valueOf(layer.getId()));
            for(Neuron neuron: layer.getNeurons())
            {
                LOGGER.debug("Processing neuron id="+String.valueOf(neuron.getId()));
                Double e = 0.0;
                for(Synapse synin: neuron.getSynapses_in())
                {    
                    LOGGER.trace("Processing synapse synin with id="+String.valueOf(synin.getId()));
                    e += synin.getValue()*synin.getWeight();
                    LOGGER.trace("e="+String.valueOf(e));
                }

                for(Synapse synin: neuron.getSynapses_in())
                {
                    Double delta = learning_factor*neuron.getError_value()*vc.calculateValue(neuron.getActivation_functions(), e, true)*synin.getValue();
                    LOGGER.trace("Delta for synapse synin with id="+String.valueOf(synin.getId())+" is delta="+String.valueOf(delta));
                    synin.setWeight(synin.getWeight() + delta);
                }
            }
        }
    }
    
    private void train()
    {
        NetworkDAO ndao = new NetworkDAO();
        RunManager runManager = new RunManager(network);
        for(int i=0; i<iterations; i++)
        {
            LOGGER.debug("Training with iteration="+String.valueOf(i));
            for(int j=0; j<learning_set.size(); j++)
            {   
                LOGGER.debug("Processing learning set row number="+String.valueOf(j));
                double[] output_vector = runManager.run(ArrayUtils.toPrimitive(learning_set.get(j)));
                double[] expected_vector = ArrayUtils.toPrimitive(training_set.get(j));
                LOGGER.debug("Calculating output errors");
                calculateAndSetOutputErrors(output_vector, expected_vector);
                LOGGER.debug("Calculating inner errors");
                calculateInnerErrors();
                LOGGER.debug("Updating weights");
                updateWeights();
            }
            LOGGER.debug("Iteration done");
            network.setTrainingIterationsDone(i+1);
            ndao.updateItem(network);
        }

        LOGGER.info("training network name="+network.getName()+" finished");
        network.setState("IDLE");
        ndao.updateItem(network);

        LOGGER.info("Sending mail");
        MailSender postman = new MailSender();
        postman.sendNofificationAfterTraining(network.getName());
    }

    @Override
    public void run() {
        train();
    }
}
