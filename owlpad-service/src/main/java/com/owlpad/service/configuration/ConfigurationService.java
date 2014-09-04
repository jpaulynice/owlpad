package com.owlpad.service.configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.owlpad.domain.configuration.ConfigurationResponse;

@Path("config")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ConfigurationService {

	@GET
	public ConfigurationResponse getUserConfiguration();
}
