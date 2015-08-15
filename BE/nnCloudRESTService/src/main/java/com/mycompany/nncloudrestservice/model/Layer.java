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
    private Set neurons;

    public Set getNeurons() {
        return neurons;
    }

    public void setNeurons(Set neurons) {
        this.neurons = neurons;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Layer other = (Layer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
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
    private int relative_number;
}
