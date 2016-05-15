/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.CFMaquinaria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.*;

/**
 *
 * @author Oscar Torres
 */
public class CFMaquinaria_DAO {
    
    public String consultarMaquina (int idMaquina){
    String id="";
    try{
    BaseDeDatos.conectar();
    String consulta="SELECT maquina_equipo FROM maquina_equipo WHERE id = ?";
    Object Param [] = {idMaquina};
    ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, Param);
        while(datos.next()){
        id=datos.getString("maquina_equipo");        
        }
        return id;
    }catch(SQLException ex){
    return id;
    }
    
    }
    public ArrayList<CFMaquinaria> consultarCFMaquinaria(int idHacienda){
        
        ArrayList<CFMaquinaria> cfmaq = new ArrayList<>();        
       
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, cantidad, precio_unidad, subtotal_compra, vida_util, precio_agnoutil, area_servicio, costo_hectarea, area_lotes, subtotal_maquinaria, maquina_equipo_id FROM cf_maquinas_equipos WHERE hacienda_id = ?";
            Object param [] = {idHacienda};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);   //BaseDeDatos.desconectar();
            while(datos.next()){                
                CFMaquinaria cfm = new CFMaquinaria();
                String id = datos.getString("id");
                String fecha_reg = datos.getString("fecha_registro");
                String cantidad = datos.getString("cantidad");
//                String fertilizante = new Fertilizante_DAO().consultarFertilizantePorID(datos.getString("fertilizantes_id")).getNombreFertilizante();
                String precioUnd = datos.getString("precio_unidad");
//                String unidad = new Unidad_DAO().consultarUnidadPorID(datos.getString("unidad_id")).getUnidad();
                String subtotal_compra = datos.getString("subtotal_compra");
                String vidaUtil= datos.getString("vida_util");
                String precio_agnoutil = datos.getString("precio_agnoutil");
                String areaServicio = datos.getString("area_servicio");
                String costoHectarea=datos.getString("costo_Hectarea");
                String areaLotes=datos.getString("area_lotes");
                String subtotalMaq=datos.getString("subtotal_maquinaria");
                int idMaquina=Integer.parseInt(datos.getString("maquina_equipo_id"));
                
                cfm.setId(Integer.parseInt(id));
                cfm.setFechaRegistro(fecha_reg);
                cfm.setCantidad(Integer.parseInt(cantidad));
                cfm.setIdMaquina(idMaquina);
                cfm.setPrecioUnd(Double.parseDouble(precioUnd));
                cfm.setSubtotalCompra(Double.parseDouble(subtotal_compra));
                cfm.setVidaUtil(Integer.parseInt(vidaUtil));    
                cfm.setPrecioAñoUtil(Double.parseDouble(precio_agnoutil));
                cfm.setAreaServicio(Float.parseFloat(areaServicio));
                cfm.setCostoHectarea(Double.parseDouble(costoHectarea));
                cfm.setAreal_lotes(Float.parseFloat(areaLotes));
                cfm.setSubtotalMaquinaria(Double.parseDouble(subtotalMaq));
          
                cfmaq.add(cfm);
            }
            
        } catch (SQLException ex) {
            CFMaquinaria cfm = new CFMaquinaria();
            int id = -1; 
            cfm.setId(id);
            cfmaq.add(cfm);            
        }
        return cfmaq;
    }
    
    public boolean registrarCFMaquina(CFMaquinaria cfMaquinaria){
        BaseDeDatos.conectar();
        String registroBD = "INSERT INTO cf_maquinas_equipos(fecha_registro, cantidad, precio_unidad, subtotal_compra, vida_util, precio_agnoutil, area_servicio, costo_hectarea, area_lotes, subtotal_maquinaria, hacienda_id, maquina_equipo_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object param [] = {cfMaquinaria.getFechaRegistro(), cfMaquinaria.getCantidad(), cfMaquinaria.getPrecioUnd(), cfMaquinaria.getSubtotalCompra(), cfMaquinaria.getVidaUtil(), cfMaquinaria.getPrecioAñoUtil(), cfMaquinaria.getAreaServicio(), cfMaquinaria.getCostoHectarea(), cfMaquinaria.getAreal_lotes(), cfMaquinaria.getSubtotalMaquinaria(), cfMaquinaria.getIdHacienda(),cfMaquinaria.getIdMaquina() };
              
        return BaseDeDatos.ejecutarActualizacionSQL(registroBD, param);
    }
    
     public boolean eliminarCFMaquina(int idCFMaquina){
        BaseDeDatos.conectar();
        String eliminar = "DELETE FROM cf_maquinas_equipos WHERE id = ?";
        Object param [] = {idCFMaquina};
        return BaseDeDatos.ejecutarActualizacionSQL(eliminar, param);
    }
     
      public boolean editarCFMaquina(CFMaquinaria cfm){
        
        BaseDeDatos.conectar();
        String actualizar = "UPDATE cf_maquinas_equipos SET fecha_registro = ?, cantidad = ?, precio_unidad = ?, subtotal_compra = ?, vida_util = ?, precio_agnoutil = ?, area_servicio = ?, costo_hectarea = ?, area_lotes = ?, subtotal_maquinaria = ?, maquina_equipo_id = ? WHERE id = ?";
        Object param [] = {};
        return BaseDeDatos.ejecutarActualizacionSQL(actualizar, param);
    }
      
  
      
      public double getSumatoriaSubtotales(String idHacienda) {
        return 0.0;
    }
}
