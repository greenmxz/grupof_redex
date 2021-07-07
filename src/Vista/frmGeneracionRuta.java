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

public class frmGeneracionRuta extends javax.swing.JPanel {

    private javax.swing.JFrame x;
    
    public frmGeneracionRuta(javax.swing.JFrame x) {
        initComponents();
        this.x = x;
        dtpLlegada.setDateTimePermissive(LocalDateTime.of(2018, 4, 01, 0, 0));
    }
    
    public void setOrigen(String codigo){
        txtOrigen.setText(codigo);
    }
    
    public void setDestino(String codigo){
        txtDestino.setText(codigo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dtpLlegada = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        btnBuscarDestino = new javax.swing.JButton();
        btnBuscarOrigen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInforme = new javax.swing.JTextArea();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("DETERMINACIÓN UNITARIA DE RUTA");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        panelConfig.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de prueba individual"));
        panelConfig.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Aeropuerto de destino");
        panelConfig.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        panelConfig.add(dtpLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel3.setText("Fecha de llegada de paquete");
        panelConfig.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel4.setText("Aeropuerto de origen");
        panelConfig.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtDestino.setEnabled(false);
        panelConfig.add(txtDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 180, -1));

        txtOrigen.setEnabled(false);
        panelConfig.add(txtOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 180, -1));

        btnBuscarDestino.setText("Buscar");
        btnBuscarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDestinoActionPerformed(evt);
            }
        });
        panelConfig.add(btnBuscarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        btnBuscarOrigen.setText("Buscar");
        btnBuscarOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrigenActionPerformed(evt);
            }
        });
        panelConfig.add(btnBuscarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        panelFondo.add(panelConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 450, 130));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("PRÓXIMAMENTE");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 48, -1, -1));

        panelFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 240, 120));

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        panelFondo.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 100, -1));

        btnLimpiar.setText("Limpiar");
        panelFondo.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 100, -1));

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
            TabuSearch ts = new TabuSearch();
            String origen = txtOrigen.getText();
            ts.inputData("resources\\aeropuertos.txt",
                "resources\\planes_vuelo.txt",
                "resources\\pack_enviados\\pack_enviado_" + origen + ".txt");
            String destino = txtDestino.getText();
            LocalDate dia = dtpLlegada.getDatePicker().getDate();
            LocalTime hora = dtpLlegada.getTimePicker().getTime();
            String fecha = new SimpleDateFormat("HH:mm").format(
                    new Date(dia.getYear()-1900, dia.getMonthValue()-1, dia.getDayOfMonth(),
                        hora.getHour(), hora.getMinute()));
            long start = System.currentTimeMillis();
            ts.tabuAlgorithm(origen, destino, fecha);
            ArrayList<Integer> solution = ts.getRouteOptimal();
            
            
            for(int i : solution){
                if(solution.get(0) != i)
                    System.out.print(" to ");
                System.out.print(i);
            }
            System.out.print(" (longitud: " + String.valueOf(ts.getRouteLenght(solution)) +
                    ") ");
            ArrayList<String> stringSol = ts.getAirportsRouteICAO();
            for(String i : stringSol){
                if(stringSol.get(0) != i)
                    System.out.print(" ->");
                System.out.print(i);
            }
            System.out.println(" ");
            
            
            System.out.println("\\");
            int lenghtOptimal = ts.getLenghtOptimal();
            System.out.println("Optimal route's lenght is: " + String.valueOf(lenghtOptimal));
            long elapsedTime = System.currentTimeMillis() - start;
            
            texto += "\nTiempo empleado: " + String.valueOf(elapsedTime) + " mseg";
            texto += "\n\nSIMULACIÓN UNITARIA FINALIZADA\n" + 
                    "**************************************\n";
            txtInforme.setText(texto);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the testing data reading process! Check it!");
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnBuscarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOrigenActionPerformed
        new Vista.frmBuscarAeropuerto(x,true,this,0).setVisible(true);
    }//GEN-LAST:event_btnBuscarOrigenActionPerformed

    private void btnBuscarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDestinoActionPerformed
        new Vista.frmBuscarAeropuerto(x,true,this,1).setVisible(true);
    }//GEN-LAST:event_btnBuscarDestinoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDestino;
    private javax.swing.JButton btnBuscarOrigen;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnLimpiar;
    private com.github.lgooddatepicker.components.DateTimePicker dtpLlegada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextArea txtInforme;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
