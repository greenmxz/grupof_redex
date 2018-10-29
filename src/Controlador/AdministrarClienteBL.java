/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoDatos.AdministrarClienteDA;
import Modelo.cliente;
import java.util.ArrayList;
/**
 *
 * @author Nowa
 */
public class AdministrarClienteBL {
    
    private AdministrarClienteDA AdministrarClienteDA ;
    public AdministrarClienteBL(){
        AdministrarClienteDA = new AdministrarClienteDA();
    }
    
    public cliente obtenerCliente(int id_cliente){
        return AdministrarClienteDA.obtenerCliente(id_cliente);
    }
    
    public cliente obtenerClienteDNI(int dni){
        return AdministrarClienteDA.obtenerClienteDNI(dni);
    }
    
    public ArrayList<cliente> listarClientes(String numeroDocumentoIdentidad,String nombre, String apellidoPaterno, String apellidoMaterno){
        return AdministrarClienteDA.listarClientes(numeroDocumentoIdentidad, nombre, apellidoPaterno, apellidoMaterno);
    }
    
    public int registrarCliente(cliente cliente){
        return AdministrarClienteDA.registrarCliente(cliente);
    }
    
    public int modificarCliente(cliente cliente){
        return AdministrarClienteDA.modificarCliente(cliente);
    }
    
    public boolean eliminarCliente(int id_cliente){
        return AdministrarClienteDA.eliminarCliente(id_cliente);
    }
}
