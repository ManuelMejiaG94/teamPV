/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IRequestExtends;
import BDPuntoVentaManuel.MODEL.Supplier;
import java.util.List;
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
    
    public List<BDPuntoVentaManuel.MODEL.Request> RetriveRequestBySupplierAndStatus
        (Supplier supplier, int statusRequest)
    {
        //System.out.println("Estatus es "+statusRequest);
           try {
            EntityManager enm = getEntityManager();

            List<BDPuntoVentaManuel.MODEL.Request> listRequest= (List<BDPuntoVentaManuel.MODEL.Request>) enm.createQuery("SELECT r FROM Request r WHERE r.idSuplier=:supplier "
                    + "AND r.bitEstatus=:status").setParameter("supplier",supplier)
                    .setParameter("status",statusRequest)
                    .getResultList();
            return listRequest;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
        }
    }

    public List<BDPuntoVentaManuel.MODEL.Request> RetriveRequestByFolio(int folio)
    {try {
            EntityManager enm = getEntityManager();

            List<BDPuntoVentaManuel.MODEL.Request> listRequest = (List<BDPuntoVentaManuel.MODEL.Request>) enm.createQuery(
                    "select r from Request r wHERE r.id LIKE :idb")
                    .setParameter("idb",folio)
                    .getResultList();
            return listRequest;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
}
