package com.owlpad.service.esclient;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Singleton Client instance
 * 
 * @author Jay Paulynice
 *
 */
@Component
public class ESClientProvider implements InitializingBean, DisposableBean{
	private static Client client;
	
	private ESClientProvider(){

	}
	
	public static synchronized Client getInstance(){
		return client;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		client = nodeBuilder().clusterName("elasticsearch").node().client();
	}

	@Override
	public void destroy() throws Exception {
		client.close();
	}
}
