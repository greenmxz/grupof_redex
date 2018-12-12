package Vista;

import Controlador.AdministrarClienteBL;
import Controlador.PaqueteBL;
import Controlador.VueloBL;
import Controlador.aeropuertoBL;
import Controlador.usuarioBL;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Modelo.Archivo;
import Modelo.Vuelo;
import Modelo.aeropuerto;
import Modelo.cliente;
import Modelo.paquete;
import Modelo.persona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmCargaDatos extends javax.swing.JPanel {

    private ArrayList<Archivo> listFile = new ArrayList<Archivo>();
    private ArrayList<String> listAerop = new ArrayList<String>();
    private ArrayList<Integer> listHusos = new ArrayList<Integer>();
    private javax.swing.JFrame x;
    private ArrayList<ArrayList<String>> usuarios = new ArrayList<ArrayList<String>>();
    
    public frmCargaDatos(javax.swing.JFrame x) {
        initComponents();
        cargarHusos();
    }
    
    public void cargarHusos(){
        try{
           //BufferedReader reader = new BufferedReader(new FileReader("resources\\husos.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("resources/husos.txt"));
            String line;
            String continent = "";
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("\\s+");
                listAerop.add(arr[0]);
                listHusos.add(Integer.parseInt(arr[1]));
            } 
//            for(int i=0; i<listAerop.size(); i++){
//                System.out.println("Aerop: " + listAerop.get(i) + " GMT" + 
//                        String.valueOf(-listHusos.get(i)));
//            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRORR!");
        }
    }
    
    public void actualizarTabla(){
        DefaultTableModel modelTable = (DefaultTableModel) tblArchivos.getModel();
        while(modelTable.getRowCount() > 0){
            modelTable.removeRow(0);
        }
        for(int i=0; i<listFile.size(); i++){
            Object[] obj = new Object[3];
            obj[0] = listFile.get(i).getNombre();
            obj[1] = listFile.get(i).getUbicacion();
            obj[2] = listFile.get(i).getTipo();
            modelTable.addRow(obj);
        }
        if((modelTable.getRowCount() == 0))
            btnEliminar.setEnabled(false);
        else
            btnEliminar.setEnabled(true);
    }
    
    public int[] hallarEspacios(String arr){
        int[] espacios = new int[10];
        int contador = 0, inicio = 0;
        if(arr.length()==0){
            int[] voider = {0,0,0,0,0,0,0,0,0,0};
            return voider;
        }
        /* Si el primero es alfanumérico, poner espacios[0] = 0 */
        if(arr.charAt(0) != ' ' && arr.charAt(0) != '\t'){
            espacios[0] = 0;
            inicio = 1;
        }else{
            inicio = 0;
            if(arr.charAt(0) == '\t')
                contador = 4;
            else contador = 1;
        }
        for(int i=1; i<arr.length(); i++){
            if(arr.charAt(i) != ' ' && contador > 0){
                espacios[inicio] = contador;
                contador = 0;
                inicio++;
            }
            else if(arr.charAt(i) == ' ') contador++;
            else if(arr.charAt(i) == '\t') contador += 4;
        }
        return espacios;
    }
    
    public ArrayList<aeropuerto> procesarAeropuertos(String ruta){
        ArrayList<aeropuerto> aux = new ArrayList<aeropuerto>();
        int linea = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            String continent = "";
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("\\s+");
                if(arr.length == 1 || arr[1].equals("OACI") || arr[1].equals("ICAO")) continue;
                int[] espacios = hallarEspacios(line);
                if(arr[0].equals("")){
                    continent = "";
                    for(int i=1;(i==1 ||(espacios[i] == 1) || (espacios[i-1] == 1)); i++){
                        if(i > arr.length) break;
                        if(i>1)
                            continent += " " + arr[i];
                        else continent += arr[i];
                    }
                    continent = continent.replace(".", "");
//                    System.out.println(" Continente: " + continent);
                }
                if(arr.length >= 5){
                    // Defining the official name of the airport
                    String nameAir = "";
                    int count=2, originCount = count;
                    for(int i=count; (i==originCount || (espacios[i] == 1)); i++){
                        if(i>originCount)
                            nameAir += " " + arr[i];
                        else nameAir += arr[i];
                        count++;
                    }
//                    System.out.print(" Ciudad: " + nameAir);
                    // Defining the country of the airport
                    String country = "";
                    originCount = count;
                    for(int i=count; (i==originCount || (espacios[i] == 1)); i++){
                        if(i>originCount)
                            country += " " + arr[i];
                        else country += arr[i];
                        count++;
                    }
//                    System.out.println(" País: " + country);
                    aeropuerto airpt = new aeropuerto(Integer.parseInt(arr[0]), nameAir, arr[1],
                            continent, country, nameAir);
                    //airpt.print();
                    aux.add(airpt);
                }
                linea++;
            }
            System.out.println("Airports' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            if(linea == 1)
                JOptionPane.showMessageDialog(null,
                    "La línea 1 contiene un error de formato. No se podrá continuar con"
                            + "la carga hasta que el error se corrija",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            int reply = JOptionPane.showConfirmDialog(null,
                    "La línea " + String.valueOf(linea) + " contiene un error de formato.\n"
                            + "¿Desea guardar los aeropuertos ya leídos?",
                    "Mensaje de error", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                return aux;
            }
            else {
               JOptionPane.showMessageDialog(null, "Registro cancelado");
               return new ArrayList<aeropuerto>();
            }
        }
        return aux;
    }
    public ArrayList<persona> procesarClientes(String ruta){
        ArrayList<persona> aux = new ArrayList<persona>();
        int linea = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            String continent = "";
            this.usuarios = new ArrayList<ArrayList<String>>();
            while( (line = reader.readLine()) != null){
                String[] arr = line.split(",");
                System.out.println(arr);
                persona p = new persona();
                //nombre,apellido_paterno,apellido_materno,numero_documento_identidad,direccion,correo,telefono,fecha_nacimiento,id_ciudad,id_tipo_documento,
                p.setNombre(arr[0]);
                p.setApellidoPaterno(arr[1]);
                p.setApellidoMaterno(arr[2]);
                p.setNumeroDocumentoIdentidad(Integer.parseInt(arr[3]));
                p.setDireccion(arr[4]);
                p.setCorreo(arr[5]);
                p.setTelefono(arr[6]);
                
                Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(arr[7]);
                
                p.setFechaNacimiento(date1);
                p.setCiudad((arr[8]));
                p.setTipoDocumento(arr[9]);
                
                aux.add(p);
                linea++;
            }
            System.out.println("Clients' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            if(linea == 1)
                JOptionPane.showMessageDialog(null,
                    "La línea 1 contiene un error de formato. No se podrá continuar con"
                            + "la carga hasta que el error se corrija",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            int reply = JOptionPane.showConfirmDialog(null,
                    "La línea " + String.valueOf(linea) + " contiene un error de formato.\n"
                            + "¿Desea guardar los clientes ya leídos?",
                    "Mensaje de error", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                return aux;
            }
            else {
               JOptionPane.showMessageDialog(null, "Registro cancelado");
               return new ArrayList<persona>();
            }
        }
        return aux;
    }
    
    public ArrayList<persona> procesarEmpleados(String ruta){
        ArrayList<persona> aux = new ArrayList<persona>();
        int linea = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            String continent = "";
            this.usuarios = new ArrayList<ArrayList<String>>();
            while( (line = reader.readLine()) != null){
                String[] arr = line.split(",");
                System.out.println(arr);
                persona p = new persona();
                
                if(Integer.parseInt(arr[9]) < 1)
                    throw new Exception();
                
//                arr[1].replace("O'", "O");
//                arr[2].replace("O'", "O");
                //nombre,apellido_paterno,apellido_materno,numero_documento_identidad,direccion,correo,telefono,fecha_nacimiento,id_ciudad,id_tipo_documento,
                p.setNombre(arr[0]);
                p.setApellidoPaterno(arr[1]);
                p.setApellidoMaterno(arr[2]);
                p.setNumeroDocumentoIdentidad(Integer.parseInt(arr[3]));
                p.setDireccion(arr[4]);
                p.setCorreo(arr[5]);
                p.setTelefono(arr[6]);
                
                Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(arr[7]);
                
                p.setFechaNacimiento(date1);
                p.setCiudad((arr[8]));
                p.setTipoDocumento(arr[9]);
                
                ArrayList<String> auxx = new ArrayList<String>();
                auxx.add(arr[10]);
                auxx.add(arr[11]);
                auxx.add(arr[12]);
                
                aux.add(p);
                this.usuarios.add(auxx);
                linea++;
            }
            System.out.println("Employee's reading process successful!");
        }catch(Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
            if(linea == 1)
                JOptionPane.showMessageDialog(null,
                    "La línea 1 contiene un error de formato. No se podrá continuar con"
                            + "la carga hasta que el error se corrija",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            else{
                int reply = JOptionPane.showConfirmDialog(null,
                        "La línea " + String.valueOf(linea) + " contiene un error de formato.\n"
                                + "¿Desea guardar los empleados ya leídos?",
                        "Mensaje de error", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    return aux;
                }
                else {
                   JOptionPane.showMessageDialog(null, "Registro cancelado");
                   return new ArrayList<persona>();
                }
            }
        }
        return aux;
    }
    
    public ArrayList<Vuelo> procesarVuelos(String ruta){
        ArrayList<Vuelo> aux = new ArrayList<Vuelo>();
        int linea = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                int indexO = listAerop.indexOf(arr[0]);
                int indexD = listAerop.indexOf(arr[1]);
                if(arr.length == 4){
//                    Vuelo plannedFlg = new Vuelo("1",
//                            Date.from(LocalTime.of(Integer.parseInt(arr[2].split(":")[0]),
//                                    Integer.parseInt(arr[2].split(":")[1])).atDate(LocalDate.of(2018, 11, 2)).
//                                            atZone(ZoneId.systemDefault()).toInstant()),
//                            Date.from(LocalTime.of(Integer.parseInt(arr[3].split(":")[0]),
//                                    Integer.parseInt(arr[3].split(":")[1])).atDate(LocalDate.of(2018, 11, 2)).
//                                        atZone(ZoneId.systemDefault()).toInstant()), arr[0], arr[1]);
                    if((Integer.parseInt(arr[2].split(":")[0]) < 0) &&  (Integer.parseInt(arr[2].split(":")[0]) > 23) &&
                            (Integer.parseInt(arr[2].split(":")[1]) < 0) &&  (Integer.parseInt(arr[2].split(":")[1]) > 59) &&
                            (Integer.parseInt(arr[3].split(":")[0]) < 0) &&  (Integer.parseInt(arr[3].split(":")[0]) > 23) &&
                            (Integer.parseInt(arr[3].split(":")[1]) < 0) &&  (Integer.parseInt(arr[3].split(":")[1]) > 59)
                            )
                        throw new Exception();
                    int horaO = Integer.parseInt(arr[2].split(":")[0]);
                    int horaD = Integer.parseInt(arr[3].split(":")[0]);
                    int aumentoO = listHusos.get(indexO);
                    int aumentoD = listHusos.get(indexD);
                    if((horaO + aumentoO) >= 24)
                        horaO += aumentoO - 24;
                    else if(horaO + aumentoO < 0)
                        horaO += aumentoO + 24;
                    else
                        horaO += aumentoO;
                    if((horaD + aumentoD) >= 24)
                        horaD += aumentoD - 24;
                    else if(horaD + aumentoD < 0)
                        horaD += aumentoD + 24;
                    else
                        horaD += aumentoD;
                    Vuelo plannedFlg = new Vuelo("1",
                            Date.from(LocalTime.of(horaO, Integer.parseInt(arr[2].split(":")[1]))
                                    .atDate(LocalDate.of(2018, 11, 2))
                                    .atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(LocalTime.of(horaD, Integer.parseInt(arr[3].split(":")[1]))
                                    .atDate(LocalDate.of(2018, 11, 2))
                                    .atZone(ZoneId.systemDefault()).toInstant()), arr[0], arr[1]);

                    //plannedFlg.print();
                    aux.add(plannedFlg);
                }
                else throw new Exception();
                linea++;
            }
            System.out.println("Flights' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            if(linea == 1)
                JOptionPane.showMessageDialog(null,
                    "La línea 1 contiene un error de formato. No se podrá continuar con"
                            + "la carga hasta que el error se corrija",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            else{
                int reply = JOptionPane.showConfirmDialog(null,
                        "La línea " + String.valueOf(linea) + " contiene un error de formato.\n"
                                + "¿Desea guardar los vuelos ya leídos?",
                        "Mensaje de error", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    return aux;
                }
                else {
                   JOptionPane.showMessageDialog(null, "Registro cancelado");
                   return new ArrayList<Vuelo>();
                }
            }
        }
        return aux;
    }
    
    public ArrayList<paquete> procesarPaquetes(String ruta){
        ArrayList<paquete> aux = new ArrayList<paquete>();
        String backslash = "/";
        String identificator = ruta.split(Pattern.quote(backslash))[ruta.split(Pattern.quote(backslash)).length - 1]
                .split("_")[2].substring(0, 4);
        int linea = 1;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
//                if(arr[1].equals("20180418")) // Se debe comentar para el final
//                    break;
                if(arr.length == 4){
//                    paquete plannedPack = new paquete(arr[0],
//                            Date.from(LocalTime.of(Integer.parseInt(arr[2].split(":")[0]),
//                                   Integer.parseInt(arr[2].split(":")[1])).atDate(LocalDate.of(
//                                        Integer.parseInt(arr[1].substring(0, 4)),
//                                        Integer.parseInt(arr[1].substring(4, 6)),
//                                        Integer.parseInt(arr[1].substring(6, 8)))).
//                                        atZone(ZoneId.systemDefault()).toInstant()), identificator, arr[3]);
                    if((Integer.parseInt(arr[2].split(":")[0]) < 0) &&  (Integer.parseInt(arr[2].split(":")[0]) > 23) &&
                            (Integer.parseInt(arr[2].split(":")[1]) < 0) &&  (Integer.parseInt(arr[2].split(":")[1]) > 59) &&
                            (Integer.parseInt(arr[1].substring(0,2)) != 20) && (Integer.parseInt(arr[1].substring(4,6)) > 12) &&
                            (Integer.parseInt(arr[1].substring(6,8)) > 31)
                            )
                        throw new Exception();
                    int indexO = listAerop.indexOf(arr[3]);
                    int horaO = Integer.parseInt(arr[2].split(":")[0]);
                    int aumentoO = listHusos.get(indexO);
                    if((horaO + aumentoO) >= 24)
                        horaO += aumentoO - 24;
                    else if(horaO + aumentoO < 0)
                        horaO += aumentoO + 24;
                    else
                        horaO += aumentoO;
                    paquete plannedPack = new paquete(arr[0],
                            Date.from(LocalTime.of(horaO, Integer.parseInt(arr[2].split(":")[1]))
                                    .atDate(LocalDate.of(
                                    Integer.parseInt(arr[1].substring(0, 4)),
                                    Integer.parseInt(arr[1].substring(4, 6)),
                                    Integer.parseInt(arr[1].substring(6, 8)))).
                                    atZone(ZoneId.systemDefault()).toInstant()), identificator, arr[3]);
                    
                    aux.add(plannedPack);
                }
                linea++;
            }
            System.out.println("Packs' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            if(linea == 1)
                JOptionPane.showMessageDialog(null,
                    "La línea 1 contiene un error de formato. No se podrá continuar con"
                            + "la carga hasta que el error se corrija",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            else{
                int reply = JOptionPane.showConfirmDialog(null,
                        "La línea " + String.valueOf(linea) + " contiene un error de formato.\n"
                                + "¿Desea guardar los paquetes ya leídos?",
                        "Mensaje de error", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    return aux;
                }
                else {
                   JOptionPane.showMessageDialog(null, "Registro cancelado");
                   return new ArrayList<paquete>();
                }
            }
        }
        return aux;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArchivos = new javax.swing.JTable();
        lblSelecArch = new javax.swing.JLabel();
        chkCargaMultiple = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        txtNombreArch = new javax.swing.JTextField();
        lblNombreArch = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTipoInfo = new javax.swing.JComboBox<>();
        lblPathArch = new javax.swing.JLabel();
        txtPathArch = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnAnhadir = new javax.swing.JButton();
        btnProcesar = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CARGA DE DATOS");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        btnArchivo.setText("Seleccione un archivo ...");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });
        panelFondo.add(btnArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 290, -1));

        tblArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ubicación", "Tipo de info."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblArchivos);
        if (tblArchivos.getColumnModel().getColumnCount() > 0) {
            tblArchivos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblArchivos.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 670, 170));

        lblSelecArch.setText("Seleccione un archivo:");
        panelFondo.add(lblSelecArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        chkCargaMultiple.setText("¿Obtener ruta?");
        panelFondo.add(chkCargaMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Detalle"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreArch.setEnabled(false);
        jPanel1.add(txtNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 350, -1));

        lblNombreArch.setText("Nombre:");
        jPanel1.add(lblNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Contenido del archivo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        cboTipoInfo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aeropuertos", "Vuelos", "Paquetes", "Clientes", "Empleados" }));
        jPanel1.add(cboTipoInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 180, -1));

        lblPathArch.setText("Ruta:");
        jPanel1.add(lblPathArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtPathArch.setEnabled(false);
        jPanel1.add(txtPathArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 350, -1));

        btnEliminar.setText("Eliminar seleccionado");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 160, -1));

        btnAnhadir.setText("Añadir a lista");
        btnAnhadir.setEnabled(false);
        btnAnhadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhadirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnhadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 160, -1));

        panelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 670, 130));

        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        panelFondo.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 100, -1));

        btnAyuda.setText("Ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        panelFondo.add(btnAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de texto (extensión TXT)", "txt");
        chooser.setFileFilter(filter);
        boolean repetido = false;
        while(!repetido){
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.CANCEL_OPTION)
                return ;
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                /* Comprobar que no está repetido */
                for(int i=0; i<listFile.size(); i++)
                    repetido = repetido || listFile.get(i).cmp(
                            new Archivo(chooser.getSelectedFile().getName(),
                                chooser.getSelectedFile().getPath()));
                if(repetido){
                    JOptionPane.showMessageDialog(null,
                        "Este archivo ya ha sido seleccionado", "Mensaje de error",
                        JOptionPane.ERROR_MESSAGE);
                    repetido = false;
                    continue;
                }
                txtNombreArch.setText(chooser.getSelectedFile().getName());
                if(chkCargaMultiple.getModel().isSelected())
                    txtPathArch.setText(chooser.getSelectedFile().getParent());
                else
                    txtPathArch.setText(chooser.getSelectedFile().getPath());
                break;
            }
        }
        btnAnhadir.setEnabled(true);
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnAnhadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhadirActionPerformed
        Archivo arch = new Archivo(txtNombreArch.getText(), 
                txtPathArch.getText(), String.valueOf(cboTipoInfo.getSelectedItem()));
        listFile.add(arch);
        actualizarTabla();
        txtNombreArch.setText("");
        txtPathArch.setText("");
    }//GEN-LAST:event_btnAnhadirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(tblArchivos.getSelectedRow() > -1){
            listFile.remove(tblArchivos.getSelectedRow());
            actualizarTabla();
        }else JOptionPane.showMessageDialog(null,
                "Debe seleccionar un archivo para poder eliminarlo", "Mensaje de error",
                JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        for(int i=0; i<listFile.size(); i++){
            boolean order = false;
            if(listFile.get(i).getTipo() == "Aeropuertos"){
                aeropuertoBL procBL = new aeropuertoBL();
                try{
                    procBL.registrarAeropuertos(procesarAeropuertos(listFile.get(i).getUbicacion()));
                    JOptionPane.showMessageDialog(null,
                    "El proceso de registro de aeropuertos", "Término de proceso",
                    JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){order = true;}
            }else if(listFile.get(i).getTipo() == "Vuelos"){
                VueloBL procBL = new VueloBL();
                try{
                    procBL.registrarVuelos(procesarVuelos(listFile.get(i).getUbicacion()));
                    JOptionPane.showMessageDialog(null,
                    "El proceso de registro de vuelos", "Término de proceso",
                    JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){order = true;}
            }else if (listFile.get(i).getTipo() == "Clientes"){
               AdministrarClienteBL procBL = new AdministrarClienteBL();
                //procBL.registrarClientes(procesarClientes(listFile.get(i).getUbicacion()));
                try{
                    procBL.registarClientes(procesarClientes(listFile.get(i).getUbicacion()));
                    JOptionPane.showMessageDialog(null,
                    "El proceso de registro de clientes", "Término de proceso",
                    JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){order = true;}
            }else if(listFile.get(i).getTipo() == "Paquetes"){
                if(chkCargaMultiple.getModel().isSelected()){
                    File f = new File(listFile.get(i).getUbicacion());
                    String[] fileList = f.list();
                    try{
                        for(String str : fileList){
                            PaqueteBL procBL = new PaqueteBL();
                            procBL.registrarPaquetes(procesarPaquetes(listFile.get(i).getUbicacion() + "/" + str));
                        }
                        JOptionPane.showMessageDialog(null,
                        "El proceso de registro de paquetes", "Término de proceso",
                        JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){order = true;}
                }else{
                    PaqueteBL procBL = new PaqueteBL();
                    try{
                        procBL.registrarPaquetes(procesarPaquetes(listFile.get(i).getUbicacion()));
                        JOptionPane.showMessageDialog(null,
                        "El proceso de registro de paquetes", "Término de proceso",
                        JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){order = true;}
                }
            }else if(listFile.get(i).getTipo() == "Empleados"){
                usuarioBL procBL = new usuarioBL();
                try{
                    ArrayList<persona> aux = procesarEmpleados(listFile.get(i).getUbicacion());
                    if(aux.isEmpty())
                        throw new Exception();
                    procBL.registrarUsuarios(aux, this.usuarios);
                    JOptionPane.showMessageDialog(null,
                    "El proceso de registro de empleados ha ", "Término de proceso",
                    JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){order = true;}
            }
            Archivo auxx = listFile.get(i);
            if(order)
                listFile.remove(auxx);
        }
        DefaultTableModel modelTable = (DefaultTableModel) tblArchivos.getModel();
        while(modelTable.getRowCount() > 0){
            modelTable.removeRow(0);
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        new frmAyudaCarga(x,true).setVisible(true);
    }//GEN-LAST:event_btnAyudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnhadir;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cboTipoInfo;
    private javax.swing.JCheckBox chkCargaMultiple;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreArch;
    private javax.swing.JLabel lblPathArch;
    private javax.swing.JLabel lblSelecArch;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblArchivos;
    private javax.swing.JTextField txtNombreArch;
    private javax.swing.JTextField txtPathArch;
    // End of variables declaration//GEN-END:variables
}
