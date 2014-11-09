package com.owlpad.ui.repository;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.domain.configuration.ConfigurationResponse;
import com.owlpad.service.configuration.ConfigurationService;

/**
 * {@link ConfigRepository} is a simple intermediate class to interact with the
 * API
 *
 * @author Jay Paulynice
 *
 */
@Repository
public class ConfigRepository {
    private static final Logger logger = LoggerFactory.getLogger(ConfigRepository.class);
    private final ConfigurationService configService;

    @Autowired
    public ConfigRepository(final ConfigurationService configService) {
        this.configService = configService;
    }

    /**
     * Get application configuration from API
     *
     * @return {@lin ConfigurationResponse} object
     */
    public ConfigurationResponse getConfig() {
        final Response res = configService.getConfiguration();

        if (res != null && res.getStatus() == 200) {
            return res.readEntity(ConfigurationResponse.class);
        } else {
            logger.error("Exception while executing getConfig");
            throw new RuntimeException("Unable to get configuration.");
        }
    }
}
