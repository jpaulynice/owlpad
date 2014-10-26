package com.owlpad.dao;

import com.owlpad.service.model.Configuration;

/**
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
