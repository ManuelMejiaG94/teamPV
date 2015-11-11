/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISalesExtends;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class SalesJpaControllerExtends implements ISalesExtends {
    
     private EntityManagerFactory emf=null;
    
    public SalesJpaControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    @Override
     public int lastId()
    {
           try {
            EntityManager enm = getEntityManager();

            int id = (int) enm.createQuery("SELECT max(s.id) FROM Sales s")
                    .getSingleResult();
            return id+1;
        } catch (Exception e) {
            Logger.getLogger(SalesJpaControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }
    
    
}
