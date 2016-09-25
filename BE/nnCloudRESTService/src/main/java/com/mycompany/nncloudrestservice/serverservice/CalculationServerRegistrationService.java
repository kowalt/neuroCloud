package com.mycompany.nncloudrestservice.serverservice;

import java.rmi.RemoteException;

import com.mycompany.nncloudrestservice.pojo.RMIServer;
import com.mycompany.nncloudrestservice.utils.ServerListContainer;
import java.util.Timer;

public class CalculationServerRegistrationService implements ICalculationServerRegistrationService{
	@Override
	public void register(RMIServer server) throws RemoteException {
            server.setId(ServerListContainer.getInstance().size());
            ServerListContainer.addServer(server);		
	}

	@Override
	public String sayHello() throws RemoteException {
            return "Hello world, 2+2="+String.valueOf(2+2);
	}

	@Override
	public void unRegister(RMIServer server) throws RemoteException {
            ServerListContainer.deleteAt(server.getId());
	}

	@Override
	public void report(RMIServer server) throws RemoteException {
            ServerListContainer.getAt(server.getId()).setLast_report_timer(System.currentTimeMillis());
	}
}
