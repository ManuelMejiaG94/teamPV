/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPoDetail;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Po;
import BDPuntoVentaManuel.MODEL.Podetail;
import BDPuntoVentaManuel.MODEL.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PodetailJpaController implements Serializable, IPoDetail {

    public PodetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PodetailJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    
    

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Podetail podetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Po idPo = podetail.getIdPo();
            if (idPo != null) {
                idPo = em.getReference(idPo.getClass(), idPo.getId());
                podetail.setIdPo(idPo);
            }
            Product idProducto = podetail.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                podetail.setIdProducto(idProducto);
            }
            em.persist(podetail);
            if (idPo != null) {
                idPo.getPodetailCollection().add(podetail);
                idPo = em.merge(idPo);
            }
            if (idProducto != null) {
                idProducto.getPodetailCollection().add(podetail);
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
    public void edit(Podetail podetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Podetail persistentPodetail = em.find(Podetail.class, podetail.getId());
            Po idPoOld = persistentPodetail.getIdPo();
            Po idPoNew = podetail.getIdPo();
            Product idProductoOld = persistentPodetail.getIdProducto();
            Product idProductoNew = podetail.getIdProducto();
            if (idPoNew != null) {
                idPoNew = em.getReference(idPoNew.getClass(), idPoNew.getId());
                podetail.setIdPo(idPoNew);
            }
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                podetail.setIdProducto(idProductoNew);
            }
            podetail = em.merge(podetail);
            if (idPoOld != null && !idPoOld.equals(idPoNew)) {
                idPoOld.getPodetailCollection().remove(podetail);
                idPoOld = em.merge(idPoOld);
            }
            if (idPoNew != null && !idPoNew.equals(idPoOld)) {
                idPoNew.getPodetailCollection().add(podetail);
                idPoNew = em.merge(idPoNew);
            }
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getPodetailCollection().remove(podetail);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getPodetailCollection().add(podetail);
                idProductoNew = em.merge(idProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = podetail.getId();
                if (findPodetail(id) == null) {
                    throw new NonexistentEntityException("The podetail with id " + id + " no longer exists.");
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
            Podetail podetail;
            try {
                podetail = em.getReference(Podetail.class, id);
                podetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The podetail with id " + id + " no longer exists.", enfe);
            }
            Po idPo = podetail.getIdPo();
            if (idPo != null) {
                idPo.getPodetailCollection().remove(podetail);
                idPo = em.merge(idPo);
            }
            Product idProducto = podetail.getIdProducto();
            if (idProducto != null) {
                idProducto.getPodetailCollection().remove(podetail);
                idProducto = em.merge(idProducto);
            }
            em.remove(podetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Podetail> findPodetailEntities() {
        return findPodetailEntities(true, -1, -1);
    }

    @Override
    public List<Podetail> findPodetailEntities(int maxResults, int firstResult) {
        return findPodetailEntities(false, maxResults, firstResult);
    }

    private List<Podetail> findPodetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Podetail.class));
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
    public Podetail findPodetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Podetail.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPodetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Podetail> rt = cq.from(Podetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
