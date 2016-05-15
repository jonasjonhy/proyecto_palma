/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.NivelacionNutriente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class nivelacionNutrientes_DAO {

    public nivelacionNutrientes_DAO() {
    }
    
    public boolean registrarNivelacion(NivelacionNutriente nivelacion){
        BaseDeDatos.conectar();
        String registroBD = "INSERT INTO nivelacion_nutrientes(area_lote, fecha_registro, fecha_aplic, cantidad, precio_nutriente, precio_aplic, unidad_id, lote_zona_id, fertilizantes_id, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object param [] = {nivelacion.getAreaLote(), nivelacion.getFechaRegistro(), nivelacion.getFechaAplicacion(), nivelacion.getCantidadFertilizante(), nivelacion.getPrecioFertilizante(), nivelacion.getPrecioAplicacion(), nivelacion.getIdUnidad(), nivelacion.getIdLote(), nivelacion.getIdFertilizante(), nivelacion.getSubtotal()};
              
        return BaseDeDatos.ejecutarActualizacionSQL(registroBD, param);
    }
    
    public boolean eliminarNivelacion(String idNivelacion){
        BaseDeDatos.conectar();
        String eliminar = "DELETE FROM nivelacion_nutrientes WHERE id = ?";
        Object param [] = {idNivelacion};
        return BaseDeDatos.ejecutarActualizacionSQL(eliminar, param);
    }
    
    //REVISAR EL METODO
    public boolean editarNivelacion(NivelacionNutriente niv){
        
        BaseDeDatos.conectar();
        String actualizar = "UPDATE nivelacion_nutrientes SET fecha_aplic = ?, fertilizantes_id = ?, cantidad = ?, unidad_id = ?, precio_nutriente = ?, precio_aplic = ?, area_lote = ?, subtotal = ? WHERE id = ?";
        Object param [] = {niv.getFechaAplicacion(), niv.getIdFertilizante(), niv.getCantidadFertilizante(), niv.getIdUnidad(), niv.getPrecioFertilizante(), niv.getPrecioAplicacion(), niv.getAreaLote(), niv.getSubtotal(), niv.getId()};
        return BaseDeDatos.ejecutarActualizacionSQL(actualizar, param);
    }
    
    
    public ArrayList<NivelacionNutriente> consultarNivelacionesDeUnLote(String idLote){
        
        ArrayList<NivelacionNutriente> nivelaciones = new ArrayList<>();        
       
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_aplic, area_lote, fertilizantes_id, cantidad, unidad_id, subtotal, precio_aplic, precio_nutriente FROM nivelacion_nutrientes WHERE lote_zona_id = ?";
            Object param [] = {idLote};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            
            //BaseDeDatos.desconectar();
            while(datos.next()){
                
                NivelacionNutriente n = new NivelacionNutriente();
                String id = datos.getString("id");
                String fecha_aplic = datos.getString("fecha_aplic");
                String area_lote = datos.getString("area_lote");
                String fertilizante = new Fertilizante_DAO().consultarFertilizantePorID(datos.getString("fertilizantes_id")).getNombreFertilizante();
                String cantidad = datos.getString("cantidad");
                String unidad = new Unidad_DAO().consultarUnidadPorID(datos.getString("unidad_id")).getUnidad();
                String subtotal = datos.getString("subtotal");
                String precio_aplic = datos.getString("precio_aplic");
                String precioFertilizante = datos.getString("precio_nutriente");
                
                n.setId(id);
                n.setFechaAplicacion(fecha_aplic);
                n.setAreaLote(area_lote);
                n.setIdFertilizante(fertilizante);// aqui llevo es el nombre del fertiliante
                n.setCantidadFertilizante(cantidad);
                n.setIdUnidad(unidad); //aqui llevo es el nombre de la unidad
                n.setSubtotal(subtotal);
                n.setPrecioAplicacion(precio_aplic);
                n.setPrecioFertilizante(precioFertilizante);
          
                nivelaciones.add(n);
            }
            
        } catch (SQLException ex) {
            NivelacionNutriente m = new NivelacionNutriente();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            m.setId(nada);
            nivelaciones.add(m);            
        }
        return nivelaciones;
    }
    
    public double subtotalNivelacionesDeLote(String idLote){
        double subtotales = 0;
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT subtotal FROM nivelacion_nutrientes WHERE lote_zona_id = ?";
            Object param [] = {idLote};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            
            while(datos.next()){
                double subtotal = Double.parseDouble(datos.getString("subtotal"));
                subtotales+= subtotal;
            }
            return subtotales;
            
        } catch (SQLException ex) {
            return subtotales;
        }
    }
    
    public float costosAplicacionNivelacionLote(String idLote){
        float costosT = 0;
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT precio_aplic FROM nivelacion_nutrientes WHERE lote_zona_id = ?";
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
    
    
    public NivelacionNutriente consultarNivelacion(String idNivelacion) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, area_lote, fecha_aplic, cantidad, precio_nutriente, precio_aplic, unidad_id, lote_zona_id, fertilizantes_id, subtotal FROM nivelacion_nutrientes WHERE id = ?";
        Object param [] = {idNivelacion};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        NivelacionNutriente n = new NivelacionNutriente();
        while(datos.next()){
            n.setId(datos.getString("id"));
            n.setAreaLote(datos.getString("area_lote"));
            n.setFechaAplicacion(datos.getString("fecha_aplic"));
            n.setPrecioFertilizante(datos.getString("precio_nutriente"));
            n.setPrecioAplicacion(datos.getString("precio_aplic"));
            n.setIdUnidad(datos.getString("unidad_id"));
            n.setIdLote(datos.getString("lote_zona_id"));
            n.setIdFertilizante(datos.getString("fertilizantes_id"));
            n.setSubtotal(datos.getString("subtotal"));
            n.setCantidadFertilizante(datos.getString("cantidad"));
        }
        return n;
    }   
}
