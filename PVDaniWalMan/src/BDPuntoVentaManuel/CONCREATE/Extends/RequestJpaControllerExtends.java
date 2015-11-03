/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IRequestExtends;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class RequestJpaControllerExtends implements IRequestExtends {
    
     private EntityManagerFactory emf=null;
    
    public RequestJpaControllerExtends()
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

            int id = (int) enm.createQuery("SELECT max(r.id) FROM Request r")
                    .getSingleResult();
            return id+1;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return 0;
        }
    }
    
}
