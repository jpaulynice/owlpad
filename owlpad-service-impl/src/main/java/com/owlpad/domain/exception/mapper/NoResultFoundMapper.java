package com.owlpad.domain.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.owlpad.domain.exception.NoDocFoundException;

@Provider
public class NoResultFoundMapper implements ExceptionMapper<NoDocFoundException> {

    @Override
    public Response toResponse(final NoDocFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}