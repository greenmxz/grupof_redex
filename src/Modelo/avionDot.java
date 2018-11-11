/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.CoordenadaDouble;
import java.util.ArrayList;

/**
 *
 * @author Nowa
 */
public class avionDot {
    private CoordenadaDouble origen;
    private CoordenadaDouble destino;
    private CoordenadaDouble actual;
    private ArrayList<CoordenadaDouble>ruta;
    private String color;
    private boolean visible;
    private Double vX;
    private Double vY;

    public CoordenadaDouble getOrigen() {
        return origen;
    }

    public void setOrigen(CoordenadaDouble origen) {
        this.origen = origen;
    }

    public CoordenadaDouble getDestino() {
        return destino;
    }

    public void setDestino(CoordenadaDouble destino) {
        this.destino = destino;
    }

    public CoordenadaDouble getActual() {
        return actual;
    }

    public void setActual(CoordenadaDouble actual) {
        this.actual = actual;
    }

    public ArrayList<CoordenadaDouble> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<CoordenadaDouble> ruta) {
        this.ruta = ruta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Double getvX() {
        return vX;
    }

    public void setvX(Double vX) {
        this.vX = vX;
    }

    public Double getvY() {
        return vY;
    }

    public void setvY(Double vY) {
        this.vY = vY;
    }
    
    
    
    
}
