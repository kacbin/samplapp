package com.cubic.rest.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.cubic.exception.ValidationException;
import com.cubic.vo.ErrorResponse;

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException>{
	public Response toResponse(ValidationException exception){
		ErrorResponse entity = new ErrorResponse();
		entity.setCode("CUST-129");
		entity.setError(exception.getMessage());
		return Response.status(520).entity(entity).build();
	}

	

	

}
