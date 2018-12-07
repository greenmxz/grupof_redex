package Controlador;

import AccesoDatos.PaqueteDA;
import Modelo.paquete;
import java.util.ArrayList;
import java.util.Date;

public class PaqueteBL {
    private PaqueteDA administrarPaqueteDA ;
    public PaqueteBL(){
        administrarPaqueteDA = new PaqueteDA();
    }
    public void registrarPaquetes(ArrayList<paquete> lstPaq){
        administrarPaqueteDA.registrarPaquetes(lstPaq);
    }
    public ArrayList<paquete> obtenerPaquetesAsign(Date fecha){
        return administrarPaqueteDA.obtenerPaquetesAsign(fecha);
    }
    
    public ArrayList<paquete> obtenerPaquetes(){
        return administrarPaqueteDA.obtenerPaquetes();
    }
    public ArrayList<paquete> obtenerPaquetesCreados(int id){
        return administrarPaqueteDA.obtenerPaquetesCreados(id);
    }
    public String obtenerCadenaRuta(String idO, String idD){
        return administrarPaqueteDA.obtenerCadenaRuta(idO, idD);
    }
    
}
