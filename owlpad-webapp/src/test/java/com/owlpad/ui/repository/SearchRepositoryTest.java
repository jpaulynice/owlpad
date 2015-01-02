package com.owlpad.ui.repository;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class SearchRepositoryTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SearchRepository repository;

    /**
     * test that the search repository is not null
     */
    @Test
    public void testNonnullSearchService() {
        Assert.assertNotNull(repository);
    }
}
