package com.owlpad.domain.index;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class IndexResponse {
	private int documentsIndexed;
	
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
}
