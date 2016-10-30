/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations.singlethreaded;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class SingleThreadRunManager{
 
    private Network n;

    public SingleThreadRunManager(int id)
    {
        NetworkDAO ndao = new NetworkDAO();
        ndao.setLazyLoadMode(false);
        n = ndao.getItem(String.valueOf(id));
    }
    
    public SingleThreadRunManager(Network n)
    {
    	this.n = n;
    }

    public double[] run(double[] input) {
        List<Layer> lList = n.getLayers();
        
        NetworkProcessor np = new NetworkProcessor(n);
        
        np.setInput(input);
        
        for(Layer l: lList)
            np.runLayer(l);

        List<Neuron> outNeuList = n.getLayers().get(3).getNeurons();
        double [] output_vector = new double[outNeuList.size()];
        int index = 0;
        for(Neuron n: outNeuList)
        {
            output_vector[index++] = n.getSynapses_out().get(0).getValue();
        }
        return output_vector;
    }
}
