/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Supplier;


import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Persona;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.MODEL.Supplier_;
import BDPuntoVentaManuel.Notifications.viewUpdate;
import BDPuntoVentaManuel.Views.View_Start;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import java.util.Vector;
import javax.swing.JInternalFrame;

/**
 *
 * @author MANCERA
 */
public class Suppliers_New_Update extends javax.swing.JPanel {

    /**
     * Creates new form Suppliers_New
     */
    public Suppliers_New_Update() {
        initComponents();       
    }
    
    public void Open_New_Windows()
    {
        supplier=new Supplier();
        this.btnProcess.setText("Registrar");
        this.Clean_Windows();
        this.ChargeCategori();
        this.txtDireccion.setEditable(false);
        this.txtCodigo.setEditable(false);
        
        Suppliers_Start.viewSupplier.setVisible(true);
    }
    public void Open_Update_Windows(BDPuntoVentaManuel.MODEL.Supplier _supplier)
    {
        this.btnProcess.setText("Modificar");
        this.supplier=_supplier;
        this.Chargedata();
        this.ChargeCategori();
        Suppliers_Start.viewDefault.setVisible(false);
        Suppliers_Start.viewDireccion.setVisible(false);
        Suppliers_Start.viewSelect.setVisible(false);
        Suppliers_Start.viewSupplier.setVisible(true);
    
    }
    
    
    
    public void Chargedata()
    {
        this.txtCodigo.setText(supplier.getStrClave());
        this.txtxRazonSocial.setText(supplier.getStrBussinessName());
        this.cmbCategories.setSelectedItem(supplier.getIdCategoria());
        this.txtNumEmpresa.setText(supplier.getStrNumber());
        this.taDescripcion.setText(supplier.getStrDescripcion());
        
        this.txtNombreContacto.setText(supplier.getIdContacto().getIdPersona().getStrNombre());
        this.txtAPaterno.setText(supplier.getIdContacto().getIdPersona().getStrAPaterno());
        this.txtAMaterno.setText(supplier.getIdContacto().getIdPersona().getStrAMaterno());
        
        this.txtNumCelularCont.setText(supplier.getIdContacto().getStrCellphone());
        this.txtEmailContact.setText(supplier.getIdContacto().getStrEmail());
        
        this.txtDireccion.setText(supplier.getStrAddress());
    }
    
    public void LoadDate_New_Windows()
    {
        supplier=new Supplier();
        this.btnProcess.setText("Registrar");
        this.txtDireccion.setEditable(false);
        this.txtCodigo.setEditable(false);
        
        Suppliers_Start.viewSupplier.setVisible(true);
    }
    
    public void ChargeCategori()
    {
        this.cmbCategories.setModel(Suppliers_Start.SuppliersProcess.getModelCategorias());
    }
    public void SupplierAddAddress()
    {
        Suppliers_Start.viewDireccion.getSupplier();
    }
    
    public BDPuntoVentaManuel.MODEL.Persona getPersona()
    {
        BDPuntoVentaManuel.MODEL.Persona persona=new Persona();
        persona.setStrNombre(txtNombreContacto.getText().trim());
        persona.setStrAPaterno(txtAPaterno.getText().trim());
        persona.setStrAMaterno(txtAMaterno.getText().trim());
        
        return persona;
        
        
    }
    
    public BDPuntoVentaManuel.MODEL.Persona getPersonaForUpdate(BDPuntoVentaManuel.MODEL.Persona persona)
    {
        
        persona.setStrNombre(txtNombreContacto.getText().trim());
        persona.setStrAPaterno(txtAPaterno.getText().trim());
        persona.setStrAMaterno(txtAMaterno.getText().trim());
        
        return persona;
        
        
    }
    public BDPuntoVentaManuel.MODEL.Contacto getContact()
    {
        BDPuntoVentaManuel.MODEL.Contacto Contact=new Contacto();
        Contact.setStrReference("N/A");
        Contact.setStrEmail(txtEmailContact.getText().trim());
        Contact.setStrCellphone(txtNumCelularCont.getText().trim());
        
        return Contact;
    }
    
