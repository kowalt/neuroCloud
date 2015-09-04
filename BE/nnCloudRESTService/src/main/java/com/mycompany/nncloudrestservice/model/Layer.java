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
public class Layer 
{
    private int id;
    private int relative_number;
    private List<Neuron> neurons = new ArrayList<>();
    
    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
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
