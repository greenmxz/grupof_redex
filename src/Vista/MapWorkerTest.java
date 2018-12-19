/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Algoritmo.Aeropuerto;
import Modelo.IlustradorAvionDot;
import Modelo.avionDot;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.josm.data.projection.Projection;

import Modelo.aeropuerto;
import Controlador.aeropuertoBL;
import Controlador.VueloBL;
import Controlador.generalBL;
import Modelo.Vuelo;
import Modelo.continente;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Math.abs;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.Date;
import java.util.*;
import javax.imageio.ImageIO;


/**
 *
 * @author JUAN
 */
public class MapWorkerTest {
    
    private ArrayList<continente> continentes = new ArrayList<continente>();
    private ArrayList<Vuelo> vuelosNew = new ArrayList<Vuelo>();
    private generalBL general = new generalBL();
    private VueloBL vueloBL = new VueloBL();
    private aeropuertoBL controladorAeropuerto = new aeropuertoBL();
    private ArrayList<aeropuerto> listaAeropuertos = new ArrayList<>();
     private ArrayList<Algoritmo.Aeropuerto> listaAeropuertosNew = new ArrayList<>();
    private VueloBL controladorVuelo = new VueloBL();
    private ArrayList<Vuelo> listaVuelos = new ArrayList<>();
    private ArrayList<Algoritmo.Vuelo> listaVuelosNew = new ArrayList<>();
    private final List<Coordinate> route = new ArrayList<>();
    
    
    private final ArrayList<CoordenadaDouble>origen=new ArrayList<>();
    private final ArrayList<CoordenadaDouble>destino=new ArrayList<>();
      
    private ArrayList<avionDot> avionesDot = new ArrayList<>();
    
    
    
    
  

