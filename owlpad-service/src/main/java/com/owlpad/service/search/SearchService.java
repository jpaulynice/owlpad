package com.owlpad.service.search;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.owlpad.domain.search.SearchRequest;

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
	public Response search(final SearchRequest searchRequest);
	
	/**
	 * Method to get a document content by id
	 * 
	 * @param docId
	 * @return
	 */
	@GET
	@Path("{docId}")
	public Response getDocContentById(final @PathParam("docId") String docId);
}
