/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Moises
 */
public class database {
    private String driver;
    private Connection connection;
    
    public database(){
        try{
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            //properties.setProperty("password", "burp12"); //Para Linux maquina 5 V
            properties.setProperty("useSSL", "false");
            //properties.setProperty("autoReconnect", "true");
        this.setDriver("com.mysql.jdbc.Driver");

        //connection = DriverManager.getConnection( "jdbc:mysql://192.168.201.105:3306/redexdb?noAccessToProcedureBodies=true","root","burp12" );//Para linux con msje
        
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/redexdb","root","root" );


        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/redexdb",properties );
        connection = DriverManager.getConnection( "jdbc:mysql://192.168.1.4:3306/redexdb?noAccessToProcedureBodies=true&allowPublicKeyRetrieval=true&useSSL=false",properties );//Para linux sin msje

       // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/redexdb",properties );
        //connection = DriverManager.getConnection( "jdbc:mysql://192.168.201.105:3306/redexdb?noAccessToProcedureBodies=true",properties );//Para linux sin msje


//        System.out.println("Se ha conectado correctamente a la base de datos");
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
