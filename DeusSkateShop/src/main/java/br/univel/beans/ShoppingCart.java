
package br.univel.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.univel.model.Produto;

@ApplicationScoped
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 6892333631063273823L;
	List<ShoppingCartItem> items;
	int qtdItems;
	double total;

	public ShoppingCart() {
		items = new ArrayList<ShoppingCartItem>();
		qtdItems = 0;
		total = 0;
	}


	public synchronized void adicionarItem(Produto produto) {

		boolean item = true;

		for (ShoppingCartItem it : items) {

			if (it.getProduto().getId() == produto.getId()) {

				item = false;
				it.incrementarQuantidade();
			}
		}

		if (item) {
			ShoppingCartItem scItem = new ShoppingCartItem(produto);
			items.add(scItem);
		}
	}


	public synchronized void atualizar(Produto product, String quantity) {

		int qtd = -1;

		qtd = Short.parseShort(quantity);

		if (qtd >= 0) {

			ShoppingCartItem item = null;

			for (ShoppingCartItem it : items) {

				if (it.getProduto().getId() == product.getId()) {

					if (qtd != 0) {
						it.setQuantidade(qtd);
					} else {
						item = it;
						break;
					}
				}
			}

			if (item != null) {
				items.remove(item);
			}
		}
	}

	public synchronized List<ShoppingCartItem> getItems() {

		return items;
	}

	public synchronized int getQtdItems() {

		qtdItems = 0;

		for (ShoppingCartItem scItem : items) {

			qtdItems += scItem.getQuantidade();
		}

		return qtdItems;
	}

	public synchronized double getSubtotal() {

		double amount = 0;

		for (ShoppingCartItem scItem : items) {

			Produto product = (Produto) scItem.getProduto();
			amount += (scItem.getQuantidade() * product.getPreco());
		}

		return amount;
	}

	public synchronized void calcularTotal(String surcharge) {

		double aux = this.getSubtotal();
		total = aux+=Double.parseDouble(surcharge) ;

	}

	public synchronized double getTotal() {

		return total;
	}

	public synchronized void limpar() {
		items.clear();
		qtdItems = 0;
		total = 0;
	}

}