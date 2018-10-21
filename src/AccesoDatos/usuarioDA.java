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
//            Statement sentencia= connect.getConnection().createStatement();
//            String sql = "select  from usuario  as u inner join persona as p on ";
//            ResultSet rs = sentencia.executeQuery(sql);
            String sqlQuery = "select *,u.id as id_usuario,p.id as id_persona ,tgd.valor as tipo_documento,c.nombre as ciudad,pa.nombre as pais , co.nombre as continente,r.nombre as rol,p.nombre as nombre_persona\n" +
            "from usuario as u \n" +
            "inner join persona as p on p.id=u.id_persona\n" +
            "inner join tabla_general_detalle as tgd on tgd.id = p.id_tipo_documento\n" +
            "inner join ciudad as c on c.id=p.id_ciudad\n" +
            "inner join pais as pa on pa.id=c.id\n" +
            "inner join continente as co on co.id=pa.id_continente\n" +
            "inner join rol as r on r.id=u.id_rol\n" +
            "where u.codigo=? and u.password=?";

            PreparedStatement stmt = connect.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next( )){
                usuario usuario= new usuario();
                persona persona= new persona();
                
                System.out.println(rs.getInt("id")+ " "+rs.getString("codigo")+ " "+rs.getString("password"));
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setCodigo(sqlQuery);
                usuario.setPassword(contraseña);
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