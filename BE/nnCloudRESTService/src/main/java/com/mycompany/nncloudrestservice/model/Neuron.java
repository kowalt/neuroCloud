/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class Neuron 
{
    private int id;
    private List<Synapse> synapses = new ArrayList<>();
    private List<ActivationFunction> activation_functions = new ArrayList<>();
    transient private List<Synapse> inputSynapses;
    transient private List<Synapse> outputSynapses;
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public void setSynapses(List<Synapse> synapses) {
        this.synapses = synapses;
    }
        
    public List<ActivationFunction> getActivation_functions() {
        return activation_functions;
    }

    public void setActivation_functions(List<ActivationFunction> activation_functions) {
        this.activation_functions = activation_functions;
    }  
}
