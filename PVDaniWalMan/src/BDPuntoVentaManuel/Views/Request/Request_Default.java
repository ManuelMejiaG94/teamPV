/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Supplier;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Request_Default extends javax.swing.JPanel {

    /**
     * Creates new form Orders_Default
     */
    public Request_Default() {
        initComponents();
        this.Start_Windows();
    }
    
    private void Start_Windows()
    {
        this.Charge_Data_Default();
    }
    
    private void Charge_Data_Default()
    {
        bitStatus=this.cmbStatus.getSelectedIndex();
        this.cmbCategorias.setModel(Request_Start.RequestProcess.getModelCategorias());
        Process_cmb();
        ChargeDataTable();
        
        this.jLabel3.setVisible(false);
        this.lbErrorMessage.setVisible(false);
        
        lbErrorMessage2.setVisible(false);
        visitError=false;
    }
    
    public void ChargeDataTable()
    {
        if(supplier == null)
        {
            this.SetModelTable();
            Request_Start.RequestProcess.ChargeDataDefault(modelTab);
            this.tbData.setModel(modelTab);
        }else{
            this.SetModelTable();
            Request_Start.RequestProcess.ChargeDataByFilter(modelTab, supplier, bitStatus);
            this.tbData.setModel(modelTab);
        }
    }
    private void SearchRequestByFolio()
    {
        if (!StringUtils.isNullOrEmpty(this.txtFolio.getText())) {
            this.SetModelTable();
            Request_Start.RequestProcess.ChargeDataByFolio(modelTab,Integer.parseInt(this.txtFolio.getText().trim()));
            this.tbData.setModel(modelTab);
            
            this.jLabel3.setVisible(false);
            this.lbErrorMessage.setVisible(false);
        }else
        {
            this.lbErrorMessage.setText("Es requerido el numero de folio de la solicitud");
            this.jLabel3.setVisible(true);
            this.lbErrorMessage.setVisible(true);
        }
        
    }
    
    private void SetModelTable()
    {
        modelTab=new DefaultTableModel();
        modelTab.addColumn("Numero de folio");
        modelTab.addColumn("Provedor");
        modelTab.addColumn("Fecha de pedido");
        modelTab.addColumn("Total");
        modelTab.addColumn("Estatus");
    }
     private void Process_cmb()
    {   
        if (this.categoria !=null && this.categoria.getId()>0)
        {    
            this.cmbSuppliers.setModel(Request_Start.RequestProcess.getModelSuppliers(categoria));
        }else{
            this.cmbSuppliers.setModel(Request_Start.RequestProcess.getModelSuppliersDefault(categoria));
        }
    }
    
     private void PaintErrorItemSelect()
     {
         lbErrorMessage2.setText("Es necesario seleccionar un elemento de la tabla");
         lbErrorMessage2.setVisible(true);
         visitError=true;   
     }
     private void CleanErrorItemselect()
     {
         lbErrorMessage2.setVisible(false);
         visitError=false;   
     }
     private void PaintErrorOpenValid()
     {
         lbErrorMessage2.setText("La solicitud no cuenta con el estatus para ser procesada de la opcion dada");
         lbErrorMessage2.setVisible(true);
         visitError=true;   
     }
     private void CleanErrorOpenValid()
     {
         lbErrorMessage2.setVisible(false);
         visitError=false;   
     }
     
     
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbCategorias = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbSuppliers = new javax.swing.JComboBox();
        lbErrorMessage = new javax.swing.JLabel();
        btnValidar = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        lbErrorMessage2 = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 0, 51));

        jLabel1.setText("Categoria");

        cmbCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriasItemStateChanged(evt);
            }
        });

        jLabel2.setText("Numero de folio");

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbData);

        jLabel4.setText("Estatus");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activas", "Cerradas", "canceladas", "Procesadas" }));
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });

        jLabel5.setText("Provedor");

        cmbSuppliers.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSuppliersItemStateChanged(evt);
            }
        });

        lbErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorMessage.setText("Error message");

        btnValidar.setText("Validar solicitud");
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });

        btnDetalle.setText("Detalle de la solicitud");
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        lbErrorMessage2.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorMessage2.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCategorias, 0, 211, Short.MAX_VALUE)
                                .addComponent(cmbSuppliers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(24, 24, 24)
                                            .addComponent(jLabel3))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFolio, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lbErrorMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnSearch)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lbErrorMessage2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValidar)
                    .addComponent(btnDetalle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriasItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.categoria = (Catcategoria) this.cmbCategorias.getSelectedItem();
            Process_cmb();
            if (categoria.getId() == -1) {
                supplier = null;
                this.cmbSuppliers.setModel(Request_Start.RequestProcess.getModelSuppliersDefault(categoria));
                ChargeDataTable();
            }
        }

        //this.cmbCategorias.setModel(Request_Start.RequestProcess.getModelCategorias());
    }//GEN-LAST:event_cmbCategoriasItemStateChanged

    private void cmbSuppliersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSuppliersItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.supplier = (Supplier) this.cmbSuppliers.getSelectedItem();
            ChargeDataTable();
        }
    }//GEN-LAST:event_cmbSuppliersItemStateChanged

    private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
        bitStatus = this.cmbStatus.getSelectedIndex();
        ChargeDataTable();
        if(this.cmbStatus.getSelectedIndex()>0 && this.cmbStatus.getSelectedIndex()<3)
        {
            this.btnValidar.setVisible(false);
        }else{
            this.btnValidar.setVisible(true);
        }
    }//GEN-LAST:event_cmbStatusItemStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        SearchRequestByFolio();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        if (visitError) {
            CleanErrorItemselect();
        }
        if (tbData.getSelectedRow() >= 0) {
            int folio=Integer.parseInt((tbData.getValueAt(tbData.getSelectedRow(),0).toString()));
            
            if(Request_Start.viewValid.Open_View_Valid(folio))
            {
                CleanErrorOpenValid();
            }else
            {
                PaintErrorOpenValid();
            }
            
        } else {
            PaintErrorItemSelect();
        }

        
    }//GEN-LAST:event_btnValidarActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        if (visitError) {
            CleanErrorItemselect();
        }
        if (tbData.getSelectedRow() >= 0) {
            int folio=Integer.parseInt((tbData.getValueAt(tbData.getSelectedRow(),0).toString()));
            Request_Start.viewValid.Open_View_Detail(folio);
            
        } else {
            PaintErrorItemSelect();
        }
        
    }//GEN-LAST:event_btnDetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnValidar;
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbSuppliers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbErrorMessage;
    private javax.swing.JLabel lbErrorMessage2;
    public static javax.swing.JTable tbData;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
 
    //Variables
    DefaultTableModel modelTab;
    Catcategoria categoria=null;
    Supplier supplier=null;
    
    int bitStatus;
    boolean visitError=false;
    
    
}
