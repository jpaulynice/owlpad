package com.owlpad.service.impl.configuration;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.Configuration;
import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.service.configuration.ConfigurationService;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService {
	private final ConfigurationDao confDao;

	@Autowired
	public ConfigurationServiceImpl(ConfigurationDao confDao) {
		this.confDao = confDao;
	}

	@Override
	public Response getUserConfiguration() {
		ConfigurationResponse res = new ConfigurationResponse();
		Configuration c = confDao.getConfiguration();
		
		if(c == null){
			throw new WebApplicationException("no configuration found.");
		}
		res.setConfiguration(c);

		GenericEntity<ConfigurationResponse> entity = new GenericEntity<ConfigurationResponse>(res) {};
		return Response.ok(entity).build();
	}
}
