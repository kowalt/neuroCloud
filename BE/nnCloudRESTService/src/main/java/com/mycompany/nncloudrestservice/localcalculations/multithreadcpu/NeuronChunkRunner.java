package com.mycompany.nncloudrestservice.localcalculations.multithreadcpu;

import java.util.List;

import com.mycompany.nncloudrestservice.localcalculations.ValueCalculator;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;

public class NeuronChunkRunner implements Runnable 
{
    private Layer l;
    private List<Neuron> chunk;
    private ValueCalculator vc;

    public NeuronChunkRunner(Layer l, List<Neuron> chunk)
    {
        this.l = l;
        this.chunk = chunk;
        vc = new ValueCalculator();
    }

    private void runChunk(Layer l, List<Neuron> chunk)
    {
        for(Neuron neu: chunk)
        {        
            List<Synapse> synapses_input = neu.getSynapses_in();
            List<Synapse> synapses_output = neu.getSynapses_out();
            double input_value_sum = 0.0; 

            for(int i=0; i<synapses_input.size(); i++)
                input_value_sum += synapses_input.get(i).getValue()*synapses_input.get(i).getWeight();

            List<ActivationFunction> activation_functions = neu.getActivation_functions();

            for(Synapse syn_out: synapses_output)
                syn_out.setValue(vc.calculateValue(activation_functions, input_value_sum, false));
        }
    }

    @Override
    public void run() {
        runChunk(l,chunk);
    }
}
