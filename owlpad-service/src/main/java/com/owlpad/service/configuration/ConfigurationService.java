package com.owlpad.service.configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * {@link ConfigurationService} is a simple interface to load application
 * configuration and layout. Currently, there is one method to get default
 * configuration.
 *
 * @author Jay Paulynice
 *
 */
@Path("config")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ConfigurationService {

    /**
     * Get default configuration
     *
     * @return
     */
    @GET
    public Response getConfiguration();
}
