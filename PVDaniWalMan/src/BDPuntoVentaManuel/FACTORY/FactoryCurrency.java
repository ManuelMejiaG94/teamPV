/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ICurrency;
import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.CONCREAT.CurrencyJpaController;
import BDPuntoVentaManuel.CONCREAT.PersonaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryCurrency {
    private static FactoryCurrency factory;
    
    static {
        try {
            factory=FactoryCurrency.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCurrency.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryCurrency getInstance()
    {   
        return factory;
    }
    
    public ICurrency getInstanceAbstract()
    {
        try{
            CurrencyJpaController currency= new CurrencyJpaController();
            return (ICurrency) currency;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryCurrency.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
