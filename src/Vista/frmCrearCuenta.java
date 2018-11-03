package Vista;

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


public class frmCrearCuenta extends javax.swing.JDialog {

    /* Atributos */
    private generalBL generalBL;
    private ArrayList<rol> roles;
    private ArrayList<ciudad> ciudades;
    private ArrayList<tipoDocumento> tipoDocumentos;
    private usuarioBL usuarioBL; 
    private usuario usuarioSeleccionado;
    int index;
    int id_persona;
    public frmCrearCuenta(java.awt.Frame parent, boolean modal,frmAdministrarCuenta padre) {
        super(parent, modal);
        generalBL = new generalBL();
        usuarioBL = new usuarioBL();
        initComponents();
        setLocationRelativeTo(parent);
        inicializar();
        index=  padre.getUsuarioSeleccionado();
        
        if (index >=0){
            this.registrar.setText("Modificar");
            this.usuarioSeleccionado=usuarioBL.obtenerInfoUsuario(index);
            this.numeroDocumento.setText(Integer.toString(this.usuarioSeleccionado.getPersona().getNumeroDocumentoIdentidad()));
            this.nombrePersona.setText(this.usuarioSeleccionado.getPersona().getNombre());
            this.apellidoPaterno.setText(this.usuarioSeleccionado.getPersona().getApellidoPaterno());
            this.apellidoMaterno.setText(this.usuarioSeleccionado.getPersona().getApellidoMaterno());
            setSelectedValueRol(this.rol,this.usuarioSeleccionado.getRol());
            this.nombreUsuario.setText(this.usuarioSeleccionado.getCodigo());
            //Setear date a localdate;
            LocalDate date = this.usuarioSeleccionado.getPersona().getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            this.fechaNac.setDate( date);
            setSelectedValueCiudad(this.ciudad,this.usuarioSeleccionado.getPersona().getCiudad());
            setSelectedValueTipoDocumento(this.tipoDocumento,this.usuarioSeleccionado.getPersona().getTipoDocumento());
            this.correo.setText(this.usuarioSeleccionado.getPersona().getCorreo());
            this.direccion.setText(this.usuarioSeleccionado.getPersona().getDireccion());
            System.out.println(this.usuarioSeleccionado.getPersona().getNombre()+ " - - "+this.usuarioSeleccionado.getPersona().getId());
            this.contraseña.setText(this.usuarioSeleccionado.getPassword());
            
            this.contraseñaRepetir.setText(this.usuarioSeleccionado.getPassword());
        }else{
            this.registrar.setText("Registrar");
        }
        System.out.println("this selected index "+index);


    }
    
