/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.model;

/**
 *
 * @author Tomasz
 */
public class ActivationFunction
{
    private int id;

    private String function;
    private String domain_rule;
    private Neuron neuron;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public void setNeuron(Neuron neuron) {
        this.neuron = neuron;
    }
    
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDomain_rule() {
        return domain_rule;
    }

    public void setDomain_rule(String domain_rule) {
        this.domain_rule = domain_rule;
    }
}
