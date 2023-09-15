package com.lucas.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionsResponse implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Date timeStamp;
	private String message;
	private String details;
	
	
	public ExceptionsResponse(Date timeStamp, String message, String details) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public String getMessage() {
		return message;
	}


	public String getDetails() {
		return details;
	}

	
	
	
}
