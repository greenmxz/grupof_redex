/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.aeropuerto;
import AccesoDatos.aeropuertoDA;
/**
 *
 * @author Nowa
 */
public class aeropuertoBL {
    private aeropuertoDA AdministrarAeropuertoDA ;
    public aeropuertoBL(){
        AdministrarAeropuertoDA = new aeropuertoDA();
    }
    
    public aeropuerto obtenerAeropuerto(int id_aeropuerto){
        return AdministrarAeropuertoDA.obtenerAeropuerto(id_aeropuerto);
    }
    public aeropuerto obtenerAeropuertoxCiudad(String nombre_ciudad){
        return AdministrarAeropuertoDA.obtenerAeropuertoxCiudad(nombre_ciudad);
    }
}
