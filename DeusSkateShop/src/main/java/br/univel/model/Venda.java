package br.univel.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import java.lang.Override;

import br.univel.model.Cliente;

import java.sql.Date;
import java.util.List;

import br.univel.model.FormaPagamento;

@Entity
public class Venda implements Serializable
{

   /**
    * 
    */
   private static final long serialVersionUID = -9010578551823009843L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private Cliente cliente;

   @Column
   private Date data;

   @Column(nullable = false)
   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private FormaPagamento formaPagamento;

   @Column
   private Double valorTotal;

   @Column
   private Double valorPago;
   
   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Produto> produtos;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Venda))
      {
         return false;
      }
      Venda other = (Venda) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public Cliente getCliente()
   {
      return cliente;
   }

   public void setCliente(Cliente cliente)
   {
      this.cliente = cliente;
   }

   public Date getData()
   {
      return data;
   }

   public void setData(Date data)
   {
      this.data = data;
   }

   public FormaPagamento getFormaPagamento()
   {
      return formaPagamento;
   }

   public void setFormaPagamento(FormaPagamento formaPagamento)
   {
      this.formaPagamento = formaPagamento;
   }

   public Double getValorTotal()
   {
      return valorTotal;
   }

   public void setValorTotal(Double valorTotal)
   {
      this.valorTotal = valorTotal;
   }

   public Double getValorPago()
   {
      return valorPago;
   }

   public void setValorPago(Double valorPago)
   {
      this.valorPago = valorPago;
   }

   
   
   public List<Produto> getProdutos() {
	return produtos;
}

public void setProdutos(List<Produto> produtos) {
	this.produtos = produtos;
}

@Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (valorTotal != null)
         result += "valorTotal: " + valorTotal;
      if (valorPago != null)
         result += ", valorPago: " + valorPago;
      return result;
   }
}