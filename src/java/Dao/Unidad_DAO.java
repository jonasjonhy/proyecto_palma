/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Pedro
 */
public class Unidad_DAO {
  
   public boolean registrarNuevaUnidad(Unidad u,String documento)throws SQLException{
      BaseDeDatos.conectar();
            String sqlRegistro = "INSERT INTO unidad( unidad, tipo, persona_numero_documento) VALUES (?, ?, ?)";
	    
            Object param[] = { u.getUnidad(),u.getTipo(), documento};
            return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
 
   }
     /**
    * este metodo liga con el registro de una nueva unidad..cuando se registra la unidad, se consulta su codigo por medio del nombre, para ser almacenado en la BD
    * @param idUnidad nombre de la unidad registrada
    * @return todos los datos de la unidad que se registro recientemente y/o cualquier unidad
    * @throws SQLException 
    */
   public Unidad consultarUnidadPorNombre(String nombreUnidad) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, unidad, tipo, persona_numero_documento FROM unidad WHERE unidad = ?";
        Object param [] = {nombreUnidad};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        
        Unidad u = new Unidad();
        while(datos.next()){
            u.setId(datos.getString("id"));
            u.setUnidad(datos.getString("unidad"));
            u.setTipo(datos.getString("tipo"));
	    u.setDocumentoPersona(datos.getString("persona_numero_documento"));
           
        }
        return u;
    }
    /**
    * consulta una unidad de acuerdo a su id
    * @param idUnidad id de la unidad que se va a consultar
    * @return toda la info de la unidad
    * @throws SQLException 
    */
   public Unidad consultarUnidadPorID(String idUnidad) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, unidad, tipo, persona_numero_documento FROM unidad WHERE id = ?";
        Object param [] = {idUnidad};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        
        Unidad u = new Unidad();
        while(datos.next()){
            u.setId(datos.getString("id"));
            u.setUnidad(datos.getString("unidad"));
            u.setTipo(datos.getString("tipo"));
	    u.setDocumentoPersona(datos.getString("persona_numero_documento"));
            
        }
        return u;
    }
     /**
    * carga todas las unidades dependiento del tipo de unidad que se pase como parámetro..util para cargar los combos de las unidades de todos los formularios
    * @param tipoUnidad tipo de unidad a consultar
    * @return una lista con todas las unidadades del tipo establecido en la consulta
    * @throws SQLException 
    */
   public ArrayList<Unidad> consultarUnidades(String tipoUnidad) throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT id, unidad, persona_numero_documento FROM unidad WHERE tipo = ?";
       Object param [] = {tipoUnidad};
       
       ArrayList<Unidad> unidades = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Unidad u = new Unidad();
           u.setId(datos.getString("id"));
           u.setUnidad(datos.getString("unidad"));
           u.setDocumentoPersona(datos.getString("persona_numero_documento"));
           unidades.add(u);
       }
       return unidades;
   }
   
}
