package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse internalResponse = new SearchResponse();
		int from = searchRequest.getResultStart();
		int size = searchRequest.getHitsPerPage();
		size = size == 0 ? Integer.MAX_VALUE:size;
		
		try{
			org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad-index")
					.setTypes("docs")
					.setSearchType(SearchType.QUERY_THEN_FETCH)
					.setQuery(QueryBuilders.queryString(searchRequest.getKeyWord()))
					//.addFacet(FacetBuilders.termsFacet("facets").field("author"))
					//.addFacet(FacetBuilders.termsFacet("facets").field("docType"))
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
			List<FacetResult> facets = new ArrayList<FacetResult>();
			
			for(Aggregation ag: aggs){
				StringTerms st = (StringTerms) ag;
				Collection<org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket> buckets = st.getBuckets();
				for(org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket b: buckets){
					FacetResult fr = new FacetResult();
					fr.setEntry(b.getKey());
					fr.setCount(b.getDocCount());
					facets.add(fr);
				}
				
			}
			
			internalResponse.setFacets(facets);
			internalResponse.setDocuments(docs);
			internalResponse.setTotalDocuments(hits.getTotalHits());
			internalResponse.setStatus(StatusType.SUCCESS);
		}catch(Exception e){
			logger.error("Exception while executing search",e);
			internalResponse.setStatus(StatusType.FAIL);
			internalResponse.setErrorMessage(e.toString());
		}

		return internalResponse;
	}

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
}
