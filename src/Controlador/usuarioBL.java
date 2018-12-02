/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoDatos.usuarioDA;
import Modelo.persona;
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
    public usuario obtenerUsuarioRecuperar(String nombreUsuario){
        return usuarioDA.obtenerUsuarioRecuperar(nombreUsuario);
    }
   
    public boolean crearUsuario(usuario usuario){
        return usuarioDA.registrarUsuario(usuario);
    }
    public boolean modificarUsuario(usuario usuario){
        return usuarioDA.modificarUsuario(usuario);
    }
    public ArrayList<usuario> obtenerUsuarios(){
        return usuarioDA.obtenerUsuarios();
    }
    public usuario obtenerInfoUsuario(int i){
        return usuarioDA.obtenerInfoUsuario(i);
    }
    public boolean borrarUsuario(int i){
        return usuarioDA.borrarUsuario( i);
    }
    public boolean iniciarSesion(int i){
        return usuarioDA.iniciarSesion( i);
    }
    public boolean cerrarSesion(int i){
        return usuarioDA.cerrarSesion( i);
    }
    public boolean existeNumDoc(int i){
        return usuarioDA.existeNumDoc(i);
    }
    public boolean existeUsuario(String usuario){
        return usuarioDA.existeUsuario(usuario);
    }
    public void registrarUsuarios(ArrayList<persona> lstUser, ArrayList<ArrayList<String>> adic){
        usuarioDA.registrarUsuarios(lstUser, adic);
    }

}
