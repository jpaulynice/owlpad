package com.owlpad.domain.search;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class Field {
	private String fieldId;
	private String i18nKey;
	private String name;
	private String fieldType;
	private String value;
	
	public Field(){
		
	}

	/**
	 * @return the fieldId
	 */
	public String getFieldId() {
		return fieldId;
	}

	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * @return the i18nKey
	 */
	public String getI18nKey() {
		return i18nKey;
	}

	/**
	 * @param i18nKey the i18nKey to set
	 */
	public void setI18nKey(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fieldType
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
