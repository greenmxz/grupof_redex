/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * 
 */
public final class Hashing {
    
    public static String MD5Hash(String password){
        
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
		sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            return "";
        }
           
        
    }
    
}
