/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.CoordenadaDouble;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author Nowa
 */
public class avionDot {
    private CoordenadaDouble origen;
    private CoordenadaDouble destino;
    private CoordenadaDouble actual;
    private String ruta;
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
    private int capacidadActual;
    private int capacidadMax;
    private int id;
    private String icaoOrigen;
    private String icaoDestino;
    private int idAeroOrigen;
    private int idAeroDestino;
    private double t_llegada;
    private double t_restante;
    private int pack_finales;
    
    
    //MANEJO DE TIEMPOS, MANEJADO EN MINUTOS
    private int salidaMM;
    private int llegadaMM;
    private int llegaDiaSig;
    private int tiempoTranscurridoMM;
    private int tiempoTranscurridoMMAcu;
    
    
    private ArrayList<Integer> idPacks = new ArrayList<>();

    public double getT_llegada() {
        return abs((this.hora_llegada*60 + this.min_llegada) -(this.hora_salida*60 + this.min_salida));
    }

    public void setT_llegada(double t_llegada) {
        this.t_llegada = t_llegada;
    }

    public double getT_restante() {
        return t_restante;
    }

    public void setT_restante(double t_restante) {
        this.t_restante = t_restante;
    }
    
    public String getIcaoOrigen() {
        return icaoOrigen;
    }

    public void setIcaoOrigen(String icaoOrigen) {
        this.icaoOrigen = icaoOrigen;
    }

    public String getIcaoDestino() {
        return icaoDestino;
    }

    public void setIcaoDestino(String icaoDestino) {
        this.icaoDestino = icaoDestino;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
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

    public int getPack_finales() {
        return pack_finales;
    }

    public void setPack_finales(int pack_finales) {
        this.pack_finales = pack_finales;
    }

    public ArrayList<Integer> getIdPacks() {
        return idPacks;
    }

    public void setIdPacks(ArrayList<Integer> idPacks) {
        this.idPacks = idPacks;
    }

    public int getIdAeroOrigen() {
        return idAeroOrigen;
    }

    public void setIdAeroOrigen(int idAeroOrigen) {
        this.idAeroOrigen = idAeroOrigen;
    }

    public int getIdAeroDestino() {
        return idAeroDestino;
    }

    public void setIdAeroDestino(int idAeroDestino) {
        this.idAeroDestino = idAeroDestino;
    }

    public int getLlegaDiaSig() {
        return llegaDiaSig;
    }

    public void setLlegaDiaSig(int llegaDiaSig) {
        this.llegaDiaSig = llegaDiaSig;
    }

    public int getSalidaMM() {
        return salidaMM;
    }

    public void setSalidaMM(int salidaMM) {
        this.salidaMM = salidaMM;
    }

    public int getLlegadaMM() {
        return llegadaMM;
    }

    public void setLlegadaMM(int llegadaMM) {
        this.llegadaMM = llegadaMM;
    }

    public int getTiempoTranscurridoMM() {
        return tiempoTranscurridoMM;
    }

    public void setTiempoTranscurridoMM(int tiempoTranscurridoMM) {
        this.tiempoTranscurridoMM = tiempoTranscurridoMM;
    }

    public int getTiempoTranscurridoMMAcu() {
        return tiempoTranscurridoMMAcu;
    }

    public void setTiempoTranscurridoMMAcu(int tiempoTranscurridoMMAcu) {
        this.tiempoTranscurridoMMAcu = tiempoTranscurridoMMAcu;
    }
    
  
    
}
