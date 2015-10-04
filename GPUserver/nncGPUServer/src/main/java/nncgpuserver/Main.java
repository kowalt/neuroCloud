/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.jocl.CL.*;
/**
 *
 * @author Tomasz
 */
public class Main {
    final static String REGISTRY_HOST = "178.62.119.189";
    
    @Parameter(names = {"-l","--list"}, description="List mode")
    private static boolean listMode = false;
    
    @Parameter(names = {"-p","--platform"}, description="Platform id")
    private static Integer platform_id = 0;
    
    @Parameter(names = {"-d","--device"}, description="Device id")
    private static Integer device_id = 0;
    
    public void initializeRMI()
    {
        try 
        {
            Server s = new Server();   
            RunNetwork rn_stub = (Server)UnicastRemoteObject.exportObject(s, 0);
            Registry registry = LocateRegistry.getRegistry(REGISTRY_HOST);
            registry.bind("RunNetwork", rn_stub);
            
            System.out.println("RMI Initialized successfully");
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
    
    public void printHelp()
    {
        System.out.println(
                "NNCLOUD SERVER usage:\n"
                        + "Print available devices and platforms:\n"
                        + "nncs -l\n"
                        + "Start server on particular platform and device. Default id's are 0.\n"
                        + "nncs -s -p [PLATFORM_ID] -d [DEVICE_ID]\n"
        );
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.initializeRMI();
    
        if(args.length == 0)
        {    
            main.printHelp();
            return;
        }
    
        setExceptionsEnabled(true);
        
        new JCommander(main, args);
        
        if(listMode)
        {
            ListUtil.listPlatforms();
            return;
        }  
    }
}
