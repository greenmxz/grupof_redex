/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Moises
 */
public class generalDA {
    
    
    
    public ciudad obtenerCiudad(int id_ciudad){
        try{
            ciudad ciudad = new ciudad();
            database connect = new database();
            String query = "select * from ciudad where id = " + id_ciudad + ";";
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                ciudad.setId(id_ciudad);
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setId_pais(rs.getInt("id_pais"));
                return ciudad;
            }
            connect.closeConnection(); 
            return null;
        }catch(Exception e){
            System.out.println("ERROR en obtenerCiudad "+e.getMessage());
            return null;
        }
    
    }
    
    public pais obtenerPais(int id_pais){
        try{
            pais pais = new pais();
            database connect = new database();
            String query = "select * from pais where id = " + id_pais + ";";
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                pais.setId(id_pais);
                pais.setNombre(rs.getString("nombre"));
                pais.setId_continente(rs.getInt("id_continente"));
                return pais;            
            }
            connect.closeConnection(); 
            return null;
        }catch(Exception e){
            System.out.println("ERROR en obtenerPais "+e.getMessage());
            return null;
        }
    
    }
    
    public continente obtenerContinente(int id_continente){
        try{
            continente continente = new continente();
            database connect = new database();
            String query = "select * from continente where id = " + id_continente + ";";
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                continente.setId(id_continente);
                continente.setNombre(rs.getString("nombre"));
                return continente;              
            }
            connect.closeConnection(); 
            return null;
        }catch(Exception e){
            System.out.println("ERROR en obtenerContinente "+e.getMessage());
            return null;
        }
    
    }
    
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
    
    public ArrayList<ciudad> obtenerCiudades(){
        try{
            database connection = new database();
            String query = "select * from ciudad ";
            Statement sentencia= connection.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            ArrayList<ciudad> listCiudad = new ArrayList<>();
             while (rs.next( )){
                ciudad ciudad=new ciudad();
                ciudad.setId(rs.getInt("id"));
                ciudad.setNombre(rs.getString("nombre"));
                listCiudad.add(ciudad);
            }
             
            connection.closeConnection();
            return listCiudad;
        }catch(Exception ex){
            return null;
        }
    }
    
    public ArrayList<tipoDocumento> obtenerTipoDocumentos(){
        try{
            database connection = new database();
            String query = "SELECT tgd.id ,tgd.valor as nombre FROM tabla_general as tg inner join tabla_general_detalle as tgd \n" +
                            "on tg.id=tgd.id_tabla_general \n" +
                            "where tg.codigo = 'tipo_documento' and tgd.activo=true;";
            Statement sentencia= connection.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            ArrayList<tipoDocumento> listTipoDocumento = new ArrayList<>();
             while (rs.next( )){
                tipoDocumento tipoDocumento=new tipoDocumento();
                tipoDocumento.setId(rs.getInt("id"));
                tipoDocumento.setNombre(rs.getString("nombre"));
                listTipoDocumento.add(tipoDocumento);
            }
             
            connection.closeConnection();
            return listTipoDocumento;
        }catch(Exception ex){
            return null;
        }
    }
}
