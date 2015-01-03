package com.owlpad.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.owlpad.domain.configuration.Configuration;
import com.owlpad.domain.configuration.Layout;
import com.owlpad.domain.configuration.Region;

/**
 *
 * @author Jay Paulynice
 *
 */
public class ConfigMapper {
    /**
     * @param config the dto object
     * @return configuration domain object
     */
    public static Configuration map(
            final com.owlpad.service.model.Configuration config) {
        final Configuration c = new Configuration();
        c.setLayout(getLayout(config.getLayout()));
        c.setName(config.getName());

        return c;
    }

    private static Layout getLayout(final com.owlpad.service.model.Layout layout) {
        final Layout l = new Layout();
        l.setLayoutType(layout.getLayoutType());

        final List<Region> regions = new ArrayList<>();
        final List<com.owlpad.service.model.Region> rs = layout.getRegions();
        for (final com.owlpad.service.model.Region r : rs) {
            regions.add(getRegion(r));
        }
        l.setRegions(regions);

        return l;
    }

    private static Region getRegion(final com.owlpad.service.model.Region region) {
        final Region r = new Region();
        r.setName(region.getName());
        r.setSelector(region.getSelector());

        return r;
    }

}
