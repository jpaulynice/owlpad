package com.owlpad.service.esclient;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import org.elasticsearch.client.Client;

/**
 * Singleton Client instance
 * 
 * @author Jay Paulynice
 *
 */
public class ESSingletonClient{
	public static Client INSTANCE = nodeBuilder().clusterName("elasticsearch").node().client();
	
	private ESSingletonClient(){
		
	}
}
