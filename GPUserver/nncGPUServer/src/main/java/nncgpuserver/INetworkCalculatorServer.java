/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pojo.original.Network;

/**
 *
 * @author Tomasz
 */
public interface INetworkCalculatorServer extends Remote {
    
    public void loadNetworkIntoGPU(Network n) throws RemoteException;
    public double[] run(double[] input, int networkID) throws RemoteException;
    public void sayHello() throws RemoteException;
    public String getLabel() throws RemoteException;
}
