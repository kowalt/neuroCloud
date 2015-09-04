/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.controllers;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.misc.ActivationFunctionParser;
import com.mycompany.nncloudrestservice.model.ActivationFunction;
import com.mycompany.nncloudrestservice.model.Layer;
import com.mycompany.nncloudrestservice.model.Network;
import com.mycompany.nncloudrestservice.model.Neuron;
import com.mycompany.nncloudrestservice.model.Synapse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class GenerateController 
{   
    public void generateNetwork(JSONObject parameters)
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
        List<ActivationFunction> af = afp.parse(activationFunctionRaw);
        
        network.setCreation(Calendar.getInstance().getTime());
        network.setName(name);
        
        List<Layer> layers = new ArrayList<>();
        
        for(int i=0;i<4;i++)
        {   
            Layer l = generateLayer(i, neuronsPerLayer[i], af);
            layers.add(l);
        }
        
        connectLayers(layers);
        
        NetworkDAO ndao = new NetworkDAO();
        ndao.addItem(network);
    }
    
    private void connectLayers(List<Layer> l)
    {
        Layer in = l.get(0);
        
        List<Neuron> inNeurons = in.getNeurons();
        Random r = new Random();
        List<Synapse> lsyn = null;
        Synapse s;
        Layer out;
        List<Neuron> outNeurons;
        
        for(Neuron n: inNeurons)
        {
            s = new Synapse();
            s.setWeight(r.nextDouble());
            
            (lsyn = new ArrayList<>()).add(s);            
            n.setSynapses(lsyn);
        }
        
        lsyn = null;
        
        //inner layers
        for(int i=1;i<3;i++)
        {
            out = in;
            in = l.get(i);
            
            inNeurons = in.getNeurons();
            outNeurons = out.getNeurons();
            
            for(Neuron no: outNeurons)
            {
                for(Neuron ni: inNeurons)
                {
                    lsyn = new ArrayList<>();
                    s = new Synapse();                 
                    s.setWeight(r.nextDouble());
                    
                    lsyn.add(s);
                }
                no.setSynapses(lsyn);
            }
        }
        
        //output layer
        out = in;
        outNeurons = out.getNeurons();
        
        for(Neuron n: outNeurons)
        {
            s = new Synapse();
            s.setWeight(r.nextDouble());
            (lsyn = new ArrayList<>()).add(s);
            
            n.setSynapses(lsyn);
        } 
    }
    
    private Layer generateLayer(int relativeNumber, int nOfNeurons, List<ActivationFunction> af)
    {
        Layer l = new Layer();
        
        l.setRelative_number(relativeNumber);
        
        List<Neuron> neurons = new ArrayList<>();
        
        while(nOfNeurons-- > 0)
            neurons.add(generateNeuron(af));
        
        l.setNeurons(neurons);
        
        return l;
    }
    
    private Neuron generateNeuron(List<ActivationFunction> af)
    {
        Neuron n = new Neuron();    
        n.setActivation_functions(af);        
        return n;
    }
}