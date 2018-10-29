/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Moises
 */
public class generalDA {
    
    
    public int obtenerIdGeneral(String codigo, String valor){
        try{
            continente continente = new continente();
            database connect = new database();
            String query =  "select tabla_general.id, tabla_general.codigo, tabla_general_detalle.valor \n" +
                            "from tabla_general\n" +
                            "inner join tabla_general_detalle on tabla_general_detalle.id_tabla_general = tabla_general.id\n" +
                            "where tabla_general_detalle.activo = 1 and tabla_general.codigo = '"+codigo+"' and\n" +
                            "tabla_general_detalle.valor = '"+valor+"';";
            
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                int id = rs.getInt("id");
                return id;
            }
            connect.closeConnection(); 
            return -1;
        
            
        }catch(Exception e){
            System.out.println("ERROR obtenerIdGeneral "+e.getMessage());
            return -1;
        }
    }
    
    public ArrayList<estado> listaEstados(String codigo){
        try{
            ArrayList<estado> lista_estados = new ArrayList();
            database connect = new database();
            String query =  "select tabla_general.id, tabla_general.codigo,tabla_general_detalle.id as id_tabla_general_detalle,tabla_general_detalle.valor \n" +
                            "from tabla_general\n" +
                            "inner join tabla_general_detalle on tabla_general_detalle.id_tabla_general = tabla_general.id\n" +
                            "where tabla_general_detalle.activo = 1 and tabla_general.codigo = '"+codigo+"';";
            
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            
            while (rs.next( )){
                estado estado = new estado();
                estado.setId_tabla_general(rs.getInt("id"));
                estado.setId_tabla_general_detalle(rs.getInt("id_tabla_general_detalle"));
                estado.setCodigo(codigo);
                estado.setValor(rs.getString("valor"));
                lista_estados.add(estado);
            }
 
            connect.closeConnection();
            return lista_estados;
        }catch(Exception e){
            System.out.println("ERROR listaEstados "+e.getMessage());
            return null;
        }
    }
    
    
    public java.sql.Date manejo_fechas(String s){
        //manejo de fechas;
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date fd =   formatter.parse(s);
                java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
                return sqlDate;
            }catch(Exception e){
                System.out.println("ERROR manejo_fechas "+e.getMessage());
            return null;
        }
    }
    
    public java.sql.Date manejo_fechas_24hs(java.util.Date fecha){
        //manejo de fechas;
            try {           
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // Or whatever format you need
                String s = sdf.format(fecha);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date fd =   formatter.parse(s);
                java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
                return sqlDate;
            }catch(Exception e){
                System.out.println("ERROR manejo_fechas_24hs "+e.getMessage());
            return null;
        }
    }
    
    
    
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
                ciudad.setId_pais(rs.getInt("id_pais"));
                listCiudad.add(ciudad);
            }
             
            connection.closeConnection();
            return listCiudad;
        }catch(Exception ex){
            return null;
        }
    }
    
    public ArrayList<pais> obtenerPaises(){
        try{
            database connection = new database();
            String query = "select * from pais ";
            Statement sentencia= connection.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            ArrayList<pais> listPais = new ArrayList<>();
             while (rs.next( )){
                pais pais=new pais();
                pais.setId(rs.getInt("id"));
                pais.setNombre(rs.getString("nombre"));
                pais.setId_continente(rs.getInt("id_continente"));
                listPais.add(pais);
            }
             
            connection.closeConnection();
            return listPais;
        }catch(Exception ex){
            return null;
        }
    }
    
    public ArrayList<continente> obtenerContinentes(){
        try{
            database connection = new database();
            String query = "select * from continente ";
            Statement sentencia= connection.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            ArrayList<continente> listCont = new ArrayList<>();
             while (rs.next( )){
                continente continente=new continente();
                continente.setId(rs.getInt("id"));
                continente.setNombre(rs.getString("nombre"));
                listCont.add(continente);
            }   
            connection.closeConnection();
            return listCont;
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
