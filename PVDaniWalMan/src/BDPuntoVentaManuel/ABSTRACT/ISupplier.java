/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Supplier;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ISupplier {

    void create(Supplier supplier);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Supplier supplier) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Supplier findSupplier(Integer id);

    List<Supplier> findSupplierEntities();

    List<Supplier> findSupplierEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getSupplierCount();
    
}
