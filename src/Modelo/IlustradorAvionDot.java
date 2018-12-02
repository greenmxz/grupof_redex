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
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import Algoritmo.*;
import Vista.TabuSimulator;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

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
    private int inicioAlgo = 1;
    private int algoritmoDelayMinutes = 60*2;
    private int tiempoAlgoMM = 0;
    private ArrayList<String> Archivos = new ArrayList<>();
    private int cantMaxColapsoAero = 0;
    private String aeroColapsoMax = "";
    private int cantMaxColapsoAvion = 0;
    private int avionColapsoMax = -1;
    private int cantPacksEntrantes = 0;
    private int cantPacksSalientes = 0;
   
    //private ArrayList<String> rutasPaquetes = new ArrayList();

    private ArrayList<Algoritmo.Paquete> rutasPaquetesAlgo = new ArrayList();

    
    private ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<>();
    private ArrayList<Algoritmo.Vuelo> listaVuelos = new ArrayList<>();

    private ArrayList<Algoritmo.Paquete> listPack = new ArrayList<>();
    private ArrayList<Algoritmo.Paquete> listPackAlgo = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Paquete>>> matrixPackXDay = new ArrayList<ArrayList<ArrayList<Paquete>>>();
    private Calendar calendar;
    
    private DataProcessing dp = new DataProcessing();
    private TabuSearch tabu = new TabuSearch();
    private Image img;
    final static BasicStroke stroke = new BasicStroke(2.0f);   
    
    
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
        lecturaData();
    }
    static int toPixels(int value) {
    return value * PIXELS_PER_POINT;
}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2=(Graphics2D)g;
        img=new ImageIcon("src\\Resource\\mapaMundi.PNG").getImage();
        g.drawImage(img, 0, 0, this);
        //int sizeList=destiny.size();
        Font font = new Font("Arial", Font.PLAIN, toPixels(FONT_SIZE));
        g2.setFont(font);
        if(horaMundial>=0 && horaMundial<10)
            g2.drawString("0"+String.valueOf(horaMundial), 800, 80);
        else
            g2.drawString(String.valueOf(horaMundial), 800, 80);
        if(minutoMundial>=0 && minutoMundial<10)
            g2.drawString("  : 0"+String.valueOf(minutoMundial), 832, 80);
        else
            g2.drawString("  : "+String.valueOf(minutoMundial), 832, 80);
        ArrayList<Shape>arrayEllipse=new ArrayList<>();
        
        //this.calendar.add(this.calendar.DATE,1);
        Date diaNuevo = this.calendar.getTime();
        
        String newYear = Integer.toString(diaNuevo.getYear() + 1900);
        String newMonth = Integer.toString(diaNuevo.getMonth());
        String newDay = Integer.toString(diaNuevo.getDate());
        
        g2.drawString(" " + newDay + "/" + newMonth + "/" +newYear,780,35);
        
        //g2.fill(new Shape("Holamundo"));
       
        int numAero=this.listaAeropuertos.size();
           
        
        for(int i=0;i<numAero;i++){
            double x=this.listaAeropuertos.get(i).getCoordX();
            double y=this.listaAeropuertos.get(i).getCoordY();
            g2.setPaint(new Color(255,251,0));
            g2.setStroke(stroke);
            Shape aeropuertoElipse = new Ellipse2D.Double(x-10,y-10,10,10);
            g2.fill(aeropuertoElipse);
            
            //g2.setStroke(new BasicStroke(0.0f));
            Rectangle2D aeropuerto = new Rectangle2D.Double(x-10,y-10,7,7);
            
            String color = this.listaAeropuertos.get(i).getColor();
            
            switch (color){
                case "negro":
                    g2.setPaint(new Color (1,1,1));
                    break;
                case "rojo":
                    g2.setPaint(new Color (242, 99, 70));
                    break;
                case "amarillo":
                    g2.setPaint(new Color (203, 201, 14));
                    break;
                case "verde":
                    g2.setPaint(new Color (117, 239, 37));
                    break;
            }
            g2.fill(aeropuerto);
        }
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            double t_min = abs((this.avionesDot.get(i).getHora_llegada()*60 + this.avionesDot.get(i).getMin_llegada()) -
                       (this.avionesDot.get(i).getHora_salida()*60 + this.avionesDot.get(i).getMin_salida()));
            
              
            //double t_min=this.avionesDot.get(i).getT_restante();
            
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
            //g2.fill(avion); //pinta avion
             
            arrayEllipse.add(avion);
            
            g2.fill(arrayEllipse.get(i)); // dibuja puntito
            dx=xFin-xIni;
            dy=yFin-yIni;
            double length=Math.sqrt(Math.pow(dx, 2.0)+Math.pow(dy, 2.0));
            //dx/=length;
            //dy/=length;
            dx=(dx/t_min);
            dy=(dy/t_min);
            this.avionesDot.get(i).setvX(dx*getVelocidad());
            this.avionesDot.get(i).setvY(dy*getVelocidad());
            //this.avionesDot.get(i).setT_restante(t_min-1);
        }
        getT().start();
    }

    
    public void cambiaEstadoMov(avionDot v){
        
        // VERIFICAR HORA DE SALIDA DEL AVION
        if (v.getEstado_mov() == 0){
            int tiempoSalidaAvion = v.getHora_salida()*60 + v.getMin_salida();
            if (this.horaMundial*60 + this.minutoMundial == tiempoSalidaAvion){
                v.setEstado_mov(1);// en transito
                
                //verificar si tiene paquetes que recojer
                for(int i = 0; i < this.listPackAlgo.size();i++){
                    String ruta = this.listPackAlgo.get(i).getRuta();
                    // verifica si tiene camino por recorrer
                    if (!ruta.equals("")){
                        //System.out.println(ruta);
                        
                        int tiempoPack = this.listPackAlgo.get(i).getOriginHour()*60 + this.listPackAlgo.get(i).getOriginMin();
                        
                        
                        String[] ids = ruta.split("-");
                        int idVuelo = Integer.parseInt(ids[0]);
                        
                        //SI EL ES EL VUELO QUE REQUIERE EL PAQUETE, TIEMPO COINCIDE, EL PAQUETE ESTA DISPONIBLE Y HAY ESPACIO EN EL AVION
                        if (idVuelo == v.getId() && tiempoSalidaAvion >= tiempoPack && this.listPackAlgo.get(i).getEstado() == 1 && v.getCapacidadActual() < v.getCapacidadMax()){
                            
                            // se mete paquete en avion
                            v.setCapacidadActual(v.getCapacidadActual() + 1); 
                            
                            
                            
                            
                            //Se busca aeropuerto origen para quitar el paquete
                            Aeropuerto aero = this.listaAeropuertos.get(v.getIdAeroOrigen()-1);
                            if(aero.getCapActual() > 0)
                                aero.setCapActual(aero.getCapActual() - 1); // se quita el paquete del aeropuerto
                            else
                                System.out.println("ERROR AQUI");
                      
                                    
                           //System.out.println("AQUI EJEAJEAJ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ids.length);
                            if (ids.length == 1){// es su ultimo paradero
                                this.listPackAlgo.get(i).setEsFinal(1);
                                //COMPROBAR COLAPSO POR TIEMPO
                                int tiempoSalida = v.getHora_salida() * 60 + v.getMin_salida();
                                int tiempoLlegada = v.getHora_llegada() * 60 + v.getMin_llegada();
                                

                                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");                  
                                Calendar calendarioLlegada = Calendar.getInstance();
                                
                                //SI EL TIEMPO DE LLEGADA ES MENOR AL DE SALIDA ENTONCES LLEGA AL DIA SIGUIENTE
                                if (tiempoLlegada < tiempoSalida)
                                    calendarioLlegada.add(Calendar.DATE, 1);
                                
                                //FECHA DE LLEGADA DEL PACK
                                Date fechaLlegada= this.calendar.getTime();
                                //FECHA DE DEBERIA LLEGAR EL PACK
                                calendarioLlegada = Calendar.getInstance();
                                //SI ES CONTINENTAL
                                if (this.listaAeropuertos.get(this.listPackAlgo.get(i).getOriginAirport()-1) == this.listaAeropuertos.get(this.listPackAlgo.get(i).getDestinyAirport()-1)){
                                    calendarioLlegada.add(Calendar.DATE, 1); // A LO MUCHO EN 24HRS
                                }else{//SI ES INTERCONTINENTAL
                                    calendarioLlegada.add(Calendar.DATE, 2); // A LO MUCHO EN 48HRS
                                }
                                
                                Date fecha_LlegadaMax = this.calendar.getTime();
                                //SI LLEGA DESPUES DE LA FECHA MAXIMA COLAPSA
                                
                                if(dateFormat.format(fechaLlegada).compareTo(dateFormat.format(fecha_LlegadaMax))==1){
                                    System.out.println("--------COLAPSO POR TIEMPO DE LLEGADA ------");
                                    System.out.println("fecha estipulada " + fechaLlegada);
                                    System.out.println("fecha llegada " + fecha_LlegadaMax);
                                }
                                
                                
                                //calendarioLlegada.set(this.listPack.get(i).getOriginYear(),this.listPack.get(i).getOriginMonth() - 1,this.listPack.get(i).getOriginDay());
                                
                                //////////////////////////////// 
                                // es su ruta final, remueve de listapack
                                //this.listPackAlgo.remove(i);// no se necesita el id del pack ya que es su ultimo movimiento y no necesita cambiar de estado
                                v.getIdPacks().add(i);
                                v.setPack_finales(v.getPack_finales() + 1);
                                this.listPackAlgo.get(i).setRuta(""); // su ruta se encuestra finalizada
                                //if(v.getId()==1505)
                                    //System.out.println("Cantidad de pack_finales: "+v.getPack_finales() + " - " + v.getId());
                            }else{ // vuelos escalados
                                // se quita el paso dado
                                ruta = ruta.substring(ruta.indexOf("-", 0)+1,ruta.length());
                                // se brinda la nueva ruta
                                this.listPackAlgo.get(i).setRuta(ruta);
                                // cambia a estado en transito para que no se vuelva a usar hasta que llegue
                                this.listPackAlgo.get(i).setEstado(2);
                                this.listPackAlgo.get(i).setNuevo(0);
                                v.getIdPacks().add(i);
                            }
                            
                            
                        }
                    }

                    
                }
                //if (v.getCapacidadActual() > 0)
                    //System.out.println(">>>>>>>>>>> vuelo salida " + v.getId() + " -- " + v.getCapacidadActual());    
                if (v.getCapacidadActual() > v.getCapacidadMax()){
                    System.out.println("------------ COLAPSO POR VUELO----vuelo  " + v.getId() + "  lleno");
                    if (this.cantMaxColapsoAvion < v.getCapacidadActual()){
                        this.cantMaxColapsoAvion = v.getCapacidadActual();
                        this.avionColapsoMax = v.getId();
                    }
                }
            }
        }
    }
    public void cambiaColorAeropuerto(Aeropuerto aero){
        if(aero.getCapActual()>aero.getCapMax()){
            aero.setColor("negro");
           
        }else if(aero.getCapActual()>(2*aero.getCapMax()/3)){
            aero.setColor("rojo");
            
        }else if(aero.getCapActual()>(aero.getCapMax()/3)){
            aero.setColor("amarillo");
            
        }else{
            aero.setColor("verde");
            
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
//        if(v.getCapacidadActual()!=0)
//            System.out.println(v.getId()+" -> " + v.getCapacidadActual());
    }
    
    
    public void calculaMovAvion(avionDot v){
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
            v.setEstado_mov(0); // EL AVION PARA EN SU DESTINO
        }
    }
    
    public void mueveAvion(avionDot v){
        
        
        calculaMovAvion(v);
        
        
        
        //SI ES LA HORA DE LLEGADA DEL AVION
        if (this.horaMundial*60 + this.minutoMundial == v.getHora_llegada()*60 + v.getMin_llegada()){
            //if (v.getCapacidadActual() > 0)
                //System.out.println(">>>>>>>>>>> vuelo llega " + v.getId() + " -- " + v.getCapacidadActual());
            v.getActual().setX(v.getDestino().getX());
            v.getActual().setY(v.getDestino().getY());
            // EL AVION LLEGA Y VACIA ALMACEN Y DEJA PAQUETES EN EL AEROPUERTO
            Aeropuerto aero = this.listaAeropuertos.get(v.getIdAeroDestino()-1);
            //for(Aeropuerto aero : this.listaAeropuertos){
                //if(aero.getIcaoCode().equals(v.getIcaoDestino())){
                    //capacidad el aero se actualiza
                    aero.setCapActual(aero.getCapActual() + v.getCapacidadActual());
                    //
                    //if(aero.getCountry().equals("Belgica"))
                    //        System.out.println(aero.getIdentificator()+" "+aero.getCapActual()+ " <- "+v.getCapacidadActual());
                    //
                    cambiaColorAeropuerto(aero);
                    //VERIFICAR COLAPSO POR FALTA DE ESPACIO EN ALMACEN DE AEROPUERTO
                    if (aero.getCapActual() > aero.getCapMax()){
                        // SISTEMA COLAPSA
                        System.out.println("------------------------->COLAPSO<-----------------------");
                        System.out.println(" -- " + " "+aero.getIdentificator()+" "+aero.getCountry()+" "+aero.getCapActual());
                        if (this.cantMaxColapsoAero < aero.getCapActual()){
                            this.cantMaxColapsoAero = aero.getCapActual();
                            this.aeroColapsoMax = aero.getCountry();
                        }
                        
                    }
                    
                    //if(aero.getCountry().equals("Belgica"))
                    //    System.out.println("Cantidad de pack_finales: "+aero.getCountry()+" "+aero.getCapActual()+" "+v.getPack_finales() + " - " + v.getId() + " "+ v.getCapacidadActual());
                    
                    //ACTUALIZA ESTADOS DE PAQUETES QUE LLEGAN
                    
                    //cliente recoge sus packs
                    aero.setCapActual(aero.getCapActual() - v.getPack_finales());
                    
                    this.cantPacksSalientes+=v.getPack_finales();
                    //LOS PACK QUE AUN TIENEN RECORRIDO POR HACER VUELVEN A ESTAR DISPONIBLES
                    for(Integer id : v.getIdPacks()){
                        if(!this.listPackAlgo.get(id).getRuta().equals("")){
                            this.listPackAlgo.get(id).setEstado(1);
                            this.listPackAlgo.get(id).setOriginAirport(aero.getIdentificator());
                        }else
                            this.listPackAlgo.get(id).setEstado(3); // packs fuera

                    }
                        
                    System.out.println(aero.getCountry()+" "+aero.getCapActual() + " : " + v.getPack_finales());
                    
                    v.getIdPacks().clear(); // se queda sin packs
                    v.setPack_finales(0); // se queda sin pack finales
                    //break; //se sale del for
             //   }
            //}
            v.setCapacidadActual(0);
            //System.out.println("<<<<<<<<<<<<< vuelo vacia" + v.getId() + " -- " + v.getCapacidadActual());
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
    
    
    void lecturaData(){
        try{
            if (this.inicio == 0){




                final File folder = new File("resources\\pack_enviados");
                listFilesForFolder(folder);



                this.tabu.setListAirport(this.listaAeropuertos);
                this.tabu.setListFlight(this.listaVuelos);
                this.tabu.generateFlightMatrix();

                this.dp.setListAirport(this.listaAeropuertos);

                this.tabu.setInputProcess(this.dp);
                for (String a : this.Archivos){
                    dp.processPackNew("resources\\pack_enviados_generados\\" + a);
//                    dp.processPackNew("resources\\pack_enviados\\" + a);
                }

                System.out.println("cant total de paquetes - " + this.dp.getPackList().size()); // todos los paquetes


                this.listPack = this.dp.getPackList();
                this.matrixPackXDay = this.dp.getMatrixPackXDay();
                

                if (this.listPack.size()>0)//se coloca la fecha del primer pack como fecha del simulador
                    this.calendar.set(this.listPack.get(0).getOriginYear(),this.listPack.get(0).getOriginMonth() - 1,this.listPack.get(0).getOriginDay());
                
                this.listPack.clear();
                this.inicio = 1;
            }
        }catch(Exception ex){
           System.out.println("ERROR lecturaData " + ex.getMessage() );
       }
    }
    
    
    //REALIZAR UNA CANTIDAD N DE BLOQUES DE PACKS ANTES DE EJECUTAR EL SIMULADOR
    public void calculoInicial(int n){
       try{ 
           
        for(int i = 0; i < n ; i++){   
            if (this.inicioAlgo == 1){
                aplicaAlgoritmo();
            }    
        }
        this.inicioAlgo = 0;
       }catch(Exception ex){
           System.out.println("error calculoInicial " + ex.getLocalizedMessage());
       }
    }
    
    
    
    public void aplicaAlgoritmo(){
        try{
                
                TabuSimulator simulador = new TabuSimulator(this.horaMundial, this.minutoMundial, this.calendar.getTime(), this.tabu, this.listaAeropuertos, this.listaVuelos, this.listPackAlgo, this.listPack,this.tiempoAlgoMM,this.algoritmoDelayMinutes);

                long startTime = System.nanoTime();
                simulador.setManual(0);
                simulador.start();
                simulador.join();
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                
                //rutasPaquetesAlgo=simulador.rutasPaquetesAlgo;
                //listaAeropuertos=simulador.listaAeropuertos;      
                
                //AGREGA LISTA PAQUETES CON RUTAS A LA EXISTENTE
                System.out.println("------>cantidad de packs  procesados " +  simulador.getListPackAlgo().size());
                
                //this.listPackAlgo.addAll(simulador.getListPackAlgo());
                this.listPackAlgo = simulador.getListPackAlgo();
                
                System.out.println("------>tiempo " +  totalTime/1000000000 + " segundos"); 
                
                System.out.println("------>cantidad de packs  almacenado " +  listPack.size()); 
                System.out.println("------>cantidad de packs  con rutas " +  listPackAlgo.size());                
                
                //Actualiza tiempo de algoritmo
                this.tiempoAlgoMM = simulador.getTiempoAlgo();
                
        }catch(Exception ex){
           System.out.println("error aplicaAlgoritmo " + ex.getLocalizedMessage());
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
                            this.listaAeropuertos.get(p.getOriginAirport()-1).setCapActual(this.listaAeropuertos.get(p.getOriginAirport()-1).getCapActual() + 1);
                            this.cantPacksEntrantes++;
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
    
    
    
    public void actionPerformed(ActionEvent e){
       try{
           //ArrayList<ArrayList<ArrayList<Paquete>>>
           
            if (this.minutoMundial == 0 && this.horaMundial == 0){
                
                this.listPack.clear();
                
                for(ArrayList<ArrayList<Paquete>> dataPacksAero : this.matrixPackXDay){
                    if(dataPacksAero.size() > cantDays){
                        ArrayList<Paquete> listaPacksxDia = dataPacksAero.get(cantDays);
                        this.listPack.addAll(listaPacksxDia);
                    }
                }
 
                System.out.println("<<<<<<<Cantidad de dias transcurridos>>>>>>>>>> -------------> " + this.cantDays);
                System.out.println("<<<<<<<Colapso maximo aero>>>>>>>>>> -------------> " + this.aeroColapsoMax + " - " + this.cantMaxColapsoAero);
                System.out.println("<<<<<<<Colapso maximo avion>>>>>>>>>> -------------> " + this.avionColapsoMax + " - "  + this.cantMaxColapsoAvion);
                
                this.cantDays++;
            }
           
           
            if (this.minutoMundial<59){
                this.minutoMundial++;
            }else{
                this.minutoMundial=0;
                if (this.horaMundial <23)
                    this.horaMundial++;
                else {
                    this.minutoMundial=0;
                    this.horaMundial=0;
                    this.calendar.add(this.calendar.DATE,1);
                }    
            }


            
            System.out.println("----->Cant Paquetes Entrantes " + this.cantPacksEntrantes);
            System.out.println("----->Cant Paquetes Salientes " + this.cantPacksSalientes);
            
            //si esta al inicio de todo, calcular n cantidad de lotes para evitar descuadre
            
            calculoInicial(4); // OBTIENE RUTAS DE 4 BLOQUES PACKS AL INICIO
            
            
            cambiaEstadoPacks(); // SE CAMBIA EL ESTADO DE LOS PAQUETES SEGUN VAN LLEGANDO A SU ORIGEN
            
            //aplica algoritmo al inicio del dia y luego cada cantidad de tics

            if (this.cantTics == this.algoritmoDelayMinutes){
                
                aplicaAlgoritmo();
                
                this.cantTics=0;
                
                
                for (Aeropuerto a : this.listaAeropuertos){
                    System.out.println(a.getCountry() + " -> " + a.getCapActual());
                }
                
            }
            
            //DIBUJA MOVIMIENTO
            for(int i=0;i<this.avionesDot.size();i++){

                cambiaEstadoMov(this.avionesDot.get(i));

                if (this.avionesDot.get(i).getEstado_mov() == 1){
                    //COLOR DEL ALMACEN
                    cambiaEstadoAlmacen(this.avionesDot.get(i));
                    mueveAvion(this.avionesDot.get(i));

                }
            }

            repaint();
            this.cantTics++;
       }catch(Exception ex){
           System.out.println("ACTION PERFORMDED" + ex.getMessage() );
           ex.printStackTrace();
       }
        
    }
    
   
}


