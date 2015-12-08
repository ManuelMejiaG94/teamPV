/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IUsuario;
import BDPuntoVentaManuel.CONCREAT.UsuarioJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.LoginJPAControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IuserExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryLogin {
     private static FactoryLogin factory;
    
    static {
        try {
            factory=FactoryLogin.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryLogin getInstance()
    {   
        return factory;
    }
    
    public IUsuario getInstanceAbstract()
    {
        try{
            UsuarioJpaController user= new UsuarioJpaController();
            return (IUsuario) user;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryCurrency.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public IuserExtends getInstanceExtends()
    {
        try{
            LoginJPAControllerExtends user= new LoginJPAControllerExtends();
            return (IuserExtends) user;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryCurrency.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
