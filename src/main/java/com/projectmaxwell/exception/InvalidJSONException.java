package com.projectmaxwell.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@SuppressWarnings("serial")
public class InvalidJSONException extends WebApplicationException {

	protected static final String errorCode = "INVALID";
	
	public InvalidJSONException(String errorId, String errorMessage) {
		super(Response.status(Response.Status.BAD_REQUEST).entity(
				new MaxwellException(errorId, errorCode, errorMessage)).type(MediaType.APPLICATION_JSON)
				.build()); 
	}
}
