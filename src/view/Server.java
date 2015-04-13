package view;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import model.QueueMessage;
import controller.QueueConsumerThread;
import controller.RMIThread;
import controller.TCPSocketClient;


public class Server 
{	
	public Server()
	{
	}
	
	public static void sendToDatabaseController (QueueMessage message) throws Exception
	{	
	    // Serialize the QueueMessage
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(baos);
		os.writeObject(message);
		byte[] message_bytes = baos.toByteArray();
		
		TCPSocketClient socket = new TCPSocketClient(TCPSocketClient.QUEUE_MESSAGE_SOCKET_PORT);
	    socket.connect();
	    socket.send(message_bytes);
	    socket.close();
	}
	
	public static void main (String args[])
	{
		QueueConsumerThread queue_consumer_thread = new QueueConsumerThread();	
		queue_consumer_thread.start();
		RMIThread rmi_thread = new RMIThread();
		rmi_thread.start();
	}
}
