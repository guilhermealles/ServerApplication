package server.domain;

import java.io.Serializable;

public class RequestResponse implements Serializable
{
	private boolean valid;
	private int[] outlet_ids;
	private int power_consumption_data;

	public RequestResponse()
	{
		this.valid = false;
		this.outlet_ids = null;
		this.power_consumption_data = 0;
	}
	
	public RequestResponse (boolean valid, int[] outlet_ids, int power_consumption_data)
	{
		this.valid = valid;
		this.outlet_ids = outlet_ids;
		this.power_consumption_data = power_consumption_data;
	}
}
