/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Tomasz
 */
public class RunManager {
    public void run(int networkId, int serverId)
    {
        try
        {
            
            Registry registry = LocateRegistry.getRegistry(host,port);
            INetworkCalculatorServer stub = (INetworkCalculatorServer) registry.lookup("ServerCPU");
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
