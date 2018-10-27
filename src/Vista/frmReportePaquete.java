/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.excelExport;
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
    
    public frmReportePaquete() {
        initComponents();
        lstPaq.add(new paquete("AAA01", new Date(118,9,21,15,30), 
                new Date(118,10,22,00,25), "SPBO", "América",
                "Lima", "SKBO", "América", "Buenos Aires", "Juan Pérez", "María García", "Entregado"));
        lstPaq.add(new paquete("AAA02", new Date(118,9,21,15,30), 
                new Date(118,9,22,00,25), "SPBO", "América",
                "Lima", "EEMM", "Europa", "París", "Francois Guillard", "María García", "En camino"));
        lstPaq.add(new paquete("AAA02", new Date(118,9,21,15,30), 
                new Date(118,9,22,00,25), "SVMM", "América",
                "La Paz", "EKML", "Europa", "Bratislava", "Guillermo Farfán", "Ján Čierny", "Entregado"));
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        listModel.add(0,"América");
        listModel.add(1,"Europa");
        listContinenteO.setModel(listModel);
        listContinenteD.setModel(listModel);
        filter = lstPaq;
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
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        dtpSalida = new com.github.lgooddatepicker.components.DateTimePicker();
        dtpLlegada = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel3 = new javax.swing.JLabel();

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

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

        jLabel2.setText("Resultado de consulta");

        panelFiltrado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        panelFiltrado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estado"));
        panelEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadoEntregado.setText("Entregado");
        panelEstado.add(chkEstadoEntregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 26, -1, -1));

        chkEstadoEnCamino.setText("En camino");
        panelEstado.add(chkEstadoEnCamino, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 26, -1, -1));

        panelFiltrado.add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 326, 70));

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnLimpiarFlitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 107, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFiltrado.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 107, -1));

        panelContinenteO.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Continente origen"));
        panelContinenteO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listContinenteO);

        panelContinenteO.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 63));

        panelFiltrado.add(panelContinenteO, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 32, 210, 90));

        panelContinenteD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente destino"));
        panelContinenteD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteD.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listContinenteD);

        panelContinenteD.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 183, 50));

        panelFiltrado.add(panelContinenteD, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 142, 210, 84));

        panelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rango temporal"));
        panelFecha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label1.setText("Llegada");
        panelFecha.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 70, -1, -1));

        label2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        label2.setText("Salida");
        panelFecha.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 42, -1, -1));
        panelFecha.add(dtpSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 37, -1, -1));
        panelFecha.add(dtpLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 70, -1, -1));

        panelFiltrado.add(panelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 32, 330, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("REPORTE DE PAQUETES");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(panelFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(308, 308, 308))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(panelFiltrado, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        LocalDate fechaLlegada = LocalDate.parse("01/10/2018",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
        LocalTime horaSalida = LocalTime.of(0,0,0);
        LocalTime horaLlegada = LocalTime.of(0,0,0);
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
        /* Fecha de llegada */
        if(dtpLlegada.getDatePicker().toString().equals("") &&
               dtpLlegada.getTimePicker().toString().equals("") ){
            fechaLlegada = LocalDate.now();
            horaLlegada = LocalTime.now();
        }
        if(!dtpLlegada.getDatePicker().toString().equals("") &&
               dtpLlegada.getTimePicker().toString().equals("")){
            fechaLlegada = dtpLlegada.getDatePicker().getDate();
            horaLlegada = LocalTime.of(0,0,0);
        }
        if(dtpLlegada.getDatePicker().toString().equals("") &&
               !dtpLlegada.getTimePicker().toString().equals("")){
            return 13;
        }
        if(!dtpLlegada.getDatePicker().toString().equals("") &&
               !dtpLlegada.getTimePicker().toString().equals("")){
            fechaSalida = dtpSalida.getDatePicker().getDate();
            horaSalida = dtpSalida.getTimePicker().getTime();
        }
            
        if(!dtpSalida.getDatePicker().toString().equals("") &&
               !dtpSalida.getTimePicker().toString().equals("")){
            fechaLlegada = dtpLlegada.getDatePicker().getDate();
            horaLlegada = dtpLlegada.getTimePicker().getTime();
        }
        if(((cmpDate(fechaSalida, fechaLlegada) == 0)) && 
                ((cmpHour(horaSalida, horaLlegada) == 0)))
            return 10;
        if((cmpDate(fechaSalida, fechaLlegada) == -1) ||
                ((cmpDate(fechaSalida, fechaLlegada) == 0)) && 
                ((cmpHour(horaSalida, horaLlegada) == -1)))
            return 11;
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
                    (ae.getContinenteOrigen().equals("América")))
                return false;
        }
        if(listContinenteD.getSelectedIndex() > -1){
            if((listContinenteD.getSelectedIndex() == 0) &&
                    (ae.getContinenteDestino().equals("Europa")))
                return false;
            if((listContinenteD.getSelectedIndex() == 1) &&
                    (ae.getContinenteDestino().equals("América")))
                return false;
        }
        return true;
    }
    
    public boolean filtroFechas(paquete ae){
        Date fechaLlegada = ae.getFechaLlegada();
        Date fechaSalida = ae.getFechaSalida();
        LocalDate dateLlegada = fechaLlegada.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime timeLlegada = fechaLlegada.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalTime();
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
        /* Fecha de llegada */
        if(dtpLlegada.getDatePicker().toString().equals("") &&
               dtpLlegada.getTimePicker().toString().equals("") ){
            filtroFechaLlegada = LocalDate.now();
            filtroHoraLlegada = LocalTime.now();
        }
        if(!dtpLlegada.getDatePicker().toString().equals("") &&
               dtpLlegada.getTimePicker().toString().equals("")){
            filtroFechaLlegada = dtpLlegada.getDatePicker().getDate();
            filtroHoraLlegada = LocalTime.of(0,0,0);
        }
        if(!dtpLlegada.getDatePicker().toString().equals("") &&
               !dtpLlegada.getTimePicker().toString().equals("")){
            filtroFechaSalida = dtpSalida.getDatePicker().getDate();
            filtroHoraSalida = dtpSalida.getTimePicker().getTime();
        }
        if(!dtpSalida.getDatePicker().toString().equals("") &&
               !dtpSalida.getTimePicker().toString().equals("")){
            filtroFechaLlegada = dtpLlegada.getDatePicker().getDate();
            filtroHoraLlegada = dtpLlegada.getTimePicker().getTime();
        }
        if((cmpDate(dateSalida, filtroFechaSalida) == 1) ||
                ((cmpDate(dateSalida, filtroFechaSalida) == 0) &&
                (cmpHour(timeSalida, filtroHoraSalida) == 1)))
            return false;
        if((cmpDate(dateLlegada, filtroFechaLlegada) == -1) ||
                ((cmpDate(dateLlegada, filtroFechaLlegada) == 0) &&
                (cmpHour(timeLlegada, filtroHoraLlegada) == -1)))
            return false;
        return true;
    }
    
    
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        excelExport controlerExcel = new excelExport();
        controlerExcel.excelExportPaquetes(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnLimpiarFlitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFlitroActionPerformed
        chkEstadoEntregado.setSelected(false);
        chkEstadoEnCamino.setSelected(false);
        dtpSalida.clear();
        dtpLlegada.clear();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiarFlitro;
    private javax.swing.JCheckBox chkEstadoEnCamino;
    private javax.swing.JCheckBox chkEstadoEntregado;
    private com.github.lgooddatepicker.components.DateTimePicker dtpLlegada;
    private com.github.lgooddatepicker.components.DateTimePicker dtpSalida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JList<String> listContinenteD;
    private javax.swing.JList<String> listContinenteO;
    private javax.swing.JPanel panelContinenteD;
    private javax.swing.JPanel panelContinenteO;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFiltrado;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblAirports;
    // End of variables declaration//GEN-END:variables
}
