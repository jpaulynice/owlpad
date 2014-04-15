package com.searchapp.search.domain;

public class Document {
	private int docId;
	private String docTitle;
	private String docURI;
	
	public Document(){
		
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocURI() {
		return docURI;
	}

	public void setDocURI(String docURI) {
		this.docURI = docURI;
	}

}
