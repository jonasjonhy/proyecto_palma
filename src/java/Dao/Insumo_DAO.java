/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

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
public class Insumo_DAO {

   public boolean registrarNuevoInsumo(Insumo i) throws SQLException {
      BaseDeDatos.conectar();
      String sqlRegistro = "INSERT INTO insumos( insumo,fecha_creado, persona_numero_documento) VALUES (?, ?, ?)";

      Object param[] = {i.getInsumo(), i.getFecha_creacion(), i.getDocumentoPersona()};
      return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
   }

   public Insumo consultarInsumoPorNombre(String insumo) throws SQLException {

      BaseDeDatos.conectar();
      String consulta = "SELECT id, insumo, fecha_creado, persona_numero_documento FROM insumos WHERE insumo = ?";
      Object param[] = {insumo};
      ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
      //BaseDeDatos.desconectar();
      Insumo i = new Insumo();
      while (datos.next()) {
	 i.setId(datos.getString("id"));
	 i.setInsumo(datos.getString("insumo"));
	 i.setFecha_creacion(datos.getString("fecha_creado"));
	 i.setDocumentoPersona(" persona_numero_documento");

      }
      return i;
   }

   public Insumo consultarInsumoPorID(String idInsumo) throws SQLException {

      BaseDeDatos.conectar();
      String consulta = "SELECT id, insumo, fecha_creado,persona_numero_documento FROM insumos WHERE id = ?";
      Object param[] = {idInsumo};
      ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
      //BaseDeDatos.desconectar();

      Insumo i = new Insumo();
      while (datos.next()) {
	 i.setId(datos.getString("id"));
	 i.setInsumo(datos.getString("insumo"));
	 i.setFecha_creacion(datos.getString("fecha_creado"));
	 i.setDocumentoPersona(" persona_numero_documento");

      }
      return i;
   }
   public ArrayList<Insumo> consultarInsumos() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT * FROM insumos ";
       
       ArrayList<Insumo> insumos = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Insumo i = new Insumo();
           i.setId(datos.getString("id"));
           i.setInsumo(datos.getString("insumo"));
           i.setDocumentoPersona(datos.getString("persona_numero_documento"));
           insumos.add(i);
       }
       return insumos;
   }
}
