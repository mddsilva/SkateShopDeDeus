package br.univel.basetests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.Test;

import br.univel.model.Categoria;
import br.univel.model.Cliente;
import br.univel.model.Fabricante;
import br.univel.model.Cliente.Genero;
import br.univel.model.Produto;
import br.univel.model.Usuario;

public class CargaBasica {

	private final static String BASE_URL = "http://localhost:8080/DeusSkateShop/rest/";

	@Test
	public void cadastrarDadosBasicos() {

		for (int i = 1; i < 10; i++) {
			Usuario user = new Usuario();
			user.setLogin(i + "@disney.com");
			user.setSenha("1234");
			Long id = salvar(user);
			user.setId(id);

			Cliente c = new Cliente();
			c.setUsuario(user);
			c.setCep("32323-23");
			c.setCpf("323232.323.23");
			c.setDataNasc(new Date(3232323));
			c.setGenero(Genero.F);
			c.setNome("Ge");
			
			salvar(c);

		}

		for (int c = 1; c <= 10; c++) {

			Categoria categoria = new Categoria();
			categoria.setDescricao("Categoria " + c);
			Long id = salvar(categoria);
			categoria.setId(id);

			Fabricante fabricante = new Fabricante();
			fabricante.setNomeFantasia("Dagostini");
			fabricante.setRazaoSocial("32323.232");
			Long idf = salvar(fabricante);
			fabricante.setId(idf);

			for (int p = 1; p <= 5; p++) {
				Produto produto = new Produto();
				produto.setNome("Produto " + p + " cat" + c);
				produto.setCategoria(categoria);
				produto.setFabricante(fabricante);
				produto.setDescricao("Skate");
				produto.setQuantidade(4);
				produto.setPreco(1f);

				addProductoIntoCarrinho(salvar(produto), "2");
			}
		}

	}

	private void addProductoIntoCarrinho(Long idProduto, String qtd) {
		System.out.println("Adicionando produto no carrinho...");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		
		Client client = clientBuilder.build();

		// Entity<Usuario> usuario = Entity.json(p);
		String uri = BASE_URL + "shoppingcart/adicionar/"+idProduto+"/"+qtd;
		System.out.println(uri);

		WebTarget target = client.target(uri);
		
		Response response = target.request().get();

		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		System.out.println("Resposta " + response.getStatus());

		client.close();
		client.close();

	}

	private Long salvar(Usuario user) {

		System.out.println("Salvando usuario");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();

		Entity<Usuario> usuario = Entity.json(user);
		String uri = BASE_URL + "usuarios";
		System.out.println(uri);

		WebTarget target = client.target(uri);

		Response respostaGravacao = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(usuario);

		System.out.println("Resposta " + respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});

		assertEquals(Status.CREATED.getStatusCode(),
				respostaGravacao.getStatus());

		client.close();
		client.close();
		String urlCatCriada = (String) respostaGravacao.getHeaders()
				.get("Location").get(0);

		return Long.parseLong(urlCatCriada.substring(urlCatCriada
				.lastIndexOf('/') + 1));
	}

	private void salvar(Cliente cl) {

		System.out.println("Salvando clientes");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();

		Entity<Cliente> cli = Entity.json(cl);
		String uri = BASE_URL + "clientes";
		System.out.println(uri);

		WebTarget target = client.target(uri);

		Response respostaGravacao = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(cli);

		System.out.println("Resposta " + respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});

		assertEquals(Status.CREATED.getStatusCode(),
				respostaGravacao.getStatus());

	}

	private Long salvar(Categoria categoria) {
		System.out.println("Salvando categoria");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();

		Entity<Categoria> categoriaJson = Entity.json(categoria);
		String uri = BASE_URL + "categoria";
		System.out.println(uri);

		WebTarget target = client.target(uri);

		Response respostaGravacao = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(categoriaJson);

		System.out.println("Resposta " + respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});

		assertEquals(Status.CREATED.getStatusCode(),
				respostaGravacao.getStatus());

		client.close();

		String urlCatCriada = (String) respostaGravacao.getHeaders()
				.get("Location").get(0);

		return Long.parseLong(urlCatCriada.substring(urlCatCriada
				.lastIndexOf('/') + 1));
	}

	private Long salvar(Fabricante fabricante) {
		System.out.println("Salvando fabricante");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();

		Entity<Fabricante> categoriaJson = Entity.json(fabricante);
		String uri = BASE_URL + "fabricantes";
		System.out.println(uri);

		WebTarget target = client.target(uri);

		Response respostaGravacao = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(categoriaJson);

		System.out.println("Resposta " + respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});

		assertEquals(Status.CREATED.getStatusCode(),
				respostaGravacao.getStatus());

		client.close();

		String urlCatCriada = (String) respostaGravacao.getHeaders()
				.get("Location").get(0);

		return Long.parseLong(urlCatCriada.substring(urlCatCriada
				.lastIndexOf('/') + 1));
	}

	private Long salvar(Produto produto) {
		System.out.println("Salvando produto");

		ClientBuilder clientBuilder = ClientBuilder.newBuilder().register(
				JacksonJaxbJsonProvider.class);
		Client client = clientBuilder.build();

		Entity<Produto> categoriaJson = Entity.json(produto);
		String uri = BASE_URL + "produtos";
		System.out.println(uri);

		WebTarget target = client.target(uri);

		Response respostaGravacao = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).post(categoriaJson);

		System.out.println("Resposta " + respostaGravacao.getStatus());

		assertEquals(Status.CREATED.getStatusCode(),
				respostaGravacao.getStatus());

		respostaGravacao.getHeaders().entrySet().forEach(e -> {
			System.out.println("\t" + e.getKey() + "\t" + e.getValue());
		});

		client.close();
		String urlCatCriada = (String) respostaGravacao.getHeaders()
				.get("Location").get(0);

		return Long.parseLong(urlCatCriada.substring(urlCatCriada
				.lastIndexOf('/') + 1));
	}
}