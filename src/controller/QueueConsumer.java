package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.QueueMessage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class QueueConsumer
{
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
			this.connection = (Connection) this.factory.newConnection();
			this.channel = (Channel) this.connection.createChannel();
			this.consumer = new QueueingConsumer((com.rabbitmq.client.Channel) channel);
			this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public QueueMessage consumeOnce()
	{
		try
		{
			this.channel.basicConsume(QUEUE_NAME, true, consumer);
			QueueingConsumer.Delivery delivery = this.consumer.nextDelivery();   
		    
			ByteArrayInputStream in = new ByteArrayInputStream(delivery.getBody());
			ObjectInputStream object_stream = new ObjectInputStream(in);
			QueueMessage received_message = (QueueMessage)object_stream.readObject();
			
			System.out.println("... " + received_message.getOutletId() + ", " + received_message.getPowerConsumption());
			
			return received_message;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
		
}