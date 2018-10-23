package Vista;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import Modelo.aeropuerto;
import Modelo.aeropuertov2;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Controlador.excelExport;
import javax.swing.DefaultListModel;

public class frmReporteAeropuerto extends javax.swing.JFrame {
    private ArrayList<aeropuertov2> lstAeropuerto = new ArrayList<aeropuertov2>();
    private ArrayList<aeropuertov2> filter = new ArrayList<aeropuertov2>();
    
    public frmReporteAeropuerto() {
        initComponents();
        panelEstado.setBorder(new TitledBorder("Estado"));
        panelCap.setBorder(new TitledBorder("Rango de capacidades"));
        panelContinente.setBorder(new TitledBorder("Continente"));
        lstAeropuerto.add(new aeropuertov2("AAA01", "Aeropuerto Internacional Jorge Chávez", 
                "América", "Lima", "Perú", 750, 720, "Saturado"));
        lstAeropuerto.add(new aeropuertov2("AAA02", "Aeropuerto Internacional Ministro Pistarini", 
                "América", "Argentina", "Buenos Aires", 950, 789, "Estable")); 
        lstAeropuerto.add(new aeropuertov2("AAA03", "Aeropuerto Internacional El Alto", 
                "América", "Bolivia", "La Paz", 920, 920, "Lleno")); 
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        listModel.add(0,"América");
        listModel.add(1,"Europa");
        listContinente.setModel(listModel);
        filter = lstAeropuerto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAirports = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelEstado = new javax.swing.JPanel();
        chkEstadoEstable = new javax.swing.JCheckBox();
        chkEstadoSaturado = new javax.swing.JCheckBox();
        chkEstadoLleno = new javax.swing.JCheckBox();
        panelCap = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCapMin = new javax.swing.JTextField();
        txtCapMax = new javax.swing.JTextField();
        btnLimpiarFlitro = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        panelContinente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listContinente = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        tblAirports.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Aeropuerto", "Continente", "País", "Ciudad", "Cap. máxima", "Cap. ocupada", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAirports);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Resultado");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Filtrado");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chkEstadoEstable.setText("Estable");

        chkEstadoSaturado.setText("Saturado");

        chkEstadoLleno.setText("Lleno");

