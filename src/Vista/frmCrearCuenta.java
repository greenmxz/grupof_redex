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
        jButton2.hide();
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
//             this.usuarioSeleccionado.getPersona().getFechaNacimiento();
//            LocalDate localD = date.toLocalDate();
            System.out.println("Persona date1 " + this.usuarioSeleccionado.getPersona().getFechaNacimiento());
            System.out.println("Persona date 2" + date);
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
    private boolean aceptarPatronContrasena(){
        int size=contraseña.getText().length();
        boolean hasNum=false;
        boolean hasNoChar=false;
        if(size>=8){
            for(int i=0;i<size;i++){
                char car=contraseña.getText().charAt(i);
                if(car>='0' && car<='9') hasNum=true;
                else if(car<'A' || car>'z'){                    
                    hasNoChar=true;
                    break;
                }
            }
            if(hasNum && !hasNoChar){
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"Su contraseña debe componer como mínimo 8 caracteres, "
                + "incluir mínimo un dígito y que sus letras pertenezcan a caracteres alfabéticos.",
                "Error de contraseña", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }
  
    private boolean validarDatos(){
        if(this.nombrePersona.getText().length()>0 && this.apellidoPaterno.getText().length()>0 && 
           this.apellidoMaterno.getText().length()>0 && this.fechaNac.getDate()!= null &&
           this.tipoDocumento.getSelectedItem()!=null && this.numeroDocumento!=null &&
           this.nombreUsuario.getText().length()>0 && this.contraseña.getText().length()>0 &&
           this.contraseñaRepetir.getText().length()>0  &&
           this.rol.getSelectedItem()!=null && this.ciudad.getSelectedItem()!=null &&
                this.direccion.getText().length()>0){
            if(this.contraseña.getText().equals(this.contraseñaRepetir.getText())==false){
                JOptionPane.showMessageDialog(null,"Su contraseña debe ser igual a la contraseña repetida."
                    ,"Error de contraseña",JOptionPane.INFORMATION_MESSAGE);
                return false;
               
            }if(usuarioBL.existeNumDoc(Integer.valueOf(this.numeroDocumento.getText()))){
                JOptionPane.showMessageDialog(null,"El número de documento ingresado ya existe."
                    ,"Error de documento",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }if(usuarioBL.existeUsuario(this.nombreUsuario.getText())){
                JOptionPane.showMessageDialog(null,"El usuario ingresado ya existe."
                    ,"Error de usuario",JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if(aceptarPatronContrasena()){
                return true;
            }
            
        }else{
           JOptionPane.showMessageDialog(null,"Por favor, complete todos sus datos."
                    ,"Error de datos",JOptionPane.INFORMATION_MESSAGE);
                return false; 
        }
        return false;
        
        
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
        cboCaracter = new javax.swing.JCheckBox();
        registrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos personales del usuario"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombres:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, -1));

        nombrePersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombrePersonaKeyTyped(evt);
            }
        });
        jPanel1.add(nombrePersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 22, 200, -1));

        jLabel2.setText("Apellido paterno:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 55, -1, -1));

        apellidoPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoPaternoKeyTyped(evt);
            }
        });
        jPanel1.add(apellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 55, 200, -1));

        jLabel3.setText("Apellido materno:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, -1, -1));

        apellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoMaternoKeyTyped(evt);
            }
        });
        jPanel1.add(apellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 85, 200, -1));

        jLabel6.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 25, -1, -1));

        jLabel4.setText("Tipo documento:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 115, -1, -1));

        tipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(tipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 115, 200, -1));

        jLabel5.setText("Número de documento:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 145, -1, -1));

        numeroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroDocumentoKeyTyped(evt);
            }
        });
        jPanel1.add(numeroDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 145, 200, -1));

        jLabel11.setText("Ciudad:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 85, -1, -1));

        ciudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 85, 200, -1));
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 115, 200, -1));

        jLabel12.setText("Dirección:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 115, -1, -1));

        jLabel13.setText("Correo electrónico:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 55, -1, -1));
        jPanel1.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 55, 200, -1));
        jPanel1.add(fechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 25, 200, -1));

        panelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 740, 190));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configuración inicial de usuario"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(nombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 25, 200, -1));

        jLabel7.setText("Nombre de usuario:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, -1, -1));

        jLabel8.setText("Contraseña:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 55, -1, -1));

        jLabel9.setText("Repetir contraseña:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 55, -1, -1));

        jLabel10.setText("Rol:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 25, -1, -1));

        rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rol.setSelectedItem(rol);
        jPanel2.add(rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 25, 200, -1));

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
        jPanel2.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 55, 200, -1));

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
        jPanel2.add(contraseñaRepetir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 55, 200, -1));

        cboCaracter.setText("Mostrar contraseña");
        cboCaracter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCaracterMouseClicked(evt);
            }
        });
        jPanel2.add(cboCaracter, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        panelFondo.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 740, 130));

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        panelFondo.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, -1, -1));

        jButton2.setText("Limpiar");
        jButton2.setHideActionText(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelFondo.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 83, -1));

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelFondo.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 87, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("CREACIÓN DE CUENTA NUEVA");
        panelFondo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
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
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

       java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;
    }
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        try {
        
        if (validarDatos()){
            //JOptionPane.showMessageDialog(this, "Todos los datos fueron digitados");
            System.out.println("acaaaa "+ fechaNac.getDate() + " " + fechaNac.getText());
            //if (validarContraseña()){
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
    
/*
Para transformar al reves es 
Date date = r.getDate();
LocalDate localD = date.toLocalDate();
*/
                //java.sql.Date d = convertUtilToSql(this.fechaNac.getDate());
                java.sql.Date date = java.sql.Date.valueOf(this.fechaNac.getDate());
                personaNueva.setFechaNacimiento(date);
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
                

            //}else{
              //  JOptionPane.showMessageDialog(this, "Contraseñas diferentes error");
            //}

        }else{
            System.out.println("cbo "+this.rol.getSelectedItem());
            Date d = new Date(this.fechaNac.getText());
            System.out.println("date "+d);
            //JOptionPane.showMessageDialog(this, "Debe de llenar todos los datos");
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void cboCaracterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCaracterMouseClicked
        if(cboCaracter.isSelected()){
            contraseña.setEchoChar((char)0);
            contraseñaRepetir.setEchoChar((char)0);
        }else{
            contraseña.setEchoChar('*');
            contraseñaRepetir.setEchoChar('*');
        }
    }//GEN-LAST:event_cboCaracterMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JCheckBox cboCaracter;
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
    private javax.swing.JLabel jLabel14;
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
