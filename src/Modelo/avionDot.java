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
    private String codigo;
    private CoordenadaDouble origen;
    private CoordenadaDouble destino;
    private CoordenadaDouble actual;
    private ArrayList<CoordenadaDouble>ruta;
    private String color; // verde -> amarillo -> rojo
    private boolean visible;
    private Double vX;
    private Double vY;
    private int hora_salida;
    private int min_salida;
    private int hora_llegada;
    private int min_llegada;
    private int estado_almacen;//0 : bajo / 1: medio/ 2: alto
    private int estado_mov;//0: en espera 1: en transito 2: llego a destino
    private int capacidadActual = 0;
    private int capacidadMax = 300;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    
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

    public int getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(int hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getMin_salida() {
        return min_salida;
    }

    public void setMin_salida(int min_salida) {
        this.min_salida = min_salida;
    }

    public int getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(int hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public int getMin_llegada() {
        return min_llegada;
    }

    public void setMin_llegada(int min_llegada) {
        this.min_llegada = min_llegada;
    }

    public int getEstado_almacen() {
        return estado_almacen;
    }

    public void setEstado_almacen(int estado_almacen) {
        this.estado_almacen = estado_almacen;
    }

    public int getEstado_mov() {
        return estado_mov;
    }

    public void setEstado_mov(int estado_mov) {
        this.estado_mov = estado_mov;
    }    

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }
    
    
}
