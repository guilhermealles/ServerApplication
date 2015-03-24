package server.domain;

public class TCPSocketClient 
{
	private String SERVER_ADRESS;
	private String SERVER_PORT;
	
	// The idea is to have to separate socket clients: the first only sends data to the databaseso that it gets
	// inserted into the tables. The second sends a request and gets a response and gets a response to show data 
	// to the user.
	
	public TCPSocketClient()
	{
		
	}
	
	public TCPSocketClient(String address, String port)
	{
		this.SERVER_ADRESS = address;
		this.SERVER_PORT = port;
	}
	
	public void connect()
	{
		
	}
	
	public void send(QueueMessage message)
	{
		
	}
	
	public void close()
	{
		
	}
	
}