    /* Métodos */
    private void  inicializar(){
        //Setear al combobox los roles del sistema
        roles = generalBL.obtenerRoles();
        this.rol.setModel(new DefaultComboBoxModel ( (roles).toArray()));
        
        //Setear al combobox las ciudades del sistema
        ciudades= generalBL.obtenerCiudades();
        this.ciudad.setModel(new DefaultComboBoxModel( (ciudades).toArray()));
        
        //Setear al combobox los tipos documentos
        tipoDocumentos = generalBL.obtenerTipoDocumentos();
        this.tipoDocumento.setModel(new DefaultComboBoxModel( (tipoDocumentos).toArray()));
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
        if(this.nombrePersona.getText().length()>0 && this.apellidoPaterno.getText().length()>0 && 
           this.apellidoMaterno.getText().length()>0 && this.fechaNac.getDate()!= null &&
           this.tipoDocumento.getSelectedItem()!=null && this.numeroDocumento!=null&&
           this.nombreUsuario.getText().length()>0 && this.contraseña.getText().length()>0&&
           this.contraseñaRepetir.getText().length()>0 &&
           this.rol.getSelectedItem()!=null && this.ciudad.getSelectedItem()!=null &&
                this.direccion.getText().length()>0){
            return true;
        }else{
            return false;
        }
        
    }
    private boolean validarContraseña(){
        return this.contraseña.getText().equals(this.contraseñaRepetir.getText());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombrePersona = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellidoPaterno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellidoMaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipoDocumento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        numeroDocumento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ciudad = new javax.swing.JComboBox<>();
        direccion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        correo = new javax.swing.JTextField();
        fechaNac = new com.github.lgooddatepicker.components.DatePicker();
        jPanel2 = new javax.swing.JPanel();
        nombreUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rol = new javax.swing.JComboBox<>();
        contraseña = new javax.swing.JPasswordField();
        contraseñaRepetir = new javax.swing.JPasswordField();
        registrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "Persona"));

        jLabel1.setText("Nombres:");

        nombrePersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombrePersonaKeyTyped(evt);
            }
        });

        jLabel2.setText("Apellido paterno:");

        apellidoPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoPaternoKeyTyped(evt);
            }
        });

        jLabel3.setText("Apellido materno:");

        apellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoMaternoKeyTyped(evt);
            }
        });

        jLabel6.setText("Fecha de nacimiento:");

        jLabel4.setText("Tipo documento:");

        tipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Número de documento:");

        numeroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroDocumentoKeyTyped(evt);
            }
        });

        jLabel11.setText("Ciudad:");

        ciudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Dirección:");

        jLabel13.setText("Correo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(apellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidoPaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numeroDocumento)
                                    .addComponent(nombrePersona, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipoDocumento, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(nombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(apellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(apellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuario"));

        jLabel7.setText("Nombre de usuario:");

        jLabel8.setText("Contraseña:");

        jLabel9.setText("Repetir contraseña:");

        jLabel10.setText("Rol:");

        rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rol.setSelectedItem(rol);

        contraseña.setText("jPasswordField1");
        contraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contraseñaMouseClicked(evt);
            }
        });
        contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraseñaActionPerformed(evt);
            }
        });

        contraseñaRepetir.setText("jPasswordField2");
        contraseñaRepetir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contraseñaRepetirMouseClicked(evt);
            }
        });
        contraseñaRepetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraseñaRepetirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(44, 44, 44)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombreUsuario)
                            .addComponent(contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28))
                            .addComponent(contraseñaRepetir, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(contraseñaRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");

        jButton3.setText("Cancelar");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(registrar)
                        .addGap(47, 47, 47)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrar)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(12, Short.MAX_VALUE))
        );

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

    private void nombrePersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombrePersonaKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if((Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_nombrePersonaKeyTyped

    private void apellidoPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoPaternoKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if((Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_apellidoPaternoKeyTyped

    private void apellidoMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoMaternoKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if((Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_apellidoMaternoKeyTyped

    private void numeroDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroDocumentoKeyTyped
        // TODO add your handling code here:
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_numeroDocumentoKeyTyped

    private void contraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraseñaMouseClicked
        // TODO add your handling code here:
        this.contraseña.setText("");
    }//GEN-LAST:event_contraseñaMouseClicked

    private void contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseñaActionPerformed
        // TODO add your handling code here:
        this.contraseña.setText("");
    }//GEN-LAST:event_contraseñaActionPerformed

    private void contraseñaRepetirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contraseñaRepetirMouseClicked
        // TODO add your handling code here:
        this.contraseñaRepetir.setText("");
    }//GEN-LAST:event_contraseñaRepetirMouseClicked

    private void contraseñaRepetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraseñaRepetirActionPerformed
        // TODO add your handling code here:
        this.contraseñaRepetir.setText("");
    }//GEN-LAST:event_contraseñaRepetirActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        try {
        
        if (validarDatos()){
            JOptionPane.showMessageDialog(this, "Todos los datos fueron digitados");
            System.out.println("acaaaa "+ fechaNac.getDate() + " " + fechaNac.getText());
            if (validarContraseña()){
                usuario usuarioNuevo = new usuario();
                persona personaNueva = new persona();
                usuarioNuevo.setCodigo(this.nombreUsuario.getText());
                usuarioNuevo.setPassword(this.contraseña.getText());
                usuarioNuevo.setRol(this.rol.getSelectedItem().toString());

                personaNueva.setNombre(this.nombrePersona.getText());
                personaNueva.setApellidoPaterno(this.apellidoPaterno.getText());
                personaNueva.setApellidoMaterno(this.apellidoMaterno.getText());
                personaNueva.setCorreo(this.correo.getText());
//                
//                SimpleDateFormat  sdf;
//                String            s;
//                sdf = new SimpleDateFormat("yyyy-MM-dd");  // Or whatever format you need
//                s = sdf.format(this.fechaNac.getDate()); 
//                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date fd =   formatter.parse(s);
//                java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
                
//                LocalDate localDate = LocalDate.now();//For reference
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLLL-dd");
//                String formattedString = this.fechaNac.getDate().format(formatter);
//                
//                System.out.println("AQI "+formattedString);
//                
//                personaNueva.setFechaNacimiento( new Date(formattedString) );

                personaNueva.setNumeroDocumentoIdentidad(parseInt(this.numeroDocumento.getText()));
                personaNueva.setDireccion(direccion.getText());
                personaNueva.setCiudad(ciudad.getSelectedItem().toString());
                personaNueva.setNumeroDocumentoIdentidad(Integer.parseInt(numeroDocumento.getText()));
                personaNueva.setTipoDocumento(tipoDocumento.getSelectedItem().toString());
                usuarioNuevo.setPersona(personaNueva);
                System.out.println("persona "+personaNueva.toString());
                
                if (index>0){
                    usuarioNuevo.setId(this.index);
                    usuarioNuevo.getPersona().setId(this.usuarioSeleccionado.getPersona().getId());
                    if (usuarioBL.modificarUsuario(usuarioNuevo)){
                        
                        JOptionPane.showMessageDialog(this, "Se modifico correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error inténtelo más tarde.");
                    }
                }else{
                    if (usuarioBL.crearUsuario(usuarioNuevo)){
                        JOptionPane.showMessageDialog(this, "Se registro correctamente");
                    }else{
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error inténtelo más tarde.");
                    }
                }
                

            }else{
                JOptionPane.showMessageDialog(this, "Contraseñas diferentes error");
            }

        }else{
            System.out.println("cbo "+this.rol.getSelectedItem());
            Date d = new Date(this.fechaNac.getText());
            System.out.println("date "+d);
            JOptionPane.showMessageDialog(this, "Debe de llenar todos los datos");
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_registrarActionPerformed

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
            java.util.logging.Logger.getLogger(frmCrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmCrearCuenta dialog = new frmCrearCuenta(new javax.swing.JFrame(), true,new frmAdministrarCuenta(new javax.swing.JFrame()));
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
    private javax.swing.JTextField apellidoMaterno;
    private javax.swing.JTextField apellidoPaterno;
    private javax.swing.JComboBox<String> ciudad;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JPasswordField contraseñaRepetir;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private com.github.lgooddatepicker.components.DatePicker fechaNac;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombrePersona;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JTextField numeroDocumento;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton registrar;
    private javax.swing.JComboBox<String> rol;
    private javax.swing.JComboBox<String> tipoDocumento;
    // End of variables declaration//GEN-END:variables
}
