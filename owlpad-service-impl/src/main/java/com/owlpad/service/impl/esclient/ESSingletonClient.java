package com.owlpad.service.impl.esclient;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import org.elasticsearch.client.Client;

public class ESSingletonClient{
	public static Client INSTANCE = nodeBuilder().clusterName("elasticsearch").node().client();
	
	private ESSingletonClient(){
		
	}
}
