package com.searchapp.domain.search;

import java.util.List;

/**
 * A document is simply a file that can be in different formats.
 * 
 * @author Jay Paulynice
 *
 */
public class Document {
	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	private List<Field> fields;
	
	public Document(){
		
	}
}
