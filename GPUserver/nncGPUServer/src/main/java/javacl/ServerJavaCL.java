/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacl;

import java.rmi.RemoteException;
import nncgpuserver.INetworkCalculatorServer;
import pojol.original.Network;

/**
 *
 * @author Tomasz
 */
public class ServerJavaCL implements INetworkCalculatorServer {

    @Override
    public void loadNetworkIntoGPU(Network n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[] run(double[] input) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void sayHello() {
        System.out.println("Hello from javaCL");
    }
}
