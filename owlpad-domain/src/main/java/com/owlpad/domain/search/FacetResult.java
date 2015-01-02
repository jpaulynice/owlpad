package com.owlpad.domain.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jay Paulynice
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "facetResult")
public class FacetResult {
    private String entry;
    private long count;

    /** Default constructor */
    public FacetResult() {

    }

    /**
     * @return the entry
     */
    public String getEntry() {
        return entry;
    }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param entry
     *            the entry to set
     */
    public void setEntry(final String entry) {
        this.entry = entry;
    }

    /**
     * @param l
     *            the count to set
     */
    public void setCount(final long l) {
        this.count = l;
    }
}
