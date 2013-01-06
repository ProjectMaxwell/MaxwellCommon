package com.projectmaxwell.util;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.Produces;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.projectmaxwell.exception.DependentServiceException;
import com.projectmaxwell.exception.MaxwellException;
import com.projectmaxwell.exception.UnableToValidateException;
import com.projectmaxwell.service.resource.filter.TokenWrapper;

@Produces("application/json")
public class PhiAuthClient {

	private static final String VALIDATE_ENDPOINT = "/token/%s";
	
	private static String hostname;

	public static String getHostname() {
		return hostname;
	}

	public static void setHostname(String hostname) {
		PhiAuthClient.hostname = hostname;
	}
	
	public static TokenWrapper validateToken(String token) throws URISyntaxException, HttpException, IOException{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String uri = hostname + String.format(VALIDATE_ENDPOINT, token);
		HttpGet request = new HttpGet(uri);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		String resp = EntityUtils.toString(entity);
		//TODO: Figure out why the proper way of getting content is failing due to content already being consumed
		//InputStream content = entity.getContent();
		//String responseString = IOUtils.toString(entity.getContent());
		ObjectMapper mapper = new ObjectMapper();
		TokenWrapper validationResponse;
		try{
			validationResponse = mapper.readValue(resp, TokenWrapper.class);
		}catch(Exception e){
			try{
				MaxwellException me = mapper.readValue(resp, MaxwellException.class);
				throw new DependentServiceException(me);
			}catch(Exception e2){
				throw new UnableToValidateException(String.valueOf(Math.random()),"Unknown error from PhiAuth: " + resp);
			}
		}
		return validationResponse;
	}
}
