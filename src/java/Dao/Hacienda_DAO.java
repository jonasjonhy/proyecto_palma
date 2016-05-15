/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Hacienda;
import Dto.Propietario;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.BaseDeDatos;

/**
 *
 * @author brialxsf
 */
public class Hacienda_DAO {

    /**
     * Constructor sin parametros Permite realizar la coneccion a la BD
     */
    public Hacienda_DAO() {
        if (!BaseDeDatos.hayConexion()) //Consulta si hay conexion
        {
            BaseDeDatos.conectar(); // En caso de no estar conectado, se conecta.
        }
    }

    /**
     * Metodo para registrar Hacienda h
     *
     * @param h Object de la clase Hacienda
     * @return True en caso de realizar el registro exitoso
     */
    public boolean registrarHacienda(Hacienda h) {
        try {
            if (!this.estaReistrado(h.getNombre(), h.getMunicipio())) //Consulto en BD si la hacienda ya existe en el municipio
            {
                System.out.println("Por aca entro!!!");
                //codigo para que el Server BD tome la instrucion a ejecutar
                String consulta2 = "INSERT INTO hacienda(nombre, direccion, telefono, cedula_catastral, area_total, descripcion, nucleo_palmero, propietario_persona_numero_documento, municipio_id, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                Object param[] = {h.getNombre(), h.getDireccion(), h.getTelefono(), h.getCedCatastral(), h.getArea(), h.getDescripcion(), h.getNucleo(), h.getPropietario_cedula(), h.getMunicipio(), "1"};
                String consulta = "INSERT INTO hacienda VALUES('" + h.getNombre() + "','"
                        + h.getDireccion() + "','" + h.getTelefono() + "','" + h.getCedCatastral() + "','" + h.getArea() + "','" + h.getDescripcion()
                        + "','" + h.getNucleo() + "','" + h.getPropietario_cedula() + "','" + h.getMunicipio() + "','1')";

                //Ejecuta la actualizacion si todo esta bien registra hacienda
                if (BaseDeDatos.ejecutarActualizacionSQL(consulta2, param)) {
                    System.out.println("Por aca entro 2 ---->!!!");
                    return true;
                }
                return false;
            }

        } catch (Exception e) {
            //captura error si se presenta cuando se ejecuta la actualizacion
            System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para consultar informacion de una Hacienda
     *
     * @param id de la hacienda a consultar
     * @return objeto de la clase Hacienda con la información de la hacienda
     * consultada
     * @throws java.sql.SQLException posible error en la ejecución
     */
    public Hacienda consultarHacienda(String id) throws SQLException {
        Hacienda nueva = new Hacienda();
        String consulta = "SELECT * FROM hacienda WHERE id = ?";
        Object param[] = {id};
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
        while (dat.next()) {
            String idHac = dat.getString("id");
            String nombre = dat.getString("nombre");
            String dir = dat.getString("direccion");
            String tel = dat.getString("telefono");
            String areaT = dat.getString("area_total");
            String mun = this.obtenerNombreMunicipio(dat.getString("municipio_id"));
            
            String cedulaCatastral = dat.getString("cedula_catastral");
            String nucleoPal = dat.getString("nucleo_palmero");
            String descripcion = dat.getString("descripcion");
            
            if(dat.getString("activo").equalsIgnoreCase("1"))
            {
                nueva.setActivo("Activo");
            }
            else
            {
                nueva.setActivo("Desactivo");
            }
            nueva.setId(idHac);
            nueva.setNombre(nombre);
            nueva.setDireccion(dir);
            nueva.setTelefono(tel);
            nueva.setArea(areaT);
            nueva.setNombreMun(mun);
            nueva.setMunicipio(dat.getString("municipio_id"));
            nueva.setCedCatastral(cedulaCatastral);
            nueva.setDescripcion(descripcion);
            nueva.setNucleo(nucleoPal);
        }
        return nueva;
    }

    /**
     * Metodo para obtener el nombre de un Municipio dado su id
     *
     * @param idMun id del municipio
     * @return String con el nombre del municipio
     * @throws SQLException posible error que se presente en ejecución
     */
    private String obtenerNombreMunicipio(String idMun) throws SQLException {
        String mun = "";
        String consulta = "SELECT nombre FROM municipio WHERE id=" + idMun;
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta);
        while (dat.next()) {
            mun = dat.getNString("nombre");
        }

        return mun;
    }

    /**
     * Metodo para obtener el nombre una hacienda
     *
     * @param id
     * @return
     * @throws SQLException
     */
    private String obtenerNombreHacienda(String id) throws SQLException {
        String nombre = "";
        String consulta = "SELECT nombre FROM hacienda WHERE id=" + id;
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta);
        while (dat.next()) {
            nombre = dat.getNString("nombre");
        }
        return nombre;
    }

