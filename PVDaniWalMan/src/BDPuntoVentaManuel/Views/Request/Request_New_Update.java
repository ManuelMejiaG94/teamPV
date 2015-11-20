/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Request;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.MODEL.Supplier;
import BDPuntoVentaManuel.Views.Product.Products_Start;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Request_New_Update extends javax.swing.JPanel {

    /**
     * Creates new form Orders_New_Update
     */
    public Request_New_Update() {
        initComponents();
        Open_NewWindows();
    }
    
    public void Open_NewWindows()
    {
        ChargeDataDefault();
        PaintViewNewDefault();
    }

    private void Start_tools()
    {
        
    }

    private void ChargeDataDefault()
    {
        this.cmbCategoria.setModel(Request_Start.RequestProcess.getModelCategorias());
        this.lbFolio.setText(Request_Start.RequestProcess.GetFolio());
        this.Process_cmb();
        this.btnCreateRequest.setText("Crear nueva solicitud");
        this.txtAmount.setText("1");
        this.txtSubTotal.setEnabled(false);
        this.txtTotalVenta.setEnabled(false);
    }
    
    private void PaintViewNewDefault()
    {
        this.pn1.setVisible(false);
        this.pn2.setVisible(false);
        
        this.cmbProduct.setVisible(false);
        this.txtAmount.setVisible(false);
        this.lbProductCode.setVisible(false);
        this.lbAmount.setVisible(false);
        
        this.CleanErrors();
        this.ChargeModelTable();
        
        this.cmbCategoria.setEnabled(true);
        this.cmbSupplier.setEnabled(true);
        
        this.btnExit.setVisible(true);
        this.btnCharge.setVisible(false);
    }
    
    private void PaintViewNewRequest()
    {
        this.pn1.setVisible(true);
        this.pn2.setVisible(true);
        
        this.cmbProduct.setVisible(true);
        this.txtAmount.setVisible(true);
        this.lbProductCode.setVisible(true);
        this.lbAmount.setVisible(true);
        
        this.CleanErrors();
        this.ChargeModelTable();
        
        this.cmbCategoria.setEnabled(false);
        this.cmbSupplier.setEnabled(false);
        
        this.btnExit.setVisible(false);
        this.btnCharge.setVisible(true);
    }
    
    private void ChargeModelTable()
    {
        modelTab=new DefaultTableModel();
        modelTab.addColumn("Clave");
        modelTab.addColumn("Nombre");
        modelTab.addColumn("Precio de compra");
        modelTab.addColumn("Precio de venta");
        modelTab.addColumn("Cantidad");
        modelTab.addColumn("Total");
        
        this.tbData.setModel(modelTab);
    }
    
    private void Process_cmb()
    {   
        if (this.categoria !=null && this.categoria.getId()>0)
        {    
            this.cmbProduct.setModel(Request_Start.RequestProcess.getModelProducts(categoria));
            this.cmbSupplier.setModel(Request_Start.RequestProcess.getModelSuppliers(categoria));
        }else{
            this.cmbProduct.setModel(Request_Start.RequestProcess.getModelProductsDefault(categoria));
            this.cmbSupplier.setModel(Request_Start.RequestProcess.getModelSuppliersDefault(categoria));
        }
    }
    
    private void PaintErrorProductNull()
    {
        this.lbErrorsMessage.setVisible(true);
        this.lbError1.setVisible(true);
        this.lbErrorsMessage.setText("Es necesario seleccionar un producto");
        this.validError=true;
    }
    
    private void PaintErrorAmountNull()
    {
        this.lbErrorsMessage.setVisible(true);
        this.lbError1.setVisible(true);
        this.lbErrorsMessage.setText("Es necesario asignar una cantidad valida");
        this.validError=true;
    }
    
     private void PaintErrorSupplierNull()
    {
        this.lbErrorsMessage.setVisible(true);
        this.lbError1.setVisible(true);
        this.lbErrorsMessage.setText("Es necesario seleccionar un provedor");
        this.validError=true;
    }
    
    private void CleanErrors()
    {
        this.lbError1.setVisible(false);
        this.lbError2.setVisible(false);
        this.lbErrorMInTB.setVisible(false);
        this.lbErrorsMessage.setVisible(false);
        
        this.validError=false;
    }
    
    private void SetDataTable(int stock)
    {
        lbErrorsMessage.setText(null);
        total=total+Request_Start.RequestProcess.SetDataTableNewUpdate(modelTab, product,stock,lbErrorsMessage);
        if(StringUtils.isNullOrEmpty(this.lbErrorsMessage.getText()))
        {
            this.tbData.setModel(modelTab);
            this.txtSubTotal.setText(String.valueOf(total));
            double totalRequest=total+(IVA*total)/100;
            this.txtTotalVenta.setText(String.valueOf(totalRequest));
        }else
        {
            this.lbErrorsMessage.setVisible(true);
            this.validError=true;
        }
    }
    
    private void CleanViewSelectProduct()
    {
        this.txtAmount.setText("1");
        this.cmbProduct.setSelectedIndex(0);
    }
    
    private List<String[]> GetProductClaveByTabData()
    {
        List<String[]> listData=new ArrayList<String[]>();
        int rows=tbData.getRowCount();
        for (int i = 0; i < rows; i++) {
            String[] data=new String[4];
            data[0]=tbData.getValueAt(i,0).toString();
            data[1]=tbData.getValueAt(i,2).toString();
            data[2]=tbData.getValueAt(i,4).toString();
            data[3]=tbData.getValueAt(i,5).toString();
            listData.add(data);
        }
        return listData;
    }
    
    private BDPuntoVentaManuel.MODEL.Request GetRequest()
    {
        BDPuntoVentaManuel.MODEL.Request request=new BDPuntoVentaManuel.MODEL.Request();
        request.setIdSuplier(supplier);
        request.setBitEstatus(1);
        request.setDatFecha(dateNow);
        request.setDoubTotal(Double.parseDouble(this.txtTotalVenta.getText().trim()));

        return request;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lbErrorMInTB = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbProductCode = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();
        lbError2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnCharge = new javax.swing.JButton();
        lbErrorsMessage = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        cmbProduct = new javax.swing.JComboBox();
        cmbSupplier = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        btnCreateRequest = new javax.swing.JButton();
        lbFolio = new javax.swing.JLabel();
        lbError1 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        pn2 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        pn1.setBackground(new java.awt.Color(255, 255, 255));

        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbData);

        jLabel4.setForeground(new java.awt.Color(51, 204, 255));
        jLabel4.setText("Productos de la solicitud");

        jLabel5.setForeground(new java.awt.Color(51, 204, 255));
        jLabel5.setText("Sub total");

        btnDelete.setForeground(new java.awt.Color(51, 204, 255));
        btnDelete.setText("Retirar");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbErrorMInTB.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorMInTB.setText("jLabel4");

        javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
        pn1.setLayout(pn1Layout);
        pn1Layout.setHorizontalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn1Layout.createSequentialGroup()
                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn1Layout.createSequentialGroup()
                                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbErrorMInTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pn1Layout.createSequentialGroup()
                                        .addComponent(btnDelete)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        pn1Layout.setVerticalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorMInTB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(btnDelete))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbProductCode.setForeground(new java.awt.Color(51, 204, 255));
        lbProductCode.setText("Codigo del producto");

        lbAmount.setForeground(new java.awt.Color(51, 204, 255));
        lbAmount.setText("Cantidad");

        lbError2.setForeground(new java.awt.Color(255, 0, 0));
        lbError2.setText("*");

        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountKeyTyped(evt);
            }
        });

        btnCharge.setForeground(new java.awt.Color(51, 204, 255));
        btnCharge.setText("Cargar");
        btnCharge.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChargeActionPerformed(evt);
            }
        });

        lbErrorsMessage.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorsMessage.setText("jLabel4");

        jLabel9.setForeground(new java.awt.Color(51, 204, 255));
        jLabel9.setText("Folio");

        jLabel1.setForeground(new java.awt.Color(51, 204, 255));
        jLabel1.setText("Categoria");

        cmbCategoria.setForeground(new java.awt.Color(51, 204, 255));
        cmbCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriaItemStateChanged(evt);
            }
        });

        cmbProduct.setForeground(new java.awt.Color(51, 204, 255));
        cmbProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProductItemStateChanged(evt);
            }
        });

        cmbSupplier.setForeground(new java.awt.Color(51, 204, 255));
        cmbSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbSupplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSupplierItemStateChanged(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(51, 204, 255));
        jLabel10.setText("Provedor");

        btnCreateRequest.setForeground(new java.awt.Color(51, 204, 255));
        btnCreateRequest.setText("Crear Solicictud");
        btnCreateRequest.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreateRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRequestActionPerformed(evt);
            }
        });

        lbFolio.setForeground(new java.awt.Color(51, 204, 255));
        lbFolio.setText("jLabel6");
        lbFolio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbFolio.setVerifyInputWhenFocusTarget(false);

        lbError1.setForeground(new java.awt.Color(255, 0, 0));
        lbError1.setText("*");

        btnExit.setForeground(new java.awt.Color(51, 204, 255));
        btnExit.setText("Salir");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbErrorsMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbProductCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addComponent(lbAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbError2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbError1)
                                                .addGap(2, 2, 2))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreateRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAmount)
                            .addComponent(cmbProduct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFolio, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbFolio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(lbError1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateRequest)
                    .addComponent(btnExit))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProductCode)
                    .addComponent(lbError2)
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorsMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCharge)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn2.setBackground(new java.awt.Color(255, 255, 255));

        btnOk.setForeground(new java.awt.Color(51, 204, 255));
        btnOk.setText("Generar solicitud");
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setForeground(new java.awt.Color(51, 204, 255));
        btnCancel.setText("Cancelar");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(51, 204, 255));
        jLabel7.setText("Total");

        javax.swing.GroupLayout pn2Layout = new javax.swing.GroupLayout(pn2);
        pn2.setLayout(pn2Layout);
        pn2Layout.setHorizontalGroup(
            pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn2Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pn2Layout.setVerticalGroup(
            pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel)
                    .addComponent(jLabel7)
                    .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (this.tbData.getSelectedRow() >= 0) {
            int Registro = this.tbData.getSelectedRow();
            double totalActual = Double.parseDouble(this.txtSubTotal.getText());
            double totalProduct = Double.parseDouble(tbData.getValueAt(Registro, 5).toString());
            double subtotalRequest = totalActual - totalProduct;
            this.txtSubTotal.setText(String.valueOf(subtotalRequest));
            double totalRequest = subtotalRequest+(IVA * subtotalRequest) / 100;
            
            String clave = tbData.getValueAt(Registro, 0).toString();
            int stock = Integer.parseInt(tbData.getValueAt(Registro, 4).toString());

            this.txtTotalVenta.setText(String.valueOf(totalRequest));

            if (Request_Start.RequestProcess.Reset_Stock(clave, stock)) {
                DefaultTableModel dtm = (DefaultTableModel) this.tbData.getModel();
                dtm.removeRow(this.tbData.getSelectedRow());

                this.lbErrorMInTB.setVisible(false);
            } else {
                this.lbErrorMInTB.setText("No fue posible resetear el stock del producto");
                this.lbErrorMInTB.setVisible(true);
            }
        } else {
            this.lbErrorMInTB.setText("Es necesario seleccionar un registro");
            this.lbErrorMInTB.setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyTyped

        char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
        if (txtAmount.getText().length()== 6)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtAmountKeyTyped

    private void btnChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChargeActionPerformed
        if (this.cmbProduct.getSelectedIndex() > 0) {
            if (!StringUtils.isNullOrEmpty(this.txtAmount.getText())){
                int cantidad = Integer.parseInt(this.txtAmount.getText().trim());
                SetDataTable(cantidad);
                CleanViewSelectProduct();
            }else{
                this.PaintErrorAmountNull();
            }
            
        } else {
            this.PaintErrorProductNull();
        }
    }//GEN-LAST:event_btnChargeActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if(Request_Start.RequestProcess.SaveRequest(this.GetProductClaveByTabData(),
                this.GetRequest(), total))
            this.Open_NewWindows();
        {
            this.PaintViewNewDefault();
            this.btnCreateRequest.setText("Crear nueva solicitud");
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        //        this.process.btnCancel(this);
        Request_Start.pnOptions.setVisible(true);
        Request_Start.viewRequest.setVisible(false);
        Request_Start.viewSelect.setVisible(false);
        Request_Start.viewDefault.setVisible(true);
        Request_Start.viewProcess.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriaItemStateChanged
          if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            this.categoria=(Catcategoria) this.cmbCategoria.getSelectedItem();
            Process_cmb();
        }
    }//GEN-LAST:event_cmbCategoriaItemStateChanged

    private void cmbSupplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSupplierItemStateChanged
          if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            this.supplier=(Supplier) this.cmbSupplier.getSelectedItem();
        }
    }//GEN-LAST:event_cmbSupplierItemStateChanged

    private void cmbProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProductItemStateChanged
          if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            this.product=(Product) this.cmbProduct.getSelectedItem();
        }
    }//GEN-LAST:event_cmbProductItemStateChanged

    private void btnCreateRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRequestActionPerformed
        
        if (validError) {
            CleanErrors();
        }
        if (this.btnCreateRequest.getText().equals("Crear nueva solicitud")) {
            if (this.cmbSupplier.getSelectedIndex() > 0) {
                this.btnCreateRequest.setText("Cancelar proceso");
                this.PaintViewNewRequest();
            } else {
                PaintErrorSupplierNull();
            }
        }else if (this.btnCreateRequest.getText().equals("Cancelar proceso")) {
            this.PaintViewNewDefault();
            this.btnCreateRequest.setText("Crear nueva solicitud");
        }
    }//GEN-LAST:event_btnCreateRequestActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
            Request_Start.pnOptions.setVisible(true);
            Request_Start.viewRequest.setVisible(false);
            Request_Start.viewSelect.setVisible(false);
            Request_Start.viewDefault.setVisible(true);
            Request_Start.viewProcess.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCharge;
    private javax.swing.JButton btnCreateRequest;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JComboBox cmbProduct;
    private javax.swing.JComboBox cmbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbError1;
    private javax.swing.JLabel lbError2;
    private javax.swing.JLabel lbErrorMInTB;
    private javax.swing.JLabel lbErrorsMessage;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JLabel lbProductCode;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pn2;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalVenta;
    // End of variables declaration//GEN-END:variables

    //Variables
    private Supplier supplier;
    private Catcategoria categoria=null;
    private Product product;
    private DefaultTableModel modelTab;
    private boolean validError=false;
    private double total=0;
    private double IVA=0;
    
    private Date dateNow = new Date(System.currentTimeMillis());

}