    public BDPuntoVentaManuel.MODEL.Contacto getContactForUpdate(BDPuntoVentaManuel.MODEL.Contacto Contact)
    {
        
        Contact.setStrReference("N/A");
        Contact.setStrEmail(txtEmailContact.getText().trim());
        Contact.setStrCellphone(txtNumCelularCont.getText().trim());
        
        return Contact;
    }
    public BDPuntoVentaManuel.MODEL.Supplier getSupplier()
    {
//        supplier=new Supplier();
        supplier.setBoolEstatus(true);
        supplier.setStrBussinessName(txtxRazonSocial.getText().trim());
        supplier.setStrClave(txtCodigo.getText().trim());
        supplier.setStrDescripcion(taDescripcion.getText().trim());
        supplier.setStrNumber(txtNumEmpresa.getText().trim());
        supplier.setIdCategoria(categoria);
        
        return supplier;
    }
    
    private Supplier GetSupplierForUpdate() {

        supplier.setStrClave(this.txtCodigo.getText().trim());
        supplier.setStrBussinessName(this.txtxRazonSocial.getText().trim());
        supplier.setStrNumber(this.txtNumCelularCont.getText().trim());
        supplier.setStrAddress(this.txtDireccion.getText().trim());
        supplier.setIdCategoria(this.categoria);
        supplier.setStrDescripcion(this.taDescripcion.getText().trim());
 

        return supplier;
    }
    private void LimpiarErrores()
    {
        lb1.setVisible(false);
        lb2.setVisible(false);
        lb3.setVisible(false);
        lb4.setVisible(false);
        lb5.setVisible(false);
        lb6.setVisible(false);
        lb7.setVisible(false);
        lb8.setVisible(false);
        lb9.setVisible(false);
        lb10.setVisible(false);
        lb11.setVisible(false);

    }
    private boolean Validar_Datos()
    {
        errors=new Vector();

        boolean Result = true;
        if (StringUtils.isNullOrEmpty(txtCodigo.getText())) {
            lb1.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtxRazonSocial.getText())) {
            lb2.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtNumEmpresa.getText())) {
            lb4.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(taDescripcion.getText())) {
            lb5.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtNombreContacto.getText())) {
            lb6.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtAPaterno.getText())) {
            lb7.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtAMaterno.getText())) {
            lb8.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtNumCelularCont.getText())) {
            lb9.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtEmailContact.getText())) {
            lb10.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(txtDireccion.getText())) {
            lb11.setVisible(true);
            Result = false;
        }

        if (!Result) {
            String error = "Los campos marcados con * son obligatorios";
            errors.add(error);
        }

        if (cmbCategories.getSelectedIndex() <= 0) {
            String error = "Es necesario indicar una categoria";

            errors.add(error);
            this.lb3.setVisible(true);
            Result = false;
        }

        return Result;
    }

     private void Back()
    {
        Suppliers_Start.viewDefault.setVisible(false);
        Suppliers_Start.viewDireccion.setVisible(false);
        Suppliers_Start.viewSelect.setVisible(false);
        Suppliers_Start.viewSupplier.setVisible(true);
    }
     private void Clean_Windows() {
        this.txtCodigo.setText(null);
        this.txtxRazonSocial.setText(null);
        this.txtNumEmpresa.setText(null);
        this.txtNombreContacto.setText(null);
        this.txtAMaterno.setText(null);
        this.txtAPaterno.setText(null);
        this.txtNumCelularCont.setText(null);
        this.txtEmailContact.setText(null);
        this.txtDireccion.setText(null);
        this.taDescripcion.setText(null);
    
        

        this.lb1.setVisible(false);
        this.lb2.setVisible(false);
        this.lb3.setVisible(false);
        this.lb4.setVisible(false);
        this.lb5.setVisible(false);
        this.lb6.setVisible(false);
        this.lb7.setVisible(false);
        this.lb8.setVisible(false);
        this.lb9.setVisible(false);
        this.lb10.setVisible(false);
        this.lb11.setVisible(false);
        
        
        
        
        this.Errors.setVisible(false);
    }
     
     public void setDireccion(){
         this.txtDireccion.setText(supplier.getStrAddress());
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
        cmbCategories = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescripcion = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtNumEmpresa = new javax.swing.JTextField();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        lb4 = new javax.swing.JLabel();
        lb5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lb1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreContacto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lb10 = new javax.swing.JLabel();
        lb9 = new javax.swing.JLabel();
        lb8 = new javax.swing.JLabel();
        lb7 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        txtAMaterno = new javax.swing.JTextField();
        txtNumCelularCont = new javax.swing.JTextField();
        txtEmailContact = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        Errors = new javax.swing.JList();
        btnCancel2 = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lb11 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnDireccion = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Datos de la empresa");

        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Razon social");

        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Categoria");

        txtxRazonSocial.setForeground(new java.awt.Color(51, 102, 255));

        cmbCategories.setForeground(new java.awt.Color(51, 102, 255));
        cmbCategories.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriesItemStateChanged(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Descripcion de la empresa");

        taDescripcion.setColumns(20);
        taDescripcion.setForeground(new java.awt.Color(51, 102, 255));
        taDescripcion.setRows(5);
        jScrollPane1.setViewportView(taDescripcion);

        jLabel17.setForeground(new java.awt.Color(51, 102, 255));
        jLabel17.setText("Numero");

        txtNumEmpresa.setForeground(new java.awt.Color(51, 102, 255));

        lb2.setForeground(new java.awt.Color(255, 0, 51));
        lb2.setText("*");

        lb3.setForeground(new java.awt.Color(255, 0, 51));
        lb3.setText("*");

        lb4.setForeground(new java.awt.Color(255, 0, 51));
        lb4.setText("*");

        lb5.setForeground(new java.awt.Color(255, 0, 51));
        lb5.setText("*");

        txtCodigo.setForeground(new java.awt.Color(51, 102, 255));

        lb1.setForeground(new java.awt.Color(255, 0, 51));
        lb1.setText("*");

        jLabel10.setForeground(new java.awt.Color(51, 102, 255));
        jLabel10.setText("Codigo del provedor");

        txtNombreContacto.setForeground(new java.awt.Color(51, 102, 255));

        jLabel12.setText("Contacto");

        jLabel13.setForeground(new java.awt.Color(51, 102, 255));
        jLabel13.setText("Nombre");

        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Apellido paterno");

        jLabel9.setForeground(new java.awt.Color(51, 102, 255));
        jLabel9.setText("Apellido materno");

        jLabel14.setForeground(new java.awt.Color(51, 102, 255));
        jLabel14.setText("Telefono celular");

        jLabel16.setForeground(new java.awt.Color(51, 102, 255));
        jLabel16.setText("Email");

        lb10.setForeground(new java.awt.Color(255, 0, 51));
        lb10.setText("*");

        lb9.setForeground(new java.awt.Color(255, 0, 51));
        lb9.setText("*");

        lb8.setForeground(new java.awt.Color(255, 0, 51));
        lb8.setText("*");

        lb7.setForeground(new java.awt.Color(255, 0, 51));
        lb7.setText("*");

        lb6.setForeground(new java.awt.Color(255, 0, 51));
        lb6.setText("*");

        txtAPaterno.setForeground(new java.awt.Color(51, 102, 255));

        txtAMaterno.setForeground(new java.awt.Color(51, 102, 255));

        txtNumCelularCont.setForeground(new java.awt.Color(51, 102, 255));

        txtEmailContact.setForeground(new java.awt.Color(51, 102, 255));

        Errors.setForeground(new java.awt.Color(51, 102, 255));
        jScrollPane5.setViewportView(Errors);

        btnCancel2.setText("Cancelar");
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        btnProcess.setText("Registrar");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Direccion");

        lb11.setForeground(new java.awt.Color(255, 0, 51));
        lb11.setText("*");

        txtDireccion.setForeground(new java.awt.Color(51, 102, 255));

        btnDireccion.setForeground(new java.awt.Color(51, 102, 255));
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
                                        .addComponent(lb5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(lb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lb3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb4, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodigo)
                                    .addComponent(cmbCategories, 0, 211, Short.MAX_VALUE)
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
                                            .addComponent(lb10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addComponent(lb11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccion))
                            .addComponent(btnDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(lb6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lb7)
                            .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNumCelularCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtEmailContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lb11)
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
                            .addComponent(lb1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtxRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNumEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lb5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcess)
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

    private void cmbCategoriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriesItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            categoria=(BDPuntoVentaManuel.MODEL.Catcategoria) this.cmbCategories.getSelectedItem();
        }
    }//GEN-LAST:event_cmbCategoriesItemStateChanged

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed

        this.LimpiarErrores();
        
        if (btnProcess.getText().equals("Registrar")) {
        if (this.Validar_Datos()) {
            if (valid) {
                BDPuntoVentaManuel.MODEL.Persona persona=getPersona();
                BDPuntoVentaManuel.MODEL.Contacto contact=getContact();
//                BDPuntoVentaManuel.MODEL.Supplier supplier=Suppliers_Start.viewDireccion.getSupplier();
                supplier=getSupplier();
                
                if(Suppliers_Start.SuppliersProcess.SaveSupplier( supplier,contact,persona));
                    this.Clean_Windows();
                    this.setVisible(false);
                {
                    Back();
                }

            } else {
                


            }
        } else {
//            LimpiarErrores();
            this.Errors.setListData(errors);
            this.Errors.setVisible(true);
        }
        }
        
        if (btnProcess.getText().equals("Modificar")) {
            if (this.Validar_Datos()) {
            if (true) {
                BDPuntoVentaManuel.MODEL.Persona persona=getPersonaForUpdate(supplier.getIdContacto().getIdPersona());
                BDPuntoVentaManuel.MODEL.Contacto contact=getContactForUpdate(supplier.getIdContacto());
//                BDPuntoVentaManuel.MODEL.Supplier supplier=Suppliers_Start.viewDireccion.getSupplier();
                supplier=GetSupplierForUpdate();
            if 
               (Suppliers_Start.SuppliersProcess.EditSupplier(supplier,contact,persona));
            this.Clean_Windows();
                    this.setVisible(false);
                    
                {
                    Back();
                }

            } else {


            }
        } else {
            LimpiarErrores();
        }
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void btnDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDireccionActionPerformed
        
   if(supplier !=null)
        {
        Suppliers_Start.viewDireccion.Open_Update_Windows(supplier);
        }else{
        Suppliers_Start.viewDireccion.Open_New_Windows(supplier);
        }
        valid=true;
        
    }//GEN-LAST:event_btnDireccionActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        this.setVisible(false);
                    
        Suppliers_Start.viewDefault.ResetDataTable();
        Suppliers_Start.viewDefault.setVisible(true);
    }//GEN-LAST:event_btnCancel2ActionPerformed


  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Errors;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnDireccion;
    private javax.swing.JButton btnProcess;
    private javax.swing.JComboBox cmbCategories;
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
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb10;
    private javax.swing.JLabel lb11;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lb9;
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
    private BDPuntoVentaManuel.MODEL.Supplier supplier;
    private BDPuntoVentaManuel.MODEL.Catcategoria categoria;
    private Vector errors;
    private boolean valid=false;

    private void ErrorsPaint() {
        this.Errors.setListData(this.errors);
        this.Errors.setVisible(true);
    }
    
}
