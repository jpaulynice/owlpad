package com.owlpad.domain.configuration;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Jay Paulynice
 *
 */
@XmlRootElement
@Entity(name = "layout")
public class Layout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore 
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="layout_type")
	private LayoutType layoutType;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "layout", cascade = CascadeType.ALL)
	private List<Region> regions;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the layoutType
	 */
	public LayoutType getLayoutType() {
		return layoutType;
	}

	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}
}
