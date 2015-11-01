/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Sales;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ISales {

    void create(Sales sales);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Sales sales) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Sales findSales(Integer id);

    List<Sales> findSalesEntities();

    List<Sales> findSalesEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getSalesCount();
    
}
