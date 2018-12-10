/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Algoritmo.Aeropuerto;
import Algoritmo.Paquete;
import Algoritmo.TabuSearch;
import Modelo.paquete;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *
 * @author JUAN
 */
public class TabuSimulator extends Thread{
    private static Semaphore mutex = new Semaphore(1);
    private int horaMundial=0;
    private int minutoMundial=0;
    private int algoritmoDelayMinutes;
    private Date fechaActual;
    private TabuSearch tabu;
    private int manual = 0; // POR DEFECTO ES SIMULADOR
    
    public ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<>();
    private ArrayList<Algoritmo.Vuelo> listaVuelos = new ArrayList<>();

    private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgoAnterior = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgoFusion = new ArrayList<>();
    Hashtable<Integer, Algoritmo.Paquete> mapPackAlgo;
    private int tiempoAlgo; 
    public TabuSimulator(){
        
    }
    //PARA EL LOGIN
    public TabuSimulator(int hora,int min,Date fecha,TabuSearch tabu,ArrayList<Aeropuerto> listaAeropuertos,ArrayList<Algoritmo.Vuelo> listaVuelos,

        ArrayList<Algoritmo.Paquete> listPackAlgo,ArrayList<Algoritmo.Paquete> listPack, int tiempoAlgo, int algoritmoDelayMinutes){
        this.horaMundial=hora;
        this.minutoMundial=min;
        this.fechaActual=fecha;
        this.tabu=tabu;
        this.listaAeropuertos=(ArrayList<Aeropuerto>)listaAeropuertos.clone();
        this.listaVuelos=(ArrayList<Algoritmo.Vuelo>)listaVuelos.clone();

        //PACKS QUE YA TIENEN RUTA
        this.listPackAlgoAnterior=listPackAlgo;
        // TODOS LOS PACKS GENERADOS
        this.listPack=listPack; 
        
        this.tiempoAlgo = tiempoAlgo;
        this.algoritmoDelayMinutes = algoritmoDelayMinutes;
    }
    
    //PARA EL SIMULADOR
    public TabuSimulator(int hora,int min,Date fecha,TabuSearch tabu,ArrayList<Aeropuerto> listaAeropuertos,ArrayList<Algoritmo.Vuelo> listaVuelos,

        Hashtable<Integer, Algoritmo.Paquete> mapPackAlgo,ArrayList<Algoritmo.Paquete> listPack, int tiempoAlgo, int algoritmoDelayMinutes){
        this.horaMundial=hora;
        this.minutoMundial=min;
        this.fechaActual=fecha;
        this.tabu=tabu;
        this.listaAeropuertos=(ArrayList<Aeropuerto>)listaAeropuertos.clone();
        this.listaVuelos=(ArrayList<Algoritmo.Vuelo>)listaVuelos.clone();

        //PACKS QUE YA TIENEN RUTA
        //this.listPackAlgoAnterior=listPackAlgo;
        this.mapPackAlgo = (Hashtable<Integer, Algoritmo.Paquete>)mapPackAlgo.clone();
        // TODOS LOS PACKS GENERADOS
        this.listPack=listPack; 
        
        this.tiempoAlgo = tiempoAlgo;
        this.algoritmoDelayMinutes = algoritmoDelayMinutes;
    }
    
