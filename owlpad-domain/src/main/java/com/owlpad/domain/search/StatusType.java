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
@XmlRootElement(name = "status")
public enum StatusType {
    /**
     * successful method execution
     */
    SUCCESS,
    /**
     * not successful
     */
    FAIL,
    /**
     * not found
     */
    NOT_FOUND
}
