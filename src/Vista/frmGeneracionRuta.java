package Vista;

import Algoritmo.TabuSearch;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

public class frmGeneracionRuta extends javax.swing.JPanel {

    public frmGeneracionRuta() {
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        listDetalle = new javax.swing.JList<>();

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
        panelConfig.add(txtDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 180, -1));
        panelConfig.add(txtOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 180, -1));

        btnBuscarDestino.setText("Buscar");
        panelConfig.add(btnBuscarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        btnBuscarOrigen.setText("Buscar");
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

        listDetalle.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "-Configure la simulación unitaria y dé cilc en \"Generar\" para iniciar el proceso-" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listDetalle);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 730, 150));

        add(panelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        try{
            TabuSearch ts = new TabuSearch();
            ts.inputData("G:\\PUCP\\9no Ciclo\\DP1\\Sistema\\algoritmo\\tabuProvisional\\aeropuertos.txt",
                "G:\\PUCP\\9no Ciclo\\DP1\\Sistema\\algoritmo\\tabuProvisional\\planes_vuelo.txt",
                "G:\\PUCP\\9no Ciclo\\DP1\\Sistema\\algoritmo\\tabuProvisional\\pack_enviado_SKBO.txt");
            ts.printPackList();
            long start = System.currentTimeMillis();
            ts.executeVCRPTabu();
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.println("Tiempo: " + String.valueOf(elapsedTime) + " mseg");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the testing data reading process! Check it!");
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listDetalle;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
