/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IRequest;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Currency;
import BDPuntoVentaManuel.MODEL.Request;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.MODEL.Requestdetail;
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
public class RequestJpaController implements Serializable, IRequest {

    public RequestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public RequestJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Request request) {
        if (request.getRequestdetailCollection() == null) {
            request.setRequestdetailCollection(new ArrayList<Requestdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Currency idCurrency = request.getIdCurrency();
            if (idCurrency != null) {
                idCurrency = em.getReference(idCurrency.getClass(), idCurrency.getId());
                request.setIdCurrency(idCurrency);
            }
            Supplier idSuplier = request.getIdSuplier();
            if (idSuplier != null) {
                idSuplier = em.getReference(idSuplier.getClass(), idSuplier.getId());
                request.setIdSuplier(idSuplier);
            }
            Collection<Requestdetail> attachedRequestdetailCollection = new ArrayList<Requestdetail>();
            for (Requestdetail requestdetailCollectionRequestdetailToAttach : request.getRequestdetailCollection()) {
                requestdetailCollectionRequestdetailToAttach = em.getReference(requestdetailCollectionRequestdetailToAttach.getClass(), requestdetailCollectionRequestdetailToAttach.getId());
                attachedRequestdetailCollection.add(requestdetailCollectionRequestdetailToAttach);
            }
            request.setRequestdetailCollection(attachedRequestdetailCollection);
            em.persist(request);
            if (idCurrency != null) {
                idCurrency.getRequestCollection().add(request);
                idCurrency = em.merge(idCurrency);
            }
            if (idSuplier != null) {
                idSuplier.getRequestCollection().add(request);
                idSuplier = em.merge(idSuplier);
            }
            for (Requestdetail requestdetailCollectionRequestdetail : request.getRequestdetailCollection()) {
                Request oldIdRequestOfRequestdetailCollectionRequestdetail = requestdetailCollectionRequestdetail.getIdRequest();
                requestdetailCollectionRequestdetail.setIdRequest(request);
                requestdetailCollectionRequestdetail = em.merge(requestdetailCollectionRequestdetail);
                if (oldIdRequestOfRequestdetailCollectionRequestdetail != null) {
                    oldIdRequestOfRequestdetailCollectionRequestdetail.getRequestdetailCollection().remove(requestdetailCollectionRequestdetail);
                    oldIdRequestOfRequestdetailCollectionRequestdetail = em.merge(oldIdRequestOfRequestdetailCollectionRequestdetail);
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
    public void edit(Request request) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Request persistentRequest = em.find(Request.class, request.getId());
            Currency idCurrencyOld = persistentRequest.getIdCurrency();
            Currency idCurrencyNew = request.getIdCurrency();
            Supplier idSuplierOld = persistentRequest.getIdSuplier();
            Supplier idSuplierNew = request.getIdSuplier();
            Collection<Requestdetail> requestdetailCollectionOld = persistentRequest.getRequestdetailCollection();
            Collection<Requestdetail> requestdetailCollectionNew = request.getRequestdetailCollection();
            List<String> illegalOrphanMessages = null;
            for (Requestdetail requestdetailCollectionOldRequestdetail : requestdetailCollectionOld) {
                if (!requestdetailCollectionNew.contains(requestdetailCollectionOldRequestdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Requestdetail " + requestdetailCollectionOldRequestdetail + " since its idRequest field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCurrencyNew != null) {
                idCurrencyNew = em.getReference(idCurrencyNew.getClass(), idCurrencyNew.getId());
                request.setIdCurrency(idCurrencyNew);
            }
            if (idSuplierNew != null) {
                idSuplierNew = em.getReference(idSuplierNew.getClass(), idSuplierNew.getId());
                request.setIdSuplier(idSuplierNew);
            }
            Collection<Requestdetail> attachedRequestdetailCollectionNew = new ArrayList<Requestdetail>();
            for (Requestdetail requestdetailCollectionNewRequestdetailToAttach : requestdetailCollectionNew) {
                requestdetailCollectionNewRequestdetailToAttach = em.getReference(requestdetailCollectionNewRequestdetailToAttach.getClass(), requestdetailCollectionNewRequestdetailToAttach.getId());
                attachedRequestdetailCollectionNew.add(requestdetailCollectionNewRequestdetailToAttach);
            }
            requestdetailCollectionNew = attachedRequestdetailCollectionNew;
            request.setRequestdetailCollection(requestdetailCollectionNew);
            request = em.merge(request);
            if (idCurrencyOld != null && !idCurrencyOld.equals(idCurrencyNew)) {
                idCurrencyOld.getRequestCollection().remove(request);
                idCurrencyOld = em.merge(idCurrencyOld);
            }
            if (idCurrencyNew != null && !idCurrencyNew.equals(idCurrencyOld)) {
                idCurrencyNew.getRequestCollection().add(request);
                idCurrencyNew = em.merge(idCurrencyNew);
            }
            if (idSuplierOld != null && !idSuplierOld.equals(idSuplierNew)) {
                idSuplierOld.getRequestCollection().remove(request);
                idSuplierOld = em.merge(idSuplierOld);
            }
            if (idSuplierNew != null && !idSuplierNew.equals(idSuplierOld)) {
                idSuplierNew.getRequestCollection().add(request);
                idSuplierNew = em.merge(idSuplierNew);
            }
            for (Requestdetail requestdetailCollectionNewRequestdetail : requestdetailCollectionNew) {
                if (!requestdetailCollectionOld.contains(requestdetailCollectionNewRequestdetail)) {
                    Request oldIdRequestOfRequestdetailCollectionNewRequestdetail = requestdetailCollectionNewRequestdetail.getIdRequest();
                    requestdetailCollectionNewRequestdetail.setIdRequest(request);
                    requestdetailCollectionNewRequestdetail = em.merge(requestdetailCollectionNewRequestdetail);
                    if (oldIdRequestOfRequestdetailCollectionNewRequestdetail != null && !oldIdRequestOfRequestdetailCollectionNewRequestdetail.equals(request)) {
                        oldIdRequestOfRequestdetailCollectionNewRequestdetail.getRequestdetailCollection().remove(requestdetailCollectionNewRequestdetail);
                        oldIdRequestOfRequestdetailCollectionNewRequestdetail = em.merge(oldIdRequestOfRequestdetailCollectionNewRequestdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = request.getId();
                if (findRequest(id) == null) {
                    throw new NonexistentEntityException("The request with id " + id + " no longer exists.");
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
            Request request;
            try {
                request = em.getReference(Request.class, id);
                request.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The request with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Requestdetail> requestdetailCollectionOrphanCheck = request.getRequestdetailCollection();
            for (Requestdetail requestdetailCollectionOrphanCheckRequestdetail : requestdetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Request (" + request + ") cannot be destroyed since the Requestdetail " + requestdetailCollectionOrphanCheckRequestdetail + " in its requestdetailCollection field has a non-nullable idRequest field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Currency idCurrency = request.getIdCurrency();
            if (idCurrency != null) {
                idCurrency.getRequestCollection().remove(request);
                idCurrency = em.merge(idCurrency);
            }
            Supplier idSuplier = request.getIdSuplier();
            if (idSuplier != null) {
                idSuplier.getRequestCollection().remove(request);
                idSuplier = em.merge(idSuplier);
            }
            em.remove(request);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Request> findRequestEntities() {
        return findRequestEntities(true, -1, -1);
    }

    @Override
    public List<Request> findRequestEntities(int maxResults, int firstResult) {
        return findRequestEntities(false, maxResults, firstResult);
    }

    private List<Request> findRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Request.class));
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
    public Request findRequest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Request.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Request> rt = cq.from(Request.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
