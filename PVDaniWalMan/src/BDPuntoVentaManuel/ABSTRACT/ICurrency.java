/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Currency;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ICurrency {

    void create(Currency currency);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Currency currency) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Currency findCurrency(Integer id);

    List<Currency> findCurrencyEntities();

    List<Currency> findCurrencyEntities(int maxResults, int firstResult);

    int getCurrencyCount();

    EntityManager getEntityManager();
    
}
