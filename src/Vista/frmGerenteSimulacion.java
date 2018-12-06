package Vista;

import Algoritmo.TabuSearch;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class frmGerenteSimulacion extends javax.swing.JPanel {
    
    TabuSearch ts;
    
    public frmGerenteSimulacion() {
        initComponents();
        dtpLlegada.setDateTimePermissive(LocalDateTime.of(2018, 4, 17, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelConfig = new javax.swing.JPanel();
        dtpLlegada = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInforme = new javax.swing.JTextArea();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("SIMULACIÓN DE NECESIDADES DE AMPLIACIÓN DE ALMACENES");
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        panelConfig.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de prueba individual"));
        panelConfig.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelConfig.add(dtpLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel3.setText("Fecha de llegada de paquete");
        panelConfig.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        panelFondo.add(panelConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 450, 80));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("PRÓXIMAMENTE");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        panelFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 240, 70));

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        panelFondo.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 100, -1));

        btnLimpiar.setText("Limpiar");
        panelFondo.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 100, -1));

        jLabel6.setText("Detalle");
        panelFondo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtInforme.setEditable(false);
        txtInforme.setColumns(20);
        txtInforme.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtInforme.setRows(5);
        txtInforme.setText("-Configure la simulación unitaria y dé cilc en \"Generar\" para iniciar el proceso-");
        jScrollPane2.setViewportView(txtInforme);

        panelFondo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 730, 170));

        add(panelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        try{
            String texto = "**************************************\nSIMULACIÓN UNITARIA INICIADA\n" + 
                    "**************************************\n\n";
            txtInforme.setText(texto);
            TabuSearch ts;
            ts = new TabuSearch();
            /*
                        ts.inputData("resources\\aeropuertos.txt",
                "resources\\planes_vuelo.txt",
                "resources\\pack_enviados");
            */
            ts.inputData("resources/aeropuertos.txt",
                "resources/planes_vuelo.txt",
                "resources/pack_enviados");
            ts.generateFlightMatrix();
            long start = System.currentTimeMillis();
            ArrayList<String> solution = ts.executeVCRPTabu(ts.getListPack());
            long elapsedTime = System.currentTimeMillis() - start;
            texto += "\nTiempo empleado: " + String.valueOf(elapsedTime) + " mseg";
            texto += "\n\nSIMULACIÓN UNITARIA FINALIZADA\n" + 
                    "**************************************\n";
            txtInforme.setText(texto);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error! " + e.getMessage());
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnLimpiar;
    private com.github.lgooddatepicker.components.DateTimePicker dtpLlegada;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTextArea txtInforme;
    // End of variables declaration//GEN-END:variables
}
