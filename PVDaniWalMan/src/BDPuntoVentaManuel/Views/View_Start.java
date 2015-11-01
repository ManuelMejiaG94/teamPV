/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views;

//import ManuelMejiaG.PuntoVenta2.Views.Product.Product_Start;
//import java.awt.Container;
//import java.awt.GridBagConstraints;
import BDPuntoVentaManuel.Views.Request.Request_Start;
import BDPuntoVentaManuel.Views.Product.Products_Start;
import BDPuntoVentaManuel.Views.PurchaseOrder.PO_Start;
import BDPuntoVentaManuel.Views.Sales.Sales_Start;
import BDPuntoVentaManuel.Views.Supplier.Suppliers_Start;
import javax.swing.JInternalFrame;

/**
 *
 * @author manuel
 */
public class View_Start extends javax.swing.JFrame {

   
//    Product_Start ProductsView;
//    View_Default DefaultView;
    /**
     * Creates new form View_Start
     */
    public View_Start() {
        initComponents();
        this.setLocationRelativeTo(null);
         this.setExtendedState(this.MAXIMIZED_BOTH);
 
       this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//        this.setVisible(true);
        //this.setBackground();
//        this.Start_Conection_Views();
    }
    
//    private void setBackground()
//    {
//        this.pnContainer.setVisible(false);
//        Container con=getContentPane();
//        Default VF=new Default();
//        con.add(VF);
//    }
    
//    private void Start_Conection_Views()
//    {
//        ProductsView=new Product_Start();
//        DefaultView=new View_Default();
//        
//        pnContainer.setLayout(Layout);
//        GridBagConstraints cons= new GridBagConstraints();
//        
//        cons.gridx=0;
//        cons.gridy=0;
//        
//        pnContainer.add(ProductsView,cons);
//        
//        
//        
//        ProductsView.setVisible(false);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        Desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnMenProducts = new javax.swing.JMenu();
        btnGestionProduct = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnNewSale = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnProvedor = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnNewOrders = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(Desktop);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnMenProducts.setText("Productos");

        btnGestionProduct.setText("Gestionar Productos");
        btnGestionProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionProductActionPerformed(evt);
            }
        });
        btnMenProducts.add(btnGestionProduct);

        jMenuBar1.add(btnMenProducts);

        jMenu2.setText(" Ventas");

        btnNewSale.setText("Nueva venta");
        btnNewSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSaleActionPerformed(evt);
            }
        });
        jMenu2.add(btnNewSale);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Provedores");

        btnProvedor.setText("Gestionar provedor");
        btnProvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvedorActionPerformed(evt);
            }
        });
        jMenu3.add(btnProvedor);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Solicitud");

        btnNewOrders.setText("Gestionar solicitudes");
        btnNewOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewOrdersActionPerformed(evt);
            }
        });
        jMenu4.add(btnNewOrders);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Ordenes de compra");

        jMenuItem2.setText("Gestionar Orden de Compra");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionProductActionPerformed

        if(!(view_Products instanceof Products_Start))
        {
            view_Products=new Products_Start();
            Centrar(view_Products);
        }else{
            view_Products.dispose();
            view_Products=new Products_Start();
            Centrar(view_Products);
        }
        
        

    }//GEN-LAST:event_btnGestionProductActionPerformed

    private void btnNewSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSaleActionPerformed
          if(!(view_Sales instanceof Sales_Start))
        {
            view_Sales=new Sales_Start();
            Centrar(view_Sales);
        }else{
            view_Sales.dispose();
            view_Sales=new Sales_Start();
            Centrar(view_Sales);
        }
    }//GEN-LAST:event_btnNewSaleActionPerformed

    private void btnProvedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvedorActionPerformed
          if(!(view_Suppliers instanceof Suppliers_Start))
        {
            view_Suppliers=new Suppliers_Start();
            Centrar(view_Suppliers);
        }else{
            view_Suppliers.dispose();
            view_Suppliers=new Suppliers_Start();
            Centrar(view_Suppliers);
        }
    }//GEN-LAST:event_btnProvedorActionPerformed

    private void btnNewOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewOrdersActionPerformed
        if(!(view_Orders instanceof Request_Start))
        {
            view_Orders=new Request_Start();
            Centrar(view_Orders);
        }else{
            view_Orders.dispose();
            view_Orders=new Request_Start();
            Centrar(view_Orders);
        }
    }//GEN-LAST:event_btnNewOrdersActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(!(view_PO instanceof PO_Start))
        {
            view_PO=new PO_Start();
            Centrar(view_PO);
        }else{
            view_PO.dispose();
            view_PO=new PO_Start();
            Centrar(view_PO);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
    private void Centrar(JInternalFrame j)
    {
        int x=(Desktop.getWidth()/2)-j.getWidth()/2;
        int y=(Desktop.getHeight()/2)-j.getHeight()/2;
        //this.setLocation(x, y);
        
        if(j.isShowing()){
            j.setLocation(x, y);
            
        }else{
            Desktop.add(j);
            j.setLocation(x, y);
            j.show();
        }
        
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuItem btnGestionProduct;
    private javax.swing.JMenu btnMenProducts;
    private javax.swing.JMenuItem btnNewOrders;
    private javax.swing.JMenuItem btnNewSale;
    private javax.swing.JMenuItem btnProvedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    //Views products
    Products_Start view_Products;
    Sales_Start view_Sales;
    Suppliers_Start view_Suppliers;
    Request_Start view_Orders;
    PO_Start view_PO;

}
