package com.owlpad.domain.configuration;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jay Paulynice
 *
 */
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
     * @param configuration
     *            the configuration to set
     */
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }
}
