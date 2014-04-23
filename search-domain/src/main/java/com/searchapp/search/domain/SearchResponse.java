package com.searchapp.search.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchResponse")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResponse {
	private StatusType status;
	private List<Document> documents;
	
	public SearchResponse(){
		
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
