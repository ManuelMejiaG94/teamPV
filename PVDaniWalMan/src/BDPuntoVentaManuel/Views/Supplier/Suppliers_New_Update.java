/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Supplier;


import static BDPuntoVentaManuel.Views.View_Start.Desktop;
import javax.swing.JInternalFrame;

/**
 *
 * @author manuel
 */
public class Suppliers_New_Update extends javax.swing.JPanel {

    /**
     * Creates new form Suppliers_New
     */
    public Suppliers_New_Update() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtxRazonSocial = new javax.swing.JTextField();
        cmbCategorias = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescripcion = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtNumEmpresa = new javax.swing.JTextField();
        lbError1 = new javax.swing.JLabel();
        lbError2 = new javax.swing.JLabel();
        lbError3 = new javax.swing.JLabel();
        lbError4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbError13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreContacto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbError7 = new javax.swing.JLabel();
        lbError6 = new javax.swing.JLabel();
        lbError11 = new javax.swing.JLabel();
        lbError12 = new javax.swing.JLabel();
        lbError5 = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        txtAMaterno = new javax.swing.JTextField();
        txtNumCelularCont = new javax.swing.JTextField();
        txtEmailContact = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        Errors2 = new javax.swing.JList();
        btnCancel2 = new javax.swing.JButton();
        btnRecover2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbError8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnDireccion = new javax.swing.JButton();

        jLabel2.setText("Datos de la empresa");

        jLabel3.setText("Razon social");

        jLabel5.setText("Categoria");

        cmbCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriasItemStateChanged(evt);
            }
        });

        jLabel8.setText("Descripcion de la empresa");

        taDescripcion.setColumns(20);
        taDescripcion.setRows(5);
        jScrollPane1.setViewportView(taDescripcion);

        jLabel17.setText("Numero");

        lbError1.setForeground(new java.awt.Color(255, 0, 51));
        lbError1.setText("*");

        lbError2.setForeground(new java.awt.Color(255, 0, 51));
        lbError2.setText("*");

        lbError3.setForeground(new java.awt.Color(255, 0, 51));
        lbError3.setText("*");

        lbError4.setForeground(new java.awt.Color(255, 0, 51));
        lbError4.setText("*");

        lbError13.setForeground(new java.awt.Color(255, 0, 51));
        lbError13.setText("*");

        jLabel10.setText("Codigo del provedor");

        jLabel12.setText("Contacto");

        jLabel13.setText("Nombre");

        jLabel4.setText("Apellido paterno");

        jLabel9.setText("Apellido materno");

        jLabel14.setText("Telefono celular");

        jLabel16.setText("Email");

        lbError7.setForeground(new java.awt.Color(255, 0, 51));
        lbError7.setText("*");

        lbError6.setForeground(new java.awt.Color(255, 0, 51));
        lbError6.setText("*");

        lbError11.setForeground(new java.awt.Color(255, 0, 51));
        lbError11.setText("*");

        lbError12.setForeground(new java.awt.Color(255, 0, 51));
        lbError12.setText("*");

        lbError5.setForeground(new java.awt.Color(255, 0, 51));
        lbError5.setText("*");

        Errors2.setForeground(new java.awt.Color(255, 0, 0));
        jScrollPane5.setViewportView(Errors2);

        btnCancel2.setText("Cancelar");
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        btnRecover2.setText("Registrar");
        btnRecover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecover2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Direccion provedor");

        lbError8.setForeground(new java.awt.Color(255, 0, 51));
        lbError8.setText("*");

        btnDireccion.setText("Asignar direccion");
        btnDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDireccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbError4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(lbError13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbError1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbError2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbError3, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodigo)
                                    .addComponent(cmbCategorias, 0, 211, Short.MAX_VALUE)
                                    .addComponent(txtxRazonSocial)
                                    .addComponent(txtNumEmpresa)))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbError7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbError6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbError5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbError11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbError12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAPaterno)
                                    .addComponent(txtAMaterno)
                                    .addComponent(txtNumCelularCont)
                                    .addComponent(txtEmailContact)
                                    .addComponent(txtNombreContacto)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbError8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccion))
                            .addComponent(btnDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRecover2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbError12)
                            .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError11)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNumCelularCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtEmailContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbError8)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbError13)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtxRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNumEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbError4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRecover2)
                    .addComponent(btnCancel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriasItemStateChanged
//        if(evt.getStateChange()==ItemEvent.SELECTED)
//        {
//            CatCategorias cat=(CatCategorias) this.cmbCategorias.getSelectedItem();
//            this.idCategoria=cat.getId();
//        }
    }//GEN-LAST:event_cmbCategoriasItemStateChanged

    private void btnRecover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecover2ActionPerformed
