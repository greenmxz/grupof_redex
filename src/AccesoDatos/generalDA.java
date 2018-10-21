/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.database;
import Modelo.rol;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Moises
 */
public class generalDA {
    public ArrayList<rol> obtenerRoles(){
        try{
            database connection = new database();
            String query = "select * from rol";
            Statement sentencia= connection.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            ArrayList<rol> listRol = new ArrayList<>();
             while (rs.next( )){
                rol rol=new rol();
                rol.setId(rs.getInt("id"));
                rol.setNombre(rs.getString("nombre"));
                listRol.add(rol);
            }
             
            connection.closeConnection();
            return listRol;
        }catch(Exception ex){
            return null;
        }
        
    }
}
