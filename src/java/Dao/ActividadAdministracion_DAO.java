/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.ActividadAdministracion;
import Dto.CostoAdministracion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BaseDeDatos;

/**
 *
 * @author Rosemberg
 */
public class ActividadAdministracion_DAO {

    public String getNombreActividad(String tipo_actividad_id) {
        System.out.println(tipo_actividad_id+"acti");
        BaseDeDatos.conectar();
        String cadena="SELECT actividad FROM cf_tipo_actividad WHERE id=?";
        Object param []={tipo_actividad_id};
        ResultSet dat=BaseDeDatos.ejecutarSQL(cadena,param);
        try {
            while (dat.next())
                return dat.getString("actividad");
            {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadAdministracion_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    public ArrayList<ActividadAdministracion> getActividades() {
        
        
        ArrayList<ActividadAdministracion> costos = new ArrayList<>();     
        try {
            BaseDeDatos.conectar();
            String consulta = "SELECT * FROM cf_tipo_actividad";
            
            ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
            while(datos.next()){
                System.out.println("Entra");
                ActividadAdministracion c = new ActividadAdministracion();
                
                String id = datos.getString("id");
                String actividad = datos.getString("actividad");
                String fecha_creacion = datos.getString("fecha_creacion");
                String persona = datos.getString("persona_numero_documento");
                
                c.setId(id);
                c.setActividad(actividad);
                c.setFecha_creacion(fecha_creacion);
                c.setPersona_numero_documento(persona);
                                
                costos.add(c); 
            }
        }catch (SQLException ex) {
            ActividadAdministracion c = new ActividadAdministracion();
            String nada = "nada"; //SI NO HAY NIINGUNA NIVELACION AGREGO UNA NIVELACION VACIA EN EL id COLOCO NADA.. EN EL NEGOCIO PREGUNTO, SI SUBTOTAL = "nada", entonces se retorna un msm de que no hay ninguna nivelacion registrada
            c.setId(nada);
            costos.add(c);            
        }
    return costos;
    }

    public String registrarActividad(ActividadAdministracion ac) {
        System.out.println("Registro de actividad");
         BaseDeDatos.conectar();
         String actualizar="INSERT INTO cf_tipo_actividad(actividad, fecha_creacion, "
                 + "persona_numero_documento) VALUES( ?, ?, ?)";
        Object param[] = {ac.getActividad(),ac.getFecha_creacion(),ac.getPersona_numero_documento()};
        boolean v=BaseDeDatos.ejecutarActualizacionSQL(actualizar, param);
        System.out.println(v+"paso o no");
        
        actualizar="SELECT id FROM cf_tipo_actividad WHERE actividad='"+ac.getActividad()+"'";
        ResultSet datos = BaseDeDatos.ejecutarSQL(actualizar);
        try {
            while(datos.next()){
            return datos.getString("id");
            }
        } catch (SQLException ex) {
                       Logger.getLogger(ActividadAdministracion_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
