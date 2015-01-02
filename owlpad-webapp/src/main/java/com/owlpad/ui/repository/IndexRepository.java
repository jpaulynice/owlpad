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

/**
 * {@link IndexRepository} is simple intermediate class to call the API
 *
 * @author Jay Paulynice
 *
 */
@Repository
public class IndexRepository {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final IndexService service;

    /**
     * @param service
     *            the index service handler
     */
    @Autowired
    public IndexRepository(final IndexService service) {
        this.service = service;
    }

    /**
     * Call the API to index and process the response
     *
     * @param req
     *            the index request object
     * @return {@link IndexResponse} object
     */
    public IndexResponse index(final IndexRequest req) {
        final Response serverResponse = service.index(req);
        IndexResponse res = new IndexResponse();
        if (serverResponse != null && serverResponse.getStatus() == 200) {
            res = serverResponse.readEntity(IndexResponse.class);
            res.setStatus(StatusType.SUCCESS);
        } else {
            res.setErrorMessage("Service error.");
            res.setStatus(StatusType.FAIL);
            LOG.info("Exception while calling index method.");
        }
        return res;
    }
}
