/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nncgpuserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import nncgpuserver.model.Network;

/**
 *
 * @author Tomasz
 */
public interface RunNetwork extends Remote {
    
    public void loadNetworkIntoGPU(Network n);
    
    public int[] run(int[] input) throws RemoteException;
}
