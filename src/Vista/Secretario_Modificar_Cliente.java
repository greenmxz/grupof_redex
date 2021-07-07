/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Nowa
 */
public class Secretario_Modificar_Cliente extends javax.swing.JDialog {

    AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    generalBL general = new generalBL();
    javax.swing.JTable tabla;
    cliente cliente;
    /**
     * Creates new form Secretario_AdministrarPedido
     */
    
    public Secretario_Modificar_Cliente() {
        initComponents();
        inicializar();
    }
    
    
     public Secretario_Modificar_Cliente(javax.swing.JTable tabla,cliente cliente,java.awt.Frame parent, boolean modal,frmAdministrarCuenta padre){
           super(parent, modal);
         initComponents();
        this.tabla = tabla;
        this.cliente = cliente;
        inicializar();
    }

    private void actualizar_tabla(){
        try{
            ArrayList<cliente> lista_clientes = controlador_cliente.listarClientes("", "", "", "");
         
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
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
    
     public void inicializar_cliente(){
         try{
            int dni = cliente.getPersona().getNumeroDocumentoIdentidad();
            String nombre = cliente.getPersona().getNombre();
            String ap_pat = cliente.getPersona().getApellidoPaterno();
            String ap_mat = cliente.getPersona().getApellidoMaterno();
            String correo = cliente.getPersona().getCorreo();
            String telefono = cliente.getPersona().getTelefono();
            String direccion = cliente.getPersona().getDireccion();

            jTextField1.setText(Integer.toString(dni));
            jTextField6.setText(nombre);
            jTextField7.setText(ap_pat);
            jTextField8.setText(ap_mat);
            jTextField9.setText(correo);
            jTextField10.setText(telefono);
            jTextField11.setText(direccion);
            
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) jComboBox7.getModel();
            DefaultComboBoxModel modelo2 = (DefaultComboBoxModel) jComboBox8.getModel();
            DefaultComboBoxModel modelo3 = (DefaultComboBoxModel) jComboBox9.getModel();
            
            int i = 0;
            for (i=0;i<modelo1.getSize();i++){
                if (modelo1.getElementAt(i).toString().equals(cliente.getPersona().getContinente())){
                    jComboBox7.setSelectedIndex(i);
                }
            }
            for (i=0;i<modelo2.getSize();i++){
                if (modelo2.getElementAt(i).toString().equals(cliente.getPersona().getPais())){
                    jComboBox8.setSelectedIndex(i);
                }
            }
            for (i=0;i<modelo3.getSize();i++){
                if (modelo3.getElementAt(i).toString().equals(cliente.getPersona().getCiudad())){
                    jComboBox9.setSelectedIndex(i);
                }
            }
         }catch(Exception e){
            System.out.println("ERROR al inicializar cliente"+e.getMessage());
        }
     }
     
     public void inicializar(){
         try{
            jComboBox7.removeAllItems();
            jComboBox8.removeAllItems();
            jComboBox9.removeAllItems();

            //continente
            ArrayList<continente> list_continente = general.obtenerContinentes();
            DefaultComboBoxModel modelo1 = (DefaultComboBoxModel) jComboBox7.getModel();           
            for(int i = 0; i < list_continente.size() ; i++){
                modelo1.addElement(list_continente.get(i).getNombre());
            }
            
            //pais
            ArrayList<pais> list_pais = general.obtenerPaises();
            DefaultComboBoxModel modelo2 = (DefaultComboBoxModel) jComboBox8.getModel();            
            for(int i = 0; i < list_pais.size() ; i++){
                modelo2.addElement(list_pais.get(i).getNombre());
            }

            //ciudad
            ArrayList<ciudad> list_ciudades = general.obtenerCiudades();
            DefaultComboBoxModel modelo3 = (DefaultComboBoxModel) jComboBox9.getModel();   
            for(int i = 0; i < list_ciudades.size() ; i++){
                modelo3.addElement(list_ciudades.get(i).getNombre());
            }

            //DATA CLIENTE
            if (cliente!=null){
                inicializar_cliente();
            }else {
                JOptionPane.showMessageDialog(null, 
                            "No se pudo cargar data del cliente", 
                            "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
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
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label18 = new java.awt.Label();
        jTextField6 = new javax.swing.JTextField();
        label19 = new java.awt.Label();
        jTextField7 = new javax.swing.JTextField();
        label20 = new java.awt.Label();
        jTextField8 = new javax.swing.JTextField();
        label21 = new java.awt.Label();
        jTextField9 = new javax.swing.JTextField();
        label22 = new java.awt.Label();
        jTextField10 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        label13 = new java.awt.Label();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        label23 = new java.awt.Label();
        jComboBox9 = new javax.swing.JComboBox<>();
        label24 = new java.awt.Label();
        label25 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField11 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
        setTitle("Modificar Cliente");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos básicos de cliente"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 150, -1));

        label2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label2.setText("DNI :");
        jPanel1.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 40, -1, -1));

        label18.setText("Nombre :");
        jPanel1.add(label18, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 70, -1, -1));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 150, -1));

        label19.setText("Apellido Paterno :");
        jPanel1.add(label19, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 100, -1, -1));

        jTextField7.setToolTipText("");
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 150, -1));

        label20.setText("Apellido Materno :");
        jPanel1.add(label20, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 130, -1, -1));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, -1));

        label21.setText("Correo :");
        jPanel1.add(label21, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 160, -1, -1));
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 150, -1));

        label22.setText("Telefono :");
        jPanel1.add(label22, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 190, -1, -1));
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 350, 230));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 570, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicación de cliente"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label13.setText("Continente :");
        jPanel3.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 40, -1, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 150, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 150, -1));

        label23.setText("Pais :");
        jPanel3.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 70, -1, -1));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 150, -1));

        label24.setText("Ciudad :");
        jPanel3.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 100, -1, -1));

        label25.setText("Dirección :");
        jPanel3.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 138, -1, -1));

        jTextField11.setColumns(20);
        jTextField11.setRows(5);
        jScrollPane1.setViewportView(jTextField11);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, 70));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 350, 230));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("MODIFICACIÓN DE CLIENTES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jButton3.setText("Modificar");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        jButton4.setText("Cancelar");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // MODIFICAR
        try{
            String dni = jTextField1.getText();
            String nombre = jTextField6.getText();
            String ap_pat = jTextField7.getText();
            String ap_mat = jTextField8.getText();
            String correo = jTextField9.getText();
            String telefono = jTextField10.getText();

            String continente = jComboBox7.getSelectedItem().toString();
            String pais = jComboBox8.getSelectedItem().toString();
            String ciudad = jComboBox9.getSelectedItem().toString();

            String direccion = jTextField11.getText();


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

            //cliente.setCantidad_pedidos(0);
            cliente.setCodigo(dni);
            cliente.setPersona(persona);
            cliente.setCodigo(dni);
            
            
            switch(controlador_cliente.modificarCliente(cliente)){
                
                case 1:
                    
                    JOptionPane.showMessageDialog(null, 
                            "El cliente ha sido correctamente modificado", 
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    
                    if(tabla != null){
                        actualizar_tabla();
                    }
                    this.dispose();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, 
                            "Error al modificar el cliente", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case 2:
                    JOptionPane.showMessageDialog(null, 
                            "El cliente ha modificar no existe", 
                            "Mensaje Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                
            }
            
            
            
            
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Secretario_Modificar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretario_Modificar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Secretario_Modificar_Cliente().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextArea jTextField11;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
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
    // End of variables declaration//GEN-END:variables
}
