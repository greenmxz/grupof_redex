/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Modelo.cliente;
import Modelo.persona;
import Modelo.database;
import Controlador.generalBL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nowa
 */
public class personaDA {
    
    
    private generalBL general = new generalBL();
    
    
    public java.sql.Date manejo_fechas(String s){
        //manejo de fechas;
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fd =   formatter.parse(s);
                java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
                return sqlDate;
            }catch(Exception e){
                System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
    public persona obtenerPersona(int id_persona){
        try {
            /*
            database connect = new database();
            String query = "{CALL obtenerPersona(?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setInt(1, id_persona);
           
            ResultSet rs = stmt.executeQuery();
            */
            database connect = new database();
            String query = "select * from persona where id = " + id_persona + ";";
            Statement sentencia= connect.getConnection().createStatement();
            ResultSet rs = sentencia.executeQuery(query);
            while (rs.next( )){

                persona persona = new persona();
                persona.setNombre(rs.getString("nombre"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setNumeroDocumentoIdentidad(rs.getInt("numero_documento_identidad"));
                //persona.setTipoDocumento(general.obtenerTipoDocumentos().get(rs.getInt("tipo_documento")).getNombre());
                persona.setDireccion(rs.getString("direccion"));
                persona.setCorreo(rs.getString("correo"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                //persona.setCiudad(rs.getString("ciudad"));
                
                return persona;
            }
            connect.closeConnection();
            System.out.println("La persona no ha sido encontrada");
            return null;
            
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
    
}
