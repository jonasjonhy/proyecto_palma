/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.PalmaSiembra;
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
public class PalmaSiembra_DAO {

    public PalmaSiembra_DAO() {
    }
    
    public boolean registroPalmaSiembra(PalmaSiembra siembra){
        BaseDeDatos.conectar();
        String registro = "INSERT INTO palma_siembra(fecha_registro, fecha_siembra, cantidad_plantulas, precio, subtotal, lote_zona_id, area_lote) VALUES(?, ?, ?, ?, ?, ?, ?)";
        Object param [] = {siembra.getFechaRegistro(), siembra.getFechaSiembra(), siembra.getCantidadPlantulas(), siembra.getPrecioPlantula(), siembra.getSubtotal(), siembra.getIdLote(), siembra.getAreaLote()};
        
        boolean registroBD = BaseDeDatos.ejecutarActualizacionSQL(registro, param);
        //BaseDeDatos.desconectar();
        
        return registroBD;
    }
    
    public boolean eliminarPalmaSiembra(String idSiembra){
        BaseDeDatos.conectar();
        String eliminar = "DELETE FROM palma_siembra WHERE id = ?";
        Object param[] = {idSiembra};
        
        boolean elimino = BaseDeDatos.ejecutarActualizacionSQL(eliminar, param);
        
        return elimino;
    }
    
    public boolean editarPalmaSiembra(PalmaSiembra siembra){
        BaseDeDatos.conectar();
        String actualizacion = "UPDATE palma_siembra SET fecha_siembra = ?, cantidad_plantulas = ?, precio = ?, subtotal = ?, area_lote = ? WHERE id = ?";
        Object param [] = {siembra.getFechaSiembra(), siembra.getCantidadPlantulas(), siembra.getPrecioPlantula(), siembra.getSubtotal(), siembra.getAreaLote(), siembra.getId()};
        
        boolean actualizo = BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
        //BaseDeDatos.desconectar();
        
        return actualizo;
    }
    
    public double subtotalesPalmaSiembraDeLote(String idLote){
        double subtotales = 0;
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT subtotal FROM palma_siembra WHERE lote_zona_id = ?";
            Object param [] = {idLote};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            //BaseDeDatos.desconectar();        
            
            while(datos.next()){
                double subtotal = Double.parseDouble(datos.getString("subtotal"));
                subtotales+= subtotal;
            }
            return subtotales;
        } catch (SQLException ex) {
            return subtotales;
        }
    }
    
    public ArrayList<PalmaSiembra> consultarSiembras(String idLote){
        
        ArrayList<PalmaSiembra> siembras = new ArrayList<>();
        
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id,fecha_siembra, area_lote, cantidad_plantulas, precio, subtotal FROM palma_siembra WHERE lote_zona_id = ?";
            Object param [] = {idLote};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            //BaseDeDatos.desconectar();
            
            while(datos.next()){
                PalmaSiembra s = new PalmaSiembra();
                String fechaSiembra = datos.getString("fecha_siembra");
                String areaLote = datos.getString("area_lote");
                String cantidadP = datos.getString("cantidad_plantulas");
                String precio = datos.getString("precio");
                String subtotal = datos.getString("subtotal");
                String id = datos.getString("id");
                
                s.setFechaSiembra(fechaSiembra);
                s.setAreaLote(areaLote);
                s.setCantidadPlantulas(cantidadP);
                s.setPrecioPlantula(precio);
                s.setSubtotal(subtotal);
                s.setId(id);
                siembras.add(s);
            }
     
        } catch (SQLException ex) {
            PalmaSiembra s = new PalmaSiembra();
            s.setId("nada");
            siembras.add(s);
        }
        return siembras;
    }
    
    public PalmaSiembra consultarSiembra(String idSiembra) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT id, area_lote, fecha_siembra, cantidad_plantulas, precio, subtotal, lote_zona_id FROM palma_siembra  WHERE id = ?";
        Object param [] = {idSiembra};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        //BaseDeDatos.desconectar();
        PalmaSiembra s = new PalmaSiembra();
        while(datos.next()){
            s.setId(datos.getString("id"));
            s.setAreaLote(datos.getString("area_lote"));
            s.setFechaSiembra(datos.getString("fecha_siembra"));
            s.setCantidadPlantulas(datos.getString("cantidad_plantulas"));
            s.setPrecioPlantula(datos.getString("precio"));
            s.setSubtotal(datos.getString("subtotal"));
            s.setIdLote(datos.getString("lote_zona_id"));
        }
        return s;
    }   
    
    
}
