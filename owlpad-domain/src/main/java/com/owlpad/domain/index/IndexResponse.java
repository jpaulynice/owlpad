package com.owlpad.domain.index;

import javax.xml.bind.annotation.XmlRootElement;

import com.owlpad.domain.search.StatusType;

/**
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "indexResponse")
public class IndexResponse {
	private int documentsIndexed;
	private StatusType status;
	private String errorMessage;
	
	public IndexResponse(){
		
	}

	/**
	 * @return the documentsIndexed
	 */
	public int getDocumentsIndexed() {
		return documentsIndexed;
	}

	/**
	 * @param documentsIndexed the documentsIndexed to set
	 */
	public void setDocumentsIndexed(int documentsIndexed) {
		this.documentsIndexed = documentsIndexed;
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
}
