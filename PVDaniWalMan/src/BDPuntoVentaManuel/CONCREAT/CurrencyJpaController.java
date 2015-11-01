/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ICurrency;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Currency;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Request;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Po;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class CurrencyJpaController implements Serializable, ICurrency {

    public CurrencyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CurrencyJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Currency currency) {
        if (currency.getRequestCollection() == null) {
            currency.setRequestCollection(new ArrayList<Request>());
        }
        if (currency.getPoCollection() == null) {
            currency.setPoCollection(new ArrayList<Po>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Request> attachedRequestCollection = new ArrayList<Request>();
            for (Request requestCollectionRequestToAttach : currency.getRequestCollection()) {
                requestCollectionRequestToAttach = em.getReference(requestCollectionRequestToAttach.getClass(), requestCollectionRequestToAttach.getId());
                attachedRequestCollection.add(requestCollectionRequestToAttach);
            }
            currency.setRequestCollection(attachedRequestCollection);
            Collection<Po> attachedPoCollection = new ArrayList<Po>();
            for (Po poCollectionPoToAttach : currency.getPoCollection()) {
                poCollectionPoToAttach = em.getReference(poCollectionPoToAttach.getClass(), poCollectionPoToAttach.getId());
                attachedPoCollection.add(poCollectionPoToAttach);
            }
            currency.setPoCollection(attachedPoCollection);
            em.persist(currency);
            for (Request requestCollectionRequest : currency.getRequestCollection()) {
                Currency oldIdCurrencyOfRequestCollectionRequest = requestCollectionRequest.getIdCurrency();
                requestCollectionRequest.setIdCurrency(currency);
                requestCollectionRequest = em.merge(requestCollectionRequest);
                if (oldIdCurrencyOfRequestCollectionRequest != null) {
                    oldIdCurrencyOfRequestCollectionRequest.getRequestCollection().remove(requestCollectionRequest);
                    oldIdCurrencyOfRequestCollectionRequest = em.merge(oldIdCurrencyOfRequestCollectionRequest);
                }
            }
            for (Po poCollectionPo : currency.getPoCollection()) {
                Currency oldIdCurrencyOfPoCollectionPo = poCollectionPo.getIdCurrency();
                poCollectionPo.setIdCurrency(currency);
                poCollectionPo = em.merge(poCollectionPo);
                if (oldIdCurrencyOfPoCollectionPo != null) {
                    oldIdCurrencyOfPoCollectionPo.getPoCollection().remove(poCollectionPo);
                    oldIdCurrencyOfPoCollectionPo = em.merge(oldIdCurrencyOfPoCollectionPo);
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
    public void edit(Currency currency) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Currency persistentCurrency = em.find(Currency.class, currency.getId());
            Collection<Request> requestCollectionOld = persistentCurrency.getRequestCollection();
            Collection<Request> requestCollectionNew = currency.getRequestCollection();
            Collection<Po> poCollectionOld = persistentCurrency.getPoCollection();
            Collection<Po> poCollectionNew = currency.getPoCollection();
            List<String> illegalOrphanMessages = null;
            for (Request requestCollectionOldRequest : requestCollectionOld) {
                if (!requestCollectionNew.contains(requestCollectionOldRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Request " + requestCollectionOldRequest + " since its idCurrency field is not nullable.");
                }
            }
            for (Po poCollectionOldPo : poCollectionOld) {
                if (!poCollectionNew.contains(poCollectionOldPo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Po " + poCollectionOldPo + " since its idCurrency field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Request> attachedRequestCollectionNew = new ArrayList<Request>();
            for (Request requestCollectionNewRequestToAttach : requestCollectionNew) {
                requestCollectionNewRequestToAttach = em.getReference(requestCollectionNewRequestToAttach.getClass(), requestCollectionNewRequestToAttach.getId());
                attachedRequestCollectionNew.add(requestCollectionNewRequestToAttach);
            }
            requestCollectionNew = attachedRequestCollectionNew;
            currency.setRequestCollection(requestCollectionNew);
            Collection<Po> attachedPoCollectionNew = new ArrayList<Po>();
            for (Po poCollectionNewPoToAttach : poCollectionNew) {
                poCollectionNewPoToAttach = em.getReference(poCollectionNewPoToAttach.getClass(), poCollectionNewPoToAttach.getId());
                attachedPoCollectionNew.add(poCollectionNewPoToAttach);
            }
            poCollectionNew = attachedPoCollectionNew;
            currency.setPoCollection(poCollectionNew);
            currency = em.merge(currency);
            for (Request requestCollectionNewRequest : requestCollectionNew) {
                if (!requestCollectionOld.contains(requestCollectionNewRequest)) {
                    Currency oldIdCurrencyOfRequestCollectionNewRequest = requestCollectionNewRequest.getIdCurrency();
                    requestCollectionNewRequest.setIdCurrency(currency);
                    requestCollectionNewRequest = em.merge(requestCollectionNewRequest);
                    if (oldIdCurrencyOfRequestCollectionNewRequest != null && !oldIdCurrencyOfRequestCollectionNewRequest.equals(currency)) {
                        oldIdCurrencyOfRequestCollectionNewRequest.getRequestCollection().remove(requestCollectionNewRequest);
                        oldIdCurrencyOfRequestCollectionNewRequest = em.merge(oldIdCurrencyOfRequestCollectionNewRequest);
                    }
                }
            }
            for (Po poCollectionNewPo : poCollectionNew) {
                if (!poCollectionOld.contains(poCollectionNewPo)) {
                    Currency oldIdCurrencyOfPoCollectionNewPo = poCollectionNewPo.getIdCurrency();
                    poCollectionNewPo.setIdCurrency(currency);
                    poCollectionNewPo = em.merge(poCollectionNewPo);
                    if (oldIdCurrencyOfPoCollectionNewPo != null && !oldIdCurrencyOfPoCollectionNewPo.equals(currency)) {
                        oldIdCurrencyOfPoCollectionNewPo.getPoCollection().remove(poCollectionNewPo);
                        oldIdCurrencyOfPoCollectionNewPo = em.merge(oldIdCurrencyOfPoCollectionNewPo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = currency.getId();
                if (findCurrency(id) == null) {
                    throw new NonexistentEntityException("The currency with id " + id + " no longer exists.");
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
            Currency currency;
            try {
                currency = em.getReference(Currency.class, id);
                currency.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The currency with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Request> requestCollectionOrphanCheck = currency.getRequestCollection();
            for (Request requestCollectionOrphanCheckRequest : requestCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Currency (" + currency + ") cannot be destroyed since the Request " + requestCollectionOrphanCheckRequest + " in its requestCollection field has a non-nullable idCurrency field.");
            }
            Collection<Po> poCollectionOrphanCheck = currency.getPoCollection();
            for (Po poCollectionOrphanCheckPo : poCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Currency (" + currency + ") cannot be destroyed since the Po " + poCollectionOrphanCheckPo + " in its poCollection field has a non-nullable idCurrency field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(currency);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Currency> findCurrencyEntities() {
        return findCurrencyEntities(true, -1, -1);
    }

    @Override
    public List<Currency> findCurrencyEntities(int maxResults, int firstResult) {
        return findCurrencyEntities(false, maxResults, firstResult);
    }

    private List<Currency> findCurrencyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Currency.class));
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
    public Currency findCurrency(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Currency.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCurrencyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Currency> rt = cq.from(Currency.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
