package com.owlpad.service.esclient;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Singleton Client instance
 * 
 * @author Jay Paulynice
 *
 */
public class ESClientProvider implements InitializingBean, DisposableBean{
	public static Client INSTANCE;
	
	private ESClientProvider(){
	
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		INSTANCE = nodeBuilder().clusterName("elasticsearch").node().client();
	}

	@Override
	public void destroy() throws Exception {
		INSTANCE.close();
	}
}
