package com.owlpad.service.search;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;

/**
 * Simple interface for search
 * 
 * @author Jay Paulynice
 * 
 */
@Path("search")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface SearchService {

	/**
	 * Method to search for documents.
	 * 
	 * @param searchRequest
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("")
	public SearchResponse search(final SearchRequest searchRequest);
	
	
	@GET
	@Path("{docId}")
	public DocResponse getDocById(final @PathParam("docId") String docId);
}
