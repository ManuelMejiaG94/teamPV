/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Product;


import BDPuntoVentaManuel.ABSTRACT.IProduct;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProduct;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Products {
//    
    public Products() {
        this.start_tools();
    }

    private void start_tools() {
        CategoriasProcess = new Process_CatCategoria();
        ctrlProducto = new FactoryProduct().getInstanceAbstractExtends();
        ctrlProductoDefault = new FactoryProduct().getInstanceAbstract();
    }

    public DefaultComboBoxModel getModelCategorias() {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }

    public void ChargeDataDefault(DefaultTableModel model) {
        List<Product> listProducts = ctrlProducto.findDatadefault();
        Object[] data = new Object[7];

        for (Product item : listProducts) {
            data[0] = item.getStrClave();
            data[1] = item.getStrName();
            data[2] = item.getDonPC();
            data[3] = item.getDobPV();
            data[4] = item.getIntStock();
            data[5] = item.getStrDescription();
            data[6] = item.getStrPresentation();

            model.addRow(data);
        }

    }

    public void SaveProduct(Product product) {
        ctrlProductoDefault.create(product);
    }

    public void ChargeDataByCategoriaId(DefaultTableModel model, Catcategoria categoria) {
        List<Product> listProducts = ctrlProducto.FindDataByCategoriaId(categoria);
        Object[] data = new Object[7];

        for (Product item : listProducts) {
            data[0] = item.getStrClave();
            data[1] = item.getStrName();
            data[2] = item.getDonPC();
            data[3] = item.getDobPV();
            data[4] = item.getIntStock();
            data[5] = item.getStrDescription();
            data[6] = item.getStrPresentation();

            model.addRow(data);
        }

    }

    public Product GetProductByCode(String code) {
        Product item = ctrlProducto.findProductByCode(code);
        return item;
    }

    public String SearchProductByLette(String letter, DefaultTableModel model) {
        String result = null;

        List<Product> listProducts = ctrlProducto.FindDataByFirstLetter(letter);
        Object[] data = new Object[7];

        if (listProducts.size() > 0) {
            for (Product item : listProducts) {
                data[0] = item.getStrClave();
                data[1] = item.getStrName();
                data[2] = item.getDonPC();
                data[3] = item.getDobPV();
                data[4] = item.getIntStock();
                data[5] = item.getStrDescription();
                data[6] = item.getStrPresentation();

                model.addRow(data);
            }
        } else {
            result = "No se encontraron datos con el nombre ingresado";
        }
        return result;
    }

    public String EditProduct(Product product) {
        String result = null;
        try {
            ctrlProductoDefault.edit(product);
        } catch (Exception ex) {
            result = "Excepcion generada " + ex.getMessage();
        }
        return result;
    }

    //Variables de procesos
    Process_CatCategoria CategoriasProcess;

    //Controlers
    IProduct ctrlProductoDefault;
    IProductExtends ctrlProducto;
}
