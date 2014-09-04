package com.owlpad.domain.configuration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfigurationResponse {
	private Configuration configuration;

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
