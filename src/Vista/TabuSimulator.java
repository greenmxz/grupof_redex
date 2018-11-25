/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Algoritmo.Aeropuerto;
import Algoritmo.TabuSearch;
import Modelo.paquete;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author JUAN
 */
public class TabuSimulator extends Thread{
    private int horaMundial=0;
    private int minutoMundial=0;
    private int algoritmoDelayMinutes = 60*5;
    private Date fechaActual;
    private TabuSearch tabu;
    private ArrayList<TabuSearch>eliminenlo;
    private ArrayList<paquete> listaPaquetes = new ArrayList();
    public ArrayList<String> rutasPaquetes = new ArrayList();
    
    public ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<>();
    private ArrayList<Algoritmo.Vuelo> listaVuelos = new ArrayList<>();

    private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
    
    public TabuSimulator(int hora,int min,Date fecha,TabuSearch tabu,ArrayList<Aeropuerto> listaAeropuertos,ArrayList<Algoritmo.Vuelo> listaVuelos,
            ArrayList<paquete> listaPaquetes,ArrayList<String> rutasPaquetes,ArrayList<Algoritmo.Paquete> listPack){
        this.horaMundial=hora;
        this.minutoMundial=min;
        this.fechaActual=fecha;
        this.tabu=tabu;
        this.listaAeropuertos=listaAeropuertos;
        this.listaVuelos=listaVuelos;
        this.listaPaquetes=listaPaquetes;
        this.rutasPaquetes=rutasPaquetes;
        this.listPack=listPack;
    }
    void seleccionPacksAlgo(){
        this.listPackAlgo.clear();
        //hora max a partir de hora mundial
        int timeMM = this.horaMundial * 60 + this.minutoMundial + this.algoritmoDelayMinutes;
        int timeANT = this.horaMundial * 60 + this.minutoMundial - this.algoritmoDelayMinutes;
        if (timeANT < 0) timeANT = 0;
        
        
        
        for(int i = 0; i < this.listPack.size(); i++){
            
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            Calendar calendarioPack = Calendar.getInstance();
            calendarioPack.set(this.listPack.get(i).getOriginYear(),this.listPack.get(i).getOriginMonth()-1,this.listPack.get(i).getOriginDay());
            
            Date fechaPack = calendarioPack.getTime();
                       
            if (dateFormat.format(fechaActual).compareTo(dateFormat.format(fechaPack))==0){//pertenecen al dia de hoy
                int timePack = this.listPack.get(i).getOriginHour()*60 + this.listPack.get(i).getOriginMin();
                if (timePack <= timeMM && timePack > timeANT){
                    this.listPackAlgo.add(this.listPack.get(i));
                    //System.out.println(this.listPack.get(i).getOriginAirport() + "->" + this.listPack.get(i).getDestinyAirport());
                }
            }
            
            
        }
        
    }
    public void run(){    
        //aplica algoritmo a un set de paquetes cada cierto delay en minutos de simulacion
        //if (this.cantTics == this.algoritmoDelayMinutes){
        seleccionPacksAlgo();
        System.out.println("cant de paquetes que aplicaran tabu - " + this.listPackAlgo.size());
        if (this.listPackAlgo.size() > 0){
            //se van agregando las rutas segun se aplique el algoritmo
            ArrayList<String> rutasPacksTrabajados = this.tabu.executeVCRPTabu(this.listPackAlgo);
            if (rutasPacksTrabajados.size() > 0){
                this.rutasPaquetes.addAll(rutasPacksTrabajados);
                //se llenan los almacenes con los paquetes nuevos
                for (String ruta : rutasPacksTrabajados){
                    String[] ids = ruta.split("-");
                    if(!ruta.equals("")){
                        int idVuelo = Integer.parseInt(ids[0]);

                        int idAero = this.listaVuelos.get(idVuelo-1).getOriginAirport();

                        this.listaAeropuertos.get(idAero-1).setCapActual(this.listaAeropuertos.get(idAero-1).getCapActual() + 1);
                    }

                }
            }
        }

            
        
    }
    
}
