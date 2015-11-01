/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IRequestDetail;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Request;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Requestdetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class RequestdetailJpaController implements Serializable, IRequestDetail {

    public RequestdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public RequestdetailJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Requestdetail requestdetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Request idRequest = requestdetail.getIdRequest();
            if (idRequest != null) {
                idRequest = em.getReference(idRequest.getClass(), idRequest.getId());
                requestdetail.setIdRequest(idRequest);
            }
            Product idProduct = requestdetail.getIdProduct();
            if (idProduct != null) {
                idProduct = em.getReference(idProduct.getClass(), idProduct.getId());
                requestdetail.setIdProduct(idProduct);
            }
            em.persist(requestdetail);
            if (idRequest != null) {
                idRequest.getRequestdetailCollection().add(requestdetail);
                idRequest = em.merge(idRequest);
            }
            if (idProduct != null) {
                idProduct.getRequestdetailCollection().add(requestdetail);
                idProduct = em.merge(idProduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Requestdetail requestdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Requestdetail persistentRequestdetail = em.find(Requestdetail.class, requestdetail.getId());
            Request idRequestOld = persistentRequestdetail.getIdRequest();
            Request idRequestNew = requestdetail.getIdRequest();
            Product idProductOld = persistentRequestdetail.getIdProduct();
            Product idProductNew = requestdetail.getIdProduct();
            if (idRequestNew != null) {
                idRequestNew = em.getReference(idRequestNew.getClass(), idRequestNew.getId());
                requestdetail.setIdRequest(idRequestNew);
            }
            if (idProductNew != null) {
                idProductNew = em.getReference(idProductNew.getClass(), idProductNew.getId());
                requestdetail.setIdProduct(idProductNew);
            }
            requestdetail = em.merge(requestdetail);
            if (idRequestOld != null && !idRequestOld.equals(idRequestNew)) {
                idRequestOld.getRequestdetailCollection().remove(requestdetail);
                idRequestOld = em.merge(idRequestOld);
            }
            if (idRequestNew != null && !idRequestNew.equals(idRequestOld)) {
                idRequestNew.getRequestdetailCollection().add(requestdetail);
                idRequestNew = em.merge(idRequestNew);
            }
            if (idProductOld != null && !idProductOld.equals(idProductNew)) {
                idProductOld.getRequestdetailCollection().remove(requestdetail);
                idProductOld = em.merge(idProductOld);
            }
            if (idProductNew != null && !idProductNew.equals(idProductOld)) {
                idProductNew.getRequestdetailCollection().add(requestdetail);
                idProductNew = em.merge(idProductNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = requestdetail.getId();
                if (findRequestdetail(id) == null) {
                    throw new NonexistentEntityException("The requestdetail with id " + id + " no longer exists.");
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
            Requestdetail requestdetail;
            try {
                requestdetail = em.getReference(Requestdetail.class, id);
                requestdetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The requestdetail with id " + id + " no longer exists.", enfe);
            }
            Request idRequest = requestdetail.getIdRequest();
            if (idRequest != null) {
                idRequest.getRequestdetailCollection().remove(requestdetail);
                idRequest = em.merge(idRequest);
            }
            Product idProduct = requestdetail.getIdProduct();
            if (idProduct != null) {
                idProduct.getRequestdetailCollection().remove(requestdetail);
                idProduct = em.merge(idProduct);
            }
            em.remove(requestdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Requestdetail> findRequestdetailEntities() {
        return findRequestdetailEntities(true, -1, -1);
    }

    @Override
    public List<Requestdetail> findRequestdetailEntities(int maxResults, int firstResult) {
        return findRequestdetailEntities(false, maxResults, firstResult);
    }

    private List<Requestdetail> findRequestdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Requestdetail.class));
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
    public Requestdetail findRequestdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Requestdetail.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getRequestdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Requestdetail> rt = cq.from(Requestdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
