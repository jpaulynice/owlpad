package com.owlpad.service.impl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlpad.dao.ConfigurationDao;
import com.owlpad.domain.configuration.Configuration;
import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.service.configuration.ConfigurationService;

@Service("configurationService")
public class ConfigurationServiceImpl implements ConfigurationService{
	private ConfigurationDao confDao;
	
	@Autowired
	public ConfigurationServiceImpl(ConfigurationDao confDao){
		this.confDao = confDao;
	}
	
	@Override
	public ConfigurationResponse getUserConfiguration() {
		ConfigurationResponse res = new ConfigurationResponse();
		Configuration c = confDao.getUserConfiguration();
		res.setConfiguration(c);	
		return res;
	}
}
