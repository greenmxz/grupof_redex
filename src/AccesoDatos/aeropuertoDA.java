/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Controlador.generalBL;
import Modelo.database;
import Modelo.aeropuerto;
import Modelo.ciudad;
import Modelo.pais;
import Modelo.continente;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Nowa
 */
public class aeropuertoDA {
    /*
    private int id;
    private String nombre;
    private String codigo;
    private int capacidad_maxima;
    private int cantidad_paquetes;
    private ciudad ciudad;
    */
    private generalBL general = new generalBL();
    public aeropuerto obtenerAeropuerto(int id_aeropuerto){
        try {

            database connect = new database();
            String query = "select * from aeropuerto where id = " + id_aeropuerto + ";";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                ciudad ciudad = general.obtenerCiudad(rs.getInt("id_ciudad"));
                aeropuerto.setCiudad(ciudad.getNombre());
                //pais
                pais pais = general.obtenerPais(ciudad.getId_pais());
                aeropuerto.setPais(pais.getNombre());
                //continente
                continente continente = general.obtenerContinente(pais.getId_continente());
                aeropuerto.setContinente(continente.getNombre());
                
                connect.closeConnection(); 
                return aeropuerto;
            }
            connect.closeConnection();
            System.out.println("El aeropuerto no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR obtenerAeropuerto "+e.getMessage());
            return null;
        }
    }
    
    public aeropuerto obtenerAeropuertoxCiudad(String nombre_ciudad){
        try {

            database connect = new database();
            String query = "select aeropuerto.id, aeropuerto.nombre, aeropuerto.codigo, aeropuerto.capacidad_maxima, aeropuerto.cantidad_paquetes,\n" +
                            "continente.nombre as continente, pais.nombre as pais, ciudad.nombre as ciudad\n" +
                            "from aeropuerto\n" +
                            "inner join ciudad on aeropuerto.id_ciudad = ciudad.id\n" +
                            "inner join pais on ciudad.id_pais = pais.id\n" +
                            "inner join continente on pais.id_continente = continente.id where ciudad.nombre = '"+nombre_ciudad+"';";
            
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                aeropuerto.setCiudad(rs.getString("ciudad"));
                //pais
                aeropuerto.setPais(rs.getString("pais"));
                //continente
                aeropuerto.setContinente(rs.getString("continente"));
                
                connect.closeConnection(); 
                return aeropuerto;
            }
            connect.closeConnection();
            System.out.println("El aeropuerto no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR obtenerAeropuertoxCiudad "+e.getMessage());
            return null;
        }
    }
    
    
    
    
}
