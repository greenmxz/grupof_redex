/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author Moises
 */
public class database {
    private String driver;
    private Connection connection;
    
    public database(){
        try{
        this.setDriver("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/redexdb","root","root" );
        System.out.println("Se ha conectado correctamente a la base de datos");
        }catch(Exception e){
            System.out.println("Ha ocurrido un error en la conexion "+ e.getMessage());
        }
    }
    /**
     * @return the name
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param name the name to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the con
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param con the con to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void closeConnection(){
        try {
            this.connection.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
