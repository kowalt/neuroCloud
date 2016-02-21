/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.util.List;
import model.original.ActivationFunction;
import model.original.Layer;
import model.original.Neuron;
import model.original.Synapse;

/**
 *
 * @author Tomasz
 */
public class NetworkProcessor 
{
    private ValueCalculator vc;
            
    public NetworkProcessor()
    {
        vc = new ValueCalculator();
    }        
    
    public double[] runLayer(Layer l, double[] input)
    {
        double[] output=null;
        int index = 0;
        for(Neuron neu: l.getNeurons())
        {        
            output = new double[l.getNeurons().size()];
            List<Synapse> synapses_input = neu.getSynapses_in();
            double input_value_sum = 0.0; 
            
            for(int i=0; i<synapses_input.size(); i++)
                input_value_sum += input[i]*synapses_input.get(i).getWeight();
                
            List<ActivationFunction> activation_functions = neu.getActivation_functions();
            
            output[index++] = vc.calculateValue(activation_functions, input_value_sum);
        }
        return output;
    }
}
