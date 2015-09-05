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
    private List<ActivationFunction> activation_functions = new ArrayList<>();
    transient private List<Synapse> synapses_in = new ArrayList<>();
    transient private List<Synapse> synapses_out = new ArrayList<>();
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ActivationFunction> getActivation_functions() {
        return activation_functions;
    }

    public void setActivation_functions(List<ActivationFunction> activation_functions) {
        this.activation_functions = activation_functions;
    }

    public List<Synapse> getSynapses_in() {
        return synapses_in;
    }

    public void setSynapses_in(List<Synapse> synapses_in) {
        this.synapses_in = synapses_in;
    }

    public List<Synapse> getSynapses_out() {
        return synapses_out;
    }

    public void setSynapses_out(List<Synapse> synapses_out) {
        this.synapses_out = synapses_out;
    }
    
}
