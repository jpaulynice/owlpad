package com.owlpad.domain.configuration;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jay Paulynice
 *
 */
@XmlRootElement
public class Configuration {
    private String name;
    private Layout layout;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param layout the layout to set
     */
    public void setLayout(final Layout layout) {
        this.layout = layout;
    }
}
