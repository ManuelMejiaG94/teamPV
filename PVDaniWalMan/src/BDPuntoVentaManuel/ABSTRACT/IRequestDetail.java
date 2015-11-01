/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Requestdetail;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IRequestDetail {

    void create(Requestdetail requestdetail);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Requestdetail requestdetail) throws NonexistentEntityException, Exception;

    Requestdetail findRequestdetail(Integer id);

    List<Requestdetail> findRequestdetailEntities();

    List<Requestdetail> findRequestdetailEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getRequestdetailCount();
    
}
