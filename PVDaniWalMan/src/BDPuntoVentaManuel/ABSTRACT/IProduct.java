/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Product;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IProduct {

    void create(Product product);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Product findProduct(Integer id);

    List<Product> findProductEntities();

    List<Product> findProductEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getProductCount();
    
}
