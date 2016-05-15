/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class Municipio_DAO {
    

    public Municipio_DAO() {
    }
    
    
    /**
     * metodo que consulta todos los departamentos de un municipio
     * @param departamento
     * @return el nombre y codigo de los municipios que coincidan con el codigo de departamento de busqueda
     */
    public String cargarMunicipiosPorDepartamento(String departamento) throws SQLException{
        System.out.println("CODIGO DEPART --------> "+departamento);
        BaseDeDatos.conectar();
        String consulta = "SELECT id, nombre FROM municipio WHERE departamento_id = ?";
        Object param[] = {departamento};
        ResultSet dat = BaseDeDatos.ejecutarSQL(consulta, param);
        String municipios = "";
        while(dat.next()){
            String idMunicipio = dat.getString("id");
            String nomMunicipio = dat.getString("nombre");
            municipios+= idMunicipio +"-"+ nomMunicipio + "/";
        }
        
        
        return municipios;
    }
    
    /**
     * metodo que consulta el nombre de un departamento segun su id
     * @param idMunicipio
     * @return el nombre del departamento de acuerdo al id que ha sido pasado como parámetro
     */
    public String consultarMunicipioPorNombre(String idMunicipio) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT nombre FROM municipio WHERE id = ?";
        Object param[] = {idMunicipio};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String nombreMunicipio = "";
        while(datos.next()){
            nombreMunicipio = datos.getString("nombre");
        }
        return nombreMunicipio;        
    }
    
}
