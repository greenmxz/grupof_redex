package Vista;

import Controlador.VueloBL;
import Controlador.generalBL;
import Vista.frmAdministrarCuenta;
import Controlador.usuarioBL;
import Modelo.*;
import java.awt.Component;
import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;


public class frmCrearPlanes extends javax.swing.JDialog {

    /* Atributos */
    private generalBL generalBL;
    private ArrayList<rol> roles;
    private ArrayList<ciudad> ciudades;
    private ArrayList<tipoDocumento> tipoDocumentos;
    private usuarioBL usuarioBL; 
    private Avion avionSeleccionado;
    private VueloBL vueloBL;
    private Avion avionNuevo;
    
    int index;
    int id_persona;
    public frmCrearPlanes(java.awt.Frame parent, boolean modal,frmAdministrarVuelo padre) {
        super(parent, modal);
        vueloBL=new VueloBL();
        generalBL = new generalBL();
        usuarioBL = new usuarioBL();
        initComponents();
        setLocationRelativeTo(parent);
        inicializar();
        index=  padre.getUsuarioSeleccionado();
        System.out.println("INDEX PADRE +"+index);
        if (index >=0){
            this.registrarTxt.setText("Modificar avión");
            this.registrar.setText("Modificar");
            this.avionSeleccionado=vueloBL.obtenerInfoVuelo(index);
            this.capacidad.setText(String.valueOf(this.avionSeleccionado.getCapacidadMaxima()) );
            this.codigo.setText(this.avionSeleccionado.getCodigo());
            this.descripcion.setText(this.avionSeleccionado.getDescripcion());
            
            

        }else{
            this.registrar.setText("Registrar");
        }
        System.out.println("this selected index "+index);


    }
    
    /* Métodos */
    private void  inicializar(){
        //Setear al combobox los roles del sistema
//        roles = generalBL.obtenerRoles();
//        this.rol.setModel(new DefaultComboBoxModel ( (roles).toArray()));
//        
//        //Setear al combobox las ciudades del sistema
//        ciudades= generalBL.obtenerCiudades();
//        this.ciudad.setModel(new DefaultComboBoxModel( (ciudades).toArray()));
//        
//        //Setear al combobox los tipos documentos
//        tipoDocumentos = generalBL.obtenerTipoDocumentos();
//        this.tipoDocumento.setModel(new DefaultComboBoxModel( (tipoDocumentos).toArray()));
    }
    public static void setSelectedValueRol(JComboBox comboBox, String value)
    {
        rol item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = (rol)comboBox.getItemAt(i);
            if (item.getNombre().equals(value))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
     public static void setSelectedValueCiudad(JComboBox comboBox, String value)
    {
        ciudad item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = (ciudad)comboBox.getItemAt(i);
            if (item.getNombre().equals(value))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
      public static void setSelectedValueTipoDocumento(JComboBox comboBox, String value)
    {
        tipoDocumento item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = (tipoDocumento)comboBox.getItemAt(i);
            if (item.getNombre().equals(value))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
    private boolean validarDatos(){
        if(this.codigo.getText().length()>0 && this.capacidad.getText().length()>0  
           && this.descripcion.getText().length()>0){
            return true;
        }else{
            return false;
        }
        
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        capacidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        registrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        registrarTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "Vuelo"));

        jLabel1.setText("Codigo:");

        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codigoKeyTyped(evt);
            }
        });

        jLabel2.setText("Capacidad:");

        capacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capacidadActionPerformed(evt);
            }
        });
        capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                capacidadKeyTyped(evt);
            }
        });

        jLabel13.setText("Descripción:");

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(capacidad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        registrarTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registrarTxt.setText("Registrar vuelos");
        registrarTxt.setToolTipText("");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(registrar)
                        .addGap(47, 47, 47)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(registrarTxt))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registrarTxt)
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrar)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Vuelo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

       java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;
    }
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        try {
        
            if (validarDatos()){
                Avion v = new Avion();
                v.setCodigo(codigo.getText());
                v.setCapacidadMaxima(Integer.parseInt(capacidad.getText()));
                v.setDescripcion(descripcion.getText());
                
                
                
                if (index>0){
                    avionNuevo= new Avion();
                    avionNuevo.setId(this.index);
                    avionNuevo.setCapacidadMaxima(Integer.parseInt(this.capacidad.getText()));
                    avionNuevo.setDescripcion(this.descripcion.getText());
                    avionNuevo.setCodigo(this.codigo.getText());
                    if (vueloBL.modificarVuelo(avionNuevo)){
                        
                        JOptionPane.showMessageDialog(this, "Se modifico correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error inténtelo más tarde.");
                    }
                }else{
                    if (vueloBL.registrarVuelo(v)){
                        
                    JOptionPane.showMessageDialog(this,"Se ha creado el vuelo correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error inténtelo más tarde.");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this,"Ha ocurrido un error al registrar el vuelo");
            }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void capacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_capacidadKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_capacidadKeyTyped

    private void codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyTyped
        // TODO add your handling code here:
//        char vchar = evt.getKeyChar();
//        if((Character.isDigit(vchar))
//            || (vchar == KeyEvent.VK_BACK_SPACE)
//            || (vchar == KeyEvent.VK_DELETE))
//        evt.consume();
    }//GEN-LAST:event_codigoKeyTyped

    private void capacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capacidadActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(frmCrearPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrearPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrearPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrearPlanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmCrearPlanes dialog = new frmCrearPlanes(new javax.swing.JFrame(), true,new frmAdministrarVuelo(new javax.swing.JFrame()));
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
    private javax.swing.JTextField capacidad;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton registrar;
    private javax.swing.JLabel registrarTxt;
    // End of variables declaration//GEN-END:variables
}
