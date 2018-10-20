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
    private Connection con;
    
    public database(){
        try{
        this.setDriver("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3036/redexdb","root","root" );
        }catch(Exception e){
            System.out.println("Ha ocurrido un error");
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
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }
    
}
