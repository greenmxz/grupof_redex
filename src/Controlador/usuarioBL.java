/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoDatos.usuarioDA;
import Modelo.usuario;
import java.util.ArrayList;

/**
 *
 * @author Moises
 */
public class usuarioBL {
    private usuarioDA usuarioDA ;
    public usuarioBL(){
        usuarioDA = new usuarioDA();
    }
    public usuario obtenerUsuario(String nombreUsuario , String contraseña){
        return usuarioDA.obtenerUsuario(nombreUsuario,contraseña);
    }
    
    public boolean crearUsuario(usuario usuario){
        return usuarioDA.registrarUsuario(usuario);
    }
    public ArrayList<usuario> obtenerUsuarios(){
        return usuarioDA.obtenerUsuarios();
    }
}
