package com.owlpad.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.owlpad.domain.configuration.Configuration;
import com.owlpad.domain.configuration.Layout;
import com.owlpad.domain.configuration.Region;

public class ConfigMapper {
	public static Configuration mapConfig(com.owlpad.service.model.Configuration config){
		Configuration c = new Configuration();
		c.setLayout(getLayout(config.getLayout()));
		c.setName(config.getName());
		
		return c;
	}
	
	private static Layout getLayout(com.owlpad.service.model.Layout layout){
		Layout l = new Layout();
		l.setLayoutType(layout.getLayoutType());
		
		List<Region> regions = new ArrayList<>();
		List<com.owlpad.service.model.Region> rs = layout.getRegions();
		for(com.owlpad.service.model.Region r: rs){
			regions.add(getRegion(r));
		}
		l.setRegions(regions);
		
		return l;
	}
	
	private static Region getRegion(com.owlpad.service.model.Region region){
		Region r = new Region();
		r.setName(region.getName());
		r.setSelector(region.getSelector());
		
		return r;
	}
	
	

}
