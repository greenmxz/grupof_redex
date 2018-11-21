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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.Date;
import java.util.*;

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
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMapViewer map = new JMapViewer() {
        
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1040, 780);
            }

            @Override
            public String getToolTipText(MouseEvent e) {
                Coordinate c = (Coordinate) getPosition(e.getX(), e.getY());
                return c.getLat() + " " + c.getLon();
            }
        };
        
        map.setToolTipText("");
        //Coordinate start = new Coordinate(-34.9286, 138.6);
        Coordinate start =new Coordinate(0,0);
        route.add(start);
        MapPolygonImpl poly = new MapPolygonImpl(route);
        poly.setColor(Color.blue);
        map.addMapPolygon(poly);
        
        
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
                
                
                int h=map.getPreferredSize().height;
                int w=map.getPreferredSize().width;
               
                /*
                double x=(lon+180)*(w/360)*1.419;
                double latRad = lat*Math.PI/180;

                double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
                double y     = (h/2)-(w*mercN/(2*Math.PI))-20;*/
                
                //double x=Double.parseDouble(arr[6]);
                //double y=Double.parseDouble(arr[7]);
                
                int index = buscaAeropuerto(this.listaAeropuertos,codigoAe);
                
                if (index != -1){
                    //  si existe aeropuerto en data del profe se asigna coordenadas y pinta puntito
                    map.addMapMarker(new MapMarkerDot(lat,lon)); // pinta puntito amarillo
                    this.listaAeropuertos.get(index).setCoordX(coordX);
                    this.listaAeropuertos.get(index).setCoordY(coordY);
                }
                //origen.add(new CoordenadaDouble(coordX,coordY));
                
                //destino.add(new CoordenadaDouble(0,0));
                //puntosXY.add(map.getMapPosition(num2, num1));
                c++;
            }            
        }catch (Exception ex){
            System.out.println("Error de lectura Coordenadas " + ex.getMessage());
        }
        
        
        // Se lee planes de vuelo para llenar los AvionesDot
        
        this.listaVuelos = controladorVuelo.listaVuelos();
        int i = 0;
        for(Vuelo v : this.listaVuelos){
            // crea AvionDot por vuelo
            avionDot dot = new avionDot();
            dot.setId(v.getId());
            dot.setVisible(true);
            dot.setEstado_almacen(0);
            dot.setEstado_mov(0);
            dot.setColor("verde");
            dot.setIcaoOrigen(v.getAeropuertoOrigen());
            dot.setIcaoDestino(v.getAeropuertoDestino());
            //hora de salida
            Date fs = v.getFechaSalida();
            Date fl =v.getFechaLlegada();
            
            //System.out.println("fs : " + fs);
            //System.out.println("fl : " + fl);
              
            
            /*
            dot.setHora_salida(0);
            dot.setMin_salida(30);
            
            dot.setHora_llegada(23);
            dot.setMin_llegada(40);
            */
            
         
            dot.setHora_salida(fs.getHours());
            dot.setMin_salida(fs.getMinutes());
            
            dot.setHora_llegada(fl.getHours());
            dot.setMin_llegada(fl.getMinutes());
            
            //System.out.println("salida : "+ dot.getHora_salida() + " " + dot.getMin_salida());
            //System.out.println("llegada : "+ dot.getHora_llegada() + " " + dot.getMin_llegada());
            
            String codAeOrigen = v.getAeropuertoOrigen();
            String codAeDestino = v.getAeropuertoDestino();
            
            int indexAeOrigen = buscaAeropuerto(this.listaAeropuertos,codAeOrigen);
            int indexAeDestino = buscaAeropuerto(this.listaAeropuertos,codAeDestino);
            
            
            if (indexAeOrigen != -1){
                double x = this.listaAeropuertos.get(indexAeOrigen).getCoordX();
                double y = this.listaAeropuertos.get(indexAeOrigen).getCoordY();
               if (x == 0 || y == 0) 
                    continue;
                dot.setOrigen(new CoordenadaDouble(x,y));
                dot.setActual(new CoordenadaDouble(x,y));
            }
            if (indexAeDestino != -1){
                double x = this.listaAeropuertos.get(indexAeDestino).getCoordX();
                double y = this.listaAeropuertos.get(indexAeDestino).getCoordY();
                if (x == 0 || y == 0) 
                    continue;
                dot.setDestino(new CoordenadaDouble(x,y));
            }
            this.avionesDot.add(dot);
        }
        
        
        /*
        try{
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("citiesXYDestino.csv"));
            while( (line = reader.readLine()) != null){
                String[] arr = line.split(",");
                double x = Double.parseDouble(arr[0]);
                double y = Double.parseDouble(arr[1]);                                            
                
                destino.add(new CoordenadaDouble(x,y));
            }            
        }catch (Exception ex){
            System.out.println("Error de lectura");
        }*/
        
        
