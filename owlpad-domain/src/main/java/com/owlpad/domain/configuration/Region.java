package com.owlpad.domain.configuration;

public class Region {
	private String name;
	private String selector;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the selector
	 */
	public String getSelector() {
		return selector;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}
}
