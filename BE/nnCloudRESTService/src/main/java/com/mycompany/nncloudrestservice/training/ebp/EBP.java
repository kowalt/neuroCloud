package com.mycompany.nncloudrestservice.training.ebp;

import com.mycompany.nncloudrestservice.comparators.LayerAscendingComparator;
import com.mycompany.nncloudrestservice.comparators.LayerDescendingComparator;
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
            i++;
        }
    }
    
    private void calculateInnerErrors()
    {
        List<Layer> layers = network.getLayers();
        Collections.sort(layers, new LayerDescendingComparator());
        for(Layer l: layers)
        {
            if(l.getRelative_number() == layers.size())
                continue;

            for(Neuron neuron: l.getNeurons())
            {
                Double error_value = 0.0;
                List<Synapse> synapses_out = neuron.getSynapses_out();
                for(Synapse synapse: synapses_out)
                    error_value += synapse.getNeuron_out().getError_value() * synapse.getWeight();
                neuron.setError_value(error_value);
            }
        }
        Collections.sort(layers, new LayerAscendingComparator());
    }
    
    private void updateWeights()
    {
        ValueCalculator vc = new ValueCalculator();

        for(Layer layer: network.getLayers())
        {
            for(Neuron neuron: layer.getNeurons())
            {
                Double e = 0.0;
                for(Synapse synin: neuron.getSynapses_in())
                    e += synin.getValue()*synin.getWeight();

                for(Synapse synin: neuron.getSynapses_in())
                {
                    Double delta = learning_factor*neuron.getError_value()*vc.calculateValue(neuron.getActivation_functions(), e, true)*synin.getValue();
                    synin.setWeight(synin.getWeight() + delta);
                }
            }
        }
    }
    
    private void train(Network n)
    {
        RunManager runManager = new RunManager(network);
        for(int i=0; i<iterations; i++)
        {
            for(int j=0; j<learning_set.size(); j++)
            {    
                double[] output_vector = runManager.run(ArrayUtils.toPrimitive(learning_set.get(j)));
                double[] expected_vector = ArrayUtils.toPrimitive(training_set.get(j));
                calculateAndSetOutputErrors(output_vector, expected_vector);
                calculateInnerErrors();
                updateWeights();
            }
        }
        
        LOGGER.info("training network "+n.getName()+" finished");
        
        MailSender postman = new MailSender();
        postman.sendNofificationAfterTraining(n.getName());
    }

    @Override
    public void run() {
        train(network);
    }
}
