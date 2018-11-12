/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.excelExport;
import Controlador.generalBL;
import Modelo.Vuelo;
import Modelo.continente;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
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
public class frmReporteVuelo extends javax.swing.JPanel {
    
    /* ATRIBUTOS */
    private ArrayList<Vuelo> lstVuelo = new ArrayList<Vuelo>();
    private ArrayList<Vuelo> filter = new ArrayList<Vuelo>();
    
    public frmReporteVuelo() {
        initComponents();
        lstVuelo.add(new Vuelo("AAA01", new Date(118,9,21,15,30), 
                new Date(118,9,22,00,25), "América", "Europa", 250, 240, "Saturado"));
        lstVuelo.add(new Vuelo("AAA02", new Date(118,9,21,19,30), 
                new Date(118,9,22,12,40), "Europa", "Europa", 280, 150, "Estable")); 
        lstVuelo.add(new Vuelo("AAA03", new Date(118,9,22,00,10), 
                new Date(118,9,2,19,50), "América", "América", 295, 295, "Lleno"));
        generalBL gen = new generalBL();
        ArrayList<continente> list = gen.obtenerContinentes();
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        for(int i=0; i<list.size();i++){
            listModel.add(i,list.get(i).getNombre());  
        }
        listContinenteO.setModel(listModel);
        listContinenteD.setModel(listModel);
        filter = lstVuelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        btnExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAirports = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelEstado = new javax.swing.JPanel();
        chkEstadoEstable = new javax.swing.JCheckBox();
        chkEstadoSaturado = new javax.swing.JCheckBox();
        chkEstadoLleno = new javax.swing.JCheckBox();
        panelCap = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCapMin = new javax.swing.JTextField();
        txtCapMax = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiarFlitro = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        panelContinenteO = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listContinenteO = new javax.swing.JList<>();
        panelContinenteD = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
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
                "Código", "Salida", "Llegada", "Origen", "Destino", "Cap. máxima", "Cap. actual", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAirports);

