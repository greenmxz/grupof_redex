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
    
    
    public pedido obtenerPedido(int id_pedido){

      try{   
            database connect = new database();
            String query = " select *  from pedido as p "
                    + " where p.id = " + id_pedido + ";";
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
                
                //pedido.setEstado("Enviado"); // <---POR CAMBIAR
                pedido.setEstado(rs.getString("id_estado")); 
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
            String query = "select * from pedido as p "
                    
                    + " where p.codigo = '" + codigo + "';";
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
                
                //pedido.setEstado("Enviado"); // <---POR CAMBIAR
                pedido.setEstado(rs.getString("id_estado")); 
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
    
    public ArrayList<pedido> listarPedidos(String codigo, String id_aeropuerto_origen, String id_aeropuerto_destino, String id_cliente_emisor, String id_cliente_receptor, String id_estado, String fecha_i, String fecha_f){ 
        try {
            ArrayList<pedido> listPedidos = new ArrayList<>();
            database connect = new database();
            String query = "select pedido.id, pedido.id_estado,pedido.codigo, pedido.fecha_pedido, pedido.descripcion, pedido.monto, pedido.fecha_entrega,\n" +
                            "aero_emisor.id as id_aeropuerto_emisor, aero_emisor.nombre as aeropuerto_emisor, aero_emisor.codigo as aeropuerto_emisor_codigo,\n" +
                            "aero_receptor.id as id_aeropuerto_receptor, aero_receptor.nombre as aeropuerto_receptor, aero_receptor.codigo as aeropuerto_receptor_codigo,\n" +
                            "aero_actual.id as id_aeropuerto_actual, aero_actual.nombre as aeropuerto_actual, aero_actual.codigo as aeropuerto_actual_codigo,\n" +
                            "cliente_emisor.id as id_cliente_emisor, cliente_emisor.codigo as codigo_cl_emisor,\n" +
                            "persona_emisor.numero_documento_identidad as cliente_emisor_dni,\n" +
                            "cliente_receptor.id as id_cliente_receptor, cliente_receptor.codigo as codigo_cl_receptor,\n" +
                            "persona_receptor.numero_documento_identidad as cliente_receptor_dni\n" +
                            "from pedido\n" +
                            
                            "inner join aeropuerto as aero_emisor on pedido.id_aeropuerto_emisor = aero_emisor.id\n" +
                            "inner join aeropuerto as aero_receptor on pedido.id_aeropuerto_receptor = aero_receptor.id\n" +
                            "inner join aeropuerto as aero_actual on pedido.id_aeropuerto_actual = aero_actual.id\n" +
                            "inner join cliente as cliente_emisor on pedido.id_cliente_emisor = cliente_emisor.id\n" +
                            "inner join persona as persona_emisor on cliente_emisor.id_persona = persona_emisor.id\n" +
                            "inner join cliente as cliente_receptor on pedido.id_cliente_receptor = cliente_receptor.id\n" +
                            "inner join persona as persona_receptor on cliente_receptor.id_persona = persona_receptor.id\n" +
                            "where pedido.activo = 1";
            
            //FILTROS
            boolean primero = true;
            if (!codigo.equals("")||!id_aeropuerto_origen.equals("")||!id_aeropuerto_destino.equals("")
                ||!id_cliente_emisor.equals("")||!id_cliente_receptor.equals("")
                ||!id_estado.equals("")||!fecha_i.equals("")||!fecha_f.equals("")){
                
                query += " and ";
                
                if (!codigo.equals("")){
                    query += " pedido.codigo = '" + codigo + "' ";
                    primero = false;
                }
                
                if (!id_aeropuerto_origen.equals("")){
                    if(!primero) query += " and ";
                    query += " pedido.id_aeropuerto_emisor = '" + id_aeropuerto_origen + "' ";
                    primero = false;
                }
                
                if (!id_aeropuerto_origen.equals("")){
                    if(!primero) query += " and ";
                    query += " pedido.id_aeropuerto_receptor = '" + id_aeropuerto_destino + "' ";
                    primero = false;
                }
                
                if (!id_cliente_emisor.equals("")){
                    if(!primero) query += " and ";
                    query += " pedido.id_cliente_emisor = '" + id_cliente_emisor + "' ";
                    primero = false;
                }
                
                if (!id_cliente_receptor.equals("")){
                    if(!primero) query += " and ";
                    query += " pedido.id_cliente_receptor = '" + id_cliente_receptor + "' ";
                    primero = false;
                }
                
            }
            
            query += ";";
            
            
            
            //////
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
                
               // pedido.setEstado("Enviado"); // <---POR CAMBIAR
               //pedido.setEstado(rs.getString("estado")); 
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
                        "id_estado,activo)\n" +
                        "VALUES\n" +
                        "(?,?,?,?,?,?,?,?,?,?,?);"; 
	
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
                    stmt.setString(10, pedido.getEstado());
                    stmt.setString(11, "1");
                    stmt.executeUpdate();
                    
            connect.closeConnection();

            return true;
        
        }catch(Exception ex){
             System.out.println("ERROR en registrarPedido " + ex.getMessage());
             return false;
         }
        
        
    }
    public String obtenerCorreosClientes(int pedido_id){
        try{
            database connect = new database();
            String query="select p1.correo as 'correo_cliente_emisor' ,p2.correo as 'correo_cliente_receptor' from pedido as p\n" +
                        "inner join cliente as c1 on c1.id = p.id_cliente_emisor\n" +
                        "inner join cliente as c2 on c2.id = p.id_cliente_receptor\n" +
                        "inner join persona as p1 on p1.id = c1.id_persona \n" +
                        "inner join persona as p2 on p2.id = c2.id_persona"
                        + " and p.id = " + pedido_id + ";";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                String correo=rs.getString("correo_cliente_emisor");
                correo+=",";
                correo+=rs.getString("correo_cliente_receptor");
                return correo;
            }
        }catch(Exception ex){
            System.out.println("ERROR en obtenerCorreosClientes " + ex.getMessage());
             return null;
        }
        return null;
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
                        "id_aeropuerto_actual = ?\n" +
                       
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
                    
                    stmt.setInt(10, pedido.getId());
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
            String query = "UPDATE pedido\n" +
                        "SET\n" +
                        "activo = 0\n" +
                        "WHERE id = ?;";
            
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
