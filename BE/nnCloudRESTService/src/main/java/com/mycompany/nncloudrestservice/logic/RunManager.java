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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Tomasz
 */
public class RunManager {
	private static final Logger logger = LogManager.getLogger(RunManager.class);
    public void run(int networkId, int serverId, double[] vector)
    {
        try
        {
            RMIServer server = ServerListContainer.getAt(serverId);
            Registry registry = LocateRegistry.getRegistry(server.getHost(),server.getPort());

            try {
                INetworkCalculatorServer stub = (INetworkCalculatorServer) registry.lookup(server.getName());
                stub.run(vector, networkId);
            } catch (NotBoundException ex) {
                logger.error(ex.getStackTrace());
            } catch (AccessException ex) {
                logger.error(ex.getStackTrace());
            }        
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }
}