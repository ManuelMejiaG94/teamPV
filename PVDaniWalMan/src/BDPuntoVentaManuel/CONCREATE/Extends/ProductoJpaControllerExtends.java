/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class ProductoJpaControllerExtends implements IProductExtends {
    private EntityManagerFactory emf=null;
    
    public ProductoJpaControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    @Override
    public List<Product> findDatadefault()
    {
         try {
            EntityManager enm = getEntityManager();

            List<Product> listAlumno = (List<Product>) enm.createQuery("select p from Producto p")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
    @Override
    public List<Product> FindDataByFirstLetter(String letter)
    {
         try {
            EntityManager enm = getEntityManager();

            List<Product> listAlumno = (List<Product>) enm.createQuery(
                    "select p from Producto p wHERE p.strNombre LIKE :letter")
                    .setParameter("letter","%"+letter+"%")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
    @Override
    public List<Product> FindDataByCategoriaId(Catcategoria categoria)
    {
        try{
            EntityManager em = getEntityManager();
            List<Product>  products=em.createQuery("SELECT p FROM Product p WHERE p.idCategoria= :categoriaId")
                    .setParameter("categoriaId",categoria).getResultList();
            
            em.close();
            return products;
        }catch(Exception e)
        {
            Logger.getLogger(ProductoJpaControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    @Override
    public Product findProductByCode(String strClave) {
         try {
            EntityManager enm = getEntityManager();

            Product product = (Product) enm.createQuery("SELECT p FROM Product p WHERE p.strClave = :strClave")
                    .setParameter("strClave", strClave).getSingleResult();
            return product;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
}
