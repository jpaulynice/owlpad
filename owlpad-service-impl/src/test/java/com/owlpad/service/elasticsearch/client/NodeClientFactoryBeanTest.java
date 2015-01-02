package com.owlpad.service.elasticsearch.client;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.owlpad.elasticsearch.client.NodeClientFactoryBean;

/**
 * @author Jay Paulynice
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class NodeClientFactoryBeanTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private NodeClientFactoryBean nodeClientFactoryBean;

    /** test that the service is not null */
    @Test
    public void testNonnullNodeClientFactoryBean() {
        Assert.assertNotNull(nodeClientFactoryBean);
    }

    /**
     * @throws Exception
     *             if creation errors
     */
    @Test
    public void testGetClient() throws Exception {
        Assert.assertNotNull(nodeClientFactoryBean.getObject());
    }

    /**
     * @return the nodeClientFactoryBean
     */
    public NodeClientFactoryBean getNodeClientFactoryBean() {
        return nodeClientFactoryBean;
    }

    /**
     * @param nodeClientFactoryBean
     *            the nodeClientFactoryBean to set
     */
    public void setNodeClientFactoryBean(
            final NodeClientFactoryBean nodeClientFactoryBean) {
        this.nodeClientFactoryBean = nodeClientFactoryBean;
    }
}
