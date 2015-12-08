/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.PurchaseOrder;

import BDPuntoVentaManuel.Views.View_Start;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author manuel
 */
public class PO_Descipcion_Process extends javax.swing.JPanel {

    /**
     * Creates new form PO_Descipcion_Process
     */
    public PO_Descipcion_Process() {
        initComponents();
    }
    
    public void OpenWindows(int folioPo)
    {
        po=PO_Start.PoProcess.GetPoByFolio(folioPo);
        this.Paintdata();
        PO_Start.viewDefault.setVisible(false);
        PO_Start.viewProcess.setVisible(true);
    }
    
    private void Paintdata()
    {
        this.SetModelToTable();
        this.ChargeDataTable();
        this.tbData.setModel(model);
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        this.lbDateTime.setText(date.format(po.getDatFechaGenerada()));
        this.lbFolio.setText(po.getId().toString());
        this.lbEmal.setText(po.getIdSupplier().getIdContacto().getStrEmail());
        this.lbSupplier.setText(po.getIdSupplier().getStrBussinessName());
        this.lbTotal.setText(View_Start.currencySystem.format(po.getDobTotal()));
    }
    private void SetModelToTable()
    {
        this.model=new DefaultTableModel();
        model.addColumn("Folio");
        model.addColumn("Produto");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Total");   
    }
    private void ChargeDataTable()
    {
        PO_Start.PoProcess.ChargeData(model, po);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbEmal = new javax.swing.JLabel();
        lbDateTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbSupplier = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnResepcion = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbEmal.setForeground(new java.awt.Color(51, 102, 255));
        lbEmal.setText("jLabel9");

        lbDateTime.setForeground(new java.awt.Color(51, 102, 255));
        lbDateTime.setText("jLabel8");

        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Email");

        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Fecha de creacion");

        lbTotal.setForeground(new java.awt.Color(51, 102, 255));
        lbTotal.setText("jLabel10");

        jLabel11.setForeground(new java.awt.Color(51, 102, 255));
        jLabel11.setText("Productos de la orden de compra");

        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Total de la orden de compra");

        lbSupplier.setForeground(new java.awt.Color(51, 102, 255));
        lbSupplier.setText("jLabel7");

        lbFolio.setForeground(new java.awt.Color(51, 102, 255));
        lbFolio.setText("jLabel6");

        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Folio");

        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Provedor");

        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbData);

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setForeground(new java.awt.Color(51, 102, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnResepcion.setBackground(new java.awt.Color(255, 255, 255));
        btnResepcion.setForeground(new java.awt.Color(51, 102, 255));
        btnResepcion.setText("Recepcion de orden de compra");
        btnResepcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbEmal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(118, 118, 118))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(lbFolio)
                    .addComponent(lbDateTime))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbSupplier)
                    .addComponent(jLabel4)
                    .addComponent(lbEmal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbTotal))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnResepcion))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnResepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepcionActionPerformed
        
        if(po!=null)
        {
            if(PO_Start.PoProcess.ReseptPpProduct(po.getId()))
            {
                PO_Start.viewDefault.setVisible(true);
                PO_Start.pnBtn.setVisible(true);
                PO_Start.viewProcess.setVisible(false);
                PO_Start.viewDefault.ResetTableValid();
            }
        }
    }//GEN-LAST:event_btnResepcionActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        PO_Start.viewDefault.setVisible(true);
        PO_Start.viewProcess.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnResepcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDateTime;
    private javax.swing.JLabel lbEmal;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JLabel lbSupplier;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tbData;
    // End of variables declaration//GEN-END:variables

    //Variables
    private BDPuntoVentaManuel.MODEL.Po po;
    private DefaultTableModel model;
    
}
