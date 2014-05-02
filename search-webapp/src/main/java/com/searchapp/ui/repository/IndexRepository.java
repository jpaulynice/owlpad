package com.searchapp.ui.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.searchapp.index.domain.IndexRequest;
import com.searchapp.index.domain.IndexResponse;
import com.searchapp.index.service.IndexService;

public class IndexRepository {
	private IndexService indexService;
	
	@Autowired
	public IndexRepository(IndexService indexService){
		this.indexService=indexService;
	}
	
	public IndexResponse index(IndexRequest indexRequest) {
		return null;
	}

	/**
	 * @return the indexService
	 */
	public IndexService getIndexService() {
		return indexService;
	}

	/**
	 * @param indexService the indexService to set
	 */
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}
}
