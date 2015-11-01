/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Salesdetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ISalesDetail {

    void create(Salesdetail salesdetail);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Salesdetail salesdetail) throws NonexistentEntityException, Exception;

    Salesdetail findSalesdetail(Integer id);

    List<Salesdetail> findSalesdetailEntities();

    List<Salesdetail> findSalesdetailEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getSalesdetailCount();
    
}
