package AccesoDatos;

import Controlador.generalBL;
import Controlador.aeropuertoBL;
import Modelo.Vuelo;
import Modelo.database;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VueloDA {
    private generalBL general = new generalBL();
    private aeropuertoBL controladorAeropuerto = new aeropuertoBL();
    
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
                System.out.println(query);
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
                System.out.println(query);
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idDestino = rs.getInt("id");
                }else{
                    System.out.println("ËRROR DESTINO");
                }
                System.out.println(String.valueOf(idOrigen) + " -> " + String.valueOf(idDestino));
                
                /* 3er paso: Registrar en vuelo */
                //
                sentencia = connect.getConnection().createStatement();
                query = "SELECT MAX(id) AS id FROM redexdb.avion";
                rs = sentencia.executeQuery(query);
                rs.next();
                if(rs.getObject("id") != null)
                    idVuelo = rs.getInt("id") + 1;
                else
                    idVuelo = 1;   
                sentencia = connect.getConnection().createStatement();
                query = "INSERT INTO redexdb.avion (id,descripcion)" +
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
    
    
    public ArrayList<Vuelo> listaVuelos(){
        try{
            
            ArrayList<Vuelo> lVuelo = new ArrayList();
            database connect = new database();
            String query = "SELECT \n" +
                        "    aeOrigen.codigo as aeOrigenCod,\n" +
                        "    plan_vuelo.fecha_salida,\n" +
                        "    aeDestino.codigo as aeDestinoCod,\n" +
                        "    plan_vuelo.fecha_llegada,\n" +
                        "    plan_vuelo.codigo,\n" +
                        "    plan_vuelo.id_vuelo,\n" +
                        "    plan_vuelo.activo\n" +
                        "FROM redexdb.plan_vuelo\n" +
                        "INNER JOIN redexdb.aeropuerto as aeOrigen on  aeOrigen.id = plan_vuelo.id_aeropuerto_salida\n" +
                        "INNER JOIN redexdb.aeropuerto as aeDestino on aeDestino.id = plan_vuelo.id_aeropuerto_llegada;";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                Vuelo vuelo = new Vuelo();
                vuelo.setCodigo(rs.getString("codigo"));
                vuelo.setEstado("Estable");
                vuelo.setFechaSalida(rs.getDate("fecha_salida"));
                vuelo.setFechaLlegada(rs.getDate("fecha_llegada"));
                vuelo.setAeropuertoOrigen(rs.getString("aeOrigenCod"));
                vuelo.setAeropuertoDestino(rs.getString("aeDestinoCod"));
                
                lVuelo.add(vuelo);
            }
            connect.closeConnection(); 
            System.out.println("Cantidad de resultados = " + lVuelo.size());
            
            return lVuelo;
        }catch(Exception e){
            System.out.println("ERROR listaVuelos "+e.getMessage());
            return null;
        }
        
    }
    
    
    
}
