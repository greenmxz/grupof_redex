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

    public paquete(String codigo, Date fechaSalida, 
            String aeropuertoOrigen, String aeropuertoDestino){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.clienteEmisor = String.valueOf(1);
        this.clienteReceptor = String.valueOf(2);
        this.estado = String.valueOf(1);
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

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getClienteEmisor() {
        return clienteEmisor;
    }

    public void setClienteEmisor(String clienteEmisor) {
        this.clienteEmisor = clienteEmisor;
    }

    public String getClienteReceptor() {
        return clienteReceptor;
    }

    public void setClienteReceptor(String clienteReceptor) {
        this.clienteReceptor = clienteReceptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContinenteOrigen() {
        return continenteOrigen;
    }

    public void setContinenteOrigen(String continenteOrigen) {
        this.continenteOrigen = continenteOrigen;
    }

    public String getContinenteDestino() {
        return continenteDestino;
    }

    public void setContinenteDestino(String continenteDestino) {
        this.continenteDestino = continenteDestino;
    }
    
    public void print(){
        System.out.println("Paquete " +
                this.getAeropuertoOrigen() + " -> " +
                this.getAeropuertoDestino() + " [" +
                String.valueOf(this.getFechaSalida()) + "]");
    }
}
