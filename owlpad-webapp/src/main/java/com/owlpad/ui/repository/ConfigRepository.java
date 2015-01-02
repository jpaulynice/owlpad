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
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ConfigurationService service;

    /**
     * @param service
     *            configuration service
     */
    @Autowired
    public ConfigRepository(final ConfigurationService service) {
        this.service = service;
    }

    /**
     * Get application configuration from API
     *
     * @return {@lin ConfigurationResponse} object
     */
    public ConfigurationResponse getConfig() {
        LOG.info("Executing call to get application configuration.");
        final Response res = service.getConfiguration();

        if (res != null && res.getStatus() == 200) {
            LOG.info("Successfully executed call to get application configuration.");
            return res.readEntity(ConfigurationResponse.class);
        } else {
            LOG.error("Exception while executing call to get application configuration.");
            throw new RuntimeException("Unable to get configuration.");
        }
    }
}
