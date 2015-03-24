package server.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class QueueConsumer
{
	// Equivalente ao Recv
	private static final String RABBITMQ_SERVER_IP = "192.168.0.104";
	private static final String QUEUE_NAME = "OUTLET_INFO_MESSAGES";
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private QueueingConsumer consumer;
	
	public QueueConsumer()
	{
		this.factory = new ConnectionFactory();
		this.factory.setHost(RABBITMQ_SERVER_IP);		
		
		try 
		{
			this.connection = this.factory.newConnection();
			this.channel = this.connection.createChannel();
			this.consumer = new QueueingConsumer(channel);
			this.channel.basicConsume(QUEUE_NAME, true, consumer);
			this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
		
	public void handleConsumeOk(String consumer_tag)
	{
		
	}
	
	public void handleDelivery()
	{
		
	}
	
	public void consumeOnce()
	{
		// Verifica se possui uma única mensagem.
		try
		{
			QueueingConsumer.Delivery delivery = this.consumer.nextDelivery();   
		    String received_message = new String(delivery.getBody());
		    System.out.println(" [x] Received '" + received_message + "'" + new Date());
		    
		    // Cria uma QueueMessage com a mensagem recebida. Adiciona ao buffer do servidor.
		    QueueMessage received_queue_message = new QueueMessage(received_message);
		    
		    // Serialize the QueueMessage
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(baos);
			os.writeObject(received_queue_message);
			byte[] message_bytes = baos.toByteArray();
		  
		  
		  	Server s = new Server();
		    s.addToBuffer(message_bytes);
		    System.out.println("First message in buffer: " + "<<" + s.getMessageBuffer().getFirst().toString() + ">>");
		    
		    
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	// Testar com uma main temporaria
	
	public static void main (String[] args)
	{
		QueueConsumer q = new QueueConsumer();
		System.out.println("Listening...");
		while(true)
		{
			q.consumeOnce();
		}
	}
		
}
