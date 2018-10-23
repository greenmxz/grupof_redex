/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;


import Modelo.database;
import Modelo.persona;
import Modelo.cliente;
import Modelo.usuario;
import Controlador.personaBL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Nowa
 */
public class AdministrarClienteDA {
    
    private personaBL controlador_persona = new personaBL();
    
    public cliente obtenerCliente(int id_cliente){
        try {
            
            database connect = new database();
            String query = "select * from cliente where id = " + id_cliente + ";";
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            
            /*
            database connect = new database();
            String query = "{CALL obtenerCliente(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_cliente);
           
            ResultSet rs = stmt.executeQuery();
            */
            
            while (rs.next( )){

                
                
                cliente cliente = new cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setCantidad_pedidos(rs.getInt("cantidad_pedidos"));
                cliente.setCodigo(rs.getString("codigo"));
                
                //obtener persona
                cliente.setPersona(controlador_persona.obtenerPersona(rs.getInt("id_persona")));
                //
                
                
                return cliente;
            }
            connect.closeConnection();
            System.out.println("El cliente no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
    
    
    
    
    
    public ArrayList<cliente> listarClientes(int numeroDocumentoIdentidad,String nombre, String apellidoPaterno, String apellidoMaterno){ 
        try {
            ArrayList<cliente> listClientes = new ArrayList<>();
            /* NO BORRAR
            database connect = new database();
            String query = "{CALL listarClientes(?,?,?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, numeroDocumentoIdentidad);
            stmt.setString(2, nombre);
            stmt.setString(3, apellidoPaterno);
            stmt.setString(4, apellidoMaterno);

            ResultSet rs = stmt.executeQuery();
            */
            
            database connect = new database();
            String query = "select * from cliente;";
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){

                persona persona= new persona();
                cliente cliente = new cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setCantidad_pedidos(rs.getInt("cantidad_pedidos"));
                
                persona = controlador_persona.obtenerPersona(rs.getInt("id_persona"));
                
                persona.setNombre(persona.getNombre());
                
                persona.setApellidoPaterno(persona.getApellidoPaterno());
                persona.setApellidoMaterno(persona.getApellidoMaterno());
                persona.setNumeroDocumentoIdentidad(persona.getNumeroDocumentoIdentidad());
                persona.setTipoDocumento(persona.getTipoDocumento());
                
                
                cliente.setPersona(persona);
                
                listClientes.add(cliente);
            }  
            
            connect.closeConnection();
            System.out.println("Cantidad de resultados = " + listClientes.size());
            return listClientes;
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
    
     public boolean registrarCliente(cliente cliente){
         try {
            database connect = new database();
            /*
            cantidad_pedidos
            doc_persona
            id_documento
            codigo_cliente
            */
  
            String query = "{CALL registrarCliente(?,?,?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            stmt.setInt(1,cliente.getCantidad_pedidos());
            stmt.setInt(2, cliente.getPersona().getNumeroDocumentoIdentidad());
            stmt.setString(3, cliente.getPersona().getTipoDocumento());
            stmt.setString(4, cliente.getCodigo());

            stmt.executeUpdate();
            
            
            
            return true;
            

            
         }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
         
     }
    
    public boolean modificarCliente(cliente cliente){
         try {
            database connect = new database();
            /*
            cantidad_pedidos
            doc_persona
            id_documento
            codigo_cliente
            */
            String query = "{CALL modificarCliente(?,?,?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            stmt.setInt(1,cliente.getCantidad_pedidos());
            stmt.setInt(2, cliente.getPersona().getNumeroDocumentoIdentidad());
            stmt.setString(3, cliente.getPersona().getTipoDocumento());
            stmt.setString(4, cliente.getCodigo());

            stmt.executeUpdate();
            
            
            
            return true;
            

            
         }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
         
     }
    
    
    public boolean eliminarCliente(int id_cliente){
         try {
            database connect = new database();
            /*
            codigo_cliente
            */
            String query = "{CALL eliminarCliente(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            

            stmt.setInt(1, id_cliente);

            stmt.executeUpdate();
            
            
            
            return true;
            

            
         }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
         
     }
}
