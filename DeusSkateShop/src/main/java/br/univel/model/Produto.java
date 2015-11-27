package br.univel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Produto implements Serializable,Entidade
{

   /**
    * 
    */
   private static final long serialVersionUID = 5720122895964402524L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private String descricao;

   @Column
   private Float  preco;

   @ManyToOne
   private Categoria categoria;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Fabricante fabricante;

 

   @Column(nullable = false)
   private Integer quantidade;

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
      if (!(obj instanceof Produto))
      {
         return false;
      }
      Produto other = (Produto) obj;
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

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public Float  getPreco()
   {
      return preco;
   }

   public void setPreco(Float  preco)
   {
      this.preco = preco;
   }

   public Categoria getCategoria()
   {
      return categoria;
   }

   public void setCategoria(Categoria categoria)
   {
      this.categoria = categoria;
   }

   public Fabricante getFabricante()
   {
      return fabricante;
   }

   public void setFabricante(Fabricante fabricante)
   {
      this.fabricante = fabricante;
   }

   public Integer getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(Integer quantidade)
   {
      this.quantidade = quantidade;
   }


   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nome != null && !nome.trim().isEmpty())
         result += "nome: " + nome;
      if (descricao != null && !descricao.trim().isEmpty())
         result += ", descricao: " + descricao;
      if (quantidade != null)
         result += ", quantidade: " + quantidade;
      return result;
   }
}