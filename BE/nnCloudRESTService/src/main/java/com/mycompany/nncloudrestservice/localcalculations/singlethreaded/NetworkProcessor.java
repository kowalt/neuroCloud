/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations.singlethreaded;

import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class NetworkProcessor 
{
    private ValueCalculator vc;
    private Network n;
    
    public NetworkProcessor(Network n)
    {
        vc = new ValueCalculator();
        this.n = n;
    }

    public void setInput(double[] input)
    {       
        int index = 0;

        for(Neuron neuron: n.getLayers().get(0).getNeurons())
        {
            Synapse syn_in = neuron.getSynapses_in().get(0);
            syn_in.setValue(input[index++]);
        }
    }        

    public void runLayer(Layer l)
    {
        for(Neuron neu: l.getNeurons())
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
}