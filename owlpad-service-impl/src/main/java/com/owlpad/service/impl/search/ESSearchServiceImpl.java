package com.owlpad.service.impl.search;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.search.SearchService;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class ESSearchServiceImpl implements SearchService{
	
	/*public static void main(String [] args){
		ESSearchServiceImpl search = new ESSearchServiceImpl();
		
		SearchRequest request = new SearchRequest();
		request.setKeyWord("Paulynice");
		search.search(request);
		
	}*/
	
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse r = new SearchResponse();
		
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		Client client = new TransportClient(settings)
									.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		
		org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad-index")
		        .setTypes("docs")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.termQuery("contents", searchRequest.getKeyWord()))
		        .execute()
		        .actionGet();
		SearchHits hits = response.getHits();
		
		for(SearchHit hit: hits){
			System.out.println(hit.getSourceAsString());
		}
		
		return r;
	}
}
