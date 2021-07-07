package Vista;

import AccesoDatos.EjecutaAlgoritmo;
import AccesoDatos.usuarioDA;
import Algoritmo.Aeropuerto;
import Algoritmo.DataProcessing;
import Algoritmo.Paquete;
import Algoritmo.TabuSearch;
import Controlador.AdministrarClienteBL;
import Controlador.AdministrarPedidoBL;
import Controlador.PaqueteBL;
import Controlador.VueloBL;
import Controlador.aeropuertoBL;
import Controlador.generalBL;
import Controlador.usuarioBL;
import Modelo.Encriptar;
import Modelo.Hashing;
import static Modelo.Hashing.MD5Hash;
import Modelo.Vuelo;
import Modelo.aeropuerto;
import Modelo.cliente;
import Modelo.continente;
import Modelo.usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import javafx.scene.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Login extends javax.swing.JFrame implements ActionListener {
    private static int esPrimero=1;
    private usuarioBL usuarioBL ;
    private usuario usuarioLogin;
    private Encriptar td ;
    private Timer timer  ;
    private int minutoMundial=0  ;
    private int horaMundial =0 ;
    private int procesado=1;
    private int numProcesados=0;
    private Calendar calendar=Calendar.getInstance();
     private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
     private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
     private int tiempoAlgoMM=0;
     private int tiempoDelayAlgoritmo=60*2;
     private DataProcessing dp = new DataProcessing();
     private ArrayList<String> Archivos = new ArrayList<>();
     private TabuSearch tabu  = new TabuSearch();
     private int esInicio = 1;
     private int esInicioPack=1;
     private int esInicioPack2=1;
     private PaqueteBL controladorPaquete= new PaqueteBL(); 
     ArrayList<Algoritmo.Paquete>listaPackNew;
     ArrayList<Algoritmo.Paquete>listaPackProcesada=new ArrayList<>();
     ArrayList<Algoritmo.Paquete>listaPackGlobal=new ArrayList<>();
    

    public Login() {
       
        initComponents();
        inicializar();
        usuarioBL = new usuarioBL();
        userName.setForeground(new Color(133,133,133));
        password.setForeground(new Color(133,133,133));
        this.setTitle("Iniciar sesión");
        password.setEchoChar((char)0);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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
        logoRedEx = new javax.swing.JLabel();
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
        jPanel3.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 203, 150, 20));

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

        frontLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frontLoginMouseClicked(evt);
            }
        });
        jPanel2.add(frontLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, 50));

        logoRedEx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/RedEx.png"))); // NOI18N
        jPanel2.add(logoRedEx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 400, 50));

        lblRecoverPassword.setText("¿Ha olvidado su contraseña?");
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
        jPanel3.add(lblRecoverPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 140, 20));

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
                    boolean respuesta = usuarioBL.iniciarSesion(usuarioLogin.getId());
                    if (respuesta==true){
                        JOptionPane.showMessageDialog(null, "Bienvenido");
                     //   this.dispose();
                     this.hide();
                        System.out.println("USUARIO "+ usuarioLogin.getRol());

                        if (usuarioLogin.getRol().equals("gerente")){
                            frmMenuProvisional menuGerente = new frmMenuProvisional(usuarioLogin);
                            menuGerente.setVisible(true);
                        }
                        if (usuarioLogin.getRol().equals("secretario")){
                             /* Crear nuevo hilo*/
                            this.timer = new Timer(8, (ActionListener) this);
                            timer.start();
                            frmMenuSecretary menuSecre= new frmMenuSecretary(usuarioLogin);
                            menuSecre.setVisible(true);
                            
                        }
                        if (usuarioLogin.getRol().equals("administrador")){
                            frmMenuAdmin menuAdmin = new frmMenuAdmin(usuarioLogin);
                            menuAdmin.setVisible(true);
                        }

                       
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "ERROR: El usuario ya se encuentra logueado en otra instancia.");
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
                        notificadorEmail.enviarConGMail("redex.admi@gmail.com", asunto, cuerpo);
                        usuarioLogin.setBaneado(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Datos ingresados incorrectos\n"
                                + "Le quedan "+ ((5 - usuarioLogin.getNumeroIntentos())<=0?0:(5 - usuarioLogin.getNumeroIntentos()))+" intento(s) restante(s).");
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrectos");           
        }
        
    }//GEN-LAST:event_loginActionPerformed


    public void actionPerformed(ActionEvent e) {
        
        try{
            if (    usuarioLogin.getRol().equals("secretario") ){
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (this.minutoMundial<59){
                    this.minutoMundial++;
                }else{

                    this.minutoMundial=0;
                    if (this.horaMundial <23)
                        this.horaMundial++;
                    else {
                        this.minutoMundial=0;
                        this.horaMundial=0; 
                    }    
                }


                if(this.esInicio == 1){
                    lecturaData();
                    this.esInicio = 0;
                }
                ArrayList<Modelo.paquete>listaPack=controladorPaquete.obtenerPaquetesCreados(usuarioLogin.getId());
                listaPackNew=new ArrayList<>();

                int size=listaPack.size();
                for(int i=0;i<size;i++){
                    int origenId=listaPack.get(i).getAeropuertoOrigenId();
                    int destinoId=listaPack.get(i).getAeropuertoDestinoId();
                    int origenHour=listaPack.get(i).getFechaSalida().getHours();
                    int origenMin=listaPack.get(i).getFechaSalida().getMinutes();
                    int origenDay=listaPack.get(i).getFechaSalida().getDay();
                    int origenMonth=listaPack.get(i).getFechaSalida().getMonth();
                    int origenYear=listaPack.get(i).getFechaSalida().getYear();
                    int id=listaPack.get(i).getId();
                    Algoritmo.Paquete pack=new Algoritmo.Paquete(origenHour,origenMin,origenId,destinoId,origenDay,origenMonth,origenYear);
                    pack.setIdentificator(id);
                    listaPackNew.add(pack);
                }

                this.listPack.clear();
                int numElem=0;
                for(int i=0;i<listaPackGlobal.size();i++){
                    if(listaPackGlobal.get(i).getEnviado()==1)
                        listaPackNew.remove(numElem);
                    else 
                        numElem++;
                }

                if (this.minutoMundial== 0  && this.horaMundial%2==0 ){                              
                    if(listaPackNew.size()>0){

                            if(esInicioPack==1){
                                esInicioPack=0;
                                EjecutaAlgoritmo t = new EjecutaAlgoritmo(tabu,this.listaPackNew,this.tiempoAlgoMM,horaMundial,minutoMundial);
                                t.start();
                                t.join();
                                this.tiempoAlgoMM = t.getTiempoAlgoMM();

                                System.out.println("ES LA HORA ->>>>>>");
                                listaPackProcesada=t.getListPackAlgo();


                                listaPackNew.clear();

                            }
                            else if(esInicioPack2==1){
                                esInicioPack2=0;
                                EjecutaAlgoritmo t = new EjecutaAlgoritmo(tabu,this.listaPackNew,this.tiempoAlgoMM,horaMundial,minutoMundial);
                                t.start();
                                t.join();
                                this.tiempoAlgoMM = t.getTiempoAlgoMM();

                                System.out.println("ES LA HORA ->>>>>>");
                                listaPackProcesada=t.getListPackAlgo();

                                listaPackNew.clear();
                            }
                            else if(listaPackProcesada.size()==0 ){
                                EjecutaAlgoritmo t = new EjecutaAlgoritmo(tabu,this.listaPackNew,this.tiempoAlgoMM,horaMundial,minutoMundial);
                                t.start();
                                t.join();
                                this.tiempoAlgoMM = t.getTiempoAlgoMM();

                                System.out.println("ES LA HORA ->>>>>>");
                                listaPackProcesada=t.getListPackAlgo();


                                listaPackNew.clear();

                            }

                        }



                }

                try{

                    cambiaEstadoMov();

                }
                catch(Exception exp){
                    System.out.println("Error de detección de correo "+exp.getMessage());
                } 
            }
            
       }catch(Exception ex){
           System.out.println("ERROR actionPerformed Login " + ex.getMessage() );
           ex.printStackTrace();
       }   

        
    }
    public void cambiaEstadoMov(){
        
        //for(int i = 0; i < 1;i++){
        for(int i = 0; i < this.listaPackProcesada.size();i++){
            Algoritmo.Paquete pack = this.listaPackProcesada.get(i);
            String ruta = pack.getRuta();
            // verifica si tiene camino por recorrer
            if (!ruta.equals("")){
                String[] ids = ruta.split("-");
                int idVuelo = Integer.parseInt(ids[0]);
                Algoritmo.Vuelo v=this.tabu.getListFlight().get(idVuelo-1);
                
                if(horaMundial*60+minutoMundial==v.getDestinyHour()*60+v.getDestinyMin()){
                    AdministrarPedidoBL controladorPedido=new AdministrarPedidoBL();
                    System.out.println("Vuelo "+ids[0]+" desde "+v.getOriginAirport()+ " a "+v.getDestinyAirport());
                    String correos=controladorPedido.obtenerCorreosClientes(pack.getIdentificator());
                    String [] correoMatrix=correos.split(",");
                    String emisor=correoMatrix[0];
                    String receptor = correoMatrix[1];
                    String ceroHora="";
                    String ceroMin="";
                    if(horaMundial<10) ceroHora+="0";
                    if (minutoMundial<10) ceroMin+="0";
                    MailWorkerTest mwt=new MailWorkerTest("redex.admi@gmail.com","grupofredex");
                    String asunto="Localización de paquete";
                    if(ids.length==1){
                        String cuerpoEmisor="Estimado usuario, "
                                + "lo saludamos para informarle que el paquete de numero de tracking "
                                + pack.getIdentificator()+ " se encuentra en el país de "+this.tabu.getListAirport().get(v.getDestinyAirport()-1).getCountry()
                                +" en su paradero final a las "+ceroHora+v.getDestinyHour()+":"+ceroMin+v.getDestinyMin()+". Muchas gracias por su atención.";
                        
                        String cuerpoReceptor="Estimado usuario, "
                                + "lo saludamos para informarle que el paquete de numero de tracking "
                                + pack.getIdentificator() + " se encuentra en el país de "+this.tabu.getListAirport().get(v.getDestinyAirport()-1).getCountry()
                                +" en su paradero final a las "+ceroHora+v.getDestinyHour()+":"+ceroMin+v.getDestinyMin()+". Por favor, acercarse a recoger su encargo. Muchas gracias por su atención.";
                        mwt.enviarConGMail(emisor, asunto, cuerpoEmisor);
                        mwt.enviarConGMail(receptor,asunto,cuerpoReceptor);
                        mwt.enviarConGMail("redex.admi@gmail.com", asunto, cuerpoReceptor);
                        numProcesados++;
                        if(listaPackProcesada.size()==numProcesados){
                           listaPackProcesada.clear();    
                           numProcesados=0;
                        }
                        pack.setEnviado(1);
                        listaPackGlobal.add(pack);
                        return;
                    }else{
                        String cuerpo="Estimado usuario, "
                                    + "lo saludamos para informarle que el paquete de numero de tracking "
                                + pack.getIdentificator() + " se encuentra en el país de "+this.tabu.getListAirport().get(v.getDestinyAirport()-1).getCountry()
                                    +" a las "+ceroHora+v.getDestinyHour()+":"+ceroMin+v.getDestinyMin()+". Muchas gracias por su atención.";
                        mwt.enviarConGMail(emisor, asunto, cuerpo);
                        mwt.enviarConGMail(receptor,asunto,cuerpo);
                        mwt.enviarConGMail("redex.admi@gmail.com", asunto, cuerpo);
                
                    }
                                    
                  
                    if (ids.length == 1){// es su ultimo paradero
                        this.listaPackProcesada.get(i).setEsFinal(1);
            
                        this.listaPackProcesada.get(i).setRuta(""); // su ruta se encuestra finalizada

                    }else{ // vuelos escalados
                        // se quita el paso dado
                        ruta = ruta.substring(ruta.indexOf("-", 0)+1,ruta.length());
                        // se brinda la nueva ruta
                        this.listaPackProcesada.get(i).setRuta(ruta);
                        
                    }
                }
            }
        }
                   
    }
     public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                Archivos.add(fileEntry.getName());
                
            }
        }
    }
    void lecturaData(){
        try{
            aeropuertoBL controladorAeropuerto= new aeropuertoBL();
            VueloBL controladorVuelo=new VueloBL();
            generalBL general = new generalBL();
            usuarioBL controladorCliente=new usuarioBL();
            ArrayList<continente> continentes = new ArrayList<continente>();
            ArrayList<aeropuerto>listaAeropuertos=new ArrayList<aeropuerto>();
            ArrayList<Modelo.Vuelo>listaVuelos=new ArrayList<Modelo.Vuelo>();
            
            final File folder = new File("resources\\pack_enviados");
                listFilesForFolder(folder);
                
                
            listaAeropuertos=controladorAeropuerto.listaAeropuertos();
            listaVuelos=controladorVuelo.listaVuelos();
            
            ArrayList<Aeropuerto>listaAeropuertosNew = new ArrayList<Aeropuerto>();
            
            ArrayList<Algoritmo.Vuelo>listaVuelosNew=new ArrayList<Algoritmo.Vuelo>();
            
            continentes = general.obtenerContinentes();
            for (aeropuerto a : listaAeropuertos){
                Aeropuerto newAero = new Aeropuerto();

                newAero.setIdentificator(a.getId());
                newAero.setIcaoCode(a.getCodigo());
                newAero.setCityId(a.getCiudad());
                int idCont = buscarContinente(a.getContinente(),continentes);
                newAero.setContinent(idCont);
                newAero.setCountry(a.getPais());
                newAero.setCapMax(a.getCapMax());

               newAero.setCapActual(a.getCapActual());
               newAero.setCoordX(a.getCoordX());
               newAero.setCoordY(a.getCoordY());
               newAero.setColor("verde");

               listaAeropuertosNew.add(newAero);

            }
            this.dp.setListAirport(listaAeropuertosNew);
            
            for(Modelo.Vuelo v:listaVuelos){
                int origenAero=v.getIdAeropuertoOrigen();
                int destinoAero=v.getIdAeropuertoDestino();
                int origenHour=v.getFechaSalida().getHours();
                int origenMin=v.getFechaSalida().getMinutes();
                int destinoHour=v.getFechaLlegada().getHours();
                int destinoMin=v.getFechaLlegada().getMinutes();
                Algoritmo.Vuelo newVuelo=new Algoritmo.Vuelo(origenAero, origenHour, origenMin, destinoAero, destinoHour, destinoMin);
                listaVuelosNew.add(newVuelo);
            }
            
            this.tabu.setListAirport(listaAeropuertosNew);
            this.tabu.setListFlight(listaVuelosNew);
            this.tabu.generateFlightMatrix();

     

            for (String a : this.Archivos){
                //dp.processPackNew("resources\\pack_enviados_generados\\" + a);
                dp.processPackNew("resources\\pack_enviados\\" + a);
            }
            this.tabu.setInputProcess(this.dp);
            this.listPack = this.dp.getPackList();
            if (this.listPack.size()>0)//se coloca la fecha del primer pack como fecha del simulador
                this.calendar.set(this.listPack.get(0).getOriginYear(),this.listPack.get(0).getOriginMonth() - 1,this.listPack.get(0).getOriginDay());

        }catch(Exception ex){
           System.out.println("ERROR lecturaData " + ex.getMessage() );
        }
        
    }
    private int buscarContinente(String c,ArrayList<continente>continentes){
        for (continente cont :continentes){
            if (cont.getNombre().equals(c)){
                return cont.getId();
            }
        }
        return -1;
    }
    
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
            
            //System.out.println("ANTES DE ENCRIPTAR: "+hashPwA);
            //System.out.println("DESPUES DE ENCRIPTAR: "+hashPwB);
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
            notificadorEmail.enviarConGMail("redex.admi@gmail.com", asunto, cuerpo);
            JOptionPane.showMessageDialog(null, "El correo fue enviado satisfactoriamente");
            return ;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        
        
    }//GEN-LAST:event_lblRecoverPasswordMouseClicked

    private void frontLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frontLoginMouseClicked
        // TODO add your handling code here:
        usuarioBL.cerrarSesionAll();
    }//GEN-LAST:event_frontLoginMouseClicked
         
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
    private javax.swing.JLabel logoRedEx;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordImg;
    private javax.swing.JLabel userImg;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
