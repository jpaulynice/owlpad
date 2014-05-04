package com.searchapp.ui.repository;

import com.searchapp.domain.search.SearchRequest;
import com.searchapp.domain.search.SearchResponse;
import com.searchapp.service.search.SearchService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Intermediate class between the controller and the service
 * 
 * @author Jay Paulynice
 * 
 */
public class SearchRepository {
	private SearchService searchService;

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
		SearchResponse response = new SearchResponse();
		try {
			response = searchService.search(searchRequest);
		} catch (Exception e) {
			// log error here. We need to return the
			// service response status as well...just map
			// from responses: 200, 404, 500 etc. to
			// SUCCESS, NOT_FOUND etc...
		}

		return response;
	}
}
