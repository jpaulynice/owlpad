package com.owlpad.domain.exception;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DocumentNotFoundException implements ExceptionMapper<EntityNotFoundException>{

	@Override
	public Response toResponse(EntityNotFoundException e) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
