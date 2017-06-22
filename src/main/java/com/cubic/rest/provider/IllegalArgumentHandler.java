package com.cubic.rest.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.cubic.vo.ErrorResponse;


@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class IllegalArgumentHandler implements ExceptionMapper<IllegalArgumentException> {

	public Response toResponse(IllegalArgumentException exception) {

		ErrorResponse entity = new ErrorResponse();
		entity.setCode("CUST-140");
		entity.setError(exception.getMessage());
		return Response.status(515).entity(entity).type(MediaType.APPLICATION_JSON).build();
	}

}
	
