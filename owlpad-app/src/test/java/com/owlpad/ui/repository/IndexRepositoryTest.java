package com.owlpad.ui.repository;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
public class IndexRepositoryTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private IndexRepository indexRepository;
	
	public IndexRepositoryTest(){
	}
	
	@Test
	public void testNonnullIndexRepository(){
		Assert.assertNotNull(indexRepository);
	}

	/**
	 * @return the indexRepository
	 */
	public IndexRepository getIndexRepository() {
		return indexRepository;
	}

	/**
	 * @param indexRepository the indexRepository to set
	 */
	public void setIndexRepository(IndexRepository indexRepository) {
		this.indexRepository = indexRepository;
	}
}
