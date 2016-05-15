/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Correccion_suelo;
import Dto.Enmienda;
import Dto.Insumo;
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
public class SiembraCobertura_DAO {

   // Registra la siembra de cobertura en la base de datos
   public boolean registrarCobertura(Siembra_cobertura siembra) throws SQLException {

      BaseDeDatos.conectar();
      String sqlRegistro = "INSERT INTO siembra_cobertura( area_lote, fecha_registro, fecha_siembra, cantidad, precio_unid, precio_aplic, subtotal, lote_zona_id, insumos_id, unidad_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      Object param[] = { siembra.getArea_lote(), siembra.getFecha_registro(), siembra.getFecha_siembra(), siembra.getCantidad(), siembra.getPrecioUnidad(),siembra.getPrecioAplic(), siembra.getSubtotal(), siembra.getIdLote(), siembra.getIdInsumo(), siembra.getIdUnidad()};
      return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);

   }
   // Actualiza la siembra de cobertura en la base de datos segun su identificador

   public boolean actualizarCobertura(Siembra_cobertura siembra)throws SQLException {

      BaseDeDatos.conectar();
      String actualizacion = "UPDATE siembra_cobertura SET area_lote = ?, fecha_siembra = ?, cantidad = ?, precio_unid = ?, precio_aplic = ?, subtotal = ?, insumos_id = ?, unidad_id = ? WHERE id = ?";
      Object param[] = {siembra.getArea_lote(), siembra.getFecha_siembra(), siembra.getCantidad(), siembra.getPrecioUnidad(),siembra.getPrecioAplic(), siembra.getSubtotal(), siembra.getIdInsumo(), siembra.getIdUnidad(), siembra.getId()};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }
// Elimina la siembra de cobertura en la base de datos segun su identificador
   public boolean eliminarCobertura(String id ) {
      BaseDeDatos.conectar();
      String actualizacion = "DELETE FROM siembra_cobertura WHERE id = ? ";
      Object param[] = {id};
      return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
   }

public ArrayList<Siembra_cobertura> consultarCoberturas(String idLote) {
   
	 ArrayList<Siembra_cobertura> coberturas = new ArrayList<>();
	 try{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, fecha_siembra, area_lote, cantidad, precio_unid, precio_aplic, subtotal, insumos_id, unidad_id FROM siembra_cobertura WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();       
        
        while(datos.next()){
            Siembra_cobertura s = new Siembra_cobertura();
            
	 String id = datos.getString("id");
	 String fecha_siembra = datos.getString("fecha_siembra");
	 String area_lote = datos.getString("area_lote");
	 String Insumo = new Insumo_DAO().consultarInsumoPorID(datos.getString("insumos_id")).getInsumo();
	 String cantidad = datos.getString("cantidad");
	 String unidad = new Unidad_DAO().consultarUnidadPorID(datos.getString("unidad_id")).getUnidad();
	 String subtotal = datos.getString("subtotal");
	 String precio_unid = datos.getString("precio_unid");
	 String precio_aplic = datos.getString("precio_aplic");
	

	 s.setId(id);
	 s.setFecha_siembra(fecha_siembra);
	 s.setArea_lote(area_lote);
	 s.setCantidad(cantidad);
	 s.setIdInsumo(Insumo);
	 s.setIdUnidad(unidad);
	 s.setSubtotal(subtotal);
	 s.setPrecioUnidad(precio_unid);
	 s.setPrecioAplic(precio_aplic);
	 
       coberturas.add(s);
        }
        return coberturas;
	} catch (SQLException ex) {
            
            Siembra_cobertura s = new Siembra_cobertura();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL SUBTOTAL COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            s.setId(nada);
            coberturas.add(s);
            return coberturas;
            
        }
    }
  
  public double subtotalesSiembraCobertura(String idLote) {
        float subtotales = 0;
	try{
        BaseDeDatos.conectar();
        String consulta = "SELECT subtotal FROM siembra_cobertura WHERE lote_zona_id = ?";
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
  
 public double costosAplicacionSiembraCobertura(String idLote) {
        double costosT = 0;
	try{
        BaseDeDatos.conectar();
        String consulta = "SELECT precio_aplic FROM siembra_cobertura WHERE lote_zona_id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();        
        
        while(datos.next()){
            double costoAplic = Double.parseDouble(datos.getString("precio_aplic"));
            costosT+= costoAplic;
        }
        return costosT;
	} catch (SQLException ex) {
            return costosT;
        }
    }
 
      public Siembra_cobertura consultarCobertura(String idCobertura) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, area_lote, fecha_siembra, cantidad, precio_unid, precio_aplic, unidad_id, lote_zona_id, insumos_id, subtotal FROM siembra_cobertura WHERE id = ?";
        Object param [] = {idCobertura};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        Siembra_cobertura s= new Siembra_cobertura();
        while(datos.next()){
            s.setId(datos.getString("id"));
            s.setArea_lote(datos.getString("area_lote"));
            s.setFecha_siembra(datos.getString("fecha_siembra"));
            s.setCantidad(datos.getString("cantidad"));
	    s.setPrecioUnidad(datos.getString("precio_unid"));
            s.setPrecioAplic(datos.getString("precio_aplic"));
            s.setIdUnidad(datos.getString("unidad_id"));
            s.setIdLote(datos.getString("lote_zona_id"));
            s.setIdInsumo(datos.getString("insumos_id"));
            s.setSubtotal(datos.getString("subtotal"));
        }
        return s;
    } 
}
