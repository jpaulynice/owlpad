package com.owlpad.ui.repository;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.owlpad.domain.search.DocResponse;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.domain.search.StatusType;
import com.owlpad.service.search.SearchService;

/**
 * Intermediate class between the controller and the service
 *
 * @author Jay Paulynice
 *
 */
@Repository
public class SearchRepository {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final SearchService service;

    /**
     * @param service
     *            the search service
     */
    @Autowired
    public SearchRepository(final SearchService service) {
        this.service = service;
    }

    /**
     * call to the service to search
     *
     * @param req
     *            the search request object
     * @return {@link SearchResponse} object
     */
    public SearchResponse search(final SearchRequest req) {
        LOG.info("Executing method to search.");
        final Response serverResponse = service.search(req);
        SearchResponse res = new SearchResponse();
        if (serverResponse.getStatus() == 200) {
            LOG.info("Successfully executed call to search.");
            res = serverResponse.readEntity(SearchResponse.class);
            res.setStatus(StatusType.SUCCESS);
        } else {
            res.setErrorMessage("Service error.");
            res.setStatus(StatusType.FAIL);
            LOG.info("Exception while calling search method.");
        }
        return res;
    }

    /**
     * Get a document content by docId
     *
     * @param docId
     *            the document id
     * @return {@link DocResponse} object
     */
    public DocResponse search(final String docId) {
        final Response serverResponse = service.search(docId);
        DocResponse res = new DocResponse();
        if (serverResponse.getStatus() == 200) {
            res = serverResponse.readEntity(DocResponse.class);
        } else if (serverResponse.getStatus() == 404) {
            res.setErrorMessage("No documents found with id :" + docId);
            res.setStatus(StatusType.FAIL);
        } else if (serverResponse.getStatus() == 500) {
            res.setErrorMessage("Internal server error.");
            res.setStatus(StatusType.FAIL);
        } else {
            res.setErrorMessage("Unknown exception.");
            res.setStatus(StatusType.FAIL);
        }
        return res;
    }
}
