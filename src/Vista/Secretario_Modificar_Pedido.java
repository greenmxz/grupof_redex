/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.AdministrarClienteBL;
import Controlador.generalBL;
import Controlador.aeropuertoBL;
import Controlador.AdministrarPedidoBL;
import Modelo.ciudad;
import Modelo.continente;
import Modelo.pais;
import Modelo.cliente;
import Modelo.persona;
import Modelo.pedido;
import Modelo.aeropuerto;
import Modelo.estado;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Nowa
 */
public class Secretario_Modificar_Pedido extends javax.swing.JDialog {

    /**
     * Creates new form Secretario_AdministrarPedido
     */
    ArrayList<estado> list_estado;
    AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    AdministrarPedidoBL controlador_pedido = new AdministrarPedidoBL();
    aeropuertoBL controlador_aeropuerto = new aeropuertoBL();
    generalBL general = new generalBL();
    javax.swing.JTable tabla;
    pedido pedido = null;
    cliente cliente_emisor = null;
    cliente cliente_receptor= null;
    aeropuerto aeropuerto_origen = null;
    aeropuerto aeropuerto_destino = null;
    
    public Secretario_Modificar_Pedido() {
        initComponents();
    }
    
    public Secretario_Modificar_Pedido(javax.swing.JTable tabla, pedido pedido,java.awt.Frame parent, boolean modal,frmAdministrarCuenta padre){
        
        /*
        public frmCrearCuenta(java.awt.Frame parent, boolean modal,frmAdministrarCuenta padre) {
        
        
        */
        super(parent, modal);
        try{
            initComponents();  
            label5.hide(); label4.hide();label2.hide(); label1.hide();
            jComboBox2.hide();jComboBox1.hide();
            jComboBox5.hide();jComboBox4.hide();
            cboEstado.hide();jLabel5.hide();
            this.tabla = tabla;
            this.pedido = pedido;
            
            this.cliente_emisor = pedido.getCliente_emisor();
            this.cliente_receptor = pedido.getCliente_receptor();
            this.aeropuerto_origen = pedido.getAeropuerto_emisor();
            this.aeropuerto_destino = pedido.getAeropuerto_receptor();
            inicializar_combo(jComboBox2,jComboBox1,jComboBox3);
            inicializar_combo(jComboBox5,jComboBox4,jComboBox6);
            ini_combo();
            set_index_comboBox(cboEstado,pedido.getEstado());
            inicializar_campos();
        }catch(Exception e){
            System.out.println("ERROR Secretario_Modificar_Pedido "+e.getMessage());
        }
    }
    

