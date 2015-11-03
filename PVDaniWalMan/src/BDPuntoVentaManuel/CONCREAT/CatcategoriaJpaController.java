/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ICatCategori;
import BDPuntoVentaManuel.ABSTRACT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.ABSTRACT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Product;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Supplier;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class CatcategoriaJpaController implements Serializable, ICatCategori{

    public CatcategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CatcategoriaJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    

    @Override
    public void create(Catcategoria catcategoria) {
        if (catcategoria.getProductCollection() == null) {
            catcategoria.setProductCollection(new ArrayList<Product>());
        }
        if (catcategoria.getSupplierCollection() == null) {
            catcategoria.setSupplierCollection(new ArrayList<Supplier>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : catcategoria.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getId());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            catcategoria.setProductCollection(attachedProductCollection);
            Collection<Supplier> attachedSupplierCollection = new ArrayList<Supplier>();
            for (Supplier supplierCollectionSupplierToAttach : catcategoria.getSupplierCollection()) {
                supplierCollectionSupplierToAttach = em.getReference(supplierCollectionSupplierToAttach.getClass(), supplierCollectionSupplierToAttach.getId());
                attachedSupplierCollection.add(supplierCollectionSupplierToAttach);
            }
            catcategoria.setSupplierCollection(attachedSupplierCollection);
            em.persist(catcategoria);
            for (Product productCollectionProduct : catcategoria.getProductCollection()) {
                Catcategoria oldIdCategoriaOfProductCollectionProduct = productCollectionProduct.getIdCategoria();
                productCollectionProduct.setIdCategoria(catcategoria);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldIdCategoriaOfProductCollectionProduct != null) {
                    oldIdCategoriaOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldIdCategoriaOfProductCollectionProduct = em.merge(oldIdCategoriaOfProductCollectionProduct);
                }
            }
            for (Supplier supplierCollectionSupplier : catcategoria.getSupplierCollection()) {
                Catcategoria oldIdCategoriaOfSupplierCollectionSupplier = supplierCollectionSupplier.getIdCategoria();
                supplierCollectionSupplier.setIdCategoria(catcategoria);
                supplierCollectionSupplier = em.merge(supplierCollectionSupplier);
                if (oldIdCategoriaOfSupplierCollectionSupplier != null) {
                    oldIdCategoriaOfSupplierCollectionSupplier.getSupplierCollection().remove(supplierCollectionSupplier);
                    oldIdCategoriaOfSupplierCollectionSupplier = em.merge(oldIdCategoriaOfSupplierCollectionSupplier);
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
    public void edit(Catcategoria catcategoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catcategoria persistentCatcategoria = em.find(Catcategoria.class, catcategoria.getId());
            Collection<Product> productCollectionOld = persistentCatcategoria.getProductCollection();
            Collection<Product> productCollectionNew = catcategoria.getProductCollection();
            Collection<Supplier> supplierCollectionOld = persistentCatcategoria.getSupplierCollection();
            Collection<Supplier> supplierCollectionNew = catcategoria.getSupplierCollection();
            List<String> illegalOrphanMessages = null;
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productCollectionOldProduct + " since its idCategoria field is not nullable.");
                }
            }
            for (Supplier supplierCollectionOldSupplier : supplierCollectionOld) {
                if (!supplierCollectionNew.contains(supplierCollectionOldSupplier)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Supplier " + supplierCollectionOldSupplier + " since its idCategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getId());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            catcategoria.setProductCollection(productCollectionNew);
            Collection<Supplier> attachedSupplierCollectionNew = new ArrayList<Supplier>();
            for (Supplier supplierCollectionNewSupplierToAttach : supplierCollectionNew) {
                supplierCollectionNewSupplierToAttach = em.getReference(supplierCollectionNewSupplierToAttach.getClass(), supplierCollectionNewSupplierToAttach.getId());
                attachedSupplierCollectionNew.add(supplierCollectionNewSupplierToAttach);
            }
            supplierCollectionNew = attachedSupplierCollectionNew;
            catcategoria.setSupplierCollection(supplierCollectionNew);
            catcategoria = em.merge(catcategoria);
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    Catcategoria oldIdCategoriaOfProductCollectionNewProduct = productCollectionNewProduct.getIdCategoria();
                    productCollectionNewProduct.setIdCategoria(catcategoria);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldIdCategoriaOfProductCollectionNewProduct != null && !oldIdCategoriaOfProductCollectionNewProduct.equals(catcategoria)) {
                        oldIdCategoriaOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldIdCategoriaOfProductCollectionNewProduct = em.merge(oldIdCategoriaOfProductCollectionNewProduct);
                    }
                }
            }
            for (Supplier supplierCollectionNewSupplier : supplierCollectionNew) {
                if (!supplierCollectionOld.contains(supplierCollectionNewSupplier)) {
                    Catcategoria oldIdCategoriaOfSupplierCollectionNewSupplier = supplierCollectionNewSupplier.getIdCategoria();
                    supplierCollectionNewSupplier.setIdCategoria(catcategoria);
                    supplierCollectionNewSupplier = em.merge(supplierCollectionNewSupplier);
                    if (oldIdCategoriaOfSupplierCollectionNewSupplier != null && !oldIdCategoriaOfSupplierCollectionNewSupplier.equals(catcategoria)) {
                        oldIdCategoriaOfSupplierCollectionNewSupplier.getSupplierCollection().remove(supplierCollectionNewSupplier);
                        oldIdCategoriaOfSupplierCollectionNewSupplier = em.merge(oldIdCategoriaOfSupplierCollectionNewSupplier);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catcategoria.getId();
                if (findCatcategoria(id) == null) {
                    throw new NonexistentEntityException("The catcategoria with id " + id + " no longer exists.");
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
            Catcategoria catcategoria;
            try {
                catcategoria = em.getReference(Catcategoria.class, id);
                catcategoria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catcategoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Product> productCollectionOrphanCheck = catcategoria.getProductCollection();
            for (Product productCollectionOrphanCheckProduct : productCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catcategoria (" + catcategoria + ") cannot be destroyed since the Product " + productCollectionOrphanCheckProduct + " in its productCollection field has a non-nullable idCategoria field.");
            }
            Collection<Supplier> supplierCollectionOrphanCheck = catcategoria.getSupplierCollection();
            for (Supplier supplierCollectionOrphanCheckSupplier : supplierCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catcategoria (" + catcategoria + ") cannot be destroyed since the Supplier " + supplierCollectionOrphanCheckSupplier + " in its supplierCollection field has a non-nullable idCategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(catcategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Catcategoria> findCatcategoriaEntities() {
        return findCatcategoriaEntities(true, -1, -1);
    }

    @Override
    public List<Catcategoria> findCatcategoriaEntities(int maxResults, int firstResult) {
        return findCatcategoriaEntities(false, maxResults, firstResult);
    }

    private List<Catcategoria> findCatcategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catcategoria.class));
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
    public Catcategoria findCatcategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catcategoria.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatcategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catcategoria> rt = cq.from(Catcategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
}
