package Modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class paquete {
    private int id;
    private String codigo;
    private Date fechaSalida;
    private Date fechaLlegada;
    private String aeropuertoOrigen;
    private int aeropuertoOrigenId;
    private String continenteOrigen;
    private String ciudadOrigen;
    private String aeropuertoDestino;
    private int aeropuertoDestinoId;
    private String continenteDestino;
    private String ciudadDestino;
    private String clienteEmisor;
    private String clienteReceptor;
    private String estado;
    
    public paquete(String codigo, Date fechaSalida, 
            String aeropuertoOrigen, String aeropuertoDestino,
            String clienteEmisor, String clienteReceptor,
            String estado){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
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
    
    public paquete(String codigo, Date fechaSalida, 
            int aeropuertoOrigen, int aeropuertoDestino){
        this.codigo = codigo;
        this.fechaSalida = fechaSalida;
        this.aeropuertoOrigenId = aeropuertoOrigen;
        this.aeropuertoDestinoId = aeropuertoDestino;
        try{
            database connect = new database();
            Statement sentencia = connect.getConnection().createStatement();
            String query = "SELECT codigo FROM redexdb.aeropuerto WHERE id = '" +
                    String.valueOf(aeropuertoOrigenId) + "'";
            ResultSet rs = sentencia.executeQuery(query);
            if(rs.next()){
                this.aeropuertoOrigen = rs.getString("codigo");
            }
            
            sentencia = connect.getConnection().createStatement();
            query = "SELECT codigo FROM redexdb.aeropuerto WHERE id = '" +
                    String.valueOf(aeropuertoDestinoId) + "'";
            rs = sentencia.executeQuery(query);
            if(rs.next()){
                this.aeropuertoDestino = rs.getString("codigo");
            }
            
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR obtenerPaquetesAsign "+e.getMessage());
        }
        this.clienteEmisor = String.valueOf(1);
        this.clienteReceptor = String.valueOf(2);
        this.estado = String.valueOf(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAeropuertoOrigenId() {
        return aeropuertoOrigenId;
    }

    public void setAeropuertoOrigenId(int aeropuertoOrigenId) {
        this.aeropuertoOrigenId = aeropuertoOrigenId;
    }

    public int getAeropuertoDestinoId() {
        return aeropuertoDestinoId;
    }

    public void setAeropuertoDestinoId(int aeropuertoDestinoId) {
        this.aeropuertoDestinoId = aeropuertoDestinoId;
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
                String.valueOf(new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(fechaSalida))+ "]");
    }
}
