package com.owlpad.service.elasticsearch.client;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Elastic search NodeClient factory bean 
 * 
 * @author Jay Paulynice
 *
 */
@Component
public class NodeClientFactoryBean implements FactoryBean<NodeClient>, InitializingBean, DisposableBean{
	
	private static final Logger logger = LoggerFactory.getLogger(NodeClientFactoryBean.class);
	private boolean local;
	private boolean enableHttp;
	private String clusterName;
	private NodeClient nodeClient;

	NodeClientFactoryBean() {
	}

	public NodeClientFactoryBean(String clusterName, boolean local, boolean enableHttp) {
		this.clusterName = clusterName;
		this.local = local;
		this.enableHttp = enableHttp;
	}

	@Override
	public NodeClient getObject() throws Exception {
		return nodeClient;
	}

	@Override
	public Class<? extends Client> getObjectType() {
		return NodeClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ImmutableSettings.Builder settings = ImmutableSettings.settingsBuilder().put("http.enabled",
				String.valueOf(this.enableHttp));

		this.nodeClient = (NodeClient) nodeBuilder().settings(settings).clusterName(this.clusterName).local(this.local).node()
				.client();
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public void setEnableHttp(boolean enableHttp) {
		this.enableHttp = enableHttp;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Override
	public void destroy() throws Exception {
		try {
			logger.info("Closing elasticSearch  client");
			if (nodeClient != null) {
				nodeClient.close();
			}
		} catch (final Exception e) {
			logger.error("Error closing ElasticSearch client: ", e);
		}
	}
}
