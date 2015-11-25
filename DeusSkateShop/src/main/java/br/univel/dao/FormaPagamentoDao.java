package br.univel.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.univel.model.FormaPagamento;

/**
 *  DAO for FormaPagamento
 */
@Stateless
public class FormaPagamentoDao
{
   @PersistenceContext(unitName = "DeusSkateShop-persistence-unit")
   private EntityManager em;

   public void create(FormaPagamento entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      FormaPagamento entity = em.find(FormaPagamento.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public FormaPagamento findById(Long id)
   {
      return em.find(FormaPagamento.class, id);
   }

   public FormaPagamento update(FormaPagamento entity)
   {
      return em.merge(entity);
   }

   public List<FormaPagamento> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<FormaPagamento> findAllQuery = em.createQuery("SELECT DISTINCT f FROM FormaPagamento f ORDER BY f.id", FormaPagamento.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      return findAllQuery.getResultList();
   }
}
