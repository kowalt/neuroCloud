/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Tomasz
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LayerDTO {
    @XmlAttribute
    private int relative_number;
    @XmlElement(name="neuron")
    private List<NeuronDTO> neurons;
    
    public int getRelative_number() {
        return relative_number;
    }

    public void setRelative_number(int relative_number) {
        this.relative_number = relative_number;
    }

    public List<NeuronDTO> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<NeuronDTO> neurons) {
        this.neurons = neurons;
    }
}
