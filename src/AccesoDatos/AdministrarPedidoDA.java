/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.cliente;
import Modelo.database;
import Modelo.persona;
import Modelo.aeropuerto;
import Modelo.pedido;
import Controlador.AdministrarClienteBL;
import Controlador.aeropuertoBL;
import Controlador.generalBL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Nowa
 */
public class AdministrarPedidoDA {
    
    
    private AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    private aeropuertoDA controlador_aeropuerto= new aeropuertoDA();
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
    
    
    public pedido obtenerPedido(int id_pedido){

      try{   
            database connect = new database();
            String query = "select * from pedido where id = " + id_pedido + ";";
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            
            
            while (rs.next( )){
                pedido pedido = new pedido();
                
                cliente cliente_emisor = new cliente();
                cliente cliente_receptor = new cliente();
                aeropuerto aeropuerto_emisor = new aeropuerto();
                aeropuerto aeropuerto_receptor = new aeropuerto();
                aeropuerto aeropuerto_actual = new aeropuerto();
             
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha_pedido(rs.getDate("fecha_pedido"));
                //pedido.setFecha_entrega(rs.getDate("fecha_entrega"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setMonto(rs.getDouble("monto"));
                
                pedido.setEstado("Enviado"); // <---POR CAMBIAR
                
                //obtener clientes
                cliente_emisor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_emisor"));
                cliente_receptor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_receptor"));
                pedido.setCliente_emisor(cliente_emisor);
                pedido.setCliente_receptor(cliente_receptor);
                
                //obtener aeropuertos
                aeropuerto_emisor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_emisor"));
                aeropuerto_receptor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_receptor"));
                aeropuerto_actual = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_actual"));
                pedido.setAeropuerto_emisor(aeropuerto_emisor);
                pedido.setAeropuerto_receptor(aeropuerto_receptor);
                pedido.setAeropuerto_actual(aeropuerto_actual);
                
                connect.closeConnection(); 
                return pedido;
            }
            connect.closeConnection(); 
            return null;
      }catch(Exception e){
            System.out.println("ERROR obtenerPedido "+e.getMessage());
            return null;
      }  
    }
   
    public pedido obtenerPedidoxCodigo(String codigo){

      try{   
            database connect = new database();
            String query = "select * from pedido where codigo = '" + codigo + "';";
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            
            
            while (rs.next( )){
                pedido pedido = new pedido();
                
                cliente cliente_emisor = new cliente();
                cliente cliente_receptor = new cliente();
                aeropuerto aeropuerto_emisor = new aeropuerto();
                aeropuerto aeropuerto_receptor = new aeropuerto();
                aeropuerto aeropuerto_actual = new aeropuerto();
             
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha_pedido(rs.getDate("fecha_pedido"));
                //pedido.setFecha_entrega(rs.getDate("fecha_entrega"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setMonto(rs.getDouble("monto"));
                
                pedido.setEstado("Enviado"); // <---POR CAMBIAR
                
                //obtener clientes
                cliente_emisor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_emisor"));
                cliente_receptor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_receptor"));
                pedido.setCliente_emisor(cliente_emisor);
                pedido.setCliente_receptor(cliente_receptor);
                
                //obtener aeropuertos
                aeropuerto_emisor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_emisor"));
                aeropuerto_receptor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_receptor"));
                aeropuerto_actual = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_actual"));
                pedido.setAeropuerto_emisor(aeropuerto_emisor);
                pedido.setAeropuerto_receptor(aeropuerto_receptor);
                pedido.setAeropuerto_actual(aeropuerto_actual);
                
                connect.closeConnection(); 
                return pedido;
            }
            connect.closeConnection(); 
            return null;
      }catch(Exception e){
            System.out.println("ERROR obtenerPedidoxCodigo "+e.getMessage());
            return null;
      }  
    }
    
