/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Nowa
 */
public class AdministrarCliente {
     public void obtenerListaClientes(){ 
        try {
        //database connect = new database();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/redexdb","root","root" );
        System.out.println("Se ha conectado");
        Statement sentencia= con.createStatement();
        String sql = "select * from usuario";
        ResultSet rs = sentencia.executeQuery(sql);
        
        while (rs.next( )){
                System.out.println("Aqui");
            System.out.println(rs.getInt("idUsuario")+ " "+rs.getString("codigo")+ " "+rs.getString("contraseña"));
                  
        }
        con.close();
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
}
