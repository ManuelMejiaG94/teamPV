/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Sales;

import BDPuntoVentaManuel.MODEL.Product;
import BDPuntoVentaManuel.Views.View_Start;
import com.mysql.jdbc.StringUtils;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Sales_Start extends javax.swing.JInternalFrame {

    /**
     * Creates new form Sales_Start
     */
    public Sales_Start() {
        initComponents();
        New_Windows();
    }
    
    public void New_Windows()
    {
        salesProcess=new Sales();
        listProducts=new ArrayList<Product>();
        this.btnOk.setText("Cerrar venta");
        this.modelo=new DefaultTableModel();
        this.Carge_Model();
        this.tbData.setModel(modelo);
        this.ClearWindws();
        this.Defaultdata();
        this.BloquearValidateData();
    }
    private void BloquearValidateData()
    {
        this.txtCambio.setEditable(false);
        this.txtSubTotal.setEditable(false);
        this.txtSubTotal.setText("0.00");
        this.txtTotalVenta.setEditable(false);
        this.txtTotalVenta.setText("0.00");
        this.txtCantidad.setText("1");
        this.txtCambio.setText("0.00");
        this.txtEfectivo.setText(null);
    }
    private void ClearWindws()
    {
        this.lbErrorsMessage.setVisible(false);
        this.lbError1.setVisible(false);
        this.lbError2.setVisible(false);
        this.lbErrorMInTB.setVisible(false);
        this.lbErrorBtnClose.setVisible(false);
    }
    private void Defaultdata()
    {
        this.lbFolio.setText(String.valueOf(salesProcess.GetFolio()));
    }
    private void Carge_Model()
    {
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Presentacion");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
    }
    private boolean Validate_Charge()
    {
        boolean result=true;
                
        if(StringUtils.isNullOrEmpty(this.txtCodigo.getText()))
        {
            this.lbErrorsMessage.setText("Es necesario ingresar un codigo de producto");
            this.lbErrorsMessage.setVisible(true);
            this.lbError1.setVisible(true);
            result=false;
        }
        if(StringUtils.isNullOrEmpty(this.txtCantidad.getText()))
        {
            this.txtCantidad.setText(String.valueOf(this.Cantidad_Default));
        }   
        if(txtCantidad.getText().equalsIgnoreCase("0"))
        {
            this.lbErrorsMessage.setText("Es necesario ingresar una cantidad apropiada");
            this.lbErrorsMessage.setVisible(true);
        }
        return result;
    }
    private void CleanDataCharge()
    {
        this.txtCodigo.setText(null);
        this.txtCantidad.setText("1");
    }
    private boolean Validate_Sale()
    {
        if(StringUtils.isNullOrEmpty(this.txtEfectivo.getText()))
        {
            this.lbErrorBtnClose.setText("Los campos marcados con * son obligatorios");
            this.lbError2.setVisible(true);
            this.lbErrorBtnClose.setVisible(true);
            return false;
        }
        return true;
    }
    private void PaintErrorProductNull()
    {
            this.lbErrorsMessage.setText("No existe un producto registrado con el codigo "
                    + "solicitado");
            this.lbErrorsMessage.setVisible(true);
            this.lbError1.setVisible(true);
    }
    private void CleanErrorProductNull()
    {
            this.lbErrorsMessage.setVisible(false);
            this.lbError1.setVisible(false);
    }
    private void PaintCostValues(double total, double subtotal)
    {
        this.txtSubTotal.setText(View_Start.currencySystem.format(subtotal));
        this.txtTotalVenta.setText(View_Start.currencySystem.format(total));
    }
    private void PaintErroSelectedData()
    {
          this.lbErrorMInTB.setText("Es necesario seleccionar un registro");
          this.lbErrorMInTB.setVisible(true);
    }
    private void CleanErrorSelectedData()
    {
        this.lbErrorMInTB.setVisible(false);
    }
    private boolean ValidateTotal()
    {
        boolean validate=true;
        if (StringUtils.isNullOrEmpty(this.txtEfectivo.getText())) {
            this.lbErrorBtnClose.setText("Es necesario ingresar el monto dado por el cliente");
            this.lbErrorBtnClose.setVisible(true);
             validate= false;
        }
        
        return validate;
    }
    private void clearErrorValidateTotal()
    {
        this.lbErrorBtnClose.setVisible(false);
    }
    private void PaintErrorAddToSale()
    {
        this.lbErrorsMessage.setText("El producto ya ha sido agregado a la venta");
        this.lbErrorsMessage.setVisible(true);
    }
    private void CleanErrorAddToSale()
    {
        this.lbErrorsMessage.setVisible(false);
    }
    private void PaintErrorFailedCreateSale()
    {
        this.lbErrorsMessage.setText("No fue posible crear la venta");
        this.lbErrorsMessage.setVisible(true);
    }
    private void PaintErrorStock()
    {
        this.lbErrorsMessage.setText("No cuentas con las unidades solicitadas para surtir el pedido");
        this.lbErrorsMessage.setVisible(true);
    }
    private void ClearErrorFailedCreateSale()
    {
        this.lbErrorsMessage.setVisible(false);
    }
    private List<String[]> GetObjectProduct()
    {
        int rows=this.tbData.getRowCount();
        listObjectProduct=new ArrayList<>();
        
        
        for (int i = 0; i < rows; i++) {
            String []data=new String[4];
            data[0]=this.tbData.getValueAt(i,0).toString();
            data[1]=this.tbData.getValueAt(i,4).toString();
            data[2]=this.tbData.getValueAt(i,2).toString();
            data[3]=this.tbData.getValueAt(i,5).toString();
            
            
            listObjectProduct.add(data);
        }

        return listObjectProduct;
    }
    
    
    private BDPuntoVentaManuel.MODEL.Sales getSale()
    {
        Date now = new Date(System.currentTimeMillis());

        BDPuntoVentaManuel.MODEL.Sales sale=new BDPuntoVentaManuel.MODEL.Sales();
        sale.setDobTotal(Total);
        sale.setDatFecha(now);
        sale.setIva(IVA);
        
        return sale;
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lbErrorMInTB = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbError1 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnCharge = new javax.swing.JButton();
        lbErrorsMessage = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lbErrorBtnClose = new javax.swing.JLabel();
        lbError2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Nueva Venta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbData.setForeground(new java.awt.Color(51, 102, 255));
        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbData);

        jLabel4.setForeground(new java.awt.Color(51, 204, 255));
        jLabel4.setText("Detalle de la venta");

        txtSubTotal.setForeground(new java.awt.Color(51, 102, 255));

        jLabel5.setForeground(new java.awt.Color(51, 102, 255));
        jLabel5.setText("Sub total");

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setForeground(new java.awt.Color(51, 102, 255));
        btnDelete.setText("Retirar");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbErrorMInTB.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorMInTB.setText("jLabel4");

        txtTotalVenta.setForeground(new java.awt.Color(51, 102, 255));

        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbErrorMInTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorMInTB)
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Codigo del producto");

        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Cantidad");

        txtCodigo.setForeground(new java.awt.Color(51, 102, 255));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lbError1.setForeground(new java.awt.Color(255, 0, 0));
        lbError1.setText("*");

        txtCantidad.setForeground(new java.awt.Color(51, 102, 255));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        btnCharge.setBackground(new java.awt.Color(255, 255, 255));
        btnCharge.setForeground(new java.awt.Color(51, 102, 255));
        btnCharge.setText("Cargar");
        btnCharge.setContentAreaFilled(false);
        btnCharge.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChargeActionPerformed(evt);
            }
        });

        lbErrorsMessage.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorsMessage.setText("jLabel4");

        jLabel9.setForeground(new java.awt.Color(51, 102, 255));
        jLabel9.setText("Folio");

        lbFolio.setForeground(new java.awt.Color(51, 102, 255));
        lbFolio.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbErrorsMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(lbError1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtCodigo)
                            .addComponent(lbFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbFolio))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbError1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbErrorsMessage)
                .addGap(19, 19, 19)
                .addComponent(btnCharge)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setForeground(new java.awt.Color(51, 102, 255));
        jLabel6.setText("Efectivo");

        txtEfectivo.setForeground(new java.awt.Color(51, 102, 255));
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Cambio");

        txtCambio.setForeground(new java.awt.Color(51, 102, 255));

        btnOk.setBackground(new java.awt.Color(255, 255, 255));
        btnOk.setForeground(new java.awt.Color(51, 102, 255));
        btnOk.setText("Cerrar venta");
        btnOk.setContentAreaFilled(false);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setForeground(new java.awt.Color(51, 102, 255));
        btnCancel.setText("Cancelar venta");
        btnCancel.setContentAreaFilled(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lbErrorBtnClose.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorBtnClose.setText("jLabel4");

        lbError2.setForeground(new java.awt.Color(255, 0, 0));
        lbError2.setText("*");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbErrorBtnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(44, 44, 44)
                        .addComponent(lbError2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel)
                    .addComponent(lbError2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorBtnClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(this.tbData.getSelectedRow()>=0)
        {
            this.CleanErrorSelectedData();
            int Registro=this.tbData.getSelectedRow();
            String clave=this.tbData.getValueAt(Registro, 0).toString();
            int cantidad = Integer.parseInt(this.tbData.getValueAt(Registro, 2).toString());
            
            BDPuntoVentaManuel.MODEL.Product product=salesProcess.GetProductByClave(clave);
            subTotal=subTotal-(product.getDobPV()*cantidad);
            Total=(subTotal+((IVA*subTotal)/100));
            this.PaintCostValues(Total, subTotal);
            
            DefaultTableModel dtm = (DefaultTableModel) this.tbData.getModel();
            dtm.removeRow(this.tbData.getSelectedRow());
            
            salesProcess.RestarProductStock(product, cantidad);

        }else{
          this.PaintErroSelectedData();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        if (txtCodigo.getText().length()== 25)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped

        char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
        if (txtCantidad.getText().length()== 6)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChargeActionPerformed
        if (this.Validate_Charge()) {
            int cantidad = Integer.parseInt(this.txtCantidad.getText().trim());
            BDPuntoVentaManuel.MODEL.Product product=salesProcess.GetProductByClave(this.txtCodigo.getText().trim());
            
            if(product !=null)
            {
                if(product.getIntStock()>cantidad)
                {  
                if (!listProducts.contains(product)) {
                    
                    this.CleanErrorAddToSale();
                    this.CleanErrorProductNull();
                    salesProcess.AddProductToSale(modelo, product, cantidad);
                    subTotal = subTotal + (product.getDobPV() * cantidad);
                    Total = (subTotal + ((IVA * subTotal) / 100));
                    this.PaintCostValues(Total, subTotal);
                    this.tbData.setModel(modelo);
                    product.setIntStock(Double.parseDouble(String.valueOf(cantidad)));
                    listProducts.add(product);
                    this.CleanDataCharge();
                } else {
                    this.PaintErrorAddToSale();                
                }
                }else{
                    this.PaintErrorStock();
                }
            }else{
                this.PaintErrorProductNull();
            }
        }

    }//GEN-LAST:event_btnChargeActionPerformed

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.')
        {
            evt.consume();
        }
        if(evt.getKeyChar()=='.' && txtEfectivo.getText().contains("."))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtEfectivoKeyTyped

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

        
        
        if(btnOk.getText().equals("Finalizar Venta"))
        {
            if(this.Validate_Sale()){
                if(salesProcess.CreateNewSale(this.getSale(), this.GetObjectProduct()))
                {
                    this.ClearErrorFailedCreateSale();
                    this.btnOk.setText("Cerrar venta");
                    this.New_Windows();
                    
                }else
                {
                    this.PaintErrorFailedCreateSale();
                }
            }
        }else if(btnOk.getText().equals("Cerrar venta")){
            if(ValidateTotal())
            {
                clearErrorValidateTotal();
                double _total=Total;//Double.parseDouble(this.txtTotalVenta.getText());
                double _efectivo=Double.parseDouble(this.txtEfectivo.getText());
                double cambio=_efectivo-_total;
                if(cambio>=0)
                {
                    
                    this.lbErrorBtnClose.setVisible(false);
                    this.txtCambio.setText(View_Start.currencySystem.format(cambio));
                    this.btnOk.setText("Finalizar Venta");
                }else{
                    this.lbErrorBtnClose.setText("La cantidad dada no cubre el gasto de la venta");
                    this.lbErrorBtnClose.setVisible(true);
                }
                
            }
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        salesProcess.RestarListProductsStock(listProducts);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCharge;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbError1;
    private javax.swing.JLabel lbError2;
    private javax.swing.JLabel lbErrorBtnClose;
    private javax.swing.JLabel lbErrorMInTB;
    private javax.swing.JLabel lbErrorsMessage;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalVenta;
    // End of variables declaration//GEN-END:variables

    //Variables
    private double Total=0;
    private double subTotal=0;
    private double IVA=5;
    private int Cantidad_Default=1;
    private DefaultTableModel modelo;
    private List<Product> listProducts;
    private List<String[]> listObjectProduct;
    
    
    //Controlador
    
  
    //Procesos
    private Sales salesProcess;
    
}
