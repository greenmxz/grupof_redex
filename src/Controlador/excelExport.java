package Controlador;

/*Tomado de: https://medium.com/el-acordeon-del-programador/creando-archivos-
excel-en-formato-xlsx-desde-java-190c30a6d67d
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Modelo.*;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import Modelo.aeropuertov2;
import javax.swing.JOptionPane;

public class excelExport {
    public void excelExportAeropuertos(ArrayList<aeropuertov2> lstAeropuertos){
        // Creamos el archivo donde almacenaremos la hoja
        // de calculo, recuerde usar la extension correcta,
        // en este caso .xlsx
        File archivo = new File("ReporteAeropuertos.xls");
        
        // Creamos el libro de trabajo de Excel formato OOXML
        HSSFWorkbook  workbook = new HSSFWorkbook (); 
        
        // La hoja donde pondremos los datos
        Sheet pagina = workbook.createSheet("Reporte de aeropuertos");
        
        // Creamos el estilo paga las celdas del encabezado
        CellStyle style = workbook.createCellStyle();
        // Indicamos que tendra un fondo azul aqua
        // con patron solido del color indicado
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        
        String[] titulos = {"Código", "Aeropuerto", "Continente", "País",
                            "Ciudad", "Capacidad máxima", "Capacidad ocupada",
                            "Estado"};      
        
        // Creamos una fila en la hoja en la posicion 0
        Row fila = pagina.createRow(0);
        
        // Creamos el encabezado
        for(int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la posicion 
            // indicada por el contador del ciclo
            Cell celda = fila.createCell(i);
            
            // Indicamos el estilo que deseamos 
            // usar en la celda, en este caso el unico 
            // que hemos creado
            celda.setCellStyle(style); 
            celda.setCellValue(titulos[i]);
        }
        
        // Y colocamos los datos en esa fila
        for(int i=0; i<lstAeropuertos.size(); i++){
            // Ahora creamos una fila en la posicion 1
            fila = pagina.createRow(i+1);
            aeropuertov2 ae = lstAeropuertos.get(i);
            fila.createCell(0).setCellValue(ae.getCodigo());
            fila.createCell(1).setCellValue(ae.getNombre());
            fila.createCell(2).setCellValue(ae.getContinente());
            fila.createCell(3).setCellValue(ae.getPais());
            fila.createCell(4).setCellValue(ae.getCiudad());
            fila.createCell(5).setCellValue(ae.getCapMax());
            fila.createCell(6).setCellValue(ae.getCapActual());
            fila.createCell(7).setCellValue(ae.getEstado());
        }
        
        // Ahora guardaremos el archivo
        try {
            // Creamos el flujo de salida de datos,
            // apuntando al archivo donde queremos 
            // almacenar el libro de Excel
            FileOutputStream salida = new FileOutputStream(archivo);
            
            // Almacenamos el libro de 
            // Excel via ese 
            // flujo de datos
            workbook.write(salida);
            
            // Cerramos el libro para concluir operaciones
            workbook.close();
            JOptionPane.showMessageDialog(null,"Archivo creado existosamente en "+
                    archivo.getAbsolutePath()+".\nADVERTENCIA: Para poder abrirlo"+
                    " deberá cerrar el sistema.",
                    "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Archivo no localizable en sistema de archivos.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error de entrada/salida.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void excelExportVuelos(ArrayList<vuelo> lstVuelos){
        // Creamos el archivo donde almacenaremos la hoja
        // de calculo, recuerde usar la extension correcta,
        // en este caso .xlsx
        File archivo = new File("ReporteVuelos.xls");
        
        // Creamos el libro de trabajo de Excel formato OOXML
        HSSFWorkbook  workbook = new HSSFWorkbook (); 
        
        // La hoja donde pondremos los datos
        Sheet pagina = workbook.createSheet("Reporte de vuelos");
        
        // Creamos el estilo paga las celdas del encabezado
        CellStyle style = workbook.createCellStyle();
        // Indicamos que tendra un fondo azul aqua
        // con patron solido del color indicado
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        
        String[] titulos = {"Código", "Fecha de salida", "Fecha de llegada", "Continente origen",
                            "Continente destino", "Capacidad máxima", "Capacidad ocupada",
                            "Estado"};      
        
        // Creamos una fila en la hoja en la posicion 0
        Row fila = pagina.createRow(0);
        
        // Creamos el encabezado
        for(int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la posicion 
            // indicada por el contador del ciclo
            Cell celda = fila.createCell(i);
            
            // Indicamos el estilo que deseamos 
            // usar en la celda, en este caso el unico 
            // que hemos creado
            celda.setCellStyle(style); 
            celda.setCellValue(titulos[i]);
        }
        
        // Y colocamos los datos en esa fila
        for(int i=0; i<lstVuelos.size(); i++){
            // Ahora creamos una fila en la posicion 1
            fila = pagina.createRow(i+1);
            vuelo ae = lstVuelos.get(i);
            fila.createCell(0).setCellValue(ae.getCodigo());
            fila.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(ae.getFechaSalida()));
            fila.createCell(2).setCellValue(new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(ae.getFechaLlegada()));
            fila.createCell(3).setCellValue(ae.getContinenteOrigen());
            fila.createCell(4).setCellValue(ae.getContinenteDestino());
            fila.createCell(5).setCellValue(ae.getCapMax());
            fila.createCell(6).setCellValue(ae.getCapActual());
            fila.createCell(7).setCellValue(ae.getEstado());
        }
        
        // Ahora guardaremos el archivo
        try {
            // Creamos el flujo de salida de datos,
            // apuntando al archivo donde queremos 
            // almacenar el libro de Excel
            FileOutputStream salida = new FileOutputStream(archivo);
            
            // Almacenamos el libro de 
            // Excel via ese 
            // flujo de datos
            workbook.write(salida);
            
            // Cerramos el libro para concluir operaciones
            workbook.close();
            JOptionPane.showMessageDialog(null,"Archivo creado existosamente en "+
                    archivo.getAbsolutePath()+".\nADVERTENCIA: Para poder abrirlo"+
                    " deberá cerrar el sistema.",
                    "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Archivo no localizable en sistema de archivos.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error de entrada/salida.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void excelExportPaquetes(ArrayList<paquete> lstPaquetes){
        // Creamos el archivo donde almacenaremos la hoja
        // de calculo, recuerde usar la extension correcta,
        // en este caso .xlsx
        File archivo = new File("ReportePaquetes.xls");
        
        // Creamos el libro de trabajo de Excel formato OOXML
        HSSFWorkbook  workbook = new HSSFWorkbook (); 
        
        // La hoja donde pondremos los datos
        Sheet pagina = workbook.createSheet("Reporte de paquetes");
        
        // Creamos el estilo paga las celdas del encabezado
        CellStyle style = workbook.createCellStyle();
        // Indicamos que tendra un fondo azul aqua
        // con patron solido del color indicado
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        
        String[] titulos = {"Código", "Fecha de salida", "Ciudad origen", "Aeropuerto origen", 
                "Cliente emisor", "Fecha de llegada", "Ciudad destino", "Aeropuerto destino",
                "Cliente receptor", "Estado"};      
        
        // Creamos una fila en la hoja en la posicion 0
        Row fila = pagina.createRow(0);
        
        // Creamos el encabezado
        for(int i = 0; i < titulos.length; i++) {
            // Creamos una celda en esa fila, en la posicion 
            // indicada por el contador del ciclo
            Cell celda = fila.createCell(i);
            
            // Indicamos el estilo que deseamos 
            // usar en la celda, en este caso el unico 
            // que hemos creado
            celda.setCellStyle(style); 
            celda.setCellValue(titulos[i]);
        }
        
        // Y colocamos los datos en esa fila
        for(int i=0; i<lstPaquetes.size(); i++){
            // Ahora creamos una fila en la posicion 1
            fila = pagina.createRow(i+1);
            paquete ae = lstPaquetes.get(i);
            fila.createCell(0).setCellValue(ae.getCodigo());
            fila.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(ae.getFechaSalida()));
            fila.createCell(2).setCellValue(ae.getCiudadOrigen());
            fila.createCell(3).setCellValue(ae.getAeropuertoOrigen());
            fila.createCell(4).setCellValue(ae.getClienteEmisor());
            fila.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(ae.getFechaLlegada()));
            fila.createCell(6).setCellValue(ae.getCiudadDestino());
            fila.createCell(7).setCellValue(ae.getAeropuertoDestino());
            fila.createCell(8).setCellValue(ae.getClienteReceptor());
            fila.createCell(9).setCellValue(ae.getEstado());
        }
        
        // Ahora guardaremos el archivo
        try {
            // Creamos el flujo de salida de datos,
            // apuntando al archivo donde queremos 
            // almacenar el libro de Excel
            FileOutputStream salida = new FileOutputStream(archivo);
            
            // Almacenamos el libro de 
            // Excel via ese 
            // flujo de datos
            workbook.write(salida);
            
            // Cerramos el libro para concluir operaciones
            workbook.close();
            JOptionPane.showMessageDialog(null,"Archivo creado existosamente en "+
                    archivo.getAbsolutePath()+".\nADVERTENCIA: Para poder abrirlo"+
                    " deberá cerrar el sistema.",
                    "Exportación exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Archivo no localizable en sistema de archivos.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error de entrada/salida.\n"
                    +"Por favor, contacte al administrador para solucionar el problema",
                    "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