    /**
     * Metodo para listar Hacienda
     *
     * @param p propietario de las haciendas
     * @return Una cadena String con los id y nombre de las haciendas
     * @throws SQLException
     */
    public String listarHacienda(Propietario p) throws SQLException {
        String listaHacienda = "";

        String consulta = "SELECT nombre,id,municipio_id FROM hacienda WHERE propietario_persona_numero_documento = ?";
        Object param[] = {p.getNumDocumento()};
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
        //if (dat.next()) {
        //System.out.println("DATOS DE LA BD 8070");
        while (dat.next()) {
            System.out.println("hola mundooooo --< ");
            String id = dat.getString("id");
            String nombre = dat.getString("nombre");
            String mun = dat.getString("municipio_id");
            System.out.println("DATOS DE LA BD -----> " + id + " " + nombre + " " + mun);
            String nombreM = this.obtenerNombreMunicipio(mun);
            String nombreDepar = new Departamento_DAO().consultarNombreDepartamentoPorMunicipio(mun);
            listaHacienda += id + "-" + nombre + "-" + nombreM + "-" + nombreDepar + "\n";
        }
        //}

        return listaHacienda;
    }

    /**
     * Metodo que me valida si la Hacienda h se encuentra registrada
     *
     * @param nombre de la Hacienda a registrar
     * @param municipioId donde se encuentra ubicada la Hacienda
     * @return true si ya esta en la BD
     * @throws SQLException
     */
    private boolean estaReistrado(String nombre, String municipioId) throws SQLException {
        //Instruccion para la BD y consultar
        String consulta2 = "SELECT nombre FROM hacienda WHERE municipio_id = ? AND nombre = ?";
        Object param[] = {municipioId, nombre};

        String consulta = "SELECT nombre FROM hacienda WHERE municipio_id=" + municipioId;
        //Obtiene todos los valores generados por la consulta
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta2, param);

        if (!dat.next()) {
            return false;
        }
        return true;

    }

    /**
     * Metodo para desactivar una Hacienda
     *
     * @param id identificación de la Hacienda
     * @return true si permitio descativarla
     */
    public boolean desactivarHacienda(String id) {
        String consulta = "UPDATE hacienda SET activo=" + 1 + " WHERE id=" + id;//donde 0 es activo y 1 es inactivo
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta));
    }
    
    public boolean hayHaciendas()
    {
        String consulta = "SELECT COUNT(id) FROM hacienda";
        ResultSet dat= BaseDeDatos.ejecutarSQL(consulta);
        return false;
    }

    /**
     * Metodo para actualizar informacion de una Hacienda
     *
     * @param h Objeto de la clase Hacienda con la informacion nueva
     * @return true si la actualización se efectuó satisfactoriamente
     */
    public boolean actualizarHacienda(Hacienda h) {
        
        String consulta2 = "UPDATE hacienda SET nombre = ?, telefono = ?, area_total = ?, nucleo_palmero = ?, descripcion = ? WHERE id = ?";
        Object param[] = {h.getNombre(), h.getTelefono(), h.getArea(), h.getNucleo(), h.getDescripcion(), h.getId()};
        String consulta = "UPDATE hacienda SET nombre=" + h.getNombre() + " telefono=" + h.getTelefono()
                + " area_total=" + h.getArea() + " propietario_persona_numero_cedula=" + h.getPropietario_cedula() + " WHERE id=" + h.getId();
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta2, param));
    }

    /**
     * Metodo que consulta el nombre de una hacienda
     *
     * @param id de la hacienda
     * @return nombre de la hacienda
     * @throws java.sql.SQLException posible error en BD
     */
    public String consultarNombreHacienda(String id) throws SQLException {
        String consulta = "SELECT nombre FROM hacienda WHERE id=" + id;
        //Obtiene todos los valores generados por la consulta
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta);
        String nombre = "";
        //Obtiene el dato consultado
        while (dat.next()) {
            nombre = dat.getNString("nombre");
        }

        return nombre;
    }

   
    /**
     * Metodo para actualizar informacion Hacienda por parte del 
     * Administador del sistema
     * @param h infomacion actualizada de la hacienda
     * @return true si actualizo
     */
    public boolean actualizarEdicionHaciendaAdmin(Hacienda h)
    {
        //String activo=this.getEstadoHacienda(h.getActivo());
        
        String consulta = "UPDATE hacienda SET nombre = ?, direccion = ?, telefono= ?, "
                        + "cedula_catastral = ?, area_total = ?, descripcion = ?, nucleo_palmero = ?,"
                        + "municipio_id = ?,  activo = ?  WHERE id = ?";
        
        Object param[] = {h.getNombre(),h.getDireccion(),h.getTelefono(),h.getCedCatastral(),h.getArea(),
                          h.getDescripcion(),h.getNucleo(),h.getMunicipio(),h.getActivo(),h.getId()};
        
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }
    
    /**
     * Metodo para cambiar el estado de la hacienda al 
     * formato de la BD
     * @param estado de la hacienda que se actualiza
     * @return formato de estado para la BD
     */
    private String getEstadoHacienda(String estado)
    {
        if(estado.equalsIgnoreCase("activo"))
        {
            return "1";
        }
        else
        {
            return "0";
        }
    }
    
    public String getAreaTotal(int idHacienda) throws SQLException {
     String consulta = "SELECT area_total FROM hacienda WHERE id=" + idHacienda;
        //Obtiene todos los valores generados por la consulta
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta);
        String area = "";
        //Obtiene el dato consultado
        while (dat.next()) {
            area = dat.getNString("area_total");
        }

        return area;
    }
}
