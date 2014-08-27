package com.owlpad.domain.search;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link SearchResponse} is the wrapper for the response to get search result or documents
 * matching our search query.
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchResponse")
public class SearchResponse {
	private StatusType status;
	private List<Document> documents;
	private long totalDocuments;
	private Map<String,Facets> facets;
	private String errorMessage;
	
	public SearchResponse(){
		
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * @return the documents
	 */
	public List<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @return the totalDocuments
	 */
	public long getTotalDocuments() {
		return totalDocuments;
	}

	/**
	 * @param totalDocuments the totalDocuments to set
	 */
	public void setTotalDocuments(long totalDocuments) {
		this.totalDocuments = totalDocuments;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the facets
	 */
	public Map<String,Facets> getFacets() {
		return facets;
	}

	/**
	 * @param facets the facets to set
	 */
	public void setFacets(Map<String,Facets> facets) {
		this.facets = facets;
	}
}
