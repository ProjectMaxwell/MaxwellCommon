package com.projectmaxwell.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class DependentServiceException extends WebApplicationException {

	protected static final String errorCode = "DEPENDENCY_FAILED";
	
	public DependentServiceException(String errorId, String errorMessage) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				new MaxwellException(errorId, errorCode, errorMessage)).build()); 
		
	}
	
	public DependentServiceException(MaxwellException me){
		me.setErrorMessage("Dependent Service failed due to exception: " + me.getErrorMessage());
		Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				me).build();
	}
}