        jLabel2.setText("Resultado de consulta");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estado"));
        panelEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadoEstable.setText("Estable");
        panelEstado.add(chkEstadoEstable, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 23, -1, -1));

        chkEstadoSaturado.setText("Saturado");
        panelEstado.add(chkEstadoSaturado, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 49, -1, -1));

        chkEstadoLleno.setText("Lleno");
        panelEstado.add(chkEstadoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, -1, -1));

        jPanel1.add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 107, 110));

        panelCap.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rango de capacidades"));
        panelCap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Capacidad mínima");
        panelCap.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel7.setText("Capacidad máxima");
        panelCap.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtCapMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMinKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 100, -1));

        txtCapMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMaxKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, -1));

        jLabel8.setText("paquetes");
        panelCap.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jLabel9.setText("paquetes");
        panelCap.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        jPanel1.add(panelCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 360, 110));

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiarFlitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, 107, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 107, -1));

        panelContinenteO.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente origen"));
        panelContinenteO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listContinenteO);

        panelContinenteO.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jPanel1.add(panelContinenteO, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, 110));

        panelContinenteD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente destino"));
        panelContinenteD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteD.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listContinenteD);

        panelContinenteD.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 80));

        jPanel1.add(panelContinenteD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 110));

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

        jPanel1.add(panelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 360, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("REPORTE DE VUELOS");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jLabel3))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        for(int i = 0; i < lstVuelo.size(); i++){
            Vuelo u = lstVuelo.get(i);
            obj[0] = u.getCodigo();
            obj[1] = new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(u.getFechaSalida());
            obj[2] = new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(u.getFechaLlegada());
            obj[3] = u.getAeropuertoOrigen();
            obj[4] = u.getAeropuertoDestino();
            obj[5] = u.getCapMax();
            obj[6] = u.getCapActual();
            obj[7] = u.getEstado();
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
    
    public int fitroValido(){
        /* Filtro de capacidades */
        int capMin = 200;
        if(!txtCapMin.getText().equals("")){
            capMin = Integer.parseInt(txtCapMin.getText());
            if((capMin > 300) || (capMin < 200))
                return 10;
        }
        int capMax = 300;
        if(!txtCapMax.getText().equals("")){
            capMax = Integer.parseInt(txtCapMax.getText());
            if((capMax > 300) || (capMax < 200))
                return 11;
        }
        if(capMax < capMin)
            return 12;
        /* Filtro de rango temporal */
        
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
            return 22;
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
            return 23;
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
            return 20;
        if((cmpDate(fechaSalida, fechaLlegada) == -1) ||
                ((cmpDate(fechaSalida, fechaLlegada) == 0)) && 
                ((cmpHour(horaSalida, horaLlegada) == -1)))
            return 21;
        return 0;
    }
    
    public boolean filtroEstado(Vuelo ae){
        boolean chk1 = chkEstadoEstable.isSelected();
        boolean chk2 = chkEstadoSaturado.isSelected();
        boolean chk3 = chkEstadoLleno.isSelected();
        if(!chk1 && !chk2 && !chk3)
            return true;
        if(!chk1)
            if(ae.getEstado().equals("Estable"))
                return false;
        if(!chk2)
            if(ae.getEstado().equals("Saturado"))
                return false;
        if(!chk3)
            if(ae.getEstado().equals("Lleno"))
                return false;
        return true;
    }
    
    public boolean filtroContinente(Vuelo ae){
        if(listContinenteO.getSelectedIndex() > -1){
            if((listContinenteO.getSelectedIndex() == 0) &&
                    (ae.getAeropuertoOrigen().equals("Europa")))
                return false;
            if((listContinenteO.getSelectedIndex() == 1) &&
                    (ae.getAeropuertoOrigen().equals("América")))
                return false;
        }
        if(listContinenteD.getSelectedIndex() > -1){
            if((listContinenteD.getSelectedIndex() == 0) &&
                    (ae.getAeropuertoDestino().equals("Europa")))
                return false;
            if((listContinenteD.getSelectedIndex() == 1) &&
                    (ae.getAeropuertoDestino().equals("América")))
                return false;
        }
        return true;
    }
    
    public boolean filtroCapacidad(Vuelo ae){
        // Si no se indica alguno, se infiere que no habrá filtro
        if(!(txtCapMax.getText().equals("")) && 
                (ae.getCapActual() > Integer.parseInt(txtCapMax.getText())))
            return false;
        if(!(txtCapMin.getText().equals("")) && 
                (ae.getCapActual() < Integer.parseInt(txtCapMin.getText())))
            return false;
        return true;
    }
    
    public boolean filtroFechas(Vuelo ae){
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
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
        LocalDate filtroFechaLlegada = LocalDate.parse("01/10/2018",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
        LocalTime filtroHoraSalida = LocalTime.of(0,0,0);
        LocalTime filtroHoraLlegada = LocalTime.of(0,0,0);
        
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
        controlerExcel.excelExportVuelos(filter);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void txtCapMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapMinKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_txtCapMinKeyTyped

    private void txtCapMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapMaxKeyTyped
        char vchar = evt.getKeyChar();
        if(!(Character.isDigit(vchar))
            || (vchar == KeyEvent.VK_BACK_SPACE)
            || (vchar == KeyEvent.VK_DELETE))
        evt.consume();
    }//GEN-LAST:event_txtCapMaxKeyTyped

    private void btnLimpiarFlitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFlitroActionPerformed
        chkEstadoEstable.setSelected(false);
        chkEstadoSaturado.setSelected(false);
        chkEstadoLleno.setSelected(false);
        dtpSalida.clear();
        dtpLlegada.clear();
        txtCapMin.setText("");
        txtCapMax.setText("");
        listContinenteO.clearSelection();
        listContinenteD.clearSelection();
        tablaDefault();
    }//GEN-LAST:event_btnLimpiarFlitroActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // Primero se tendrá que validar el filtro
        filter = new ArrayList<Vuelo>();
        if(fitroValido() == 0){
            for(int i=0; i<lstVuelo.size(); i++){
                if(filtroEstado(lstVuelo.get(i)) &&
                    filtroContinente(lstVuelo.get(i)) &&
                    filtroCapacidad(lstVuelo.get(i)) &&
                    filtroFechas(lstVuelo.get(i))){
                    filter.add(lstVuelo.get(i));
                }
            }
            DefaultTableModel modelo = (DefaultTableModel) tblAirports.getModel();
            int tamanho = modelo.getRowCount();
            for(int i=0; i<tamanho; i++){
                modelo.removeRow(0);
            }
            for(int i = 0; i < filter.size(); i++){
                Object[] obj = new Object[8];
                Vuelo u = filter.get(i);
                obj[0] = u.getCodigo();
                obj[1] = new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(u.getFechaSalida());
                obj[2] = new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(u.getFechaLlegada());
                obj[3] = u.getAeropuertoOrigen();
                obj[4] = u.getAeropuertoDestino();
                obj[5] = u.getCapMax();
                obj[6] = u.getCapActual();
                obj[7] = u.getEstado();
                modelo.addRow(obj);
            }
            if(filter.isEmpty()){
                Object[] obj = new Object[1];
                obj[0] = "No se encontró ninguna incidencia";
                modelo.addRow(obj);
            }
        }else{
            switch(fitroValido()){
                case 10:
                JOptionPane.showMessageDialog(null,
                    "La capacidad mínima debe estar en el rango indicado",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 11:
                JOptionPane.showMessageDialog(null,
                    "La capacidad máxima debe estar en el rango indicado",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 12:
                JOptionPane.showMessageDialog(null,
                    "La capacidad mínima no puede ser mayor que la capacidad"
                    + " máxima", "Mensaje de error",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
                case 20:
                JOptionPane.showMessageDialog(null,
                    "Las fechas de salida y llegada no pueden ser iguales",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 21:
                JOptionPane.showMessageDialog(null,
                    "La fecha de salida no puede ser más reciente que la"
                    + " fecha de llegada",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 22:
                JOptionPane.showMessageDialog(null,
                    "La fecha de salida proporcionada es inválida",
                    "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                break;
                case 23:
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
    private javax.swing.JCheckBox chkEstadoEstable;
    private javax.swing.JCheckBox chkEstadoLleno;
    private javax.swing.JCheckBox chkEstadoSaturado;
    private com.github.lgooddatepicker.components.DateTimePicker dtpLlegada;
    private com.github.lgooddatepicker.components.DateTimePicker dtpSalida;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JList<String> listContinenteD;
    private javax.swing.JList<String> listContinenteO;
    private javax.swing.JPanel panelCap;
    private javax.swing.JPanel panelContinenteD;
    private javax.swing.JPanel panelContinenteO;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JTable tblAirports;
    private javax.swing.JTextField txtCapMax;
    private javax.swing.JTextField txtCapMin;
    // End of variables declaration//GEN-END:variables
}
