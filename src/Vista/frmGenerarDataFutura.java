package Vista;

import Modelo.proyeccionData;

public class frmGenerarDataFutura extends javax.swing.JPanel {

    proyeccionData proyeccionData = new proyeccionData();
    
    public frmGenerarDataFutura() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelGen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        frmGen = new javax.swing.JButton();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("GENERACIÓN DE INFORMACIÓN FUTURA");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelFondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        panelGen.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de generación"));
        panelGen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Número de días proyectados:");
        panelGen.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        panelGen.add(txtDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 120, -1));

        jLabel2.setText("días");
        panelGen.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        panelFondo.add(panelGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 340, 60));

        frmGen.setText("Iniciar generación");
        frmGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frmGenActionPerformed(evt);
            }
        });
        panelFondo.add(frmGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void frmGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frmGenActionPerformed
        //ENGM000000001-20180417-02:58-LZIB
        //[CodigoAeropuertoEmisor][CodigoEnvio]-[AAAAMMDD]-[HH:MM]-[CodigoAeropuertoReceptor]
        proyeccionData.proyectar(Integer.parseInt(txtDias.getText()));
    }//GEN-LAST:event_frmGenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton frmGen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelGen;
    private javax.swing.JTextField txtDias;
    // End of variables declaration//GEN-END:variables
}
