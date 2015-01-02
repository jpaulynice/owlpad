package com.owlpad.service.impl.index;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.owlpad.service.index.IndexService;

/**
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class ESIndexServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private IndexService index;

    /** test that the service is not null */
    @Test
    public void testNonnullService() {
        Assert.assertNotNull(index);
    }

    /**
     * @return the indexService
     */
    public IndexService getIndexService() {
        return index;
    }

    /**
     * @param indexService
     *            the indexService to set
     */
    public void setIndexService(final IndexService indexService) {
        this.index = indexService;
    }
}
