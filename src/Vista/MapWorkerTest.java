/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
/**
 *
 * @author JUAN
 */
public class MapWorkerTest {
   
    private final List<Coordinate> route = new ArrayList<>();
    private final ArrayList<CoordenadaDouble>origen=new ArrayList<>();
    private final ArrayList<CoordenadaDouble>destino=new ArrayList<>();
    public void display() throws IOException {
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
        
        try{
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("cities2.csv"));
            while( (line = reader.readLine()) != null){
                String[] arr = line.split(",");
                double num1 = Double.parseDouble(arr[4]);//longitud
                double num2 = Double.parseDouble(arr[5]);//latitud
                
                map.addMapMarker(new MapMarkerDot(num2,num1));
                origen.add(new CoordenadaDouble(0,0));
                destino.add(new CoordenadaDouble(num2+78,num1+180));
                //puntosXY.add(map.getMapPosition(num2, num1));
                

            }
//            BufferedReader reader2 = new BufferedReader(new FileReader("citiesPlus.csv"));
//            while( (line = reader2.readLine()) != null){
//                String[] arr = line.split(",");
//                double num1 = Double.parseDouble(arr[4]);
//                double num2 = Double.parseDouble(arr[5]);
//                map.addMapMarker(new MapMarkerDot(num2,num1));
//                origen.add(new CoordenadaDouble(0,0));
//                destino.add(new CoordenadaDouble(num2*10,num1*10));
//                //puntosXY.add(map.getMapPosition(num2, num1));
//                
//
//            }
        }catch (Exception ex){
            System.out.println("Error de lectura");
        }
        map.setDisplayPosition(start, 2);
        AvionIcon avion=new AvionIcon(origen,destino);   
        avion.setVisible(true);
        avion.setSize(map.getPreferredSize());
        avion.setOpaque(false);
        //avion.setBackground(new Color(0,0,0,30));
        avion.setLocation(map.getLocation());
        map.add(avion); 
        map.setZoomControlsVisible(false);
       
        f.add(map);
        
         
        f.pack();
        f.setSize(map.getPreferredSize());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        //new MapWorker(map, start).execute();
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