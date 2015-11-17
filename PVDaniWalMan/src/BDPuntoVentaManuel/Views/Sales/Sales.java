/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Sales;

import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.ABSTRACT.ISales;
import BDPuntoVentaManuel.ABSTRACT.ISalesDetail;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISalesExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.FACTORY.FactorySales;
import BDPuntoVentaManuel.FACTORY.FactorySalesDetail;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Salesdetail;
import java.util.List;
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
        ctrSalesDetail=new FactorySalesDetail().getInstanceAbstract();
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
    
     public void RestarListProductsStock(List<Product> listproduct)
    {
        try{
            for(Product product: listproduct)
            {
                double stockProduct=product.getIntStock();
                Product itemUpdate=ctrProduct.findProduct(product.getId());
                
                itemUpdate.setIntStock(itemUpdate.getIntStock()+stockProduct);
            
                ctrProduct.edit(itemUpdate);
            }
            
        }catch(Exception e)
        {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public boolean CreateNewSale(BDPuntoVentaManuel.MODEL.Sales _sale,List<String[]> listProductObject)
    {
        try{
            ctrSales.create(_sale);
            for (int i=0 ; i<listProductObject.size(); i++) {
                BDPuntoVentaManuel.MODEL.Salesdetail salesDetails=new Salesdetail();
                Product product=GetProductByClave(listProductObject.get(i)[0]);
                
                salesDetails.setIdSales(_sale);
                salesDetails.setIdProducto(product);
                salesDetails.setIntquantity(Integer.parseInt(listProductObject.get(i)[2]));
                salesDetails.setDobPrice(Double.parseDouble(listProductObject.get(i)[1]));
                salesDetails.setDobTotal(Double.parseDouble(listProductObject.get(i)[3]));
                ctrSalesDetail.create(salesDetails);
            }
            
            return true;
        }catch(Exception e)
        {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    
    //Controladora
    private ISales ctrSales;
    private ISalesDetail ctrSalesDetail;
    private IProduct ctrProduct;
    private IProductExtends ctrProductExtends;
    private ISalesExtends ctrSalesExtends;
    
    
}
