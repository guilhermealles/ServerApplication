package model;

import java.io.Serializable;

public class QueueMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int outlet_id;
	private double outlet_power_consuption;
		
	public QueueMessage(int id, double power_consumption)
	{
		this.outlet_id = id;
		this.outlet_power_consuption = power_consumption;
	}
	
	public int getOutletId()
	{
		return this.outlet_id;
	}
	
	public double getPowerConsumption()
	{
		return this.outlet_power_consuption;
	}

}
