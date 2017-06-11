/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.utils;

import com.mycompany.nncloudrestservice.explorers.SynapseExplorer;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.logic.Generate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * Class for testing purposes
 * @author Tomek
 */
public class TestUtils {
    public static Network createNetwork1()
    {
        Network n = new Network();
        List<Layer> layers = new ArrayList<Layer>();
        Layer l1 = new Layer();
        Neuron n1 = new Neuron();
        ActivationFunction af = new ActivationFunction();
        af.setFunction("x");
        af.setDomain_rule("x<1.0");
        List<ActivationFunction> afl = new ArrayList<>();
        afl.add(af);
        n1.setActivation_functions(afl);
        n1.setLayer(l1);
        List<Neuron> neuList = new ArrayList<>();
        neuList.add(n1);
        l1.setNeurons(neuList);
        
        Layer l2 = new Layer();
        Neuron n2 = new Neuron();
        ActivationFunction af2 = new ActivationFunction();
        af2.setFunction("x");
        af2.setDomain_rule("x<1.0");
        List<ActivationFunction> afl2 = new ArrayList<>();
        afl2.add(af2);
        n2.setActivation_functions(afl2);
        n2.setLayer(l2);
        List<Neuron> neuList2 = new ArrayList<>();
        neuList2.add(n2);
        l2.setNeurons(neuList2);
        
        layers.add(l1);
        layers.add(l2);
        
        Synapse s1 = new Synapse();
        s1.setNeuron_out(n1);
        s1.setValue(0.1);
        s1.setWeight(0.5);
        n1.getSynapses_in().add(s1);
        
        Synapse s2 = new Synapse();
        s2.setNeuron_in(n1);
        s2.setNeuron_out(n2);
        s2.setWeight(0.9);
        n1.getSynapses_out().add(s2);
        n2.getSynapses_in().add(s2);
        
        Synapse s3 = new Synapse();
        s3.setNeuron_in(n2);
        s3.setWeight(0.75);
        n2.getSynapses_out().add(s3);
        
        n.setLayers(layers);
        return n;
    }
    
    public static Network createNetwork2()
    {
        Generate generator = new Generate();
        
        JSONObject parameters = new JSONObject("{"
                + "'name': 'network2',"
                + "'neuronsPerLayer':[4,3,2,2]," 
                + "'activationFunction':'Tanh(x)'" 
                + "}");
        Network network = generator.generateNetwork(parameters,Generate.Mode.RETURN_ONLY);
        
        SynapseExplorer se = new SynapseExplorer(network);
        se.setWeights(0.5);
        
        List<Neuron> neurons = network.getLayers().get(0).getNeurons();
        
        for(Neuron n: neurons)
        {
            List<Synapse> synapses = n.getSynapses_in();
            
            for(Synapse syn: synapses)
                syn.setValue(0.4);
        }

        return network;
    }
    
    public static Network createNetwork3()
    {
        Generate generator = new Generate();
        
        JSONObject parameters = new JSONObject("{"
                + "'name': 'network3',"
                + "'neuronsPerLayer':[3,2,1]," 
                + "'activationFunction':'Tanh(x)'" 
                + "}");
        Network network = generator.generateNetwork(parameters,Generate.Mode.RETURN_ONLY);
        
        SynapseExplorer se = new SynapseExplorer(network);
        se.setWeights(0.5);
        
        List<Neuron> neurons = network.getLayers().get(0).getNeurons();
        
        for(Neuron n: neurons)
        {
            List<Synapse> synapses = n.getSynapses_in();
            
            for(Synapse syn: synapses)
                syn.setValue(0.73);
        }
        return network;
    }        
}
