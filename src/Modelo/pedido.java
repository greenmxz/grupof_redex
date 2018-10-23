/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Nowa
 */
public class pedido {
    private int id;
    private String codigo;
    private Date fecha_pedido;
    private Date fecha_entrega;
    private String descripcion;
    private double monto;
    private cliente cliente_emisor;
    private aeropuerto aeropuerto_emisor;
    private cliente cliente_receptor;
    private aeropuerto aeropuerto_receptor;
    private aeropuerto aeropuerto_actual;
    private String estado;

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


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public cliente getCliente_emisor() {
        return cliente_emisor;
    }

    public void setCliente_emisor(cliente cliente_emisor) {
        this.cliente_emisor = cliente_emisor;
    }

    public aeropuerto getAeropuerto_emisor() {
        return aeropuerto_emisor;
    }

    public void setAeropuerto_emisor(aeropuerto aeropuerto_emisor) {
        this.aeropuerto_emisor = aeropuerto_emisor;
    }

    public cliente getCliente_receptor() {
        return cliente_receptor;
    }

    public void setCliente_receptor(cliente cliente_receptor) {
        this.cliente_receptor = cliente_receptor;
    }

    public aeropuerto getAeropuerto_receptor() {
        return aeropuerto_receptor;
    }

    public void setAeropuerto_receptor(aeropuerto aeropuerto_receptor) {
        this.aeropuerto_receptor = aeropuerto_receptor;
    }

    public aeropuerto getAeropuerto_actual() {
        return aeropuerto_actual;
    }

    public void setAeropuerto_actual(aeropuerto aeropuerto_actual) {
        this.aeropuerto_actual = aeropuerto_actual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    
}
