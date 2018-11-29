/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.PaqueteBL;
import Controlador.excelExport;
import Controlador.generalBL;
import Modelo.continente;
import Modelo.paquete;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alulab14
 */
public class frmReportePaquete extends javax.swing.JPanel {
    
    /* ATRIBUTOS */
    private ArrayList<paquete> lstPaq = new ArrayList<paquete>();
    private ArrayList<paquete> filter = new ArrayList<paquete>();
    private javax.swing.JFrame x;
    private generalBL gen;
    private ArrayList<String> contSalida;
    private ArrayList<String> contLlegada;
    
    public frmReportePaquete(javax.swing.JFrame x) {
        initComponents();
//        lstPaq.add(new paquete("AAA01", new Date(118,9,21,15,30), "SPBO",  "SKBO", "Juan Pérez", "María García", "Entregado"));
//        lstPaq.add(new paquete("AAA02", new Date(118,9,21,15,30), "SPBO", "EEMM","Francois Guillard", "María García", "En camino"));
//        lstPaq.add(new paquete("AAA02", new Date(118,9,21,15,30), "SVMM", "EKML","Guillermo Farfán", "Ján Čierny", "Entregado"));
        PaqueteBL paqueteBL = new PaqueteBL();
        lstPaq = paqueteBL.obtenerPaquetes();
        gen = new generalBL();
        ArrayList<continente> list = gen.obtenerContinentes();
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        for(int i=0; i<list.size();i++){
            listModel.add(i,list.get(i).getNombre());  
        }
        listContinenteO.setModel(listModel);
        listContinenteD.setModel(listModel);
        filter = lstPaq;
        this.x = x;
    }

    public void setOrigen(String codigo){
        txtOrigen.setText(codigo);
    }
    
    public void setDestino(String codigo){
        txtDestino.setText(codigo);
    }
    
    public void setCodigo(String codigo){
        txtCodigo.setText(codigo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAirports = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        panelFiltrado = new javax.swing.JPanel();
        panelEstado = new javax.swing.JPanel();
        chkEstadoEntregado = new javax.swing.JCheckBox();
        chkEstadoEnCamino = new javax.swing.JCheckBox();
        btnLimpiarFlitro = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        panelContinenteO = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listContinenteO = new javax.swing.JList<>();
        panelContinenteD = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listContinenteD = new javax.swing.JList<>();
        panelFecha = new javax.swing.JPanel();
        dtpSalida = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtpSalida1 = new com.github.lgooddatepicker.components.DateTimePicker();
        panelAeropuerto = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        btnBuscarOrigen = new javax.swing.JButton();
        btnBuscarDestino = new javax.swing.JButton();
        panelCodigo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscarPaq = new javax.swing.JButton();
        panelFiltro = new javax.swing.JPanel();
        chkAerop = new javax.swing.JCheckBox();
        chkPaq = new javax.swing.JCheckBox();
        chkFechas = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        panelFondo.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 172, -1));

