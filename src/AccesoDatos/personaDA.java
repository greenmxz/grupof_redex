/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.cliente;
import Modelo.persona;
import Modelo.database;
import Controlador.generalBL;
import Modelo.ciudad;
import Modelo.continente;
import Modelo.pais;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nowa
 */
public class personaDA {
    
    
    private generalBL general = new generalBL();
    
    public java.sql.Date manejo_fechas(String s){
        //manejo de fechas;
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fd =   formatter.parse(s);
                java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
                return sqlDate;
            }catch(Exception e){
                System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
    
    public boolean insertarPersona(persona persona){
        try{
            database connect = new database();
            
            //registrar persona
           
            String queryPersona="insert into persona (nombre,apellido_paterno,apellido_materno,"
                    + "numero_documento_identidad,direccion,fecha_nacimiento,id_ciudad,id_tipo_documento,correo,telefono)"
                    + "values (?,?,?,?,?,?,(select id from ciudad where nombre =?),(select id from tabla_general_detalle where valor = ?),?,?)"; 
            
            
            PreparedStatement stmt = connect.getConnection().prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
            
            //manejo de fechas;
            
            SimpleDateFormat  sdf;
            String            s;
            sdf = new SimpleDateFormat("yyyy-MM-dd");  // Or whatever format you need
            s = sdf.format(persona.getFechaNacimiento()); 
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fd =   formatter.parse(s);
            java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
            
            //stmt.registerOutParameter("id",java.sql.Types.INTEGER );
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellidoPaterno());
            stmt.setString(3,persona.getApellidoMaterno());
            stmt.setInt(4,persona.getNumeroDocumentoIdentidad());
            stmt.setString(5,persona.getDireccion());
            stmt.setDate(6,sqlDate);
            System.out.println("city "+persona.getCiudad());
            System.out.println("number "+persona.getTipoDocumento());
            stmt.setString(7,persona.getCiudad());
            //stmt.setString(8,persona.getTipoDocumento());
            stmt.setString(8, "DNI");
            stmt.setString(9, persona.getCorreo());
            stmt.setString(10, persona.getTelefono());
            int count = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            
            
            return true;
        }catch(Exception e){
                System.out.println("ERROR "+e.getMessage());
            return false;
        }
      
    }
    
    public boolean modificarPersona(persona persona){
        try{
            database connect = new database();
            
            //actualizar persona
           
           String queryPersona = "UPDATE persona\n" +
                                "SET\n" +
                                "nombre = ?,\n" +
                                "apellido_paterno = ?,\n" +
                                "apellido_materno = ?,\n" +
                                "numero_documento_identidad = ?,\n" +
                                "direccion = ?,\n" +
                                "fecha_nacimiento = ?,\n" +
                                "id_ciudad = (select id from ciudad where nombre =?),\n" +
                                "telefono = ?,\n" +
                                "correo = ?\n" +
                                "WHERE numero_documento_identidad = ?;";
           
            PreparedStatement stmt = connect.getConnection().prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
            
            //manejo de fechas;
            
            SimpleDateFormat  sdf;
            String            s;
            sdf = new SimpleDateFormat("yyyy-MM-dd");  // Or whatever format you need
            s = sdf.format(persona.getFechaNacimiento()); 
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fd =   formatter.parse(s);
            java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
            

            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellidoPaterno());
            stmt.setString(3,persona.getApellidoMaterno());
            stmt.setInt(4,persona.getNumeroDocumentoIdentidad());
            stmt.setString(5,persona.getDireccion());
            stmt.setDate(6,sqlDate);
            stmt.setString(7,persona.getCiudad());
            stmt.setString(8,persona.getTelefono());
            stmt.setString(9, persona.getCorreo());
            stmt.setInt(10,persona.getNumeroDocumentoIdentidad());
            
            System.out.println("query => "+ queryPersona);
            
            int count = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            System.out.println("up_per : " + count);
            
            connect.closeConnection(); 
            return true;
        }catch(Exception e){
                System.out.println("ERROR "+e.getMessage());
            return false;
        }
      
    }
    
    public persona obtenerPersona(int id_persona){
        try {
            /*
            database connect = new database();
            String query = "{CALL obtenerPersona(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_persona);
           
            ResultSet rs = stmt.executeQuery();
            */
            database connect = new database();
            String query = "select * from persona where id = " + id_persona + ";";
            Statement sentencia= connect.getConnection().createStatement();
            System.out.println("query => "+ query);
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){

                persona persona = new persona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setNumeroDocumentoIdentidad(rs.getInt("numero_documento_identidad"));
                //persona.setTipoDocumento(general.obtenerTipoDocumentos().get(rs.getInt("tipo_documento")).getNombre());
                persona.setDireccion(rs.getString("direccion"));
                persona.setCorreo(rs.getString("correo"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                //ciudad
                ciudad ciudad = general.obtenerCiudad(rs.getInt("id_ciudad"));
                persona.setCiudad(ciudad.getNombre());
                //pais
                pais pais = general.obtenerPais(ciudad.getId_pais());
                persona.setPais(pais.getNombre());
                //continente
                continente continente = general.obtenerContinente(pais.getId_continente());
                persona.setContinente(continente.getNombre());
                
                connect.closeConnection(); 
                return persona;
            }
            connect.closeConnection();
            System.out.println("La persona no ha sido encontrada");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR en obtenerPersona "+e.getMessage());
            return null;
        }
    }
    
    public persona obtenerPersonaxDNI(int dni){
        try {
            /*
            database connect = new database();
            String query = "{CALL obtenerPersona(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_persona);
           
            ResultSet rs = stmt.executeQuery();
            */
            database connect = new database();
            String query = "select * from persona where numero_documento_identidad like '%" + dni + "%';";
            System.out.println("query => "+ query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){

                persona persona = new persona();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setNumeroDocumentoIdentidad(rs.getInt("numero_documento_identidad"));
                //persona.setTipoDocumento(general.obtenerTipoDocumentos().get(rs.getInt("tipo_documento")).getNombre());
                persona.setDireccion(rs.getString("direccion"));
                persona.setCorreo(rs.getString("correo"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                //ciudad
                ciudad ciudad = general.obtenerCiudad(rs.getInt("id_ciudad"));
                persona.setCiudad(ciudad.getNombre());
                //pais
                pais pais = general.obtenerPais(ciudad.getId_pais());
                persona.setPais(pais.getNombre());
                //continente
                continente continente = general.obtenerContinente(pais.getId_continente());
                persona.setContinente(continente.getNombre());
                
                
                connect.closeConnection(); 
                return persona;
            }
            connect.closeConnection();
            System.out.println("La persona no ha sido encontrada");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
}

