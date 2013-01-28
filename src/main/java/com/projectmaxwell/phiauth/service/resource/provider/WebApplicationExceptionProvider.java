package com.projectmaxwell.phiauth.service.resource.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.JsonMappingException;

import com.projectmaxwell.exception.InvalidJSONException;

@Provider
public class WebApplicationExceptionProvider implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException arg0) {
		arg0.printStackTrace();
		return (new InvalidJSONException(String.valueOf(Math.random()),"WAE.  " + arg0.getMessage())).getResponse();
	}

}
