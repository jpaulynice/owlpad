package com.owlpad.domain.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@link SearchResponse} is the wrapper for the response to get search result or documents
 * matching our search query.
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse {
	
	@XmlElement(name = "status")
	private StatusType status;
	
	@XmlElement(name = "documents")
	private List<Document> documents;
	
	@XmlElement(name = "totalDocuments")
	private long totalDocuments;
	
	@XmlElement(name = "facets")
	private HashMap<String,Facets> facets;
	
	@XmlElement(name = "errorMessage")
	private String errorMessage;
	
	public SearchResponse(){
		
	}
	
	public SearchResponse(StatusType status,List<Document> docs, long total, HashMap<String,Facets> facets,String message){
		this.status = status;
		this.documents = docs;
		this.totalDocuments = total;
		this.facets = facets;
		this.errorMessage = message;
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
	public void setFacets(HashMap<String,Facets> facets) {
		this.facets = facets;
	}
}
