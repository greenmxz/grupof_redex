/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoDatos.AdministrarPedidoDA;
import Modelo.pedido;
import java.util.ArrayList;
/**
 *
 * @author Nowa
 */
public class AdministrarPedidoBL {
    
    private AdministrarPedidoDA AdministrarPedidoDA ;
    public AdministrarPedidoBL(){
        AdministrarPedidoDA = new AdministrarPedidoDA();
    }
    
    public pedido obtenerPedido(int id_pedido){
        return AdministrarPedidoDA.obtenerPedido(id_pedido);
    }
    
    public pedido obtenerPedidoxCodigo(String codigo){
        return AdministrarPedidoDA.obtenerPedidoxCodigo(codigo);
    }
    
    public ArrayList<pedido> listarPedidos(String codigo, String id_aeropuerto_origen, String id_aeropuerto_destino, String id_cliente_emisor, String id_cliente_receptor, String id_estado, String fecha_i, String fecha_f){
        return AdministrarPedidoDA.listarPedidos(codigo, id_aeropuerto_origen, id_aeropuerto_destino, id_cliente_emisor, id_cliente_receptor, id_estado, fecha_i, fecha_f);
    }
    
    public boolean registrarPedido(pedido pedido){
        return AdministrarPedidoDA.registrarPedido(pedido);
    }
    
    public boolean modificarPedido(pedido pedido){
        return AdministrarPedidoDA.modificarPedido(pedido);
    }
    
    public boolean eliminarPedido(int id_pedido){
        return AdministrarPedidoDA.eliminarPedido(id_pedido);
    }
    
    public String obtenerCorreosClientes(int p){
        return AdministrarPedidoDA.obtenerCorreosClientes(p);
    }
}
