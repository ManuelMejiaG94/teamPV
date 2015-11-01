/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ISalesDetail;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Sales;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Salesdetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class SalesdetailJpaController implements Serializable, ISalesDetail {

    public SalesdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public SalesdetailJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Salesdetail salesdetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sales idSales = salesdetail.getIdSales();
            if (idSales != null) {
                idSales = em.getReference(idSales.getClass(), idSales.getId());
                salesdetail.setIdSales(idSales);
            }
            Product idProducto = salesdetail.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                salesdetail.setIdProducto(idProducto);
            }
            em.persist(salesdetail);
            if (idSales != null) {
                idSales.getSalesdetailCollection().add(salesdetail);
                idSales = em.merge(idSales);
            }
            if (idProducto != null) {
                idProducto.getSalesdetailCollection().add(salesdetail);
                idProducto = em.merge(idProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Salesdetail salesdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salesdetail persistentSalesdetail = em.find(Salesdetail.class, salesdetail.getId());
            Sales idSalesOld = persistentSalesdetail.getIdSales();
            Sales idSalesNew = salesdetail.getIdSales();
            Product idProductoOld = persistentSalesdetail.getIdProducto();
            Product idProductoNew = salesdetail.getIdProducto();
            if (idSalesNew != null) {
                idSalesNew = em.getReference(idSalesNew.getClass(), idSalesNew.getId());
                salesdetail.setIdSales(idSalesNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                salesdetail.setIdProducto(idProductoNew);
            }
            salesdetail = em.merge(salesdetail);
            if (idSalesOld != null && !idSalesOld.equals(idSalesNew)) {
                idSalesOld.getSalesdetailCollection().remove(salesdetail);
                idSalesOld = em.merge(idSalesOld);
            }
            if (idSalesNew != null && !idSalesNew.equals(idSalesOld)) {
                idSalesNew.getSalesdetailCollection().add(salesdetail);
                idSalesNew = em.merge(idSalesNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getSalesdetailCollection().remove(salesdetail);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getSalesdetailCollection().add(salesdetail);
                idProductoNew = em.merge(idProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salesdetail.getId();
                if (findSalesdetail(id) == null) {
                    throw new NonexistentEntityException("The salesdetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salesdetail salesdetail;
            try {
                salesdetail = em.getReference(Salesdetail.class, id);
                salesdetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salesdetail with id " + id + " no longer exists.", enfe);
            }
            Sales idSales = salesdetail.getIdSales();
            if (idSales != null) {
                idSales.getSalesdetailCollection().remove(salesdetail);
                idSales = em.merge(idSales);
            }
            Product idProducto = salesdetail.getIdProducto();
            if (idProducto != null) {
                idProducto.getSalesdetailCollection().remove(salesdetail);
                idProducto = em.merge(idProducto);
            }
            em.remove(salesdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Salesdetail> findSalesdetailEntities() {
        return findSalesdetailEntities(true, -1, -1);
    }

    @Override
    public List<Salesdetail> findSalesdetailEntities(int maxResults, int firstResult) {
        return findSalesdetailEntities(false, maxResults, firstResult);
    }

    private List<Salesdetail> findSalesdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salesdetail.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Salesdetail findSalesdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salesdetail.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getSalesdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salesdetail> rt = cq.from(Salesdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
