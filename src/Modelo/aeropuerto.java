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
    private String continente;
    private String pais;
    private String ciudad;

    public aeropuerto(){
        
    }
    
    public aeropuerto(int id, String nombre, String codigo, String continente,
            String pais, String ciudad){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.capacidad_maxima = 750;
        this.cantidad_paquetes = 500;
        this.continente = continente;
        this.pais = pais;
        this.ciudad = ciudad;
    }
    
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

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void print(){
        System.out.println("Airport " + String.valueOf(this.getNombre()) +
                " (" + this.getCodigo() + ") - [" +
                String.valueOf(this.getContinente()) + "," + 
                String.valueOf(this.getPais()) + "," + 
                String.valueOf(this.getCiudad()) + "]");
    }

}
