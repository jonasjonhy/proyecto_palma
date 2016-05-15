/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.Herramienta;
import Dto.Maquina_Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Oscar Torres
 */
public class Herramienta_DAO {
     public ArrayList<Herramienta> consultarHerramienta() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT id, herramienta, fecha_creacion, persona_numero_documento FROM herramienta";
       
       ArrayList<Herramienta> h = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Herramienta he = new Herramienta();
           he.setId(Integer.parseInt(datos.getString("id")));
           he.setHerramienta(datos.getString("herramienta"));
           he.setFecha_creacion(datos.getString("fecha_creacion"));
           he.setPersona_numero_documento(datos.getString("persona_numero_documento"));
           h.add(he);
       }
       return h;
    }
}
