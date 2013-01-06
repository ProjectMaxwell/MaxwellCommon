package com.projectmaxwell.service.resource.filter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory;

@Provider
public class ResourceFilterFactory extends RolesAllowedResourceFilterFactory {

   // private @Context SecurityContext sc;
    private PhiAuthSecurityFilter pasf = new PhiAuthSecurityFilter();

    /*private class Filter implements ResourceFilter,  ContainerRequestFilter { 

        private final boolean denyAll; 
        private final String[] rolesAllowed; 

        protected Filter() { 
            this.denyAll = true; 
            this.rolesAllowed = null; 
        } 

        protected Filter(String[] rolesAllowed) { 
            this.denyAll = false; 
            this.rolesAllowed = (rolesAllowed != null) ?   
rolesAllowed : new String[] {}; 
        } 

        // ResourceFilter 

        public ContainerRequestFilter getRequestFilter() { 
            return this; 
        } 

        public ContainerResponseFilter getResponseFilter() { 
            return null; 
        } 

        // ContainerRequestFilter 

        public ContainerRequest filter(ContainerRequest request) { 
            if (!denyAll) { 
                for (String role : rolesAllowed) { 
                    if (sc.isUserInRole(role)) 
                        return request; 
                } 
            } 

            throw new   
WebApplicationException(Response.Status.FORBIDDEN); 
        } 
    } */

    public List<ResourceFilter> create(AbstractMethod am) { 
    	//System.out.println("Resource Filter Factory triggered.");
    	
    	List<ResourceFilter> rolesFilters = super.create(am);
    	if(rolesFilters == null){
    		rolesFilters = new ArrayList<ResourceFilter>();
    	}
    	
    	List<ResourceFilter> filters = new ArrayList<ResourceFilter>(rolesFilters);
    	
    	if(am.isAnnotationPresent(RolesAllowed.class)){
    		filters.add(0,pasf);
    	}
    	
/*    	if(am.isAnnotationPresent(RolesAllowed.class)){
    		
    	}*/
    	
    	return filters;
    	
       /* // DenyAll on the method take precedence over RolesAllowed and PermitAll 
        if (am.isAnnotationPresent(DenyAll.class)) 
            return Collections.<ResourceFilter>singletonList(new   
Filter()); 

        // RolesAllowed on the method takes precedence over PermitAll 
        RolesAllowed ra = am.getAnnotation(RolesAllowed.class); 
        if (ra != null) 
            return Collections.<ResourceFilter>singletonList(new   
Filter(ra.value())); 

        // PermitAll takes precedence over RolesAllowed on the class 
        if (am.isAnnotationPresent(PermitAll.class)) 
            return null; 

        // RolesAllowed on the class takes precedence over PermitAll 
        ra = am.getResource().getAnnotation(RolesAllowed.class); 
        if (ra != null) 
            return Collections.<ResourceFilter>singletonList(new   
Filter(ra.value())); 

        // No need to check whether PermitAll is present. 
        return null; 
    }  */
    }
}
