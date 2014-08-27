package com.owlpad.domain.search;

import java.util.Set;

public class Facets{
	Set<FacetResult> facetResults;

	public Facets(){
		
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
