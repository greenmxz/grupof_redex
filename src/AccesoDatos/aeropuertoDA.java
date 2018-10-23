/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.database;
import Modelo.aeropuerto;
import Modelo.ciudad;
import java.sql.CallableStatement;
import java.sql.ResultSet;

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
    
    public aeropuerto obtenerAeropuerto(int id_aeropuerto){
        try {
            
            database connect = new database();
            String query = "{CALL obtenerAeropuerto(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_aeropuerto);
           
            ResultSet rs = stmt.executeQuery();
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                ciudad ciudad = new ciudad();
                ciudad.setId(rs.getInt("id_ciudad"));
                ciudad.setNombre(rs.getString("ciudad"));
                
                aeropuerto.setCiudad(ciudad);
                return aeropuerto;
            }
            connect.closeConnection();
            System.out.println("El aeropuerto no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
    
    
    
    
    
}
