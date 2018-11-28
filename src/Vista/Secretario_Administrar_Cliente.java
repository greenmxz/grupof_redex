package Vista;

import Controlador.AdministrarClienteBL;
import Controlador.personaBL;
import Modelo.cliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Secretario_Administrar_Cliente extends javax.swing.JPanel {

    private AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    private personaBL controlador_persona = new personaBL();
    private javax.swing.JFrame x;
    
    public Secretario_Administrar_Cliente(javax.swing.JFrame x) {
        initComponents();
        this.x = x;
        inicializar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelFiltrado = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        txtDNI = new javax.swing.JTextField();
        label5 = new java.awt.Label();
        txtNombre = new javax.swing.JTextField();
        label10 = new java.awt.Label();
        txtApPat = new javax.swing.JTextField();
        label11 = new java.awt.Label();
        txtApMat = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("ADMINISTRACIÓN DE CLIENTES");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 22, -1, -1));

        jLabel1.setText("Resultado de consulta");
        panelFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 258, -1, -1));

        panelFiltrado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        panelFiltrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("DNI :");
        panelFiltrado.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 20));
        panelFiltrado.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 130, 20));

        label5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label5.setText("Nombre :");
        panelFiltrado.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, 20));
        panelFiltrado.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 210, 20));

        label10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label10.setText("Apellido paterno :");
        panelFiltrado.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));
        panelFiltrado.add(txtApPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 210, 20));

        label11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label11.setText("Apellido materno :");
        panelFiltrado.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, 20));
        panelFiltrado.add(txtApMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 210, 20));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 160, -1));

        btnLimpiar.setText("Limpiar filtro");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 160, -1));

        panelFondo.add(panelFiltrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 68, 725, 156));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panelFondo.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 465, 126, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelFondo.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 465, 126, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        panelFondo.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 254, 126, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "DNI", "Nombres", "Ap. paterno", "Ap. materno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 295, 725, 150));

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
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // FILTRAR LISTADO
        String dni = txtDNI.getText();
        String nombre = txtNombre.getText();
        String ap_pat = txtApPat.getText();
        String ap_mat = txtApMat.getText();

        ArrayList<cliente> lista_clientes = controlador_cliente.listarClientes(dni,nombre, ap_pat, ap_mat);
        inicializar_Tabla(lista_clientes);

    }//GEN-LAST:event_btnFiltrarActionPerformed

    /* Métodos */
    private void inicializar(){
        ArrayList<cliente> lista_clientes = controlador_cliente.listarClientes("", "", "", "");
        inicializar_Tabla(lista_clientes);
    }

    
     private void inicializar_Tabla(ArrayList<cliente> lista_clientes){
         try{
            //jTable1.removeAll();
             DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
             modelo.setRowCount(0);
             Object[] obj = new Object[5];
             for (int i = 0; i < lista_clientes.size(); i++){
                 cliente cliente = lista_clientes.get(i);
                 obj[0] = cliente.getCodigo();
                 obj[1] = cliente.getPersona().getNumeroDocumentoIdentidad();
                 obj[2] = cliente.getPersona().getNombre();
                 obj[3] = cliente.getPersona().getApellidoPaterno();
                 obj[4] = cliente.getPersona().getApellidoMaterno();
                 modelo.addRow(obj);
             }
         
         }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
     }
    
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtDNI.setText("");
        txtNombre.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //Agregar cliente
        new Vista.Secretario_Crear_Cliente(x,true,tblClientes).setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //Eliminar cliente
        if (tblClientes.getSelectedRowCount()>0){
            //Eliminar cliente
            int select = tblClientes.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
            String dni = modelo.getValueAt(select, 1).toString();
            cliente cliente = controlador_cliente.obtenerClienteDNI(Integer.parseInt(dni));

            if (cliente != null){

                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar al client con numero de documento de identidad " + dni + " ?", "Alerta!", JOptionPane.YES_NO_OPTION);

                if (resp == 0){ // SI
                    controlador_cliente.eliminarCliente(cliente.getId());
                    inicializar();
                }

            }else{
                JOptionPane.showMessageDialog(null,
                    "Error al obtener cliente",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //Modificar cliente
        if (tblClientes.getSelectedRowCount()>0){
            int select = tblClientes.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
            String codigo = modelo.getValueAt(select, 0).toString();
            String dni = modelo.getValueAt(select, 1).toString();
            String nombre = modelo.getValueAt(select, 2).toString();
            String ap_pat = modelo.getValueAt(select, 3).toString();
            String ap_mat = modelo.getValueAt(select, 4).toString();

            cliente cliente = controlador_cliente.obtenerClienteDNI(Integer.parseInt(dni));

            if (cliente != null){
                //Modificar cliente

                JOptionPane.showMessageDialog(null,
                    "Cliente con el documento de identidad " + cliente.getPersona().getNumeroDocumentoIdentidad() + " será modificado",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);

                Vista.Secretario_Modificar_Cliente m_cliente = new Vista.Secretario_Modificar_Cliente(tblClientes,cliente, x,true,null);
                m_cliente.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,
                    "Error al obtener cliente",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label2;
    private java.awt.Label label5;
    private javax.swing.JPanel panelFiltrado;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApMat;
    private javax.swing.JTextField txtApPat;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
