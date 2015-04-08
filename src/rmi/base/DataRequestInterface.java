package rmi.base;

import java.rmi.Remote;

public interface DataRequestInterface extends Remote {
	public static final String SERVICE_NAME = "RequestToDatabase";
	
	RequestResponse executeRequest(DatabaseRequest request) throws Exception;
}
