/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.PurchaseOrder;

import BDPuntoVentaManuel.ABSTRACT.IPO;
import BDPuntoVentaManuel.CONCREAT.PoJpaController;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IPoExtends;
import BDPuntoVentaManuel.FACTORY.FactoryPo;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import BDPuntoVentaManuel.ViewsProcess.Process_Suppliers;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class PO {
    
    public PO()
    {
        startTools();
    }
    
    private void startTools()
    {
        CategoriasProcess=new Process_CatCategoria();
        SupplierProcess=new Process_Suppliers();
        ctrPo=new FactoryPo().getInstanceAbstract();
        ctrPoExtends=new FactoryPo().getInstanceExtends();
    }
    
     public DefaultComboBoxModel getModelCategorias() {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }
    public DefaultComboBoxModel getModelSuppliers(Catcategoria categori) {
        return SupplierProcess.GetComoBoxModelSupplier(categori);
    }
    public DefaultComboBoxModel getModelSuppliersDefault(Catcategoria categori) {
        return SupplierProcess.GetComoBoxModelSupplierDefault(categori);
    }
     
     public void PainDefaultDataTable(DefaultTableModel model)    
     {
         List<BDPuntoVentaManuel.MODEL.Po> listPo= ctrPo.findPoEntities(50,1);
         Object []data=new Object[5];
         SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
         if(listPo.size()>0)
         {
         for (BDPuntoVentaManuel.MODEL.Po itemPo : listPo) {
             data[0]=itemPo.getId();
             data[1]=itemPo.getIdSupplier().getStrBussinessName();
             data[2]=date.format(itemPo.getDatFechaGenerada());
             data[3]=itemPo.getDobTotal();
             data[4]=this.GetStatus(itemPo.getBitEstatus());
             
             model.addRow(data);
         }
         }
     }
    
     public void PainDataTableByStatus(DefaultTableModel model,int status)    
     {
         List<BDPuntoVentaManuel.MODEL.Po> listPo= ctrPoExtends.GetPoByStatus(status);
         Object []data=new Object[5];
         SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
         for (BDPuntoVentaManuel.MODEL.Po itemPo : listPo) {
             data[0]=itemPo.getId();
             data[1]=itemPo.getIdSupplier().getStrBussinessName();
             data[2]=date.format(itemPo.getDatFechaGenerada());
             data[3]=itemPo.getDobTotal();
             data[4]=this.GetStatus(itemPo.getBitEstatus());
             
             model.addRow(data);
         }
     }
     private String GetStatus(int bitStatus)
     {
          String status="";
        
        switch(bitStatus)
        {
            case 1:
                status="Creada";
                break;
            case 2:
                status="Cerrada";
                break;
        }
        
        return status;
     }
    public void PainDataTableByStatusAndSupplier(DefaultTableModel model,int status, Supplier supplier)    
     {
         List<BDPuntoVentaManuel.MODEL.Po> listPo= ctrPoExtends.GetPoByStatusAndSupplier(status, supplier);
         Object []data=new Object[5];
         SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
         for (BDPuntoVentaManuel.MODEL.Po itemPo : listPo) {
             data[0]=itemPo.getId();
             data[1]=itemPo.getIdSupplier().getStrBussinessName();
             data[2]=date.format(itemPo.getDatFechaGenerada());
             data[3]=itemPo.getDobTotal();
             data[4]=this.GetStatus(itemPo.getBitEstatus());
             
             model.addRow(data);
         }
     }
    //Controladoras
    private IPO ctrPo;
    private IPoExtends ctrPoExtends;
    
     //Estatus de las ordenes de compra
    private int pocreate=1;
    private int poClosed=2;
     
     //Apoyos
     Process_CatCategoria CategoriasProcess;
     Process_Suppliers SupplierProcess;
}
