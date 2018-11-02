package AccesoDatos;

import Controlador.generalBL;
import Modelo.Vuelo;
import Modelo.database;
import java.util.ArrayList;

public class VueloDA {
    private generalBL general = new generalBL();
    
    public void registrarAeropuertos(ArrayList<Vuelo> lstAerop){
        try{
            database connect = new database();
            for(int i=0; i<lstAerop.size(); i++){
                
            }
        }catch(Exception e){
            System.out.println("ERROR registrarAeropuertos "+e.getMessage());
        }
    }
}
