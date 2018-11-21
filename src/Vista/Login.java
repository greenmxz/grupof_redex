package Vista;

import AccesoDatos.usuarioDA;
import Controlador.usuarioBL;
import Modelo.Encriptar;
import Modelo.Hashing;
import static Modelo.Hashing.MD5Hash;
import Modelo.usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import javafx.scene.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Login extends javax.swing.JFrame {
    private usuarioBL usuarioBL ;
    private usuario usuarioLogin;
    private Encriptar td ;
    public Login() {
       
        initComponents();
        inicializar();
        usuarioBL = new usuarioBL();
        userName.setForeground(new Color(133,133,133));
        password.setForeground(new Color(133,133,133));
        password.setEchoChar((char)0);
    }
    private void inicializar(){
        ImageIcon imgUsername = new ImageIcon(getClass().getResource("/Resource/username.png"));
        ImageIcon iconUsername = new ImageIcon (imgUsername.getImage().getScaledInstance(userImg.getWidth(), userImg.getHeight(), Image.SCALE_DEFAULT));
        userImg.setIcon(iconUsername);
        
        ImageIcon imgPassword = new ImageIcon(getClass().getResource("/Resource/password.png"));
        ImageIcon iconPassword = new ImageIcon (imgPassword.getImage().getScaledInstance(passwordImg.getWidth(), passwordImg.getHeight(), Image.SCALE_DEFAULT));
        passwordImg.setIcon(iconPassword);
        
        ImageIcon imgFront = new ImageIcon(getClass().getResource("/Resource/frontlogin.png"));
        ImageIcon iconFront= new ImageIcon (imgFront.getImage().getScaledInstance(frontLogin.getWidth(), frontLogin.getHeight(), Image.SCALE_DEFAULT));
        frontLogin.setIcon(iconFront);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        login = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        userName = new javax.swing.JTextField();
        userImg = new javax.swing.JLabel();
        passwordImg = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        frontLogin = new javax.swing.JLabel();
        lblRecoverPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 17, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login.setText("INICIAR SESIÓN");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jPanel3.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 200, 150, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userName.setText("Nombre de usuario");
        userName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userNameFocusLost(evt);
            }
        });
        userName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userNameKeyTyped(evt);
            }
        });
        jPanel1.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 200, 20));

        userImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(userImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 5, 30, 30));

        passwordImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(passwordImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, 30, 30));

        password.setText("Contraseña");
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 200, 20));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 400, 90));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(frontLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 0, 50, 50));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 400, -1));

        lblRecoverPassword.setText("Olvidé mi contraseña");
        lblRecoverPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRecoverPasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRecoverPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRecoverPasswordMouseExited(evt);
            }
        });
        jPanel3.add(lblRecoverPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 164, 100, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void setColor(JPanel panel){
        panel.setBackground(new Color(30,67,112));
        //136,161,192
    }
    void resetColor(JPanel panel){
        panel.setBackground(new Color(173,192,206));
    }
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        usuarioLogin = usuarioBL.obtenerUsuario(this.userName.getText(),this.password.getText());
        if (usuarioLogin!=null){
            if (usuarioLogin.isBaneado()){
                JOptionPane.showMessageDialog(null,  "Ha excedido el número de intentos permitidos,\n "
                        + "Debe de esperar "+usuarioLogin.getTiempoRestanteBaneado()+ " segundos.");
            }else{
                if (usuarioLogin.isEncontrado()){
                    JOptionPane.showMessageDialog(null, "Datos ingresados correctos");
                    this.dispose();
                    System.out.println("USUARIO "+ usuarioLogin.getRol());
                     
                    if (usuarioLogin.getRol().equals("gerente")){
                        frmMenuProvisional menuGerente = new frmMenuProvisional(usuarioLogin);
                      
                        menuGerente.setVisible(true);
                    }
                    if (usuarioLogin.getRol().equals("secretario")){
                        frmMenuSecretary menuSecre= new frmMenuSecretary(usuarioLogin);

                        menuSecre.setVisible(true);
                    }
                    if (usuarioLogin.getRol().equals("administrador")){
                        frmMenuAdmin menuAdmin = new frmMenuAdmin(usuarioLogin);

                        menuAdmin.setVisible(true);
                    }
                }
                else {
                    if(usuarioLogin.getNumeroIntentos()>=5){
                        MailWorkerTest notificadorEmail=new MailWorkerTest(usuarioLogin.getPersona().getCorreo(),"12345678");
                        String asunto="Sistema RedEx - Exceso de intentos de acceso";
                        String cuerpo="Estimado usuario, "
                                + "lo saludamos para informarle que se ha registrado una cantidad de número de intentos para "
                                + "ingresar a su cuenta. Por lo tanto, por motivos de seguridad, su cuenta será suspendida por "
                                + "los próximos 5 minutos para volver a ser funcional. Si usted está relacionado con esta actividad, "
                                + "le recomendamos pasar por el proceso de Recuperación de Contraseña. Si usted no realizó esta actividad,"
                                + " sugerimos cambiar su contraseña. Muchas gracias por su atención.";
                        notificadorEmail.enviarConGMail("juanfsts@gmail.com", asunto, cuerpo);
                        usuarioLogin.setBaneado(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Datos ingresados incorrectos\n"
                                + "Le quedan "+ ((5 - usuarioLogin.getNumeroIntentos())<=0?0:(5 - usuarioLogin.getNumeroIntentos()))+" intento(s) restante(s).");
                    }
                }
            }
            userName.setText("Usuario");
        }else{
            userName.setText("Usuario");
            JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrectos");
            
        }
        
    }//GEN-LAST:event_loginActionPerformed

    private void userNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userNameKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_TAB)
            password.requestFocus();
    }//GEN-LAST:event_userNameKeyTyped

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        password.setForeground(new Color(0,0,0));
        if(password.getText().equals("Contraseña")){
            password.setText("");
            password.setEchoChar('*');
        }
    }//GEN-LAST:event_passwordFocusGained

    private void userNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusGained
        userName.setForeground(new Color(0,0,0));
        if(userName.getText().equals("Nombre de usuario"))
            userName.setText("");
    }//GEN-LAST:event_userNameFocusGained

    private void userNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userNameFocusLost
        if(userName.getText().equals("")){
            userName.setText("Nombre de usuario");
            userName.setForeground(new Color(133,133,133));
        }
    }//GEN-LAST:event_userNameFocusLost

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        if(password.getText().equals("")){
            password.setEchoChar((char)0);
            password.setText("Contraseña");
            password.setForeground(new Color(133,133,133));
        }
    }//GEN-LAST:event_passwordFocusLost

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            login.doClick();
    }//GEN-LAST:event_passwordKeyPressed

    private void lblRecoverPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRecoverPasswordMouseEntered
        Font font = lblRecoverPassword.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lblRecoverPassword.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_lblRecoverPasswordMouseEntered

    private void lblRecoverPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRecoverPasswordMouseExited
        Font font = lblRecoverPassword.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, -1);
        lblRecoverPassword.setFont(font.deriveFont(attributes));
    }//GEN-LAST:event_lblRecoverPasswordMouseExited

    private void lblRecoverPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRecoverPasswordMouseClicked
        //usuarioLogin = usuarioBL.obtenerUsuario(this.userName.getText(),this.password.getText());
        try {
            usuario userRecuperar = usuarioBL.obtenerUsuarioRecuperar(this.userName.getText());
            if(userRecuperar==null){
                JOptionPane.showMessageDialog(null, "Por favor, ingrese su usuario.");
                return;
            }//Xuq6t7Fr/Dg=
            td= new Encriptar();
           String hashPwA = td.encrypt("123");
            String hashPwB = td.decrypt(hashPwA);
            
            System.out.println("ANTES DE ENCRIPTAR: "+hashPwA);
            System.out.println("DESPUES DE ENCRIPTAR: "+hashPwB);
            //String hashPw = MD5Hash(userRecuperar.getPassword());
            String hashPw = td.decrypt(userRecuperar.getPassword());
            MailWorkerTest notificadorEmail=new MailWorkerTest(userRecuperar.getPersona().getCorreo(),"sdfdf");
            String asunto="Sistema RedEx - Recuperación de contraseña";        
            String cuerpo="Estimado usuario, "
                    + "antes de revisar su contraseña en este correo, le recomendamos estar en una zona privada "
                    + "y ,apenas identifique su contraseña en el mensaje, lo elimine inmediatamente por motivos "
                    + "de seguridad. Habiéndole informado, procedemos con la recuperación de su contraseña de su cuenta."
                    + "Su contraseña es" + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n "
                    + hashPw;
            notificadorEmail.enviarConGMail("juanfsts@gmail.com", asunto, cuerpo);
            JOptionPane.showMessageDialog(null, "El correo fue enviado satisfactoriamente");
            return ;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        
        
    }//GEN-LAST:event_lblRecoverPasswordMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel frontLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblRecoverPassword;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordImg;
    private javax.swing.JLabel userImg;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
