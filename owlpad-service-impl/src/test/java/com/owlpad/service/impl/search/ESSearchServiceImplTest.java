package com.owlpad.service.impl.search;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.owlpad.service.search.SearchService;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
public class ESSearchServiceImplTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private SearchService search;
	
	@Test
	public void testNonnullService(){
		Assert.assertNotNull(search);
	}

	/**
	 * @return the searchService
	 */
	public SearchService getSearchService() {
		return search;
	}

	/**
	 * @param searchService the searchService to set
	 */
	public void setSearchService(SearchService searchService) {
		this.search = searchService;
	}
}
