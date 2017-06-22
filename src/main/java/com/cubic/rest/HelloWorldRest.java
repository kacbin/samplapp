package com.cubic.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

@Service
@Path("/hello")
public class HelloWorldRest {

	@GET
	public String welcome() {
		return "REST Web Services Welcomes You ALL!!!";
	}
	
	

}
