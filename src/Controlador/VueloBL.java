package Controlador;

import AccesoDatos.VueloDA;
import Modelo.Vuelo;
import java.util.ArrayList;

public class VueloBL {
    private VueloDA administrarVueloDA ;
    public VueloBL(){
        administrarVueloDA = new VueloDA();
    }
    public void registrarAeropuertos(ArrayList<Vuelo> lstAerop){
        administrarVueloDA.registrarVuelos(lstAerop);
    }
}
