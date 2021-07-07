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
    private int capMax;
    private int capActual;
    private String continente;
    private String pais;
    private String ciudad;
    private double coordX;
    private double coordY;
    private String estado;
    
    public aeropuerto(){
        this.estado = "Estable";
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
    public aeropuerto(int id, String nombre, String codigo, String continente,
            String pais, String ciudad){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.capMax = 1000;
        this.capActual = 0;
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

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    
    
    public void print(){
        System.out.println("Airport " + String.valueOf(this.getNombre()) +
                " (" + this.getCodigo() + ") - [" +
                String.valueOf(this.getContinente()) + "," + 
                String.valueOf(this.getPais()) + "," + 
                String.valueOf(this.getCiudad()) + "]");
    }

}
