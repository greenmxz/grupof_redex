package Modelo;

import java.util.Date;

public class Vuelo {
    private String codigo;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String continenteOrigen;
    private String continenteDestino;
    private int capMax;
    private int capActual;
    private String estado;
    
    public Vuelo(String codigo, Date fechaSalida, Date fechaLlegada, 
            String continenteOrigen, String continenteDestino, int capMax, int capActual,
            String estado){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.continenteOrigen = continenteOrigen;
        this.continenteDestino = continenteDestino;
        this.capMax = capMax;
        this.capActual = capActual;
        this.estado = estado;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the fechaLlegada
     */
    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * @param fechaLlegada the fechaLlegada to set
     */
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * @return the continenteOrigen
     */
    public String getContinenteOrigen() {
        return continenteOrigen;
    }

    /**
     * @param continenteOrigen the continenteOrigen to set
     */
    public void setContinenteOrigen(String continenteOrigen) {
        this.continenteOrigen = continenteOrigen;
    }

    /**
     * @return the continenteDestino
     */
    public String getContinenteDestino() {
        return continenteDestino;
    }

    /**
     * @param continenteDestino the continenteDestino to set
     */
    public void setContinenteDestino(String continenteDestino) {
        this.continenteDestino = continenteDestino;
    }

    /**
     * @return the capMax
     */
    public int getCapMax() {
        return capMax;
    }

    /**
     * @param capMax the capMax to set
     */
    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }

    /**
     * @return the capActual
     */
    public int getCapActual() {
        return capActual;
    }

    /**
     * @param capActual the capActual to set
     */
    public void setCapActual(int capActual) {
        this.capActual = capActual;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
