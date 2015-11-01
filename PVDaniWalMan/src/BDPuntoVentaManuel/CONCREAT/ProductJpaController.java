/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Salesdetail;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Podetail;
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
public class ProductJpaController implements Serializable, IProduct {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProductJpaController() {
    this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }

    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Product product) {
        if (product.getSalesdetailCollection() == null) {
            product.setSalesdetailCollection(new ArrayList<Salesdetail>());
        }
        if (product.getPodetailCollection() == null) {
            product.setPodetailCollection(new ArrayList<Podetail>());
        }
        if (product.getRequestdetailCollection() == null) {
            product.setRequestdetailCollection(new ArrayList<Requestdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catcategoria idCategoria = product.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                product.setIdCategoria(idCategoria);
            }
            Collection<Salesdetail> attachedSalesdetailCollection = new ArrayList<Salesdetail>();
            for (Salesdetail salesdetailCollectionSalesdetailToAttach : product.getSalesdetailCollection()) {
                salesdetailCollectionSalesdetailToAttach = em.getReference(salesdetailCollectionSalesdetailToAttach.getClass(), salesdetailCollectionSalesdetailToAttach.getId());
                attachedSalesdetailCollection.add(salesdetailCollectionSalesdetailToAttach);
            }
            product.setSalesdetailCollection(attachedSalesdetailCollection);
            Collection<Podetail> attachedPodetailCollection = new ArrayList<Podetail>();
            for (Podetail podetailCollectionPodetailToAttach : product.getPodetailCollection()) {
                podetailCollectionPodetailToAttach = em.getReference(podetailCollectionPodetailToAttach.getClass(), podetailCollectionPodetailToAttach.getId());
                attachedPodetailCollection.add(podetailCollectionPodetailToAttach);
            }
            product.setPodetailCollection(attachedPodetailCollection);
            Collection<Requestdetail> attachedRequestdetailCollection = new ArrayList<Requestdetail>();
            for (Requestdetail requestdetailCollectionRequestdetailToAttach : product.getRequestdetailCollection()) {
                requestdetailCollectionRequestdetailToAttach = em.getReference(requestdetailCollectionRequestdetailToAttach.getClass(), requestdetailCollectionRequestdetailToAttach.getId());
                attachedRequestdetailCollection.add(requestdetailCollectionRequestdetailToAttach);
            }
            product.setRequestdetailCollection(attachedRequestdetailCollection);
            em.persist(product);
            if (idCategoria != null) {
                idCategoria.getProductCollection().add(product);
                idCategoria = em.merge(idCategoria);
            }
            for (Salesdetail salesdetailCollectionSalesdetail : product.getSalesdetailCollection()) {
                Product oldIdProductoOfSalesdetailCollectionSalesdetail = salesdetailCollectionSalesdetail.getIdProducto();
                salesdetailCollectionSalesdetail.setIdProducto(product);
                salesdetailCollectionSalesdetail = em.merge(salesdetailCollectionSalesdetail);
                if (oldIdProductoOfSalesdetailCollectionSalesdetail != null) {
                    oldIdProductoOfSalesdetailCollectionSalesdetail.getSalesdetailCollection().remove(salesdetailCollectionSalesdetail);
                    oldIdProductoOfSalesdetailCollectionSalesdetail = em.merge(oldIdProductoOfSalesdetailCollectionSalesdetail);
                }
            }
            for (Podetail podetailCollectionPodetail : product.getPodetailCollection()) {
                Product oldIdProductoOfPodetailCollectionPodetail = podetailCollectionPodetail.getIdProducto();
                podetailCollectionPodetail.setIdProducto(product);
                podetailCollectionPodetail = em.merge(podetailCollectionPodetail);
                if (oldIdProductoOfPodetailCollectionPodetail != null) {
                    oldIdProductoOfPodetailCollectionPodetail.getPodetailCollection().remove(podetailCollectionPodetail);
                    oldIdProductoOfPodetailCollectionPodetail = em.merge(oldIdProductoOfPodetailCollectionPodetail);
                }
            }
            for (Requestdetail requestdetailCollectionRequestdetail : product.getRequestdetailCollection()) {
                Product oldIdProductOfRequestdetailCollectionRequestdetail = requestdetailCollectionRequestdetail.getIdProduct();
                requestdetailCollectionRequestdetail.setIdProduct(product);
                requestdetailCollectionRequestdetail = em.merge(requestdetailCollectionRequestdetail);
                if (oldIdProductOfRequestdetailCollectionRequestdetail != null) {
                    oldIdProductOfRequestdetailCollectionRequestdetail.getRequestdetailCollection().remove(requestdetailCollectionRequestdetail);
                    oldIdProductOfRequestdetailCollectionRequestdetail = em.merge(oldIdProductOfRequestdetailCollectionRequestdetail);
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
    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Catcategoria idCategoriaOld = persistentProduct.getIdCategoria();
            Catcategoria idCategoriaNew = product.getIdCategoria();
            Collection<Salesdetail> salesdetailCollectionOld = persistentProduct.getSalesdetailCollection();
            Collection<Salesdetail> salesdetailCollectionNew = product.getSalesdetailCollection();
            Collection<Podetail> podetailCollectionOld = persistentProduct.getPodetailCollection();
            Collection<Podetail> podetailCollectionNew = product.getPodetailCollection();
            Collection<Requestdetail> requestdetailCollectionOld = persistentProduct.getRequestdetailCollection();
            Collection<Requestdetail> requestdetailCollectionNew = product.getRequestdetailCollection();
            List<String> illegalOrphanMessages = null;
            for (Salesdetail salesdetailCollectionOldSalesdetail : salesdetailCollectionOld) {
                if (!salesdetailCollectionNew.contains(salesdetailCollectionOldSalesdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Salesdetail " + salesdetailCollectionOldSalesdetail + " since its idProducto field is not nullable.");
                }
            }
            for (Podetail podetailCollectionOldPodetail : podetailCollectionOld) {
                if (!podetailCollectionNew.contains(podetailCollectionOldPodetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Podetail " + podetailCollectionOldPodetail + " since its idProducto field is not nullable.");
                }
            }
            for (Requestdetail requestdetailCollectionOldRequestdetail : requestdetailCollectionOld) {
                if (!requestdetailCollectionNew.contains(requestdetailCollectionOldRequestdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Requestdetail " + requestdetailCollectionOldRequestdetail + " since its idProduct field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                product.setIdCategoria(idCategoriaNew);
            }
            Collection<Salesdetail> attachedSalesdetailCollectionNew = new ArrayList<Salesdetail>();
            for (Salesdetail salesdetailCollectionNewSalesdetailToAttach : salesdetailCollectionNew) {
                salesdetailCollectionNewSalesdetailToAttach = em.getReference(salesdetailCollectionNewSalesdetailToAttach.getClass(), salesdetailCollectionNewSalesdetailToAttach.getId());
                attachedSalesdetailCollectionNew.add(salesdetailCollectionNewSalesdetailToAttach);
            }
            salesdetailCollectionNew = attachedSalesdetailCollectionNew;
            product.setSalesdetailCollection(salesdetailCollectionNew);
            Collection<Podetail> attachedPodetailCollectionNew = new ArrayList<Podetail>();
            for (Podetail podetailCollectionNewPodetailToAttach : podetailCollectionNew) {
                podetailCollectionNewPodetailToAttach = em.getReference(podetailCollectionNewPodetailToAttach.getClass(), podetailCollectionNewPodetailToAttach.getId());
                attachedPodetailCollectionNew.add(podetailCollectionNewPodetailToAttach);
            }
            podetailCollectionNew = attachedPodetailCollectionNew;
            product.setPodetailCollection(podetailCollectionNew);
            Collection<Requestdetail> attachedRequestdetailCollectionNew = new ArrayList<Requestdetail>();
            for (Requestdetail requestdetailCollectionNewRequestdetailToAttach : requestdetailCollectionNew) {
                requestdetailCollectionNewRequestdetailToAttach = em.getReference(requestdetailCollectionNewRequestdetailToAttach.getClass(), requestdetailCollectionNewRequestdetailToAttach.getId());
                attachedRequestdetailCollectionNew.add(requestdetailCollectionNewRequestdetailToAttach);
            }
            requestdetailCollectionNew = attachedRequestdetailCollectionNew;
            product.setRequestdetailCollection(requestdetailCollectionNew);
            product = em.merge(product);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProductCollection().remove(product);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProductCollection().add(product);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (Salesdetail salesdetailCollectionNewSalesdetail : salesdetailCollectionNew) {
                if (!salesdetailCollectionOld.contains(salesdetailCollectionNewSalesdetail)) {
                    Product oldIdProductoOfSalesdetailCollectionNewSalesdetail = salesdetailCollectionNewSalesdetail.getIdProducto();
                    salesdetailCollectionNewSalesdetail.setIdProducto(product);
                    salesdetailCollectionNewSalesdetail = em.merge(salesdetailCollectionNewSalesdetail);
                    if (oldIdProductoOfSalesdetailCollectionNewSalesdetail != null && !oldIdProductoOfSalesdetailCollectionNewSalesdetail.equals(product)) {
                        oldIdProductoOfSalesdetailCollectionNewSalesdetail.getSalesdetailCollection().remove(salesdetailCollectionNewSalesdetail);
                        oldIdProductoOfSalesdetailCollectionNewSalesdetail = em.merge(oldIdProductoOfSalesdetailCollectionNewSalesdetail);
                    }
                }
            }
            for (Podetail podetailCollectionNewPodetail : podetailCollectionNew) {
                if (!podetailCollectionOld.contains(podetailCollectionNewPodetail)) {
                    Product oldIdProductoOfPodetailCollectionNewPodetail = podetailCollectionNewPodetail.getIdProducto();
                    podetailCollectionNewPodetail.setIdProducto(product);
                    podetailCollectionNewPodetail = em.merge(podetailCollectionNewPodetail);
                    if (oldIdProductoOfPodetailCollectionNewPodetail != null && !oldIdProductoOfPodetailCollectionNewPodetail.equals(product)) {
                        oldIdProductoOfPodetailCollectionNewPodetail.getPodetailCollection().remove(podetailCollectionNewPodetail);
                        oldIdProductoOfPodetailCollectionNewPodetail = em.merge(oldIdProductoOfPodetailCollectionNewPodetail);
                    }
                }
            }
            for (Requestdetail requestdetailCollectionNewRequestdetail : requestdetailCollectionNew) {
                if (!requestdetailCollectionOld.contains(requestdetailCollectionNewRequestdetail)) {
                    Product oldIdProductOfRequestdetailCollectionNewRequestdetail = requestdetailCollectionNewRequestdetail.getIdProduct();
                    requestdetailCollectionNewRequestdetail.setIdProduct(product);
                    requestdetailCollectionNewRequestdetail = em.merge(requestdetailCollectionNewRequestdetail);
                    if (oldIdProductOfRequestdetailCollectionNewRequestdetail != null && !oldIdProductOfRequestdetailCollectionNewRequestdetail.equals(product)) {
                        oldIdProductOfRequestdetailCollectionNewRequestdetail.getRequestdetailCollection().remove(requestdetailCollectionNewRequestdetail);
                        oldIdProductOfRequestdetailCollectionNewRequestdetail = em.merge(oldIdProductOfRequestdetailCollectionNewRequestdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Salesdetail> salesdetailCollectionOrphanCheck = product.getSalesdetailCollection();
            for (Salesdetail salesdetailCollectionOrphanCheckSalesdetail : salesdetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Salesdetail " + salesdetailCollectionOrphanCheckSalesdetail + " in its salesdetailCollection field has a non-nullable idProducto field.");
            }
            Collection<Podetail> podetailCollectionOrphanCheck = product.getPodetailCollection();
            for (Podetail podetailCollectionOrphanCheckPodetail : podetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Podetail " + podetailCollectionOrphanCheckPodetail + " in its podetailCollection field has a non-nullable idProducto field.");
            }
            Collection<Requestdetail> requestdetailCollectionOrphanCheck = product.getRequestdetailCollection();
            for (Requestdetail requestdetailCollectionOrphanCheckRequestdetail : requestdetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Requestdetail " + requestdetailCollectionOrphanCheckRequestdetail + " in its requestdetailCollection field has a non-nullable idProduct field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Catcategoria idCategoria = product.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProductCollection().remove(product);
                idCategoria = em.merge(idCategoria);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    @Override
    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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
    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
