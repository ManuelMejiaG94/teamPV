/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ICatCategori;
import BDPuntoVentaManuel.CONCREAT.CatcategoriaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryCategoria {
    private static FactoryCategoria factory;
    
    static {
        try {
            factory=FactoryCategoria.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryCategoria getInstance()
    {   
        return factory;
    }
    
    public ICatCategori getInstanceAbstract()
    {
        try{
            CatcategoriaJpaController categoria= new CatcategoriaJpaController();
            return (ICatCategori) categoria;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryCategoria.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
