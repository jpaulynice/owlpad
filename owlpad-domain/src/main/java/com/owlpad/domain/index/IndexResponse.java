package com.owlpad.domain.index;

import com.owlpad.domain.search.StatusType;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class IndexResponse {
	private int documentsIndexed;
	private StatusType status;
	
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
}
