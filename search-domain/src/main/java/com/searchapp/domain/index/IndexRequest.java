package com.searchapp.domain.index;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class IndexRequest {
	private String directoryPath;
	private String indexDirectoryPath;
	private String suffix;
	
	public IndexRequest(){
		
	}

	/**
	 * @return the directoryPath
	 */
	public String getDirectoryPath() {
		return directoryPath;
	}

	/**
	 * @param directoryPath the directoryPath to set
	 */
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	/**
	 * @return the indexDirectoryPath
	 */
	public String getIndexDirectoryPath() {
		return indexDirectoryPath;
	}

	/**
	 * @param indexDirectoryPath the indexDirectoryPath to set
	 */
	public void setIndexDirectoryPath(String indexDirectoryPath) {
		this.indexDirectoryPath = indexDirectoryPath;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
