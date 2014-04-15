package com.searchapp.search.service.impl;

import com.searchapp.search.domain.SearchRequest;
import com.searchapp.search.domain.SearchResponse;
import com.searchapp.search.service.SearchService;

public class SearchServiceImpl implements SearchService {

	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		return new SearchResponse();
	}

}
