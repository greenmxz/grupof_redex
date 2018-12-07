package Vista;

import Controlador.AdministrarClienteBL;
import Controlador.AdministrarPedidoBL;
import Controlador.aeropuertoBL;
import Controlador.generalBL;
import Modelo.aeropuerto;
import Modelo.ciudad;
import Modelo.cliente;
import Modelo.continente;
import Modelo.pais;
import Modelo.pedido;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Secretario_Crear_Pedido extends javax.swing.JDialog {

    AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    AdministrarPedidoBL controlador_pedido = new AdministrarPedidoBL();
    aeropuertoBL controlador_aeropuerto = new aeropuertoBL();
    generalBL general = new generalBL();
    javax.swing.JTable tabla;
    cliente cliente_emisor = null;
    cliente cliente_receptor= null;
    aeropuerto aeropuerto_origen = null;
    aeropuerto aeropuerto_destino = null;
    private static int id;
    public Secretario_Crear_Pedido(java.awt.Frame parent, boolean modal,int id) {
        super(parent, modal);
        initComponents();
        this.id=id;
    }
    
    public Secretario_Crear_Pedido(java.awt.Frame parent, boolean modal, javax.swing.JTable tabla,int id) {
        super(parent, modal);
        initComponents();
        inicializar_combo(jComboBox2,jComboBox1,jComboBox3);
        inicializar_combo(jComboBox5,jComboBox4,jComboBox6);
        this.tabla = tabla;
        this.id=id;
    }

    /* Métodos */  
   public String generar_codigo_provisional(String cod){
        
        // POR CAMBIAR
        int numero = (int) (Math.random() * 1000) + 1;
        
        String codigo = "ENV"+cod+Integer.toString(numero);
        
        return codigo;
    }
    
    
    private void actualizar_Tabla(){
         ArrayList<pedido> lista_pedidos = controlador_pedido.listarPedidos("","","","","","","","");
         
         DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
         modelo.setRowCount(0);
         String nombre,ap_pat,ap_mat;
         Object[] obj = new Object[5];
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
             //obj[5] = pedido.getEstado();
             modelo.addRow(obj);
        }
    }
    
    public void inicializar_combo(javax.swing.JComboBox<String> comboBox1, javax.swing.JComboBox<String> comboBox2, javax.swing.JComboBox<String> comboBox3){
        try{
            comboBox1.removeAllItems();
            comboBox2.removeAllItems();
            comboBox3.removeAllItems();

            //continente
            ArrayList<continente> list_continente = general.obtenerContinentes();
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) comboBox1.getModel();           
            for(int i = 0; i < list_continente.size() ; i++){
                modelo1.addElement(list_continente.get(i).getNombre());
            }
            
            //pais
            ArrayList<pais> list_pais = general.obtenerPaises();
            DefaultComboBoxModel modelo2 = (DefaultComboBoxModel) comboBox2.getModel();            
            for(int i = 0; i < list_pais.size() ; i++){
                modelo2.addElement(list_pais.get(i).getNombre());
            }

            //ciudad
            ArrayList<ciudad> list_ciudades = general.obtenerCiudades();
            DefaultComboBoxModel modelo3 = (DefaultComboBoxModel) comboBox3.getModel();   
            for(int i = 0; i < list_ciudades.size() ; i++){
                modelo3.addElement(list_ciudades.get(i).getNombre());
            }

        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        label3 = new java.awt.Label();
        jTextField3 = new javax.swing.JTextField();
        panelReceptor = new javax.swing.JPanel();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        jButton2 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        label14 = new java.awt.Label();
        label15 = new java.awt.Label();
        jComboBox4 = new javax.swing.JComboBox<>();
        label16 = new java.awt.Label();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelEmisor = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        label4 = new java.awt.Label();
        jComboBox2 = new javax.swing.JComboBox<>();
        label5 = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        label10 = new java.awt.Label();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label3.setText("Monto :");
        panelFondo.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        panelFondo.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 120, -1));

        panelReceptor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del cliente receptor"));
        panelReceptor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label12.setText("Nombre :");
        panelReceptor.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        label13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label13.setText("No de doc. de identidad :");
        panelReceptor.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelReceptor.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 80, -1));

        jTextField5.setEditable(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        panelReceptor.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 270, -1));
        panelReceptor.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label14.setText("Continente :");
        jPanel5.add(label14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        label15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label15.setText("Pais :");
        jPanel5.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 200, -1));

        label16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label16.setText("Ciudad :");
        jPanel5.add(label16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, -1));

        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jLabel4.setText("Aeropuerto :");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jTextField7.setEditable(false);
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 200, -1));

        panelReceptor.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 320, 180));

        panelFondo.add(panelReceptor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 360, 310));

        jButton3.setText("Registrar");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelFondo.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 120, -1));

        jButton4.setText("Cancelar");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelFondo.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 120, -1));

        panelEmisor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del cliente emisor"));
        panelEmisor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelEmisor.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, -1));

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        panelEmisor.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 270, -1));

        label1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label1.setText("Nombre :");
        panelEmisor.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("No de doc. de identidad :");
        panelEmisor.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelEmisor.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 80, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label4.setText("Continente:");
        jPanel4.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 200, -1));

        label5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label5.setText("País:");
        jPanel4.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 200, -1));

        label10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label10.setText("Ciudad:");
        jPanel4.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 28));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, -1));

        jLabel3.setText("Aeropuerto:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jTextField6.setEditable(false);
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, -1));

        panelEmisor.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 320, 180));

        panelFondo.add(panelEmisor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 370, 310));

        jLabel13.setText("Descripción :");
        panelFondo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 430, 70));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CREACIÓN DE PEDIDOS");
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

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BUSCAR CLIENTE RECEPTOR
//        int dni_receptor = Integer.parseInt(jTextField4.getText());
//        cliente cliente_receptor = controlador_cliente.obtenerClienteDNI(dni_receptor);
//        String nombre = cliente_receptor.getPersona().getNombre() + " "
//        + cliente_receptor.getPersona().getApellidoPaterno() + " "
//        + cliente_receptor.getPersona().getApellidoMaterno();
//        jTextField5.setText(nombre);
//        this.cliente_receptor = cliente_receptor;
//        
        
        frmBuscarCliente frm = new frmBuscarCliente(this,true);
        frm.setVisible(true);
        System.out.println("AQUI");
        cliente c = frm.getClienteSeleccionado();
        //int dni_emisor = Integer.parseInt(jTextField1.getText());
        if (c!= null){
            String nombre = c.getPersona().getNombre() + " "
            + c.getPersona().getApellidoPaterno() + " "
            + c.getPersona().getApellidoMaterno();
            jTextField4.setText(String.valueOf(c.getPersona().getNumeroDocumentoIdentidad()));
            jTextField5.setText( nombre );
            this.cliente_receptor = c;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // BUCAR AEROPUERTO DESTINO
        String ciudad = jComboBox6.getSelectedItem().toString();
        aeropuerto aeropuerto = controlador_aeropuerto.obtenerAeropuertoxCiudad(ciudad);
        jTextField7.setText(aeropuerto.getNombre());
        this.aeropuerto_destino = aeropuerto;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // REGISTRAR PEDIDO

        if (cliente_emisor != null && cliente_receptor != null && aeropuerto_origen != null && aeropuerto_destino != null){

            if (aeropuerto_origen.getId() == aeropuerto_destino.getId()){
                JOptionPane.showMessageDialog(null,
                    "Los aeropuertos deben ser diferentes",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }else{

                if (cliente_emisor.getId() == cliente_receptor.getId()){
                    JOptionPane.showMessageDialog(null,
                        "Los clientes deben ser diferentes",
                        "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                }else{

                    // REGISTRAR PEDIDO

                    pedido pedido = new pedido();
                    String codigo_pedido = generar_codigo_provisional(cliente_emisor.getCodigo());
                    pedido.setCodigo(codigo_pedido);
                    //Fecha de pedido
                    java.util.Date fecha = new Date();
                    System.out.println (fecha);
                    java.util.Date  fecha_f_pedido = general.manejo_fechas_24hs(fecha);
                    System.out.println (fecha_f_pedido);
                    pedido.setFecha_pedido(fecha_f_pedido);
                    ///////////////////////////////////
                    pedido.setDescripcion(jTextArea1.getText());
                    pedido.setMonto(Double.parseDouble(jTextField3.getText()));
                    // cliente emisor
                    pedido.setCliente_emisor(cliente_emisor);
                    //areopuerto origen
                    pedido.setAeropuerto_emisor(aeropuerto_origen);
                    //cliente receptor
                    pedido.setCliente_receptor(cliente_receptor);
                    //aeropuerto destino
                    pedido.setAeropuerto_receptor(aeropuerto_destino);
                    //aeropuerto actual
                    pedido.setAeropuerto_actual(aeropuerto_origen);

                    //estado incial provicional
                    pedido.setEstado(String.valueOf(id));

                    if(controlador_pedido.registrarPedido(pedido)){
                        JOptionPane.showMessageDialog(null,
                            "Pedido registrado exitosamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        actualizar_Tabla();
                        //this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,
                            "Error al registrar pedido.",
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }

        }else{

            if (cliente_emisor == null){
                JOptionPane.showMessageDialog(null,
                    "Se debe indicar el cliente emisor",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

            if (cliente_receptor == null){
                JOptionPane.showMessageDialog(null,
                    "Se debe indicar el cliente receptor",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

            if (aeropuerto_origen == null){
                JOptionPane.showMessageDialog(null,
                    "Se debe indicar un origen adecuado",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

            if (aeropuerto_destino == null){
                JOptionPane.showMessageDialog(null,
                    "Se debe indicar un destino adecuado",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BUSCAR CLIENTE_EMI POR DNI
        frmBuscarCliente frm = new frmBuscarCliente(this,true);
        frm.setVisible(true);
        System.out.println("AQUI");
        cliente c = frm.getClienteSeleccionado();
        //int dni_emisor = Integer.parseInt(jTextField1.getText());
        if (c!= null){
            String nombre = c.getPersona().getNombre() + " "
            + c.getPersona().getApellidoPaterno() + " "
            + c.getPersona().getApellidoMaterno();
            jTextField2.setText(nombre);
            jTextField1.setText( String.valueOf(c.getPersona().getNumeroDocumentoIdentidad()) );
            this.cliente_emisor = c;
        }
        //cliente cliente_emisor = controlador_cliente.obtenerClienteDNI(dni_emisor);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BUCAR AEROPUERTO ORIGEN
        String ciudad = jComboBox3.getSelectedItem().toString();
        aeropuerto aeropuerto = controlador_aeropuerto.obtenerAeropuertoxCiudad(ciudad);
        jTextField6.setText(aeropuerto.getNombre());
        this.aeropuerto_origen = aeropuerto;
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Secretario_Crear_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretario_Crear_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Secretario_Crear_Pedido dialog = new Secretario_Crear_Pedido(new javax.swing.JFrame(), true,id);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label15;
    private java.awt.Label label16;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private javax.swing.JPanel panelEmisor;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelReceptor;
    // End of variables declaration//GEN-END:variables
}
