package com.owlpad.ui.repository;

import javax.ws.rs.core.Response;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.domain.search.StatusType;
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
@Repository
public class SearchRepository {
	private final SearchService searchService;
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
		Response serverResponse = searchService.search(searchRequest);
		SearchResponse res = new SearchResponse();
		if (serverResponse.getStatus() == 200) {
			res = serverResponse.readEntity(SearchResponse.class);
			res.setStatus(StatusType.SUCCESS);
		}
		else {
			res.setErrorMessage("Service error.");
			res.setStatus(StatusType.FAIL);
			logger.info("Exception while calling search method.");
		}
		return res;
	}
	
	/**
	 * Get a document content by docId
	 * 
	 * @param docId
	 * @return
	 */
	public DocResponse getDocContentById(String docId){
		Response serverResponse = searchService.getDocContentById(docId);
		DocResponse res = new DocResponse();
		if (serverResponse.getStatus() == 200) {
			res = serverResponse.readEntity(DocResponse.class);
			res.setStatus(StatusType.SUCCESS);
		} 
		else if (serverResponse.getStatus() == 404) {
			res.setErrorMessage("No documents found with id :"+docId);
			res.setStatus(StatusType.FAIL);
		}else if(serverResponse.getStatus() == 500){
			res.setErrorMessage("Internal server error.");
			res.setStatus(StatusType.FAIL);
		}else{
			res.setErrorMessage("Unknown exception.");
			res.setStatus(StatusType.FAIL);
		}
		return res;
	}
}
