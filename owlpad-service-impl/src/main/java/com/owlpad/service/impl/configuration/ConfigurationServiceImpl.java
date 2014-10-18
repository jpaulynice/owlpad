package com.owlpad.service.impl.configuration;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.service.configuration.ConfigurationService;
import com.owlpad.service.mapper.ConfigMapper;
import com.owlpad.service.model.Configuration;

/**
 * 
 * @author Jay Paulynice
 *
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	@Autowired
	private ConfigurationDao configurationDao;

	public ConfigurationServiceImpl() {
	}

	@Override
	public Response getConfiguration() {
		ConfigurationResponse res = new ConfigurationResponse();
		Configuration c = configurationDao.getConfiguration();
		
		if(c == null){
			throw new WebApplicationException("no configuration found.");
		}
		res.setConfiguration(ConfigMapper.mapConfig(c));

		GenericEntity<ConfigurationResponse> entity = new GenericEntity<ConfigurationResponse>(res) {};
		return Response.ok(entity).build();
	}
}
