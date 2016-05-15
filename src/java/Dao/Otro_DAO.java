/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Insumo;
import Dto.Item;
import Dto.Otro;
import Dto.Siembra_cobertura;
import Dto.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Pedro
 */
public class Otro_DAO {

   // Registra la siembra de cobertura en la base de datos
   public boolean registrarOtro(Otro o) throws SQLException {
       System.out.println(o.getAreaLote()+" "+o.getCantidadItem()+" "+o.getFecha_activacion()+" "+o.getFecha_registro()+" "+o.getIdItem()+" "+o.getIdLote()+" "+o.getIdUnidad()+" "+o.getPrecioItem()+" "+o.getPrecioAplicacion()+" "+o.getSubtotal());

      BaseDeDatos.conectar();
      String sqlRegistro = "INSERT INTO otro_ce(area, fecha_registro, fecha_act, cantidad, precio, precio_aplic, subtotal , unidad_id, lote_zona_id, item_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
      Object param[] = {o.getAreaLote(),o.getFecha_registro(), o.getFecha_activacion(), o.getCantidadItem(), o.getPrecioItem(),o.getPrecioAplicacion(),o.getSubtotal(), o.getIdUnidad(),o.getIdLote(), o.getIdItem()};
      return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);

   }
   public boolean actualizarOtro( Otro o) throws SQLException {
       System.out.println(o.getId()+" "+o.getAreaLote()+" "+o.getCantidadItem()+" "+o.getFecha_activacion()+" "+o.getIdItem()+" "+o.getIdLote()+" "+o.getIdUnidad()+" "+o.getPrecioItem()+" "+o.getPrecioAplicacion()+" "+o.getSubtotal());
      BaseDeDatos.conectar();
      String actualizacion = "UPDATE otro_ce SET area = ?,  fecha_act = ?, cantidad = ?, precio = ?, precio_aplic = ?, subtotal = ?, unidad_id = ?, item_id = ? WHERE id = ?";
      Object param[] = {o.getAreaLote(),o.getFecha_activacion(), o.getCantidadItem(),o.getPrecioItem(),o.getPrecioAplicacion(),o.getSubtotal(),o.getIdUnidad(),o.getIdItem(),o.getId()};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }
   
   public boolean eliminarOtro(String id) {
      BaseDeDatos.conectar();
      String actualizacion = "DELETE FROM otro_ce WHERE id = ?";
      Object param[] = {id};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }
   public ArrayList<Otro> consultarOtros(String idLote){
         ArrayList<Otro> otros = new ArrayList<>();
      try {
        BaseDeDatos.conectar();
        String consulta = "SELECT id, fecha_act, area, cantidad, precio, precio_aplic, subtotal, item_id, unidad_id FROM otro_ce WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        
       
        
        while(datos.next()){
            Otro o = new Otro();
            
	 String id = datos.getString("id");
	 String fecha_act = datos.getString("fecha_act");
	 String area_lote = datos.getString("area");
	 String Item = new Item_DAO().consultarItemPorID(datos.getString("item_id")).getNombre();
	 String cantidad = datos.getString("cantidad");
	 String unidad = new Unidad_DAO().consultarUnidadPorID(datos.getString("unidad_id")).getUnidad();
	 String subtotal = datos.getString("subtotal");
	 String precio = datos.getString("precio");
	 String precio_aplic = datos.getString("precio_aplic");
	

	 o.setId(id);
	 o.setFecha_activacion(fecha_act);
	 o.setIdItem(Item);
	 o.setAreaLote(area_lote);
	 o.setIdUnidad(unidad);
	 o.setCantidadItem(cantidad);
	 o.setSubtotal(subtotal);
	 o.setPrecioItem(precio);
	o.setPrecioAplicacion(precio_aplic);
	 
      otros.add(o);
        }
        return otros;
	} catch (SQLException ex) {
            
            Otro o = new Otro();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL SUBTOTAL COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            o.setSubtotal(nada);
            otros.add(o);
            return otros;
            
        }
    }
  public float subtotalesOtro(String idLote){
      float subtotales = 0;
      try{
     BaseDeDatos.conectar();
        String consulta = "SELECT subtotal FROM otro_ce WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        
       
        
        while(datos.next()){
            float subtotal = Float.parseFloat(datos.getString("subtotal"));
            subtotales+= subtotal;
        }
        return subtotales;
	}catch (SQLException ex) {
            return subtotales;
        }
    }
   public float costosAplicacionOtro(String idLote) {
        float costosT = 0;
	try{
      BaseDeDatos.conectar();
        String consulta = "SELECT precio_aplic FROM otro_ce WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
       
        
        while(datos.next()){
            float costoAplic = Float.parseFloat(datos.getString("precio_aplic"));
            costosT+= costoAplic;
        }
        return costosT;
	} catch (SQLException ex) {
            return costosT;
        }
    }
     public Otro consultarOtro(String idOtro) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, area, fecha_act, cantidad, precio, precio_aplic, unidad_id, lote_zona_id, item_id, subtotal FROM otro_ce WHERE id = ?";
        Object param [] = {idOtro};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        Otro o = new Otro();
	while(datos.next()){
            o.setId(datos.getString("id"));
            o.setAreaLote(datos.getString("area"));
            o.setFecha_activacion(datos.getString("fecha_act"));
            o.setCantidadItem(datos.getString("cantidad"));
	    o.setPrecioItem(datos.getString("precio"));
            o.setPrecioAplicacion(datos.getString("precio_aplic"));
            o.setIdUnidad(datos.getString("unidad_id"));
            o.setIdLote(datos.getString("lote_zona_id"));
            o.setIdItem(datos.getString("item_id"));
            o.setSubtotal(datos.getString("subtotal"));
        }
        return o;
    } 
}
