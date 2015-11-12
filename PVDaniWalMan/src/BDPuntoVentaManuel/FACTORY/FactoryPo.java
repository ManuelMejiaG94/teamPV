/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPO;
import BDPuntoVentaManuel.CONCREAT.PoJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.PoJPAControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IPoExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPo {
    private static FactoryPo factory;
    
    static {
        try {
            factory=FactoryPo.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryPo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPo getInstance()
    {   
        return factory;
    }
    
    public IPO getInstanceAbstract()
    {
        try{
            PoJpaController po= new PoJpaController();
            return (IPO) po;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryPo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public IPoExtends getInstanceExtends()
    {
        try{
            PoJPAControllerExtends po= new PoJPAControllerExtends();
            return (IPoExtends) po;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryPo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
