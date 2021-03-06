/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.CONCREAT.ProductJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.ProductoJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryProduct {
     private static FactoryProduct factory;
     
    
    static {
        try {
            factory=FactoryProduct.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryProduct getInstance()
    {   
        return factory;
    }
    
    public IProduct getInstanceAbstract()
    {
        try{
            ProductJpaController ctrlProducto= new ProductJpaController();
            return (IProduct) ctrlProducto;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
    
     public IProductExtends getInstanceAbstractExtends()
    {
        try{
            ProductoJpaControllerExtends ctrlProducto= new ProductoJpaControllerExtends();
            return (IProductExtends) ctrlProducto;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
     
      public IProductExtends getInstanceExtends()
    {
        try{
            ProductoJpaControllerExtends product= new ProductoJpaControllerExtends();
            return (IProductExtends) product;
        }catch(Exception ex)
        {
            Logger.getLogger(FactoryProduct.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}
}