package com.myapp.search.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.myapp.search.domain.SearchRequest;
import com.myapp.search.domain.SearchResponse;

/**
 * Simple interface for search
 * 
 * @author Jay Paulynice
 *
 */
@Path("v1")
@Consumes({MediaType.APPLICATION_JSON })
@Produces({MediaType.APPLICATION_JSON })
public interface SearchService {
	
    @POST
    @Path("search")
	public SearchResponse search(SearchRequest searchRequest);
}
