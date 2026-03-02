package org.example.response;

import java.io.Serializable;

public class StatusResponse  implements RestResponse
{
	protected int status;
	protected String message;
	
	public StatusResponse(){
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusResponse(int status)
	{
		this.status = status;
	}	
		
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public int getStatus()
	{
		return this.status;		
	}
}
