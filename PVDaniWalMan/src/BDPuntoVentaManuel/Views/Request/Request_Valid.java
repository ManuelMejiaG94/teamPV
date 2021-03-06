/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.MODEL.Po;
import BDPuntoVentaManuel.MODEL.Requestdetail;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author manuel
 */
public class Request_Valid extends javax.swing.JPanel {

    /**
     * Creates new form Request_Valid
     */
    public Request_Valid() {
        initComponents();
        this.lbErrorMessage2.setVisible(false);
    }
    
    public void Open_View_Detail(int folio)
    {
        Button_Detail();
        PaintDataDetail2(folio);
        
        OpenWindowsDetail();
    }
    public boolean Open_View_Valid(int folio)
    {
        Button_Validate();
        boolean valid=false;
        if(PaintDataDetail(folio))
        {
        OpenWindowsValidate();
        valid=true;
        }
        return valid;
    }

    private void Button_Validate()
    {
        this.btnCancel.setVisible(true);
        this.btnCancel.setText("Cancelar");
        this.btnPoPartials.setVisible(true);
        this.btnReject.setVisible(true);
        this.btnGenerate.setVisible(true);
    }
    
    private void Button_Detail()
    {
        this.btnCancel.setVisible(true);
        this.btnCancel.setText("Aseptar");
        this.btnGenerate.setVisible(false);
        this.btnPoPartials.setVisible(false);
        this.btnReject.setVisible(false);
    }
 
    private boolean PaintDataDetail(int folio)
    {
        SetModelTable();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        BDPuntoVentaManuel.MODEL.Request request=Request_Start.RequestProcess.GetRequestByFolio(folio);
        
        this.lbFolio.setText(request.getId().toString());
        this.lbDate.setText(date.format(request.getDatFecha()));
        
        this.lbEmail.setText(request.getIdSuplier().getIdContacto().getStrEmail());
        this.lbSupplier.setText(request.getIdSuplier().getStrBussinessName());
        
        this.lbTotal.setText("$"+String.valueOf(request.getDoubTotal()));
     
        if(request.getBitEstatus()==4)
        {
            this.btnGenerate.setVisible(false);
        }
        if(request.getBitEstatus()==1 || request.getBitEstatus()== 4)
        {
            PaintDataTable((List<Requestdetail>) request.getRequestdetailCollection());
            return true;
        }
        return false;
    }
    
    private void PaintDataDetail2(int folio)
    {
        SetModelTable();
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        BDPuntoVentaManuel.MODEL.Request request=Request_Start.RequestProcess.GetRequestByFolio(folio);
        
        this.lbFolio.setText(request.getId().toString());
        this.lbDate.setText(date.format(request.getDatFecha()));
        
        this.lbEmail.setText(request.getIdSuplier().getIdContacto().getStrEmail());
        this.lbSupplier.setText(request.getIdSuplier().getStrBussinessName());
        
        this.lbTotal.setText("$"+String.valueOf(request.getDoubTotal()));
     
        if(request.getBitEstatus()==4)
        {
            this.btnGenerate.setVisible(false);
        }
        
            PaintDataTableDetail((List<Requestdetail>) request.getRequestdetailCollection());
        
    }
    
    private void SetModelTable()
    {
        modelTable=new DefaultTableModel();
        
        modelTable.addColumn("Codigo");
        modelTable.addColumn("Nombre");
        modelTable.addColumn("Precio");
        modelTable.addColumn("Cantidad");
        modelTable.addColumn("Total");
        
        tbDatadetails.setModel(modelTable);
    }
     private void PaintDataTable(List<BDPuntoVentaManuel.MODEL.Requestdetail> listDetails)
    {
        Object[] data=new Object[5];
        
        for(BDPuntoVentaManuel.MODEL.Requestdetail item : listDetails)
        {
            if(!item.getBolAssigned())
            {
                data[0]=item.getIdProduct().getStrClave();
                data[1]=item.getIdProduct().getStrName();
                data[2]=item.getDobPrice();
                data[3]=item.getDobQuantity();
                data[4]=item.getDobTotal();
            
                modelTable.addRow(data);
            }
        }
        this.tbDatadetails.setModel(modelTable);
        
        this.tbDatadetails.setEnabled(false);
    }
    
    private void PaintDataTableDetail(List<BDPuntoVentaManuel.MODEL.Requestdetail> listDetails)
    {
        Object[] data=new Object[5];
        
        for(BDPuntoVentaManuel.MODEL.Requestdetail item : listDetails)
        {
           
                data[0]=item.getIdProduct().getStrClave();
                data[1]=item.getIdProduct().getStrName();
                data[2]=item.getDobPrice();
                data[3]=item.getDobQuantity();
                data[4]=item.getDobTotal();
            
                modelTable.addRow(data);
        }
        this.tbDatadetails.setModel(modelTable);
        
        this.tbDatadetails.setEnabled(false);
    }
    
    private void OpenWindowsDetail()
    {
            Request_Start.viewRequest.setVisible(false);
            Request_Start.pnOptions.setVisible(false);
            Request_Start.viewDefault.setVisible(false);
            Request_Start.viewProcess.setVisible(false);
            Request_Start.viewPartial.setVisible(false);
            Request_Start.viewValid.setVisible(true);
    }
    
