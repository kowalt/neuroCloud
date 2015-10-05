/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import java.rmi.RemoteException;
import nncgpuserver.model.Network;
import static org.jocl.CL.CL_DEVICE_TYPE_ALL;

/**
 *
 * @author Tomasz
 */
public class Server implements RunNetwork
{    
    @Override
    public int[] run(int[] input) 
    {
        
        
        return null;
    }

    @Override
    public void loadNetworkIntoGPU(Network n) {
        
    }
}
