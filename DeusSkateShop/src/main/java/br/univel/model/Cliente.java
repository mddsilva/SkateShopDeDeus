package br.univel.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cliente implements Serializable,Entidade
{

   /**
    * 
    */
   private static final long serialVersionUID = 795403685562871714L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String Nome;

   @Column(nullable = false)
   private String cep;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private Usuario usuario;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Venda> vendas = new HashSet<Venda>();

   @Column(length = 16, nullable = false)
   private String cpf;

   @Column
   private Date dataNasc;

   @Column
   private Genero genero;

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
      if (!(obj instanceof Cliente))
      {
         return false;
      }
      Cliente other = (Cliente) obj;
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
      return Nome;
   }

   public void setNome(String Nome)
   {
      this.Nome = Nome;
   }

   public String getCep()
   {
      return cep;
   }

   public void setCep(String cep)
   {
      this.cep = cep;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public Date getDataNasc()
   {
      return dataNasc;
   }

   public void setDataNasc(Date dataNasc)
   {
      this.dataNasc = dataNasc;
   }

   public Genero getGenero()
   {
      return genero;
   }

   public void setGenero(Genero genero)
   {
      this.genero = genero;
   }

   public Set<Venda> getVendas()
   {
      return vendas;
   }

   public void setVendas(Set<Venda> vendas)
   {
      this.vendas = vendas;
   }

   public enum Genero
   {

      M("Masculino"), F("Feminino");

      public String descricao;

      Genero(String descricao)
      {
         this.descricao = descricao;
      }
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (Nome != null && !Nome.trim().isEmpty())
         result += "Nome: " + Nome;
      if (cep != null && !cep.trim().isEmpty())
         result += ", cep: " + cep;
      if (cpf != null && !cpf.trim().isEmpty())
         result += ", cpf: " + cpf;
      return result;
   }
}