package Vista;

import Controlador.PaqueteBL;
import Modelo.paquete;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmBuscarPaquete extends javax.swing.JDialog {

    private ArrayList<paquete> lstAerop = new ArrayList<paquete>();
    private ArrayList<paquete> filter = new ArrayList<paquete>();
    private PaqueteBL controlador = new PaqueteBL();
    private frmReportePaquete panelPaq = null;
    
    public frmBuscarPaquete(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
    }
    
    public frmBuscarPaquete(java.awt.Frame parent, boolean modal, frmReportePaquete panel) {
        super(parent, modal);
        initComponents();
        this.panelPaq = panel;
        lstAerop = controlador.obtenerPaquetes();
        DefaultTableModel model = (DefaultTableModel)tblAerop.getModel();
        for(int i=0; i<lstAerop.size(); i++){
            Object[] obj = new Object[3];
            obj[0] = lstAerop.get(i).getCodigo();
            obj[1] = lstAerop.get(i).getAeropuertoDestino() + " -> " + lstAerop.get(i).getAeropuertoOrigen();
            obj[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lstAerop.get(i).getFechaSalida());
            model.addRow(obj);
        }
        filter = lstAerop;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnSelec = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAerop = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre:");
        panelFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("BUSCAR PAQUETE");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        panelFondo.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 310, -1));

        btnSelec.setText("Seleccionar");
        btnSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecActionPerformed(evt);
            }
        });
        panelFondo.add(btnSelec, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 100, -1));

        tblAerop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Origen -> Destino", "Fecha de entrada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAerop);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 480, 230));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecActionPerformed
        if(tblAerop.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null,
                "Debe seleccionar un aeropuerto de la tabla primero",
                "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
        else{
            panelPaq.setCodigo(filter.get(tblAerop.getSelectedRow()).getCodigo());
            this.dispose();
        }
    }//GEN-LAST:event_btnSelecActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        String parametro = txtNombre.getText() + String.valueOf(evt.getKeyChar());
        DefaultTableModel model = (DefaultTableModel)tblAerop.getModel();
        while(tblAerop.getRowCount() > 0)
            model.removeRow(0);
        filter = new ArrayList<paquete>();
        if(!(((txtNombre.getText().length() == 1) || (txtNombre.getText().length() == 0)) &&
                ((evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) || (evt.getKeyChar() == KeyEvent.VK_DELETE)))){
            for(int i=0; i<lstAerop.size(); i++){
                if(lstAerop.get(i).getCodigo().toLowerCase().contains(parametro))
                    filter.add(lstAerop.get(i));
            }
        }
        else filter = lstAerop;
        for(int i=0; i<filter.size(); i++){
            Object[] obj = new Object[3];
            obj[0] = filter.get(i).getCodigo();
            obj[1] = filter.get(i).getAeropuertoDestino() + " -> " + filter.get(i).getAeropuertoOrigen();
            obj[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(filter.get(i).getFechaSalida());
            model.addRow(obj);
        } 
    }//GEN-LAST:event_txtNombreKeyTyped

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
            java.util.logging.Logger.getLogger(frmBuscarPaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPaquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmBuscarPaquete dialog = new frmBuscarPaquete(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblAerop;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
