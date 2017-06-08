/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.localcalculations.multithreadcpu.RunProcessor;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class RunManager{
 
    private Network n;
    private Mode mode = Mode.SINGLETHREAD;
    
    private enum Mode
    {
        SINGLETHREAD,MULTITHREAD
    }
    
    public RunManager(int id)
    {
        NetworkDAO ndao = new NetworkDAO();
        ndao.setLazyLoadMode(false);
        n = ndao.getItem(String.valueOf(id));
    }
    
    public RunManager(Network n)
    {
    	this.n = n;
    }

    public double[] run(double[] input) {
        List<Layer> lList = n.getLayers();
        
        IRunProcessor np;

        if(mode == Mode.MULTITHREAD)
            np = new com.mycompany.nncloudrestservice.localcalculations.multithreadcpu.RunProcessor(n);
        else
            np = new com.mycompany.nncloudrestservice.localcalculations.singlethreaded.RunProcessor(n);
        
        np.setInput(input);
        
        for(Layer l: lList)
            np.runLayer(l);

        List<Neuron> outNeuList = n.getLayers().get(n.getLayers().size()-1).getNeurons();
        double [] output_vector = new double[outNeuList.size()];
        int index = 0;
        for(Neuron n: outNeuList)
            output_vector[index++] = n.getSynapses_out().get(0).getValue();

        NetworkDAO ndao = new NetworkDAO();
        ndao.updateItem(n);

        return output_vector;
    }
}
