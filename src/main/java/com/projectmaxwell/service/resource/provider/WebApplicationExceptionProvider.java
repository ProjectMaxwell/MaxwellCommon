package com.projectmaxwell.service.resource.provider;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionProvider implements ExceptionMapper<WebApplicationException> {

	private static final Logger LOGGER = Logger.getLogger(WebApplicationException.class.getName());
	
	@Override
	public Response toResponse(WebApplicationException e) {
		LOGGER.log(Level.SEVERE, "Unknown Web Application Exception thrown: " + e.getMessage());
		ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.APPLICATION_JSON);
		return builder.build();
	}

}
