/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.ABSTRACT.ICurrency;
import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.ABSTRACT.IRequest;
import BDPuntoVentaManuel.ABSTRACT.IRequestDetail;
import BDPuntoVentaManuel.CONCREAT.ProductJpaController;
import BDPuntoVentaManuel.CONCREAT.RequestJpaController;
import BDPuntoVentaManuel.CONCREAT.RequestdetailJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.ProductoJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.Extends.RequestJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IRequestExtends;
import BDPuntoVentaManuel.FACTORY.FactoryCurrency;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.FACTORY.FactoryRequest;
import BDPuntoVentaManuel.FACTORY.FactoryRequestDetail;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Currency;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Requestdetail;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.ViewsProcess.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author manuel
 */
public class Request {
    
    //Notas
    /**
     * Los estatus de una solicitud son 3 
     * 1.- Creada;
     * 2.- Cerrada;
     * 3.- Cancelada/Rechazada;
     */
    
    
    public Request()
    {
        this.start_tools();
    }
    private void start_tools()
    {
        CategoriasProcess=new Process_CatCategoria();
        productProcess=new Process_Products();
        prosesSuppliers=new Process_Suppliers();
        
        ctrRequest=new FactoryRequest().getInstanceAbstract();
        ctrRequestExtends=new FactoryRequest().getInstanceExtends();
        ctrProduct=new FactoryProduct().getInstanceAbstract();
        ctrRequestDetail=new FactoryRequestDetail().getInstanceAbstract();
        ctrProductExtends=new FactoryProduct().getInstanceExtends();
        
        ctrCurrency=new FactoryCurrency().getInstanceAbstract();
        
    }
    
    public DefaultComboBoxModel getModelCategorias()
    {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }
    
    public DefaultComboBoxModel getModelSuppliers(Catcategoria categori)
    {
        return prosesSuppliers.GetComoBoxModelSupplier(categori);
    }
    public DefaultComboBoxModel getModelProducts(Catcategoria categori)
    {
        return productProcess.GetComboBoxModelProducts(categori);
    }
     public DefaultComboBoxModel getModelSuppliersDefault(Catcategoria categori)
    {
        return prosesSuppliers.GetComoBoxModelSupplier(categori);
    }
    public DefaultComboBoxModel getModelProductsDefault(Catcategoria categori)
    {
        return productProcess.GetComboBoxModelProducts(categori);
    }
    
    public void ChargeDataDefault(DefaultTableModel model) {
        List<BDPuntoVentaManuel.MODEL.Request> listRequest = ctrRequest.findRequestEntities(100, 1);
        Object[] data = new Object[5];
        String status="";

        for (BDPuntoVentaManuel.MODEL.Request item : listRequest) {
            data[0] = item.getId();
            data[1] = item.getIdSuplier().getStrBussinessName();
            data[2] = item.getDatFecha();
            data[3] = item.getDoubTotal();
            data[4] = this.StatusRequest(item);

            model.addRow(data);
        }

    }
    
    private String StatusRequest(BDPuntoVentaManuel.MODEL.Request request)
    {
        String status="";
        
        switch(request.getBitEstatus())
        {
            case 1:
                status="Creada";
                break;
            case 2:
                status="Cerrada";
                break;
            case 3:
                status="Cancelada";
                break;
        }
        
        return status;
    }
    
    public String GetFolio()
    {
        String folio="";
        
        folio=String.valueOf(ctrRequestExtends.lastId());
        
        return folio;
    }
    
    public double SetDataTableNewUpdate(DefaultTableModel model, Product product,int stock,JLabel errorMessage)
    {
        try{
            this.startTransaccion();
            if(product.getIntStock()>stock)
            {
                product.setIntStock(product.getIntStock()-stock);
                ctrProduct.edit(product);
                
                Object []dataProduct=new Object[6];
        
                double total=product.getDonPC()*stock;
                dataProduct[0]=product.getStrClave();
                dataProduct[1]=product.getStrName();
                dataProduct[2]=product.getDonPC();
                dataProduct[3]=product.getDobPV();
                dataProduct[4]=stock;
                dataProduct[5]=total;
                
                model.addRow(dataProduct);
                
                
                return total;    
            }else{
                
                errorMessage.setText("No cuenta con suficientes unidades\nPara surtir el producto");
            }
        }catch(Exception e)
        {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
            CancelTransaccion();
        }
        return 0;
    }
    
    public boolean SaveRequest(List<String[]> productCodes,BDPuntoVentaManuel.MODEL.Request request,double total)
    {
        try
        {
            //ctrRequest.getEntityManager().getTransaction().begin();
        Currency currency=ctrCurrency.findCurrency(1);
        
        request.setIdCurrency(currency);
        ctrRequest.create(request);
        
        for (int i = 0; i < productCodes.size(); i++) {
            Requestdetail requestDetail=new Requestdetail();
            Product product=ctrProductExtends.findProductByCode(productCodes.get(i)[0].toString());
            requestDetail.setIdRequest(request);
            requestDetail.setIdProduct(product);
            requestDetail.setDobPrice(Double.parseDouble(productCodes.get(i)[1].toString()));
            requestDetail.setDobQuantity(Double.parseDouble(productCodes.get(i)[2].toString()));
            requestDetail.setDobTotal(Double.parseDouble(productCodes.get(i)[3].toString()));

            ctrRequestDetail.create(requestDetail);
        }
        
        //ctrRequest.getEntityManager().getTransaction().commit();
        return true;
        }catch(Exception e)
        {
            ctrRequest.getEntityManager().getTransaction().rollback();
        }
        return false;

    }
    
    
    
    //Variables
    Process_CatCategoria CategoriasProcess;
    Process_Products productProcess;
    Process_Suppliers prosesSuppliers;
    
    //Controladoras
    IRequest ctrRequest;
    IRequestExtends ctrRequestExtends;
    IProduct ctrProduct;
    IRequestDetail ctrRequestDetail;
    IProductExtends ctrProductExtends;
    ICurrency ctrCurrency;
    
    private void startTransaccion()
    {
        //Iniciamos la transaccion
        ctrProduct.getEntityManager().getTransaction().begin();
    }
    private void finishTransaccion()
    {
        //Finalizamos la transaccion
        ctrProduct.getEntityManager().getTransaction().commit();
    }
    public void CancelTransaccion()
    {
        //Cancelamos la transaccion
        ctrProduct.getEntityManager().getTransaction().rollback();
    }
}
