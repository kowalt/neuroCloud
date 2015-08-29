/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.controllers;

import com.mycompany.nncloudrestservice.misc.ActivationFunctionParser;
import com.mycompany.nncloudrestservice.model.ActivationFunction;
import com.mycompany.nncloudrestservice.model.Layer;
import com.mycompany.nncloudrestservice.model.Network;
import com.mycompany.nncloudrestservice.model.Neuron;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class GenerateController 
{   
    
    public Network generateNetwork(JSONObject parameters)
    {
        Network network = new Network();
        String name = parameters.getString("name");
        int[] neuronsPerLayer = new int[3]; 
        
        neuronsPerLayer[0] = Integer.parseInt(parameters.getString("1stlayer"));
        neuronsPerLayer[1] = Integer.parseInt(parameters.getString("2ndlayer"));
        neuronsPerLayer[2] = Integer.parseInt(parameters.getString("3rdlayer"));
        neuronsPerLayer[3] = Integer.parseInt(parameters.getString("4thlayer"));
        
        String activationFunctionRaw = parameters.getString("activationFunction");
        
        ActivationFunctionParser afp = new ActivationFunctionParser();
        ActivationFunction af = afp.parse(activationFunctionRaw);
        
        network.setCreation(Calendar.getInstance().getTime());
        network.setName(name);
        
        Set<Layer> layers = new HashSet<>();
        
        for(int i=0;i<4;i++)
        {   
            Layer l = generateLayer(i, neuronsPerLayer[i], af);
            layers.add(l);
        }
                
        return network;
    }
    
    private Layer generateLayer(int relativeNumber, int nOfNeurons, ActivationFunction af)
    {
        Layer l = new Layer();
        
        l.setRelative_number(relativeNumber);
        
        Set<Neuron> neurons = new HashSet<Neuron>();
        
        while(nOfNeurons-- > 0)
            neurons.add(generateNeuron(af));
        
        l.setNeurons(neurons);
        
        return l;
    }
    
    private Neuron generateNeuron(ActivationFunction af)
    {
        Neuron n = new Neuron();
        
        // TODO need to sleep;
        return n;
    }
      
}
