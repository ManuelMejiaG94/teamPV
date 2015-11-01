/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IRequestDetail;
import BDPuntoVentaManuel.CONCREAT.RequestJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryRequestDetail {
    private static FactoryRequestDetail factory;
    
    static {
        try {
            factory=FactoryRequestDetail.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryRequestDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryRequestDetail getInstance()
    {   
        return factory;
    }
    
    public IRequestDetail getInstanceAbstract()
    {
        try{
            RequestJpaController requestDetail= new RequestJpaController();
            return (IRequestDetail) requestDetail;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryRequestDetail.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
