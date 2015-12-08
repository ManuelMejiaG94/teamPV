/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IUsuario;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Perfil;
import BDPuntoVentaManuel.MODEL.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class UsuarioJpaController implements Serializable, IUsuario {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil idPerfil = usuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getId());
                usuario.setIdPerfil(idPerfil);
            }
            em.persist(usuario);
            if (idPerfil != null) {
                idPerfil.getUsuarioCollection().add(usuario);
                idPerfil = em.merge(idPerfil);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Perfil idPerfilOld = persistentUsuario.getIdPerfil();
            Perfil idPerfilNew = usuario.getIdPerfil();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getId());
                usuario.setIdPerfil(idPerfilNew);
            }
            usuario = em.merge(usuario);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getUsuarioCollection().remove(usuario);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getUsuarioCollection().add(usuario);
                idPerfilNew = em.merge(idPerfilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Perfil idPerfil = usuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getUsuarioCollection().remove(usuario);
                idPerfil = em.merge(idPerfil);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    @Override
    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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
    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
