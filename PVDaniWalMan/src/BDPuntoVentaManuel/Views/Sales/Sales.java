/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Sales;

import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.ABSTRACT.ISales;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISalesExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.FACTORY.FactorySales;
import BDPuntoVentaManuel.MODEL.Product;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Sales {
    
    public Sales()
    {
     this.startTools();   
    }
    
    public void startTools()
    {
        ctrSales= new FactorySales().getInstanceAbstract();
        ctrProductExtends=new FactoryProduct().getInstanceAbstractExtends();
        ctrSalesExtends=new FactorySales().getInstanceExtends();
        ctrProduct=new FactoryProduct().getInstanceAbstract();
    }
    
    public Product GetProductByClave(String clave)
    {
        Product product=ctrProductExtends.findProductByCode(clave);
        
        return product;
    }
    
    public int GetFolio()
    {
        return ctrSalesExtends.lastId();
    }
    
    public void AddProductToSale(DefaultTableModel model, Product product, int amount)
    {
        try{
        Object []data=new Object[6];
        
        data[0]=product.getStrClave();
        data[1]=product.getStrName();
        data[2]=amount;
        data[3]=product.getStrPresentation();
        data[4]=product.getDobPV();
        data[5]=product.getDobPV()*amount;
        
        model.addRow(data);
        product.setIntStock(product.getIntStock()-amount);
        
        ctrProduct.edit(product);
        }catch(Exception e)
        {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void RestarProductStock(Product product, int stock)
    {
        try{
            product.setIntStock(product.getIntStock()+stock);
            
            ctrProduct.edit(product);
            
        }catch(Exception e)
        {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    //Controladora
    private ISales ctrSales;
    private IProduct ctrProduct;
    private IProductExtends ctrProductExtends;
    private ISalesExtends ctrSalesExtends;
    
}
