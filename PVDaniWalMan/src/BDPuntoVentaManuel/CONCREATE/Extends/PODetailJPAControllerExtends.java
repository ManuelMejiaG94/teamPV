/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IPoDetailExtends;
import BDPuntoVentaManuel.MODEL.Po;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PODetailJPAControllerExtends implements IPoDetailExtends {
     private EntityManagerFactory emf=null;
     
     public PODetailJPAControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    
    @Override
    public List<BDPuntoVentaManuel.MODEL.Podetail> GetPoDetaiByPo(Po po)
    {
         try {
            EntityManager enm = getEntityManager();

            List<BDPuntoVentaManuel.MODEL.Podetail> listPo= (List<BDPuntoVentaManuel.MODEL.Podetail>) 
                    enm.createQuery("SELECT p FROM Podetail p WHERE p.idPo = :po ").
                            setParameter("po",po)
                    .getResultList();
            return listPo;
        } catch (Exception e) {
            Logger.getLogger(PODetailJPAControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
}
