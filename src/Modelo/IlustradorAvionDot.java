/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.CoordenadaDouble;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import static javax.swing.text.html.CSS.Attribute.FONT_SIZE;
import java.awt.Color;
import javafx.scene.Group;
import java.awt.Shape;
import java.awt.geom.Area;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import Algoritmo.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author JUAN
 */

public class IlustradorAvionDot extends JPanel implements ActionListener{
    static final int PIXELS_PER_POINT = 4; // 4x

// Image size in points
static final int IMAGE_WIDTH = 150;
static final int IMAGE_HEIGHT = 60;
// Font size in points
static final int FONT_SIZE = 11;

    private Timer t;
    static int  count = 0;
    private ArrayList<avionDot> avionesDot;
    static private double precision = 1;
    static private double velocidad = 1;
    private int horaMundial=0;
    private int minutoMundial=0;
    private int timeMS=8;
    private int cantDays = 0;
    private int cantTics = 0;
    private int inicio = 0;
    private int algoritmoDelayMinutes = 60*5;
    private ArrayList<String> Archivos = new ArrayList<>();
    
    private ArrayList<paquete> listaPaquetes = new ArrayList();
    private ArrayList<String> rutasPaquetes = new ArrayList();
    
    private ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<>();
    private ArrayList<Algoritmo.Vuelo> listaVuelos = new ArrayList<>();

    private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
    
    private Calendar calendar;
    
    private DataProcessing dp = new DataProcessing();
    private TabuSearch tabu = new TabuSearch();
    
        /**
     * @return the horaMundial
     */
    public int getHoraMundial() {
        return horaMundial;
    }

    /**
     * @param horaMundial the horaMundial to set
     */
    public void setHoraMundial(int horaMundial) {
        this.horaMundial = horaMundial;
    }

    /**
     * @return the minutoMundial
     */
    public int getMinutoMundial() {
        return minutoMundial;
    }

