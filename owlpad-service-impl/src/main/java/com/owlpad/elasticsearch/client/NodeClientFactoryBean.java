package com.owlpad.elasticsearch.client;

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
public class NodeClientFactoryBean implements FactoryBean<NodeClient>,
InitializingBean, DisposableBean {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private boolean local;
    private boolean enableHttp;
    private String clusterName;
    private NodeClient nodeClient;

    /** Default constructor */
    public NodeClientFactoryBean() {
    }

    /**
     * Constructor to create node client factory with elastic search parameters.
     *
     * @param clusterName
     *            the elastic search cluster name
     * @param local
     *            whether the cluster is local or remote
     * @param enableHttp
     *            whether to enable http requests
     */
    public NodeClientFactoryBean(final String clusterName, final boolean local,
            final boolean enableHttp) {
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
        final ImmutableSettings.Builder settings = ImmutableSettings
                .settingsBuilder().put("http.enabled",
                        String.valueOf(this.enableHttp));

        this.nodeClient = (NodeClient) nodeBuilder().settings(settings)
                .clusterName(this.clusterName).local(this.local).node()
                .client();
    }

    @Override
    public void destroy() throws Exception {
        try {
            LOG.info("Closing elasticSearch client");
            if (nodeClient != null) {
                nodeClient.close();
            }
        } catch (final Exception e) {
            LOG.error("Error closing ElasticSearch client: ", e);
        }
    }

    /**
     * @return the local
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * @return the enableHttp
     */
    public boolean isEnableHttp() {
        return enableHttp;
    }

    /**
     * @return the clusterName
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * @return the nodeClient
     */
    public NodeClient getNodeClient() {
        return nodeClient;
    }

    /**
     * @param local
     *            the local to set
     */
    public void setLocal(final boolean local) {
        this.local = local;
    }

    /**
     * @param enableHttp
     *            the enableHttp to set
     */
    public void setEnableHttp(final boolean enableHttp) {
        this.enableHttp = enableHttp;
    }

    /**
     * @param clusterName
     *            the clusterName to set
     */
    public void setClusterName(final String clusterName) {
        this.clusterName = clusterName;
    }

    /**
     * @param nodeClient
     *            the nodeClient to set
     */
    public void setNodeClient(final NodeClient nodeClient) {
        this.nodeClient = nodeClient;
    }
}
