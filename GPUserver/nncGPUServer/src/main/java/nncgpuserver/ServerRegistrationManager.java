package nncgpuserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nncgpuserver.RMIServer;

public class ServerRegistrationManager implements Runnable{

	private final Long REPORT_INTERVAL = 15000l;
	private final String HOST_WS = "178.62.119.189";
	private final int PORT_WS = 61262;
	private RMIServer server;
	private ICalculationServerRegistrationService serv;

	public ServerRegistrationManager(RMIServer server) {
		this.server = server;

		try
		{
                    Registry registry = LocateRegistry.getRegistry(HOST_WS, PORT_WS);
                    ICalculationServerRegistrationService serv = (ICalculationServerRegistrationService) registry.lookup("ICalculationServerRegistrationService");

                    this.serv = serv;
                    System.out.println(serv.sayHello());
		}
		catch(Exception e)
		{
                    e.printStackTrace();
		}
	}

	public void register()
	{
            try {
                serv.register(this.server);
            } catch (RemoteException e) {
                    e.printStackTrace();
            }
	}

	public void unRegister()
	{
            try {
                serv.unRegister(this.server);
            } catch (RemoteException e) {
                    e.printStackTrace();
            }
	}

	@Override
	public void run() {
            try {
                    serv.report(this.server);
                    Thread.sleep(REPORT_INTERVAL);
            } 
            catch (RemoteException e)
            {
                    e.printStackTrace();
            }
            catch (InterruptedException e) 
            {
                    e.printStackTrace();
            }
	}
}