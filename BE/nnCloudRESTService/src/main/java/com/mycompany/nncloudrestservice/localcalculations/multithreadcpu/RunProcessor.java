package com.mycompany.nncloudrestservice.localcalculations.multithreadcpu;

import com.mycompany.nncloudrestservice.localcalculations.IRunProcessor;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.nncloudrestservice.pojo.Layer;
import com.mycompany.nncloudrestservice.pojo.Network;
import com.mycompany.nncloudrestservice.pojo.Neuron;
import com.mycompany.nncloudrestservice.pojo.Synapse;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;



public class RunProcessor implements IRunProcessor
{
    private Network n;
    private static final Logger LOGGER = LogManager.getLogger(RunProcessor.class);

    public RunProcessor(Network n)
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
    	int delta = (int)Math.ceil((double)nList.size()/(double)threads);
    	List<List<Neuron>> chunks = new ArrayList<>();

        StopWatch stopWatch = new Log4JStopWatch("Starting network run on " + String.valueOf(threads)+" threads.");
        
        LOGGER.info("Starting network run on " + String.valueOf(threads)+" threads. 2nd message");

    	for(int i=0; i<threads; i++)
    	{
            chunks.add(new ArrayList<Neuron>());
            if((i*delta+delta) < nList.size()) //if
                chunks.get(i).addAll(nList.subList(i*delta, i*delta+delta));
            else
            {    
                chunks.get(i).addAll(nList.subList(i*delta, nList.size()));
                break;
            }
            if(i == (threads - 1)) //if last thread
                chunks.get(i).addAll(nList.subList(i*delta+delta, nList.size()));
    	}
        
        List<Thread> tlist = new ArrayList<Thread>();
        
    	for(List<Neuron> chunk: chunks)
        {    
            Thread thread = (new Thread(new NeuronChunkRunner(l, chunk)));
            thread.start();
            tlist.add(thread);
        }
        
        for(Thread thread: tlist)
        {        
            try {
                thread.join();
            } catch (InterruptedException ex) {
                LOGGER.error("Thread interrupted");
            }
        }
        stopWatch.stop();
    }
}