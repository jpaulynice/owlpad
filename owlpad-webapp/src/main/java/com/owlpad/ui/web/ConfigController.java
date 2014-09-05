package com.owlpad.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.ui.repository.ConfigRepository;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {
	private ConfigRepository configRepository;
	
	@Autowired
	public ConfigController(ConfigRepository configRepository){
		this.configRepository = configRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ConfigurationResponse getConfig(){
		return configRepository.getConfig();
	}
	
	/**
	 * @return the configRepository
	 */
	public ConfigRepository getConfigRepository() {
		return configRepository;
	}

	/**
	 * @param configRepository the configRepository to set
	 */
	public void setConfigRepository(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}
}
