package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.elasticsearch.client.NodeClientFactoryBean;
import com.owlpad.service.search.SearchService;

/**
 * Elasticsearch searchService implementation
 * 
 * @author Jay Paulynice
 *
 */
@Service("esSearchService")
public class ESSearchServiceImpl implements SearchService{
	NodeClientFactoryBean nodeClientFactoryBean;
	NodeClient client;
	
	@Autowired
	public ESSearchServiceImpl(NodeClientFactoryBean nodeClientFactoryBean) throws Exception{
		this.nodeClientFactoryBean = nodeClientFactoryBean;
		client = this.nodeClientFactoryBean.getObject();
	}
	
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse internalResponse = new SearchResponse();

		org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad-index")
				.setTypes("docs")
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("contents",searchRequest.getKeyWord()))
				.setFrom(0)
				.setSize(searchRequest.getMaxHits())
				.execute()
				.actionGet();
		SearchHits hits = response.getHits();

		List<Document> docs = new ArrayList<>();
		int id = 1;
		for(SearchHit hit: hits){
			Document doc = new Document(hit,id);
			docs.add(doc);
			id++;
		}
		internalResponse.setDocuments(docs);

		return internalResponse;
	}
}
