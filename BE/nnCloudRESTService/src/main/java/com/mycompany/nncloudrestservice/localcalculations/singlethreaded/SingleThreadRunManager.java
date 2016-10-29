/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.localcalculations.singlethreaded;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
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
        double[] output = new double[getLargestLayerSize(lList)];
        NetworkProcessor np = new NetworkProcessor();
        double[] internalInput = input;
        
        for(Layer l: lList)
        {
            output = np.runLayer(l, internalInput);
            internalInput = output;
        }

        return output;
    }
    
    private int getLargestLayerSize(List<Layer> lList)
    {
        Iterator<Layer> iter = lList.iterator();
        
        int largest = 0;
        int cursize;
        
        while(iter.hasNext())
        {
            cursize = iter.next().getNeurons().size();
            largest = (largest >= cursize ) ? largest : cursize;
        }
        return largest;
    }
}
