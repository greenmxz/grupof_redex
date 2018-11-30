package AccesoDatos;

import Controlador.generalBL;
import Modelo.database;
import Modelo.paquete;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaqueteDA {
    private generalBL general = new generalBL();
    
    public void registrarPaquetes(ArrayList<paquete> lstPaq){
        try{
            database connect = new database();
            for(int i=0; i<lstPaq.size(); i++){
                String codigo = lstPaq.get(i).getCodigo();
                String aeropuertoOrigen = lstPaq.get(i).getAeropuertoOrigen();
                String aeropuertoDestino = lstPaq.get(i).getAeropuertoDestino();
                Date salida = lstPaq.get(i).getFechaSalida();
                String clEmisor = lstPaq.get(i).getClienteEmisor();
                String clReceptor = lstPaq.get(i).getClienteReceptor();
                String estado = lstPaq.get(i).getEstado();
                //
                int idOrigen = 0, idDestino = 0;
                int idPedido = 0;
                /* 1er paso: Hallar id de aeropuerto donde se entregó*/
                // 
                Statement sentencia = connect.getConnection().createStatement();
                String query = "SELECT id FROM redexdb.aeropuerto WHERE codigo = '" +
                        aeropuertoOrigen + "'";
                ResultSet rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idOrigen = rs.getInt("id");
                }else{
                    System.out.println("ËRROR ORIGEN");
                }
                /* 2do paso: Hallar id de aeropuerto donde quiere llegar */
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
                
                /* 3er paso: Registrar en pedido */
                //
                sentencia = connect.getConnection().createStatement();
                query = "SELECT MAX(id) AS id FROM redexdb.pedido";
                rs = sentencia.executeQuery(query);
                rs.next();
                if(rs.getObject("id") != null)
                    idPedido = rs.getInt("id") + 1;
                else
                    idPedido = 1;
                
                sentencia = connect.getConnection().createStatement();
                query = "INSERT INTO redexdb.pedido (id,codigo,fecha_pedido,id_aeropuerto_emisor,"
                        + "id_aeropuerto_receptor,id_cliente_emisor, id_cliente_receptor,id_estado)" +
                        "VALUES ('" + String.valueOf(idPedido) + "','" + codigo + "','" +
                        new SimpleDateFormat("yyyy-MM-dd, HH:mm:00").format(salida) + "','" +
                        String.valueOf(idOrigen) + "','" + String.valueOf(idDestino) + "','" +
                        clEmisor + "','" + clReceptor + "','1')";
//                System.out.println("Query: " + query);
                sentencia.executeUpdate(query); 
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR registrarPaquetes "+e.getMessage());
        }
    }
    
    public ArrayList<paquete> obtenerPaquetesAsign(Date fecha){
        ArrayList<paquete> aux = new ArrayList<paquete>();
        try{
            database connect = new database();
            Statement sentencia = connect.getConnection().createStatement();
            String query = "SELECT * FROM redexdb.pedido WHERE fecha_pedido <= '" +
                    new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(fecha) + 
                    "' AND id_estado < '3' ORDER BY fecha_pedido";
            ResultSet rs = sentencia.executeQuery(query);
            while(rs.next()){
                Date nuevaFecha = new Date(rs.getDate("fecha_pedido").getYear(), rs.getDate("fecha_pedido").getMonth(),
                            rs.getDate("fecha_pedido").getDay(), rs.getTime("fecha_pedido").getHours(),
                            rs.getTime("fecha_pedido").getMinutes());
                paquete newPaq = new paquete(rs.getString("codigo"), nuevaFecha,
                    rs.getInt("id_aeropuerto_emisor"), rs.getInt("id_aeropuerto_receptor"));
                aux.add(newPaq);
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR obtenerPaquetesAsign "+e.getMessage());
        }
        return aux;
    }
    
    public ArrayList<paquete> obtenerPaquetes(){
        ArrayList<paquete> aux = new ArrayList<paquete>();
        try{
            database connect = new database();
            Statement sentencia = connect.getConnection().createStatement();
            String query = "SELECT * FROM redexdb.pedido";
            ResultSet rs = sentencia.executeQuery(query);
            while(rs.next()){
                Date nuevaFecha = new Date(rs.getDate("fecha_pedido").getYear(), rs.getDate("fecha_pedido").getMonth(),
                            rs.getDate("fecha_pedido").getDay(), rs.getTime("fecha_pedido").getHours(),
                            rs.getTime("fecha_pedido").getMinutes());
                paquete newPaq = new paquete(rs.getString("codigo"), nuevaFecha, 
                    rs.getString("id_aeropuerto_emisor"), rs.getString("id_aeropuerto_receptor"),
                    rs.getString("id_cliente_emisor"), rs.getString("id_cliente_emisor"),
                    rs.getString("id_estado"));
                
                newPaq.setAeropuertoOrigenId(rs.getInt("id_aeropuerto_emisor"));
                newPaq.setAeropuertoDestinoId(rs.getInt("id_aeropuerto_receptor"));
                
                newPaq.setFechaSalida(nuevaFecha);
                
                aux.add(newPaq);
            }
            for(int i=0; i<aux.size(); i++){
                ResultSet rs1 = sentencia.executeQuery("SELECT c.nombre as ciudad, n.nombre as continente\n" +
                    "FROM redexdb.aeropuerto AS a, redexdb.ciudad as c, redexdb.pais as p, redexdb.continente as n\n" +
                    "WHERE a.id = '" + aux.get(i).getAeropuertoOrigen() +
                    "' AND a.id_ciudad = c.id AND c.id_pais = p.id AND p.id_continente = n.id");
                rs1.next();
                aux.get(i).setCiudadOrigen(rs1.getString("ciudad"));
                aux.get(i).setContinenteOrigen(rs1.getString("continente"));
                rs1 = sentencia.executeQuery("SELECT c.nombre as ciudad, n.nombre as continente\n" +
                    "FROM redexdb.aeropuerto AS a, redexdb.ciudad as c, redexdb.pais as p, redexdb.continente as n\n" +
                    "WHERE a.id = '" + aux.get(i).getAeropuertoDestino() +
                    "' AND a.id_ciudad = c.id AND c.id_pais = p.id AND p.id_continente = n.id");
                rs1.next();
                aux.get(i).setCiudadDestino(rs1.getString("ciudad"));
                aux.get(i).setContinenteDestino(rs1.getString("continente"));
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR obtenerPaquetesAsign "+e.getMessage());
        }
        return aux;
    }
}
