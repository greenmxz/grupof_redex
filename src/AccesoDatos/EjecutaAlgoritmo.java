/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Algoritmo.DataProcessing;
import Algoritmo.TabuSearch;
import Vista.TabuSimulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author JUAN
 */
public class EjecutaAlgoritmo extends Thread {
//    private  Timer t ;
//
     private int minutoMundial=0  ;
     private int horaMundial =0 ;
     private Calendar calendar;
     private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
     private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
     private int tiempoAlgoMM=0;
     private int tiempoDelayAlgoritmo=60*2;
     private DataProcessing dp = new DataProcessing();
     private ArrayList<String> Archivos = new ArrayList<>();
     private TabuSearch ts;
     
     public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                Archivos.add(fileEntry.getName());
                
            }
        }
    }
     void lecturaData(){
        try{

            final File folder = new File("resources\\pack_enviados");
            listFilesForFolder(folder);

            ts = new TabuSearch();
            ts.inputData("resources\\aeropuertos.txt",
                "resources\\planes_vuelo.txt",
                "resources\\pack_enviados");
            ts.generateFlightMatrix();  
            int size=ts.getListPack().size();
            this.dp.setListAirport(ts.getListAirport());

            ts.setInputProcess(this.dp);
            for (String a : this.Archivos){
                dp.processPackNew("resources\\pack_enviados_generados\\" + a);
                //dp.processPackNew("resources\\pack_enviados\\" + a);
            }

            System.out.println("cant total de paquetes - " + this.dp.getPackList().size()); // todos los paquetes


            this.listPack = this.dp.getPackList();
            //this.matrixPackXDay = this.dp.getMatrixPackXDay();


            if (this.listPack.size()>0)//se coloca la fecha del primer pack como fecha del simulador
                this.calendar.set(this.listPack.get(0).getOriginYear(),this.listPack.get(0).getOriginMonth() - 1,this.listPack.get(0).getOriginDay());
            this.listPack.clear();
            //this.inicio = 1;
            
        }catch(Exception ex){
           System.out.println("ERROR lecturaData " + ex.getMessage() );
       }
    }
    public void run(){
//        t = new Timer(8,this);
//        t.start();
        //while(true){
        calendar=Calendar.getInstance();
            
        try {
            System.out.println("AQUI implementare algoritmo");
            
            
            ts = new TabuSearch();
            ts.inputData("resources\\aeropuertos.txt",
                "resources\\planes_vuelo.txt",
                "resources\\pack_enviados");
            ts.generateFlightMatrix();  
            int size=ts.getListPack().size();
            this.dp.setListAirport(ts.getListAirport());

                ts.setInputProcess(this.dp);
                for (String a : this.Archivos){
                    dp.processPackNew("resources\\pack_enviados_generados\\" + a);
                    //dp.processPackNew("resources\\pack_enviados\\" + a);
                }

                System.out.println("cant total de paquetes - " + this.dp.getPackList().size()); // todos los paquetes


                this.listPack = this.dp.getPackList();
                //this.matrixPackXDay = this.dp.getMatrixPackXDay();
                

                if (this.listPack.size()>0)//se coloca la fecha del primer pack como fecha del simulador
                    this.calendar.set(this.listPack.get(0).getOriginYear(),this.listPack.get(0).getOriginMonth() - 1,this.listPack.get(0).getOriginDay());
            
                
            ts.executeVCRPTabu(listPack);
//TabuSimulator simulador=new TabuSimulator(horaMundial,minutoMundial,calendar.getTime(),ts,ts.getListAirport(),ts.getListFlight(),listPackAlgo,ts.getListPack(),tiempoAlgoMM,tiempoDelayAlgoritmo);
            //ArrayList<String> solution = ts.executeVCRPTabu(ts.getListPack());
            //simulador.start();
            //this.listPackAlgo = simulador.getListPackAlgo();
            //this.tiempoAlgoMM = simulador.getTiempoAlgo();
            //for(int i=0;i<size;i++)
                //System.out.println(listPackAlgo.get(i).getRuta());
            
            this.finalize();
            
            
            //}
        } catch (Throwable ex) {
            Logger.getLogger(EjecutaAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
