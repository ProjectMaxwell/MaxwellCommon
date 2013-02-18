package com.projectmaxwell.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@SuppressWarnings("serial")
public class InvalidFileException extends WebApplicationException {

	protected static final String errorCode = "FILE_UNUSEABLE";
	
	public InvalidFileException(String errorId, String errorMessage) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				new MaxwellException(errorId, errorCode, errorMessage))
				.build());
		
	}
}
