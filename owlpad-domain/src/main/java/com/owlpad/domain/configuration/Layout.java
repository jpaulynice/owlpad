package com.owlpad.domain.configuration;

import java.util.List;

public class Layout {
	private LayoutType layoutType;
	private List<Region> regions;
	
	/**
	 * @return the layoutType
	 */
	public LayoutType getLayoutType() {
		return layoutType;
	}
	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}
	
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}
	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
	
}
