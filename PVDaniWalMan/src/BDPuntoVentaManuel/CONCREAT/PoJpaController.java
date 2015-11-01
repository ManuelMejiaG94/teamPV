/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPO;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Currency;
import BDPuntoVentaManuel.MODEL.Po;
import BDPuntoVentaManuel.MODEL.Podetail;
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
public class PoJpaController implements Serializable, IPO {

    public PoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Po po) {
        if (po.getPodetailCollection() == null) {
            po.setPodetailCollection(new ArrayList<Podetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Currency idCurrency = po.getIdCurrency();
            if (idCurrency != null) {
                idCurrency = em.getReference(idCurrency.getClass(), idCurrency.getId());
                po.setIdCurrency(idCurrency);
            }
            Collection<Podetail> attachedPodetailCollection = new ArrayList<Podetail>();
            for (Podetail podetailCollectionPodetailToAttach : po.getPodetailCollection()) {
                podetailCollectionPodetailToAttach = em.getReference(podetailCollectionPodetailToAttach.getClass(), podetailCollectionPodetailToAttach.getId());
                attachedPodetailCollection.add(podetailCollectionPodetailToAttach);
            }
            po.setPodetailCollection(attachedPodetailCollection);
            em.persist(po);
            if (idCurrency != null) {
                idCurrency.getPoCollection().add(po);
                idCurrency = em.merge(idCurrency);
            }
            for (Podetail podetailCollectionPodetail : po.getPodetailCollection()) {
                Po oldIdPoOfPodetailCollectionPodetail = podetailCollectionPodetail.getIdPo();
                podetailCollectionPodetail.setIdPo(po);
                podetailCollectionPodetail = em.merge(podetailCollectionPodetail);
                if (oldIdPoOfPodetailCollectionPodetail != null) {
                    oldIdPoOfPodetailCollectionPodetail.getPodetailCollection().remove(podetailCollectionPodetail);
                    oldIdPoOfPodetailCollectionPodetail = em.merge(oldIdPoOfPodetailCollectionPodetail);
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
    public void edit(Po po) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Po persistentPo = em.find(Po.class, po.getId());
            Currency idCurrencyOld = persistentPo.getIdCurrency();
            Currency idCurrencyNew = po.getIdCurrency();
            Collection<Podetail> podetailCollectionOld = persistentPo.getPodetailCollection();
            Collection<Podetail> podetailCollectionNew = po.getPodetailCollection();
            List<String> illegalOrphanMessages = null;
            for (Podetail podetailCollectionOldPodetail : podetailCollectionOld) {
                if (!podetailCollectionNew.contains(podetailCollectionOldPodetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Podetail " + podetailCollectionOldPodetail + " since its idPo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCurrencyNew != null) {
                idCurrencyNew = em.getReference(idCurrencyNew.getClass(), idCurrencyNew.getId());
                po.setIdCurrency(idCurrencyNew);
            }
            Collection<Podetail> attachedPodetailCollectionNew = new ArrayList<Podetail>();
            for (Podetail podetailCollectionNewPodetailToAttach : podetailCollectionNew) {
                podetailCollectionNewPodetailToAttach = em.getReference(podetailCollectionNewPodetailToAttach.getClass(), podetailCollectionNewPodetailToAttach.getId());
                attachedPodetailCollectionNew.add(podetailCollectionNewPodetailToAttach);
            }
            podetailCollectionNew = attachedPodetailCollectionNew;
            po.setPodetailCollection(podetailCollectionNew);
            po = em.merge(po);
            if (idCurrencyOld != null && !idCurrencyOld.equals(idCurrencyNew)) {
                idCurrencyOld.getPoCollection().remove(po);
                idCurrencyOld = em.merge(idCurrencyOld);
            }
            if (idCurrencyNew != null && !idCurrencyNew.equals(idCurrencyOld)) {
                idCurrencyNew.getPoCollection().add(po);
                idCurrencyNew = em.merge(idCurrencyNew);
            }
            for (Podetail podetailCollectionNewPodetail : podetailCollectionNew) {
                if (!podetailCollectionOld.contains(podetailCollectionNewPodetail)) {
                    Po oldIdPoOfPodetailCollectionNewPodetail = podetailCollectionNewPodetail.getIdPo();
                    podetailCollectionNewPodetail.setIdPo(po);
                    podetailCollectionNewPodetail = em.merge(podetailCollectionNewPodetail);
                    if (oldIdPoOfPodetailCollectionNewPodetail != null && !oldIdPoOfPodetailCollectionNewPodetail.equals(po)) {
                        oldIdPoOfPodetailCollectionNewPodetail.getPodetailCollection().remove(podetailCollectionNewPodetail);
                        oldIdPoOfPodetailCollectionNewPodetail = em.merge(oldIdPoOfPodetailCollectionNewPodetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = po.getId();
                if (findPo(id) == null) {
                    throw new NonexistentEntityException("The po with id " + id + " no longer exists.");
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
            Po po;
            try {
                po = em.getReference(Po.class, id);
                po.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The po with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Podetail> podetailCollectionOrphanCheck = po.getPodetailCollection();
            for (Podetail podetailCollectionOrphanCheckPodetail : podetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Po (" + po + ") cannot be destroyed since the Podetail " + podetailCollectionOrphanCheckPodetail + " in its podetailCollection field has a non-nullable idPo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Currency idCurrency = po.getIdCurrency();
            if (idCurrency != null) {
                idCurrency.getPoCollection().remove(po);
                idCurrency = em.merge(idCurrency);
            }
            em.remove(po);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Po> findPoEntities() {
        return findPoEntities(true, -1, -1);
    }

    @Override
    public List<Po> findPoEntities(int maxResults, int firstResult) {
        return findPoEntities(false, maxResults, firstResult);
    }

    private List<Po> findPoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Po.class));
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
    public Po findPo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Po.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Po> rt = cq.from(Po.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
