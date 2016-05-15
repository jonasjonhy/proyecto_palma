/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.PreparacionSuelo;
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
public class PreparacionSuelo_DAO {

    public PreparacionSuelo_DAO() {
        if (!BaseDeDatos.hayConexion()) {
            BaseDeDatos.conectar();
        }
    }

    /**
     * Metodo para registrar la preparacion del suelo de un Lote
     *
     * @param ps Object de la clase PreparacionSuelo que contiene la informacion
     * a registrar
     * @return true si el registro es exitoso
     */
    public boolean registrarPreparacionSuelo(PreparacionSuelo ps) {
        String consulta = "INSERT INTO preparacion_suelo (fecha registro,fecha_preparacion,tipo_a_m,cantidad,"
                + "precio,subtotal,labor_id,lote_zona_id) VALUES(?,?,?,?,?,?,?,?)";
        Object param[] = {ps.getFechaPreparacion(), ps.getFechaPreparacion(), ps.getTipoUso(), ps.getCantidadPreparado(), ps.getPrecio(),
            ps.getSubtotal(), ps.getIdLabor(), ps.getIdLote()};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    /**
     * Metodo para actualizar los datos de una preparacion de suelo de un lote
     *
     * @param ps Object con la informacion actualizada
     * @return true si la actualizacion es exitosa
     */
    public boolean actualizarPreparacionSuelo(PreparacionSuelo ps) {
        String consulta = "UPDATE preparacion_suelo SET fecha_preparacion = ?,tipo_a_m = ?,cantidad = ?,"
                + "precio,subtotal = ?,labor_id = ?,lote_zona_id = ?";
        Object param[] = {ps.getFechaPreparacion(), ps.getTipoUso(), ps.getCantidadPreparado(), ps.getPrecio(),
            ps.getSubtotal(), ps.getIdLabor(), ps.getIdLote()};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    /**
     * Metodo para eliminar registro de preparacion de suelo de un lote dado su
     * id
     *
     * @param id identificador de la preparacion de suelo
     * @return true si la eliminacion fue exitosa
     */
    public boolean eliminarPreparacionSuelo(String id) {
        String consulta = "DELETE FROM preparacion_suelo WHERE id = ? ";
        Object param[] = {id};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    /**
     * Metodo para obtener todas las preparacines de sulelo relacionados con un
     * Lote
     *
     * @param idLote codigo del lote
     * @param tipo especifica la preparacion del suelo s es a ó m
     * @return Lista de objects preparacion de suelo con la informacion
     * solicitada
     */
    public ArrayList<PreparacionSuelo> getPreparacionSuelo(String idLote, String tipo) {
        try {
            ArrayList<PreparacionSuelo> ps = new ArrayList<>();
            String consulta = "SELECT fecha_preparacion,labor_id,cantidad,precio,subtotal,lote_zona_id FROM preparacion_suelo"
                    + " WHERE lote_zona_id = ? AND tipo_a_m = ?";
            Object param[] = {idLote, tipo};
            
            ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
            
            while (dat.next()) {
                
                String fecha_prepa = dat.getString("fecha_preparacion");
                String idLabor = dat.getString("labor_id");
                String labor = new LaborSiembra_DAO().getNombreLaborSiembra(idLabor);
                String cantidad = dat.getString("cantidad");
                String precio = dat.getString("precio");
                String subtotal = dat.getString("subtotal");
                String codigoZonaLote = new Lote_DAO().obtenerCodigoLote(idLote);
                
                PreparacionSuelo p = new PreparacionSuelo();
                
                p.setFechaPreparacion(fecha_prepa);
                p.setIdLabor(idLabor);
                p.setLabor(labor);
                p.setIdLote(idLote);
                p.setCodigoZonaLote(codigoZonaLote);
                
                ps.add(p);
                
            }
            return ps;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public PreparacionSuelo getPreparacionSuelo(String id)
    {
        try {
            PreparacionSuelo ps = new PreparacionSuelo();
            String consulta = "SELECT fecha_preparacion,tipo_a_m,cantidad,precio,subtotal,labor_id,lote_zona_id FROM preparacion_suelo WHERE id = ?";
            Object param[] = {id};
            
            ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
            
            while (dat.next()) {
                
                String fecha_prepa = dat.getString("fecha_preparacion");
                String tipo = dat.getString("tipo_a_m");
                String cantidad = dat.getString("cantidad");
                String precio = dat.getString("precio");
                String subtotal = dat.getString("subtotal");
                String idLabor = dat.getString("labor_id");
                String labor = new LaborSiembra_DAO().getNombreLaborSiembra(idLabor);
                String idLote = dat.getString("lote_zona_id");
                String codigoZonaLote = new Lote_DAO().consultarCodigoLote(idLote);
                
                
                ps.setFechaPreparacion(fecha_prepa);
                ps.setTipoUso(tipo);
                ps.setCantidadPreparado(cantidad);
                ps.setPrecio(precio);
                ps.setSubtotal(subtotal);
                ps.setIdLabor(idLabor);
                ps.setLabor(labor);
                ps.setCodigoZonaLote(codigoZonaLote);
            }
            
            
            return ps;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para calcular el total de la suma de los subtotales de labores siembra 
     * de un Lote especifico
     * @param id id del lote 
     * @return resultado de la suma
     */
    public double getTotalPreparacionSuelo(String id)
    {   
        double total=0;
        try {
            String consulta = "SELECT subtotal FROM preparacion_suelo WHERE lote_zona_id = ?";
            Object param [] = {id};
            ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
            
            if (dat != null) {
                while (dat.next()) {
                    total += Double.parseDouble(dat.getString("subtotal"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el total labor siembra");
        }
        return (total);
    }
}
