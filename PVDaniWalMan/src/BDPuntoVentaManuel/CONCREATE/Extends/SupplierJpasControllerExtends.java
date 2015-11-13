/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISupplierExtends;
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
public class SupplierJpasControllerExtends implements ISupplierExtends {
    private EntityManagerFactory emf=null;
    
    public SupplierJpasControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
        
     public List<Supplier> FindDataByFirstLetter(String letter)
    {
         try {
            EntityManager enm = getEntityManager();

            List<Supplier> listAlumno = (List<Supplier>) enm.createQuery(
                    "select s from Supplier s wHERE s.strBussinessName LIKE :letter")
                    .setParameter("letter","%"+letter+"%")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error es "+ex.getMessage());
            return null;
            }
    }
    
    @Override
    public List<Supplier> FindDataByCategoriaId(Catcategoria categoria)
    {
        try{
            EntityManager em = getEntityManager();
            List<Supplier>  suppliers=em.createQuery("SELECT s FROM Supplier s WHERE s.idCategoria= :categoriaId")
                    .setParameter("categoriaId",categoria).getResultList();
            
            em.close();
            return suppliers;
        }catch(Exception e)
        {
            Logger.getLogger(ProductoJpaControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public List<Supplier> findDataDefault() {
        try {
            EntityManager enm = getEntityManager();

            List<Supplier> listAlumno = (List<Supplier>) enm.createQuery("select s from Supplier s")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error es "+ex.getMessage());
            return null;
            }
    }

    @Override
    public Supplier findSupplierByCode(String strClave) {
       try {
            EntityManager enm = getEntityManager();

            Supplier supplier = (Supplier) enm.createQuery("SELECT s FROM Supplier s WHERE s.strClave = :strClave")
                    .setParameter("strClave", strClave).getSingleResult();
            return supplier;
        } catch (Exception ex) {
            System.out.println("Error es "+ex.getMessage());
            return null;
            }
    }
}
