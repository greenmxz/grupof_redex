package AccesoDatos;

import Controlador.generalBL;
import Modelo.Vuelo;
import Modelo.database;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VueloDA {
    private generalBL general = new generalBL();
    
    public void registrarVuelos(ArrayList<Vuelo> lstVuelo){
        try{
            database connect = new database();
            for(int i=0; i<lstVuelo.size(); i++){
                String aeropuertoOrigen = lstVuelo.get(i).getAeropuertoOrigen();
                String aeropuertoDestino = lstVuelo.get(i).getAeropuertoDestino();
                Date salida = lstVuelo.get(i).getFechaSalida();
                Date llegada = lstVuelo.get(i).getFechaLlegada();
                int capMax = lstVuelo.get(i).getCapMax();
                int capActual = lstVuelo.get(i).getCapActual();
                String estado = lstVuelo.get(i).getEstado();
                //
                int idOrigen = 0, idDestino = 0;
                int idVuelo = 0;
                /* 1er paso: Hallar id de aeropuerto origen*/
                // 
                Statement sentencia = connect.getConnection().createStatement();
                String query = "SELECT id FROM redexdb.aeropuerto WHERE codigo = '" +
                        aeropuertoOrigen + "'";
                //System.out.println(query);
                ResultSet rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idOrigen = rs.getInt("id");
                }else{
                    System.out.println("ËRROR ORIGEN");
                }
                /* 2do paso: Hallar id de aeropuerto destino*/
                // 
                sentencia = connect.getConnection().createStatement();
                query = "SELECT id FROM redexdb.aeropuerto WHERE codigo = '" +
                        aeropuertoDestino + "'";
//                System.out.println(query);
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idDestino = rs.getInt("id");
                }else{
                    System.out.println("ËRROR DESTINO");
                }
//                System.out.println(String.valueOf(idOrigen) + " -> " + String.valueOf(idDestino));
                
                /* 3er paso: Registrar en vuelo */
                //
                sentencia = connect.getConnection().createStatement();
                query = "SELECT MAX(id) AS id FROM redexdb.vuelo";
                rs = sentencia.executeQuery(query);
                rs.next();
                if(rs.getObject("id") != null)
                    idVuelo = rs.getInt("id") + 1;
                else
                    idVuelo = 1;   
                sentencia = connect.getConnection().createStatement();
                query = "INSERT INTO redexdb.vuelo (id,descripcion)" +
                    "VALUES ('" + String.valueOf(idVuelo) + "','¡Hola! ¡Hola! ¡Hola!')";
                sentencia.executeUpdate(query);
                
                sentencia = connect.getConnection().createStatement();
                query = "INSERT INTO redexdb.plan_vuelo (id,id_aeropuerto_salida,fecha_salida,"
                        + "id_aeropuerto_llegada,fecha_llegada,id_vuelo,activo)" +
                        "VALUES ('" + String.valueOf(idVuelo) + "','" + String.valueOf(idOrigen) + "','" +
                        new SimpleDateFormat("yyyy-MM-dd, HH:mm:00").format(salida) + "','" +
                        String.valueOf(idDestino) + "','" +
                        new SimpleDateFormat("yyyy-MM-dd, HH:mm:00").format(llegada) + "','" +
                        String.valueOf(idVuelo) + "','1')";
                sentencia.executeUpdate(query); 
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR registrarAeropuertos "+e.getMessage());
        }
    }
}
