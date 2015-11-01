/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Po;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPO {

    void create(Po po);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Po po) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Po findPo(Integer id);

    List<Po> findPoEntities();

    List<Po> findPoEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPoCount();
    
}
