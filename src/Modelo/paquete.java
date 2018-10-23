package Modelo;

import java.util.Date;

public class paquete {
    private String codigo;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String aeropuertoOrigen;
    private String continenteOrigen;
    private String ciudadOrigen;
    private String aeropuertoDestino;
    private String continenteDestino;
    private String ciudadDestino;
    private String clienteEmisor;
    private String clienteReceptor;
    private String estado;
    
    public paquete(String codigo, Date fechaSalida, Date fechaLlegada, 
            String aeropuertoOrigen, String continenteOrigen, String ciudadOrigen, String aeropuertoDestino,
            String continenteDestino, String ciudadDestino, String clienteEmisor, String clienteReceptor,
            String estado){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.continenteOrigen = continenteOrigen;
        this.ciudadOrigen = ciudadOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.continenteDestino = continenteDestino;
        this.ciudadDestino = ciudadDestino;
        this.clienteEmisor = clienteEmisor;
        this.clienteReceptor = clienteReceptor;
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
    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    /**
     * @param continenteOrigen the continenteOrigen to set
     */
    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    /**
     * @return the ciudadOrigen
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * @param ciudadOrigen the ciudadOrigen to set
     */
    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    /**
     * @return the continenteDestino
     */
    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    /**
     * @param continenteDestino the continenteDestino to set
     */
    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    /**
     * @return the ciudadDestino
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * @param ciudadDestino the ciudadDestino to set
     */
    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    /**
     * @return the clienteEmisor
     */
    public String getClienteEmisor() {
        return clienteEmisor;
    }

    /**
     * @param clienteEmisor the clienteEmisor to set
     */
    public void setClienteEmisor(String clienteEmisor) {
        this.clienteEmisor = clienteEmisor;
    }

    /**
     * @return the clienteReceptor
     */
    public String getClienteReceptor() {
        return clienteReceptor;
    }

    /**
     * @param clienteReceptor the clienteReceptor to set
     */
    public void setClienteReceptor(String clienteReceptor) {
        this.clienteReceptor = clienteReceptor;
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
}
