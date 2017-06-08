/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations;

import com.mycompany.nncloudrestservice.pojo.Layer;

/**
 *
 * @author Tomek
 */
public interface IRunProcessor {
    public void setInput(double[] input);
    public void runLayer(Layer l);
    
}
