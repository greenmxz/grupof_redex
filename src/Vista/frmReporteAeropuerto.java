package Vista;

import Controlador.aeropuertoBL;
import Controlador.excelExport;
import Controlador.generalBL;
import Modelo.aeropuerto;
import Modelo.continente;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmReporteAeropuerto extends javax.swing.JPanel {

    /* ATRIBUTOS */
    private ArrayList<aeropuerto> lstAeropuerto = new ArrayList<aeropuerto>();
    private ArrayList<aeropuerto> filter = new ArrayList<aeropuerto>();
    
    
    public frmReporteAeropuerto() {
        initComponents();
//        lstAeropuerto.add(new aeropuertov2("AAA01", "Aeropuerto Internacional Jorge Chávez", 
//                "América", "Lima", "Perú", 750, 720, "Saturado"));
//        lstAeropuerto.add(new aeropuertov2("AAA02", "Aeropuerto Internacional Ministro Pistarini", 
//                "América", "Argentina", "Buenos Aires", 950, 789, "Estable")); 
//        lstAeropuerto.add(new aeropuertov2("AAA03", "Aeropuerto Internacional El Alto", 
//                "América", "Bolivia", "La Paz", 920, 920, "Lleno")); 
        aeropuertoBL paqueteBL = new aeropuertoBL();
        lstAeropuerto = paqueteBL.listaAeropuertos();
        generalBL gen = new generalBL();
        ArrayList<continente> list = gen.obtenerContinentes();
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        for(int i=0; i<list.size();i++){
            listModel.add(i,list.get(i).getNombre());  
        }
        listContinente.setModel(listModel);
        filter = lstAeropuerto;
        lblAyudaMin.setToolTipText("Este valor debe estar entre 600 y 1000.\n No"
                + " puede ser mayor al valor máximo colocado.");
        lblAyudaMax.setToolTipText("Este valor debe estar entre 600 y 1000.\n No"
                + " puede ser menor al valor mínimo colocado.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAirports = new javax.swing.JTable();
        panelFlitrado = new javax.swing.JPanel();
        panelEstado = new javax.swing.JPanel();
        chkEstadoEstable = new javax.swing.JCheckBox();
        chkEstadoSaturado = new javax.swing.JCheckBox();
        chkEstadoLleno = new javax.swing.JCheckBox();
        panelCap = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCapMin = new javax.swing.JTextField();
        txtCapMax = new javax.swing.JTextField();
        lblAyudaMin = new javax.swing.JLabel();
        lblAyudaMax = new javax.swing.JLabel();
        btnLimpiarFlitro = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        panelContinente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listContinente = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        panelFondo.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 450, 240, -1));

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

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 222, 749, 210));

        panelFlitrado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        panelFlitrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estado"));
        panelEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadoEstable.setText("Estable");
        panelEstado.add(chkEstadoEstable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        chkEstadoSaturado.setText("Saturado");
        panelEstado.add(chkEstadoSaturado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        chkEstadoLleno.setText("Lleno");
        panelEstado.add(chkEstadoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        panelFlitrado.add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 100));

        panelCap.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rangos de capacidad máxima"));
        panelCap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Valor mínimo");
        panelCap.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 30, -1, -1));

        jLabel7.setText("Valor máximo");
        panelCap.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, -1, -1));

        txtCapMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMinKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 70, -1));

        txtCapMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMaxKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 70, -1));

        lblAyudaMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/ayuda.png"))); // NOI18N
        panelCap.add(lblAyudaMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 30, -1, -1));

        lblAyudaMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/ayuda.png"))); // NOI18N
        panelCap.add(lblAyudaMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 60, -1, -1));

        panelFlitrado.add(panelCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 210, 100));

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });
        panelFlitrado.add(btnLimpiarFlitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 130, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFlitrado.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 130, -1));

        panelContinente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente"));
        panelContinente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinente.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContinente);

        panelContinente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        panelFlitrado.add(panelContinente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 210, 100));

        panelFondo.add(panelFlitrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 51, 750, 140));

        jLabel1.setText("Resultado de consulta");
        panelFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 202, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("REPORTE DE AEROPUERTOS");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 18, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /* MÉTODOS */
    public void tablaDefault(){
        DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
        int tamanho = modelo.getRowCount();
        for(int i=0; i<tamanho; i++){
            modelo.removeRow(0);
        }
        Object[] obj = new Object[8];
        for(int i = 0; i < lstAeropuerto.size(); i++){
            aeropuerto u = lstAeropuerto.get(i);
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
    
    public boolean filtroEstado(aeropuerto ae){
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
    
    public boolean filtroContinente(aeropuerto ae){
        if(listContinente.getSelectedIndex() > -1){
            if((listContinente.getSelectedIndex() == 0) &&
                    (ae.getContinente().equals("Europa")))
                return false;
            if((listContinente.getSelectedIndex() == 1) &&
                    (ae.getContinente().equals("America del Sur")))
                return false;
        }
        return true;
    }
    
    public boolean filtroCapacidad(aeropuerto ae){
        // Si no se indica alguno, se infiere que no habrá filtro
        if(!(txtCapMax.getText().equals("")) && 
                (ae.getCapMax()> Integer.parseInt(txtCapMax.getText())))
            return false;
        if(!(txtCapMin.getText().equals("")) && 
                (ae.getCapMax() < Integer.parseInt(txtCapMin.getText())))
            return false;
        return true;
    }
    
    
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        excelExport controlerExcel = new excelExport();
        controlerExcel.excelExportAeropuertos(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

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
        filter = new ArrayList<aeropuerto>();
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
                aeropuerto u = filter.get(i);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiarFlitro;
    private javax.swing.JCheckBox chkEstadoEstable;
    private javax.swing.JCheckBox chkEstadoLleno;
    private javax.swing.JCheckBox chkEstadoSaturado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAyudaMax;
    private javax.swing.JLabel lblAyudaMin;
    private javax.swing.JList<String> listContinente;
    private javax.swing.JPanel panelCap;
    private javax.swing.JPanel panelContinente;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFlitrado;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblAirports;
    private javax.swing.JTextField txtCapMax;
    private javax.swing.JTextField txtCapMin;
    // End of variables declaration//GEN-END:variables
}
