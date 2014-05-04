package com.searchapp.service.index;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.searchapp.domain.index.IndexRequest;
import com.searchapp.domain.index.IndexResponse;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Path("v1")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IndexService {

	@POST
	@Path("index")
	public IndexResponse index(IndexRequest indexRequest) throws Exception;
}
