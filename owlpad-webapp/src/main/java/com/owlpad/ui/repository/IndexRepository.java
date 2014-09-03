package com.owlpad.ui.repository;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.domain.index.IndexRequest;
import com.owlpad.domain.index.IndexResponse;
import com.owlpad.domain.search.StatusType;
import com.owlpad.service.index.IndexService;

@Repository("indexRepository")
public class IndexRepository {
	private IndexService indexService;
	private static final Logger logger = LoggerFactory.getLogger(IndexRepository.class);
	
	@Autowired
	public IndexRepository(IndexService indexService){
		this.indexService=indexService;
	}
	
	public IndexResponse index(IndexRequest indexRequest) {
		Response serverResponse =  indexService.index(indexRequest);
		IndexResponse res = new IndexResponse();
		if (serverResponse!=null && serverResponse.getStatus() == 200) {
			res = serverResponse.readEntity(IndexResponse.class);
			res.setStatus(StatusType.SUCCESS);
		} else {
			res.setErrorMessage("Service error.");
			res.setStatus(StatusType.FAIL);
			logger.info("Exception while calling index method.");
		}
		return res;
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