    /**
     * @param minutoMundial the minutoMundial to set
     */
    public void setMinutoMundial(int minutoMundial) {
        this.minutoMundial = minutoMundial;
    }
    /**
     * @return the t
     */
    public Timer getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Timer t) {
        this.t = t;
    }

    /**
     * @return the precision
     */
    public static double getPrecision() {
        return precision;
    }

    /**
     * @param aPrecision the precision to set
     */
    public static void setPrecision(double aPrecision) {
        precision = aPrecision;
    }

    /**
     * @return the velocidad
     */
    public static double getVelocidad() {
        return velocidad;
    }

    /**
     * @param aVelocidad the velocidad to set
     */
    public static void setVelocidad(double aVelocidad) {
        velocidad = aVelocidad;
    }

    
    
    public IlustradorAvionDot(ArrayList<avionDot> avionesDot,ArrayList<Aeropuerto> aero,ArrayList<Algoritmo.Vuelo> vuelo){
        t= new Timer(timeMS,this);
        this.avionesDot = avionesDot;
        this.listaVuelos = vuelo;
        this.listaAeropuertos = aero;
        Calendar calendario = Calendar.getInstance();
        this.calendar = calendario;
    }
    static int toPixels(int value) {
    return value * PIXELS_PER_POINT;
}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        //int sizeList=destiny.size();
        Font font = new Font("Arial", Font.PLAIN, toPixels(FONT_SIZE));
        g2.setFont(font);
        g2.drawString(String.valueOf(horaMundial), 700, 80);
        g2.drawString(" :"+String.valueOf(minutoMundial), 732, 80);
        
        
        //this.calendar.add(this.calendar.DATE,1);
        Date diaNuevo = this.calendar.getTime();
        
        String newYear = Integer.toString(diaNuevo.getYear() + 1900);
        String newMonth = Integer.toString(diaNuevo.getMonth());
        String newDay = Integer.toString(diaNuevo.getDate());
        
        g2.drawString(" " + newDay + "/" + newMonth + "/" +newYear,700,35);
        
        //g2.fill(new Shape("Holamundo"));
        ArrayList<Shape>arrayEllipse=new ArrayList<>();
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            double t_min = abs((this.avionesDot.get(i).getHora_llegada()*60 + this.avionesDot.get(i).getMin_llegada()) -
                       (this.avionesDot.get(i).getHora_salida()*60 + this.avionesDot.get(i).getMin_salida()));
            
            double xIni=this.avionesDot.get(i).getActual().getX();
            double yIni=this.avionesDot.get(i).getActual().getY();
            
            double xFin=this.avionesDot.get(i).getDestino().getX();
            double yFin=this.avionesDot.get(i).getDestino().getY();
            
            String color = this.avionesDot.get(i).getColor();
            
            switch (color){
                case "negro":
                    g2.setPaint(new Color (1,1,1));
                    break;
                case "rojo":
                    g2.setPaint(new Color (238, 54, 63));
                    break;
                case "amarillo":
                    g2.setPaint(new Color (234, 203, 29));
                    break;
                case "verde":
                    g2.setPaint(new Color (106, 203, 29));
                    break;
            }
            
            double dx,dy;
            

           
                  
            Shape avion = new Ellipse2D.Double(xIni,yIni,4,4);
            g2.fill(avion); //pinta avion
             
            arrayEllipse.add(avion);
            
            g2.fill(arrayEllipse.get(i)); // dibuja puntito
            dx=xFin-xIni;
            dy=yFin-yIni;
            //length=Math.sqrt(Math.pow(dx, 2.0)+Math.pow(dy, 2.0));
            //dx/=length;
            //dy/=length;
            dx=(dx/t_min);
            dy=(dy/t_min);
            this.avionesDot.get(i).setvX(dx*getVelocidad());
            this.avionesDot.get(i).setvY(dy*getVelocidad());
        }
        getT().start();
    }

    
    public void cambiaEstadoMov(avionDot v){
        
        // VERIFICAR HORA DE SALIDA DEL AVION
        if (v.getEstado_mov() == 0){
            if (this.horaMundial*60 + this.minutoMundial == v.getHora_salida()*60 + v.getMin_salida()){
                v.setEstado_mov(1);// en transito
                
                //verificar si tiene paquetes que recojer
                for(int i = 0; i < this.rutasPaquetes.size();i++){
                    String ruta = this.rutasPaquetes.get(i);
                    
                    if (ruta.equals("")){ // si la ruta esta vacia se quita de rutasPaquetes
                        this.rutasPaquetes.remove(i);
                    }else{
                        //System.out.println(ruta);  
                        String[] ids = ruta.split("-");
                        int idVuelo = Integer.parseInt(ids[0]);
                        if (idVuelo == v.getId()){//si el paquete debe entrar a ese vuelo
                            
                            
                            v.setCapacidadActual(v.getCapacidadActual() + 1); // se mete paquete en avion
                            
                            for(Aeropuerto aero : this.listaAeropuertos){
                                if (aero.getIcaoCode().equals(v.getIcaoOrigen())){
                                    aero.setCapActual(aero.getCapActual() - 1); // se quita el paquete del aeropuerto
                                    break;
                                }
                            }
                            
                            if (ids.length == 1){
                                ruta.equals(""); // se quita el paso dado
                            }else{
                                ruta = ruta.substring(ruta.indexOf("-", 0)+1,ruta.length()); // se quita el paso dado
                            }
                            
                            this.rutasPaquetes.set(i, ruta);
                        }
                    }

                    
                }
                    
                
            }
        }
    }
    
    public void cambiaEstadoAlmacen(avionDot v){
        if(v.getCapacidadActual()>v.getCapacidadMax()){
            v.setColor("negro");
            v.setEstado_almacen(3);
        }else if(v.getCapacidadActual()>(2*v.getCapacidadMax()/3)){
            v.setColor("rojo");
            v.setEstado_almacen(2);
        }else if(v.getCapacidadActual()>(v.getCapacidadMax()/3)){
            v.setColor("amarillo");
            v.setEstado_almacen(1);
        }else{
            v.setColor("verde");
            v.setEstado_almacen(0);
        }
    }
    
    public void mueveAvion(avionDot v){
        
        double xIni = v.getActual().getX();
        double xFin = v.getDestino().getX();

        double yIni = v.getActual().getY();
        double yFin = v.getDestino().getY();
        
        if (xIni != xFin || yIni != yFin){
        
            if (xIni != xFin)
                v.getActual().setX(xIni+v.getvX());
            
            if (yIni != yFin)
                v.getActual().setY(yIni+v.getvY());
             
        }
        
        double xOrig = v.getOrigen().getX();
        double yOrig = v.getOrigen().getY();
        double xDest = v.getDestino().getX();
        double yDest = v.getDestino().getY();
        double xAct = v.getActual().getX();
        double yAct = v.getActual().getY();

        if ((abs(xAct) - abs(xDest)) <= getPrecision() && (abs(yAct) - abs(yDest)) <= getPrecision()) {

            v.getActual().setX(v.getOrigen().getX());
            v.getActual().setY(v.getOrigen().getY());
            v.setEstado_mov(0); // se para
        }
        
        //si ya es la hora de llegada se situa en el destino
        if (this.horaMundial*60 + this.minutoMundial == v.getHora_llegada()*60 + v.getMin_llegada()){
            v.getActual().setX(v.getDestino().getX());
            v.getActual().setY(v.getDestino().getY());
            // EL AVION LLEGA Y VACIA ALMACEN Y DEJA PAQUETES EN EL AEROPUERTO
            for(Aeropuerto aero : this.listaAeropuertos){
                if(aero.getIcaoCode().equals(v.getIcaoDestino())){
                    aero.setCapActual(aero.getCapActual() + v.getCapacidadActual());
                    
                    //VERIFICAR COLAPSO POR FALTA DE ESPACIO EN ALMACEN DE AEROPUERTO
                    if (aero.getCapActual() > aero.getCapMax()){
                        // SISTEMA COLAPSA
                        System.out.println("------------------------->COLAPSO<-----------------------");
                        
                    }
                    break;
                }
            }
            v.setCapacidadActual(0);
            
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
    
    void seleccionPacksAlgo(){
        this.listPackAlgo.clear();
        //hora max a partir de hora mundial
        int timeMM = this.horaMundial * 60 + this.minutoMundial + this.algoritmoDelayMinutes;
        int timeANT = this.horaMundial * 60 + this.minutoMundial - this.algoritmoDelayMinutes;
        if (timeANT < 0) timeANT = 0;
        
        Date fechaActual = this.calendar.getTime();
        
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
    
    void lecturaData(){
        
        if (this.inicio == 0){
            
            
            
            
            final File folder = new File("resources\\pack_enviados");
            listFilesForFolder(folder);
            

            
            this.tabu.setListAirport(this.listaAeropuertos);
            this.tabu.setListFlight(this.listaVuelos);
            this.tabu.generateFlightMatrix();
            
            this.dp.setListAirport(this.listaAeropuertos);

            this.tabu.setInputProcess(this.dp);
            for (String a : this.Archivos){
                dp.processPackNew("resources\\pack_enviados\\" + a);
            }
            
            System.out.println("cant total de paquetes - " + this.dp.getPackList().size());
            
            
            this.listPack = this.dp.getPackList();
           
            if (this.listPack.size()>0)//se coloca la fecha del primer pack como fecha del simulador
                this.calendar.set(this.listPack.get(0).getOriginYear(),this.listPack.get(0).getOriginMonth() - 1,this.listPack.get(0).getOriginDay());
            
            this.inicio = 1;
        }
        
        
        //aplica algoritmo a un set de paquetes cada cierto delay en minutos de simulacion
        if (this.cantTics == this.algoritmoDelayMinutes){
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

            this.cantTics = 0;
        }
    }
    
    
    public void actionPerformed(ActionEvent e){
        if (this.minutoMundial<59){
            this.minutoMundial++;
        }else{
            this.minutoMundial=0;
            if (this.horaMundial <23)
                this.horaMundial++;
            else {
                this.minutoMundial=0;
                this.horaMundial=0; 
            }    
        }
        
        if (this.minutoMundial == 0 && this.horaMundial == 0){
            this.calendar.add(this.calendar.DATE,1);
            this.cantDays++;
        }
        
        lecturaData();
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            //if (i == 1) break;
            
            cambiaEstadoMov(this.avionesDot.get(i));
            
            if (this.avionesDot.get(i).getEstado_mov() == 1){
                cambiaEstadoAlmacen(this.avionesDot.get(i));
                mueveAvion(this.avionesDot.get(i));
            
            }
        }

        repaint();
        this.cantTics++;
    }

   
}


