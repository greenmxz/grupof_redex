package Controlador;

import AccesoDatos.PaqueteDA;
import Modelo.paquete;
import java.util.ArrayList;

public class PaqueteBL {
    private PaqueteDA administrarPaqueteDA ;
    public PaqueteBL(){
        administrarPaqueteDA = new PaqueteDA();
    }
    public void registrarPaquetes(ArrayList<paquete> lstPaq){
        administrarPaqueteDA.registrarPaquetes(lstPaq);
    }
}
