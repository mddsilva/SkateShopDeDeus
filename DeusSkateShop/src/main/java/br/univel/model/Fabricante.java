package br.univel.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Fabricante implements Serializable,Entidade
{

   /**
    * 
    */
   private static final long serialVersionUID = 1822972103851783751L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String razaoSocial;

   @Column(nullable = false)
   private String nomeFantasia;

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
      if (!(obj instanceof Fabricante))
      {
         return false;
      }
      Fabricante other = (Fabricante) obj;
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

   public String getRazaoSocial()
   {
      return razaoSocial;
   }

   public void setRazaoSocial(String razaoSocial)
   {
      this.razaoSocial = razaoSocial;
   }

   public String getNomeFantasia()
   {
      return nomeFantasia;
   }

   public void setNomeFantasia(String nomeFantasia)
   {
      this.nomeFantasia = nomeFantasia;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (razaoSocial != null && !razaoSocial.trim().isEmpty())
         result += "razaoSocial: " + razaoSocial;
      if (nomeFantasia != null && !nomeFantasia.trim().isEmpty())
         result += ", nomeFantasia: " + nomeFantasia;
      return result;
   }
}