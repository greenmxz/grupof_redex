/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.*;
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
import Modelo.coefCiudad;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*; 
import java.nio.file.Path;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import Modelo.PolRegresion;
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
    
 
    private ArrayList<Integer> CantidadPedidosDia = new ArrayList<>();
    private ArrayList<String> FechasTrabajadas = new ArrayList<>();
    
    private ArrayList<String> Archivos = new ArrayList<>();
    private ArrayList<coefCiudad> coeficientes = new ArrayList<>();
        
    public proyeccionData(){
        
    }
    
    
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
    
    public void cleanFile(String path){
        try{
            
            PrintWriter pw = new PrintWriter(path);
            pw.close();
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
        }
        
        
    }
    
    public static void appendEnvios(String fileName, ArrayList<String> enviosGen) { 
        try { 
  
            // Open given file in append mode. 
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            
            for (String envio : enviosGen){
                out.write(envio);
                out.newLine();
            }

            out.close(); 
        } catch (IOException e) { 
            System.out.println("exception occoured" + e); 
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

    
    
    public String aeropuertoRandom(String origen){
        try{
            
            String destino = origen;
            
            do {
                int index = ThreadLocalRandom.current().nextInt(0, coeficientes.size());
                destino = coeficientes.get(index).getAeropuertoCod();
            } while (origen.equals(destino));
            
            return destino;
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    public String generarFill(int cant, char c){
        try{
            String fill = "";
            if (cant > 0){
                for (int i = 0; i < cant ; i++){
                    fill += c;
                }
            }
            return fill;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    
    public String generarCodEnvio(int numEnvios ,String aero, int pos){
        try{
            String num = Integer.toString(numEnvios + pos);
            String fill = generarFill(9 - num.length(),'0'); // por los 9 digitos luego del cod aeropuerto
            String cod = aero + fill + num;
            return cod;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    
    public String generaFecha(String fecha, int pos){
        try{
            //System.out.println("FECHA A TRABAJAR : " + fecha);
            String fechaNueva = "";
        
            Calendar calendar = Calendar.getInstance();
            
            int yy = Integer.parseInt(fecha.substring(0, 4));
            int mm = Integer.parseInt(fecha.substring(4, 6));
            int dd = Integer.parseInt(fecha.substring(6, 8));
            
            calendar.set(yy, mm, dd);
            
            calendar.add(Calendar.DAY_OF_YEAR, pos);
            
            Date diaNuevo = calendar.getTime();
            
            //System.out.println("FECHA -----> " + diaNuevo);
           
            String newYear = Integer.toString(diaNuevo.getYear() + 1900);
            String newMonth = Integer.toString(diaNuevo.getMonth());
            String newDay = Integer.toString(diaNuevo.getDate());

            
            if (newMonth.length() < 2)
                newMonth = generarFill(1,'0') + newMonth;
            
            if (newDay.length() < 2)
                newDay = generarFill(1,'0') + newDay;
            
            fechaNueva = newYear + newMonth + newDay;
            //System.out.println("FECHA A GENERADA : " + fechaNueva);
            return fechaNueva;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    
    public String generaHora(){
        try{
            String horaNueva = "";
            
            int num = ThreadLocalRandom.current().nextInt(0, 1440); // valores entre 0 y 1439
            int hh = num / 60;
            int mm = num % 60;
            
            String hora = Integer.toString(hh);
            String minuto = Integer.toString(mm);
            
            if (hora.length() < 2)
                hora = generarFill(1,'0') + hora;
            
            if (minuto.length() < 2)
                minuto = generarFill(1,'0') + minuto;
            
            horaNueva = hora+ ":" +minuto;
        
            return horaNueva;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    
        //Ejemplo
        //ENGM000000001-20180417-02:58-LZIB
        //[CodigoAeropuertoEmisor][CodigoEnvio]-[AAAAMMDD]-[HH:MM]-[CodigoAeropuertoReceptor]
    
    public void proyectar(int numeroDias){
        try{
            //reinicia
            this.Archivos = new ArrayList<>();
            this.coeficientes = new ArrayList<>();
            ////////////////////////////////////////
            ArrayList<String> enviosGen = new ArrayList<>();
            // se calcula los coeficientes de todos los archivos
            calcularCoeficientes();
            // se proyecta la data por cada archivo segun la cantidad indicada
            int numCiudad = 0;
            for(coefCiudad coef : coeficientes){
                int contadorEnvio = 0;
                if (numeroDias > coef.getCantidadFechas()){
                    int cantDiasFaltantes = numeroDias - coef.getCantidadFechas();
                    //System.out.println(coef.getAeropuertoCod() +" Cantidad de dias por generar :" + cantDiasFaltantes);
                    for (int i = 0; i < cantDiasFaltantes; i++){
                        //genera data del dia siguiente
                        //cada dia tiene una cantidad de envios realizados que se calculan con los coefientes
                        double[] c = coef.getCoef(); // coeficientes de del aeropuerto
                        int diaNuevo = coef.getCantidadFechas() + i + 1;
                        int numEnvio = coef.getCantidadDatosAnalizados();
                        int cant = (int)(c[0] + c[1]*diaNuevo + c[2] * diaNuevo*diaNuevo); // cantidad de envios
                        String fecha = generaFecha(coef.getUltimaFecha(),i+1);
                        //System.out.println(coef.getAeropuertoCod() + " dia "+ diaNuevo +" - Cant de envios a generar : " + cant);
                        for (int j = 0; j < cant; j++){
                            // por cada envio se genera la data del envio
                            //aeropuerto receptor random
                            String aeropuertoReceptor = aeropuertoRandom(coef.getAeropuertoCod());
                            // fecha y hora random
                            String hora = generaHora();
                            // se genera codigo de envio
                            String codigoEnvio = generarCodEnvio(numEnvio,coef.getAeropuertoCod(),contadorEnvio+1);
                            // se arma string de envio
                            String envioSt = codigoEnvio + "-" + fecha + "-" + hora + "-" + aeropuertoReceptor;
                            //System.out.println(envioSt);
                            enviosGen.add(envioSt);
                            contadorEnvio += 1;
                        }
                        
                    }
 
                }
                
                // con cada string de envio generado se arma nuevo archivo
                File archivo = new File("resources\\pack_enviados\\" + this.Archivos.get(numCiudad));
                File archivoNuevo = new File("resources\\pack_enviados_generados\\" + this.Archivos.get(numCiudad));
                
                //limpia data
                cleanFile("resources\\pack_enviados_generados\\" + this.Archivos.get(numCiudad));
                
                //copia archivos
                copyFile(archivo,archivoNuevo);
                //agrega data nueva
                appendEnvios("resources\\pack_enviados_generados\\" + this.Archivos.get(numCiudad),enviosGen);
                
                enviosGen = new ArrayList<>();// se reinicia
                numCiudad += 1;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
        }

    }
    
    public void calcularCoeficientes(){
        try{
        
            final File folder = new File("resources\\pack_enviados");
            listFilesForFolder(folder);

            //por cada archivo en la carpeta va a sacar los coefs
            for(String name : Archivos){           
                coefCiudad coefCiudad = processPack("resources\\pack_enviados\\"+name);
                coeficientes.add(coefCiudad);         
            }

            System.out.println("Coeficientes");
            for (coefCiudad coef : coeficientes){
                System.out.println("Aeropuerto : " + coef.getAeropuertoCod());
                for(int i = 0; i < coef.getCoef().length; i++){
                     System.out.println(coef.getCoef()[i]);
                }
                System.out.println("---------------------");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
        }

    }
    
    
    public coefCiudad processPack(String namePack){
        try{
            ArrayList<String> CodigoAeropuertoEmisor = new ArrayList<>();
            ArrayList<String> CodigoEnvio = new ArrayList<>();
            ArrayList<String> fecha = new ArrayList<>();
            ArrayList<String> hora = new ArrayList<>();
            ArrayList<String> CodigoAeropuertoReceptor = new ArrayList<>();
            CantidadPedidosDia = new ArrayList<>();
            FechasTrabajadas = new ArrayList<>();
            
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
                }
            }
            
            for(int i = 0; i < CodigoEnvio.size(); i++){       
                if (!FechasTrabajadas.contains(fecha.get(i))){
                    FechasTrabajadas.add(fecha.get(i));
                }
            }

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
            
            ///Test de regresion polinomica 2do grado
            coefCiudad coefCiudad = new coefCiudad();
            double[] x = new double[CodigoEnvio.size()];
            double[] y = new double[CodigoEnvio.size()];
            
            for(int i = 0; i < FechasTrabajadas.size(); i++){ 
                x[i] = i + 1;
                y[i] = CantidadPedidosDia.get(i);
            }
            
            PolRegresion PolRegresion = new PolRegresion(x,y,2);
            PolRegresion.calculaPolinomio();
            double[] coef = PolRegresion.getA();
            coefCiudad.setAeropuertoCod(CodigoAeropuertoEmisor.get(0));
            coefCiudad.setCoef(coef);
            coefCiudad.setCantidadDatosAnalizados(CodigoEnvio.size());
            coefCiudad.setCantidadFechas(FechasTrabajadas.size());
            coefCiudad.setUltimaFecha(FechasTrabajadas.get(FechasTrabajadas.size()-1));
            //System.out.println("ULTIMA FECHA " + coefCiudad.getUltimaFecha());
            return coefCiudad;
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return null;
        }
    }

    public ArrayList<coefCiudad> getCoeficientes() {
        return coeficientes;
    }

    public void setCoeficientes(ArrayList<coefCiudad> coeficientes) {
        this.coeficientes = coeficientes;
    }

    
}
