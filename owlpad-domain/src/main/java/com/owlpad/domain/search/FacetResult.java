package com.owlpad.domain.search;


public class FacetResult {
	private String entry;
	private long count;
	
	public FacetResult(){
		
	}

	/**
	 * @return the entry
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param entry the entry to set
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

	/**
	 * @param l the count to set
	 */
	public void setCount(long l) {
		this.count = l;
	}
}
