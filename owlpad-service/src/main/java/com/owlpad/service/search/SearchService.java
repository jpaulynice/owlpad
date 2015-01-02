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
 * {@link SearchService} is a simple interface for search. This consists of 2
 * methods to search: 1. Find all documents matching a criteria 2. Find a
 * document by id
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
     *            search request object
     * @return {@link Response} object
     */
    @POST
    public Response search(final SearchRequest searchRequest);

    /**
     * Method to get a document by id
     *
     * @param docId
     *            string document id
     * @return {@link Response} object
     */
    @GET
    @Path("{docId}")
    public Response search(final @PathParam("docId") String docId);
}
