/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Controlador.generalBL;
import Modelo.database;
import Modelo.aeropuerto;
import Modelo.ciudad;
import Modelo.pais;
import Modelo.continente;
import Modelo.pedido;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nowa
 */
public class aeropuertoDA {
    /*
    private int id;
    private String nombre;
    private String codigo;
    private int capacidad_maxima;
    private int cantidad_paquetes;
    private ciudad ciudad;
    */
    private generalBL general = new generalBL();
    public aeropuerto obtenerAeropuerto(int id_aeropuerto){
        try {

            database connect = new database();
            String query = "select * from aeropuerto where id = " + id_aeropuerto + ";";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                ciudad ciudad = general.obtenerCiudad(rs.getInt("id_ciudad"));
                aeropuerto.setCiudad(ciudad.getNombre());
                //pais
                pais pais = general.obtenerPais(ciudad.getId_pais());
                aeropuerto.setPais(pais.getNombre());
                //continente
                continente continente = general.obtenerContinente(pais.getId_continente());
                aeropuerto.setContinente(continente.getNombre());
                
                connect.closeConnection(); 
                return aeropuerto;
            }
            connect.closeConnection();
            System.out.println("El aeropuerto no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR obtenerAeropuerto "+e.getMessage());
            return null;
        }
    }
    
    public aeropuerto obtenerAeropuertoxCiudad(String nombre_ciudad){
        try {

            database connect = new database();
            String query = "select aeropuerto.id, aeropuerto.nombre, aeropuerto.codigo, aeropuerto.capacidad_maxima, aeropuerto.cantidad_paquetes,\n" +
                            "continente.nombre as continente, pais.nombre as pais, ciudad.nombre as ciudad\n" +
                            "from aeropuerto\n" +
                            "inner join ciudad on aeropuerto.id_ciudad = ciudad.id\n" +
                            "inner join pais on ciudad.id_pais = pais.id\n" +
                            "inner join continente on pais.id_continente = continente.id where ciudad.nombre = '"+nombre_ciudad+"';";
            
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                aeropuerto.setCiudad(rs.getString("ciudad"));
                //pais
                aeropuerto.setPais(rs.getString("pais"));
                //continente
                aeropuerto.setContinente(rs.getString("continente"));
                
                connect.closeConnection(); 
                return aeropuerto;
            }
            connect.closeConnection();
            System.out.println("El aeropuerto no ha sido encontrado");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR obtenerAeropuertoxCiudad "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<aeropuerto> listaAeropuertos(){
    
        try{
            ArrayList<aeropuerto> listAero = new ArrayList<>();
            database connect = new database();
            String query = "select aeropuerto.id, aeropuerto.nombre, aeropuerto.codigo, aeropuerto.capacidad_maxima, aeropuerto.cantidad_paquetes,\n" +
                            "continente.nombre as continente, pais.nombre as pais, ciudad.nombre as ciudad\n" +
                            "from aeropuerto\n" +
                            "inner join ciudad on aeropuerto.id_ciudad = ciudad.id\n" +
                            "inner join pais on ciudad.id_pais = pais.id\n" +
                            "inner join continente on pais.id_continente = continente.id;";
            System.out.println("query => " + query);
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){
                aeropuerto aeropuerto = new aeropuerto();
                
                aeropuerto.setId(rs.getInt("id"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                aeropuerto.setCapacidad_maxima(rs.getInt("capacidad_maxima"));
                aeropuerto.setCantidad_paquetes(rs.getInt("cantidad_paquetes"));
                //ciudad
                aeropuerto.setCiudad(rs.getString("ciudad"));
                //pais
                aeropuerto.setPais(rs.getString("pais"));
                //continente
                aeropuerto.setContinente(rs.getString("continente"));
                
                listAero.add(aeropuerto);
            }
            
            connect.closeConnection(); 
            System.out.println("Cantidad de resultados = " + listAero.size());
            return listAero;
            
        }catch(Exception e){
            System.out.println("ERROR listaAeropuertos "+e.getMessage());
            return null;
        }
    }
    
    public void registrarAeropuertos(ArrayList<aeropuerto> lstAerop){
        try{
            database connect = new database();
            for(int i=0; i<lstAerop.size(); i++){
                String continente = lstAerop.get(i).getContinente();
                String pais = lstAerop.get(i).getPais();
                String ciudad = lstAerop.get(i).getCiudad();
                String codigo = lstAerop.get(i).getCodigo();
                String nombre = lstAerop.get(i).getNombre();
                //
                int idContinente = 0, idPais = 0;
                int idCiudad = 0, idAeropuerto = 0;
                /* 1er paso: Registrar continente*/
                // 
                //System.out.println("[" + continente + "," + pais + "," + ciudad + "," + codigo + "]");
                Statement sentencia = connect.getConnection().createStatement();
                String query = "SELECT id FROM redexdb.continente WHERE nombre = '" +
                        continente + "'";
                //System.out.println(query);
                ResultSet rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idContinente = rs.getInt("id");
                }else{  
                    sentencia = connect.getConnection().createStatement();
                    query = "SELECT MAX(id) AS id FROM redexdb.continente";
                    rs = sentencia.executeQuery(query);
                    rs.next();
                    idContinente = rs.getInt("id") + 1;
                    /* Como es nuevo, se registra */
                    sentencia = connect.getConnection().createStatement();
                    query = "INSERT INTO redexdb.continente (id,nombre,codigo) VALUES ('" +
                            String.valueOf(idContinente) + "','" + continente +
                             "','" + codificarContinente(continente)+"')";
                    sentencia.executeUpdate(query);
                }
                
                /* 2er paso: Registrar pais*/
                // 
                sentencia = connect.getConnection().createStatement();
                query = "SELECT id FROM redexdb.pais WHERE nombre = '" +
                        pais + "'";
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idPais = rs.getInt("id");
                }else{
                    sentencia = connect.getConnection().createStatement();
                    query = "SELECT MAX(id) AS id FROM redexdb.pais";
                    rs = sentencia.executeQuery(query);
                    rs.next();
                    idPais = rs.getInt("id") + 1;
                    /* Como es nuevo, se registra */
                    sentencia = connect.getConnection().createStatement();
                    query = "INSERT INTO redexdb.pais (id,nombre,codigo,id_continente) VALUES ('" +
                            String.valueOf(idPais) + "','" + pais +
                             "','" + codificarPais(pais) + "','" +
                            String.valueOf(idContinente) + "')";
                    sentencia.executeUpdate(query);
                }
                
                /* 3er paso: Registrar ciudad*/
                // 
                sentencia = connect.getConnection().createStatement();
                query = "SELECT id FROM redexdb.ciudad WHERE nombre = '" +
                        ciudad + "'";
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idCiudad = rs.getInt("id");
                }else{
                    sentencia = connect.getConnection().createStatement();
                    query = "SELECT MAX(id) AS id FROM redexdb.ciudad";
                    rs = sentencia.executeQuery(query);
                    rs.next();
                    idCiudad = rs.getInt("id") + 1;
                    /* Como es nuevo, se registra */
                    sentencia = connect.getConnection().createStatement();
                    query = "INSERT INTO redexdb.ciudad (id,nombre,codigo,id_pais,activo) VALUES ('" +
                            String.valueOf(idCiudad) + "','" + ciudad +
                             "','" + codificarCiudad(ciudad) + "','" +
                            String.valueOf(idPais) + "','1')";
                    sentencia.executeUpdate(query);
                }
                
                /* 4er paso: Registrar aeropuerto*/
                //
                sentencia = connect.getConnection().createStatement();
                query = "SELECT id FROM redexdb.aeropuerto WHERE nombre = '" +
                        nombre + "'";
                rs = sentencia.executeQuery(query);
                if(rs.next()){
                    if(rs.getObject("id") != null)
                        idAeropuerto = rs.getInt("id");
                }else{
                    sentencia = connect.getConnection().createStatement();
                    query = "SELECT MAX(id) AS id FROM redexdb.aeropuerto";
                    rs = sentencia.executeQuery(query);
                    rs.next();
                    idAeropuerto = rs.getInt("id") + 1;
                    /* Como es nuevo, se registra */
                    sentencia = connect.getConnection().createStatement();
                    query = "INSERT INTO redexdb.aeropuerto (id,nombre,codigo,capacidad_maxima,cantidad_paquetes,id_ciudad,activo) VALUES ('" +
                            String.valueOf(idAeropuerto) + "','" + nombre +
                            "','" + codigo + "','" + lstAerop.get(i).getCapacidad_maxima() + "','" +
                            lstAerop.get(i).getCantidad_paquetes() + "','" +
                            String.valueOf(idCiudad) + "','1')";
                    sentencia.executeUpdate(query);
                }
            }
            connect.getConnection().close();
        }catch(Exception e){
            System.out.println("ERROR registrarAeropuertos "+e.getMessage());
        }
    }
    
    /* Métodos */
    public String codificarContinente(String continente){
        String auxContinente = continente.toLowerCase();
        if(auxContinente.split(" ").length == 1)
            return auxContinente.substring(0, 3);
        else{
            return auxContinente.split(" ")[0].substring(0, 1) +
                    auxContinente.split(" ")[auxContinente.split(" ").length - 1].substring(0, 2);
        }
    }
    
    public String codificarPais(String pais){
        String auxPais = pais.toLowerCase();
        if(auxPais.split(" ").length == 1)
            return auxPais.substring(0, 3);
        else{
            return auxPais.split(" ")[0].substring(0, 1) +
                    auxPais.split(" ")[auxPais.split(" ").length - 1].substring(0, 2);
        }
    }
    
    public String codificarCiudad(String ciudad){
        String auxCiudad = ciudad.toLowerCase();
        if(auxCiudad.split(" ").length == 1)
            return auxCiudad.substring(0, 3);
        else{
            return auxCiudad.split(" ")[0].substring(0, 1) +
                    auxCiudad.split(" ")[auxCiudad.split(" ").length - 1].substring(0, 2);
        }
    }
    
}
