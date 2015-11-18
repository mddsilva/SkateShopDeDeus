package br.univel.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.univel.model.Produto;

@SessionScoped
@Named
public class Carrinho implements Serializable {

	private static final long serialVersionUID = -1114104252732317979L;

	private List<Produto> produtos;

	public void addProduto(Produto produto) {
		
		if(produtos==null)
			produtos = new LinkedList<Produto>();
		produtos.add(produto);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	

	

	
	
}
