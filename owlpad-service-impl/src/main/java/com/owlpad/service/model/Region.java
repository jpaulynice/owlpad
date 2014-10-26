package com.owlpad.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jay Paulynice
 *
 */
@Entity(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "selector")
    @XmlElement(name = "selector")
    private String selector;

    /**
     * @return the selector
     */
    public String getSelector() {
        return selector;
    }

    /**
     * @param selector
     *            the selector to set
     */
    public void setSelector(final String selector) {
        this.selector = selector;
    }

    @ManyToOne(optional = false)
    private Layout layout;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * @param layout
     *            the layout to set
     */
    public void setLayout(final Layout layout) {
        this.layout = layout;
    }

}
