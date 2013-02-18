package com.projectmaxwell.service.resource.filter;

import java.security.Principal;
import java.util.HashSet;

import com.projectmaxwell.util.PhiAuthValidationResponse;

public class TokenWrapper extends PhiAuthValidationResponse implements Principal  {
	
	@Override
	public String getName() {
		return null;
	}
	
	public HashSet<String> getScopesAsSet(){
		String[] scopes = getScopes();
		HashSet<String> scopeSet = new HashSet<String>();
		for(String s : scopes){
			scopeSet.add(s);
		}
		return scopeSet;
	}

}
