/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Persona;
import Dto.Propietario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class Persona_DAO {

    public Persona_DAO() {
    }

    public boolean registrarPersona(Persona p) throws SQLException {

        if (!this.estaRegistradaPersona(p.getNumDocumento())) {
            BaseDeDatos.conectar();
            String sqlRegistro = "INSERT INTO persona(numero_documento, nombre, apellidos, genero, direccion_residencia, telefono, celular, email, password, nivel_acceso, tipo_documento_id, tipo_persona_id, municipio_id, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object param[] = {p.getNumDocumento(), p.getNombre(), p.getApellidos(), p.getGenero(), p.getDireccionResidencia(), p.getTelefono(), p.getCelular(), p.getEmail(), p.getPassword(), p.getNivelAcceso(), p.getTipoDocumento(), p.getTipoPersona(), p.getMunicipio(), p.getEstado()};
            return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
        }
        return false;
    }

    public boolean estaRegistradaPersona(String documento) throws SQLException {
        BaseDeDatos.conectar();
        String consulta = "SELECT numero_documento FROM persona WHERE numero_documento = ?";
        Object param[] = {documento};
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
        if (!dat.next()) {
            return false;
        }
        return true;
    }

    public boolean actualizarInformacionPersona(Persona p) {
        BaseDeDatos.conectar();
        String actualizacion = "UPDATE persona SET genero = ?, direccion_residencia = ?, municipio_id = ?, telefono = ?, celular = ?, email = ? WHERE numero_documento = ?";
        Object param[] = {p.getGenero(), p.getDireccionResidencia(), p.getMunicipio(), p.getTelefono(), p.getCelular(), p.getEmail(), p.getNumDocumento()};
        return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
    }

    /**
     * método que actualiza en la base de datos la informacion referente de un
     * propietario
     *
     * @param p informacion actualizada del propietario
     * @return true si actializa propietario en la base de datos
     */
    public boolean actualizarInformacionPropietario(Propietario p, String idPropietario) {
        //tipoDocumento, numero documento, nombre, apellido, genero, direccion, departamento, municipio, telefono, celular, email
        //zona palmera, cedula palmera
        if (new Propietario_DAO().eliminarPropietario(idPropietario)) {
            if (new Persona_DAO().actualizarInformacionPropietario(p, idPropietario)) {
                String sqlRegistro = "INSERT INTO propietario(numero_doc, cedula_palmera, zona_palmera) VALUES (?, ?, ?)";
                String zonaPalmera="";
                try {
                    zonaPalmera = (new Departamento_DAO().consultarIdDepartamentoPorMunicipio(p.getMunicipio()));
                } catch (SQLException ex) {
                    Logger.getLogger(Persona_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                Object param[] = {p.getNumDocumento(), p.getCedulaPalmera(), zonaPalmera};
                return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
            }
        }
        return false;
//        return (BaseDeDatos.ejecutarActualizacionSQL(consulta));

    }

    /**
     * Meetodo para dar formato a la informacion genero de la BD
     *
     * @param dato genero en BD
     * @return formato infomacion general
     */
    private String getGenero(String dato) {
        if (dato.equalsIgnoreCase("1")) {
            return ("Masculino");
        } else {
            return ("Femenino");
        }
    }

}
