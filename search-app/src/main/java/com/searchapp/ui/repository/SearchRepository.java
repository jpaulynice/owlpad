package com.searchapp.ui.repository;

import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;
import com.searchapp.search.service.SearchService;

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
	public SearchRepository(SearchService searchService){
		this.searchService = searchService;
	}
	
	/**
	 * call to the service to search
	 * 
	 * @param searchRequest
	 * @return
	 */
	public SearchResponse search(SearchRequest searchRequest){
		return searchService.search(searchRequest);
	}
}
