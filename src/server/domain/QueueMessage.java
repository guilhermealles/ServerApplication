package server.domain;

import java.io.Serializable;

public class QueueMessage implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	//private int outlet_id;
	//private int outlet_power_consuption;
	public String message;
	
	public QueueMessage (String message)
	{
		this.message = message;
	}
	
	/*
	public QueueMessage(int id, int power_consumption)
	{
		this.outlet_id = id;
		this.outlet_power_consuption = power_consumption;
	}
	
	public int getOutletId()
	{
		return this.outlet_id;
	}
	
	public int getPowerConsumption()
	{
		return this.outlet_power_consuption;
	}
	*/

}
