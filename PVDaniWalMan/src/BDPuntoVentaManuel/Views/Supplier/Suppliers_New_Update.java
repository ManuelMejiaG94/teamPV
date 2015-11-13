/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Supplier;


import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Persona;
import BDPuntoVentaManuel.MODEL.Supplier;
import static BDPuntoVentaManuel.Views.View_Start.Desktop;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import java.util.Vector;
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
    public void Open_Windows_New(boolean open) {
        this.btnRegistrar.setText("Registrar");
        this.Clean_Windows();
        this.Charge_Default_Data();

        this.setVisible(open);
    }
     public void Open_Windows_Update(Supplier _supplier) {
        this.btnRegistrar.setText("Modificar");
        this.Clean_Windows();
        this.Charge_Default_Data();

        this.Charge_Supplier_Modific(_supplier);

        this.setVisible(true);
    }
     private void Charge_Supplier_Modific(Supplier _supplier) {
         
        supplier = _supplier;
        String Estatus; 

        this.txtCodigo.setText(supplier.getStrClave());
        this.txtCodigo.setEnabled(false);
        if (supplier.getBoolEstatus()){
            Estatus = "activo";            
        }
        else {
            Estatus = "Inactivo";
        }

        this.txtNombreEmpresa.setText(supplier.getStrBussinessName());
        this.txtNumero.setText(supplier.getStrNumber());
        this.txtRazonSocial.setText(supplier.getStrDescripcion());
        this.txtDireccion.setText(supplier.getStrAddress());
        this.txtNombreContacto.setText(supplier.getIdContacto().getIdPersona().getStrNombre());
        this.txtAPaterno.setText(supplier.getIdContacto().getIdPersona().getStrAPaterno());
        this.txtAMaterno.setText(supplier.getIdContacto().getIdPersona().getStrAMaterno());
        this.txtNumCelularCont.setText(supplier.getIdContacto().getStrCellphone());
        this.txtEmailContact.setText(supplier.getIdContacto().getStrEmail());
        this.txtReferences.setText(supplier.getIdContacto().getStrReference());
        this.txtDescripcion.setText(supplier.getStrDescripcion());
        this.cmbCategories.setModel(Suppliers_Start.SuppliersProcess.getModelCategorias());

        this.cmbCategories.setSelectedItem(supplier.getIdCategoria());
        this.cmbCategories.setEnabled(false);
    }
     public void Charge_Default_Data() {
        this.cmbCategories.setModel(Suppliers_Start.SuppliersProcess.getModelCategorias());
    }
     private void Clean_Windows() {
        this.txtCodigo.setText(null);
        this.txtNombreEmpresa.setText(null);
        this.txtRazonSocial.setText(null);
        this.txtNumero.setText(null);
        this.txtDireccion.setText(null);
        this.txtAMaterno.setText(null);
        this.txtAPaterno.setText(null);
        this.txtNombreContacto.setText(null);
        this.txtNumCelularCont.setText(null);
        this.txtEmailContact.setText(null);
        this.txtReferences.setText(null);
        this.txtDescripcion.setText(null);
    
        

        this.lbError1.setVisible(false);
        this.lbError2.setVisible(false);
        this.lbError3.setVisible(false);
        this.lbError4.setVisible(false);
        this.lbError5.setVisible(false);
        this.lbError6.setVisible(false);
        this.lbError7.setVisible(false);
        this.lbError8.setVisible(false);
        this.lbError10.setVisible(false);
        this.lbError11.setVisible(false);
        this.lbError13.setVisible(false);
        this.lbError14.setVisible(false);
        this.lbError15.setVisible(false);
        
        
        
        this.ErrorsMessage.setVisible(false);
    }
     
     private boolean Validate_Data() {
        this.ListErros = new Vector();

        boolean Result = true;
        if (StringUtils.isNullOrEmpty(this.txtCodigo.getText())) {
            this.lbError13.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtNombreEmpresa.getText())) {
            this.lbError6.setVisible(true);
            Result = false;
        }
        
        if (StringUtils.isNullOrEmpty(this.txtDireccion.getText())) {
            this.lbError8.setVisible(true);
            Result = false;
        }
                
        if (StringUtils.isNullOrEmpty(this.txtRazonSocial.getText())) {
            this.lbError1.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtNumero.getText())) {
            this.lbError3.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtDescripcion.getText())) {
            this.lbError13.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtNombreContacto.getText())) {
            this.lbError5.setVisible(true);
            Result = false;
        }
        
        if (StringUtils.isNullOrEmpty(this.txtAMaterno.getText())) {
            this.lbError14.setVisible(true);
            Result = false;
        }
                
        if (StringUtils.isNullOrEmpty(this.txtAPaterno.getText())) {
            this.lbError11.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtNumCelularCont.getText())) {
            this.lbError7.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtEmailContact.getText())) {
            this.lbError10.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtReferences.getText())) {
            this.lbError15.setVisible(true);
            Result = false;
        }
        

        if (!Result) {
            String error = "Los campos marcados con * son obligatorios";
            this.ListErros.add(error);
        }

        if (this.cmbCategories.getSelectedIndex() <= 0) {
            String error = "Es necesario indicar una categoria";

            this.ListErros.add(error);
            this.ErrorsMessage.setVisible(true);
            Result = false;
        }

        return Result;
    }
     
     private void ErrorsPaint() {
        this.ErrorsMessage.setListData(this.ListErros);
        this.ErrorsMessage.setVisible(true);
        this.VisitErrors = true;

    }

    private void Clear_Erros() {
        this.lbError1.setVisible(false);
        this.lbError8.setVisible(false);
        this.lbError3.setVisible(false);
        this.lbError5.setVisible(false);
        this.lbError13.setVisible(false);
                

        this.txtDescripcion.setVisible(false);
    }
    
    private void PaintErrorExistenSupplier() {

        String error = "El producto con el codigo dado ya exite en tu repletorio";
        this.ListErros.add(error);
        this.lbError1.setVisible(true);

        this.ErrorsPaint();
    }
    
    private Supplier GetSupplierNew() {
        supplier = new Supplier();
        supplier.setStrClave(this.txtCodigo.getText().trim());
        supplier.setStrBussinessName(this.txtNombreEmpresa.getText().trim());
        supplier.setStrNumber(this.txtRazonSocial.getText().trim());
        supplier.setStrDescripcion(this.txtDescripcion.getText().trim());
        supplier.setStrAddress(this.txtDireccion.getText().trim());
        supplier.setBoolEstatus(true);
    
        
        supplier.setIdCategoria(this.categoriaSupplier);
        
        
        

        return supplier;
    }
    
    private Persona GetPerson()
    {
        person =new Persona();
        person.setStrAMaterno(this.txtAMaterno.getText().trim());
        person.setStrAPaterno(this.txtAPaterno.getText().trim());
        person.setStrNombre(this.txtNombreEmpresa.getText().trim());
        
        return person;
    }
    
    private Contacto getContacto()
    {
        contact =new Contacto();
        contact=new Contacto();
        contact.setStrCellphone(this.txtNumCelularCont.getText().trim());
        contact.setStrEmail(this.txtEmailContact.getText().trim());
     
        
        
        return contact;
    }
    
    private Supplier GetSupplierForUpdate() {

        supplier.setStrClave(this.txtCodigo.getText().trim());
        supplier.setStrBussinessName(this.txtNombreEmpresa.getText().trim());
        supplier.setStrNumber(this.txtNumCelularCont.getText().trim());
        supplier.setStrAddress(this.txtDireccion.getText().trim());
        supplier.setIdCategoria(this.categoriaSupplier);
        supplier.setStrDescripcion(this.txtDescripcion.getText().trim());
 

        return supplier;
    }
    
    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {
        if (txtCodigo.getText().length()== 15)
        {
            evt.consume();
        }
    }

    private void txtNombreContactoKeyTyped(java.awt.event.KeyEvent evt) {
           char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
        if (txtNombreEmpresa.getText().length()== 10)
        {
            evt.consume();
        }
    }

    private void txttxtNumCelularContKeyTyped(java.awt.event.KeyEvent evt) {
        if (txtNumCelularCont.getText().length()== 10)
        {
            evt.consume();
        }
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
        txtRazonSocial = new javax.swing.JTextField();
        cmbCategories = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
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
        lbError5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ErrorsMessage = new javax.swing.JList();
        btnCancel2 = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbError8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnDireccion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        lbError6 = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        lbError14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbError11 = new javax.swing.JLabel();
        txtAMaterno = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lbError7 = new javax.swing.JLabel();
        txtNumCelularCont = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lbError10 = new javax.swing.JLabel();
        txtEmailContact = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lbError15 = new javax.swing.JLabel();
        txtReferences = new javax.swing.JTextField();

        jLabel2.setText("Datos de la empresa");

        jLabel3.setText("Razon social");

        jLabel5.setText("Categoria");

        cmbCategories.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriesItemStateChanged(evt);
            }
        });

        jLabel8.setText("Descripcion de la empresa");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel17.setText("Numero");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

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

        lbError5.setForeground(new java.awt.Color(255, 0, 51));
        lbError5.setText("*");

        ErrorsMessage.setForeground(new java.awt.Color(255, 0, 0));
        jScrollPane5.setViewportView(ErrorsMessage);

        btnCancel2.setText("Cancelar");
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
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

        jLabel6.setText("Nombre Empresa");

        txtNombreEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpresaKeyTyped(evt);
            }
        });

        lbError6.setForeground(new java.awt.Color(255, 0, 51));
        lbError6.setText("*");

        lbError14.setForeground(new java.awt.Color(255, 0, 51));
        lbError14.setText("*");

        jLabel7.setText("Apellido paterno");

        jLabel9.setText("Apellido materno");

        lbError11.setForeground(new java.awt.Color(255, 0, 51));
        lbError11.setText("*");

        txtAMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAMaternoActionPerformed(evt);
            }
        });

        jLabel14.setText("Telefono celular");

        lbError7.setForeground(new java.awt.Color(255, 0, 51));
        lbError7.setText("*");

        jLabel16.setText("Email");

        lbError10.setForeground(new java.awt.Color(255, 0, 51));
        lbError10.setText("*");

        jLabel11.setText("Referencias");

        lbError15.setForeground(new java.awt.Color(255, 0, 51));
        lbError15.setText("*");

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
                                .addGap(103, 103, 103)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbError13, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbError6, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbError4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbError2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbError1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbError3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbError8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCategories, 0, 211, Short.MAX_VALUE)
                            .addComponent(txtRazonSocial)
                            .addComponent(txtNumero)
                            .addComponent(txtDireccion))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(lbError5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbError10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbError7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbError11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbError14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(lbError15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtReferences)
                                            .addComponent(txtEmailContact)
                                            .addComponent(txtNumCelularCont)
                                            .addComponent(txtAPaterno)
                                            .addComponent(txtAMaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbError14)
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
                            .addComponent(lbError7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtEmailContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbError15)
                            .addComponent(txtReferences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDireccion)
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnCancel2))
                        .addGap(158, 158, 158))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbError13)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbError6))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbError3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbError8)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbError4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriesItemStateChanged
         if(evt.getStateChange()==ItemEvent.SELECTED)
        {
           this.categoriaSupplier=(Catcategoria) this.cmbCategories.getSelectedItem();
        }
    }//GEN-LAST:event_cmbCategoriesItemStateChanged

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
          if (this.VisitErrors) {
            this.Clear_Erros();
        }
        if (btnRegistrar.getText().equals("Registrar")) {
            if (this.Validate_Data()) {
                String Codigo = txtCodigo.getText().trim();
                Supplier item = Suppliers_Start.SuppliersProcess.GetSupplierByCode(Codigo);
                if (item == null) {
                    Suppliers_Start.SuppliersProcess.SaveSupplier(this.GetSupplierNew());
                    this.Clean_Windows();
                    this.setVisible(false);

                    Suppliers_Start.viewDefault.ResetDataTable();
                    Suppliers_Start.viewDefault.setVisible(true);
                } else {
                    this.PaintErrorExistenSupplier();
                }
            } else {

                this.ErrorsPaint();
            }
        }
        if (btnRegistrar.getText().equals("Modificar")) {
            String result;
            if (this.Validate_Data()) {
                result = Suppliers_Start.SuppliersProcess.EditSupplier(GetSupplierForUpdate());
                if (result == null) {
                    this.setVisible(false);
                    Suppliers_Start.viewDefault.ResetDataTable();
                    Suppliers_Start.viewDefault.setVisible(true);
                } else {
                    this.ListErros.add(result);
                }

            } else {
                this.ErrorsPaint();
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed

        this.setVisible(false);
                    
        Suppliers_Start.viewDefault.ResetDataTable();
        Suppliers_Start.viewDefault.setVisible(true);
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

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtNombreEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpresaKeyTyped
        if (txtNombreEmpresa.getText().length()== 25)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreEmpresaKeyTyped

    private void txtAMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAMaternoActionPerformed


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
    private javax.swing.JList ErrorsMessage;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnDireccion;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cmbCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbError1;
    private javax.swing.JLabel lbError10;
    private javax.swing.JLabel lbError11;
    private javax.swing.JLabel lbError13;
    private javax.swing.JLabel lbError14;
    private javax.swing.JLabel lbError15;
    private javax.swing.JLabel lbError2;
    private javax.swing.JLabel lbError3;
    private javax.swing.JLabel lbError4;
    private javax.swing.JLabel lbError5;
    private javax.swing.JLabel lbError6;
    private javax.swing.JLabel lbError7;
    private javax.swing.JLabel lbError8;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmailContact;
    private javax.swing.JTextField txtNombreContacto;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNumCelularCont;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtReferences;
    // End of variables declaration//GEN-END:variables

    //Objects view
    Suppliers_Direccion view_Direction;
    
    private Catcategoria categoriaSupplier;
    private boolean VisitErrors=false;
    Vector ListErros;
    private Supplier supplier;
    private Persona person;
    private Contacto contact;
    
}
