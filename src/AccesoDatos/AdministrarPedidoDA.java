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
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Nowa
 */
public class AdministrarPedidoDA {
    
    
    private AdministrarClienteBL controlador_cliente = new AdministrarClienteBL();
    private aeropuertoDA controlador_aeropuerto= new aeropuertoDA();
    
    
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
            String query = "{CALL obtenerPedido(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_pedido);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next( )){
                pedido pedido = new pedido();
                
                cliente cliente_emisor = new cliente();
                cliente cliente_receptor = new cliente();
                aeropuerto aeropuerto_emisor = new aeropuerto();
                aeropuerto aeropuerto_receptor = new aeropuerto();
                aeropuerto aeropuerto_actual = new aeropuerto();
             
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha_pedido(rs.getDate("fecha_registro"));
                pedido.setFecha_entrega(rs.getDate("fecha_entrega"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setEstado("estado");
                //obtener clientes
                cliente_emisor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_emisor"));
                cliente_receptor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_receptor"));
                //
                
                pedido.setCliente_emisor(cliente_emisor);
                pedido.setCliente_receptor(cliente_receptor);
                
                //obtener aeropuertos
                aeropuerto_emisor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_emisor"));
                aeropuerto_receptor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_receptor"));
                aeropuerto_actual = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_actual"));
                //
                pedido.setAeropuerto_emisor(aeropuerto_emisor);
                pedido.setAeropuerto_receptor(aeropuerto_receptor);
                pedido.setAeropuerto_actual(aeropuerto_actual);
                
                
                return pedido;
            }
            return null;
      }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
      }  
    }
   
    
    
    public ArrayList<pedido> listarPedidos(String codigo, String fecha_entrega, String fecha_registro){ 
        try {
            SimpleDateFormat  sdf;
            String            s_fecha_entrega, s_fecha_registro;
            java.sql.Date     d_fecha_entrega, d_fecha_registro;
            
            sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            s_fecha_entrega = sdf.format(fecha_entrega);
            s_fecha_registro = sdf.format(fecha_registro);
            d_fecha_entrega = manejo_fechas(s_fecha_entrega);
            d_fecha_registro = manejo_fechas(s_fecha_registro);
            
            database connect = new database();
            String query = "{CALL listarPedidos(?,?,?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            stmt.setString(1,codigo);
            stmt.setDate(2, d_fecha_entrega);
            stmt.setDate(4, d_fecha_registro);
            
            ArrayList<pedido> listPedidos = new ArrayList<>();
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next( )){
                
                pedido pedido = new pedido();
                cliente cliente_emisor = new cliente();
                cliente cliente_receptor = new cliente();
                aeropuerto aeropuerto_emisor = new aeropuerto();
                aeropuerto aeropuerto_receptor = new aeropuerto();
                aeropuerto aeropuerto_actual = new aeropuerto();
             
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha_pedido(rs.getDate("fecha_registro"));
                pedido.setFecha_entrega(rs.getDate("fecha_entrega"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setEstado("estado");
                //obtener clientes
                cliente_emisor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_emisor"));
                cliente_receptor = controlador_cliente.obtenerCliente(rs.getInt("id_cliente_receptor"));
                //
                
                pedido.setCliente_emisor(cliente_emisor);
                pedido.setCliente_receptor(cliente_receptor);
                
                //obtener aeropuertos
                aeropuerto_emisor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_emisor"));
                aeropuerto_receptor = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_receptor"));
                aeropuerto_actual = controlador_aeropuerto.obtenerAeropuerto(rs.getInt("id_aeropuerto_actual"));
                //
                
                pedido.setAeropuerto_emisor(aeropuerto_emisor);
                pedido.setAeropuerto_receptor(aeropuerto_receptor);
                pedido.setAeropuerto_actual(aeropuerto_actual);
                
                
                listPedidos.add(pedido);
            }  
            
            connect.closeConnection();
            System.out.println("Cantidad de resultados = " + listPedidos.size());
            return listPedidos;
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
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
            String query = "{CALL registrarPedido(?,?,?,?,?,?,?,?,?,?,?)}";
            
            SimpleDateFormat  sdf;
            String            s_fecha_entrega, s_fecha_pedido;
            java.sql.Date     d_fecha_entrega, d_fecha_pedido;
            
            sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            s_fecha_entrega = sdf.format(pedido.getFecha_entrega());
            s_fecha_pedido = sdf.format(pedido.getFecha_pedido());
            d_fecha_entrega = manejo_fechas(s_fecha_entrega);
            d_fecha_pedido = manejo_fechas(s_fecha_pedido);

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            
            stmt.setString(1, pedido.getCodigo());
            stmt.setDate(2, d_fecha_pedido);
            stmt.setDate(3, d_fecha_entrega);
            stmt.setString(4, pedido.getDescripcion());
            stmt.setDouble(5, pedido.getMonto());
            stmt.setInt(6,pedido.getCliente_emisor().getId());
            stmt.setInt(7, pedido.getAeropuerto_emisor().getId());
            stmt.setInt(8,pedido.getCliente_receptor().getId());
            stmt.setInt(9, pedido.getAeropuerto_receptor().getId());
            stmt.setInt(10, pedido.getAeropuerto_actual().getId());
            stmt.setString(11, pedido.getEstado());
            
            stmt.executeUpdate();
            
            
            
            return true;
        
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
        
        
    }
    
     public boolean modificarPedido(pedido pedido){
        try{

            database connect = new database();
            String query = "{CALL modificarPedido(?,?,?,?,?,?,?,?,?,?,?)}";
            
            SimpleDateFormat  sdf;
            String            s_fecha_entrega, s_fecha_pedido;
            java.sql.Date     d_fecha_entrega, d_fecha_pedido;
            
            sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            s_fecha_entrega = sdf.format(pedido.getFecha_entrega());
            s_fecha_pedido = sdf.format(pedido.getFecha_pedido());
            d_fecha_entrega = manejo_fechas(s_fecha_entrega);
            d_fecha_pedido = manejo_fechas(s_fecha_pedido);

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            
            
            stmt.setString(1, pedido.getCodigo());
            stmt.setDate(2, d_fecha_pedido);
            stmt.setDate(3, d_fecha_entrega);
            stmt.setString(4, pedido.getDescripcion());
            stmt.setDouble(5, pedido.getMonto());
            stmt.setInt(6,pedido.getCliente_emisor().getId());
            stmt.setInt(7, pedido.getAeropuerto_emisor().getId());
            stmt.setInt(8,pedido.getCliente_receptor().getId());
            stmt.setInt(9, pedido.getAeropuerto_receptor().getId());
            stmt.setInt(10, pedido.getAeropuerto_actual().getId());
            stmt.setString(11, pedido.getEstado());
            
            stmt.executeUpdate();
            
            
            
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
