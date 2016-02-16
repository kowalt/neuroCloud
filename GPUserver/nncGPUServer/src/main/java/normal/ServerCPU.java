/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package normal;

import java.rmi.RemoteException;
import nncgpuserver.INetworkCalculatorServer;
import nncgpuserver.model.Network;

/**
 *
 * @author Tomasz
 */
public class ServerCPU implements INetworkCalculatorServer{

    @Override
    public void loadNetworkIntoGPU(Network n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] run(int[] input) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
