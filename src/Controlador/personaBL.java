/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import AccesoDatos.personaDA;
import Modelo.persona;
/**
 *
 * @author Nowa
 */
public class personaBL {
    private personaDA personaDA ;
    public personaBL(){
        personaDA = new personaDA();
    }
    public persona obtenerPersona(int id_persona){
        return personaDA.obtenerPersona(id_persona);
    }
    public persona obtenerPersonaxDNI(int dni){
        return personaDA.obtenerPersonaxDNI(dni);
    }
    
    public boolean insertarPersona(persona persona){
        return personaDA.insertarPersona(persona);
    }
}
