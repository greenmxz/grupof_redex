package Vista;

import Controlador.AdministrarClienteBL;
import Controlador.AdministrarPedidoBL;
import Controlador.aeropuertoBL;
import Controlador.generalBL;
import Modelo.aeropuerto;
import Modelo.cliente;
import Modelo.estado;
import Modelo.pedido;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Secretario_Administrar_Pedido extends javax.swing.JPanel {

    private generalBL general = new generalBL();
    private AdministrarPedidoBL controlador_pedido = new AdministrarPedidoBL();
    private aeropuertoBL controlador_aeropuerto = new aeropuertoBL();
    private AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    ArrayList<aeropuerto> listAero;
    ArrayList<estado> listEstado;
    
    public Secretario_Administrar_Pedido() {
        initComponents();
//        inicializar();
    }

    /* Métodos */
    private void inicializar(){
        String [] rol = {"Secre"};
        ArrayList<aeropuerto> listAero = controlador_aeropuerto.listaAeropuertos();
        ArrayList<estado> listEstado = general.listaEstados("estado_pedido");
        this.listAero = listAero;
        this.listEstado = listEstado;
        ArrayList<pedido> lista_pedidos = controlador_pedido.listarPedidos("","","","","","","","");
        inicializar_Tabla(lista_pedidos);
        inicializar_ComboBox();
    }
    
    private void inicializar_ComboBox(){
        try{
            ArrayList<String> listDetAero = new ArrayList<>();
        
            for(int i = 0; i < listAero.size();i++){
                aeropuerto aeropuerto = listAero.get(i);
                String detalle = aeropuerto.getPais() + " " + aeropuerto.getCiudad() + " - " + aeropuerto.getNombre();
                listDetAero.add(detalle);
            }
            
            cboOrigen.removeAllItems();
            cboDestino.removeAllItems();
            cboEstado.removeAllItems();
            
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) cboOrigen.getModel();           
            for(int i = 0; i < listDetAero.size() ; i++){
                modelo1.addElement(listDetAero.get(i));
            }
            DefaultComboBoxModel modelo2 = (DefaultComboBoxModel) cboDestino.getModel();           
            for(int i = 0; i < listDetAero.size() ; i++){
                modelo2.addElement(listDetAero.get(i));
            }
            DefaultComboBoxModel modelo3 = (DefaultComboBoxModel) cboEstado.getModel();           
            for(int i = 0; i < listEstado.size() ; i++){
                modelo3.addElement(listEstado.get(i).getValor());
            }
        }catch(Exception e){
            System.out.println("ERROR en inicializar_ComboBox "+e.getMessage());
        }  
    }
    
    private void inicializar_Tabla(ArrayList<pedido> lista_pedidos){
        DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
        modelo.setRowCount(0);
        String nombre,ap_pat,ap_mat;
        Object[] obj = new Object[6];
        for (int i = 0; i < lista_pedidos.size(); i++){
             pedido pedido = lista_pedidos.get(i);
             obj[0] = pedido.getCodigo();
             obj[1] = pedido.getAeropuerto_emisor().getNombre();
             nombre = pedido.getCliente_emisor().getPersona().getNombre();
             ap_pat = pedido.getCliente_emisor().getPersona().getApellidoPaterno();
             ap_mat = pedido.getCliente_emisor().getPersona().getApellidoMaterno();
             obj[2] = nombre + " " + ap_pat + " " + ap_mat;
             obj[3] = pedido.getAeropuerto_receptor().getNombre();
             nombre = pedido.getCliente_receptor().getPersona().getNombre();
             ap_pat = pedido.getCliente_receptor().getPersona().getApellidoPaterno();
             ap_mat = pedido.getCliente_receptor().getPersona().getApellidoMaterno();
             obj[4] = nombre + " " + ap_pat + " " + ap_mat;
             obj[5] = pedido.getEstado();
             modelo.addRow(obj);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        panelFiltrado = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        txtCodigo = new javax.swing.JTextField();
        label3 = new java.awt.Label();
        dateSalida = new com.toedter.calendar.JDateChooser();
        label5 = new java.awt.Label();
        cboOrigen = new javax.swing.JComboBox<>();
        label10 = new java.awt.Label();
        cboDestino = new javax.swing.JComboBox<>();
        label11 = new java.awt.Label();
        txtDniEmisor = new javax.swing.JTextField();
        label12 = new java.awt.Label();
        txtDniReceptor = new javax.swing.JTextField();
        label13 = new java.awt.Label();
        cboEstado = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        label4 = new java.awt.Label();
        dateLlegada = new com.toedter.calendar.JDateChooser();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panelFondo.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 126, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelFondo.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 470, 125, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        panelFondo.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 127, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("ADMINISTRACIÓN DE PEDIDOS");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Origen", "Cliente emisor", "Destino", "Cliente receptor", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 720, 125));

        panelFiltrado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        panelFiltrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("Codigo :");
        panelFiltrado.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        panelFiltrado.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 100, -1));

        label3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label3.setText("Fecha de pedido entre:");
        panelFiltrado.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));
        panelFiltrado.add(dateSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        label5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label5.setText("Aeropuerto Origen :");
        panelFiltrado.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        cboOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelFiltrado.add(cboOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 130, -1));

        label10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label10.setText("Aeropuerto Destino :");
        panelFiltrado.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        cboDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelFiltrado.add(cboDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 130, -1));

        label11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label11.setText("No doc. identidad Emisor :");
        panelFiltrado.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));
        panelFiltrado.add(txtDniEmisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 110, -1));

        label12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label12.setText("No doc. identidad Receptor :");
        panelFiltrado.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));
        panelFiltrado.add(txtDniReceptor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 110, -1));

        label13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label13.setText("Estado : ");
        panelFiltrado.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelFiltrado.add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 110, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 160, -1));

        label4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label4.setText("-");
        panelFiltrado.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));
        panelFiltrado.add(dateLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        btnLimpiar.setText("Limpiar filtro");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 160, -1));

        panelFondo.add(panelFiltrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 722, 210));

        jLabel1.setText("Resultado de filtrado");
        panelFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
