package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class TCPSocketClient 
{
	public static final int QUEUE_MESSAGE_SOCKET_PORT = 10000;
	public static final int DATABASE_REQUEST_SOCKET_PORT = 10001;
	
	private static String SERVER_ADDRESS = "192.168.0.106";	
	private int SERVER_PORT;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in;
	
	public TCPSocketClient(int server_port)
	{
		SERVER_PORT = server_port;
		socket = null;
		out = null;
		in = null;
	}
	
	public void connect() throws Exception
	{
		socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
		out = new DataOutputStream (socket.getOutputStream());
		in = new DataInputStream (socket.getInputStream());
	}
	
	public void send (byte[] data) throws Exception
	{
		out.write(data);
	}
	
	public byte[] getResponse() throws Exception
	{
		byte[] response = null;
		in.read(response);
		return response;
	}
	
	public void close()
	{
		try
		{
			socket.close();
		}
		catch (Exception e)
		{
			
		}
	}
}