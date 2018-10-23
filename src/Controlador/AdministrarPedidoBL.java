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
    
    public ArrayList<pedido> listarPedidos(String codigo, String fecha_entrega, String fecha_registro){
        return AdministrarPedidoDA.listarPedidos(codigo, fecha_entrega, fecha_registro);
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
    
}
