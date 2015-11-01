/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Podetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPoDetail {

    void create(Podetail podetail);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Podetail podetail) throws NonexistentEntityException, Exception;

    Podetail findPodetail(Integer id);

    List<Podetail> findPodetailEntities();

    List<Podetail> findPodetailEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPodetailCount();
    
}
