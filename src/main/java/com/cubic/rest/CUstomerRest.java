package com.cubic.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Service;

import com.cubic.service.CustomerMemoryDB;
import com.cubic.vo.AuthVO;
import com.cubic.vo.CustomerVO;

@Service
@Path("/customer")
public class CUstomerRest {

	@GET
	public String Welcome() {
		return "REST WEB SERVICES ";
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") final Long pk) {
		final CustomerVO entity = CustomerMemoryDB.get().getCustomer(pk);
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomers() {
		final List<CustomerVO> customers = CustomerMemoryDB.get().getCustomers();
		CustomerList entity = new CustomerList(customers);
		return Response.ok().entity(entity).build();
	}

	// QueryParam
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_XML)
	public Response searchCustomer(@QueryParam("firstName") final String firstName,
			@QueryParam("lastName") final String lastName) {
		final List<CustomerVO> customers = CustomerMemoryDB.get().searchCustomers(firstName, lastName);
		CustomerList entity = new CustomerList(customers);
		return Response.ok().entity(entity).build();
	}

	// MatrixParam
	@GET
	@Path("/matrix")
	@Produces(MediaType.APPLICATION_XML)
	public Response matrixCustomer(@MatrixParam("firstName") final String firstName,
			@MatrixParam("lastName") final String lastName) {
		final List<CustomerVO> customers = CustomerMemoryDB.get().searchCustomers(firstName, lastName);
		CustomerList entity = new CustomerList(customers);
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/hdr")
	@Produces(MediaType.APPLICATION_XML)
	public Response getHdrParam(@HeaderParam("auth-type") final String authType,
			@HeaderParam("auth-key") final String authKey) {
		AuthVO result = new AuthVO(authType, authKey);
		return Response.ok().entity(result).build();
	}

	@GET
	@Path("/hdrset")
	@Produces(MediaType.APPLICATION_XML)
	public Response getHdrParam(@Context final HttpHeaders headers) {

		final String authType = headers.getRequestHeader("auth-type").get(0);
		final String authKey = headers.getRequestHeader("auth-key").get(0);

		AuthVO result = new AuthVO(authType, authKey);
		return Response.ok().entity(result).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createCustomer(final CustomerVO vo) {
		final CustomerVO entity = CustomerMemoryDB.get().addCustomer(vo);
		ResponseBuilder rb = Response.ok();
		if (vo.getFirstName().startsWith("J")) {
			rb = rb.type(MediaType.APPLICATION_XML);
		} else {
			rb = rb.type(MediaType.APPLICATION_JSON);

		}
		return rb.entity(entity).build();
	}

	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateCustomer(final CustomerVO vo) {
		final CustomerVO result = CustomerMemoryDB.get().updateCustomer(vo);
		return Response.ok().entity(result).build();

	}

	@DELETE
	@Path("/{id}")
	public Response removeCustomer(@PathParam("id") final Long pk) {
		CustomerMemoryDB.get().removeCustomer(pk);
		return Response.noContent().build();
	}

}
