/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Request;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IRequest {

    void create(Request request);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Request request) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Request findRequest(Integer id);

    List<Request> findRequestEntities();

    List<Request> findRequestEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getRequestCount();
    
}
