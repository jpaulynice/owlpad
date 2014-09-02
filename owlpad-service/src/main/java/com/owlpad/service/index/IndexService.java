package com.owlpad.service.index;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Path("index")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IndexService {

	@POST
	public IndexResponse index(final IndexRequest indexRequest);
}
