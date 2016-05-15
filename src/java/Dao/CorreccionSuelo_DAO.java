/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Correccion_suelo;
import Dto.Enmienda;
import Dto.NivelacionNutriente;
import Dto.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Pedro
 */
public class CorreccionSuelo_DAO {

   public CorreccionSuelo_DAO() {

   }

   //Registra la correccion de suelos en la base de datos 
   public boolean registrarCorreccion(Correccion_suelo correccion) throws SQLException {

      BaseDeDatos.conectar();
      String sqlRegistro = "INSERT INTO correcionSuelo(area_aplic, fecha_registro, fecha_aplic, cantidad,precio_enmienda, precio_aplic_enmienda, subtotal, enmienda_id, unidad_id, lote_zona_id) VALUES ( ?, ?,?,?,?,?,?,?,?,?)";
      Object param[] = {correccion.getArea_aplicacion(), correccion.getFecha_registro(), correccion.getFecha_aplicacion(), correccion.getCantidad(), correccion.getPrecio_enmienda(), correccion.getPrecio_aplic_enmienda(), correccion.getSubtotal(),correccion.getIdEnmienda(), correccion.getIdUnidad(), correccion.getIdLote()};
      return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);

   }
//Actualiza la correccion de suelos en la base de datos en base a su identificador 

   public boolean actualizarCorreccion(Correccion_suelo correccion) throws SQLException {
       System.out.println("id CORRECCION!! ----> "+correccion.getId());
      BaseDeDatos.conectar();
      String actualizacion = "UPDATE correcionsuelo SET area_aplic = ?, fecha_aplic = ?, cantidad = ?, precio_enmienda = ?, precio_aplic_enmienda = ?, subtotal = ?, enmienda_id = ?, unidad_id = ? WHERE id = ?";
      Object param[] = {correccion.getArea_aplicacion(), correccion.getFecha_aplicacion(), correccion.getCantidad(), correccion.getPrecio_enmienda(), correccion.getPrecio_aplic_enmienda(), correccion.getSubtotal(), correccion.getIdEnmienda(), correccion.getIdUnidad(), correccion.getId()};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }
// Elimina la coreccion en base al identificador

   public boolean eliminarCorreccion(String id) {
      BaseDeDatos.conectar();
      String actualizacion = "DELETE FROM correcionsuelo WHERE id= ? ";
      Object param[] = {id};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }

   public ArrayList<Correccion_suelo> consultarCorreccionesDeUnLote(String idLote)  {
      ArrayList<Correccion_suelo> correcciones = new ArrayList<>();
      try {
      BaseDeDatos.conectar();
      String consulta = "SELECT id,area_aplic, fecha_aplic, cantidad, precio_enmienda, precio_aplic_enmienda, subtotal, enmienda_id, unidad_id FROM correcionsuelo WHERE lote_zona_id = ? ";
      Object param[] = {idLote};
      ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
     
      while (datos.next()) {

	 Correccion_suelo c = new Correccion_suelo();
	 String id = datos.getString("id");
	 String fecha_aplic = datos.getString("fecha_aplic");
	 String area_aplic = datos.getString("area_aplic");
	 String Enmienda = new Enmienda_DAO().consultarEnmiendaPorID(datos.getString("enmienda_id")).getEnmienda();
	 String cantidad = datos.getString("cantidad");
	 String unidad = new Unidad_DAO().consultarUnidadPorID(datos.getString("unidad_id")).getUnidad();
	 String subtotal = datos.getString("subtotal");
	 String precio_enmienda = datos.getString("precio_enmienda");
	 String precio_aplic_Enmienda = datos.getString("precio_aplic_enmienda");

	 c.setId(id);
	 c.setFecha_aplicacion(fecha_aplic);
	 c.setArea_aplicacion(area_aplic);
	 c.setIdEnmienda(Enmienda);
	 c.setCantidad(cantidad);
	 c.setIdUnidad(unidad);
	 c.setSubtotal(subtotal);
	 c.setPrecio_enmienda(precio_enmienda);
	 c.setPrecio_aplic_enmienda(precio_aplic_Enmienda);

	 correcciones.add(c);

      }

      return correcciones;
      } catch (SQLException ex) {
            
            Correccion_suelo c = new Correccion_suelo();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL SUBTOTAL COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
            correcciones.add(c);
            return correcciones;
            
        }
   }
   
    public double subtotalesCorreccionSuelos(String idLote) {
       double subtotales = 0;
       try{
       BaseDeDatos.conectar();
        String consulta = "SELECT subtotal FROM correcionsuelo WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();       
        
        while(datos.next()){
            double subtotal = Double.parseDouble(datos.getString("subtotal"));
            subtotales+= subtotal;
        }
        return subtotales;
       }catch (SQLException ex) {
            return subtotales;
        }
    }
     public double costosAplicacionCorreccionSuelos(String idLote) {
        double costosT = 0;
	try{
	BaseDeDatos.conectar();
        String consulta = "SELECT precio_aplic_enmienda FROM correcionsuelo WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        
        while(datos.next()){
            double costoAplic = Double.parseDouble(datos.getString("precio_aplic_enmienda"));
            costosT+= costoAplic;
        }
        return costosT;
	} catch (SQLException ex) {
            return costosT;
        }
    }
       public Correccion_suelo consultarCorreccion(String idCorreccion) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, area_aplic, fecha_aplic, cantidad, precio_enmienda, precio_aplic_enmienda, unidad_id, lote_zona_id, enmienda_id, subtotal FROM correcionsuelo WHERE id = ?";
        Object param [] = {idCorreccion};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        Correccion_suelo c = new Correccion_suelo();
        while(datos.next()){
            c.setId(datos.getString("id"));
            c.setArea_aplicacion(datos.getString("area_aplic"));
            c.setFecha_aplicacion(datos.getString("fecha_aplic"));
            c.setCantidad(datos.getString("cantidad"));
	    c.setPrecio_enmienda(datos.getString("precio_enmienda"));
            c.setPrecio_aplic_enmienda(datos.getString("precio_aplic_enmienda"));
            c.setIdUnidad(datos.getString("unidad_id"));
            c.setIdLote(datos.getString("lote_zona_id"));
            c.setIdEnmienda(datos.getString("enmienda_id"));
            c.setSubtotal(datos.getString("subtotal"));
        }
        return c;
    }  
}
