package AccesoDatos;

import Modelo.database;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryGenerator {
    public ArrayList<ArrayList<String>> hacerConsulta(String query){
        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
        try{
            database connect = new database();
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while(rs.next()){
                ArrayList<String> auxiliar = new ArrayList<String>();
                auxiliar.add(rs.getString("Codigo"));
                auxiliar.add(rs.getString("Nombre"));
                auxiliar.add(rs.getString("Frecuencia"));
                lista.add(auxiliar);
            }
            connect.closeConnection();
        }catch(Exception e){
            System.out.println("ERROR query "+e.getMessage());
            return null;
        }
        return lista;
    }
}
