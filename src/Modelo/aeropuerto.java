/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Nowa
 */
public class aeropuerto {
    private int id;
    private String nombre;
    private String codigo;
    private int capacidad_maxima;
    private int cantidad_paquetes;
    private ciudad ciudad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public int getCantidad_paquetes() {
        return cantidad_paquetes;
    }

    public void setCantidad_paquetes(int cantidad_paquetes) {
        this.cantidad_paquetes = cantidad_paquetes;
    }

    public ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
}