        tblAirports.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Ciudad origen", "Emisor", "Ciudad destino", "Receptor", "Estado", "Aero. origen", "Aero. destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAirports);

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 740, 120));

        jLabel2.setText("Resultado de consulta");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        panelFiltrado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        panelFiltrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estado"));
        panelEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadoEntregado.setText("Entregado");
        panelEstado.add(chkEstadoEntregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));

        chkEstadoEnCamino.setText("En camino");
        panelEstado.add(chkEstadoEnCamino, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 25, -1, -1));

        panelFiltrado.add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 190, 70));

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnLimpiarFlitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 107, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 107, -1));

        panelContinenteO.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente origen"));
        panelContinenteO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listContinenteO);

        panelContinenteO.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 60));

        panelFiltrado.add(panelContinenteO, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 82, 210, 90));

        panelContinenteD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente destino"));
        panelContinenteD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteD.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContinenteD);

        panelContinenteD.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 60));

        panelFiltrado.add(panelContinenteD, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 82, 210, 90));

        panelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rango temporal"));
        panelFecha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelFecha.add(dtpSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 15, -1, 22));

        jLabel1.setText("Hasta");
        panelFecha.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, -1, -1));

        jLabel6.setText("Desde");
        panelFecha.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        panelFecha.add(dtpSalida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 42, -1, 22));

        panelFiltrado.add(panelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 380, 70));

        panelAeropuerto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Aeropuerto"));
        panelAeropuerto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Destino");
        panelAeropuerto.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, -1, -1));

        jLabel10.setText("Origen");
        panelAeropuerto.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, -1, -1));

        txtDestino.setEnabled(false);
        panelAeropuerto.add(txtDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 25, 50, -1));

        txtOrigen.setEnabled(false);
        panelAeropuerto.add(txtOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 25, 40, 20));

        btnBuscarOrigen.setEnabled(false);
        btnBuscarOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrigenActionPerformed(evt);
            }
        });
        panelAeropuerto.add(btnBuscarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 25, 30, 20));

        btnBuscarDestino.setEnabled(false);
        btnBuscarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDestinoActionPerformed(evt);
            }
        });
        panelAeropuerto.add(btnBuscarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 25, 30, 20));

        panelFiltrado.add(panelAeropuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 112, 280, 60));

        panelCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código de paquete"));
        panelCodigo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Código");
        panelCodigo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtCodigo.setEnabled(false);
        panelCodigo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 260, -1));

        btnBuscarPaq.setText("Buscar");
        btnBuscarPaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPaqActionPerformed(evt);
            }
        });
        panelCodigo.add(btnBuscarPaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        panelFiltrado.add(panelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 18, 430, 60));

        panelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Habilitar/deshabilitar"));
        panelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkAerop.setText("Buscar por aeropuerto");
        chkAerop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAeropMouseClicked(evt);
            }
        });
        panelFiltro.add(chkAerop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        chkPaq.setText("Activar los filtros personalizados");
        chkPaq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkPaqMouseClicked(evt);
            }
        });
        panelFiltro.add(chkPaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        chkFechas.setText("Activar fechas");
        chkFechas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkFechasMouseClicked(evt);
            }
        });
        panelFiltro.add(chkFechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        panelFiltrado.add(panelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 280, 80));

        panelFondo.add(panelFiltrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 740, 260));

        jLabel3.setText("REPORTE DE PAQUETES");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelFondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 11, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /* MÉTODOS */
    public void tablaDefault(){
        DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
        int tamanho = modelo.getRowCount();
        for(int i=0; i<tamanho; i++){
            modelo.removeRow(0);
        }
        Object[] obj = new Object[8];
        for(int i = 0; i < lstPaq.size(); i++){
            paquete u = lstPaq.get(i);
            obj[0] = u.getCodigo();
            obj[1] = u.getCiudadOrigen();
            obj[2] = u.getClienteEmisor();
            obj[3] = u.getCiudadDestino();
            obj[4] = u.getClienteReceptor();
            obj[5] = u.getEstado();
            obj[6] = u.getAeropuertoOrigen();
            obj[7] = u.getAeropuertoDestino();
            modelo.addRow(obj);
        }
    }
    
    public int cmpDate(LocalDate fecha1, LocalDate fecha2){
        /* Resultado:
         * -1: La primera fecha es más reciente que la segunda.
         * 0 : Ambas fechas son iguales
         * 1 : La primera fecha es más antigua que la segunda.  
         */
        if (fecha1.getYear() > fecha2.getYear())
            return -1;
        else if (fecha1.getYear() == fecha2.getYear()){
            if (fecha1.getMonthValue() > fecha2.getMonthValue())
                return -1;
            else if (fecha1.getMonthValue() == fecha2.getMonthValue()){
                if (fecha1.getDayOfMonth() > fecha2.getDayOfMonth())
                    return -1;
                if (fecha1.getDayOfMonth() < fecha2.getDayOfMonth())
                    return 1;
                return 0;
            }
        }
        return 1;
    }
    
    public int cmpHour(LocalTime hora1, LocalTime hora2){
        /* Resultado:
         * -1: La primera hora es más reciente que la segunda.
         * 0 : Ambas horas son iguales
         * 1 : La primera hora es más antigua que la segunda.  
         */
        if (hora1.getHour() > hora2.getHour())
            return -1;
        else if (hora1.getHour() == hora2.getHour()){
            if (hora1.getMinute() > hora2.getMinute())
                return -1;
            else if (hora1.getMinute() == hora2.getMinute()){
                if (hora1.getSecond() > hora2.getSecond())
                    return -1;
                if (hora1.getSecond() < hora2.getSecond())
                    return 1;
                return 0;
            }
            return 1;
        }
        return 1;
    }
    
    // Métodos
    public int fitroValido(){
        /* En caso que la fecha de salida esté vacía, por defecto se selecciona
         * 01/10/18 00:00.
         * En caso que la fecha de llegada esté vacía, por defecto se selecciona
         * el momento actual.
         */
        LocalDate fechaSalida = LocalDate.parse("01/10/2018",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
        LocalTime horaSalida = LocalTime.of(0,0,0);
        /* Fecha de salida */
        if(dtpSalida.getDatePicker().toString().equals("") &&
               dtpSalida.getTimePicker().toString().equals("") ){
            fechaSalida = LocalDate.parse("01/10/2018",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            horaSalida = LocalTime.of(0,0,0);
        }
        if(!dtpSalida.getDatePicker().toString().equals("") &&
               dtpSalida.getTimePicker().toString().equals("")){
            fechaSalida = dtpSalida.getDatePicker().getDate();
            horaSalida = LocalTime.of(0,0,0);
        }
        if(dtpSalida.getDatePicker().toString().equals("") &&
               !dtpSalida.getTimePicker().toString().equals("")){
            return 12;
        }
        return 0;
    }
    
    public boolean filtroEstado(paquete ae){
        boolean chk1 = chkEstadoEntregado.isSelected();
        boolean chk2 = chkEstadoEnCamino.isSelected();
        if(!chk1 && !chk2)
            return true;
        if(!chk1)
            if(ae.getEstado().equals("Entregado"))
                return false;
        if(!chk2)
            if(ae.getEstado().equals("En camino"))
                return false;
        return true;
    }
    
    public boolean filtroContinente(paquete ae){
        if(listContinenteO.getSelectedIndex() > -1){
            if((listContinenteO.getSelectedIndex() == 0) &&
                    (ae.getContinenteOrigen().equals("Europa")))
                return false;
            if((listContinenteO.getSelectedIndex() == 1) &&
                    (ae.getContinenteOrigen().equals("America del Sur")))
                return false;
        }
        if(listContinenteD.getSelectedIndex() > -1){
            if((listContinenteD.getSelectedIndex() == 0) &&
                    (ae.getContinenteDestino().equals("Europa")))
                return false;
            if((listContinenteD.getSelectedIndex() == 1) &&
                    (ae.getContinenteDestino().equals("America del Sur")))
                return false;
        }
        return true;
    }
    
    public boolean filtroFechas(paquete ae){
        if(chkFechas.getModel().isSelected()){
            Date fechaSalida = ae.getFechaSalida();
            LocalDate dateSalida = fechaSalida.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime timeSalida = fechaSalida.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalTime();
            LocalDate filtroFechaSalida = LocalDate.parse("01/10/2018",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate filtroFechaLlegada = LocalDate.now();
            LocalTime filtroHoraSalida = LocalTime.of(0,0,0);
            LocalTime filtroHoraLlegada = LocalTime.now();

            /* Fecha de salida */
            if(dtpSalida.getDatePicker().toString().equals("") &&
                   dtpSalida.getTimePicker().toString().equals("") ){
                filtroFechaSalida = LocalDate.parse("01/10/2018",
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                filtroHoraSalida = LocalTime.of(0,0,0);
            }
            if(!dtpSalida.getDatePicker().toString().equals("") &&
                   dtpSalida.getTimePicker().toString().equals("")){
                filtroFechaSalida = dtpSalida.getDatePicker().getDate();
                filtroHoraSalida = LocalTime.of(0,0,0);
            }
            if((cmpDate(dateSalida, filtroFechaSalida) == 1) ||
                    ((cmpDate(dateSalida, filtroFechaSalida) == 0) &&
                    (cmpHour(timeSalida, filtroHoraSalida) == 1)))
                return false;
            return true;
        }else return true;
    }
    
    
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        excelExport controlerExcel = new excelExport();
        controlerExcel.excelExportPaquetes(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnLimpiarFlitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFlitroActionPerformed
        chkEstadoEntregado.setSelected(false);
        chkEstadoEnCamino.setSelected(false);
        dtpSalida.clear();
        listContinenteO.clearSelection();
        listContinenteD.clearSelection();
        tablaDefault();
    }//GEN-LAST:event_btnLimpiarFlitroActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // Primero se tendrá que validar el filtro
        filter = new ArrayList<paquete>();
        if(fitroValido() == 0){
            for(int i=0; i<lstPaq.size(); i++){
                if(filtroEstado(lstPaq.get(i)) &&
                    filtroContinente(lstPaq.get(i)) &&
                    filtroFechas(lstPaq.get(i))){
                    filter.add(lstPaq.get(i));
                }
            }
            DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
            int tamanho = modelo.getRowCount();
            for(int i=0; i<tamanho; i++){
                modelo.removeRow(0);
            }
            Object[] obj = new Object[8];
            for(int i = 0; i < filter.size(); i++){
                paquete u = filter.get(i);
                obj[0] = u.getCodigo();
                obj[1] = u.getCiudadOrigen();
                obj[2] = u.getClienteEmisor();
                obj[3] = u.getCiudadDestino();
                obj[4] = u.getClienteReceptor();
                obj[5] = u.getEstado();
                obj[6] = u.getAeropuertoOrigen();
                obj[7] = u.getAeropuertoDestino();
                modelo.addRow(obj);
            }
        }else{
            switch(fitroValido()){
                case 10:
                JOptionPane.showMessageDialog(null,
                    "Las fechas de salida y llegada no pueden ser iguales",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 11:
                JOptionPane.showMessageDialog(null,
                    "La fecha de salida no puede ser más reciente que la"
                    + " fecha de llegada",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 12:
                JOptionPane.showMessageDialog(null,
                    "La fecha de salida proporcionada es inválida",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 13:
                JOptionPane.showMessageDialog(null,
                    "La fecha de llegada proporcionada es inválida",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnBuscarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOrigenActionPerformed
        new Vista.frmBuscarAeropuerto(x,true,this,0).setVisible(true);
    }//GEN-LAST:event_btnBuscarOrigenActionPerformed

    private void btnBuscarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDestinoActionPerformed
        new Vista.frmBuscarAeropuerto(x,true,this,1).setVisible(true);
    }//GEN-LAST:event_btnBuscarDestinoActionPerformed

    private void chkAeropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkAeropMouseClicked
        if(chkAerop.getModel().isSelected()){
            listContinenteO.clearSelection();
            listContinenteO.setEnabled(false);
            listContinenteD.clearSelection();
            listContinenteD.setEnabled(false);
            btnBuscarOrigen.setEnabled(true);
            btnBuscarDestino.setEnabled(true);
        }else{
            listContinenteO.setEnabled(true);
            listContinenteD.setEnabled(true);
            btnBuscarOrigen.setEnabled(false);
            btnBuscarOrigen.setText("");
            btnBuscarDestino.setEnabled(false);
            btnBuscarDestino.setText("");
        }
    }//GEN-LAST:event_chkAeropMouseClicked

    private void btnBuscarPaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaqActionPerformed
        new Vista.frmBuscarPaquete(x,true,this).setVisible(true);
    }//GEN-LAST:event_btnBuscarPaqActionPerformed

    private void chkFechasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkFechasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFechasMouseClicked

    private void chkPaqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPaqMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPaqMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDestino;
    private javax.swing.JButton btnBuscarOrigen;
    private javax.swing.JButton btnBuscarPaq;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiarFlitro;
    private javax.swing.JCheckBox chkAerop;
    private javax.swing.JCheckBox chkEstadoEnCamino;
    private javax.swing.JCheckBox chkEstadoEntregado;
    private javax.swing.JCheckBox chkFechas;
    private javax.swing.JCheckBox chkPaq;
    private com.github.lgooddatepicker.components.DateTimePicker dtpSalida;
    private com.github.lgooddatepicker.components.DateTimePicker dtpSalida1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listContinenteD;
    private javax.swing.JList<String> listContinenteO;
    private javax.swing.JPanel panelAeropuerto;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelContinenteD;
    private javax.swing.JPanel panelContinenteO;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFiltrado;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblAirports;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
