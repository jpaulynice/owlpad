package com.owlpad.domain.search;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jay Paulynice
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "facets")
public class Facets {
    @XmlElement(name = "facetResults")
    private Set<FacetResult> facetResults;

    /** Default constructor */
    public Facets() {

    }

    /**
     * @param facets the set of facet results
     */
    public Facets(final Set<FacetResult> facets) {
        this.facetResults = facets;
    }

    /**
     * @return the facetResults
     */
    public Set<FacetResult> getFacetResults() {
        return facetResults;
    }

    /**
     * @param facetResults the facetResults to set
     */
    public void setFacetResults(final Set<FacetResult> facetResults) {
        this.facetResults = facetResults;
    }
}
