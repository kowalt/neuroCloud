/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.dto;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Tomasz
 */
public class NeuronDTO {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String activation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
