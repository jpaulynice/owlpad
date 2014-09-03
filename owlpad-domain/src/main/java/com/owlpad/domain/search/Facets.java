package com.owlpad.domain.search;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "facets")
public class Facets{
	@XmlElement(name = "facetResults")
	private Set<FacetResult> facetResults;

	public Facets(){
		
	}
	
	public Facets(Set<FacetResult> facets){
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
	public void setFacetResults(Set<FacetResult> facetResults) {
		this.facetResults = facetResults;
	}
}
