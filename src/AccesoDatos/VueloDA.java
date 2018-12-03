package AccesoDatos;

import Controlador.generalBL;
import Controlador.aeropuertoBL;
import Modelo.Avion;
import Modelo.Vuelo;
import Modelo.database;
import Modelo.usuario;
import java.sql.PreparedStatement;
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
                
                /* 3er paso: Registrar en avion */
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
    
    public boolean registrarVuelo(Avion a){
        try{
            database connect = new database();
            String query = "insert into avion (codigo,capacidad_maxima,descripcion) values"
                    + "(?,?,?)";
            PreparedStatement stmt = connect.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            //manejo de fechas;
            stmt.setString(1,a.getCodigo());
            stmt.setInt(2,a.getCapacidadMaxima());
            stmt.setString(3,a.getDescripcion());


            int count = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            long id = 0 ;
            while(rs.next()){
                id = rs.getLong(1);
                System.out.println("count-> "+id);
                //Creamos el usuario
                return true;
            //stmt.setString(5, usuario.getPersona());
            }
            return false;
        }catch(Exception ex){
            System.out.println("Error "+ex.getMessage());
            return true;
            
        }
    }
    public ArrayList<Vuelo> listaVuelos(){
        try{
            
            ArrayList<Vuelo> lVuelo = new ArrayList();
            database connect = new database();
            String query = "SELECT \n" +
                        "    plan_vuelo.id as id, "+
                        "    aeOrigen.id as aeOrigenId, "+
                        "    aeDestino.id as aeDestinoId, "+
                        "    aeOrigen.codigo as aeOrigenCod,\n" +
                        "    plan_vuelo.fecha_salida,\n" +
                        "    aeDestino.codigo as aeDestinoCod,\n" +
                        "    plan_vuelo.fecha_llegada,\n" +
                        "    plan_vuelo.codigo,\n" +
                        "    plan_vuelo.id_vuelo,\n" +
                        "    plan_vuelo.activo\n" +
                        "FROM redexdb.plan_vuelo\n" +
                        "INNER JOIN redexdb.aeropuerto as aeOrigen on  aeOrigen.id = plan_vuelo.id_aeropuerto_salida\n" +
                        "INNER JOIN redexdb.aeropuerto as aeDestino on aeDestino.id = plan_vuelo.id_aeropuerto_llegada "
                    + " order by plan_vuelo.id asc;";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            int i=1;
            while (rs.next( )){
                Vuelo vuelo = new Vuelo();
                vuelo.setCodigo(String.valueOf(i));
                vuelo.setEstado("Estable");
                vuelo.setId(rs.getInt("id"));
                vuelo.setFechaSalida(rs.getTimestamp("fecha_salida"));
                vuelo.setFechaLlegada(rs.getTimestamp("fecha_llegada"));
                
                vuelo.setIdAeropuertoDestino(rs.getInt("aeDestinoId"));
                vuelo.setIdAeropuertoOrigen(rs.getInt("aeOrigenId"));

        /*
                vuelo.setFechaSalida(new SimpleDateFormat("yyyy-MM-dd HH:mm").
                        parse(rs.getString("fecha_salida").substring(0, rs.getString("fecha_salida").length()-5)));
                vuelo.setFechaLlegada(new SimpleDateFormat("yyyy-MM-dd HH:mm").
                        parse(rs.getString("fecha_llegada").substring(0, rs.getString("fecha_llegada").length()-5)));
                        */

                vuelo.setAeropuertoOrigen(rs.getString("aeOrigenCod"));
                vuelo.setAeropuertoDestino(rs.getString("aeDestinoCod"));
                
                lVuelo.add(vuelo);
                i++;
            }
            connect.closeConnection(); 
            System.out.println("Cantidad de resultados = " + lVuelo.size());
            
            return lVuelo;
        }catch(Exception e){
            System.out.println("ERROR listaVuelos "+e.getMessage());
            return null;
        }
        
    }
    
    public ArrayList<Avion> listaAvion(){
        try{
            
            ArrayList<Avion> lVuelo = new ArrayList();
            database connect = new database();
            String query = "select * from avion";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            int i=1;
            while (rs.next( )){
                Avion avion = new Avion();
                avion.setId(rs.getInt("id"));
                avion.setCodigo(rs.getString("codigo"));
                avion.setDescripcion(rs.getString("descripcion"));
                avion.setCapacidadMaxima(rs.getInt("capacidad_maxima"));

                
                lVuelo.add(avion);
                i++;
            }
            connect.closeConnection(); 
            System.out.println("Cantidad de resultados = " + lVuelo.size());
            
            return lVuelo;
        }catch(Exception e){
            System.out.println("ERROR listaVuelos "+e.getMessage());
            return null;
        }
        
    }
    
    public boolean borrarVuelo(int i){
        try {
            database connect = new database();
            String queryUsuario="delete from avion where id = ?"; 
            PreparedStatement stmt2 = connect.getConnection().prepareStatement(queryUsuario);
            stmt2.setInt(1,i);
            stmt2.executeUpdate();
            System.out.println("Se regristro el usuario correctamente");
            System.out.println("Se registro el usuario");
            return true;
            

            
         }catch(Exception ex){
             System.out.println("ERROR "+ex.getMessage());
             return false;
         }
    }
    
            
    public Avion obtenerInfoVuelo(int id){
        try{
            System.out.println("AQUI EMT "+id);
            database connection = new database();
             String query="select * from avion where id = ?;";
            //Statement stmt = connection.getConnection().createStatement();
            PreparedStatement stmt = connection.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            //ArrayList<usuario> lista = new ArrayList<>();
            Avion avion = new Avion();
            while (rs.next( )){
                
                
                avion.setId(rs.getInt("id"));
                avion.setCodigo(rs.getString("codigo"));
                avion.setDescripcion(rs.getString("descripcion"));
                avion.setCapacidadMaxima(rs.getInt("capacidad_maxima"));

                //lista.add(usuario);
            }

            connection.getConnection().close();
            return avion;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null;
        }
    }
    
    public boolean modificarVuelo(Avion v){
         try {
            database connect = new database();
            String queryPersona="update avion   set capacidad_maxima = ? , codigo = ?, "
                    + " descripcion= ? "
                    + "where id = ?";

            
            //Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            PreparedStatement stmt = connect.getConnection().prepareStatement(queryPersona);
            
            //manejo de fechas;
            
           
             //stmt.registerOutParameter("id",java.sql.Types.INTEGER );
            stmt.setInt(1,v.getCapacidadMaxima());
            stmt.setString(2,v.getCodigo());
            stmt.setString(3,v.getDescripcion());
            stmt.setInt(4,v.getId());
            int count = stmt.executeUpdate();
            

           
            
            return true;
            

            
         }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
         
     }
    
    
}
