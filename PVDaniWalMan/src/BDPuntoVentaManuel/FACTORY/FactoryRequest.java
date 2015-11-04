/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IRequest;
import BDPuntoVentaManuel.CONCREAT.RequestJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.RequestJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IRequestExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryRequest {
    private static FactoryRequest factory;
    
    static {
        try {
            factory=FactoryRequest.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryRequest getInstance()
    {   
        return factory;
    }
    
    public IRequest getInstanceAbstract()
    {
        try{
            RequestJpaController request= new RequestJpaController();
            return (IRequest) request;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryRequest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public IRequestExtends getInstanceExtends()
    {
        try{
            RequestJpaControllerExtends request= new RequestJpaControllerExtends();
            return (IRequestExtends) request;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryRequest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
