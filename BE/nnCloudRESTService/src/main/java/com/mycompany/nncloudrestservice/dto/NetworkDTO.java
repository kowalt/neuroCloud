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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomasz
 */
@XmlRootElement(name="network")
@XmlAccessorType(XmlAccessType.FIELD)
public class NetworkDTO {
   @XmlAttribute
   private Integer id = null;
   @XmlAttribute
   private String name;

   @XmlElement(name="synapse")
   private List<SynapseDTO> synapsesInput;

   @XmlElement(name="layer")
   private LayerDTO layer1;

   @XmlElement(name="synapse")
   private List<SynapseDTO> synapsesBetween1and2Layer;

   @XmlElement(name="layer")
   private LayerDTO layer2;

   @XmlElement(name="synapse")
   private List<SynapseDTO> synapsesBetween2and3Layer;

   @XmlElement(name="layer")
   private LayerDTO layer3;

   @XmlElement(name="synapse")
   private List<SynapseDTO> synapsesBetween3and4Layer;

   @XmlElement(name="layer")
   private LayerDTO layer4;

   @XmlElement(name="synapse")
   private List<SynapseDTO> synapsesOutput;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SynapseDTO> getSynapsesInput() {
        return synapsesInput;
    }

    public void setSynapsesInput(List<SynapseDTO> synapsesInput) {
        this.synapsesInput = synapsesInput;
    }

    public LayerDTO getLayer1() {
        return layer1;
    }

    public void setLayer1(LayerDTO layer1) {
        this.layer1 = layer1;
    }

    public List<SynapseDTO> getSynapsesBetween1and2Layer() {
        return synapsesBetween1and2Layer;
    }

    public void setSynapsesBetween1and2Layer(List<SynapseDTO> synapsesBetween1and2Layer) {
        this.synapsesBetween1and2Layer = synapsesBetween1and2Layer;
    }

    public LayerDTO getLayer2() {
        return layer2;
    }

    public void setLayer2(LayerDTO layer2) {
        this.layer2 = layer2;
    }

    public List<SynapseDTO> getSynapsesBetween2and3Layer() {
        return synapsesBetween2and3Layer;
    }

    public void setSynapsesBetween2and3Layer(List<SynapseDTO> synapsesBetween2and3Layer) {
        this.synapsesBetween2and3Layer = synapsesBetween2and3Layer;
    }

    public LayerDTO getLayer3() {
        return layer3;
    }

    public void setLayer3(LayerDTO layer3) {
        this.layer3 = layer3;
    }

    public List<SynapseDTO> getSynapsesBetween3and4Layer() {
        return synapsesBetween3and4Layer;
    }

    public void setSynapsesBetween3and4Layer(List<SynapseDTO> synapsesBetween3and4Layer) {
        this.synapsesBetween3and4Layer = synapsesBetween3and4Layer;
    }

    public LayerDTO getLayer4() {
        return layer4;
    }

    public void setLayer4(LayerDTO layer4) {
        this.layer4 = layer4;
    }

    public List<SynapseDTO> getSynapsesOutput() {
        return synapsesOutput;
    }

    public void setSynapsesOutput(List<SynapseDTO> synapsesOutput) {
        this.synapsesOutput = synapsesOutput;
    }
}
