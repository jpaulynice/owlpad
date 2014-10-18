package com.owlpad.service.configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Path("config")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ConfigurationService {

	/**
	 * 
	 * @return
	 */
	@GET
	public Response getConfiguration();
}