//
//        //this.process.setMessage("Mejia_Garcia94@hotmail.com");
//
//        this.Limpiar_Errores();
//
//        if (this.Validar_Datos()) {
//            if (this.AccionMap == 1) {
//                Object persona=this.process.getPersona(this.txtNombreContacto.getText().trim(), this.txtAPaterno.getText().trim(),this.txtAMaterno.getText().trim());
//                Object contacto=this.process.getContacto(this.txtNumCelularCont.getText().trim(),this.txtEmailContact.getText().trim());
//                Object provedor=this.process.getProvedor(this.txtxRazonSocial.getText().trim(),this.idCategoria,this.txtNumEmpresa.getText().trim(),this.taDescripcion.getText().trim(), this.txtCalle.getText().trim(), this.txtReferencia.getText().trim(),this.txtCodigo.getText().trim());
//
//                if(this.process.RegistrarProvedor(persona, contacto, provedor))
//                {
//                    JOptionPane.showMessageDialog(null,"ok");
//                    this.dispose();
//                }
//
//            } else {
//                PuntoDeVenta.ManuelMejia.Models.ErrorMessage.MessageError MessageFieldError
//                = new PuntoDeVenta.ManuelMejia.Models.ErrorMessage.MessageError();
//
//                MessageFieldError.setId("Field");
//                MessageFieldError.setMessage("Es necesario identificar primero la direccion");
//                this.Errores.add(MessageFieldError);
//                this.ErrorsPaint();
//            }
//        } else {
//            this.ErrorsPaint();
//        }
    }//GEN-LAST:event_btnRecover2ActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
//        this.process.btnCancel(this);
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void btnDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDireccionActionPerformed
        
        
//        View_Start.Desktop.add(view_Direction);
         if(!(view_Direction instanceof Suppliers_Direccion))
        {
            view_Direction=new Suppliers_Direccion();
            Centrar(view_Direction);
        }else{
            view_Direction.dispose();
            view_Direction=new Suppliers_Direccion();
            Centrar(view_Direction);
        }
        
        
        
        
    }//GEN-LAST:event_btnDireccionActionPerformed


    private void Centrar(JInternalFrame j)
    {
        int x=(Desktop.getWidth()/2)-j.getWidth()/2;
        int y=(Desktop.getHeight()/2)-j.getHeight()/2;
        this.setLocation(x, y);
        
        if(j.isShowing()){
            j.setLocation(x, y);
            
        }else{
            Desktop.add(j);
            j.setLocation(x, y);
            j.show();
        }
        
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Errors2;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnDireccion;
    private javax.swing.JButton btnRecover2;
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbError1;
    private javax.swing.JLabel lbError11;
    private javax.swing.JLabel lbError12;
    private javax.swing.JLabel lbError13;
    private javax.swing.JLabel lbError2;
    private javax.swing.JLabel lbError3;
    private javax.swing.JLabel lbError4;
    private javax.swing.JLabel lbError5;
    private javax.swing.JLabel lbError6;
    private javax.swing.JLabel lbError7;
    private javax.swing.JLabel lbError8;
    private javax.swing.JTextArea taDescripcion;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmailContact;
    private javax.swing.JTextField txtNombreContacto;
    private javax.swing.JTextField txtNumCelularCont;
    private javax.swing.JTextField txtNumEmpresa;
    private javax.swing.JTextField txtxRazonSocial;
    // End of variables declaration//GEN-END:variables

    //Objects view
    Suppliers_Direccion view_Direction;
}
