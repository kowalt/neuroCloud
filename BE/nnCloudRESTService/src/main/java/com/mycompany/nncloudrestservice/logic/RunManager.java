/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.daos.NetworkDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomasz
 */
public class RunManager {
    public void run(int networkId, int serverId)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry("178.62.119.189:2001");
            INetworkCalculatorServer stub = (INetworkCalculatorServer) registry.lookup("ServerCPU");
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
