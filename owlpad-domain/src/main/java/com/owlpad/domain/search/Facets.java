package com.owlpad.domain.search;

import java.util.Set;

public class Facets{
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
