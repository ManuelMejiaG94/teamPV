/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ViewsProcess;

import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.CONCREAT.ProductJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.ProductoJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.MODEL.Catcategoria;

import BDPuntoVentaManuel.MODEL.Product;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author manuel
 */
public class Process_Products {
    public Process_Products()
    {
        this.Start_tools();
    }
    
    private void Start_tools()
    {
        ctrProduct=new FactoryProduct().getInstanceExtends();
        
    }
    
    public DefaultComboBoxModel GetComboBoxModelProducts(Catcategoria categori)
    {
        Vector<Product> dataModel=new Vector<Product>();
        Product defaultModel=new Product();
        defaultModel.setId(-1);
        defaultModel.setStrName("Seleccionar");
        
        dataModel.add(defaultModel);
        
        if(categori !=null)
        {
            List<Product> listProducts=this.ctrProduct.FindDataByCategoriaId(categori);
            for ( Product item : listProducts ) {
            dataModel.add(item);
            }
        }
        
        DefaultComboBoxModel model=new DefaultComboBoxModel(dataModel);
        return model;
    }
    
    //Controladoras
    IProductExtends ctrProduct=new ProductoJpaControllerExtends();
    
}
