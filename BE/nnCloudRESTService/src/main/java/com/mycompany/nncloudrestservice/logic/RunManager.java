/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nncloudrestservice.logic;

import com.mycompany.nncloudrestservice.pojo.RMIServer;
import com.mycompany.nncloudrestservice.utils.ServerListContainer;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
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
    public void run(int networkId, int serverId, double[] vector)
    {
        try
        {
            RMIServer server = ServerListContainer.getAt(serverId);
            Registry registry = LocateRegistry.getRegistry(server.getHost(),server.getPort());

            try {
                INetworkCalculatorServer stub = (INetworkCalculatorServer) registry.lookup(server.getName());
                stub.run(vector);
            } catch (NotBoundException ex) {
                Logger.getLogger(RunManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AccessException ex) {
                Logger.getLogger(RunManager.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
