/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomasz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Synapse 
{
    @XmlAttribute
    private int id;
    private double weight;
    @XmlTransient
    private Neuron neuron_in;
    @XmlTransient
    private Neuron neuron_out;
    
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Neuron getNeuron_in() {
        return neuron_in;
    }

    public void setNeuron_in(Neuron neuron_in) {
        this.neuron_in = neuron_in;
    }

    public Neuron getNeuron_out() {
        return neuron_out;
    }

    public void setNeuron_out(Neuron neuron_out) {
        this.neuron_out = neuron_out;
    }
    
    
}
