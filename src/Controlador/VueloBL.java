package Controlador;

import AccesoDatos.VueloDA;
import Modelo.Avion;
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
    public boolean registrarVuelo (Avion v){
        return administrarVueloDA.registrarVuelo(v);
    }
    
    public ArrayList<Avion> listaAvion (){
        return administrarVueloDA.listaAvion();
    }
    
    public boolean  borrarVuelo(int i ){
        return administrarVueloDA.borrarVuelo( i );
    }
    public Avion obtenerInfoVuelo(int i){
        return administrarVueloDA.obtenerInfoVuelo( i);
    }
    public boolean  modificarVuelo(Avion a){
        return administrarVueloDA.modificarVuelo( a);
    }
    
}
