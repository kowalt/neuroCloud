/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomasz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Neuron 
{
    @XmlAttribute
    private int id;
    @XmlElementWrapper
    @XmlElement(name="activation_function")
    private List<ActivationFunction> activation_functions = new ArrayList<>();
    @XmlElementWrapper
    @XmlElement(name="synapse_in")
    private List<Synapse> synapses_in = new ArrayList<>();
    @XmlElementWrapper
    @XmlElement(name="synapse_out")
    private List<Synapse> synapses_out = new ArrayList<>();
    @XmlTransient
    private Layer layer;
    
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

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
}
