/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.CFHerramientas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author brialxsfxubun
 */
public class CFHerramientas_DAO {
    
    public ArrayList<CFHerramientas> consultarCFHerramientas(int idHacienda){
        
        ArrayList<CFHerramientas> cfHerra = new ArrayList<>();        
       
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, cantidad, precio_unidad, subtotal_compra, vida_util, precio_agnoutil, area_servicio, costo_hectarea, area_lotes, subtotal_herramientas, herramienta_id FROM cf_herramientas WHERE hacienda_id = ?";
            Object param [] = {idHacienda};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);   //BaseDeDatos.desconectar();
            while(datos.next()){                
                CFHerramientas cfh = new CFHerramientas();
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
                String subtotalHerra=datos.getString("subtotal_herramientas");
                int idHerramienta=Integer.parseInt(datos.getString("herramienta_id"));
                cfh.setId(Integer.parseInt(id));
                cfh.setFechaRegistro(fecha_reg);
                cfh.setCantidad(Integer.parseInt(cantidad));
                cfh.setIdHerramienta(idHerramienta);
                cfh.setPrecioUnd(Double.parseDouble(precioUnd));
                cfh.setSubtotalCompra(Double.parseDouble(subtotal_compra));
                cfh.setVidaUtil(Integer.parseInt(vidaUtil));    
                cfh.setPrecioAñoUtil(Double.parseDouble(precio_agnoutil));
                cfh.setAreaServicio(Float.parseFloat(areaServicio));
                cfh.setCostoHectarea(Double.parseDouble(costoHectarea));
                cfh.setAreal_lotes(Float.parseFloat(areaLotes));
                cfh.setSubtotalHerramientas(Double.parseDouble(subtotalHerra));
          
                cfHerra.add(cfh);
            }
            
        } catch (SQLException ex) {
            CFHerramientas cfh = new CFHerramientas();
            int id = -1; 
            cfh.setId(id);
            cfHerra.add(cfh);            
        }
        return cfHerra;
    }
    
    public String consultarHerramienta (int idHerramienta){
    String id="";
    try{
    BaseDeDatos.conectar();
    String consulta="SELECT herramienta FROM herramienta WHERE id = ?";
    Object Param [] = {idHerramienta};
    ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, Param);
        while(datos.next()){
        id=datos.getString("herramienta");
        }
    }catch(SQLException ex){}
    return id;
    }
    
    public double getSumatoriaSubtotales(String idHacienda) {
        return 0.0;
    }
    
}
