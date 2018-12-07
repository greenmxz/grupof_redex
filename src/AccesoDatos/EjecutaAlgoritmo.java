/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Algoritmo.DataProcessing;
import Algoritmo.Paquete;
import Algoritmo.TabuSearch;
import Controlador.AdministrarClienteBL;
import Controlador.AdministrarPedidoBL;
import Controlador.PaqueteBL;
import Modelo.cliente;
import Modelo.usuario;
import Vista.Login;
import Vista.MailWorkerTest;
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
     private int tiempoAlgoMM;
     private int tiempoDelayAlgoritmo=60*2;
     private DataProcessing dp = new DataProcessing();
     private ArrayList<String> Archivos = new ArrayList<>();
     private TabuSearch ts=new TabuSearch();
     private ArrayList<Algoritmo.Paquete>listaPackNew;

    
     public EjecutaAlgoritmo(TabuSearch tabu,ArrayList<Algoritmo.Paquete>listPackNew,int t,int h,int m){
         
         ts=tabu;
         //ts.setListPack(new ArrayList<Paquete>());
         //this.arrUsuario=(ArrayList<usuario>) arrUsuario.clone();
         horaMundial=h;
         minutoMundial=m;
         this.tiempoAlgoMM = t;
         this.listaPackNew=listPackNew;
     }

    public ArrayList<Paquete> getListPackAlgo() {
        return listPackAlgo;
    }

    public void setListPackAlgo(ArrayList<Paquete> listPackAlgo) {
        this.listPackAlgo = listPackAlgo;
    }
     
    public void run(){
//        t = new Timer(8,this);
//        t.start();
        //while(true){
        calendar=Calendar.getInstance();
        
           
        try {
            System.out.println("AQUI implementare algoritmo");
            
            ts.setListPack(this.listaPackNew);
            
            TabuSimulator simulador=new TabuSimulator(horaMundial,minutoMundial,calendar.getTime(),ts,ts.getListAirport(),ts.getListFlight(),listPackAlgo,ts.getListPack(),tiempoAlgoMM,tiempoDelayAlgoritmo);
            simulador.setManual(1);
            simulador.start();
            simulador.join();
            this.listPackAlgo = simulador.getListPackAlgo();
            int size=listPackAlgo.size();
            System.out.println(size);
            for(int i=0;i<size;i++){
                System.out.println(listPackAlgo.get(i).getRuta());
            }
            cambiaEstadoPacks();

            //MailWorkerTest notificadorEmail=new MailWorkerTest(correo,"sdfdf");
            
//            for(int i=0;i<size;i++){
//                if(listPackAlgo.get(i).getEstado()==1){
//                    System.out.println(listPackAlgo.get(i).getRuta());
//                }
//            }
            
            
            
            this.tiempoAlgoMM = simulador.getTiempoAlgo();
            this.finalize();
            //for(int i=0;i<size;i++)
                //System.out.println(listPackAlgo.get(i).getRuta());
            
            //this.finalize();

            
            
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
    

    public int getTiempoAlgoMM() {
        return tiempoAlgoMM;
    }

    public void setTiempoAlgoMM(int tiempoAlgoMM) {
        this.tiempoAlgoMM = tiempoAlgoMM;
    }


}
