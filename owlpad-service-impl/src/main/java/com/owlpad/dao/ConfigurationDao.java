package com.owlpad.dao;

import com.owlpad.domain.configuration.Configuration;

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
