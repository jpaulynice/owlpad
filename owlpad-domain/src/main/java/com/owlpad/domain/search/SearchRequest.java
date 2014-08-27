package com.owlpad.domain.search;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement(name = "searchRequest")
public class SearchRequest {
	private String keyWord;
	private int hitsPerPage;
	private int resultStart;
	private boolean paging;
	
	public SearchRequest(){
		
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the resultStart
	 */
	public int getResultStart() {
		return resultStart;
	}

	/**
	 * @param resultStart the resultStart to set
	 */
	public void setResultStart(int resultStart) {
		this.resultStart = resultStart;
	}

	/**
	 * @return the hitsPerPage
	 */
	public int getHitsPerPage() {
		return hitsPerPage;
	}

	/**
	 * @param hitsPerPage the hitsPerPage to set
	 */
	public void setHitsPerPage(int hitsPerPage) {
		this.hitsPerPage = hitsPerPage;
	}

	/**
	 * @return the paging
	 */
	public boolean isPaging() {
		return paging;
	}

	/**
	 * @param paging the paging to set
	 */
	public void setPaging(boolean paging) {
		this.paging = paging;
	}

}
