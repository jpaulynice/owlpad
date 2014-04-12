package com.myapp.search.service;

import com.myapp.search.domain.SearchRequest;
import com.myapp.search.domain.SearchResponse;

public interface SearchService {
	
	public SearchResponse search(SearchRequest searchRequest);
}
