/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomasz
 */
public class Main {
    final static String REGISTRY_HOST = "178.62.119.189";
    
    public static void main(String[] args)
    {
        try 
        {
            Server s = new Server();   
            RunNetwork rn_stub = (Server)UnicastRemoteObject.exportObject(s, 0);
            Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST);
            registry.bind("RunNetwork", rn_stub);
            
            System.out.println("Server ready!");
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (AlreadyBoundException ex) 
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
