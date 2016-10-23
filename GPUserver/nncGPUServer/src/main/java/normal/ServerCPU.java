/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import nncgpuserver.INetworkCalculatorServer;
import pojo.original.Layer;
import pojo.original.Network;
/**
 *
 * @author Tomasz
 */
public class ServerCPU implements INetworkCalculatorServer{
 
    private Network n;
    private final String LABEL = "Very basic single-threaded CPU server";

    public ServerCPU() throws RemoteException
    {
    
    }
    
    @Override
    public void loadNetworkIntoGPU(Network n) throws RemoteException {
       this.n = n;
    }

    @Override
    public double[] run(double[] input) throws RemoteException {
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
    
    private int getLargestLayerSize(List<Layer> lList) throws RemoteException
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

    @Override
    public void sayHello() throws RemoteException {
        System.out.println("Hello world!");
    }

    @Override
    public String getLabel() throws RemoteException {
            return LABEL;
    }
}
