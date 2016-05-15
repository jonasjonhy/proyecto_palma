/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.*;


/**
 *
 * @author madarme
 */
public class TestConnection {




 public static void main(String[] args) throws Exception 
 {
   BaseDeDatos.conectar();
    if (BaseDeDatos.hayConexion())
        System.out.println("...Hay conexión...");
    else
        System.out.println("...No Hay conexión...");
   

 }
}