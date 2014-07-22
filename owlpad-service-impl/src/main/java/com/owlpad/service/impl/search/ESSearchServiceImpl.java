package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.esclient.ESSingletonClient;
import com.owlpad.service.search.SearchService;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class ESSearchServiceImpl implements SearchService{

	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse internalResponse = new SearchResponse();

		Client client = ESSingletonClient.INSTANCE;

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
		int num = 1;
		for(SearchHit hit: hits){
			Document doc = new Document(hit,num);
			docs.add(doc);
			num++;
		}
		internalResponse.setDocuments(docs);
		client.close();

		return internalResponse;
	}
}
