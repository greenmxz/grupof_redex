/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import AccesoDatos.generalDA;
import Modelo.*;
import java.util.*;

/**
 *
 * @author Moises
 */
public class generalBL {
    private generalDA generalDA;
    public generalBL(){
        generalDA = new generalDA();
    }
    
    public int obtenerIdGeneral(String codigo, String valor){
        return generalDA.obtenerIdGeneral(codigo, valor);
    }
    public java.sql.Date manejo_fechas(String s){
        return generalDA.manejo_fechas(s);
    }
    public java.sql.Date manejo_fechas_24hs(java.util.Date fecha){
        return generalDA.manejo_fechas_24hs(fecha);
    }
    public ArrayList<rol> obtenerRoles(){
        return generalDA.obtenerRoles();
    }
    public ArrayList<ciudad> obtenerCiudades(){
        return generalDA.obtenerCiudades();
    }
    
    public ArrayList<pais> obtenerPaises(){
        return generalDA.obtenerPaises();
    }
    
    public ArrayList<continente> obtenerContinentes(){
        return generalDA.obtenerContinentes();
    }
    
    public ArrayList<tipoDocumento> obtenerTipoDocumentos(){
        return generalDA.obtenerTipoDocumentos();
    }
    
    public ciudad obtenerCiudad(int id_ciudad){
        return generalDA.obtenerCiudad(id_ciudad);
    }
    
    public pais obtenerPais(int id_pais){
        return generalDA.obtenerPais(id_pais);
    }
    
    public continente obtenerContinente(int id_continente){
        return generalDA.obtenerContinente(id_continente);
    }
    
    
    
}
