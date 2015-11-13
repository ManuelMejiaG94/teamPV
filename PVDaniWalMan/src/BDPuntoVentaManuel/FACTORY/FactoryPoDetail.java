/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPoDetail;
import BDPuntoVentaManuel.CONCREAT.PodetailJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.PODetailJPAControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IPoDetailExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPoDetail {
    private static FactoryPoDetail factory;
    
    static {
        try {
            factory=FactoryPoDetail.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryPoDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPoDetail getInstance()
    {   
        return factory;
    }
    
    public IPoDetail getInstanceAbstract()
    {
        try{
            PodetailJpaController PoDetail= new PodetailJpaController();
            return (IPoDetail) PoDetail;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryPoDetail.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      public IPoDetailExtends getInstanceExtends()
    {
        try{
            PODetailJPAControllerExtends PoDetail= new PODetailJPAControllerExtends();
            return (IPoDetailExtends) PoDetail;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryPoDetail.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
