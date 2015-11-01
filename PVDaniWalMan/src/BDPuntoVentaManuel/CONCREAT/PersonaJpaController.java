/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Persona;
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
public class PersonaJpaController implements Serializable, IPersona {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PersonaJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Persona persona) {
        if (persona.getContactoCollection() == null) {
            persona.setContactoCollection(new ArrayList<Contacto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Contacto> attachedContactoCollection = new ArrayList<Contacto>();
            for (Contacto contactoCollectionContactoToAttach : persona.getContactoCollection()) {
                contactoCollectionContactoToAttach = em.getReference(contactoCollectionContactoToAttach.getClass(), contactoCollectionContactoToAttach.getId());
                attachedContactoCollection.add(contactoCollectionContactoToAttach);
            }
            persona.setContactoCollection(attachedContactoCollection);
            em.persist(persona);
            for (Contacto contactoCollectionContacto : persona.getContactoCollection()) {
                Persona oldIdPersonaOfContactoCollectionContacto = contactoCollectionContacto.getIdPersona();
                contactoCollectionContacto.setIdPersona(persona);
                contactoCollectionContacto = em.merge(contactoCollectionContacto);
                if (oldIdPersonaOfContactoCollectionContacto != null) {
                    oldIdPersonaOfContactoCollectionContacto.getContactoCollection().remove(contactoCollectionContacto);
                    oldIdPersonaOfContactoCollectionContacto = em.merge(oldIdPersonaOfContactoCollectionContacto);
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
    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            Collection<Contacto> contactoCollectionOld = persistentPersona.getContactoCollection();
            Collection<Contacto> contactoCollectionNew = persona.getContactoCollection();
            List<String> illegalOrphanMessages = null;
            for (Contacto contactoCollectionOldContacto : contactoCollectionOld) {
                if (!contactoCollectionNew.contains(contactoCollectionOldContacto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contacto " + contactoCollectionOldContacto + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Contacto> attachedContactoCollectionNew = new ArrayList<Contacto>();
            for (Contacto contactoCollectionNewContactoToAttach : contactoCollectionNew) {
                contactoCollectionNewContactoToAttach = em.getReference(contactoCollectionNewContactoToAttach.getClass(), contactoCollectionNewContactoToAttach.getId());
                attachedContactoCollectionNew.add(contactoCollectionNewContactoToAttach);
            }
            contactoCollectionNew = attachedContactoCollectionNew;
            persona.setContactoCollection(contactoCollectionNew);
            persona = em.merge(persona);
            for (Contacto contactoCollectionNewContacto : contactoCollectionNew) {
                if (!contactoCollectionOld.contains(contactoCollectionNewContacto)) {
                    Persona oldIdPersonaOfContactoCollectionNewContacto = contactoCollectionNewContacto.getIdPersona();
                    contactoCollectionNewContacto.setIdPersona(persona);
                    contactoCollectionNewContacto = em.merge(contactoCollectionNewContacto);
                    if (oldIdPersonaOfContactoCollectionNewContacto != null && !oldIdPersonaOfContactoCollectionNewContacto.equals(persona)) {
                        oldIdPersonaOfContactoCollectionNewContacto.getContactoCollection().remove(contactoCollectionNewContacto);
                        oldIdPersonaOfContactoCollectionNewContacto = em.merge(oldIdPersonaOfContactoCollectionNewContacto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Contacto> contactoCollectionOrphanCheck = persona.getContactoCollection();
            for (Contacto contactoCollectionOrphanCheckContacto : contactoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Contacto " + contactoCollectionOrphanCheckContacto + " in its contactoCollection field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    @Override
    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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
    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
