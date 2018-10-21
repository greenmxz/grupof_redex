/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.*;
import Modelo.database;
import Modelo.persona;
import Modelo.usuario;


/**
 *
 * @author Moises
 */
public class usuarioDA {
    public usuario obtenerUsuario(String nombreUsuario, String contraseña){ 
        try {
            
            System.out.println(nombreUsuario+" "+contraseña);
            database connect = new database();
            String query = "{CALL obtenerUsuario(?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery();
            System.out.println("EEEEEEEEEEEEEEEEEEEEE");
            while (rs.next( )){
               usuario usuario= new usuario();
               if (rs.getBoolean("encontrado")){                     
                    persona persona= new persona();

                    System.out.println(rs.getInt("id")+ " "+rs.getString("codigo")+ " "+rs.getString("password"));
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setCodigo(nombreUsuario);
                    usuario.setPassword(contraseña);
                    
                    usuario.setNumeroIntentos(rs.getInt("numero_intentos"));
                    usuario.setTiempoRestanteBaneado(rs.getInt("tiempo_restante"));
                    usuario.setBaneado(rs.getBoolean("baneado"));
                    usuario.setEncontrado(rs.getBoolean("encontrado"));
                    
                    usuario.setRol(rs.getString("rol"));
                    persona.setNombre(rs.getString("nombre_persona"));
                    persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                    persona.setApellidoMaterno(rs.getString("apellido_materno"));
                    persona.setCiudad(rs.getString("ciudad"));
                    persona.setCorreo(rs.getString("correo"));
                    persona.setDireccion(rs.getString("direccion"));
                    persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    persona.setNumeroDocumentoIdentidad(rs.getInt("numero_documento_identidad"));
                    persona.setTelefono(rs.getString("telefono"));
                    persona.setTipoDocumento(rs.getString("tipo_documento"));
                    
                    usuario.setPersona(persona);
                    return usuario;
                }else{
                   if (rs.getBoolean("castigar")){
                       usuario.setTiempoRestanteBaneado(rs.getInt("tiempo_restante"));
                       usuario.setBaneado(rs.getBoolean("baneado"));
                       usuario.setNumeroIntentos(rs.getInt("numero_intentos"));
                       usuario.setEncontrado(rs.getBoolean("encontrado"));
                       usuario.setId(rs.getInt("id_usuario"));
                       return usuario;
                   }
                   
                }  
            }
            connect.closeConnection();
            System.out.println("El usuario no ha sido encontrado");
            return null;
        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
            return null;
        }
    }
}