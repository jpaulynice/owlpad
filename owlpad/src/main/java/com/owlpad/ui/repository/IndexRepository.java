package com.owlpad.ui.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.service.index.IndexService;

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