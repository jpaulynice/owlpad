package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.get.GetResponse;
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
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse res = new SearchResponse();
		int from = searchRequest.getResultStart();
		int size = searchRequest.getHitsPerPage();
		size = size == 0 ? Integer.MAX_VALUE:size;
		
		try{
			org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad-index")
					.setTypes("docs")
					.setSearchType(SearchType.QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.queryString(searchRequest.getKeyWord()))
					.addAggregation(AggregationBuilders.terms("authors").field("author"))
					.addAggregation(AggregationBuilders.terms("docTypes").field("docType"))
					.setFrom(from)
					.setSize(size)
					.execute()
					.actionGet();
			
			SearchHits hits = response.getHits();
			
			List<Document> docs = new ArrayList<Document>();
			int id = from + 1;
			for(SearchHit hit: hits){
				Document doc = new Document(hit,id);
				docs.add(doc);
				id++;
			}
			
			Aggregations aggs = response.getAggregations();
			Map<String,Facets> facets = getFacetsFromAggregations(aggs);
			
			res.setFacets(facets);
			res.setDocuments(docs);
			res.setTotalDocuments(hits.getTotalHits());
			res.setStatus(StatusType.SUCCESS);
		}catch(Exception e){
			logger.error("Exception while executing search",e);
			res.setStatus(StatusType.FAIL);
			res.setErrorMessage(e.toString());
		}

		return res;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.owlpad.service.search.SearchService#getDocById(java.lang.String)
	 */
	@Override
	public DocResponse getDocById(String docId) {
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
	
	/**
	 * Create facets from aggregations
	 * 
	 * @param aggs
	 * @return
	 */
	private Map<String,Facets> getFacetsFromAggregations(Aggregations aggs){
		Map<String,Facets> facets = new HashMap<String,Facets>();
		for(Aggregation ag: aggs){
			StringTerms st = (StringTerms) ag;
			Collection<org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket> buckets = st.getBuckets();
			Facets f = getFacetResults(buckets);
			facets.put(ag.getName(), f);
		}		
		return facets;
	}
	
	/**
	 * Create a facet result from aggregation term buckets.
	 * 
	 * @param b
	 * @return
	 */
	private Facets getFacetResults(Collection<org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket> buckets){
		Facets fr = new Facets();
		Set<FacetResult> fres = new HashSet<>();
		for(org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket b: buckets){
			FacetResult f = new FacetResult();
			f.setCount(b.getDocCount());
			f.setEntry(b.getKey());
			fres.add(f);
		}
		
		fr.setFacetResults(fres);
		return fr;
	}
}
