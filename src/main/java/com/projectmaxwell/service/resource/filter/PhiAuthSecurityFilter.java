package com.projectmaxwell.service.resource.filter;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpException;

import com.projectmaxwell.exception.DependentServiceException;
import com.projectmaxwell.exception.UnableToValidateException;
import com.projectmaxwell.util.PhiAuthClient;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

@Provider
@Produces("application/json")
public class PhiAuthSecurityFilter implements ResourceFilter, ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest request) {

		final String accessToken = request.getHeaderValue("Authorization");

		Session session = null;
		TokenWrapper response = null;
 
		if (accessToken != null && accessToken.length() > 0) {
			
			try {
				PhiAuthClient.setHostname("http://evergreenalumniclub.com:7080/PhiAuth/rest");
				response = PhiAuthClient.validateToken(accessToken);
			} catch (URISyntaxException e) {
				throw new UnableToValidateException(String.valueOf(Math.random()),"Could not validate token due to URI exception." + e.getMessage());
			} catch (HttpException e) {
				throw new UnableToValidateException(String.valueOf(Math.random()),"Could not validate token due to http exception." + e.getMessage());
			} catch (IOException e) {
				throw new UnableToValidateException(String.valueOf(Math.random()),"Could not validate token due to IO exception.");
			} catch (DependentServiceException e){
				throw e;
			} catch (UnableToValidateException e){
				throw new WebApplicationException();
			}
		}

		// Set security context
		request.setSecurityContext(new PhiAuthSecurityContext(session, response));
		return request;
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return null;
	}
}
