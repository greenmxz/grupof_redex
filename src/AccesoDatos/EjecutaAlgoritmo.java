/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Algoritmo.DataProcessing;
import Algoritmo.Paquete;
import Algoritmo.TabuSearch;
import Controlador.PaqueteBL;
import Vista.TabuSimulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author JUAN
 */
public class EjecutaAlgoritmo extends Thread{
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
     private TabuSearch ts=new TabuSearch();
     
     public EjecutaAlgoritmo(TabuSearch tabu){
         ts=tabu;
         ts.setListPack(new ArrayList<Paquete>());
     }
    public void run(){
//        t = new Timer(8,this);
//        t.start();
        //while(true){
        calendar=Calendar.getInstance();
        PaqueteBL controladorPaquete= new PaqueteBL();    
        try {
            System.out.println("AQUI implementare algoritmo");
            ArrayList<Modelo.paquete>listaPack=controladorPaquete.obtenerPaquetes();
            ArrayList<Algoritmo.Paquete>listaPackNew=new ArrayList<>();
            int size=listaPack.size();
            for(int i=0;i<size;i++){
                int origenId=listaPack.get(i).getAeropuertoOrigenId();
                int destinoId=listaPack.get(i).getAeropuertoDestinoId();
                int origenHour=listaPack.get(i).getFechaSalida().getHours();
                int origenMin=listaPack.get(i).getFechaSalida().getMinutes();
                int origenDay=listaPack.get(i).getFechaSalida().getDay();
                int origenMonth=listaPack.get(i).getFechaSalida().getMonth();
                int origenYear=listaPack.get(i).getFechaSalida().getYear();
                Algoritmo.Paquete pack=new Algoritmo.Paquete(origenHour,origenMin,origenId,destinoId,origenDay,origenMonth,origenYear);
                listaPackNew.add(pack);
            }
            
            ts.setListPack(listaPackNew);
            //ts.executeVCRPTabu(listPack);
            TabuSimulator simulador=new TabuSimulator(horaMundial,minutoMundial,calendar.getTime(),ts,ts.getListAirport(),ts.getListFlight(),listPackAlgo,ts.getListPack(),tiempoAlgoMM,tiempoDelayAlgoritmo);
            simulador.start();
            this.listPackAlgo = simulador.getListPackAlgo();
            size=listPackAlgo.size();
            System.out.println(size);
            for(int i=0;i<size;i++){
                System.out.println(listPackAlgo.get(i).getRuta());
            }
            cambiaEstadoPacks();
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
    public void cambiaEstadoPacks(){
         try{               
            //SE ACTUALIZA ESTADO DE PAQUETES SEGUN LA HORA ACTUAL (LLEGADA A AEROPUERTO DE ORIGEN)
            //System.out.println("cambiaEstadoPacks listPackAlgo ->  " + this.listPackAlgo.size());
            for (int iter = 0; iter < this.listPackAlgo.size(); iter++) {
                Paquete p = this.listPackAlgo.get(iter);
                //CAMBIA EL ESTADO DE 0: NO DISPONIBLE A 1: DISPONIBLE SI TIENEN RUTA POR RECORRER
                if (!p.getRuta().equals("") && p.getEstado() == 0) {
                    
                    int tiempoActual = this.horaMundial * 60 + this.minutoMundial;
                    int tiempoPack = p.getOriginHour() * 60 + p.getOriginMin();

                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Calendar calendarioPack = Calendar.getInstance();
                    calendarioPack.set(p.getOriginYear(),p.getOriginMonth()-1,p.getOriginDay());

                    Date fechaPack = calendarioPack.getTime();
                    Date fechaActual = this.calendar.getTime();

                    //System.out.println("fecha_pack  " + fechaPack);
                    //System.out.println("fecha_act " + fechaActual);
                    
                    //pertenece al dia de hoy
                    if (dateFormat.format(fechaActual).compareTo(dateFormat.format(fechaPack))==0){
                        // es su hora de llegada al origen
                        if (tiempoActual == tiempoPack){
                            p.setEstado(1); //paquete ahora esta disponible
                            //System.out.println("pack - " + p.getRuta());
                            
                            /*
                            for(int i=0;i<this.listaAeropuertos.size();i++)
                                if(this.listaAeropuertos.get(i).getCountry().equals("Belgica"))
                                      System.out.println(this.listaAeropuertos.get(i).getCountry()+" "+this.listaAeropuertos.get(i).getCapActual());
                            */
                            
                            //SE AUMENTA EN 1 LA CAPACIDAD ACTUAL DEL AEROPUERTO ORIGEN
                            this.ts.getListAirport().get(p.getOriginAirport()-1).setCapActual(this.ts.getListAirport().get(p.getOriginAirport()-1).getCapActual() + 1);
                            //this.cantPacksEntrantes++;
                            /*
                            for(int i=0;i<this.listaAeropuertos.size();i++)
                                if(this.listaAeropuertos.get(i).getCountry().equals("Belgica"))
                                   System.out.println(this.listaAeropuertos.get(i).getCountry()+" "+this.listaAeropuertos.get(i).getCapActual());
                            */
                        }
                    }
                }
            }
        }catch(Exception ex){
           System.out.println("ERROR cambiaEstadoPacks " + ex.getLocalizedMessage());
           ex.printStackTrace();
       }
    }


}
