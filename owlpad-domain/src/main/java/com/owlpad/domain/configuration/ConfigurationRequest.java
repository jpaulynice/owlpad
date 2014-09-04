package com.owlpad.domain.configuration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfigurationRequest {
	private String userName;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
