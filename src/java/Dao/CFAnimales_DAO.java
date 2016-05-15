/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.CFAnimales;
import Dto.CFMaquinaria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author brialxsfxubun
 */
public class CFAnimales_DAO {
    
    public ArrayList<CFAnimales> consultarCFAnimales(int idHacienda){
        
        ArrayList<CFAnimales> cfani = new ArrayList<>();        
       
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, cantidad, precio_unidad, subtotal_compra, vida_util, precio_agnoutil, area_servicio, costo_hectarea, area_lotes, subtotal_animales, animal_id FROM cf_animales WHERE hacienda_id = ?";
            Object param [] = {idHacienda};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);   //BaseDeDatos.desconectar();
            while(datos.next()){                
                CFAnimales cfa = new CFAnimales();
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
                String subtotalAni=datos.getString("subtotal_Animales");
                int idAnimal=Integer.parseInt(datos.getString("animal_id"));
                cfa.setId(Integer.parseInt(id));
                cfa.setFechaRegistro(fecha_reg);
                cfa.setCantidad(Integer.parseInt(cantidad));
                cfa.setIdAnimal(idAnimal);
                cfa.setPrecioUnd(Double.parseDouble(precioUnd));
                cfa.setSubtotalCompra(Double.parseDouble(subtotal_compra));
                cfa.setVidaUtil(Integer.parseInt(vidaUtil));    
                cfa.setPrecioAñoUtil(Double.parseDouble(precio_agnoutil));
                cfa.setAreaServicio(Float.parseFloat(areaServicio));
                cfa.setCostoHectarea(Double.parseDouble(costoHectarea));
                cfa.setAreal_lotes(Float.parseFloat(areaLotes));
                cfa.setSubtotalAnimales(Double.parseDouble(subtotalAni));
          
                cfani.add(cfa);
            }
            
        } catch (SQLException ex) {
            CFAnimales cfa = new CFAnimales();
            int id = -1; 
            cfa.setId(id);
            cfani.add(cfa);            
        }
        return cfani;
    }
    
    public String consultarAnimal (int idAnimal){
    String id="";
    try{
    BaseDeDatos.conectar();
    String consulta="SELECT animal FROM animal WHERE id = ?";
    Object Param [] = {idAnimal};
    ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, Param);
        while(datos.next()){
        id=datos.getString("animal");
        }
    }catch(SQLException ex){}
    return id;
    }
    
    public double getSumatoriaSubtotales(String idHacienda) {
        return 0.0;
    }
}
