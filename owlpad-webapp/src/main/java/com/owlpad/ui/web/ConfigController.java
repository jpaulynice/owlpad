package com.owlpad.ui.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.ui.repository.ConfigRepository;

/**
 * REST end point for loading application UI configuration.
 *
 * @author julespaulynice
 *
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController {
    private final ConfigRepository configRepository;

    @Autowired
    public ConfigController(final ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    /**
     * Get application configuration from api
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ConfigurationResponse getConfig() {
        return configRepository.getConfig();
    }
}