    public ArrayList<pedido> listarPedidos(String codigo){ 
        try {
            ArrayList<pedido> listPedidos = new ArrayList<>();
            
            /*NO BORRAR
            database connect = new database();
            String query = "{CALL listarPedidos(?,?,?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            stmt.setString(1,codigo);
            stmt.setDate(2, d_fecha_entrega);
            stmt.setDate(4, d_fecha_registro);
            
            ResultSet rs = stmt.executeQuery();
            */
            database connect = new database();
            String query = "select * from pedido;";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                
                pedido pedido = new pedido();
                cliente cliente_emisor = new cliente();
                cliente cliente_receptor = new cliente();
                aeropuerto aeropuerto_emisor = new aeropuerto();
                aeropuerto aeropuerto_receptor = new aeropuerto();
                aeropuerto aeropuerto_actual = new aeropuerto();
             
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha_pedido(rs.getDate("fecha_pedido"));
                //pedido.setFecha_entrega(rs.getDate("fecha_entrega"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setMonto(rs.getDouble("monto"));
                
                pedido.setEstado("Enviado"); // <---POR CAMBIAR
                
                //obtener clientes
                cliente_emisor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_emisor"));
                cliente_receptor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_receptor"));
                pedido.setCliente_emisor(cliente_emisor);
                pedido.setCliente_receptor(cliente_receptor);
                
                //obtener aeropuertos
                aeropuerto_emisor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_emisor"));
                aeropuerto_receptor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_receptor"));
                aeropuerto_actual = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_actual"));
                pedido.setAeropuerto_emisor(aeropuerto_emisor);
                pedido.setAeropuerto_receptor(aeropuerto_receptor);
                pedido.setAeropuerto_actual(aeropuerto_actual);
                
                
                listPedidos.add(pedido);
            }  
            
            connect.closeConnection();
            System.out.println("Cantidad de resultados = " + listPedidos.size());
            return listPedidos;
        }catch(Exception e){
            System.out.println("ERROR en listarPedidos "+e.getMessage());
            return null;
        }
    }
    
    
    public boolean registrarPedido(pedido pedido){
        try{
        /*
        private String codigo;
        private Date fecha_pedido;
        private Date fecha_entrega;
        private String descripcion;
        private double monto;
        private cliente cliente_emisor;
        private aeropuerto aeropuerto_emisor;
        private cliente cliente_receptor;
        private aeropuerto aeropuerto_receptor;
        private aeropuerto aeropuerto_actual;
        private String estado;
        */          
            database connect = new database();

            String query="INSERT INTO pedido\n" +
                        "(codigo,\n" +
                        "fecha_pedido,\n" +
                        "descripcion,\n" +
                        "monto,\n" +
                        "id_cliente_emisor,\n" +
                        "id_aeropuerto_emisor,\n" +
                        "id_cliente_receptor,\n" +
                        "id_aeropuerto_receptor,\n" +
                        "id_aeropuerto_actual,\n" +
                        "id_estado)\n" +
                        "VALUES\n" +
                        "(?,?,?,?,?,?,?,?,?,?);"; 
	
                    PreparedStatement stmt = connect.getConnection().prepareStatement(query);
                           
                    stmt.setString(1, pedido.getCodigo());
                    stmt.setDate(2, general.manejo_fechas_24hs(pedido.getFecha_pedido()));
                    stmt.setString(3, pedido.getDescripcion());
                    stmt.setDouble(4, pedido.getMonto());
                    stmt.setInt(5, pedido.getCliente_emisor().getId());
                    stmt.setInt(6, pedido.getAeropuerto_emisor().getId());
                    stmt.setInt(7, pedido.getCliente_receptor().getId());
                    stmt.setInt(8, pedido.getAeropuerto_receptor().getId());
                    stmt.setInt(9, pedido.getAeropuerto_actual().getId());
                    stmt.setInt(10, general.obtenerIdGeneral("estado_pedido", pedido.getEstado()));
                    stmt.executeUpdate();
                    
            connect.closeConnection();

            return true;
        
        }catch(Exception ex){
             System.out.println("ERROR en registrarPedido " + ex.getMessage());
             return false;
         }
        
        
    }
    
     public boolean modificarPedido(pedido pedido){
        try{

             database connect = new database();

            String query="UPDATE pedido\n" +
                        "SET\n" +
                        "codigo = ?,\n" +
                        "fecha_pedido = ?,\n" +
                        "descripcion = ?,\n" +
                        "monto = ?,\n" +
                        "id_cliente_emisor = ?,\n" +
                        "id_aeropuerto_emisor = ?,\n" +
                        "id_cliente_receptor = ?,\n" +
                        "id_aeropuerto_receptor = ?,\n" +
                        "id_aeropuerto_actual = ?,\n" +
                        "id_estado = ?\n" +
                        "WHERE id = ?;"; 
                        
                    PreparedStatement stmt = connect.getConnection().prepareStatement(query);
                           
                    stmt.setString(1, pedido.getCodigo());
                    stmt.setDate(2, general.manejo_fechas_24hs(pedido.getFecha_pedido()));
                    stmt.setString(3, pedido.getDescripcion());
                    stmt.setDouble(4, pedido.getMonto());
                    stmt.setInt(5, pedido.getCliente_emisor().getId());
                    stmt.setInt(6, pedido.getAeropuerto_emisor().getId());
                    stmt.setInt(7, pedido.getCliente_receptor().getId());
                    stmt.setInt(8, pedido.getAeropuerto_receptor().getId());
                    stmt.setInt(9, pedido.getAeropuerto_actual().getId());
                    stmt.setInt(10, general.obtenerIdGeneral("estado_pedido", pedido.getEstado()));
                    stmt.setInt(11, pedido.getId());
                    System.out.println("query => " + query);
                    stmt.executeUpdate();
                    
            connect.closeConnection(); 
            return true;
        
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
        
        
    }
    
     public boolean eliminarPedido(int id_pedido){
        try{

            database connect = new database();
            String query = "{CALL eliminarPedido(?)}";
            

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            
            stmt.setInt(1, id_pedido);
            
            stmt.executeUpdate();
            
            
            
            return true;
        
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
        
        
    }
    
}
