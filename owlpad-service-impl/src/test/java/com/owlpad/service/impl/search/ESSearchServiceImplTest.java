package com.owlpad.service.impl.search;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.owlpad.service.search.SearchService;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ESSearchServiceImplTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private SearchService searchService;
	
	@Test
	public void testNonnullService(){
		Assert.assertNotNull(searchService);
	}

	/**
	 * @return the searchService
	 */
	public SearchService getSearchService() {
		return searchService;
	}

	/**
	 * @param searchService the searchService to set
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}
