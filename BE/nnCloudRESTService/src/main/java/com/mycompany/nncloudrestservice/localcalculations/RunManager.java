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
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Tomasz
 */
public class RunManager{

    private final Logger LOGGER = LogManager.getLogger(RunManager.class);
    
    private final Network n;
    private Mode mode = Mode.SINGLETHREAD;
    
    public enum Mode
    {
        SINGLETHREAD,MULTITHREAD
    }
    
    public RunManager(int id)
    {
        LOGGER.debug("Initializing RunManager");
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
        {
            LOGGER.debug("Multithread mode on");
            np = new com.mycompany.nncloudrestservice.localcalculations.multithreadcpu.RunProcessor(n);
        }
        else
        {
            LOGGER.debug("Singlethread mode on");
            np = new com.mycompany.nncloudrestservice.localcalculations.singlethreaded.RunProcessor(n);
        }
        
        LOGGER.debug("Setting input: " + Arrays.toString(input));
        np.setInput(input);

        for(Layer l: lList)
        {
            LOGGER.debug("Invoking runLayer for layer number " + String.valueOf(l.getRelative_number()));
            np.runLayer(l);
        }

        List<Neuron> outNeuList = n.getLayers().get(n.getLayers().size()-1).getNeurons();
        double [] output_vector = new double[outNeuList.size()];
        int index = 0;
        for(Neuron n: outNeuList)
            output_vector[index++] = n.getSynapses_out().get(0).getValue();

        String callerClassName = new Exception().getStackTrace()[1].getClassName();
        
        if(!callerClassName.contains("Test"))
        {        
            NetworkDAO ndao = new NetworkDAO();
            ndao.updateItem(n);
        }
        return output_vector;
    }
    
    public void setMode(Mode mode)
    {
        this.mode = mode;
    }
}
