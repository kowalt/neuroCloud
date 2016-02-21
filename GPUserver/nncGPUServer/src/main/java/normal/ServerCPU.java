/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nncgpuserver.INetworkCalculatorServer;
import model.original.ActivationFunction;
import model.original.Layer;
import model.original.Network;
import model.original.Neuron;
import model.original.Synapse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Tomasz
 */
public class ServerCPU implements INetworkCalculatorServer{

    private Network n;
    
    @Override
    public void loadNetworkIntoGPU(Network n) {
       this.n = n;
    }

    @Override
    public double[] run(double[] input) throws RemoteException {
        Network n = new Network();
        ValueCalculator vc = new ValueCalculator();

        List<Layer> lList = n.getLayers();

        List<Neuron> neurons = lList.get(0).getNeurons();       

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
