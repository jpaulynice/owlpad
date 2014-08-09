package com.owlpad.ui.repository;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:servlet-context.xml"})
public class SearchRepositoryTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private SearchRepository searchRepository;
	
	public SearchRepositoryTest(){
		
	}
	
	@Test
	public void testNonnullSearchService(){
		Assert.assertNotNull(searchRepository);
	}

	/**
	 * @return the searchRepository
	 */
	public SearchRepository getSearchRepository() {
		return searchRepository;
	}

	/**
	 * @param searchRepository the searchRepository to set
	 */
	public void setSearchRepository(SearchRepository searchRepository) {
		this.searchRepository = searchRepository;
	}

}
