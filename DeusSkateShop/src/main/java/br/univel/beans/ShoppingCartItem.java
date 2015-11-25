/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package br.univel.beans;

import java.io.Serializable;

import br.univel.model.Produto;

/**
 *
 * @author tgiunipero
 */
public class ShoppingCartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5960152303333268803L;
	Produto produto;
	int quantidade;

	public ShoppingCartItem(Produto produto) {
		this.produto = produto;
		quantidade = 1;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void incrementarQuantidade() {
		quantidade++;
	}

	public void decrementarQuantidade() {
		quantidade--;
	}

	public double getTotal() {
	
		return this.getQuantidade() * produto.getPreco();
	}
}