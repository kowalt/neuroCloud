/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.misc.ActivationFunctionParser;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author Tomasz
 */
public class Generate 
{   
    public void generateNetwork(JSONObject parameters)
    {
        Network network = new Network();
        String name = parameters.getString("name");
        int[] neuronsPerLayer = new int[4]; 
                             
        neuronsPerLayer[0] = parameters.getInt("1stlayer");
        neuronsPerLayer[1] = parameters.getInt("2ndlayer");
        neuronsPerLayer[2] = parameters.getInt("3rdlayer");
        neuronsPerLayer[3] = parameters.getInt("4thlayer");
        
        String activationFunctionRaw = parameters.getString("activationFunction");
        
        ActivationFunctionParser afp = new ActivationFunctionParser();
        List<ActivationFunction> af = afp.parse(activationFunctionRaw);
        
        network.setCreation(Calendar.getInstance().getTime());
        network.setName(name);
        
        List<Layer> layers = new ArrayList<>();
        
        for(int i=0;i<4;i++)
        {   
            Layer l = generateLayer(i+1, neuronsPerLayer[i], af);
            l.setNetwork(network);
            layers.add(l);
        }
        connectLayers(layers); 
        network.setLayers(layers);
        
        NetworkDAO ndao = new NetworkDAO();
        ndao.addItem(network);
    }
    
    private void connectLayers(List<Layer> l)
    {
        Random random = new Random();
        //input
        Layer inputLayer = l.get(0);
        List<Neuron> neuronsIn = inputLayer.getNeurons();
        for(Neuron n: neuronsIn)
        {
            Synapse s = new Synapse();
            s.setWeight(random.nextDouble());
            s.setNeuron_in(null);
            s.setNeuron_out(n);
            addSynapse_in(n,s);
        }
        //inner layers
        for(int i=1;i<4;i++)
        {
            Layer previousLayer = l.get(i-1);
            Layer currentLayer = l.get(i);
            List<Neuron> neuronsPrevious = previousLayer.getNeurons();
            List<Neuron> neuronsCurrent = currentLayer.getNeurons();
            
            for(Neuron nc: neuronsCurrent)
            {
                for(Neuron np: neuronsPrevious)
                {
                    Synapse s = new Synapse();
                    s.setWeight(random.nextDouble());
                    s.setNeuron_in(np);
                    s.setNeuron_out(nc);
                    addSynapse_out(np,s);
                    addSynapse_in(nc,s);
                }
            }
        }
        
        //output
        Layer outputLayer = l.get(3);
        List<Neuron> neuronsOut = outputLayer.getNeurons();
        
        for(Neuron n: neuronsOut)
        {
            Synapse s = new Synapse();
            s.setWeight(random.nextDouble());
            s.setNeuron_out(null);
            s.setNeuron_in(n);
            addSynapse_out(n,s);
        }
    }
    
    private void addSynapse_in(Neuron n,Synapse input)
    {
        List<Synapse> currentSynapses = n.getSynapses_in();
        currentSynapses.add(input);
    }
    
    private void addSynapse_out(Neuron n, Synapse output)
    {
        List<Synapse> currentSynapses = n.getSynapses_out();
        currentSynapses.add(output);
    }
    
    private Layer generateLayer(int relativeNumber, int nOfNeurons, List<ActivationFunction> af)
    {
        Layer l = new Layer();      
        l.setRelative_number(relativeNumber);
        List<Neuron> neurons = new ArrayList<>();
        
        while(nOfNeurons-- > 0)
        {
            Neuron neu = new Neuron();
            neu.setLayer(l);
            List<ActivationFunction> afc = new ArrayList<>();
            for(ActivationFunction a: af)
                afc.add(a.cloneFunctionAndDomainRule());
            
            for(ActivationFunction a: afc)
                a.setNeuron(neu);
            
            neu.setActivation_functions(afc);  
            neurons.add(neu);
        }
        l.setNeurons(neurons);
        return l;
    }
    
}