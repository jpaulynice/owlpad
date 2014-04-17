package com.searchapp.search.domain;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class SearchRequest {
	private String keyWord;
	private int maxHits;
	
	public SearchRequest(){
		
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the maxHits
	 */
	public int getMaxHits() {
		return maxHits;
	}

	/**
	 * @param maxHits the maxHits to set
	 */
	public void setMaxHits(int maxHits) {
		this.maxHits = maxHits;
	}

}
