/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.pojo.Network;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Tomasz
 */
public interface INetworkCalculatorServer extends Remote {
    
    public void loadNetworkIntoGPU(Network n) throws RemoteException;
    public double[] run(double[] input) throws RemoteException;
    public void sayHello() throws RemoteException;        
}
