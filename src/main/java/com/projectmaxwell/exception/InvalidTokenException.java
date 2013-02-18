package com.projectmaxwell.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class InvalidTokenException extends WebApplicationException {

	public static final String errorCode = "INVALID_TOKEN";
	
	public String errorId;
	public String errorMessage;
	
	public InvalidTokenException(String errorId, String errorMessage) {
		super(Response.status(Response.Status.UNAUTHORIZED).entity(
				new MaxwellException(errorId, errorCode, errorMessage)).type(MediaType.APPLICATION_JSON)
				.build());
		this.errorId = errorId;	
		this.errorMessage = errorMessage;
	} 

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
