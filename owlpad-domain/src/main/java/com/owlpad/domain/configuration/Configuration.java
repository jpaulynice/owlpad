package com.owlpad.domain.configuration;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity(name="configuration")
public class Configuration {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Layout layout;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the layout
	 */
	public Layout getLayout() {
		return layout;
	}


	/**
	 * @param layout the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}
