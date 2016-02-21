/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import nncgpuserver.INetworkCalculatorServer;
import model.original.ActivationFunction;
import model.original.Layer;
import model.original.Network;
import model.original.Neuron;
import model.original.Synapse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tomasz
 */
public class ServerCPU implements INetworkCalculatorServer{

    private Network n;
    
    @Override
    public void loadNetworkIntoGPU(Network n) {
       this.n = n;
    }

    @Override
    public double[] run(double[] input) throws RemoteException {
       Network n = new Network();
       ValueCalculator vc = new ValueCalculator();
       
       List<Layer> lList = n.getLayers();
              
       List<Neuron> neurons = lList.get(0).getNeurons();       
       List<Double> last_layer_output=new ArrayList<>();
       
       //convert array to list
       for(int i=0;i<input.length;i++)
       {
           last_layer_output.add(input[i]);
       }
       
               
       double input_value_sum = 0.0; 
       
       //firstLayer
       for(Neuron neu: neurons)
       {    
           List<Synapse> synapses_input = neu.getSynapses_in();
           input_value_sum = 0.0; 
           
           for(int i=0; i<synapses_input.size(); i++)
               input_value_sum += input[i]*synapses_input.get(i).getWeight();
                                
           List<ActivationFunction> activation_functions = neu.getActivation_functions();
           
           last_layer_output.add(vc.calculateValue(activation_functions, input_value_sum));  
       }
        
       //innerLayers
       List<Layer> innerLayer = lList.subList(1, 3);
       
       for(int i=0;i<innerLayer.size();i++)
       {
            neurons = lList.get(i).getNeurons();

            for(Neuron neu: neurons)
            {
                List<Synapse> synapses_input = neu.getSynapses_in();
                input_value_sum = 0.0;
                
                for(int j=0; j<synapses_input.size(); j++)
                {
                    input_value_sum += input[i]*synapses_input.get(i).getWeight();  
                }
                
                List<ActivationFunction> activation_functions = neu.getActivation_functions();
                    
            }
       }
       

       
       //lastLayer
       
       return null;
    }
}
