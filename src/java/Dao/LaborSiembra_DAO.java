/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BaseDeDatos;

/**
 * LaborSiebra se realiza dado un Lote der una Hacienda
 *
 * @author mauricio uribe
 */
public class LaborSiembra_DAO {

    public LaborSiembra_DAO() {
        if (!BaseDeDatos.hayConexion()) {
            BaseDeDatos.conectar();
        }
    }

    /**
     * Metodo para registrar Labor Siembra de un Lote
     *
     *
     * @param ls Object Labor Siembra que contiene la Información a registrar
     * @param idPropi id del propietario o palmicultor
     *
     * @return true si registro exitosamente
     */
    public boolean registrarLaborSiembra(LaborSiembra ls, String idPropi) {

        if (ls.getIdLabor().equalsIgnoreCase("0")) {
            this.registrarLabor(ls, idPropi);
        }
        String consulta = "INSERT INTO labor_siembra (area_labor, fecha_registro, fecha_labor, cantidad_laboradas, precio_labor, labor_id, unidad_id, lote_zona_id) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        Object param[] = {ls.getAreaLoteLabor(), ls.getFechaRegistro(), ls.getFechaLabor(), ls.getCantidadLabor(), ls.getPrecioLabor(), ls.getIdLabor(), ls.getIdUnidad(), ls.getIdLote()};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    private void registrarLabor(LaborSiembra ls, String idPropi) {
        String consulta = "INSERT INTO labor (labor,fecha_creado,persona_numero_documento) VALUES (?,?,?)";
        Object param[] = {ls.getLabor(), ls.getFechaLabor(), idPropi};
        if (BaseDeDatos.ejecutarActualizacionSQL(consulta, param)) {
            try {
                String id = getIdLabor(ls.getLabor());
                ls.setIdLabor(id);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    private String getIdLabor(String labor) throws SQLException {
        String cosulta = "SELECT id FROM labor WHERE labor = ?";
        Object param[] = {labor};
        ResultSet dat = BaseDeDatos.ejecutarSQL(cosulta, param);
        while (dat.next()) {
            return (dat.getString("id"));
        }

        return "";
    }

    /**
     * Metodo para actualizar Labor Siembra de un Lote
     *
     * @param ls informacion a actualizar
     * @return true si actualiza exitosamente
     */
    public boolean actualizarLaborSiembra(LaborSiembra ls) {
        String consulta = "UPDATE labor_siembra SET area_labor = ?, fecha_registro = ?, fecha_labor = ?, cantidad_laboradas = ?, precio_labor = ?, labor_id = ?, unidad_id = ?, lote_zona_id= ? WHERE id= ?";
        Object param[] = {ls.getAreaLoteLabor(), ls.getFechaRegistro(), ls.getFechaLabor(), ls.getCantidadLabor(), ls.getPrecioLabor(), ls.getIdLabor(), ls.getIdUnidad(), ls.getIdLote(), ls.getId()};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    /**
     * Metodo para eliminar Labor Siembra de un Lote
     *
     * @param id id de la labor a eliminar
     * @return true si se elimino exitosamente
     */
    public boolean eliminarLaborSiembra(String id) {
        String consulta = "DELETE FROM labor_siembra WHERE id = ?";
        Object param[] = {id};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }

    /**
     * Metodo para obtener el nombre de una labor dado su id
     *
     * @param id identificador de la labor
     * @return Strng con el dato que se necesita
     * @throws java.sql.SQLException
     */
    public String getNombreLaborSiembra(String id) throws SQLException {
        String nombre = "";
        String consulta = "SELECT labor FROM labor WHERE id = ?";
        Object param[] = {id};
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
        while (dat.next()) {
            nombre = dat.getString("labor");
        }

        return nombre;
    }
    
    public ArrayList<LaborSiembra> getLabores()
    {
        try {
            ArrayList<LaborSiembra> ls = new ArrayList<>();
            String consulta = "SELECT labor, id FROM labor";
            
            ResultSet dat = BaseDeDatos.ejecutarSQL(consulta);
            
            while (dat.next())
            {
                String id = dat.getString("id");
                String labor = dat.getString("labor");
                
                LaborSiembra l = new LaborSiembra();
                
                l.setIdLabor(id);
                l.setLabor(labor);
                
                ls.add(l);
            }
            
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Metodo para obtener laborSiembra de un Lote
     * @param id del lote
     * @return ArrayList conObject Labor Siembra
     * @throws SQLException 
     */
    public ArrayList<LaborSiembra> getLaboresSiembra(String id) throws SQLException {
        ArrayList<LaborSiembra> ls = new ArrayList<>();
        String consulta = "SELECT fecha_labor,area_labor,labor_id,cantidad_laboradas, "
                + "unidad_id,precio_labor,subtotal FROM labor_siembra WHERE lote_zona_id= ?";
        Object param[] = {id};

        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);

        while (dat.next()) {

            String fecha = dat.getString("fecha_labor");
            String area = dat.getString("area_labor");
            String id_labor = dat.getString("labor_id");
            String labor = this.getNombreLaborSiembra(id_labor);
            String cantidad = dat.getString("cantidad_laboradas");
            String id_uni = dat.getString("unidad_id");
            Unidad u = new Unidad_DAO().consultarUnidadPorID(id_uni);
            String precio = dat.getString("precio_labor");
            String subtotal = dat.getString("subtotal");

            LaborSiembra l = new LaborSiembra();

            l.setFechaLabor(fecha);
            l.setAreaLoteLabor(area);
            l.setLabor(labor);
            l.setCantidadLabor(cantidad);
            l.setUnidad(u.getUnidad());
            l.setPrecioLabor(precio);
            l.setSubtotal(subtotal);

            ls.add(l);
        }
        return ls;
    }

    /**
     * Metodo para obtener la informacion de una labor siembra dado su id
     *
     * @param id id de la labor siembra
     * @return object con la informacion almacenada
     * @throws SQLException
     */
    public LaborSiembra getLaborSiembra(String id) throws SQLException {

        LaborSiembra l = new LaborSiembra();

        String consulta = "SELECT fecha_labor,area_labor,labor_id,cantidad_laboradas, "
                + "unidad_id,precio_labor,subtotal FROM labor_siembra WHERE id= ?";
        Object param[] = {id};

        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);

        while (dat.next()) {

            String fecha = dat.getString("fecha_labor");
            String area = dat.getString("area_labor");
            String id_labor = dat.getString("labor_id");
            String labor = this.getNombreLaborSiembra(id_labor);
            String cantidad = dat.getString("cantidad_laboradas");
            String id_uni = dat.getString("unidad_id");
            Unidad u = new Unidad_DAO().consultarUnidadPorID(id_uni);
            String precio = dat.getString("precio_labor");
            String subtotal = dat.getString("subtotal");

            l.setFechaLabor(fecha);
            l.setAreaLoteLabor(area);
            l.setLabor(labor);
            l.setCantidadLabor(cantidad);
            l.setUnidad(u.getUnidad());
            l.setPrecioLabor(precio);
            l.setSubtotal(subtotal);
        }
        return l;
    }

    /**
     * Metodo para calcular el total de la suma de los subtotales de labores
     * siembra de un Lote especifico
     *
     * @param id id del lote
     * @return resultado de la suma
     */
    public double getTotalLaborSiembra(String id) {
        double total = 0;
        try {

            String consulta = "SELECT subtotal FROM labor_siembra WHERE lote_zona_id= ?";
            Object param[] = {id};
            ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);

            if (dat != null) {
                while (dat.next()) {
                    total += Double.parseDouble(dat.getString("subtotal"));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener el total labor siembra");
            return total;
        }
        return (total);
    }

    public static void main(String arg[]) {
        System.out.println(new LaborSiembra_DAO().getTotalLaborSiembra("1"));
    }

}
