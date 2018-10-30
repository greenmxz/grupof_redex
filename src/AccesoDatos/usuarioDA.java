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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author Moises
 */
public class usuarioDA {
    public usuario obtenerUsuario(String nombreUsuario, String contraseña){ 
        try {
            database connect = new database();
            String query = "{CALL obtenerUsuario(?,?)}";

            CallableStatement stmt = connect.getConnection().prepareCall(query);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery();
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
     public boolean registrarUsuario(usuario usuario){
         try {
            database connect = new database();
            String queryPersona="insert into persona (nombre,apellido_paterno,apellido_materno,"
                    + "numero_documento_identidad,direccion,fecha_nacimiento,id_ciudad,id_tipo_documento,correo)"
                    + "values (?,?,?,?,?,?,(select id from ciudad where nombre =?),(select id from tabla_general_detalle where valor = ?),?)"; 
            
            
            PreparedStatement stmt = connect.getConnection().prepareStatement(queryPersona,Statement.RETURN_GENERATED_KEYS);
            
            //manejo de fechas;
            SimpleDateFormat  sdf;
            String            s;
            sdf = new SimpleDateFormat("yyyy-MM-dd");  // Or whatever format you need
            s = sdf.format(usuario.getPersona().getFechaNacimiento()); 
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fd =   formatter.parse(s);
            java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
            
            //stmt.registerOutParameter("id",java.sql.Types.INTEGER );
            stmt.setString(1,usuario.getPersona().getNombre());
            stmt.setString(2,usuario.getPersona().getApellidoPaterno());
            stmt.setString(3,usuario.getPersona().getApellidoMaterno());
            stmt.setInt(4,usuario.getPersona().getNumeroDocumentoIdentidad());
            stmt.setString(5,usuario.getPersona().getDireccion());
            stmt.setDate(6,sqlDate);
            System.out.println("city "+usuario.getPersona().getCiudad());
           System.out.println("number "+usuario.getPersona().getTipoDocumento());
            stmt.setString(7,usuario.getPersona().getCiudad());
            stmt.setString(8,usuario.getPersona().getTipoDocumento());
            stmt.setString(9, usuario.getPersona().getCorreo());
            int count = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            long id = 0 ;
            while(rs.next()){
                id = rs.getLong(1);
                System.out.println("count-> "+id);
                //Creamos el usuario
                
            //stmt.setString(5, usuario.getPersona());
            }
            String queryUsuario="insert into usuario (codigo,password,"
                    + "id_rol,id_persona,create_date)"
                    + "values (?,?,(select id from rol where nombre=? ),?,now())"; 
            PreparedStatement stmt2 = connect.getConnection().prepareStatement(queryUsuario);
            stmt2.setString(1,usuario.getCodigo());
            stmt2.setString(2,usuario.getPassword());
            stmt2.setString(3,usuario.getRol());
            stmt2.setLong(4,id);
            stmt2.executeUpdate();
            System.out.println("Se regristro el usuario correctamente");
            System.out.println("Se registro el usuario");
            return true;
            

            
         }catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
         }
         
     }
    public ArrayList<usuario> obtenerUsuarios(){
        try{
            database connection = new database();
             String query="select *,u.codigo as 'nombre_usuario',p.nombre as 'nombre_persona',r.nombre as 'rol' from usuario as u "
                     + "inner join persona as p on u.id_persona=p.id "
                     + "inner join rol as r on r.id=u.id_rol";
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<usuario> lista = new ArrayList<>();
            
            while (rs.next( )){
                usuario usuario = new usuario();
                persona persona = new persona();     
                usuario.setCodigo(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                persona.setNombre(rs.getString("nombre_persona"));
                persona.setApellidoPaterno(rs.getString("apellido_paterno"));
                persona.setApellidoMaterno(rs.getString("apellido_materno"));
                persona.setCorreo((rs.getString("correo")));
                usuario.setPersona(persona);
                lista.add(usuario);
            }
            connection.getConnection().close();
            return lista;
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
            return null;
        }
    }
}