package Vista;

import Controlador.VueloBL;
import Controlador.excelExport;
import Controlador.generalBL;
import Modelo.Vuelo;
import Modelo.continente;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmReporteVuelo extends javax.swing.JPanel {
    
    /* ATRIBUTOS */
    private ArrayList<Vuelo> lstVuelo = new ArrayList<Vuelo>();
    private ArrayList<Vuelo> filter = new ArrayList<Vuelo>();
    private ArrayList<continente> list = new ArrayList<continente>();
    private generalBL gen;
    private ArrayList<String> contSalida;
    private ArrayList<String> contLlegada;
    private javax.swing.JFrame x;
    
    public frmReporteVuelo(javax.swing.JFrame x) {
        initComponents();
        VueloBL vuelos = new VueloBL();
        lstVuelo = vuelos.listaVuelos();
        gen = new generalBL();
        list = gen.obtenerContinentes();
        tablaDefault();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        for(int i=0; i<list.size();i++){
            listModel.add(i,list.get(i).getNombre());  
        }
        contSalida = gen.obtenerContinenteSalida();
        contLlegada = gen.obtenerContinenteLlegada();
        listContinenteO.setModel(listModel);
        listContinenteD.setModel(listModel);
        filter = lstVuelo;
        this.x = x;
    }

    public void setOrigen(String codigo){
        txtOrigen.setText(codigo);
    }
    
    public void setDestino(String codigo){
        txtDestino.setText(codigo);
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
        panelAerop = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        btnBuscarOrigen = new javax.swing.JButton();
        btnBuscarDestino = new javax.swing.JButton();
        chkAerop = new javax.swing.JCheckBox();
        panelFecha = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtpSalida = new com.lavantech.gui.comp.TimePanel();
        dtpLlegada = new com.lavantech.gui.comp.TimePanel();
        panelUbic = new javax.swing.JPanel();
        cboFiltro = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExcel.setText("Exportar a hoja de cálculo");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        panelFondo.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 465, 172, -1));

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

        panelFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 758, 130));

        jLabel2.setText("Resultado de consulta");
        panelFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de filtrado"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estado"));
        panelEstado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadoEstable.setText("Estable");
        panelEstado.add(chkEstadoEstable, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, -1, -1));

        chkEstadoSaturado.setText("Saturado");
        panelEstado.add(chkEstadoSaturado, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 35, -1, -1));

        chkEstadoLleno.setText("Lleno");
        panelEstado.add(chkEstadoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 55, -1, -1));

        jPanel1.add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 120, 90));

        panelCap.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rango de capacidades"));
        panelCap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Valor de capacidad mínima:");
        panelCap.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 25, -1, -1));

        jLabel7.setText("Valor de capacidad máxima:");
        panelCap.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 50, -1, -1));

        txtCapMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMinKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 25, 60, -1));

        txtCapMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapMaxKeyTyped(evt);
            }
        });
        panelCap.add(txtCapMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 60, -1));

        jLabel8.setText("paquetes");
        panelCap.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 25, -1, -1));

        jLabel9.setText("paquetes");
        panelCap.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        jPanel1.add(panelCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 290, 90));

        btnLimpiarFlitro.setText("Limpiar filtro");
        btnLimpiarFlitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFlitroActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiarFlitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 120, -1));

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 195, 120, -1));

        panelContinenteO.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente origen"));
        panelContinenteO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listContinenteO);

        panelContinenteO.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jPanel1.add(panelContinenteO, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 210, 100));

        panelContinenteD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Continente destino"));
        panelContinenteD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listContinenteD.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listContinenteD);

        panelContinenteD.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 70));

        jPanel1.add(panelContinenteD, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 210, 100));

        panelAerop.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Aeropuerto"));
        panelAerop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Destino");
        panelAerop.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, -1));

        jLabel10.setText("Origen");
        panelAerop.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtDestino.setEnabled(false);
        panelAerop.add(txtDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 45, 50, -1));

        txtOrigen.setEnabled(false);
        panelAerop.add(txtOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 50, 20));

        btnBuscarOrigen.setEnabled(false);
        btnBuscarOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOrigenActionPerformed(evt);
            }
        });
        panelAerop.add(btnBuscarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 30, 20));

        btnBuscarDestino.setEnabled(false);
        btnBuscarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDestinoActionPerformed(evt);
            }
        });
        panelAerop.add(btnBuscarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 45, 30, 20));

        chkAerop.setText("Buscar por aeropuerto");
        chkAerop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAeropMouseClicked(evt);
            }
        });
        panelAerop.add(chkAerop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jPanel1.add(panelAerop, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 150, 100));

        panelFecha.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rango temporal"));
        panelFecha.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Llegada");
        panelFecha.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 17, -1, -1));

        jLabel4.setText("Salida");
        panelFecha.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 17, -1, -1));

        dtpSalida.setCalendar(new java.util.GregorianCalendar(2018, 10, 12, 0, 0, 0));
        dtpSalida.setDisplayAnalog(false);
        dtpSalida.setSecDisplayed(false);
        panelFecha.add(dtpSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 50));

        dtpLlegada.setCalendar(new java.util.GregorianCalendar(2018, 10, 12, 23, 59, 0));
        dtpLlegada.setDisplayAnalog(false);
        dtpLlegada.setSecDisplayed(false);
        panelFecha.add(dtpLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, 50));

        jPanel1.add(panelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 290, 90));

        panelUbic.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtrar ubicación por:"));
        panelUbic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Continente", "País" }));
        cboFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFiltroItemStateChanged(evt);
            }
        });
        panelUbic.add(cboFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, -1));

        jPanel1.add(panelUbic, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 115, 120, 50));

        panelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 40, 758, 240));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("REPORTE DE VUELOS");
        panelFondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 15, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            obj[1] = new SimpleDateFormat("HH:mm").format(u.getFechaSalida());
            obj[2] = new SimpleDateFormat("HH:mm").format(u.getFechaLlegada());
            obj[3] = u.getAeropuertoOrigen();
            obj[4] = u.getAeropuertoDestino();
            obj[5] = u.getCapMax();
            obj[6] = u.getCapActual();
            obj[7] = u.getEstado();
            modelo.addRow(obj);
        }
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
        Date salida = dtpSalida.getCalendar().getTime();
        Date llegada = dtpLlegada.getCalendar().getTime();
        /* Las listas deben estar en un rango de 24 horas */
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
    
    public boolean filtroContinente(int ident){
        String contOrigen = contSalida.get(ident);
        String contDestino= contLlegada.get(ident);
        if(listContinenteO.getSelectedIndex() > -1){
            for(int i=0; i<list.size(); i++){
                if(listContinenteO.getSelectedIndex() == i &&
                        !(contOrigen.equals(list.get(i).getNombre())))
                    return false;
            }
        }
        if(listContinenteD.getSelectedIndex() > -1){
            for(int i=0; i<list.size(); i++){
                if(listContinenteD.getSelectedIndex() == i &&
                        !(contDestino.equals(list.get(i).getNombre())))
                    return false;
            }
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
        GregorianCalendar gcSal = dtpSalida.getCalendar();
        GregorianCalendar gcLle = dtpLlegada.getCalendar();
        /* Si la fecha de salida es menor a la del respectivo gc
        no se toma en cuenta */
        if(fechaSalida.getHours() < gcSal.getTime().getHours())
            return false;
        else if(fechaSalida.getHours() == gcSal.getTime().getHours())
            if(fechaSalida.getMinutes() < gcSal.getTime().getMinutes())
                return false;
        /* Si la fecha de llegada es mayor a la del respectivo gc
        no se toma en cuenta */
        if(fechaLlegada.getHours() < fechaSalida.getHours())
            return false;
        if(fechaLlegada.getHours() > gcLle.getTime().getHours())
            return false;
        else if(fechaLlegada.getHours() == gcLle.getTime().getHours())
            if(fechaLlegada.getMinutes() > gcLle.getTime().getMinutes())
                return false;
        return true;
    }
    
    public boolean filtroOrigen(Vuelo ae){
        if(txtOrigen.getText().equals(""))
            return true;
        else
            if(ae.getAeropuertoOrigen().equals(txtOrigen.getText()))
                return true;
        return false;
            
    }
    
    public boolean filtroDestino(Vuelo ae){
        if(txtDestino.getText().equals(""))
            return true;
        else
            if(ae.getAeropuertoDestino().equals(txtDestino.getText()))
                return true;
        return false;
            
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
        txtCapMin.setText("");
        txtCapMax.setText("");
        listContinenteO.clearSelection();
        listContinenteD.clearSelection();
        btnBuscarOrigen.setText("");
        btnBuscarDestino.setText("");
        tablaDefault();
    }//GEN-LAST:event_btnLimpiarFlitroActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // Primero se tendrá que validar el filtro
        filter = new ArrayList<Vuelo>();
        if(fitroValido() == 0){
            for(int i=0; i<lstVuelo.size(); i++){
                if(filtroEstado(lstVuelo.get(i)) &&
                        filtroContinente(i) &&
                        filtroCapacidad(lstVuelo.get(i)) &&
                        filtroFechas(lstVuelo.get(i)) &&
                        filtroOrigen(lstVuelo.get(i)) &&
                        filtroDestino(lstVuelo.get(i)) ){
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
                obj[1] = new SimpleDateFormat("HH:mm").format(u.getFechaSalida());
                obj[2] = new SimpleDateFormat("HH:mm").format(u.getFechaLlegada());
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

    private void cboFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFiltroItemStateChanged
         if(evt.getStateChange() == ItemEvent.SELECTED) {
             System.out.println(cboFiltro.getSelectedIndex());
         }
    }//GEN-LAST:event_cboFiltroItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDestino;
    private javax.swing.JButton btnBuscarOrigen;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiarFlitro;
    private javax.swing.JComboBox<String> cboFiltro;
    private javax.swing.JCheckBox chkAerop;
    private javax.swing.JCheckBox chkEstadoEstable;
    private javax.swing.JCheckBox chkEstadoLleno;
    private javax.swing.JCheckBox chkEstadoSaturado;
    private com.lavantech.gui.comp.TimePanel dtpLlegada;
    private com.lavantech.gui.comp.TimePanel dtpSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listContinenteD;
    private javax.swing.JList<String> listContinenteO;
    private javax.swing.JPanel panelAerop;
    private javax.swing.JPanel panelCap;
    private javax.swing.JPanel panelContinenteD;
    private javax.swing.JPanel panelContinenteO;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JPanel panelFecha;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelUbic;
    private javax.swing.JTable tblAirports;
    private javax.swing.JTextField txtCapMax;
    private javax.swing.JTextField txtCapMin;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
