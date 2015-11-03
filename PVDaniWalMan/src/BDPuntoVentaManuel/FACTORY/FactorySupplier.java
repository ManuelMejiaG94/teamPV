/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ISupplier;
import BDPuntoVentaManuel.CONCREAT.SupplierJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.SupplierJpasControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISupplierExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactorySupplier {
        private static FactorySupplier factory;
    
    static {
        try {
            factory=FactorySupplier.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactorySupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactorySupplier getInstance()
    {   
        return factory;
    }
    
    public ISupplier getInstanceAbstract()
    {
        try{
            SupplierJpaController supplier= new SupplierJpaController();
            return (ISupplier) supplier;
        }catch(Exception ex)
        {
            Logger.getLogger(FactorySupplier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ISupplierExtends getInstanceEstends()
    {
        try{
            SupplierJpasControllerExtends supplier= new SupplierJpasControllerExtends();
            return (ISupplierExtends) supplier;
        }catch(Exception ex)
        {
            Logger.getLogger(FactorySupplier.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
