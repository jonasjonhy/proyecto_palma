/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;
import Dto.Enmienda;
import Dto.Insumo;
import Dto.Item;
import Dto.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Pedro
 */
public class Enmienda_DAO {
   
    public boolean registrarNuevaEnmienda(Enmienda e)throws SQLException{
      BaseDeDatos.conectar();
            String sqlRegistro = "INSERT INTO enmienda( nombre_enmienda,fecha_creado, persona_numero_documento) VALUES (?,?,?)";
            Object param[] = { e.getEnmienda(),e.getFecha(), e.getDocumentoPersona()};
            return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
   }
   public Enmienda consultarEnmiendaPorNombre(String enmienda) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, nombre_enmienda, fecha_creado, persona_numero_documento FROM enmienda WHERE nombre_enmienda = ?";
        Object param [] = {enmienda};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
	Enmienda e = new Enmienda();
        while(datos.next()){
           e.setId(datos.getString("id"));
           e.setEnmienda(datos.getString("nombre_enmienda"));
           e.setFecha(datos.getString("fecha_creado"));
	   e.setDocumentoPersona("persona_numero_documento");
        }
        return e;
    }
   
   public Enmienda consultarEnmiendaPorID(String idEnmienda) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, nombre_enmienda, fecha_creado,persona_numero_documento FROM enmienda WHERE id = ?";
        Object param [] = {idEnmienda};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        
       Enmienda e = new Enmienda();
        while(datos.next()){
           e.setId(datos.getString("id"));
           e.setEnmienda(datos.getString("nombre_enmienda"));
           e.setFecha(datos.getString("fecha_creado"));
	   e.setDocumentoPersona("persona_numero_documento");
           
        }
        return e;
    }
    public ArrayList<Enmienda> consultarEnmiendas() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT * FROM enmienda ";
    
       ArrayList<Enmienda> enmiendas = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Enmienda e = new Enmienda();
           e.setId(datos.getString("id"));
           e.setEnmienda(datos.getString("nombre_enmienda"));
           e.setDocumentoPersona(datos.getString("persona_numero_documento"));
           enmiendas.add(e);
       }
       return enmiendas;
   }
}
