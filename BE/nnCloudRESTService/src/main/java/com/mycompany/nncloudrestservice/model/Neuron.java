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
    private int id_neuron;
    private int id_layer;
    private Set synapses;
    private Set activation_functions;
    
    public Set getSynapses() {
        return synapses;
    }

    public void setSynapses(Set synapses) {
        this.synapses = synapses;
    }

    public int getId_neuron() {
        return id_neuron;
    }

    public void setId_neuron(int id_neuron) {
        this.id_neuron = id_neuron;
    }

    public int getId_layer() {
        return id_layer;
    }

    public void setId_layer(int id_layer) {
        this.id_layer = id_layer;
    }

    public Set getActivation_functions() {
        return activation_functions;
    }

    public void setActivation_functions(Set activation_functions) {
        this.activation_functions = activation_functions;
    }

}
