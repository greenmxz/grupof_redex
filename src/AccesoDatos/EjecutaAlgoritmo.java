/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Algoritmo.TabuSearch;
import Vista.TabuSimulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public void run(){
//        t = new Timer(8,this);
//        t.start();
        //while(true){
        calendar=Calendar.getInstance();
            
        try {
            System.out.println("AQUI implementare algoritmo");
            TabuSearch ts;
            ts = new TabuSearch();
            ts.inputData("resources\\aeropuertos.txt",
                "resources\\planes_vuelo.txt",
                "resources\\pack_enviados");
            ts.generateFlightMatrix();  
            int size=ts.getListPack().size();
            for(int i=0;i<size;i++)
                System.out.println(ts.getListPack().get(i).getRuta());
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            HiloEjecuta he = new HiloEjecuta();
//            he.start();
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
//            
//            if (this.minutoMundial<59){
//                this.minutoMundial++;
//            }else{
//                this.minutoMundial=0;
//                if (this.horaMundial <23)
//                    this.horaMundial++;
//                else {
//                    this.minutoMundial=0;
//                    this.horaMundial=0; 
//                }    
//            }
//            
//            if (this.minutoMundial== 0  && this.horaMundial%2==0){
//                HiloEjecuta t = new HiloEjecuta();
//                t.start();
//                System.out.println("ES LA HORA ->>>>>>");
//            }
//        
//    }
}
