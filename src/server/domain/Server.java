package server.domain;

import java.io.ObjectInputStream;
import java.util.LinkedList;

public class Server 
{
	private LinkedList<QueueMessage> message_buffer;
	
	public Server()
	{
		this.message_buffer = new LinkedList<QueueMessage>();
	}
	
	public void addToBuffer (byte[] message_bytes)
	{
		// Deserializes the messages and add it to the message buffer. 

		ObjectInputStream object_stream = new ObjectInputStream();
		QueueMessage data = (QueueMessage)object_stream.readObject();
		
		try
		{
			this.message_buffer.addFirst(message_bytes);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void sendToDatabaseController ()
	{
		// Abrir um socket e mandar para DB
	}
	
	public RequestResponse requestToDatabase (int operation_id)
	{
		return new RequestResponse();
	}
	
	public LinkedList<QueueMessage> getMessageBuffer()
	{
		return this.message_buffer;
	}
	 
	/*
	public static void main()
	{
		while (true)
		{
			// Consumir algo do rabbitmq. OBS.: a mensagem estará serializada, precisamos montá-la.
			// Verificar se tem mensagem no buffer. Se tiver, sendToDbController(); senao, ignora.
			// ?? RMI
			
		}
	}
	*/

}
