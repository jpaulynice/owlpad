package com.owlpad.ui.repository;

import javax.ws.rs.core.Response;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.search.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Intermediate class between the controller and the service
 * 
 * @author Jay Paulynice
 * 
 */
@Repository("searchRepository")
public class SearchRepository {
	private SearchService searchService;
	private static final Logger logger = LoggerFactory.getLogger(SearchRepository.class);


	@Autowired
	public SearchRepository(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * call to the service to search
	 * 
	 * @param searchRequest
	 * @return
	 */
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse response = null;
		Response r = searchService.search(searchRequest);
		if (r!=null && r.getStatus() == 200) {
			response = r.readEntity(SearchResponse.class);
		} else {
			logger.info("Exception while calling search method");
		}
		return response;
	}
	
	public DocResponse getDocById(String docId){
		DocResponse response = new DocResponse();
		try {
			response = searchService.getDocContentById(docId);
		} catch (Exception e) {
			//
		}

		return response;
	}
}
