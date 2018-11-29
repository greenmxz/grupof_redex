package Vista;

import AccesoDatos.QueryGenerator;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class frmReporteFrecuencias extends javax.swing.JPanel {

    private QueryGenerator consulta = new QueryGenerator();
    
    public frmReporteFrecuencias() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsulta = new javax.swing.JTable();
        panelConf = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboDiv = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboFrec = new javax.swing.JComboBox<>();
        btnQuery = new javax.swing.JButton();
        cboOrden = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("REPORTE DE FRECUENCIAS");
        panelFondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jLabel2.setText("Resultado de consulta");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        panelFondo.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 172, -1));

        tblConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Frecuencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblConsulta);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 740, 170));

        panelConf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de configuración"));
        panelConf.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tipo de reporte:");
        panelConf.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, -1, -1));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Frecuencia de movimientos de paquetes en zonas geográficas", "Frecuencia de vuelos usados en planes de vuelo", " " }));
        panelConf.add(cboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 580, -1));

        jLabel4.setText("División geográfica:");
        panelConf.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, -1, -1));

        cboDiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Continente", "País", "Ciudad", "Aeropuerto" }));
        panelConf.add(cboDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 130, -1));

        jLabel5.setText("Mostrar:");
        panelConf.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 82, -1, -1));

        cboFrec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "5", "10", "20" }));
        panelConf.add(cboFrec, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 50, -1));

        btnQuery.setText("Realizar consulta");
        btnQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });
        panelConf.add(btnQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 180, -1));

        cboOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mayor valor", "Menor valor" }));
        panelConf.add(cboOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 80, 100, -1));

        jLabel6.setText("Orden:");
        panelConf.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 82, -1, -1));

        panelFondo.add(panelConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 740, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
//        excelExport controlerExcel = new excelExport();
//        controlerExcel.excelExportPaquetes(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryActionPerformed
        String query = "";
        /* Identificar el tipo */
        switch(cboTipo.getSelectedIndex()*10+cboDiv.getSelectedIndex()){
            case 0:
                query += "SELECT n.codigo AS Codigo, n.nombre AS Nombre, count(d.id_aeropuerto_emisor) AS Frecuencia "+
                        "FROM redexdb.pais as p, redexdb.pedido as d, redexdb.aeropuerto as a, redexdb.ciudad as c, redexdb.continente as n "+
                        "WHERE a.id = d.id_aeropuerto_emisor AND a.id_ciudad = c.id AND p.id = c.id_pais AND n.id = p.id_continente "+
                        "GROUP BY d.id_aeropuerto_emisor ORDER BY count(1) ";
                break;
            case 1:
                query += "SELECT p.codigo AS Codigo, p.nombre AS Nombre, count(d.id_aeropuerto_emisor) AS Frecuencia "+
                        "FROM redexdb.pais as p, redexdb.pedido as d, redexdb.aeropuerto as a, redexdb.ciudad as c "+
                        "WHERE a.id = d.id_aeropuerto_emisor AND a.id_ciudad = c.id AND p.id = c.id_pais "+
                        "GROUP BY d.id_aeropuerto_emisor ORDER BY count(1) ";
                break;
            case 2:
                query += "SELECT c.codigo AS Codigo, c.nombre AS Nombre, count(d.id_aeropuerto_emisor) AS Frecuencia "+
                        "FROM redexdb.pedido as d, redexdb.aeropuerto as a, redexdb.ciudad as c "+
                        "WHERE a.id = d.id_aeropuerto_emisor AND a.id_ciudad = c.id "+
                        "GROUP BY d.id_aeropuerto_emisor ORDER BY count(1) ";
                break;
            case 3:
                query += "SELECT a.codigo AS Codigo, a.nombre AS Nombre, count(d.id_aeropuerto_emisor) AS Frecuencia "+
                        "FROM redexdb.pedido as d, redexdb.aeropuerto as a "+
                        "WHERE a.id = d.id_aeropuerto_emisor "+
                        "GROUP BY d.id_aeropuerto_emisor ORDER BY count(1) ";
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
        }
        switch(cboOrden.getSelectedIndex()){
            case 0:
                query += "desc";
                break;
            case 1:
                query += "asc";
                break;
        }
        switch(cboFrec.getSelectedIndex()){
            case 0:
                query += " LIMIT 1;";
                break;
            case 1:
                query += " LIMIT 2;";
                break;
            case 2:
                query += " LIMIT 5;";
                break;
            case 3:
                query += " LIMIT 10;";
                break;
            default:
                query += " LIMIT 20;";
                break; 
        }
        System.out.println(query);
        ArrayList<ArrayList<String>> lista = consulta.hacerConsulta(query);
        // Colocando en la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblConsulta.getModel();
        int tamanho = modelo.getRowCount();
        for(int i=0; i<tamanho; i++){
            modelo.removeRow(0);
        }
        Object[] obj = new Object[3];
        System.out.println(lista.size());
        for(int i = 0; i < lista.size(); i++){
            obj[0] = lista.get(i).get(0);
            obj[1] = lista.get(i).get(1);
            obj[2] = lista.get(i).get(2);
            modelo.addRow(obj);
        }
    }//GEN-LAST:event_btnQueryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnQuery;
    private javax.swing.JComboBox<String> cboDiv;
    private javax.swing.JComboBox<String> cboFrec;
    private javax.swing.JComboBox<String> cboOrden;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelConf;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblConsulta;
    // End of variables declaration//GEN-END:variables
}
