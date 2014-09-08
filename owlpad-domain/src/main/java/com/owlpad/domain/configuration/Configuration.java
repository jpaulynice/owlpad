package com.owlpad.domain.configuration;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement
@Entity(name = "configuration")
public class Configuration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore 
	private int id;
	
	@Column(name="name")
	@XmlElement(name = "name")
	private String name;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
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
	 * @param layout
	 *            the layout to set
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
}
