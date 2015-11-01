/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ISales;
import BDPuntoVentaManuel.CONCREAT.SalesJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactorySales {
     private static FactorySales factory;
    
    static {
        try {
            factory=FactorySales.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactorySales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactorySales getInstance()
    {   
        return factory;
    }
    
    public ISales getInstanceAbstract()
    {
        try{
            SalesJpaController sales= new SalesJpaController();
            return (ISales) sales;
        }catch(Exception ex)
        {
            Logger.getLogger(FactorySales.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
