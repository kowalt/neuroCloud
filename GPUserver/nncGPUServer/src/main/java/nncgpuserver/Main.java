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
import javacl.ServerJavaCL;
import jocl.ServerJOCL;
import normal.ServerCPU;
import static org.jocl.CL.*;
import utils.ExternalIpUtil;
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

    @Parameter(names= {"-m","--mode"}, description="0-normal, 1-jocl, 2-javacl")
    private Integer mode;

    @Parameter(names= {"-o","--registryPort"}, description="Port of remote RMI registry to connect with")
    private Integer registryPort;
    
    @Parameter(names= {"-s","--registryHost"}, description="Host of remote RMI registry to connect with")
    private String registryHost;
    
    public static final int RMI_PORT = 61263; 
    
    private void initializeRMIServer()
    {
        try 
        {
            INetworkCalculatorServer s = null;
            switch(mode)
            {
                case 0:
                    s = new ServerCPU();
                    break;    
                case 1:
                    s = new ServerJOCL(platform_index, device_index);
                    break;
                case 2:
                    s = new ServerJavaCL();
                    break;
                default:
                    printHelp();
                    return;
            }

            INetworkCalculatorServer rn_stub = (INetworkCalculatorServer)UnicastRemoteObject.exportObject(s, RMI_PORT);
            Registry registry = LocateRegistry.getRegistry(registryHost, registryPort);
            registry.bind("INetworkCalculatorServer", rn_stub);

            RMIServer server = new RMIServer(null, s.getLabel());
            server.setHost(ExternalIpUtil.getExternalIP());
            server.setPort(RMI_PORT);
            server.setName(s.getClass().getName());
            ServerRegistrationManager manager = new ServerRegistrationManager(server);
            manager.register();
            manager.run();
            System.out.println("RMI Initialized successfully");
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(INetworkCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //setExceptionsEnabled(true);
        main.initializeRMIServer();
    }
}
