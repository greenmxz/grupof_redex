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
import java.util.Calendar;
import java.util.Date;


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
    
    
    private ArrayList<paquete> listaPaquetes = new ArrayList();
    
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

    
    
    public IlustradorAvionDot(ArrayList<avionDot> avionesDot){
        t= new Timer(timeMS,this);
        this.avionesDot = avionesDot;
        
        
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
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,this.cantDays);
        Date diaNuevo = calendar.getTime();
        
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
        
        // verificar shora salida
        if (v.getEstado_mov() == 0){
            if (this.horaMundial*60 + this.minutoMundial == v.getHora_salida()*60 + v.getMin_salida()){
                v.setEstado_mov(1);// en transito
            }
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
                this.cantDays++;
            }    
        }
        
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            //if (i == 1) break;
            
            cambiaEstadoMov(this.avionesDot.get(i));
            
            if (this.avionesDot.get(i).getEstado_mov() == 1){
                
                mueveAvion(this.avionesDot.get(i));
            
            }
        }

        repaint();
        
    }

   
}


