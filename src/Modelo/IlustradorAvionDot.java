/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.CoordenadaDouble;
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

/**
 *
 * @author JUAN
 */
public class IlustradorAvionDot extends JPanel implements ActionListener{

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
    private Timer t;
    static int  count = 0;
    private ArrayList<avionDot> avionesDot;
    static private double precision = 1;
    static private double velocidad = 1;
    
    
    public IlustradorAvionDot(ArrayList<avionDot> avionesDot){
        t= new Timer(8,this);
        this.avionesDot = avionesDot;
        
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        //int sizeList=destiny.size();
        ArrayList<Ellipse2D>arrayEllipse=new ArrayList<>();
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            double xIni=this.avionesDot.get(i).getActual().getX();
            double yIni=this.avionesDot.get(i).getActual().getY();
            
            double xFin=this.avionesDot.get(i).getDestino().getX();
            double yFin=this.avionesDot.get(i).getDestino().getY();
            
            double dx,dy,length;
            
            arrayEllipse.add(new Ellipse2D.Double(xIni,yIni,5,5));
            g2.fill(arrayEllipse.get(i));
            dx=xFin-xIni;
            dy=yFin-yIni;
            length=Math.sqrt(Math.pow(dx, 2.0)+Math.pow(dy, 2.0));
            dx/=length;
            dy/=length;
            this.avionesDot.get(i).setvX(dx*getVelocidad());
            this.avionesDot.get(i).setvY(dy*getVelocidad());
        }
        getT().start();
    }

    public void actionPerformed(ActionEvent e){
        
        for(int i=0;i<this.avionesDot.size();i++){
            
            double xIni=this.avionesDot.get(i).getActual().getX();
            double xFin=this.avionesDot.get(i).getDestino().getX();
            
            double yIni=this.avionesDot.get(i).getActual().getY();
            double yFin=this.avionesDot.get(i).getDestino().getY();
            
            
            //si se usa while, cabe la posibilidad de que no sean iguales por incertidumbre
            if(xIni< xFin && yIni<yFin){
                double xOrig =  this.avionesDot.get(i).getOrigen().getX();
                double yOrig =  this.avionesDot.get(i).getOrigen().getY();
                double xDest =  this.avionesDot.get(i).getDestino().getX();
                double yDest =  this.avionesDot.get(i).getDestino().getY();
                double xAct =  this.avionesDot.get(i).getActual().getX();
                double yAct =  this.avionesDot.get(i).getActual().getY();
                this.avionesDot.get(i).getActual().setX(xIni+this.avionesDot.get(i).getvX());
                this.avionesDot.get(i).getActual().setY(yIni+this.avionesDot.get(i).getvY());
                if((abs(xAct)- abs(xDest) )<=getPrecision() &&(abs(yAct)- abs(yDest) )<=getPrecision()){
                    this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                    this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                }
            }else if(xIni< xFin && yIni>yFin){
                double xOrig =  this.avionesDot.get(i).getOrigen().getX();
                double yOrig =  this.avionesDot.get(i).getOrigen().getY();
                double xDest =  this.avionesDot.get(i).getDestino().getX();
                double yDest =  this.avionesDot.get(i).getDestino().getY();
                double xAct =  this.avionesDot.get(i).getActual().getX();
                double yAct =  this.avionesDot.get(i).getActual().getY();
                this.avionesDot.get(i).getActual().setX(xIni+this.avionesDot.get(i).getvX());
                this.avionesDot.get(i).getActual().setY(yIni+this.avionesDot.get(i).getvY());
                if((abs(xAct)- abs(xDest) )<=getPrecision() &&(abs(yAct)- abs(yDest) )<=getPrecision()){
                    this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                    this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                }
            }else if(xIni> xFin && yIni<yFin){
                double xOrig =  this.avionesDot.get(i).getOrigen().getX();
                double yOrig =  this.avionesDot.get(i).getOrigen().getY();
                double xDest =  this.avionesDot.get(i).getDestino().getX();
                double yDest =  this.avionesDot.get(i).getDestino().getY();
                double xAct =  this.avionesDot.get(i).getActual().getX();
                double yAct =  this.avionesDot.get(i).getActual().getY();
                this.avionesDot.get(i).getActual().setX(xIni+this.avionesDot.get(i).getvX());
                this.avionesDot.get(i).getActual().setY(yIni+this.avionesDot.get(i).getvY());
                if((abs(xAct)- abs(xDest) )<=getPrecision() &&(abs(yAct)- abs(yDest) )<=getPrecision()){
                    this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                    this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                }
            }else if(xIni> xFin && yIni>yFin){
                double xOrig =  this.avionesDot.get(i).getOrigen().getX();
                double yOrig =  this.avionesDot.get(i).getOrigen().getY();
                double xDest =  this.avionesDot.get(i).getDestino().getX();
                double yDest =  this.avionesDot.get(i).getDestino().getY();
                double xAct =  this.avionesDot.get(i).getActual().getX();
                double yAct =  this.avionesDot.get(i).getActual().getY();
                this.avionesDot.get(i).getActual().setX(xIni+this.avionesDot.get(i).getvX());
                this.avionesDot.get(i).getActual().setY(yIni+this.avionesDot.get(i).getvY());
                if((abs(xAct)- abs(xDest) )<=getPrecision() &&(abs(yAct)- abs(yDest) )<=getPrecision()){
                    this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                    this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                }
            }else{
                /*
                this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                */
                double xOrig =  this.avionesDot.get(i).getOrigen().getX();
                double yOrig =  this.avionesDot.get(i).getOrigen().getY();
                double xDest =  this.avionesDot.get(i).getDestino().getX();
                double yDest =  this.avionesDot.get(i).getDestino().getY();
                double xAct =  this.avionesDot.get(i).getActual().getX();
                double yAct =  this.avionesDot.get(i).getActual().getY();
                
                
                count++;
               // System.out.println(i + " ( "+  xAct +" , "+yAct+"  ) -> ( "+xDest +" , " +yDest +") ");

                if ( i==0) {
                
               
                //System.out.println( "( "+  xAct +" , "+yAct+"  ) -> ( "+xDest +" , " +yDest +") ");
                if ( (abs(xAct)- abs(xDest) )<=getPrecision() &&(abs(yAct)- abs(yDest) )<=getPrecision()){
                    
                   // System.out.println("Llego aqui!! ee "+count);
//                   this.avionesDot.get(i).getActual().setX(0);
//                    this.avionesDot.get(i).getActual().setY(0);
                    this.avionesDot.get(i).getActual().setX(this.avionesDot.get(i).getOrigen().getX());
                    this.avionesDot.get(i).getActual().setY(this.avionesDot.get(i).getOrigen().getY());
                }
                
                //continue;
                
                 }
            }
        }
        
//        t1=new threadGraphic(this.origin,this.destiny,this.velocityX,this.velocityY);
//        t2=new threadGraphic(this.origin,this.destiny,this.velocityX,this.velocityY);
//        try{
//            t1.start();
//            Thread.sleep(10);
//            t2.start();
//        }catch(Exception exp){
//            System.out.println("error");
//        }
//        if(xIni1<= xFin && yIni1<=yFin){
//            xIni1+=velX1;
//            yIni1+=velY1;
//            
//        }if(xIni2<= xFin2 && yIni2<=yFin2){
//            xIni2+=velX2;
//            yIni2+=velY2;
//            
//        }

        repaint();
        
    }

   
}


