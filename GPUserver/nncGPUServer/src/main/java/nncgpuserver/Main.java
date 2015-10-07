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

    @Parameter(names = {"-l","--list"}, description="List mode")
    private boolean listMode = false;
    
    @Parameter(names = {"-p","--platform"}, description="Platform index")
    private Integer platform_index = 0;
    
    @Parameter(names = {"-d","--device"}, description="Device index")
    private Integer device_index = 0;
        
    @Parameter(names = {"-n", "--name"}, description="Server's name")
    private String name;
    
    private void initializeRMIServer()
    {
        try 
        {
            Server s = new Server();
            DeviceInitializer di = new DeviceInitializer();
            di.initialize(platform_index, device_index);
            s.setInitializer(di);
            RunNetwork rn_stub = (RunNetwork)UnicastRemoteObject.exportObject(s, 0);
            Registry registry = LocateRegistry.getRegistry();
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
    
    private void printHelp()
    {
        System.out.println(
                "NNCLOUD SERVER usage:\n"
                        + "Print available devices and platforms:\n"
                        + "nncs -l\n"
                        + "Start server on particular platform and device. Default id's are 0.\n"
                        + "nncs start -p [PLATFORM_ID] -d [DEVICE_ID] -n [SERVER_NAME]\n"
        );
    }
    
    public static void main(String[] args)
    {
        Main main = new Main();

        if(args.length == 0)
        {    
            main.printHelp();
            return;
        }
   
        new JCommander(main, args);
        
        if(main.listMode)
        {
            ListUtil.listPlatforms();
            return;
        }
        
        setExceptionsEnabled(true);
        main.initializeRMIServer();
    }
}
