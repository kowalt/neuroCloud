/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tomasz
 */
public class Synapse 
{
    private int id;
    private double weight;
    private Set<Neuron> neurons = new HashSet<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(Set<Neuron> neurons) {
        this.neurons = neurons;
    }
    
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }    
}
