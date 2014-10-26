package com.owlpad.domain.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.owlpad.domain.exception.NoConfigFoundException;

@Provider
public class NoConfigFoundMapper implements ExceptionMapper<NoConfigFoundException> {

    @Override
    public Response toResponse(final NoConfigFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
