package controller;

import java.util.LinkedList;

import view.Server;
import model.QueueMessage;

public class QueueConsumerThread extends Thread
{
	private QueueConsumer queue_consumer;
	private LinkedList<QueueMessage> message_buffer;
	
	public QueueConsumerThread()
	{
		queue_consumer = new QueueConsumer();
		message_buffer = new LinkedList<QueueMessage>();
	}
	
	public void addToBuffer (QueueMessage message)
	{	
		try
		{
			this.message_buffer.addLast(message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public LinkedList<QueueMessage> getMessageBuffer()
	{
		return this.message_buffer;
	}
	
	public void run()
	{
		// Main da thread
		while (true)
		{
			System.out.println("Server is listening...");
			
			
			QueueMessage message = queue_consumer.consumeOnce();
			this.addToBuffer(message);
			System.out.println("Id: " + message.getOutletId() + " Power consumed: " + message.getPowerConsumption());
			
			while (! this.message_buffer.isEmpty())
			{
				QueueMessage message_from_buffer = this.message_buffer.getFirst();
				this.message_buffer.removeFirst();
				
				try
				{
					Server.sendToDatabaseController(message_from_buffer);
				}
				catch (Exception e)
				{
					this.message_buffer.addFirst(message_from_buffer);
					break;
				}
			}
		}
	}

}
