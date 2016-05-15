/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.CostoTierra;
import Dto.Hacienda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Rosemberg
 */
public class CostoTierra_DAO {


    public ArrayList<CostoTierra> consultarCostosTierraHacienda(String idHacienda) {
        System.out.println(idHacienda);
        ArrayList<CostoTierra> costos = new ArrayList<>();     
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, area_siembra, precio_arriendo, subtotal FROM cf_tierra_siembra WHERE hacienda_id = ?";
            Object param [] = {idHacienda};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            while(datos.next()){
                
                CostoTierra c = new CostoTierra();
                String id = datos.getString("id");
                String fecha_reg = datos.getString("fecha_registro");
                String area_siembra = datos.getString("area_siembra");
                String precio_arriendo =datos.getString("precio_arriendo");
                String subtotal = datos.getString("subtotal");

                c.setId(id);
                c.setFecha(fecha_reg);
                c.setAreaLotes(area_siembra);
                c.setPrecioArriendo(precio_arriendo);
                c.setSubtotal(subtotal);
                costos.add(c);
            }
        }catch (SQLException ex) {
            CostoTierra c = new CostoTierra();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
            costos.add(c);            
        }
    return costos;
}

    public CostoTierra consultarCostoTierraHacienda(String idCosto) {
         System.out.println(idCosto+" este es el costo");
        CostoTierra costos = new CostoTierra();     
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT id, fecha_registro, area_siembra, precio_arriendo, subtotal FROM cf_tierra_siembra WHERE id = ?";
            Object param [] = {idCosto};
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
            while(datos.next()){
                
                CostoTierra c = new CostoTierra();
                String id = datos.getString("id");
                System.out.println(id);
                String fecha_reg = datos.getString("fecha_registro");
                
                System.out.println(fecha_reg);
                String area_siembra = datos.getString("area_siembra");
                System.out.println(area_siembra);
                String precio_arriendo =datos.getString("precio_arriendo");
                System.out.println(precio_arriendo);
                String subtotal = datos.getString("subtotal");
                System.out.println(subtotal);
                c.setId(id);
                c.setFecha(fecha_reg);
                c.setAreaLotes(area_siembra);
                c.setPrecioArriendo(precio_arriendo);
                c.setSubtotal(subtotal);
                costos=c;
            }
        }catch (SQLException ex) {
            CostoTierra c = new CostoTierra();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
                      System.out.println("Error");
        }
    return costos;
    }

    public boolean editarCostoTierra(CostoTierra n) {
        BaseDeDatos.conectar();
        int nuevoSubtotal=Integer.parseInt(n.getAreaLotes())*Integer.parseInt(n.getPrecioArriendo());
        String actualizar="UPDATE cf_tierra_siembra SET fecha_registro=?, precio_arriendo=?, subtotal=? WHERE id=?";
        Object param [] ={n.getFecha(),n.getPrecioArriendo(),nuevoSubtotal ,n.getId()};
        return BaseDeDatos.ejecutarActualizacionSQL(actualizar, param);
    }


    public boolean registrarCostoTierra(CostoTierra c, int suma, Hacienda h) {
        BaseDeDatos.conectar();
        int subtotal=suma*Integer.parseInt(c.getPrecioArriendo());
        System.out.println(c.getFecha()+"+"+suma+"+"+c.getPrecioArriendo()+"+"+subtotal+"+"+h.getId());
        String cadena="INSERT INTO cf_tierra_siembra(fecha_registro, area_siembra, precio_arriendo, subtotal, hacienda_id) VALUES( ?, ?, ?, ?, ?)";
        Object param[] = {c.getFecha(),suma,c.getPrecioArriendo(),subtotal,h.getId()};
        return (BaseDeDatos.ejecutarActualizacionSQL(cadena, param));
        
    
}

    public boolean eliminarCostoTierra(String idCosto) {
        System.out.println(idCosto);
         BaseDeDatos.conectar();
        String eliminar = "DELETE FROM cf_tierra_siembra WHERE id = ?";
        Object param [] = {idCosto};
        boolean d=BaseDeDatos.ejecutarActualizacionSQL(eliminar, param);
        System.out.println(d);
        return d;
    
    }
    
  

    public double getSumatoriaSubtotales(String idHacienda) {

        System.out.println("SELECT SUM(subtotal) FROM lote_zona WHERE hacienda_id='"+idHacienda+"'");
        BaseDeDatos.conectar();
        int r=0;
        String consulta="SELECT SUM(subtotal) FROM cf_tierra_siembra WHERE hacienda_id='"+idHacienda+"'";
        
        ArrayList<String>c=BaseDeDatos.getConsultaSQL(consulta);
        String cosa=c.get(0);
        cosa=cosa.replaceAll("&", "");
        System.out.println(cosa);
        if(cosa.equalsIgnoreCase("null"))
            return 0.0d;
        return Integer.parseInt(cosa);
        
    }
}