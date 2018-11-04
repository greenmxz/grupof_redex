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
                System.out.println(query);
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
                System.out.println(query);
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idDestino = rs.getInt("id");
                }else{
                    System.out.println("ËRROR DESTINO");
                }
                System.out.println(String.valueOf(idOrigen) + " -> " + String.valueOf(idDestino));
                
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
                System.out.println("Query: " + query);
                sentencia.executeUpdate(query); 
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR registrarPaquetes "+e.getMessage());
        }
    }
}
