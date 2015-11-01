/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ISales;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Sales;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Salesdetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class SalesJpaController implements Serializable, ISales {

    public SalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public SalesJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Sales sales) {
        if (sales.getSalesdetailCollection() == null) {
            sales.setSalesdetailCollection(new ArrayList<Salesdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Salesdetail> attachedSalesdetailCollection = new ArrayList<Salesdetail>();
            for (Salesdetail salesdetailCollectionSalesdetailToAttach : sales.getSalesdetailCollection()) {
                salesdetailCollectionSalesdetailToAttach = em.getReference(salesdetailCollectionSalesdetailToAttach.getClass(), salesdetailCollectionSalesdetailToAttach.getId());
                attachedSalesdetailCollection.add(salesdetailCollectionSalesdetailToAttach);
            }
            sales.setSalesdetailCollection(attachedSalesdetailCollection);
            em.persist(sales);
            for (Salesdetail salesdetailCollectionSalesdetail : sales.getSalesdetailCollection()) {
                Sales oldIdSalesOfSalesdetailCollectionSalesdetail = salesdetailCollectionSalesdetail.getIdSales();
                salesdetailCollectionSalesdetail.setIdSales(sales);
                salesdetailCollectionSalesdetail = em.merge(salesdetailCollectionSalesdetail);
                if (oldIdSalesOfSalesdetailCollectionSalesdetail != null) {
                    oldIdSalesOfSalesdetailCollectionSalesdetail.getSalesdetailCollection().remove(salesdetailCollectionSalesdetail);
                    oldIdSalesOfSalesdetailCollectionSalesdetail = em.merge(oldIdSalesOfSalesdetailCollectionSalesdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Sales sales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sales persistentSales = em.find(Sales.class, sales.getId());
            Collection<Salesdetail> salesdetailCollectionOld = persistentSales.getSalesdetailCollection();
            Collection<Salesdetail> salesdetailCollectionNew = sales.getSalesdetailCollection();
            List<String> illegalOrphanMessages = null;
            for (Salesdetail salesdetailCollectionOldSalesdetail : salesdetailCollectionOld) {
                if (!salesdetailCollectionNew.contains(salesdetailCollectionOldSalesdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Salesdetail " + salesdetailCollectionOldSalesdetail + " since its idSales field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Salesdetail> attachedSalesdetailCollectionNew = new ArrayList<Salesdetail>();
            for (Salesdetail salesdetailCollectionNewSalesdetailToAttach : salesdetailCollectionNew) {
                salesdetailCollectionNewSalesdetailToAttach = em.getReference(salesdetailCollectionNewSalesdetailToAttach.getClass(), salesdetailCollectionNewSalesdetailToAttach.getId());
                attachedSalesdetailCollectionNew.add(salesdetailCollectionNewSalesdetailToAttach);
            }
            salesdetailCollectionNew = attachedSalesdetailCollectionNew;
            sales.setSalesdetailCollection(salesdetailCollectionNew);
            sales = em.merge(sales);
            for (Salesdetail salesdetailCollectionNewSalesdetail : salesdetailCollectionNew) {
                if (!salesdetailCollectionOld.contains(salesdetailCollectionNewSalesdetail)) {
                    Sales oldIdSalesOfSalesdetailCollectionNewSalesdetail = salesdetailCollectionNewSalesdetail.getIdSales();
                    salesdetailCollectionNewSalesdetail.setIdSales(sales);
                    salesdetailCollectionNewSalesdetail = em.merge(salesdetailCollectionNewSalesdetail);
                    if (oldIdSalesOfSalesdetailCollectionNewSalesdetail != null && !oldIdSalesOfSalesdetailCollectionNewSalesdetail.equals(sales)) {
                        oldIdSalesOfSalesdetailCollectionNewSalesdetail.getSalesdetailCollection().remove(salesdetailCollectionNewSalesdetail);
                        oldIdSalesOfSalesdetailCollectionNewSalesdetail = em.merge(oldIdSalesOfSalesdetailCollectionNewSalesdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sales.getId();
                if (findSales(id) == null) {
                    throw new NonexistentEntityException("The sales with id " + id + " no longer exists.");
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
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sales sales;
            try {
                sales = em.getReference(Sales.class, id);
                sales.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sales with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Salesdetail> salesdetailCollectionOrphanCheck = sales.getSalesdetailCollection();
            for (Salesdetail salesdetailCollectionOrphanCheckSalesdetail : salesdetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sales (" + sales + ") cannot be destroyed since the Salesdetail " + salesdetailCollectionOrphanCheckSalesdetail + " in its salesdetailCollection field has a non-nullable idSales field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Sales> findSalesEntities() {
        return findSalesEntities(true, -1, -1);
    }

    @Override
    public List<Sales> findSalesEntities(int maxResults, int firstResult) {
        return findSalesEntities(false, maxResults, firstResult);
    }

    private List<Sales> findSalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sales.class));
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
    public Sales findSales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sales.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getSalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sales> rt = cq.from(Sales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
