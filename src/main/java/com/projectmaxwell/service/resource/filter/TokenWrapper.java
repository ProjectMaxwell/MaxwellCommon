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
		System.out.println("Requesting scopes");
		String[] scopes = getScopes();
		HashSet<String> scopeSet = new HashSet<String>();
		for(String s : scopes){
/*			try{
				scopeSet.add(Scope.valueOf(s));
			}catch(IllegalArgumentException e){
				System.out.println("Skipping scope: " + s);
				continue;
			}*/
			scopeSet.add(s);
		}
		return scopeSet;
	}

}
