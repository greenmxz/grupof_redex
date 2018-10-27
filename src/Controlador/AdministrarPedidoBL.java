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
    
    public ArrayList<pedido> listarPedidos(String codigo){
        return AdministrarPedidoDA.listarPedidos(codigo);
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
