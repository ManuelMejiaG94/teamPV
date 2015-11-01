/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.ABSTRACT.IRequest;
import BDPuntoVentaManuel.CONCREAT.RequestJpaController;
import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
        ctrRequest=new RequestJpaController();
    }
    
    public DefaultComboBoxModel getModelCategorias()
    {
        System.out.println("Estas dentro");
        return CategoriasProcess.GetComoBoxModelCategoria();
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
    
    
    //Variables
    Process_CatCategoria CategoriasProcess;
    
    //Controladoras
    IRequest ctrRequest;
}