//        try{
//            String line;
//            
//            BufferedWriter writer = new BufferedWriter(new FileWriter("citiesXY.csv"));
//            int size=origen.size();
//            
//            for(int i=0;i<size;i++){
//                String str;
//                double x=origen.get(i).getX();
//                double y=origen.get(i).getY();
//                str = new String(String.valueOf(x)+","+String.valueOf(y));
//                
//                
//                writer.write(str+"\n");
//            }
//            writer.close();
//        }catch (Exception ex){
//            System.out.println("Error de escritura");
//        }


        
        


        map.setDisplayPosition(start, 2);

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
        ilustradorAvion.setSize(map.getPreferredSize());
        ilustradorAvion.setOpaque(false);
        JButton button1 = new JButton();
        button1.setLocation(900,900);
        button1.setText("<<");
        
        
        JButton button3 = new JButton();
        button3.setLocation(900,900);
        button3.setText("Pausa");
        
        
        JButton button4 = new JButton();
        button4.setLocation(900,900);
        button4.setText("Reanudar");
        
        
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
        
        /*
                System.out.println("AQUI AQUI");
        velocidad*=2;*/
        
        
        ilustradorAvion.add(button1);
        ilustradorAvion.add(button2);
        ilustradorAvion.add(label);
        ilustradorAvion.add(button3);
        ilustradorAvion.add(button4);
        ilustradorAvion.add(label2);
        
        
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
        ilustradorAvion.setLocation(map.getLocation());
        map.add(ilustradorAvion); 
        map.setZoomControlsVisible(false);
       
        f.add(map);
        //f.setResizable(false);
        Dimension d = new Dimension(1040,780);
        f.setMaximumSize( d);
        f.setResizable(false);
        System.out.println( "MI SIZE: " + f.getMaximumSize( ));
        f.pack();
        f.setSize(map.getPreferredSize());
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
    private class MapWorker extends SwingWorker<Void, Coordinate> {

        private final JMapViewer map;
        private Coordinate last;
        private MapMarkerDot lastdot;
        private MapMarkerDot actualdot;
        
        public MapWorker(JMapViewer map, Coordinate start)  {
            this.map = map;
            this.last = start;
        }
        
        @Override
        protected Void doInBackground() throws Exception {
            while (!isCancelled()) {
                
                lastdot=new MapMarkerDot(last.getLat(),last.getLon());
                last = new Coordinate(last.getLat() + 1, last.getLon() + 1);
                actualdot=new MapMarkerDot(last.getLat(),last.getLon());
                publish(last);
                
                //Thread.sleep(1000);
                
            }
            return null;
        }

        @Override
        protected void process(List<Coordinate> chunks) {
            //for (Coordinate c : chunks) {
                //route.add(c);
                
                
                //map.removeAllMapMarkers();
                
                
                
                
             //map.removeMapMarker(lastdot);
                map.addMapMarker(actualdot);
                
            //}
            map.repaint();
        }
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