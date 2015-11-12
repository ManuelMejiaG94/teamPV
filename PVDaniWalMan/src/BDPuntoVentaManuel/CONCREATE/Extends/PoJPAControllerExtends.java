/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IPoExtends;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Supplier;
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
public class PoJPAControllerExtends implements IPoExtends {
    private EntityManagerFactory emf=null;
    
    public PoJPAControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    @Override
    public List<BDPuntoVentaManuel.MODEL.Po> GetPoByStatus(int status)
    {
         try {
            EntityManager enm = getEntityManager();

            List<BDPuntoVentaManuel.MODEL.Po> listPo= (List<BDPuntoVentaManuel.MODEL.Po>) 
                    enm.createQuery("SELECT p FROM Po p WHERE p.bitEstatus = :status ").
                            setParameter("status",status)
                    .getResultList();
            return listPo;
        } catch (Exception e) {
            Logger.getLogger(PoJPAControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
     @Override
    public List<BDPuntoVentaManuel.MODEL.Po> GetPoByStatusAndSupplier(int status, Supplier supplier)
    {
         try {
            EntityManager enm = getEntityManager();

            List<BDPuntoVentaManuel.MODEL.Po> listPo= (List<BDPuntoVentaManuel.MODEL.Po>) 
                    enm.createQuery("SELECT p FROM Po p WHERE p.bitEstatus = :status AND p.idSupplier= :supplier").
                            setParameter("status",status).
                            setParameter("supplier", supplier)
                    .getResultList();
            return listPo;
        } catch (Exception e) {
            Logger.getLogger(PoJPAControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
