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
public class Layer 
{
    private int id;
    private int relative_number;
    private Set<Neuron> neurons;

    public Set<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(Set<Neuron> neurons) {
        this.neurons = neurons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelative_number() {
        return relative_number;
    }

    public void setRelative_number(int relative_number) {
        this.relative_number = relative_number;
    }
}
