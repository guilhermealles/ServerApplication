package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import rmi.base.DataRequestInterface;
import rmi.base.DatabaseRequest;
import rmi.base.RequestResponse;

public class RequestToDatabaseController implements DataRequestInterface {

	private static final String DATABASE_HOST_ADDRESS = "192.168.0.106";
	
	@Override
	public RequestResponse executeRequest(DatabaseRequest request) throws Exception {
		
		System.out.println("DEBUG> Received request, type= " + request.getType() + ", outlet= " + request.getOutlet());
		
		// Create client socket to pass data to the database controller
		Socket s = null;
		DataOutputStream out;
		DataInputStream in;
		RequestResponse response = null;
		try {
			s = new Socket(DATABASE_HOST_ADDRESS, 10001);
			out = new DataOutputStream(s.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(request);
			
			in = new DataInputStream(s.getInputStream());
			ObjectInputStream object_stream = new ObjectInputStream(in);
			response = (RequestResponse)object_stream.readObject();
		}
		catch (UnknownHostException e) {
			System.err.println(e);
		}
		catch (IOException e) {
			System.err.println(e);
		}
		finally {
			if (s != null) {
				try {
					s.close();
				}
				catch (IOException e) {
					System.err.println(e);
				}
			}
		}
		
		return response;
	}	
}
