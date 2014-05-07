package com.owlpad.domain.search;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * {@link SearchResponse} is the wrapper for the response to get search result or documents
 * matching our search query.
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchResponse")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResponse {
	private StatusType status;
	private List<Document> documents;
	private int totalDocuments;
	
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
	public int getTotalDocuments() {
		return totalDocuments;
	}

	/**
	 * @param totalDocuments the totalDocuments to set
	 */
	public void setTotalDocuments(int totalDocuments) {
		this.totalDocuments = totalDocuments;
	}
}
