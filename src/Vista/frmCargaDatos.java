package Vista;

import Controlador.aeropuertoBL;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Modelo.Archivo;
import Modelo.Vuelo;
import Modelo.aeropuerto;
import Modelo.paquete;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmCargaDatos extends javax.swing.JPanel {

    private ArrayList<Archivo> listFile = new ArrayList<Archivo>();
    
    public frmCargaDatos() {
        initComponents();
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
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            String continent = "";
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("\\s+");
                if(arr.length == 1 || arr[1].equals("OACI") || arr[1].equals("ICAO")) continue;
//                for(int i=0; i<arr.length; i++){
//                    System.out.print("'");
//                    System.out.print(arr[i] + "' ");
//                }
                int[] espacios = hallarEspacios(line);
//                System.out.print("[");
//                for(int i=0; i<espacios.length; i++){
//                    System.out.print(espacios[i]);
//                    System.out.print(",");
//                }
//                System.out.print("]");
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
                    airpt.print();
                    aux.add(airpt);
                }
            }
            System.out.println("Airports' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the airports' reading process! Check it!");
        }
        return aux;
    }
    
    public ArrayList<Vuelo> procesarVuelos(String ruta){
        ArrayList<Vuelo> aux = new ArrayList<Vuelo>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                if(arr.length == 4){
                    Vuelo plannedFlg = new Vuelo("Da igual",
                            Date.from(LocalTime.of(Integer.parseInt(arr[2].split(":")[0]),
                                    Integer.parseInt(arr[2].split(":")[1])).atDate(LocalDate.of(2018, 11, 2)).
                                            atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(LocalTime.of(Integer.parseInt(arr[3].split(":")[0]),
                                    Integer.parseInt(arr[3].split(":")[1])).atDate(LocalDate.of(2018, 11, 2)).
                                        atZone(ZoneId.systemDefault()).toInstant()), arr[0], arr[1]);
                    plannedFlg.print();
                    aux.add(plannedFlg);
                }
            }
            System.out.println("Flights' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
        }
        return aux;
    }
    
    public ArrayList<paquete> procesarPaquetes(String ruta){
        ArrayList<paquete> aux = new ArrayList<paquete>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
                if(arr[1].equals("20180418"))
                    break;
                if(arr.length == 4){
                    paquete plannedPack = new paquete(arr[0],
                            Date.from(LocalTime.of(Integer.parseInt(arr[2].split(":")[0]),
                                   Integer.parseInt(arr[2].split(":")[1])).atDate(LocalDate.of(
                                        Integer.parseInt(arr[1].substring(0, 4)),
                                        Integer.parseInt(arr[1].substring(4, 6)),
                                        Integer.parseInt(arr[1].substring(6, 8)))).
                                        atZone(ZoneId.systemDefault()).toInstant()), "SKBO", arr[3]);
                    plannedPack.print();
                    aux.add(plannedPack);
                }
            }
            System.out.println("Packs' reading process successful!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
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

        chkCargaMultiple.setText("¿Activar carga múltiple?");
        panelFondo.add(chkCargaMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Detalle"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreArch.setEnabled(false);
        jPanel1.add(txtNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 350, -1));

        lblNombreArch.setText("Nombre:");
        jPanel1.add(lblNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Contenido del archivo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        cboTipoInfo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aeropuertos", "Vuelos", "Paquetes", "Clientes" }));
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
        JFileChooser chooser = new JFileChooser("G:\\PUCP\\9no Ciclo\\DP1\\Sistema\\algoritmo\\tabuProvisional");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de texto (extensión TXT)", "txt");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(chkCargaMultiple.getModel().isSelected());
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
            if(listFile.get(i).getTipo() == "Aeropuertos"){
                aeropuertoBL procBL = new aeropuertoBL();
                procesarAeropuertos(listFile.get(i).getUbicacion());
                procBL.registrarAeropuertos(procesarAeropuertos(listFile.get(i).getUbicacion()));
            }else if(listFile.get(i).getTipo() == "Vuelos"){
                procesarVuelos(listFile.get(i).getUbicacion());
            }else if(listFile.get(i).getTipo() == "Paquetes"){
                procesarPaquetes(listFile.get(i).getUbicacion());
            }
        }
    }//GEN-LAST:event_btnProcesarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnhadir;
    private javax.swing.JButton btnArchivo;
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
