package Vista;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class frmMenuSecretary extends javax.swing.JFrame {
    public frmMenuSecretary() {
        initComponents();
        this.setTitle("Sistema de distribución de paquetes para RedEx");
        setLocationRelativeTo(null);
        cerrar();
        Dimension size = panelMenu.getPreferredSize();
        panelMenu.setBounds(-150, 50, size.width, size.height);
        new CambiarPanel(panelPrincipal, new Secretario_Administrar_Pedido(this));
    }

    public void cerrar(){
        try{
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    confirmarSalida();
                }
            });
            this.setVisible(true);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void confirmarSalida(){
        int valor = JOptionPane.showConfirmDialog(this,"No ha cerrado sesión"+
                ", ¿estás seguro de cerrar?", "Advertencia", 
                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            String hora = "";
            if(LocalTime.now().getHour() <= 19 || LocalTime.now().getHour() > 4)
                hora = "Buenas noches.";
            else if(LocalTime.now().getHour() <= 4 && LocalTime.now().getHour() > 12)
                hora = "Buenos días.";
            else
                hora = "Buenas tardes.";
            JOptionPane.showMessageDialog(null,"Gracias por su visita.\n"+
                    hora,"Gracias",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        panelInfoUsuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrarSesion = new Especial.RSButtonMetro();
        btnMenu = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        btnPedidos = new Especial.RSButtonMetro();
        btnClientes = new Especial.RSButtonMetro();
        panelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInfoUsuario.setBackground(new java.awt.Color(1, 58, 223));
        panelInfoUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("SECRETARIO");
        panelInfoUsuario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 28, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Sistema de Distribución de Paquetes - RedEx");
        panelInfoUsuario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        btnCerrarSesion.setBackground(new java.awt.Color(1, 58, 223));
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        panelInfoUsuario.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 90, 15));

        btnMenu.setBorder(null);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        panelInfoUsuario.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 25, 25, 20));

        panelFondo.add(panelInfoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPedidos.setText("Pedidos");
        btnPedidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });
        panelMenu.add(btnPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 140, 30));

        btnClientes.setText("Clientes");
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        panelMenu.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 35, 140, 30));

        panelFondo.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 150, 500));

        panelPrincipal.setLayout(new javax.swing.BoxLayout(panelPrincipal, javax.swing.BoxLayout.LINE_AXIS));
        panelFondo.add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 800, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed
        new CambiarPanel(panelPrincipal, new Secretario_Administrar_Pedido(this));
        if(this.panelMenu.getX()>-1)
            Animacion.Animacion.mover_izquierda(0, -150, 2, 2, panelMenu);   
    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new CambiarPanel(panelPrincipal, new Secretario_Administrar_Cliente(this));
        if(this.panelMenu.getX()>-1)
            Animacion.Animacion.mover_izquierda(0, -150, 2, 2, panelMenu);   
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        String hora = "";
        if(LocalTime.now().getHour() <= 19 || LocalTime.now().getHour() > 4)
            hora = "Buenas noches.";
        else if(LocalTime.now().getHour() <= 4 && LocalTime.now().getHour() > 12)
            hora = "Buenos días.";
        else
            hora = "Buenas tardes.";
        JOptionPane.showMessageDialog(null,"Gracias por su visita.\n"+
                hora,"Gracias",
                JOptionPane.INFORMATION_MESSAGE);
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        int posicion = this.panelMenu.getX();
        System.out.println(this.panelMenu.getX());
        if(posicion>-1)
            Animacion.Animacion.mover_izquierda(0, -150, 2, 2, panelMenu);            
        else
            Animacion.Animacion.mover_derecha(-150, 0, 2, 2, panelMenu);
    }//GEN-LAST:event_btnMenuActionPerformed

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
            java.util.logging.Logger.getLogger(frmMenuSecretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenuSecretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenuSecretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenuSecretary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenuSecretary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Especial.RSButtonMetro btnCerrarSesion;
    private Especial.RSButtonMetro btnClientes;
    private javax.swing.JButton btnMenu;
    private Especial.RSButtonMetro btnPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelInfoUsuario;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
