/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ViewsProcess;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISupplierExtends;
import BDPuntoVentaManuel.FACTORY.FactorySupplier;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Supplier;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author manuel
 */
public class Process_Suppliers {
     public Process_Suppliers()
    {
        this.Start_tools();
    }
    
    private void Start_tools()
    {
        ctrSupplier=new FactorySupplier().getInstanceExtends();
        
    }
    
    public DefaultComboBoxModel GetComoBoxModelSupplier(Catcategoria categori)
    {
        Vector<Supplier> dataModel=new Vector<Supplier>();
        Supplier defaultModel=new Supplier();
        defaultModel.setId(-1);
        defaultModel.setStrBussinessName("Seleccionar");
        defaultModel.setIdCategoria(categori);
        
        dataModel.add(defaultModel);
        
        if(categori != null)
        {
            List<Supplier> listSuppliers=this.ctrSupplier.FindDataByCategoriaId(categori);    
            for ( Supplier item : listSuppliers ) {
                
                if(item.getBoolEstatus())
                {
                    dataModel.add(item);
                }
            }
        }
        
        DefaultComboBoxModel model=new DefaultComboBoxModel(dataModel);
        return model;
    }
    
      public DefaultComboBoxModel GetComoBoxModelSupplierDefault(Catcategoria categori)
    {
        
        Vector<Supplier> dataModel=new Vector<Supplier>();
        Supplier defaultModel=new Supplier();
        defaultModel.setId(-1);
        defaultModel.setStrBussinessName("Seleccionar");
        defaultModel.setIdCategoria(categori);
        dataModel.add(defaultModel);
        
        DefaultComboBoxModel model=new DefaultComboBoxModel(dataModel);
        return model;
    }
    
    //Controladoras
    private ISupplierExtends ctrSupplier;
}
