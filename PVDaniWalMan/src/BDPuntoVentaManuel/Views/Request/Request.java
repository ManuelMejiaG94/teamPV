/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.ABSTRACT.ICurrency;
import BDPuntoVentaManuel.ABSTRACT.IPO;
import BDPuntoVentaManuel.ABSTRACT.IPoDetail;
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
import BDPuntoVentaManuel.FACTORY.FactoryPo;
import BDPuntoVentaManuel.FACTORY.FactoryPoDetail;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.FACTORY.FactoryRequest;
import BDPuntoVentaManuel.FACTORY.FactoryRequestDetail;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Currency;
import BDPuntoVentaManuel.MODEL.Po;
import BDPuntoVentaManuel.MODEL.Podetail;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Requestdetail;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.ViewsProcess.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
        ctrPo=new FactoryPo().getInstanceAbstract();
        ctrPoDetail=new FactoryPoDetail().getInstanceAbstract();
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
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        

        for (BDPuntoVentaManuel.MODEL.Request item : listRequest) {
            if(item.getBitEstatus()==1)
            {
                data[0] = item.getId();
            data[1] = item.getIdSuplier().getStrBussinessName();
            data[2] = date.format(item.getDatFecha());
            data[3] = item.getDoubTotal();
            data[4] = this.StatusRequest(item);

            model.addRow(data);
            }
        }

    }
    
    public BDPuntoVentaManuel.MODEL.Request GetRequestByFolio(int folio)
    {
//        List<BDPuntoVentaManuel.MODEL.Request> listRequest = ctrRequestExtends.RetriveRequestByFolio(folio);        
//        return listRequest.get(0);
        return ctrRequest.findRequest(folio);
    }
    
    public void ChargeDataByFolio(DefaultTableModel model, int folio) {
        List<BDPuntoVentaManuel.MODEL.Request> listRequest = ctrRequestExtends.RetriveRequestByFolio(folio);
        Object[] data = new Object[5];
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        for (BDPuntoVentaManuel.MODEL.Request item : listRequest) {
            data[0] = item.getId();
            data[1] = item.getIdSuplier().getStrBussinessName();
            data[2] = date.format(item.getDatFecha());
            data[3] = item.getDoubTotal();
            data[4] = this.StatusRequest(item);

            model.addRow(data);
        }
    }
    
     public void ChargeDataByFilter(DefaultTableModel model,Supplier supplier,int statusId) {
        List<BDPuntoVentaManuel.MODEL.Request> listRequest = ctrRequestExtends.RetriveRequestBySupplierAndStatus(supplier, statusId+1);
        Object[] data = new Object[5];
        String status="";
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        for (BDPuntoVentaManuel.MODEL.Request item : listRequest) {
            data[0] = item.getId();
            data[1] = item.getIdSuplier().getStrBussinessName();
            data[2] = date.format(item.getDatFecha());
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
            case 4:
                status="odc generada";
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
            //this.startTransaccion();
//            if(product.getIntStock()>stock)
//            {
                //product.setIntStock(product.getIntStock()-stock);
                //ctrProduct.edit(product);
                
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
//            }else{
//                
//                errorMessage.setText("No cuenta con suficientes unidades\nPara surtir el producto");
//            }
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
            requestDetail.setBolAssigned(false);

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
    
    public boolean Reset_Stock(String strClave, int stock) 
    {
        try{
        Product product=ctrProductExtends.findProductByCode(strClave);
        product.setIntStock(product.getIntStock()+stock);
        
        ctrProduct.edit(product);
        return true;
        }catch(Exception e)
        {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    public boolean Cancel_Request(int folio)
    {
        try{
            BDPuntoVentaManuel.MODEL.Request request= GetRequestByFolio(folio);
            request.setBitEstatus(Cancel);
            this.ctrRequest.edit(request);
            return true;
        }catch(Exception e)
        {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public boolean CreateOdc(int folio) {
        try {
            BDPuntoVentaManuel.MODEL.Request request = ctrRequest.findRequest(folio);

            BDPuntoVentaManuel.MODEL.Po po = new Po();

            po.setBitEstatus(pocreate);
            po.setDobTotal(request.getDoubTotal());
            po.setDatFechaGenerada(request.getDatFecha());
            po.setIdCurrency(request.getIdCurrency());
            po.setIdSupplier(request.getIdSuplier());
            ctrPo.create(po);

            List<Requestdetail> listRequestDetails = (List<Requestdetail>) request.getRequestdetailCollection();

            for (Requestdetail requestdetail : listRequestDetails) {
                Podetail poDetail = new Podetail();

                poDetail.setDobPc(requestdetail.getDobPrice());
                poDetail.setDobQuantity(requestdetail.getDobQuantity());
                poDetail.setDobTotal(requestdetail.getDobTotal());
                poDetail.setIdPo(po);
                poDetail.setIdProducto(requestdetail.getIdProduct());

                ctrPoDetail.create(poDetail);
                
                requestdetail.setBolAssigned(true);
                ctrRequestDetail.edit(requestdetail);
            }

            request.setBitEstatus(Asepted);
            ctrRequest.edit(request);

            return true;
        } catch (Exception e) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }
    
    public DefaultListModel getModelListProduct(BDPuntoVentaManuel.MODEL.Request request)
    {
        DefaultListModel lm=new DefaultListModel();
        List<BDPuntoVentaManuel.MODEL.Requestdetail> listRequestDetails=(List<BDPuntoVentaManuel.MODEL.Requestdetail>) request.getRequestdetailCollection();
        
         for ( BDPuntoVentaManuel.MODEL.Requestdetail item : listRequestDetails ) {
             if(!item.getBolAssigned())
             {
            lm.addElement(item);
             }
       }
         return lm;
    }
    public DefaultListModel getModelListProductNull()
    {
        DefaultListModel lm=new DefaultListModel();
       
         return lm;
    }
    
    public boolean CreatePartialPO(List<Requestdetail> _listRequestdetails, int folio) {
        try {
            BDPuntoVentaManuel.MODEL.Request request = ctrRequest.findRequest(folio);

            BDPuntoVentaManuel.MODEL.Po po = new Po();

            po.setBitEstatus(pocreate);
            
            po.setIdCurrency(request.getIdCurrency());
            po.setIdSupplier(request.getIdSuplier());
            po.setDatFechaGenerada(request.getDatFecha());
            ctrPo.create(po);

            List<Requestdetail> listRequestDetails = (List<Requestdetail>) request.getRequestdetailCollection();
            double total=0;
            List<Podetail> podetails=new ArrayList<Podetail>();

            for (Requestdetail requestdetail : listRequestDetails) {
                int a=0;
                for (int i = a; i < _listRequestdetails.size(); i++) {
                    Requestdetail requestDetails = (Requestdetail) _listRequestdetails.get(i);
                    if (requestdetail.getId() == requestDetails.getId()) {
                        Podetail poDetail = new Podetail();

                        poDetail.setDobPc(requestdetail.getDobPrice());
                        poDetail.setDobQuantity(requestdetail.getDobQuantity());
                        poDetail.setDobTotal(requestdetail.getDobTotal());
                        total=total+requestdetail.getDobTotal();
                        poDetail.setIdPo(po);
                        poDetail.setIdProducto(requestdetail.getIdProduct());

                        requestdetail.setBolAssigned(true);

                        ctrRequestDetail.edit(requestdetail);
                        ctrPoDetail.create(poDetail);
                        podetails.add(poDetail);
                    }
                }
                a++;
            }
            po.setDobTotal(total);
            po.setPodetailCollection(podetails);
            ctrPo.edit(po);

            request.setBitEstatus(this.ValidateStatusForPartialPo(listRequestDetails));
            ctrRequest.edit(request);
            return true;
        } catch (Exception e) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private int ValidateStatusForPartialPo(List<BDPuntoVentaManuel.MODEL.Requestdetail> listRequestDetails)
    {
        
        int datatrue=0;
        for (int i = 0; i < listRequestDetails.size(); i++) {
            if(listRequestDetails.get(i).getBolAssigned())
            {
                datatrue++;
            }
        }
        if(listRequestDetails.size()==datatrue)
        {
            return Asepted;
        }
        
        return Processed;
    }
    
    
    //Variables Status de las solicitudes
    private int create=1;
    private int Asepted=2;
    private int Cancel=3;
    private int Processed=4;
    
    //Estatus de las ordenes de compra
    private int pocreate=1;
    private int poClosed=2;
    
    
    //Variables de proceso
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
    IPO ctrPo;
    IPoDetail ctrPoDetail;
    
    
    
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
