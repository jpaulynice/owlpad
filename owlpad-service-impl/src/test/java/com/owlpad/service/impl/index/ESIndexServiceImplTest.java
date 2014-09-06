package com.owlpad.service.impl.index;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.owlpad.service.index.IndexService;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
public class ESIndexServiceImplTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private IndexService indexService;
	
	@Test
	public void testNonnullService(){
		Assert.assertNotNull(indexService);
	}

	/**
	 * @return the indexService
	 */
	public IndexService getIndexService() {
		return indexService;
	}

	/**
	 * @param indexService the indexService to set
	 */
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}
}
