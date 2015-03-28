package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import base.DataRequestInterface;
import base.RmiStarter;

public class RequestToDatabaseControllerStarter extends RmiStarter {

	@Override
	public void start() {
		try {
			DataRequestInterface exposed_obj = new RequestToDatabaseController();
			DataRequestInterface stub = (DataRequestInterface) UnicastRemoteObject.exportObject(exposed_obj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(DataRequestInterface.SERVICE_NAME, stub);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
