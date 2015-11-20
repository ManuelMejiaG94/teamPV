
package BDPuntoVentaManuel.Views.Supplier;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Suppliers_Default extends javax.swing.JPanel {

    /**
     * Creates new form Suppliers_Default
     */
    public Suppliers_Default() {
        initComponents();
        this.Windows_Start();
    }
    private void Windows_Start()
    {
        this.Data_Default();
    }
    
    private void Data_Default() {
        Suppliers_Start.SuppliersProcess=new Suppliers();
        this.cmbCategoria.setModel(Suppliers_Start.SuppliersProcess.getModelCategorias());
        this.lbErrorMEssage.setVisible(false);
        
        this.ResetDataTable();
    }
    
    private void SetModelTable() {
        modelTab=new DefaultTableModel();
        modelTab.addColumn("Codigo");
        modelTab.addColumn("Nombre");
        modelTab.addColumn("Numero");
        modelTab.addColumn("Direccion");
        modelTab.addColumn("Contacto");
        modelTab.addColumn("Descripcion");
        modelTab.addColumn("Estatus");
    }
    
    public void ResetDataTable()
    {
        if(categoria !=null && categoria.getId()>0)
        {
            this.SetModelTable();
            Suppliers_Start.SuppliersProcess.ChargeDataByCategoriaId(modelTab, categoria);
            this.tbData.setModel(modelTab);
        }
        if(categoria==null || categoria.getId()<=0)
        {
            this.SetModelTable();
            Suppliers_Start.SuppliersProcess.ChargeDataDefault(modelTab);
            this.tbData.setModel(modelTab);
        }
    }
    
    private void ResetDataTableBySearch()
    {
            this.SetModelTable();
            Suppliers_Start.SuppliersProcess.SearchSupplierByLette(this.txtNombre.getText().trim(),
                    modelTab);
            this.tbData.setModel(modelTab);
    }
    private void PainErrorMessage()
    {
        this.lbErrorMEssage.setText("Es necesario llenar el campo nombre");
        this.lbErrorMEssage.setVisible(true);
    }
    private void CleanErrorMessage()
    {
        this.lbErrorMEssage.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbData = new javax.swing.JTable();
        lbErrorMEssage = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        tbData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbData);

        lbErrorMEssage.setText("ErrorMessage");

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre");

        cmbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriaItemStateChanged(evt);
            }
        });
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoria del producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(41, 41, 41)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 86, Short.MAX_VALUE))
                            .addComponent(lbErrorMEssage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorMEssage)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void cmbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriaItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            categoria =(Catcategoria) this.cmbCategoria.getSelectedItem();
            this.ResetDataTable();
        }
    }//GEN-LAST:event_cmbCategoriaItemStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(!StringUtils.isNullOrEmpty(this.txtNombre.getText()))
        {
            CleanErrorMessage();
            ResetDataTableBySearch();
        }else
        {
            PainErrorMessage();
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbErrorMEssage;
    private javax.swing.JTable tbData;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel modelTab;
    private Catcategoria categoria=null;

    
}
