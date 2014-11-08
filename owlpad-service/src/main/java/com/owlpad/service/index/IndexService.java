package com.owlpad.service.index;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.owlpad.domain.index.IndexRequest;

/**
 * {@link IndexService} is a simple interface for indexing a directory.
 *
 * @author Jay Paulynice
 *
 */
@Path("index")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IndexService {

    /**
     * Method to index a directory by passing the directory name and file suffix
     * if file suffix is null then all files are indexed otherwise index those
     * files that end with the suffix.
     * 
     * @param indexRequest
     * @return
     */
    @POST
    public Response index(final IndexRequest indexRequest);
}
