/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IContact;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Contacto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Persona;
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
public class ContactoJpaController implements Serializable, IContact {

    public ContactoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ContactoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Contacto contacto) {
        if (contacto.getSupplierCollection() == null) {
            contacto.setSupplierCollection(new ArrayList<Supplier>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = contacto.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getId());
                contacto.setIdPersona(idPersona);
            }
            Collection<Supplier> attachedSupplierCollection = new ArrayList<Supplier>();
            for (Supplier supplierCollectionSupplierToAttach : contacto.getSupplierCollection()) {
                supplierCollectionSupplierToAttach = em.getReference(supplierCollectionSupplierToAttach.getClass(), supplierCollectionSupplierToAttach.getId());
                attachedSupplierCollection.add(supplierCollectionSupplierToAttach);
            }
            contacto.setSupplierCollection(attachedSupplierCollection);
            em.persist(contacto);
            if (idPersona != null) {
                idPersona.getContactoCollection().add(contacto);
                idPersona = em.merge(idPersona);
            }
            for (Supplier supplierCollectionSupplier : contacto.getSupplierCollection()) {
                Contacto oldIdContactoOfSupplierCollectionSupplier = supplierCollectionSupplier.getIdContacto();
                supplierCollectionSupplier.setIdContacto(contacto);
                supplierCollectionSupplier = em.merge(supplierCollectionSupplier);
                if (oldIdContactoOfSupplierCollectionSupplier != null) {
                    oldIdContactoOfSupplierCollectionSupplier.getSupplierCollection().remove(supplierCollectionSupplier);
                    oldIdContactoOfSupplierCollectionSupplier = em.merge(oldIdContactoOfSupplierCollectionSupplier);
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
    public void edit(Contacto contacto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contacto persistentContacto = em.find(Contacto.class, contacto.getId());
            Persona idPersonaOld = persistentContacto.getIdPersona();
            Persona idPersonaNew = contacto.getIdPersona();
            Collection<Supplier> supplierCollectionOld = persistentContacto.getSupplierCollection();
            Collection<Supplier> supplierCollectionNew = contacto.getSupplierCollection();
            List<String> illegalOrphanMessages = null;
            for (Supplier supplierCollectionOldSupplier : supplierCollectionOld) {
                if (!supplierCollectionNew.contains(supplierCollectionOldSupplier)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Supplier " + supplierCollectionOldSupplier + " since its idContacto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getId());
                contacto.setIdPersona(idPersonaNew);
            }
            Collection<Supplier> attachedSupplierCollectionNew = new ArrayList<Supplier>();
            for (Supplier supplierCollectionNewSupplierToAttach : supplierCollectionNew) {
                supplierCollectionNewSupplierToAttach = em.getReference(supplierCollectionNewSupplierToAttach.getClass(), supplierCollectionNewSupplierToAttach.getId());
                attachedSupplierCollectionNew.add(supplierCollectionNewSupplierToAttach);
            }
            supplierCollectionNew = attachedSupplierCollectionNew;
            contacto.setSupplierCollection(supplierCollectionNew);
            contacto = em.merge(contacto);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getContactoCollection().remove(contacto);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getContactoCollection().add(contacto);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Supplier supplierCollectionNewSupplier : supplierCollectionNew) {
                if (!supplierCollectionOld.contains(supplierCollectionNewSupplier)) {
                    Contacto oldIdContactoOfSupplierCollectionNewSupplier = supplierCollectionNewSupplier.getIdContacto();
                    supplierCollectionNewSupplier.setIdContacto(contacto);
                    supplierCollectionNewSupplier = em.merge(supplierCollectionNewSupplier);
                    if (oldIdContactoOfSupplierCollectionNewSupplier != null && !oldIdContactoOfSupplierCollectionNewSupplier.equals(contacto)) {
                        oldIdContactoOfSupplierCollectionNewSupplier.getSupplierCollection().remove(supplierCollectionNewSupplier);
                        oldIdContactoOfSupplierCollectionNewSupplier = em.merge(oldIdContactoOfSupplierCollectionNewSupplier);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contacto.getId();
                if (findContacto(id) == null) {
                    throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.");
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
            Contacto contacto;
            try {
                contacto = em.getReference(Contacto.class, id);
                contacto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Supplier> supplierCollectionOrphanCheck = contacto.getSupplierCollection();
            for (Supplier supplierCollectionOrphanCheckSupplier : supplierCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contacto (" + contacto + ") cannot be destroyed since the Supplier " + supplierCollectionOrphanCheckSupplier + " in its supplierCollection field has a non-nullable idContacto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = contacto.getIdPersona();
            if (idPersona != null) {
                idPersona.getContactoCollection().remove(contacto);
                idPersona = em.merge(idPersona);
            }
            em.remove(contacto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Contacto> findContactoEntities() {
        return findContactoEntities(true, -1, -1);
    }

    @Override
    public List<Contacto> findContactoEntities(int maxResults, int firstResult) {
        return findContactoEntities(false, maxResults, firstResult);
    }

    private List<Contacto> findContactoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contacto.class));
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
    public Contacto findContacto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contacto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getContactoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contacto> rt = cq.from(Contacto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