    private void OpenWindowsValidate()
    {
            Request_Start.viewRequest.setVisible(false);
            Request_Start.pnOptions.setVisible(false);
            Request_Start.viewDefault.setVisible(false);
            Request_Start.viewProcess.setVisible(false);
            Request_Start.viewPartial.setVisible(false);
            Request_Start.viewValid.setVisible(true);
    }
    
    private void CleanErrorCantCreatePo()
     {
         lbErrorMessage2.setVisible(false);
         visitError=false;   
     }
    
    private void PaintErrorCantCreatePo()
    {
        this.lbErrorMessage2.setText("No ha sido posible crear una orden de compra "
                + "para la solicitud");
        this.lbErrorMessage2.setVisible(true);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatadetails = new javax.swing.JTable();
        lbFolio = new javax.swing.JLabel();
        lbSupplier = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnGenerate = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnPoPartials = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lbErrorMessage2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Folio");

        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Provedor");

        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Fecha de creacion");

        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Email");

        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Total de la solicitud");

        tbDatadetails.setForeground(new java.awt.Color(51, 102, 255));
        tbDatadetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbDatadetails);

        lbFolio.setForeground(new java.awt.Color(51, 102, 255));
        lbFolio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFolio.setText("jLabel6");

        lbSupplier.setForeground(new java.awt.Color(51, 102, 255));
        lbSupplier.setText("jLabel7");

        lbDate.setForeground(new java.awt.Color(51, 102, 255));
        lbDate.setText("jLabel8");

        lbEmail.setForeground(new java.awt.Color(51, 102, 255));
        lbEmail.setText("jLabel9");

        lbTotal.setForeground(new java.awt.Color(51, 102, 255));
        lbTotal.setText("jLabel10");

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setForeground(new java.awt.Color(51, 102, 255));
        btnCancel.setText("Cancelar");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGenerate.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerate.setForeground(new java.awt.Color(51, 102, 255));
        btnGenerate.setText("Generar orden de compra");
        btnGenerate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnReject.setBackground(new java.awt.Color(255, 255, 255));
        btnReject.setForeground(new java.awt.Color(51, 102, 255));
        btnReject.setText("Rechazar solicitud");
        btnReject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        btnPoPartials.setBackground(new java.awt.Color(255, 255, 255));
        btnPoPartials.setForeground(new java.awt.Color(51, 102, 255));
        btnPoPartials.setText("Generar orden parcial");
        btnPoPartials.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPoPartials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoPartialsActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(51, 102, 255));
        jLabel11.setText("Productos de la orden de compra");

        lbErrorMessage2.setForeground(new java.awt.Color(255, 51, 51));
        lbErrorMessage2.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPoPartials, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbErrorMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(lbFolio)
                    .addComponent(lbDate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbSupplier)
                    .addComponent(jLabel4)
                    .addComponent(lbEmail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbTotal))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbErrorMessage2)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPoPartials)
                    .addComponent(btnGenerate)
                    .addComponent(btnReject)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Request_Start.viewRequest.setVisible(false);
        Request_Start.pnOptions.setVisible(true);
        Request_Start.viewDefault.setVisible(true);
        Request_Start.viewProcess.setVisible(false);
        Request_Start.viewPartial.setVisible(false);
        Request_Start.viewValid.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        
        if (visitError) {
            CleanErrorCantCreatePo();
        }

        int folio = Integer.parseInt(this.lbFolio.getText());
        if (Request_Start.RequestProcess.Cancel_Request(folio)) {

            Request_Start.viewRequest.setVisible(false);
            Request_Start.pnOptions.setVisible(true);
            Request_Start.viewDefault.setVisible(true);
            Request_Start.viewDefault.ChargeDataTable();
            Request_Start.viewProcess.setVisible(false);
            Request_Start.viewPartial.setVisible(false);
            Request_Start.viewValid.setVisible(false);
        }

    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed

        if(visitError)
        {
            CleanErrorCantCreatePo();
        }
        
        int folio = Integer.parseInt(this.lbFolio.getText());
        if (Request_Start.RequestProcess.CreateOdc(folio)) {
            
            Request_Start.viewRequest.setVisible(false);
            Request_Start.pnOptions.setVisible(true);
            Request_Start.viewDefault.setVisible(true);
            Request_Start.viewDefault.ChargeDataTable();
            Request_Start.viewProcess.setVisible(false);
            Request_Start.viewPartial.setVisible(false);
            Request_Start.viewValid.setVisible(false);
            
        } else {
            PaintErrorCantCreatePo();
        }
        
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnPoPartialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoPartialsActionPerformed
//        Request_Start.viewRequest.setVisible(false);
//        Request_Start.pnOptions.setVisible(false);
//        Request_Start.viewDefault.setVisible(false);
//        Request_Start.viewProcess.setVisible(false);
//        Request_Start.viewPartial.setVisible(true);
//        Request_Start.viewValid.setVisible(false);
        int folio=Integer.parseInt(this.lbFolio.getText());
        Request_Start.viewPartial.OpenWindows(folio);
    }//GEN-LAST:event_btnPoPartialsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnPoPartials;
    private javax.swing.JButton btnReject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbErrorMessage2;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JLabel lbSupplier;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tbDatadetails;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel modelTable;
    private boolean visitError=false;

}
