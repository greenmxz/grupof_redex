package Vista;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Modelo.Archivo;
import java.util.ArrayList;
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
        btnLimpiar = new javax.swing.JButton();
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
        jPanel1.add(txtNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 350, -1));

        lblNombreArch.setText("Nombre:");
        jPanel1.add(lblNombreArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setText("Contenido del archivo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        cboTipoInfo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aeropuertos", "Vuelos", "Paquetes", "Clientes" }));
        jPanel1.add(cboTipoInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 180, -1));

        lblPathArch.setText("Ruta:");
        jPanel1.add(lblPathArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel1.add(txtPathArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 350, -1));

        btnLimpiar.setText("Limpiar");
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 160, -1));

        btnEliminar.setText("Eliminar seleccionado");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 160, -1));

        btnAnhadir.setText("Añadir a lista");
        btnAnhadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhadirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnhadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 160, -1));

        panelFondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 670, 130));

        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        panelFondo.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        JFileChooser chooser = new JFileChooser();
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
        
    }//GEN-LAST:event_btnProcesarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnhadir;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
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
