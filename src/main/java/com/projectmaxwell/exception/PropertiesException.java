package com.projectmaxwell.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class PropertiesException extends WebApplicationException {

	protected static final String errorCode = "INVALID_PROPERTIES";
	
	public PropertiesException(String errorId, String errorMessage) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				new MaxwellException(errorId, errorCode, errorMessage)).build()); 
		
	}
	
	public PropertiesException(MaxwellException me){
		me.setErrorMessage("Dependent Service failed due to exception: " + me.getErrorMessage());
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				me).build();
	}
}
