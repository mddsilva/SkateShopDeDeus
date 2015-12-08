package br.univel.rest;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.univel.beans.ShoppingCart;
import br.univel.beans.ShoppingCartItem;
import br.univel.dao.ClienteDao;
import br.univel.dao.ProdutoDao;
import br.univel.dao.VendaDao;
import br.univel.model.ItemVenda;
import br.univel.model.Produto;
import br.univel.model.Venda;

@Stateful
@Path("/shoppingcart")
public class ShoppingCarEndpoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1329173077542115992L;
	
	@Inject
	ShoppingCart shoppingCar;
	
	@Inject
	ProdutoDao produtoDao;
	
	@Inject
	ClienteDao clienteDao;
	
	@Inject
	VendaDao vendaDao;
	
	@GET
	@Path("/adicionar/{id:[0-9][0-9]*}/{qtd:[0-9][0-9]*}")
	public synchronized Response addProduto(@PathParam("id") Long id,@PathParam("qtd") int qtd) {
		
		Produto pdt = produtoDao.findById(id);
		
		shoppingCar.adicionarItem(pdt);
		shoppingCar.atualizar(pdt, String.valueOf(qtd));
		

		System.out.println("Produtos foram adiconado");
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/limpar")
	public synchronized Response limpar(){
		shoppingCar.limpar();
		return Response.ok().build();
	}

	
	@GET
	@Path("/total")
	public Response getTotal() {
		
		return Response.ok(String.valueOf(shoppingCar.getSubtotal())).build();
	}
	
	
	@GET
	@Path("/finalizar/{idCliente:[0-9][0-9]*}")
	public synchronized Response finalizar(@PathParam("idCliente") long idCliente){
		Venda venda = new Venda();
		Set<ItemVenda> itensVenda = new HashSet<>();
		
		List<ShoppingCartItem> itens = shoppingCar.getItems();
		for (ShoppingCartItem shoppingCartItem : itens) {
			Produto pdt = shoppingCartItem.getProduto();
			ItemVenda it = new ItemVenda();
			it.setNome(pdt.getNome());
			it.setCategoria(pdt.getDescricao());
			it.setQuantidade(shoppingCartItem.getQuantidade());
			it.setCategoria(pdt.getCategoria().getDescricao());
			itensVenda.add(it);
		}
		
		venda.setCliente(clienteDao.findById(idCliente));
		venda.setItens(itensVenda);
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    venda.setData(sqlDate);
		venda.setTotal((float) shoppingCar.getTotal());
		vendaDao.create(venda);
		return Response.ok().build();
	}
	
	@GET
	@Path("/size")
	public Response getSize() {
		
		return Response.ok(String.valueOf(shoppingCar.getQtdItems())).build();
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
