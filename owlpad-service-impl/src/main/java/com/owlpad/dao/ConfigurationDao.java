package com.owlpad.dao;

import com.owlpad.service.model.Configuration;

/**
 * Simple interface for application configuration data access.
 *
 * @author Jay Paulynice
 *
 */
public interface ConfigurationDao {

    /**
     * Get application configuration from database
     *
     * @return
     */
    public Configuration getConfiguration();
}
