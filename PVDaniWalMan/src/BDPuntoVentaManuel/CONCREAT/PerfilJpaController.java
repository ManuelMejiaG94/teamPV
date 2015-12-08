/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Perfil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Pma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manuel
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        if (perfil.getUsuarioCollection() == null) {
            perfil.setUsuarioCollection(new ArrayList<Usuario>());
        }
        if (perfil.getPmaCollection() == null) {
            perfil.setPmaCollection(new ArrayList<Pma>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : perfil.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getId());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            perfil.setUsuarioCollection(attachedUsuarioCollection);
            Collection<Pma> attachedPmaCollection = new ArrayList<Pma>();
            for (Pma pmaCollectionPmaToAttach : perfil.getPmaCollection()) {
                pmaCollectionPmaToAttach = em.getReference(pmaCollectionPmaToAttach.getClass(), pmaCollectionPmaToAttach.getId());
                attachedPmaCollection.add(pmaCollectionPmaToAttach);
            }
            perfil.setPmaCollection(attachedPmaCollection);
            em.persist(perfil);
            for (Usuario usuarioCollectionUsuario : perfil.getUsuarioCollection()) {
                Perfil oldIdPerfilOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getIdPerfil();
                usuarioCollectionUsuario.setIdPerfil(perfil);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldIdPerfilOfUsuarioCollectionUsuario != null) {
                    oldIdPerfilOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldIdPerfilOfUsuarioCollectionUsuario = em.merge(oldIdPerfilOfUsuarioCollectionUsuario);
                }
            }
            for (Pma pmaCollectionPma : perfil.getPmaCollection()) {
                Perfil oldIdperfilOfPmaCollectionPma = pmaCollectionPma.getIdperfil();
                pmaCollectionPma.setIdperfil(perfil);
                pmaCollectionPma = em.merge(pmaCollectionPma);
                if (oldIdperfilOfPmaCollectionPma != null) {
                    oldIdperfilOfPmaCollectionPma.getPmaCollection().remove(pmaCollectionPma);
                    oldIdperfilOfPmaCollectionPma = em.merge(oldIdperfilOfPmaCollectionPma);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getId());
            Collection<Usuario> usuarioCollectionOld = persistentPerfil.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = perfil.getUsuarioCollection();
            Collection<Pma> pmaCollectionOld = persistentPerfil.getPmaCollection();
            Collection<Pma> pmaCollectionNew = perfil.getPmaCollection();
            List<String> illegalOrphanMessages = null;
            for (Pma pmaCollectionOldPma : pmaCollectionOld) {
                if (!pmaCollectionNew.contains(pmaCollectionOldPma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pma " + pmaCollectionOldPma + " since its idperfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getId());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            perfil.setUsuarioCollection(usuarioCollectionNew);
            Collection<Pma> attachedPmaCollectionNew = new ArrayList<Pma>();
            for (Pma pmaCollectionNewPmaToAttach : pmaCollectionNew) {
                pmaCollectionNewPmaToAttach = em.getReference(pmaCollectionNewPmaToAttach.getClass(), pmaCollectionNewPmaToAttach.getId());
                attachedPmaCollectionNew.add(pmaCollectionNewPmaToAttach);
            }
            pmaCollectionNew = attachedPmaCollectionNew;
            perfil.setPmaCollection(pmaCollectionNew);
            perfil = em.merge(perfil);
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setIdPerfil(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Perfil oldIdPerfilOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getIdPerfil();
                    usuarioCollectionNewUsuario.setIdPerfil(perfil);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldIdPerfilOfUsuarioCollectionNewUsuario != null && !oldIdPerfilOfUsuarioCollectionNewUsuario.equals(perfil)) {
                        oldIdPerfilOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldIdPerfilOfUsuarioCollectionNewUsuario = em.merge(oldIdPerfilOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            for (Pma pmaCollectionNewPma : pmaCollectionNew) {
                if (!pmaCollectionOld.contains(pmaCollectionNewPma)) {
                    Perfil oldIdperfilOfPmaCollectionNewPma = pmaCollectionNewPma.getIdperfil();
                    pmaCollectionNewPma.setIdperfil(perfil);
                    pmaCollectionNewPma = em.merge(pmaCollectionNewPma);
                    if (oldIdperfilOfPmaCollectionNewPma != null && !oldIdperfilOfPmaCollectionNewPma.equals(perfil)) {
                        oldIdperfilOfPmaCollectionNewPma.getPmaCollection().remove(pmaCollectionNewPma);
                        oldIdperfilOfPmaCollectionNewPma = em.merge(oldIdperfilOfPmaCollectionNewPma);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getId();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pma> pmaCollectionOrphanCheck = perfil.getPmaCollection();
            for (Pma pmaCollectionOrphanCheckPma : pmaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Pma " + pmaCollectionOrphanCheckPma + " in its pmaCollection field has a non-nullable idperfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Usuario> usuarioCollection = perfil.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setIdPerfil(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
