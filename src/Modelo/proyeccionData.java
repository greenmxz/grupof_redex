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
import Modelo.coefCiudad;
import java.util.concurrent.ThreadLocalRandom;

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
    
    
    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                Archivos.add(fileEntry.getName());
            }
        }
    }
        //Ejemplo
        //ENGM000000001-20180417-02:58-LZIB
        //[CodigoAeropuertoEmisor][CodigoEnvio]-[AAAAMMDD]-[HH:MM]-[CodigoAeropuertoReceptor]
    
    
    
    public String aeropuertoRandom(){
        try{
            int index = ThreadLocalRandom.current().nextInt(0, coeficientes.size() + 1);
            return coeficientes.get(index).getAeropuertoCod();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }
    
    
    
    public void proyectar(int numeroDias){
        try{
            // se calcula los coeficientes de todos los archivos
            calcularCoeficientes();
            // se proyecta la data por cada archivo segun la cantidad indicada
            for(coefCiudad coef : coeficientes){

                if (numeroDias > coef.getCantidadDatosAnalizados()){
                    for (int i = 0; i < numeroDias - coef.getCantidadDatosAnalizados(); i++){
                        //genera data del dia siguiente
                        //cada dia tiene una cantidad de envios realizados que se calculan con los coefientes
                        double[] c = coef.getCoef(); // coeficientes de del aeropuerto
                        int cant = (int)(c[0] + c[1]*numeroDias + c[2] * numeroDias*numeroDias); // cantidad de envios
                        
                        for (int j = 0; j < cant; j++){
                            // por cada envio se genera la data del envio
                            //aeropuerto receptor random
                            String aeropuertoRand = aeropuertoRandom();
                            // hora random
                            
                            // se genera codigo de envio
                            
                            // se guarda info de envio
                            
                            // se arma string de envio
                            
                        }
                        
                    }
                }
                
                
                // con cada string de envio generado se arma nuevo archivo
                
                
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