        javax.swing.GroupLayout panelEstadoLayout = new javax.swing.GroupLayout(panelEstado);
        panelEstado.setLayout(panelEstadoLayout);
        panelEstadoLayout.setHorizontalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEstadoEstable)
                    .addComponent(chkEstadoSaturado)
                    .addComponent(chkEstadoLleno))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelEstadoLayout.setVerticalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkEstadoEstable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkEstadoSaturado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkEstadoLleno)
                .addContainerGap())
        );

        panelCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Capacidad mínima");

        jLabel7.setText("Capacidad máxima");

        txtCapMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMinKeyTyped(evt);
            }
        });

        txtCapMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMaxKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelCapLayout = new javax.swing.GroupLayout(panelCap);
        panelCap.setLayout(panelCapLayout);
        panelCapLayout.setHorizontalGroup(
            panelCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelCapLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCapLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCapMin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelCapLayout.setVerticalGroup(
            panelCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCapLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCapMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        panelContinente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listContinente.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContinente);

        javax.swing.GroupLayout panelContinenteLayout = new javax.swing.GroupLayout(panelContinente);
        panelContinente.setLayout(panelContinenteLayout);
        panelContinenteLayout.setHorizontalGroup(
            panelContinenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
        );
        panelContinenteLayout.setVerticalGroup(
            panelContinenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelContinente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiarFlitro, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnLimpiarFlitro)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelContinente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelEstado.getAccessibleContext().setAccessibleName("Estado");
        panelCap.getAccessibleContext().setAccessibleName("Rango de capacidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Métodos
    public void tablaDefault(){
        DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
        int tamanho = modelo.getRowCount();
        for(int i=0; i<tamanho; i++){
            modelo.removeRow(0);
        }
        Object[] obj = new Object[8];
        for(int i = 0; i < lstAeropuerto.size(); i++){
            aeropuertov2 u = lstAeropuerto.get(i);
            obj[0] = u.getCodigo();
            obj[1] = u.getNombre();
            obj[2] = u.getContinente();
            obj[3] = u.getPais();
            obj[4] = u.getCiudad();
            obj[5] = u.getCapMax();
            obj[6] = u.getCapActual();
            obj[7] = u.getEstado();
            modelo.addRow(obj);
        }
    }
    
    public int fitroValido(){
        int capMin = 600;
        if(!txtCapMin.getText().equals("")){
            capMin = Integer.parseInt(txtCapMin.getText());
            if((capMin > 1000) || (capMin < 600))
                return 10;
        }
        int capMax = 1000;
        if(!txtCapMax.getText().equals("")){
            capMax = Integer.parseInt(txtCapMax.getText());
            if((capMax > 1000) || (capMax < 600))
                return 11;
        }
        if(capMax < capMin)
            return 12;
        return 0;
    }
    
    public boolean filtroEstado(aeropuertov2 ae){
        boolean chk1 = chkEstadoEstable.isSelected();
        boolean chk2 = chkEstadoSaturado.isSelected();
        boolean chk3 = chkEstadoLleno.isSelected();
        if(!chk1 && !chk2 && !chk3)
            return true;
        if(!chk1)
            if(ae.getEstado().equals("Estable"))
                return false;
        if(!chk2)
            if(ae.getEstado().equals("Saturado"))
                return false;
        if(!chk3)
            if(ae.getEstado().equals("Lleno"))
                return false;
        return true;
    }
    
    public boolean filtroContinente(aeropuertov2 ae){
        if(listContinente.getSelectedIndex() > -1){
            if((listContinente.getSelectedIndex() == 0) &&
                    (ae.getContinente().equals("Europa")))
                return false;
            if((listContinente.getSelectedIndex() == 1) &&
                    (ae.getContinente().equals("América")))
                return false;
        }
        return true;
    }
    
    public boolean filtroCapacidad(aeropuertov2 ae){
        // Si no se indica alguno, se infiere que no habrá filtro
        if(!(txtCapMax.getText().equals("")) && 
                (ae.getCapActual() > Integer.parseInt(txtCapMax.getText())))
            return false;
        if(!(txtCapMin.getText().equals("")) && 
                (ae.getCapActual() < Integer.parseInt(txtCapMin.getText())))
            return false;
        return true;
    }
    
    private void btnLimpiarFlitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFlitroActionPerformed
        chkEstadoEstable.setSelected(false);
        chkEstadoSaturado.setSelected(false);
        chkEstadoLleno.setSelected(false);
        txtCapMin.setText("");
        txtCapMax.setText("");
        listContinente.clearSelection();
        tablaDefault();
    }//GEN-LAST:event_btnLimpiarFlitroActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // Primero se tendrá que validar el filtro
        filter = new ArrayList<aeropuertov2>();
        if(fitroValido() == 0){
            for(int i=0; i<lstAeropuerto.size(); i++){
                if(filtroEstado(lstAeropuerto.get(i)) && 
                        filtroContinente(lstAeropuerto.get(i)) &&
                        filtroCapacidad(lstAeropuerto.get(i))){
                    filter.add(lstAeropuerto.get(i));
                }
            }
            DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
            int tamanho = modelo.getRowCount();
            for(int i=0; i<tamanho; i++){
                modelo.removeRow(0);
            }
            Object[] obj = new Object[8];
            for(int i = 0; i < filter.size(); i++){
                aeropuertov2 u = filter.get(i);
                obj[0] = u.getCodigo();
                obj[1] = u.getNombre();
                obj[2] = u.getContinente();
                obj[3] = u.getPais();
                obj[4] = u.getCiudad();
                obj[5] = u.getCapMax();
                obj[6] = u.getCapActual();
                obj[7] = u.getEstado();
                modelo.addRow(obj);
            }
        }else{
            switch(fitroValido()){
                case 10:
                    JOptionPane.showMessageDialog(null, 
                        "La capacidad mínima debe estar en el rango indicado", 
                        "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, 
                        "La capacidad máxima debe estar en el rango indicado", 
                        "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, 
                        "La capacidad mínima no puede ser mayor que la capacidad"
                                + " máxima", "Mensaje de error", 
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtCapMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapMinKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
                || (vchar == KeyEvent.VK_BACK_SPACE)
                || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtCapMinKeyTyped

    private void txtCapMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapMaxKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
                || (vchar == KeyEvent.VK_BACK_SPACE)
                || (vchar == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtCapMaxKeyTyped

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        excelExport controlerExcel = new excelExport();
        controlerExcel.excelExportAeropuertos(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

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
            java.util.logging.Logger.getLogger(frmReporteAeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReporteAeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReporteAeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReporteAeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteAeropuerto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiarFlitro;
    private javax.swing.JCheckBox chkEstadoEstable;
    private javax.swing.JCheckBox chkEstadoLleno;
    private javax.swing.JCheckBox chkEstadoSaturado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listContinente;
    private javax.swing.JPanel panelCap;
    private javax.swing.JPanel panelContinente;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JTable tblAirports;
    private javax.swing.JTextField txtCapMax;
    private javax.swing.JTextField txtCapMin;
    // End of variables declaration//GEN-END:variables
}