    void seleccionPacksAlgo(){
        
        this.listPackAlgo = new ArrayList<>();
        System.out.println("cant listPack = " + this.listPack.size());
        int timeFin = -1;
        int timeIni = -1;
        if (this.manual == 1){
            timeFin = tiempoAlgo; // TOPE DE BLOQUE
            timeIni = tiempoAlgo - this.algoritmoDelayMinutes; // INICIO DE BLOQUE
        }else{
            timeFin = tiempoAlgo + this.algoritmoDelayMinutes; // TOPE DE BLOQUE
            timeIni = tiempoAlgo; // INICIO DE BLOQUE
        }

        System.out.println("bloque -- t ini " + timeIni);
        System.out.println("bloque -- t fin " + timeFin);

        if (timeIni >= 0) {
            for (int i = 0; i < this.listPack.size(); i++) {
                Paquete pack = this.listPack.get(i);
                if (pack.getProcesado() == 0){ // SI ES UN PACK SIN PROCESAR
                    //tiempo de llegada del pack
                    int timePack = pack.getOriginHour() * 60 + pack.getOriginMin();
                    if(this.manual==1){
                        this.listPackAlgo.add(this.listPack.get(i));
                        // quita pack de lista original
                        //this.listPack.remove(i);
                        pack.setProcesado(1);
                    }else
                    //si se encuentra en el rango
                    if (timePack < timeFin && timePack >= timeIni) {
                        //System.out.println("bloque -- se añade pack");
                        // añade lista pack a procesar
                        this.listPackAlgo.add(this.listPack.get(i));
                        // quita pack de lista original
                        //this.listPack.remove(i);
                        pack.setProcesado(1);
                        //System.out.println(this.listPack.get(i).getOriginAirport() + "->" + this.listPack.get(i).getDestinyAirport());
                    }
                }
            }
            System.out.println("cant listPackAlgo f = " + this.listPackAlgo.size());
            //System.out.println("cant listPack f = " + this.listPack.size());
            System.out.println("tiempo ALGO = " + this.tiempoAlgo);      
        }
        if (this.tiempoAlgo < 1440) this.tiempoAlgo += algoritmoDelayMinutes;
        else
            if (this.tiempoAlgo >= 1440) this.tiempoAlgo = 0; 
    }
    
    //PARA EL SIMULADOR
    void seleccionPacksAlgoSimulador(){
        
        System.out.println("cant listPack = " + this.listPack.size());
        int timeFin = -1;
        int timeIni = -1;

        timeFin = tiempoAlgo + this.algoritmoDelayMinutes; // TOPE DE BLOQUE
        timeIni = tiempoAlgo; // INICIO DE BLOQUE

        System.out.println("bloque -- t ini " + timeIni);
        System.out.println("bloque -- t fin " + timeFin);

        if (timeIni >= 0) {
            for (int i = 0; i < this.listPack.size(); i++) {
                Paquete pack = this.listPack.get(i);
                if (pack.getProcesado() == 0){ // SI ES UN PACK SIN PROCESAR
                    //tiempo de llegada del pack
                    int timePack = pack.getOriginHour() * 60 + pack.getOriginMin();
                    //si se encuentra en el rango
                    if (timePack < timeFin && timePack >= timeIni) {
                        // añade lista pack a procesar
                        this.mapPackAlgo.put(pack.getIdentificator(), pack);
                        // quita pack de lista original
                        //this.listPack.remove(i);
                        pack.setProcesado(1);
                        //System.out.println(this.listPack.get(i).getOriginAirport() + "->" + this.listPack.get(i).getDestinyAirport());
                    }
                }
            }
            System.out.println("cant listPackAlgo f = " + this.mapPackAlgo.size());
            //System.out.println("cant listPack f = " + this.listPack.size());
            /*
            Iterator it = this.mapPackAlgo.keySet().iterator();
            while (it.hasNext()) {
                Integer key = (Integer)it.next();
                System.out.println("Clave: " + key + " -> Valor: " + this.mapPackAlgo.get(key).getIdentificator() + " time : " + this.mapPackAlgo.get(key).getOriginHour() + ":" +this.mapPackAlgo.get(key).getOriginMin() + " estado " + this.mapPackAlgo.get(key).getEstado() + " O : " +  this.mapPackAlgo.get(key).getOriginAirport()+ " D : " +  this.mapPackAlgo.get(key).getDestinyAirport());
            }*/
            
            System.out.println("tiempo ALGO = " + this.tiempoAlgo);      
        }
        if (this.tiempoAlgo < 1440) this.tiempoAlgo += algoritmoDelayMinutes;
        else
            if (this.tiempoAlgo >= 1440) this.tiempoAlgo = 0; 
    }
    
    
    
