package com.mycompany.nncloudrestservice.localcalculations.multithreadcpu;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.nncloudrestservice.localcalculations.singlethreaded.ValueCalculator;
import com.mycompany.nncloudrestservice.pojo.ActivationFunction;
import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import com.mycompany.nncloudrestservice.localcalculations.multithreadcpu.NeuronChunkRunner;

public class NetworkProcessor 
{
    private Network n;
    
    public NetworkProcessor(Network n)
    {
        this.n = n;
    }

    public void setInput(double[] input)
    {       
        int index = 0;

        for(Neuron neuron: n.getLayers().get(0).getNeurons())
        {
            Synapse syn_in = neuron.getSynapses_in().get(0);
            syn_in.setValue(input[index++]);
        }
    }        

    public void runLayer(Layer l)
    {
    	List<Neuron> nList = l.getNeurons();
    	int threads = Runtime.getRuntime().availableProcessors();
    	int delta = nList.size()/threads;
    	List<List<Neuron>> chunks = new ArrayList<>();

    	for(int i=0; i<threads; i++)
    	{	
    		if((i*delta+delta) < nList.size())
    			chunks.get(i).addAll(nList.subList(i*delta, i*delta+delta));
    		else
    			chunks.get(i).addAll(nList.subList(i*delta, nList.size()));    			
    		if(i == (threads - 1))
    			chunks.get(i).addAll(nList.subList(i*delta+delta, nList.size()));
    	}
    	
    	
    	for(List<Neuron> chunk: chunks)
    		(new Thread(NeuronChunkRunner(l, chunk))).start();
    }
}