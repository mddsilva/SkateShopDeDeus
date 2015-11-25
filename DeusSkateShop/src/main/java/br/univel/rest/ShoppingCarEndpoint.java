package br.univel.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.univel.beans.ShoppingCart;

@Path("/shoppingcart")
public class ShoppingCarEndpoint {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok(" ShoppingCarEndpoint-doGet").build();
	}

	@POST
	@Consumes({"text/plain", "application/json"})
	public Response doPost(String entity) {
		return Response.created(
				UriBuilder.fromResource(ShoppingCart.class).build()).build();
	}

	@PUT
	@Consumes({"text/plain", "application/json"})
	public Response doPut(String entity) {
		return Response.created(
				UriBuilder.fromResource(ShoppingCart.class).build()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response doDelete(@PathParam("id") Long id) {
		return Response.noContent().build();
	}
}
