package Vista;

import Controlador.usuarioBL;
import Modelo.usuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class frmAdministrarCuenta extends javax.swing.JPanel {
    /* Atributos */
    private  int usuarioSeleccionado;
    usuarioBL usuarioBL;
    javax.swing.JFrame x;
    ArrayList<usuario> usuarios;
    
    public frmAdministrarCuenta(javax.swing.JFrame x) {
        initComponents();
        int usuarioSeleccionado=-1;
        this.x = x;
        usuarioBL = new usuarioBL();
        usuarios=  usuarioBL.obtenerUsuarios();        
        if (usuarios!=null){
            DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
            Object rowData[] = new Object[4];
            for(int i = 0; i < usuarios.size(); i++){
                rowData[0] = usuarios.get(i).getCodigo();
                rowData[1] = usuarios.get(i).getPersona().getNombre()+" "+usuarios.get(i).getPersona().getApellidoPaterno()+" "+usuarios.get(i).getPersona().getApellidoMaterno();
                rowData[2] = usuarios.get(i).getRol();
                rowData[3] = usuarios.get(i).getPersona().getCorreo();
                model.addRow(rowData);
            }

        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        registrarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registrarUsuario.setText("Registrar Usuario");
        registrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarUsuarioActionPerformed(evt);
            }
        });
        panelFondo.add(registrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, -1));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de usuario", "Nombre de empleado", "Rol", "Fecha de creación"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 710, 197));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("ADMINISTRACIÓN DE USUARIOS");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelFondo.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

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

    private void registrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarUsuarioActionPerformed
        // TODO add your handling code here:
        new frmCrearCuenta(x,true,this).setVisible(true);
    }//GEN-LAST:event_registrarUsuarioActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println("Selected");
        System.out.println(this.tblUsuarios.getSelectedRow());
        System.out.println("iD: "+this.usuarios.get(this.tblUsuarios.getSelectedRow()).getId());
        System.out.println("eee");
        this.setUsuarioSeleccionado(this.usuarios.get(this.tblUsuarios.getSelectedRow()).getId());
        new frmCrearCuenta(x,true,this).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton registrarUsuario;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the usuarioSeleccionado
     */
    public int getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(int usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
}
