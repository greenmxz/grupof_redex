/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Nowa
 */

// Generacion de Data
        
        //ENGM000000001-20180417-02:58-LZIB
        //[CodigoAeropuertoEmisor][CodigoEnvio]-[AAAAMMDD]-[HH:MM]-[CodigoAeropuertoReceptor]
        
        // Y = X ^ n
        // Y : cantidad de paquetes por día
        // X : tiempo --- Día
        // n : coef de variacion por ciudad(aeropuerto)

// si se quiere hallar el coef
        // n = log(x)Y
        


public class proyeccionData {
    
    private ArrayList<String> CodigoAeropuertoEmisor = new ArrayList<>();
    private ArrayList<String> CodigoEnvio = new ArrayList<>();
    private ArrayList<String> fecha = new ArrayList<>();
    private ArrayList<String> hora = new ArrayList<>();
    private ArrayList<String> CodigoAeropuertoReceptor = new ArrayList<>();
    
    private ArrayList<Integer> CantidadPedidosDia = new ArrayList<>();
    private ArrayList<String> FechasTrabajadas = new ArrayList<>();  
    
    public proyeccionData(){
        
    }
    
    
    public void exportExcel(){
        
        File archivo = new File("ReporteData.xls");
        
        HSSFWorkbook  workbook = new HSSFWorkbook (); 
        
        Sheet pagina = workbook.createSheet("Reporte de data");
        
        CellStyle style = workbook.createCellStyle();
        
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        
        String[] titulos = {"Fecha", "Cantidad de Envios"}; 
        
        Row fila = pagina.createRow(0);
        
        for(int i = 0; i < titulos.length; i++) {

            Cell celda = fila.createCell(i);
            celda.setCellStyle(style); 
            celda.setCellValue(titulos[i]);
        }
        
        for(int i=0; i<FechasTrabajadas.size(); i++){
            // Ahora creamos una fila en la posicion 1
            fila = pagina.createRow(i+1);
            fila.createCell(0).setCellValue(FechasTrabajadas.get(i));
            fila.createCell(1).setCellValue(CantidadPedidosDia.get(i));
        }
             
        try {

            FileOutputStream salida = new FileOutputStream(archivo);
            
            workbook.write(salida);

            workbook.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR "+ex.getMessage());
        }
        
    }
    
    
    
    
    public void processPack(String namePack){
        try{
            
            
            BufferedReader reader = new BufferedReader(new FileReader(namePack));
            String line;
            while( (line = reader.readLine()) != null){
                String[] arr = line.split("-");
              
                
                if(arr.length == 4){
                    
                    CodigoAeropuertoEmisor.add(arr[0].substring(0, 4));
                    CodigoEnvio.add(arr[0]);
                    fecha.add(arr[1]);
                    hora.add(arr[2]);
                    CodigoAeropuertoReceptor.add(arr[3]);
                    
                    System.out.println("CodigoAeropuertoEmisor : " + arr[0].substring(0, 4));
                    System.out.println("CodigoEnvio : " + arr[0]);
                    System.out.println("fecha : " + arr[1]);
                    System.out.println("hora : " + arr[2]);
                    System.out.println("CodigoAeropuertoReceptor : " + arr[3]);
                    System.out.println("-----------------------------------------------------");
                }
            }
            System.out.println("Packs' reading process successful!");
            System.out.println("Cantidad de pedidos Totales : " + CodigoEnvio.size());
            
            for(int i = 0; i < CodigoEnvio.size(); i++){       
                if (!FechasTrabajadas.contains(fecha.get(i))){
                    FechasTrabajadas.add(fecha.get(i));
                }
            }
            
            System.out.println("Cantidad de días de data Totales : " + FechasTrabajadas.size());
            int count = 0;
            for(int i = 0; i < FechasTrabajadas.size(); i++){
                for(int j = 0; j < CodigoEnvio.size(); j++){
                    if (fecha.get(j).equals(FechasTrabajadas.get(i))){
                        count += 1;    
                    }
                }
                CantidadPedidosDia.add(count);
                count = 0;
            }
            
            for(int i = 0; i < FechasTrabajadas.size(); i++){
                System.out.println("Fecha " + FechasTrabajadas.get(i) + " pedidos : " + CantidadPedidosDia.get(i));
            }
            
            exportExcel();
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There are a several problem with the flights' reading process! Check it!");
        }
    }

    public ArrayList<String> getCodigoAeropuertoEmisor() {
        return CodigoAeropuertoEmisor;
    }

    public void setCodigoAeropuertoEmisor(ArrayList<String> CodigoAeropuertoEmisor) {
        this.CodigoAeropuertoEmisor = CodigoAeropuertoEmisor;
    }

    public ArrayList<String> getCodigoEnvio() {
        return CodigoEnvio;
    }

    public void setCodigoEnvio(ArrayList<String> CodigoEnvio) {
        this.CodigoEnvio = CodigoEnvio;
    }

    public ArrayList<String> getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList<String> fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getHora() {
        return hora;
    }

    public void setHora(ArrayList<String> hora) {
        this.hora = hora;
    }

    public ArrayList<String> getCodigoAeropuertoReceptor() {
        return CodigoAeropuertoReceptor;
    }

    public void setCodigoAeropuertoReceptor(ArrayList<String> CodigoAeropuertoReceptor) {
        this.CodigoAeropuertoReceptor = CodigoAeropuertoReceptor;
    }
    
    
    
}
