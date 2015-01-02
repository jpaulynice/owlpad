package com.owlpad.domain.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jay Paulynice
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "field")
public class Field {
    private String fieldId;
    private String i18nKey;
    private String name;
    private String fieldType;
    private String value;
    private boolean visible;

    /** Default constructor */
    public Field() {

    }

    /**
     * @return the fieldId
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     *            the fieldId to set
     */
    public void setFieldId(final String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return the i18nKey
     */
    public String getI18nKey() {
        return i18nKey;
    }

    /**
     * @param i18nKey
     *            the i18nKey to set
     */
    public void setI18nKey(final String i18nKey) {
        this.i18nKey = i18nKey;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the fieldType
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * @param fieldType
     *            the fieldType to set
     */
    public void setFieldType(final String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible
     *            the visible to set
     */
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
}
