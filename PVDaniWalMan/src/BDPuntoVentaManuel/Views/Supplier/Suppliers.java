/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Supplier;

import BDPuntoVentaManuel.ABSTRACT.IContact;
import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.ABSTRACT.ISupplier;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.ISupplierExtends;
import BDPuntoVentaManuel.FACTORY.FactoryContact;
import BDPuntoVentaManuel.FACTORY.FactoryPerson;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.FACTORY.FactorySupplier;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Persona;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MANCERA
 */
public class Suppliers {
    
    public Suppliers() {
        this.start_tools();
    }
    
    private void start_tools() {
        CategoriasProcess = new Process_CatCategoria();
        ctrlSupplier = new FactorySupplier().getInstanceAbstractExtends();
        ctrlSupplierDefault = new FactorySupplier().getInstanceAbstract();
        ctrContact=new FactoryContact().getInstanceAbstract();
        ctrPersona=new FactoryPerson().getInstanceAbstract();
    }
    
    public DefaultComboBoxModel getModelCategorias() {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }
    
    public void ChargeDataDefault(DefaultTableModel model) {
        List<Supplier> listSuppliers = ctrlSupplier.findDataDefault();
        Object[] data = new Object[7];

        for (Supplier item : listSuppliers) {
            data[0] = item.getStrClave();
            data[1] = item.getStrBussinessName();
            data[2] = item.getStrNumber();
            data[3] = item.getStrDescripcion();
            data[4] = item.getIdContacto();
            data[5] = item.getIdCategoria();
            
            if(item.getBoolEstatus())
            {
                data[6] = "activo";
            }else{
                data[6] = "inactivo";
            }
            

            model.addRow(data);
        }

    }
    public boolean SaveSupplier(Supplier supplier,Contacto contact,Persona person ) {
        try{
        ctrPersona.create(person);
        contact.setIdPersona(person);
        ctrContact.create(contact);
        supplier.setIdContacto(contact);
        ctrlSupplierDefault.create(supplier);
        return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public void ChargeDataByCategoriaId(DefaultTableModel model, Catcategoria categoria) {
        List<Supplier> listSuppliers = ctrlSupplier.FindDataByCategoriaId(categoria);
        Object[] data = new Object[7];

        for (Supplier item : listSuppliers) {
            data[0] = item.getStrClave();
            data[1] = item.getStrBussinessName();
            data[2] = item.getStrNumber();
            data[3] = item.getStrDescripcion();
            data[4] = item.getIdContacto();
            data[5] = item.getIdCategoria();
            
            if(item.getBoolEstatus())
            {
                data[6] = "activo";
            }else{
                data[6] = "inactivo";
            }
            

            model.addRow(data);
        }

    }
     public Supplier GetSupplierByCode(String code) {
        Supplier item = ctrlSupplier.findSupplierByCode(code);
        return item;
    }
     
     public String SearchSupplierByLette(String letter, DefaultTableModel model) {
        String result = null;

        List<Supplier> listSuppliers = ctrlSupplier.FindDataByFirstLetter(letter);
        Object[] data = new Object[7];

        if (listSuppliers.size() > 0) {
            for (Supplier item : listSuppliers) {
            data[0] = item.getStrClave();
            data[1] = item.getStrBussinessName();
            data[2] = item.getStrNumber();
            data[3] = item.getStrDescripcion();
            data[4] = item.getIdContacto();
            data[5] = item.getIdCategoria();
            
            if(item.getBoolEstatus())
            {
                data[6] = "activo";
            }else{
                data[6] = "inactivo";
            }
            

            model.addRow(data);
        }
        } else {
            result = "No se encontraron datos con el nombre ingresado";
        }
        return result;
    }
     
     public String EditSupplier(Supplier supplier) {
        String result = null;
        try {
            ctrlSupplierDefault.edit(supplier);
        } catch (Exception ex) {
            result = "Excepcion generada " + ex.getMessage();
        }
        return result;
    }
     
     
    
    Process_CatCategoria CategoriasProcess;
    
    ISupplier ctrlSupplierDefault;
    ISupplierExtends ctrlSupplier;
    
    IPersona ctrPersona;
    IContact ctrContact;
}
