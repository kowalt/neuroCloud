/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.explorers;

import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomasz
 */
public class SynapseExplorer {
    private Network network;
    private Map<Integer, Synapse> registry;
    
    public SynapseExplorer(Network n)
    {
        this.network = n;
        registry = new HashMap<Integer, Synapse>();
    }
    
    private boolean isInRegistry(Integer synId)
    {
        return registry.get(synId) != null;
    }
    
    /**
     * Returns all unique synapses
    */
    public List<Synapse> getAll()
    {        

        for(Layer layer: network.getLayers())
            for(Neuron neuron: layer.getNeurons())
            {
                for(Synapse synIn: neuron.getSynapses_in())
                {
                    if(isInRegistry(synIn.getId()))
                        registry.put(synIn.getId(), synIn);
                }
                
                for(Synapse synOut: neuron.getSynapses_out())
                {
                    if(isInRegistry(synOut.getId()))
                        registry.put(synOut.getId(), synOut);
                }
            }
        List<Synapse> synList = new ArrayList<>();
        synList.addAll(registry.values());
        return synList;
    }
    
    /**
     * Set weights of all synapses
     */
    public void setWeights(double value)
    {
        List<Synapse> synapses = getAll();
        for(Synapse s: synapses)
            s.setWeight(value);
    }
}
