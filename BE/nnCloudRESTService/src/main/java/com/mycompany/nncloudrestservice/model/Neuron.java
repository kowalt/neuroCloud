/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import java.util.Set;

/**
 *
 * @author Tomasz
 */
public class Neuron 
{
    private int id;
    private Set<Synapse> synapses;
    private Set<ActivationFunction> activation_functions;
    
    public Set getSynapses() {
        return synapses;
    }

    public void setSynapses(Set synapses) {
        this.synapses = synapses;
    }

    public int getId_neuron() {
        return id;
    }

    public void setId_neuron(int id_neuron) {
        this.id = id_neuron;
    }
    
    public Set<ActivationFunction> getActivation_functions() {
        return activation_functions;
    }

    public void setActivation_functions(Set<ActivationFunction> activation_functions) {
        this.activation_functions = activation_functions;
    }

}
