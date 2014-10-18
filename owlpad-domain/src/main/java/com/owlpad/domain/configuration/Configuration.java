package com.owlpad.domain.configuration;

public class Configuration {
	private String name;
	private Layout layout;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param layout the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
}
