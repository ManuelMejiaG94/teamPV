/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IuserExtends;
import BDPuntoVentaManuel.MODEL.Usuario;
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
public class LoginJPAControllerExtends implements IuserExtends {
 
    private EntityManagerFactory emf=null;
    
    public LoginJPAControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("PVDaniWalManPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    
    @Override
    public Usuario findUsuario(String Nombre, String Pass ) {
      try{
          System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddd"+Nombre+" contrasenia "+Pass);
            EntityManager em = getEntityManager();
            List<Usuario>  User=(List<Usuario>)em.createQuery("SELECT u FROM Usuario u WHERE u.nombre=:nombre AND u.contraseña=:contraseña")
                    .setParameter("nombre",Nombre).setParameter("contraseña",Pass)
                    .getResultList();
            
            em.close();
            if(User.size()>0)
            {
                return User.get(0);
            }else
            {
                return null;
            }
            
        }catch(Exception e)
        {
            //Logger.getLogger(LoginJPAControllerExtends.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error encontrado\n"+e.getMessage());
            return null;
        }
    }
}
