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
public class Item_DAO {

   public boolean registrarNuevoItem(Item i) throws SQLException {
      BaseDeDatos.conectar();
      String sqlRegistro = "INSERT INTO item( nombre,fecha_creado, persona_numero_documento) VALUES (?, ?, ?)";

      Object param[] = {i.getNombre(), i.getFecha(), i.getDocumentoPersona()};
      return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
   }

   public Item consultarItemPorNombre(String Item) throws SQLException {

      BaseDeDatos.conectar();
      String consulta = "SELECT id, nombre, fecha_creado, persona_numero_documento FROM item WHERE nombre = ?";
      Object param[] = {Item};
      ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
      //BaseDeDatos.desconectar();
      Item i = new Item();
      while (datos.next()) {
	 i.setId(datos.getString("id"));
	 i.setNombre(datos.getString("nombre"));
	 i.setFecha(datos.getString("fecha_creado"));
	 i.setDocumentoPersona("persona_numero_documento");

      }
      return i;
   }

   public Item consultarItemPorID(String idItem) throws SQLException {

      BaseDeDatos.conectar();
      String consulta = "SELECT id, nombre, fecha_creado,persona_numero_documento FROM item WHERE id = ?";
      Object param[] = {idItem};
      ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
      //BaseDeDatos.desconectar();

      Item i = new Item();
      while (datos.next()) {
	 i.setId(datos.getString("id"));
	 i.setNombre(datos.getString("nombre"));
	 i.setFecha(datos.getString("fecha_creado"));
	 i.setDocumentoPersona(datos.getString("persona_numero_documento"));

      }
      return i;
   }
    public ArrayList<Item> consultarItems() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT * FROM item";
       
       ArrayList<Item> items = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Item i = new Item();
           i.setId(datos.getString("id"));
           i.setNombre(datos.getString("nombre"));
           i.setDocumentoPersona(datos.getString("persona_numero_documento"));
           items.add(i);
       }
       return items;
   }
}
