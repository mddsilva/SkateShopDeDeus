package br.univel.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Venda implements Serializable, Entidade
{

   /**
    * 
    */
   private static final long serialVersionUID = 3749024473203083580L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;

   @Version
   @Column(name = "version")
   private int version;

   @Column
   private Date data;

   @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<ItemVenda> itens = new HashSet<ItemVenda>();

   @ManyToOne
   private Cliente cliente;
   
   @Column
   private float total;

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

   public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
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

   public Set<ItemVenda> getItens()
   {
      return this.itens;
   }

   public void setItens(final Set<ItemVenda> itens)
   {
      this.itens = itens;
   }

   public float getTotal()
   {
      return total;
   }

   public void setTotal(float total)
   {
      this.total = total;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      result += "total: " + total;
      return result;
   }
}