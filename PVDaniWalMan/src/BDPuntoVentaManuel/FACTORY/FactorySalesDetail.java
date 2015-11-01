/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ISalesDetail;
import BDPuntoVentaManuel.CONCREAT.SalesdetailJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactorySalesDetail {
     private static FactorySalesDetail factory;
    
    static {
        try {
            factory=FactorySalesDetail.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactorySalesDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactorySalesDetail getInstance()
    {   
        return factory;
    }
    
    public ISalesDetail getInstanceAbstract()
    {
        try{
            SalesdetailJpaController SalesDetail= new SalesdetailJpaController();
            return (ISalesDetail) SalesDetail;
        }catch(Exception ex)
        {
            Logger.getLogger(FactorySalesDetail.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
