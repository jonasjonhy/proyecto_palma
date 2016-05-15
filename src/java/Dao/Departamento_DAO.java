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
public class Departamento_DAO {

    public Departamento_DAO() {
        if(!BaseDeDatos.hayConexion())
            BaseDeDatos.conectar();
    }
    
    /**
     * método que consulta todos las filas de la tabla departamentos de la BD 
     * @return nombre e id todos los departamento
     * @throws SQLException 
     */
    public String cargarDepartamentos() throws SQLException{
        
        String consulta="SELECT id, nombre FROM departamento";
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta);
        String departamentos="";
        while(dat.next()){
            String idDepartamento = dat.getString("id");
            String nombreDepartamento = dat.getString("nombre");
            departamentos+= idDepartamento + "-" + nombreDepartamento + "/";
        }
        
        if(departamentos!=""){
            departamentos=departamentos.substring(0, departamentos.length()-1);
        }
        return departamentos;
    }
    
    
    public String consultarIdDepartamentoPorMunicipio(String idMunicipio) throws SQLException{
        
        String consulta = "SELECT d.id FROM municipio m, departamento d WHERE m.id = ? AND m.departamento_id = d.id";
        Object param[] = {idMunicipio};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String nombreDepart = "";
        
        while(datos.next()){
            nombreDepart = datos.getString(1);
        } 
        return nombreDepart;
    }
    
    
    /**
     * método que consulta el departamento segun un municipio determinado
     * @param idMunicipio
     * @return nombre del departamento de acuerdo al id del municipio pasado como parámetro
     */
    public String consultarNombreDepartamentoPorMunicipio(String idMunicipio) throws SQLException{
        
        String consulta = "SELECT d.nombre FROM municipio m, departamento d WHERE m.id = ? AND m.departamento_id = d.id";
        Object param[] = {idMunicipio};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String nombreDepart = "";
        
        while(datos.next()){
            nombreDepart = datos.getString(1);
        } 
        return nombreDepart;
    }
    
    
}
