package nncgpuserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculationServerRegistrationService extends Remote {
	public void register(RMIServer server) throws RemoteException;
	public String sayHello() throws RemoteException;
	public void unRegister(RMIServer server) throws RemoteException;
	public void report(RMIServer server) throws RemoteException; 
}
