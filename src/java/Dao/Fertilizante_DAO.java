/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.Fertilizante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class Fertilizante_DAO {
    
    /**
     * incluye unu nuevo fertilizante en la base de datos
     * @param f atributos del fertilizant (nombre, fecha de creacion, es decir la actual del sistema y el id de la persona que lo registra)
     * @return verdadero si se ha llevado a cabo el registro de buena manera
     */
    public boolean registrarNuevoFertilizante(Fertilizante f){
        
        BaseDeDatos.conectar();
        String registro = "INSERT INTO fertilizantes(fertilizante, fecha_creada, persona_numero_documento) VALUES(?, ?, ?)";
        Object param [] = {f.getNombreFertilizante(), f.getFechaCreacion(), f.getDocumentoPersona()};
        
        return BaseDeDatos.ejecutarActualizacionSQL(registro, param);
    }
    
    public Fertilizante consultarFertilizantePorNombre(String nombreFertilizante) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, fertilizante, fecha_creada, persona_numero_documento FROM fertilizantes WHERE fertilizante = ?";
        Object param [] = {nombreFertilizante};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        
        Fertilizante f = new Fertilizante();
        while(datos.next()){
            f.setId(datos.getString("id"));
            f.setNombreFertilizante(datos.getString("fertilizante"));
            f.setFechaCreacion(datos.getString("fecha_creada"));
            f.setDocumentoPersona(datos.getString("persona_numero_documento"));
        }
        return f;
    }
    
    public Fertilizante consultarFertilizantePorID(String idFertilizante) throws SQLException{
        
        BaseDeDatos.conectar();
        String consulta = "SELECT id, fertilizante, fecha_creada, persona_numero_documento FROM fertilizantes WHERE id = ?";
        Object param [] = {idFertilizante};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        
        Fertilizante f = new Fertilizante();
        while(datos.next()){
            f.setId(datos.getString("id"));
            f.setNombreFertilizante(datos.getString("fertilizante"));
            f.setFechaCreacion(datos.getString("fecha_creada"));
            f.setDocumentoPersona(datos.getString("persona_numero_documento"));
        }
        return f;
    }
    
    /**
     * consulta todos los fertilizantes de la BD
     * @return una lista de tipo Fertilizante con todos los fertilizantes registrados en la BD
     * @throws SQLException 
     */
    public ArrayList<Fertilizante> consultarFertilizantes() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT id, fertilizante, fecha_creada, persona_numero_documento FROM fertilizantes";
       
       ArrayList<Fertilizante> fertilizantes = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Fertilizante f = new Fertilizante();
           f.setId(datos.getString("id"));
           f.setNombreFertilizante(datos.getString("fertilizante"));
           f.setFechaCreacion(datos.getString("fecha_creada"));
           f.setDocumentoPersona(datos.getString("persona_numero_documento"));
           fertilizantes.add(f);
       }
       return fertilizantes;
    }
}
