package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.base.Preconditions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.FacetResult;
import com.owlpad.domain.search.Facets;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.domain.search.StatusType;
import com.owlpad.service.elasticsearch.client.NodeClientFactoryBean;
import com.owlpad.service.search.SearchService;

/**
 * Elasticsearch searchService implementation
 * 
 * @author Jay Paulynice
 *
 */
@Service("searchService")
public class ESSearchServiceImpl implements SearchService{
	private static final Logger logger = LoggerFactory.getLogger(ESSearchServiceImpl.class);

	private NodeClientFactoryBean nodeClientFactoryBean;
	private NodeClient client;
	
	@Autowired
	public ESSearchServiceImpl(NodeClientFactoryBean nodeClientFactoryBean) throws Exception{
		this.nodeClientFactoryBean = nodeClientFactoryBean;
		this.client = this.nodeClientFactoryBean.getObject();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.search.SearchService#search(com.owlpad.domain.search.SearchRequest)
	 */
	@Override
	public Response search(SearchRequest searchRequest) {
		Preconditions.checkNotNull(searchRequest,"No search request specified.");
		SearchResponse res;
		
		int from = searchRequest.getResultStart();
		int size = searchRequest.getHitsPerPage();
		boolean paging = searchRequest.isPaging();
		String keyWord = searchRequest.getKeyWord();
		
		try{
			org.elasticsearch.action.search.SearchResponse response = search(paging,keyWord,from,size);
			res = getInternalResponse(response,from);
		}catch(Exception e){
			logger.error("Exception while executing search",e);
			return Response.serverError().build();
		}

		GenericEntity<SearchResponse> entity = new GenericEntity<SearchResponse>(res){};
		return Response.ok().entity(entity).build();
	}
	
	/**
	 * Execute search given parameters.  If we're paging, we don't need to add aggregations.  
	 * Looking to use scrolling instead.
	 * 
	 * @param paging
	 * @param keyWord
	 * @param from
	 * @param size
	 * @return
	 */
	private org.elasticsearch.action.search.SearchResponse search(boolean isPaging,String keyWord,int from, int size) throws Exception{
		
		SearchRequestBuilder builder = client.prepareSearch("owlpad-index")
				.setTypes("docs")
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.queryString(keyWord))
				.setFrom(from)
				.setSize(size);
		
		if(!isPaging){
			builder.addAggregation(AggregationBuilders.terms("authors").field("author"))
			.addAggregation(AggregationBuilders.terms("docTypes").field("docType"));
		}
		
		return builder.execute().actionGet();
	}
	
	/**
	 * Map from elastic search searchResponse to internal searchResponse
	 * 
	 * @param response
	 * @param from
	 * @return
	 */
	private SearchResponse getInternalResponse(org.elasticsearch.action.search.SearchResponse response, int from){
		SearchHits hits = response.getHits();
		
		List<Document> docs = getDocumentsFromSearchHits(hits,from);
		HashMap<String,Facets> facets = getFacetsFromAggregations(response.getAggregations());
		
		return new SearchResponse(StatusType.SUCCESS,docs,hits.getTotalHits(),facets,null);
	}
	
	/**
	 * Map from searchHits to Documents
	 * 
	 * @param hits
	 * @param from
	 * @return
	 */
	private List<Document> getDocumentsFromSearchHits(SearchHits hits, int from){
		List<Document> docs = new ArrayList<Document>();

		int id = from + 1;
		for(SearchHit hit: hits){
			Document doc = new Document(hit,id);
			docs.add(doc);
			id++;
		}
		
		return docs;
	}
	
	/**
	 * Create facets from aggregations
	 * 
	 * @param aggs
	 * @return
	 */
	private HashMap<String,Facets> getFacetsFromAggregations(Aggregations aggs){
		HashMap<String,Facets> facets = new HashMap<String,Facets>();
		for(Aggregation ag: aggs){
			StringTerms st = (StringTerms) ag;
			Facets f = getFacetResults(st.getBuckets());
			facets.put(ag.getName(), f);
		}		
		return facets;
	}
	
	/**
	 * Create facets result from aggregation term buckets.
	 * 
	 * @param b
	 * @return
	 */
	private Facets getFacetResults(Collection<Bucket> buckets){
		Set<FacetResult> fres = new HashSet<>();
		for(Bucket b: buckets){
			FacetResult f = new FacetResult();
			f.setCount(b.getDocCount());
			f.setEntry(b.getKey());
			fres.add(f);
		}
		return new Facets(fres);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.search.SearchService#getDocById(java.lang.String)
	 */
	@Override
	public DocResponse getDocContentById(String docId) {
		Preconditions.checkNotNull(docId,"Document id is required to get document.");
		
		DocResponse res = new DocResponse();
		GetResponse response = null;
		
		try{
			response = client.prepareGet("owlpad-index","docs",docId)
					.execute()
					.actionGet();
			String source = (String) response.getSourceAsMap().get("contents");
			
			res.setSource(source);
			res.setStatus(StatusType.SUCCESS);
		}catch(Exception e){
			logger.error("Exception while executing getDocById",e);
			res.setStatus(StatusType.FAIL);
			res.setErrorMessage(e.toString());
		}
		
		return res;
	}
}
