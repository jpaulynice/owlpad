package com.owlpad.service.impl.search;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import static org.elasticsearch.node.NodeBuilder.*;


import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.search.SearchService;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class ESSearchServiceImpl implements SearchService{
	
	public static void main(String [] args){
		SearchService search = new ESSearchServiceImpl();
		
		SearchRequest request = new SearchRequest();
		request.setKeyWord("Paulynice");
		search.search(request);
		
	}
	
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse r = new SearchResponse();
		
		Client client = nodeBuilder().clusterName("elasticsearch").node().client();
		
		org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad6")
		        .setTypes("docs")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.matchQuery("contents","Paulynice"))
		        .setFrom(0).setSize(60).setExplain(true)
		        .execute()
		        .actionGet();
		SearchHits hits = response.getHits();
		
		for(SearchHit hit: hits){
			System.out.println(hit.getSourceAsString());
		}
		client.close();
		
		return r;
	}
}
