package com.owlpad.service.elasticsearch.client;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class NodeClientFactoryBeanTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private NodeClientFactoryBean nodeClientFactoryBean;
	
	public NodeClientFactoryBeanTest(){
		
	}
	
	@Test
	public void testNonnullNodeClientFactoryBean(){
		Assert.assertNotNull(nodeClientFactoryBean);
	}

	/**
	 * @return the nodeClientFactoryBean
	 */
	public NodeClientFactoryBean getNodeClientFactoryBean() {
		return nodeClientFactoryBean;
	}

	/**
	 * @param nodeClientFactoryBean the nodeClientFactoryBean to set
	 */
	public void setNodeClientFactoryBean(NodeClientFactoryBean nodeClientFactoryBean) {
		this.nodeClientFactoryBean = nodeClientFactoryBean;
	}

}