    public static BufferedImage getScreenShot(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(),component.getHeight(),BufferedImage.TYPE_INT_RGB);
        // call the Component's paint method, using
        // the Graphics object of the image.
        component.paint( image.getGraphics() ); // alternately use .printAll(..)
        return image;
    }
    public static void getSaveSnapShot(Component component, String fileName) throws Exception {
        BufferedImage img = getScreenShot(component);
        // write the captured image as a PNG
        ImageIO.write(img, "png", new File(fileName));
    }
    

    public int buscaAeropuerto( ArrayList<aeropuerto> listaAe, String codigo){
        try{
            int index = 0;
            
            for (aeropuerto ae : listaAe){
                
                if (ae.getCodigo().equals(codigo))
                    return index;
                
                index += 1;
            }
            
            return -1;
        }catch (Exception ex){
            System.out.println("Error en buscaAeropuerto " + ex.getMessage());
            return -1;
        }
        
        
        
    }
    
    void display() throws IOException {
        JFrame f = new JFrame("MapWorker");
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        

//        JMapViewer map = new JMapViewer() {
//        
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(1040, 780);
//            }
//
//            @Override
//            public String getToolTipText(MouseEvent e) {
//                Coordinate c = (Coordinate) getPosition(e.getX(), e.getY());
//                return c.getLat() + " " + c.getLon();
//            }
//        };
//        map.setMapRectanglesVisible(false);
//        map.setToolTipText("");
//        //Coordinate start = new Coordinate(-34.9286, 138.6);
//        Coordinate start =new Coordinate(0,0);
//        route.add(start);
//        MapPolygonImpl poly = new MapPolygonImpl(route);
//        poly.setColor(Color.blue);
//        map.addMapPolygon(poly);
        
        
        // Lectura de Aeropuertos
        
        this.listaAeropuertos = controladorAeropuerto.listaAeropuertos();
            
        // Lectura de coordenadas de Aeropuertos
        try{
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("citiesf5.csv"));
            int c = 0;
            while( (line = reader.readLine()) != null){

                String[] arr = line.split(",");
                double lon = Double.parseDouble(arr[4]);//longitud
                double lat = Double.parseDouble(arr[5]);//latitud
                double coordX = Double.parseDouble(arr[6]); // coordenada x
                double coordY = Double.parseDouble(arr[7]); // coordenada y

                String codigoAe = arr[8];//codigo
                String ciudadAe = arr[9];//ciudad
                String paisAe = arr[10];//pais
                
                
                //int h=map.getPreferredSize().height;
                //int w=map.getPreferredSize().width;
               
                
                int index = buscaAeropuerto(this.listaAeropuertos,codigoAe);
                
                if (index != -1){
                    //  si existe aeropuerto en data del profe se asigna coordenadas y pinta puntito
                    //map.addMapMarker(new MapMarkerDot(lat,lon)); // pinta puntito amarillo
                    
                    this.listaAeropuertos.get(index).setCoordX(coordX);
                    this.listaAeropuertos.get(index).setCoordY(coordY);
                }

                c++;
            }            
        }catch (Exception ex){
            System.out.println("Error de lectura Coordenadas " + ex.getMessage());
        }
        
        
        // Se lee planes de vuelo para llenar los AvionesDot
        
        this.listaVuelos = controladorVuelo.listaVuelos();
        
        for(int i = 0;i < this.listaVuelos.size();i++){
            Vuelo v = this.listaVuelos.get(i);
            // crea AvionDot por vuelo
            avionDot dot = new avionDot();
            dot.setId(v.getId());
            dot.setVisible(true);
            dot.setEstado_almacen(0);
            dot.setEstado_mov(0);
            dot.setColor("verde");
            dot.setIcaoOrigen(v.getAeropuertoOrigen());
            dot.setIcaoDestino(v.getAeropuertoDestino());
            dot.setIdAeroOrigen(v.getIdAeropuertoOrigen());
            dot.setIdAeroDestino(v.getIdAeropuertoDestino());
            dot.setPack_finales(0);
            //hora de salida
            Date fs = v.getFechaSalida();
            Date fl =v.getFechaLlegada();
            dot.setCapacidadActual(0);
            dot.setCapacidadMax(300); // verificar si debe setear aca
                     
            dot.setHora_salida(fs.getHours());
            dot.setMin_salida(fs.getMinutes());
            
            dot.setHora_llegada(fl.getHours());
            dot.setMin_llegada(fl.getMinutes());
            
            dot.setT_llegada(abs((dot.getHora_llegada()*60 + dot.getMin_llegada()) -(dot.getHora_salida()*60 + dot.getMin_salida())));
            dot.setT_restante(dot.getT_llegada());
            
            String codAeOrigen = v.getAeropuertoOrigen();
            String codAeDestino = v.getAeropuertoDestino();
            
            int indexAeOrigen = buscaAeropuerto(this.listaAeropuertos,codAeOrigen);
            int indexAeDestino = buscaAeropuerto(this.listaAeropuertos,codAeDestino);
            
            aeropuerto aero_orig = this.listaAeropuertos.get(indexAeOrigen);
            aeropuerto aero_dest = this.listaAeropuertos.get(indexAeDestino);
            
            if (indexAeOrigen != -1){
                double x = this.listaAeropuertos.get(indexAeOrigen).getCoordX();
                double y = this.listaAeropuertos.get(indexAeOrigen).getCoordY();
               if (x == 0 || y == 0) 
                    continue;
                dot.setOrigen(new CoordenadaDouble(x-10,y-10));
                dot.setActual(new CoordenadaDouble(x-10,y-10));
            }
            if (indexAeDestino != -1){
                double x = this.listaAeropuertos.get(indexAeDestino).getCoordX();
                double y = this.listaAeropuertos.get(indexAeDestino).getCoordY();
                if (x == 0 || y == 0) 
                    continue;
                dot.setDestino(new CoordenadaDouble(x,y));
            }
            
            
            // PARA EL MANEJO DE TIEMPOS
            int horaSalida = dot.getHora_salida();
            int horaLlegada = dot.getHora_llegada();
            int llegaDiaSig = revisaTiempo(horaSalida,horaLlegada,aero_orig,aero_dest);
            
            if (llegaDiaSig == 1){
                horaLlegada += 24; // llega al dia siguiente
            }
            
            
            dot.setSalidaMM(horaSalida*60 + dot.getMin_salida());
            dot.setLlegadaMM(horaLlegada*60 + dot.getMin_llegada());
            dot.setLlegaDiaSig(llegaDiaSig);
            dot.setTiempoTranscurridoMM(0);
            
            this.avionesDot.add(dot);
            
        }
        
        
        //map.setDisplayPosition(start, 2);

        this.continentes = general.obtenerContinentes();

        for (aeropuerto a : this.listaAeropuertos){
            Aeropuerto newAero = new Aeropuerto();
            
            newAero.setIdentificator(a.getId());
            newAero.setIcaoCode(a.getCodigo());
            newAero.setCityId(a.getCiudad());
            int idCont = buscarContinente(a.getContinente());
            newAero.setContinent(idCont);
            newAero.setCountry(a.getPais());
            newAero.setCapMax(a.getCapMax());

           newAero.setCapActual(a.getCapActual());
           newAero.setCoordX(a.getCoordX());
           newAero.setCoordY(a.getCoordY());
           newAero.setColor("verde");

            this.listaAeropuertosNew.add(newAero);
            
 
        }


        for (Modelo.Vuelo a : this.listaVuelos){
            Algoritmo.Vuelo v = new Algoritmo.Vuelo(a.getIdAeropuertoOrigen(),a.getFechaSalida().getHours(),a.getFechaSalida().getMinutes(),
            a.getIdAeropuertoDestino(),a.getFechaLlegada().getHours(),a.getFechaLlegada().getMinutes()
            );
            this.listaVuelosNew.add(v);

        }
        
        IlustradorAvionDot ilustradorAvion = new IlustradorAvionDot(this.avionesDot,this.listaAeropuertosNew,this.listaVuelosNew);   
        ilustradorAvion.setVisible(true);
        //ilustradorAvion.setSize(map.getPreferredSize());
        //ilustradorAvion.setOpaque(false);
        
        JButton button1 = new JButton();
        button1.setLocation(900,900);
        button1.setText("<<");
        
        
        JButton button3 = new JButton();
        button3.setLocation(900,900);
        button3.setText("Pausa");
        
        
        JButton button4 = new JButton();
        button4.setLocation(900,900);
        button4.setText("Reanudar");
        
        JButton button5 = new JButton();
        button5.setLocation(900,900);
        button5.setText("Capturar Imagen");
        
        JButton button6 = new JButton();
        button6.setLocation(900,900);
        button6.setText("Cerrar");
        
        JLabel label = new JLabel();
        label.setText("Velocidad");
        
        
        JLabel label2 = new JLabel();
        label.setText("X"+String.valueOf(ilustradorAvion.getT().getDelay()));
        
        
        
        JButton button2 = new JButton();
        button2.setLocation(850,850);
        button2.setText(">>");
        
         button2.addActionListener(new ActionListener() {//Aumentar velocidad
            
            private void button2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // BUCAR AEROPUERTO ORIGEN
                //ilustradorAvion.setT(ilustradorAvion.getT()*2);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ilustradorAvion.getT().getDelay() >1){
                 ilustradorAvion.getT().setDelay(  (ilustradorAvion.getT().getDelay()/2) );
                 label.setText(String.valueOf(ilustradorAvion.getT().getDelay()));
                }else{
                
                   }
                  System.out.println("Delay-> "+ilustradorAvion.getT().getDelay());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
         
          button3.addActionListener(new ActionListener() {//Pausar
            
            private void button2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // BUCAR AEROPUERTO ORIGEN
                ilustradorAvion.setVelocidad(ilustradorAvion.getVelocidad()*0);
                label.setText(String.valueOf(ilustradorAvion.getT().getDelay()));
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                ilustradorAvion.getT().setDelay(  4096 );
                 System.out.println("Delay-> "+ilustradorAvion.getT().getDelay());
                 label.setText(String.valueOf("PAUSADO"));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
        
        
        button4.addActionListener(new ActionListener() {//Reaundar
            
            private void button2ActionPerformed(java.awt.event.ActionEvent evt) {  
                
                // BUCAR AEROPUERTO ORIGEN
                ilustradorAvion.setVelocidad(ilustradorAvion.getVelocidad()+1);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ilustradorAvion.getT().getDelay()>=2048){
                     System.out.println("Delay-> "+ilustradorAvion.getT().getDelay());
                     
                     ilustradorAvion.getT().setDelay(  1 );
                     label.setText(String.valueOf(ilustradorAvion.getT().getDelay()));
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
        
        button1.addActionListener(new ActionListener() {//Disminuir velocidad
            
            private void button1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // BUCAR AEROPUERTO ORIGEN
                ilustradorAvion.setVelocidad(ilustradorAvion.getVelocidad()/2);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                   System.out.println("Delay-> "+ilustradorAvion.getT().getDelay());
                   if (ilustradorAvion.getT().getDelay()<=64){
                       ilustradorAvion.getT().setDelay(  (ilustradorAvion.getT().getDelay()*2) );
                       label.setText(String.valueOf(ilustradorAvion.getT().getDelay()));
                   }
                  
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        
        button5.addActionListener(new ActionListener() {//Disminuir velocidad
            
            private void button5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // BUCAR AEROPUERTO ORIGEN
                
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                    LocalDateTime now = LocalDateTime.now();
                    //String fecha=dateFormat.format(cal);
                    //System.out.println(fecha);
                    //getSaveSnapShot(f,"captures\\"+dtf.format(now)+".png");
                    getSaveSnapShot(f,"captures/"+dtf.format(now)+".png");
                }catch(Exception exp){
                    System.out.println("Error de captura");
                }
                  
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        button6.addActionListener(new ActionListener(){
            private void button5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // BUCAR AEROPUERTO ORIGEN
                
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //ilustradorAvion.setVisible(false);
                    ilustradorAvion.setCerrado(1);
                    //ilustradorAvion.removeAll();
                    f.dispose();
                    
                }catch(Exception exp){
                    System.out.println("Error en cerrar ventana");
                }
                  
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        /*
                System.out.println("AQUI AQUI");
        velocidad*=2;*/
        
        
        ilustradorAvion.add(button1);
        ilustradorAvion.add(button2);
        ilustradorAvion.add(label);
        ilustradorAvion.add(button3);
        ilustradorAvion.add(button4);
        ilustradorAvion.add(label2);
        ilustradorAvion.add(button5);
        ilustradorAvion.add(button6);
        ilustradorAvion.addMouseListener(new MouseListener() {//Disminuir velocidad
            


            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });

        ilustradorAvion.addMouseListener(new MouseListener() {//Disminuir velocidad
            


            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
        
        ilustradorAvion.addMouseWheelListener(new MouseWheelListener() {//Disminuir velocidad
            
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }


        });
        //ilustradorAvion.setLocation(map.getLocation());
        //map.add(ilustradorAvion); 
        //map.setZoomControlsVisible(false);
       
        f.add(ilustradorAvion);
        //f.setResizable(false);
        Dimension d = new Dimension(1040,780);
        f.setMaximumSize( d);
        f.setResizable(false);
        System.out.println( "MI SIZE: " + f.getMaximumSize( ));
        f.pack();
        f.setSize(1023,697);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        //new MapWorker(map, start).execute();
    }
    private int buscarContinente(String c){
        for (continente cont :this.continentes){
            if (cont.getNombre().equals(c)){
                return cont.getId();
            }
        }
        return -1;
    }
    
    public int revisaTiempo(int horaSalida, int horaLlegada,aeropuerto aero_orig,aeropuerto aero_dest){
        int es_continental = 0;
        if (aero_orig.getContinente().equals(aero_dest.getContinente()))
            es_continental = 1;
        else
            es_continental = 0;
        
        if (horaLlegada <= horaSalida) return 1; // llega un dia despues
        
        else if (horaLlegada - horaSalida < 9 && es_continental == 0) return 1; // llega un dia despues
        
        return 0; // llega el mismo dia
        
    } 
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MapWorkerTest mapWorker=new MapWorkerTest();
                mapWorker.display();
            } catch (IOException ex) {
                Logger.getLogger(MapWorkerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}