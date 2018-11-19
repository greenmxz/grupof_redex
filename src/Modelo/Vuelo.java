package Modelo;

import java.util.Date;

public class Vuelo {
    private String codigo;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String aeropuertoOrigen;
    private int idAeropuertoOrigen;
    private String aeropuertoDestino;
    private int idAeropuertoDestino;

    public int getIdAeropuertoOrigen() {
        return idAeropuertoOrigen;
    }

    public void setIdAeropuertoOrigen(int idAeropuertoOrigen) {
        this.idAeropuertoOrigen = idAeropuertoOrigen;
    }

    public int getIdAeropuertoDestino() {
        return idAeropuertoDestino;
    }

    public void setIdAeropuertoDestino(int idAeropuertoDestino) {
        this.idAeropuertoDestino = idAeropuertoDestino;
    }
    private int capMax;
    private int capActual;
    private String estado;
    
    public Vuelo(){
        this.capMax = 200;
        this.capActual = 0;
    }
    
    public Vuelo(String codigo, Date fechaSalida, Date fechaLlegada, 
            String aeropuertoOrigen, String aeropuertoDestino, int capMax, int capActual,
            String estado){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.capMax = capMax;
        this.capActual = capActual;
        this.estado = estado;
    }

    public Vuelo(String codigo, Date fechaSalida, Date fechaLlegada, 
            String aeropuertoOrigen, String aeropuertoDestino){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.capMax = 200;
        this.capActual = 150;
        this.estado = "Estable";
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    public int getCapActual() {
        return capActual;
    }

    public void setCapActual(int capActual) {
        this.capActual = capActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /* Methods */
    public void print(){
        System.out.println("Vuelo " +
                this.getAeropuertoOrigen() +
                " -> " + this.getAeropuertoDestino() + " [" +
                String.valueOf(this.getFechaSalida()) + " - " +
                String.valueOf(this.getFechaLlegada()) + "]");
    }
}
