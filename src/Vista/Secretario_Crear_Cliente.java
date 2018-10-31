package Vista;

import Controlador.AdministrarClienteBL;
import Controlador.generalBL;
import Modelo.ciudad;
import Modelo.cliente;
import Modelo.continente;
import Modelo.pais;
import Modelo.persona;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Secretario_Crear_Cliente extends javax.swing.JDialog {

    AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    generalBL general = new generalBL();
    javax.swing.JTable tabla;
    
    public Secretario_Crear_Cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Secretario_Crear_Cliente(java.awt.Frame parent, boolean modal, javax.swing.JTable tabla) {
        super(parent, modal);
        initComponents();
        inicializar();
        this.tabla = tabla;
    }
    
    /* Métodos */
    public void inicializar(){
        try{
            cboContinente.removeAllItems();
            cboPais.removeAllItems();
            cboCiudad.removeAllItems();

            //continente
            ArrayList<continente> list_continente = general.obtenerContinentes();
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) cboContinente.getModel();           
            for(int i = 0; i < list_continente.size() ; i++){
                modelo1.addElement(list_continente.get(i).getNombre());
            }
            
            //pais
            ArrayList<pais> list_pais = general.obtenerPaises();
            DefaultComboBoxModel modelo2 = (DefaultComboBoxModel) cboPais.getModel();            
            for(int i = 0; i < list_pais.size() ; i++){
                modelo2.addElement(list_pais.get(i).getNombre());
            }

            //ciudad
            ArrayList<ciudad> list_ciudades = general.obtenerCiudades();
            DefaultComboBoxModel modelo3 = (DefaultComboBoxModel) cboCiudad.getModel();   
            for(int i = 0; i < list_ciudades.size() ; i++){
                modelo3.addElement(list_ciudades.get(i).getNombre());
            }
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
    
    private void actualizar_tabla(cliente cliente){
        try{
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                        Object[] obj = new Object[5];
                        obj[0] = cliente.getCodigo();
                        obj[1] = cliente.getPersona().getNumeroDocumentoIdentidad();
                        obj[2] = cliente.getPersona().getNombre();
                        obj[3] = cliente.getPersona().getApellidoPaterno();
                        obj[4] = cliente.getPersona().getApellidoMaterno();
            modelo.addRow(obj);
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        panelDatosBasicos = new javax.swing.JPanel();
        txtDNI = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label18 = new java.awt.Label();
        txtNombres = new javax.swing.JTextField();
        label19 = new java.awt.Label();
        txtApPat = new javax.swing.JTextField();
        label20 = new java.awt.Label();
        txtApMat = new javax.swing.JTextField();
        label21 = new java.awt.Label();
        txtCorreo = new javax.swing.JTextField();
        label22 = new java.awt.Label();
        txtTelefono = new javax.swing.JTextField();
        panelUbicacion = new javax.swing.JPanel();
        label13 = new java.awt.Label();
        cboContinente = new javax.swing.JComboBox<>();
        cboPais = new javax.swing.JComboBox<>();
        label23 = new java.awt.Label();
        cboCiudad = new javax.swing.JComboBox<>();
        label24 = new java.awt.Label();
        txtDireccion = new javax.swing.JTextField();
        label25 = new java.awt.Label();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDatosBasicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos básicos del cliente"));
        panelDatosBasicos.setPreferredSize(new java.awt.Dimension(348, 3));
        panelDatosBasicos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelDatosBasicos.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 40, 97, -1));

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("DNI:");
        panelDatosBasicos.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 40, -1, -1));

        label18.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label18.setText("Nombre:");
        panelDatosBasicos.add(label18, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 70, -1, -1));
        panelDatosBasicos.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 68, 222, -1));

        label19.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label19.setText("Apellido paterno:");
        panelDatosBasicos.add(label19, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 100, -1, -1));

        txtApPat.setToolTipText("");
        panelDatosBasicos.add(txtApPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 200, -1));

        label20.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label20.setText("Apellido materno :");
        panelDatosBasicos.add(label20, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 130, -1, -1));
        panelDatosBasicos.add(txtApMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 200, -1));

        label21.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label21.setText("Correo :");
        panelDatosBasicos.add(label21, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 160, -1, -1));
        panelDatosBasicos.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 240, -1));

        label22.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label22.setText("Teléfono :");
        panelDatosBasicos.add(label22, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 190, -1, -1));
        panelDatosBasicos.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 170, -1));

        panelFondo.add(panelDatosBasicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 350, 230));

        panelUbicacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación del cliente"));
        panelUbicacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label13.setText("Continente :");
        panelUbicacion.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 42, -1, -1));

        cboContinente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboContinente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboContinenteActionPerformed(evt);
            }
        });
        panelUbicacion.add(cboContinente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 210, -1));

        cboPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelUbicacion.add(cboPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 210, -1));

        label23.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label23.setText("Pais :");
        panelUbicacion.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 72, -1, -1));

        cboCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelUbicacion.add(cboCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 210, -1));

        label24.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label24.setText("Ciudad :");
        panelUbicacion.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 102, -1, -1));
        panelUbicacion.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 210, 60));

        label25.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label25.setText("Dirección :");
        panelUbicacion.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 138, -1, -1));

        panelFondo.add(panelUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 350, 230));

        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelFondo.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 140, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.setToolTipText("");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        panelFondo.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 140, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CREACIÓN DE CLIENTES");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboContinenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboContinenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboContinenteActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

        //dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:

        //registro de cliente
        try{
            String dni = txtDNI.getText();
            String nombre = txtNombres.getText();
            String ap_pat = txtApPat.getText();
            String ap_mat = txtApMat.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            String continente = cboContinente.getSelectedItem().toString();
            String pais = cboPais.getSelectedItem().toString();
            String ciudad = cboCiudad.getSelectedItem().toString();

            String direccion = txtDireccion.getText();

            persona persona = new persona();

            persona.setNumeroDocumentoIdentidad(Integer.parseInt(dni));
            persona.setNombre(nombre);
            persona.setApellidoPaterno(ap_pat);
            persona.setApellidoMaterno(ap_mat);
            persona.setCorreo(correo);
            persona.setTelefono(telefono);
            persona.setCiudad(ciudad);
            persona.setPais(pais);
            persona.setContinente(continente);
            persona.setDireccion(direccion);

            cliente cliente = new cliente();

            cliente.setCantidad_pedidos(0);
            cliente.setCodigo(dni);
            cliente.setPersona(persona);
            cliente.setCodigo(dni);

            if (!dni.equals("") && !nombre.equals("") && !ap_pat.equals("") && !ap_mat.equals("") && !correo.equals("") && !telefono.equals("")) {
                switch (controlador_cliente.registrarCliente(cliente)) {
                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "El cliente ha sido correctamente registrado",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                        if (tabla != null) {
                            actualizar_tabla(cliente);
                        }
                        //this.dispose();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null,
                                "No se pudo registrar usuario",
                                "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null,
                                "Otro cliente con el mismo documento de identidad ya ha sido registrado",
                                "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se pudo registrar usuario, llene todos los campos.",
                        "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(Secretario_Crear_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Secretario_Crear_Cliente dialog = new Secretario_Crear_Cliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cboCiudad;
    private javax.swing.JComboBox<String> cboContinente;
    private javax.swing.JComboBox<String> cboPais;
    private javax.swing.JLabel jLabel2;
    private java.awt.Label label13;
    private java.awt.Label label18;
    private java.awt.Label label19;
    private java.awt.Label label2;
    private java.awt.Label label20;
    private java.awt.Label label21;
    private java.awt.Label label22;
    private java.awt.Label label23;
    private java.awt.Label label24;
    private java.awt.Label label25;
    private javax.swing.JPanel panelDatosBasicos;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelUbicacion;
    private javax.swing.JTextField txtApMat;
    private javax.swing.JTextField txtApPat;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