    public void set_index_comboBox(javax.swing.JComboBox<String> jComboBox, String valor){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) jComboBox.getModel();
            int i = 0;
            for (i=0;i<modelo.getSize();i++){
                if (modelo.getElementAt(i).toString().equals(valor)){
                    jComboBox.setSelectedIndex(i);
                }
            }
    }
    
    
    public void inicializar_campos(){
        try{
            
            String nombre, ap_pat, ap_mat, nombre_completo;
            
            nombre = cliente_emisor.getPersona().getNombre();
            ap_pat = cliente_emisor.getPersona().getApellidoPaterno();
            ap_mat = cliente_emisor.getPersona().getApellidoMaterno();
            nombre_completo = nombre + " " + ap_pat + " " + ap_mat;

            jTextField2.setText(nombre_completo);
            
            nombre = cliente_receptor.getPersona().getNombre();
            ap_pat = cliente_receptor.getPersona().getApellidoPaterno();
            ap_mat = cliente_receptor.getPersona().getApellidoMaterno();
            nombre_completo = nombre + " " + ap_pat + " " + ap_mat;
            
            jTextField5.setText(nombre_completo);
            
            jTextField1.setText(Integer.toString(cliente_emisor.getPersona().getNumeroDocumentoIdentidad())); 
            jTextField4.setText(Integer.toString(cliente_receptor.getPersona().getNumeroDocumentoIdentidad()));        
            jTextField6.setText(aeropuerto_origen.getNombre()); 
            jTextField7.setText(aeropuerto_destino.getNombre());
            txtMonto.setText(Double.toString(pedido.getMonto()));
            jTextArea1.setText(pedido.getDescripcion());


            set_index_comboBox(jComboBox2,aeropuerto_origen.getContinente());
            set_index_comboBox(jComboBox1,aeropuerto_origen.getPais());
            set_index_comboBox(jComboBox3,aeropuerto_origen.getCiudad());
            set_index_comboBox(jComboBox5,aeropuerto_destino.getContinente());
            set_index_comboBox(jComboBox4,aeropuerto_destino.getPais());
            set_index_comboBox(jComboBox6,aeropuerto_destino.getCiudad());
            
        }catch(Exception e){
            System.out.println("ERROR inicializar_campos "+e.getMessage());
        }
    }
    public void ini_combo(){
        cboEstado.removeAllItems();
         list_estado = general.listaEstados("estado_pedido");
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) cboEstado.getModel();           
            for(int i = 0; i < list_estado.size() ; i++){
                modelo1.addElement(list_estado.get(i).getValor());
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
    
    public String generar_codigo_provisional(String cod){
        
        // POR CAMBIAR
        int numero = (int) (Math.random() * 1000) + 1;
        
        String codigo = cod+Integer.toString(numero);
        
        return codigo;
    }
    
    
    private void actualizar_Tabla(){
        try{
            ArrayList<pedido> lista_pedidos = controlador_pedido.listarPedidos("","","","","","","","");
         
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
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
         
         }catch(Exception e){
            System.out.println("ERROR actualizar_Tabla "+e.getMessage());
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        label3 = new java.awt.Label();
        txtMonto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        label4 = new java.awt.Label();
        jComboBox2 = new javax.swing.JComboBox<>();
        label5 = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        label10 = new java.awt.Label();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        label12 = new java.awt.Label();
        label13 = new java.awt.Label();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label11 = new java.awt.Label();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Pedido");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setText("Descripción :");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 440, 100));

        label3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label3.setText("Monto :");
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, -1, -1));

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        getContentPane().add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 69, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del cliente emisor"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, -1));

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 270, -1));

        label1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label1.setText("Nombre :");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("No de doc. de identidad :");
        jPanel1.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 80, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label4.setText("Continente :");
        jPanel2.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 200, -1));

        label5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label5.setText("Pais :");
        jPanel2.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 200, -1));

        label10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label10.setText("Ciudad :");
        jPanel2.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 28));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, -1));

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jLabel3.setText("Aeropuerto :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jTextField6.setEditable(false);
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, 180));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 370, 310));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del cliente receptor"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 100, -1));

        jTextField5.setEditable(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 270, -1));

        label12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label12.setText("Nombre :");
        jPanel3.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        label13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label13.setText("No de doc. de identidad :");
        jPanel3.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 80, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 200, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 200, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, -1));

        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jTextField7.setEditable(false);
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 200, -1));

        label6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label6.setText("Continente :");
        jPanel5.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        label7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label7.setText("Pais :");
        jPanel5.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        label11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label11.setText("Ciudad :");
        jPanel5.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 28));

        jLabel6.setText("Aeropuerto :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, 180));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 360, 310));

        jLabel5.setText("Estado:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, -1, -1));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 110, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("MODIFICACIÓN DE PEDIDOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jButton4.setText("Cancelar");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 500, -1, -1));

        jButton3.setText("Modificar");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // MODIFICAR PEDIDO
        
        if (cliente_emisor != null && cliente_receptor != null && aeropuerto_origen != null && aeropuerto_destino != null && txtMonto!=null){

            if (aeropuerto_origen.getId() == aeropuerto_destino.getId()){
                JOptionPane.showMessageDialog(null,
                    "Los aeropuertos deben ser diferentes",
                    "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{

                if (cliente_emisor.getId() == cliente_receptor.getId()){
                    JOptionPane.showMessageDialog(null,
                        "Los clientes deben ser diferentes",
                        "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else{

                    // REGISTRAR PEDIDO
                    String monto=txtMonto.getText();
                    int size=monto.length();
                    for(int i=0;i<size;i++){
                        if(!(monto.charAt(i)>='0' && monto.charAt(i)<='9')){
                            JOptionPane.showMessageDialog(null,
                                "El monto debe ser mayor a cero",
                                "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        
                    }
                    pedido pedido = new pedido();
                    pedido.setId(this.pedido.getId());
                    String codigo_pedido = this.pedido.getCodigo();
                    pedido.setCodigo(codigo_pedido);
                    //Fecha de pedido(se actualiza la fecha)
                    java.util.Date fecha = new Date();
                    System.out.println (fecha); 
                    java.util.Date  fecha_f_pedido = general.manejo_fechas_24hs(fecha);
                    System.out.println (fecha_f_pedido);
                    pedido.setFecha_pedido(fecha_f_pedido);
                    ///////////////////////////////////
                    pedido.setDescripcion(jTextArea1.getText());
                    pedido.setMonto(Double.parseDouble(txtMonto.getText()));
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
                    int index = cboEstado.getSelectedIndex();
                    estado e = this.list_estado.get(index);
                    pedido.setEstado(e.getValor());
                    
                    if(controlador_pedido.modificarPedido(pedido)){
                        JOptionPane.showMessageDialog(null, 
                            "Pedido modificado exitosamente.", 
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        actualizar_Tabla();
                        this.dispose();
                        return;
                    }else{
                        JOptionPane.showMessageDialog(null, 
                            "Error al modificar pedido.", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    
                }
 
            }   
            
        }else{
            
            if (cliente_emisor == null){
                JOptionPane.showMessageDialog(null, 
                            "Se debe indicar el cliente emisor", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (cliente_receptor == null){
                JOptionPane.showMessageDialog(null, 
                            "Se debe indicar el cliente receptor", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (aeropuerto_origen == null){
                JOptionPane.showMessageDialog(null, 
                            "Se debe indicar un origen adecuado", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (aeropuerto_destino == null){
                JOptionPane.showMessageDialog(null, 
                            "Se debe indicar un destino adecuado", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BUSCAR CLIENTE_EMI POR DNI
        int dni_emisor = Integer.parseInt(jTextField1.getText());
        cliente cliente_emisor = controlador_cliente.obtenerClienteDNI(dni_emisor);
        String nombre = cliente_emisor.getPersona().getNombre() + " "
        + cliente_emisor.getPersona().getApellidoPaterno() + " "
        + cliente_emisor.getPersona().getApellidoMaterno();
        jTextField2.setText(nombre);
        this.cliente_emisor = cliente_emisor;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BUCAR AEROPUERTO ORIGEN
        String ciudad = jComboBox3.getSelectedItem().toString();
        aeropuerto aeropuerto = controlador_aeropuerto.obtenerAeropuertoxCiudad(ciudad);
        jTextField6.setText(aeropuerto.getNombre());
        this.aeropuerto_origen = aeropuerto;

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BUSCAR CLIENTE RECEPTOR
        int dni_receptor = Integer.parseInt(jTextField4.getText());
        cliente cliente_receptor = controlador_cliente.obtenerClienteDNI(dni_receptor);
        String nombre = cliente_receptor.getPersona().getNombre() + " "
        + cliente_receptor.getPersona().getApellidoPaterno() + " "
        + cliente_receptor.getPersona().getApellidoMaterno();
        jTextField5.setText(nombre);
        this.cliente_receptor = cliente_receptor;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // BUCAR AEROPUERTO DESTINO
        String ciudad = jComboBox6.getSelectedItem().toString();
        aeropuerto aeropuerto = controlador_aeropuerto.obtenerAeropuertoxCiudad(ciudad);
        jTextField7.setText(aeropuerto.getNombre());
        this.aeropuerto_destino = aeropuerto;

    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Secretario_Modificar_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Secretario_Modificar_Pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboEstado;
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
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
