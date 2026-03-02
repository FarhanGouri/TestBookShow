package org.example.response;

public class DataResponse  extends StatusResponse
{
protected Object data;
	
	public DataResponse()
	{}
	
	public DataResponse(int status, String message, Object data)
	{
		super.status = status;
		this.data = data;
		this.message = message;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
	
	public Object getData()
	{
		return this.data;		
	}

}