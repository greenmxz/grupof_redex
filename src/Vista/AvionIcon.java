/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author JUAN
 */
public class AvionIcon extends JPanel implements ActionListener{
    Timer t=new Timer(10,this);
    private ArrayList<CoordenadaDouble>origin;    
    private ArrayList<CoordenadaDouble>destiny;
    private double[]velocityX;
    private double[]velocityY;
    private int sizeList;
    
    public AvionIcon(ArrayList<CoordenadaDouble>puntosOrigen,ArrayList<CoordenadaDouble>puntosDestino){
        this.origin=(ArrayList<CoordenadaDouble>)puntosOrigen.clone();
        this.destiny=(ArrayList<CoordenadaDouble>)puntosDestino.clone();
        this.sizeList=this.destiny.size();
        this.velocityX=new double[this.sizeList];
        this.velocityY=new double[this.sizeList];
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        int sizeList=destiny.size();
        ArrayList<Ellipse2D>arrayEllipse=new ArrayList<>();
        
        for(int i=0;i<1;i++){
            double xIni=this.origin.get(i).getX();
            double yIni=this.origin.get(i).getY();
            double xFin=565;//this.destiny.get(i).getX(); ,,
            double yFin=240;//this.destiny.get(i).getY();
            double dx,dy,length;
            arrayEllipse.add(new Ellipse2D.Double(xIni,yIni,5,5));
            g2.fill(arrayEllipse.get(i));
            dx=xFin-xIni;
            dy=yFin-yIni;
            length=Math.sqrt(Math.pow(dx, 2.0)+Math.pow(dy, 2.0));
            dx/=length;
            dy/=length;
            velocityX[i]=dx;
            velocityY[i]=dy;
        }
        t.start();
    }
    
    public void actionPerformed(ActionEvent e){
        
        for(int i=0;i<1;i++){
            double xIni=origin.get(i).getX();
            double xFin=destiny.get(i).getX();
            double yIni=origin.get(i).getY();
            double yFin=destiny.get(i).getY();
            //si se usa while, cabe la posibilidad de que no sean iguales por incertidumbre
            if(xIni< xFin && yIni<yFin){
                origin.get(i).setX(xIni+velocityX[i]);
                origin.get(i).setY(yIni+velocityY[i]);
            }else if(xIni< xFin && yIni>yFin){
                origin.get(i).setX(xIni+velocityX[i]);
                origin.get(i).setY(yIni+velocityY[i]);
            }else if(xIni> xFin && yIni<yFin){
                origin.get(i).setX(xIni+velocityX[i]);
                origin.get(i).setY(yIni+velocityY[i]);
            }else if(xIni> xFin && yIni>yFin){
                origin.get(i).setX(xIni+velocityX[i]);
                origin.get(i).setY(yIni+velocityY[i]);
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