//        trycatch(Exception e){
//            System.out.println("ERROR en FILTRAR pedido "+e.getMessage());
//        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //MODIFICAR PEDIDO
        if (tblPedidos.getSelectedRowCount()>0){
            //Modificar pedido

            int select = tblPedidos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
            String codigo = modelo.getValueAt(select, 0).toString();

            pedido pedido = controlador_pedido.obtenerPedidoxCodigo(codigo);

            if (pedido != null){
                Vista.Secretario_Modificar_Pedido m_pedido = new Vista.Secretario_Modificar_Pedido(tblPedidos,pedido);
                m_pedido.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,
                    "Error al obtener pedido",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (tblPedidos.getSelectedRowCount()>0){
            //Eliminar pedido

            int select = tblPedidos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
            String codigo = modelo.getValueAt(select, 0).toString();
            pedido pedido = controlador_pedido.obtenerPedidoxCodigo(codigo);

            if (pedido != null){

                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el pedido con codigo " + codigo + " ?", "Alerta!", JOptionPane.YES_NO_OPTION);

                if (resp == 0){ // SI

                    controlador_pedido.eliminarPedido(pedido.getId());
                    ArrayList<pedido> lista_pedidos = controlador_pedido.listarPedidos("","","","","","","","");
                    inicializar_Tabla(lista_pedidos);
                }

            }else{
                JOptionPane.showMessageDialog(null,
                    "Error al obtener pedido",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        //Crear pedido

//        Vista.Secretario_Crear_Pedido c_pedido = new Vista.Secretario_Crear_Pedido(tblPedidos);

//        c_pedido.setVisible(true);

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboDestino;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboOrigen;
    private com.toedter.calendar.JDateChooser dateLlegada;
    private com.toedter.calendar.JDateChooser dateSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private javax.swing.JPanel panelFiltrado;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDniEmisor;
    private javax.swing.JTextField txtDniReceptor;
    // End of variables declaration//GEN-END:variables
}