    public void runNormal() {

        try {
            //aplica algoritmo a un set de paquetes cada cierto delay en minutos de simulacion
            //if (this.cantTics == this.algoritmoDelayMinutes){
            //SE SELECCIONAN PACKS NUEVOS SEGUN VAN LLEGANDO
            seleccionPacksAlgo();
            System.out.println("cant de paquetes nuevos que aplicaran tabu - " + this.listPackAlgo.size());

            //SE JUNTAN LOS NUEVOS CON LOS QUE YA TIENEN RUTA
            // EL ALGORITMO SOLO BRINDA RUTA A LOS PACKS DISPONIBLES Y A LOS NUEVOS
            //this.listPackAlgo.addAll(this.listPack);
            this.listPackAlgoFusion.addAll(this.listPackAlgoAnterior);
            this.listPackAlgoFusion.addAll(this.listPackAlgo);

            System.out.println("cant de paquetes totales que aplicaran tabu - " + this.listPackAlgoFusion.size());
            if (this.listPackAlgoFusion.size() > 0) {
                //se van agregando las rutas segun se aplique el algoritmo
                //MUTEX
                mutex.acquire();
                System.out.println("ENTRO AL HILO");

                //OBTIENE RUTAS
                this.tabu.executeVCRPTabu(this.listPackAlgoFusion);

                mutex.release();

                //MUTEX OFF
            }

        } catch (Exception ex) {
            System.out.println("runNormal ERROR: " + ex.getMessage());
        }

    }
    
    public void runSimulator(){
        try {
            //aplica algoritmo a un set de paquetes cada cierto delay en minutos de simulacion
            //SE SELECCIONAN PACKS NUEVOS SEGUN VAN LLEGANDO
            seleccionPacksAlgoSimulador();
            //System.out.println("cant de paquetes nuevos que aplicaran tabu - " + this.listPackAlgo.size());

            //SE JUNTAN LOS NUEVOS CON LOS QUE YA TIENEN RUTA
            // EL ALGORITMO SOLO BRINDA RUTA A LOS PACKS DISPONIBLES Y A LOS NUEVOS

            System.out.println("cant de paquetes totales que aplicaran tabu - " + this.mapPackAlgo.size());
            if (this.mapPackAlgo.size() > 0) {
                //se van agregando las rutas segun se aplique el algoritmo
                //MUTEX
                mutex.acquire();
                System.out.println("ENTRO AL HILO");

                //OBTIENE RUTAS
                this.tabu.executeVCRPTabuSimulator(this.mapPackAlgo);

                mutex.release();

                //MUTEX OFF
            }

        } catch (Exception ex) {
            System.out.println("runSimulator ERROR: " + ex.getMessage());
        }
    }
    
    public void run(){   
        try{
            if (manual == 1)
                runNormal();
            else
                runSimulator();
        }
        catch(Exception ex ){
            System.out.println("run ERROR: "+ex.getMessage());
        }
      
        
    }

    public int getTiempoAlgo() {
        return tiempoAlgo;
    }

    public void setTiempoAlgo(int tiempoAlgo) {
        this.tiempoAlgo = tiempoAlgo;
    }

    public ArrayList<Paquete> getListPackAlgo() {
        return listPackAlgoFusion;
    }

    public void setListPackAlgo(ArrayList<Paquete> listPackAlgo) {
        this.listPackAlgoFusion = listPackAlgo;
    }

    public int getManual() {
        return manual;
    }

    public void setManual(int manual) {
        this.manual = manual;
    }

    public Hashtable<Integer, Paquete> getMapPackAlgo() {
        return mapPackAlgo;
    }

    public void setMapPackAlgo(Hashtable<Integer, Paquete> mapPackAlgo) {
        this.mapPackAlgo = mapPackAlgo;
    }
    
}
