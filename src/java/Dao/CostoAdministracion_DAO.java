/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.CostoAdministracion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Rosemberg
 */

public class CostoAdministracion_DAO {

    public ArrayList<CostoAdministracion> consultarCostosAdministracionHacienda(String idHacienda) {
       
        System.out.println(idHacienda+"hacienda");
        
        ArrayList<CostoAdministracion> costos = new ArrayList<>();     
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT * FROM cf_administracion WHERE hacienda_id = ?";
            Object param [] = {idHacienda};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            while(datos.next()){
                System.out.println("Entra");
                CostoAdministracion c = new CostoAdministracion();
                String id = datos.getString("id");
                String fecha_registro = datos.getString("fecha_registro");
                String dias_laborados = datos.getString("dias_laborados");
                String costo_jornal =datos.getString("costo_jornal");
                String subtotal = datos.getString("subtotal");
                String actividad = datos.getString("tipo_actividad_id");

                c.setId(id);
                c.setFecha_registro(fecha_registro);
                c.setDias_laborados(dias_laborados);
                c.setCosto_jornal(costo_jornal);
                c.setSubtotal(subtotal);
                c.setTipo_actividad_id(actividad);
                costos.add(c); 
            }
        }catch (SQLException ex) {
            CostoAdministracion c = new CostoAdministracion();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
            costos.add(c);            
        }
    return costos;
    }

    public boolean registrarCostoAdministracion(CostoAdministracion c, int subtotalAdministracion, String hacienda) {
        System.out.println("Registro Costo Admon");
        BaseDeDatos.conectar();
         String actualizar="INSERT INTO cf_administracion (fecha_registro, dias_laborados, "
                 + "costo_jornal, subtotal, hacienda_id, tipo_actividad_id) VALUES( ?, ?, ?, ?, ? ,?)";
         System.out.println(c.getFecha_registro()+","+c.getDias_laborados()+","+c.getCosto_jornal()+","+subtotalAdministracion+","+ hacienda +","+c.getTipo_actividad_id());
         Object param[] = {c.getFecha_registro(),c.getDias_laborados(),c.getCosto_jornal(),subtotalAdministracion, hacienda ,c.getTipo_actividad_id()};
         
          return (BaseDeDatos.ejecutarActualizacionSQL(actualizar, param));
    }

    public boolean eliminarCostoAdministracion(String idCosto) {
        
        System.out.println(idCosto);
         BaseDeDatos.conectar();
        String eliminar = "DELETE FROM cf_administracion WHERE id = ?";
        Object param [] = {idCosto};
        boolean d=BaseDeDatos.ejecutarActualizacionSQL(eliminar, param);
        System.out.println(d);
        return d;
        
    }

    public double getSumatoriaSubtotales(String idHacienda) {
    
        System.out.println("SELECT SUM(subtotal) FROM cf_administracion WHERE hacienda_id='"+idHacienda+"'");
        BaseDeDatos.conectar();
        int r=0;
        String consulta="SELECT SUM(subtotal) FROM cf_administracion WHERE hacienda_id='"+idHacienda+"'";
        
        ArrayList<String>c=BaseDeDatos.getConsultaSQL(consulta);
        String cosa=c.get(0);
        cosa=cosa.replaceAll("&", "");
        System.out.println(cosa);
        if(cosa.equalsIgnoreCase("null"))
            return 0.0d;
        return Integer.parseInt(cosa);
    
    }

    public CostoAdministracion consultarCostoAdministracionHacienda(String idCosto) {
        
             System.out.println(idCosto+" este es el costo");
        CostoAdministracion costos = new CostoAdministracion();     
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, dias_laborados, costo_jornal, subtotal, tipo_actividad_id FROM cf_administracion WHERE id = ?";
            Object param [] = {idCosto};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            while(datos.next()){
                
                CostoAdministracion c = new CostoAdministracion();
                String id = datos.getString("id");
                System.out.println(id);
                String fecha_reg = datos.getString("fecha_registro");
                
                System.out.println(fecha_reg);
                String dias_laborados = datos.getString("dias_laborados");
                System.out.println(dias_laborados);
                String costo_jornal =datos.getString("costo_jornal");
                System.out.println(costo_jornal);
                String subtotal = datos.getString("subtotal");
                System.out.println(subtotal);
                String tipo_actividad_id = datos.getString("tipo_actividad_id");
                System.out.println(tipo_actividad_id);
                
                c.setId(id);
                c.setFecha_registro(fecha_reg);
                c.setDias_laborados(dias_laborados);
                c.setCosto_jornal(costo_jornal);
                c.setSubtotal(subtotal);
                c.setTipo_actividad_id(tipo_actividad_id);
                costos=c;
            }
        }catch (SQLException ex) {
            CostoAdministracion c = new CostoAdministracion();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
                      System.out.println("Error");
        }
    return costos;
        
    }

    public boolean editarCostoAdministracion(CostoAdministracion c) {
       
        BaseDeDatos.conectar();
        int nuevoSubtotal=Integer.parseInt(c.getCosto_jornal())*Integer.parseInt(c.getDias_laborados());
        String actualizar="UPDATE cf_administracion SET fecha_registro=?, dias_laborados=?, "
                + "costo_jornal=?, tipo_actividad_id=?, subtotal=? WHERE id=?";
        Object param [] ={c.getFecha_registro(),c.getDias_laborados(), c.getCosto_jornal(),c.getTipo_actividad_id(), nuevoSubtotal ,c.getId()};
        return BaseDeDatos.ejecutarActualizacionSQL(actualizar, param);
    
    }


    
}
