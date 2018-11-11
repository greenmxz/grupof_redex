package Controlador;

import AccesoDatos.VueloDA;
import Modelo.Vuelo;
import java.util.ArrayList;

public class VueloBL {
    private VueloDA administrarVueloDA ;
    public VueloBL(){
        administrarVueloDA = new VueloDA();
    }
    public void registrarVuelos(ArrayList<Vuelo> lstVue){
        administrarVueloDA.registrarVuelos(lstVue);
    }
    public ArrayList<Vuelo> listaVuelos(){
        return administrarVueloDA.listaVuelos();
    }
}
