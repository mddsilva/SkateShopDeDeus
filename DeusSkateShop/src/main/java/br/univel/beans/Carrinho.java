package br.univel.beans;

import java.util.List;

import javax.enterprise.context.SessionScoped;

import br.univel.model.Produto;
import java.io.Serializable;

@SessionScoped
public class Carrinho implements Serializable {

	private static final long serialVersionUID = -1114104252732317979L;

	private List<Produto> produtos;

	public void addProduto(Produto produto) {
		produtos.add(produto);
	}

}