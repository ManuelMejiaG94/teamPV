/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ISupplier;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Request;
import BDPuntoVentaManuel.MODEL.Supplier;
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
public class SupplierJpaController implements Serializable, ISupplier {

    public SupplierJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public SupplierJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Supplier supplier) {
        if (supplier.getRequestCollection() == null) {
            supplier.setRequestCollection(new ArrayList<Request>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contacto idContacto = supplier.getIdContacto();
            if (idContacto != null) {
                idContacto = em.getReference(idContacto.getClass(), idContacto.getId());
                supplier.setIdContacto(idContacto);
            }
            Catcategoria idCategoria = supplier.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                supplier.setIdCategoria(idCategoria);
            }
            Collection<Request> attachedRequestCollection = new ArrayList<Request>();
            for (Request requestCollectionRequestToAttach : supplier.getRequestCollection()) {
                requestCollectionRequestToAttach = em.getReference(requestCollectionRequestToAttach.getClass(), requestCollectionRequestToAttach.getId());
                attachedRequestCollection.add(requestCollectionRequestToAttach);
            }
            supplier.setRequestCollection(attachedRequestCollection);
            em.persist(supplier);
            if (idContacto != null) {
                idContacto.getSupplierCollection().add(supplier);
                idContacto = em.merge(idContacto);
            }
            if (idCategoria != null) {
                idCategoria.getSupplierCollection().add(supplier);
                idCategoria = em.merge(idCategoria);
            }
            for (Request requestCollectionRequest : supplier.getRequestCollection()) {
                Supplier oldIdSuplierOfRequestCollectionRequest = requestCollectionRequest.getIdSuplier();
                requestCollectionRequest.setIdSuplier(supplier);
                requestCollectionRequest = em.merge(requestCollectionRequest);
                if (oldIdSuplierOfRequestCollectionRequest != null) {
                    oldIdSuplierOfRequestCollectionRequest.getRequestCollection().remove(requestCollectionRequest);
                    oldIdSuplierOfRequestCollectionRequest = em.merge(oldIdSuplierOfRequestCollectionRequest);
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
    public void edit(Supplier supplier) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Supplier persistentSupplier = em.find(Supplier.class, supplier.getId());
            Contacto idContactoOld = persistentSupplier.getIdContacto();
            Contacto idContactoNew = supplier.getIdContacto();
            Catcategoria idCategoriaOld = persistentSupplier.getIdCategoria();
            Catcategoria idCategoriaNew = supplier.getIdCategoria();
            Collection<Request> requestCollectionOld = persistentSupplier.getRequestCollection();
            Collection<Request> requestCollectionNew = supplier.getRequestCollection();
            List<String> illegalOrphanMessages = null;
            for (Request requestCollectionOldRequest : requestCollectionOld) {
                if (!requestCollectionNew.contains(requestCollectionOldRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Request " + requestCollectionOldRequest + " since its idSuplier field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idContactoNew != null) {
                idContactoNew = em.getReference(idContactoNew.getClass(), idContactoNew.getId());
                supplier.setIdContacto(idContactoNew);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                supplier.setIdCategoria(idCategoriaNew);
            }
            Collection<Request> attachedRequestCollectionNew = new ArrayList<Request>();
            for (Request requestCollectionNewRequestToAttach : requestCollectionNew) {
                requestCollectionNewRequestToAttach = em.getReference(requestCollectionNewRequestToAttach.getClass(), requestCollectionNewRequestToAttach.getId());
                attachedRequestCollectionNew.add(requestCollectionNewRequestToAttach);
            }
            requestCollectionNew = attachedRequestCollectionNew;
            supplier.setRequestCollection(requestCollectionNew);
            supplier = em.merge(supplier);
            if (idContactoOld != null && !idContactoOld.equals(idContactoNew)) {
                idContactoOld.getSupplierCollection().remove(supplier);
                idContactoOld = em.merge(idContactoOld);
            }
            if (idContactoNew != null && !idContactoNew.equals(idContactoOld)) {
                idContactoNew.getSupplierCollection().add(supplier);
                idContactoNew = em.merge(idContactoNew);
            }
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getSupplierCollection().remove(supplier);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getSupplierCollection().add(supplier);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (Request requestCollectionNewRequest : requestCollectionNew) {
                if (!requestCollectionOld.contains(requestCollectionNewRequest)) {
                    Supplier oldIdSuplierOfRequestCollectionNewRequest = requestCollectionNewRequest.getIdSuplier();
                    requestCollectionNewRequest.setIdSuplier(supplier);
                    requestCollectionNewRequest = em.merge(requestCollectionNewRequest);
                    if (oldIdSuplierOfRequestCollectionNewRequest != null && !oldIdSuplierOfRequestCollectionNewRequest.equals(supplier)) {
                        oldIdSuplierOfRequestCollectionNewRequest.getRequestCollection().remove(requestCollectionNewRequest);
                        oldIdSuplierOfRequestCollectionNewRequest = em.merge(oldIdSuplierOfRequestCollectionNewRequest);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = supplier.getId();
                if (findSupplier(id) == null) {
                    throw new NonexistentEntityException("The supplier with id " + id + " no longer exists.");
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
            Supplier supplier;
            try {
                supplier = em.getReference(Supplier.class, id);
                supplier.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The supplier with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Request> requestCollectionOrphanCheck = supplier.getRequestCollection();
            for (Request requestCollectionOrphanCheckRequest : requestCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Supplier (" + supplier + ") cannot be destroyed since the Request " + requestCollectionOrphanCheckRequest + " in its requestCollection field has a non-nullable idSuplier field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Contacto idContacto = supplier.getIdContacto();
            if (idContacto != null) {
                idContacto.getSupplierCollection().remove(supplier);
                idContacto = em.merge(idContacto);
            }
            Catcategoria idCategoria = supplier.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getSupplierCollection().remove(supplier);
                idCategoria = em.merge(idCategoria);
            }
            em.remove(supplier);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Supplier> findSupplierEntities() {
        return findSupplierEntities(true, -1, -1);
    }

    @Override
    public List<Supplier> findSupplierEntities(int maxResults, int firstResult) {
        return findSupplierEntities(false, maxResults, firstResult);
    }

    private List<Supplier> findSupplierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Supplier.class));
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
    public Supplier findSupplier(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getSupplierCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Supplier> rt = cq.from(Supplier.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
