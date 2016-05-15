/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.Propietario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufps.util.JCrypt;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class Propietario_DAO {

    public Propietario_DAO() {
    }
    
    public String validarSesion(String id, String clave){
        System.out.println("USUARIO ...--> " +id+"  "+clave);
        try {
        BaseDeDatos.conectar();
        String consulta="SELECT numero_documento, password, tipo_persona_id FROM persona WHERE numero_documento = ? AND activo = ?";
        Object []param = {id, 1};
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta, param);
       
            while(dat.next()){                
                String pass=dat.getString("password");
                String salto=pass.substring(0, 2);
                String claveSesion=JCrypt.crypt(salto, clave);
                if(claveSesion.equals(pass)){
                    int tipoPersona = dat.getInt("tipo_persona_id");
                    if(tipoPersona == 0){
                        return "../jsp/administrador.jsp";
                    }else{
                        if(tipoPersona == 1){
                            return "../jsp/informacionPalmicultor.jsp";
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            return "false";
        }
        return "false";
    }
    
    public boolean validaSesion(String idPropietario, String clave) throws SQLException{
        
        String consulta = "SELECT numero_documento, password FROM persona WHERE numero_documento = ?";
        Object param[] = {idPropietario};
        System.out.println("HOLIS --> " +idPropietario +" - "+clave);
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        while(datos.next()){
            String password = datos.getString("password");
            String salto=password.substring(0, 2);
            String claveSesion=JCrypt.crypt(salto, clave);
            if(password.equals(claveSesion)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean registrarPropietario(Propietario p) throws SQLException{
        System.out.println(p.getNombre()+"  "+p.getApellidos()+"  "+p.getNumDocumento()+"  "+p.getDireccionResidencia()+"  "+p.getCedulaPalmera());
        if(!new Persona_DAO().estaRegistradaPersona(p.getNumDocumento())){
            new Persona_DAO().registrarPersona(p);
        }
        if(!this.estaRegistradoPropietario(p.getNumDocumento())){
            BaseDeDatos.conectar();
            String sqlRegistro = "INSERT INTO propietario(numero_doc, cedula_palmera, zona_palmera) VALUES (?, ?, ?)";
            String zonaPalmera = this.zonaPalmeraPorDepartamento(new Departamento_DAO().consultarIdDepartamentoPorMunicipio(p.getMunicipio()));
            Object param[] = {p.getNumDocumento(), p.getCedulaPalmera(), zonaPalmera};
            return BaseDeDatos.ejecutarActualizacionSQL(sqlRegistro, param);
        }
        return false;       
    }
    
    public boolean estaRegistradoPropietario(String documento_id) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT numero_doc FROM propietario WHERE numero_doc = ?";
        Object param[] = {documento_id};
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta, param);
        if(!dat.next()){
            return false;
        }
        return true;
    }
    
    
    /**
     * método que consulta en la base de datos la informacion referente de un propietario 
     * @param idPropietario
     * @return la informacion que registra el propietario en la base de datos
     * @throws SQLException 
     */
    public String consultarInformacionPropietario(String idPropietario) throws SQLException{
        //tipoDocumento, numero documento, nombre, apellido, genero, direccion, departamento, municipio, telefono, celular, email
        //zona palmera, cedula palmera
        String consulta = "SELECT p.tipo_documento_id, p.numero_documento, p.nombre, p.apellidos, p.genero, p.direccion_residencia, p.municipio_id, p.telefono, p.celular, p.email, pr.zona_palmera, pr.cedula_palmera, p.activo  FROM persona p, propietario pr, propietario WHERE pr.numero_doc = ? AND p.numero_documento = pr.numero_doc";
        Object param[] = {idPropietario};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String tipoDocumento = "", numeroDocumento = "", nombre = "", apellido = "", genero = "", direccion = "", departamento = "", municipio = "", telefono = "", celular = "", email = "", zonaPalmera = "", cedulaPalmera = "", activo = "";
        
        while(datos.next()){
            tipoDocumento = datos.getString(1);
            numeroDocumento = datos.getString(2);
            nombre = datos.getString(3);
            apellido = datos.getString(4);
            genero = datos.getString(5);
            direccion = datos.getString(6);
            municipio = datos.getString(7);
            telefono = datos.getString(8);
            celular = datos.getString(9);
            email = datos.getString(10);
            zonaPalmera = datos.getString(11);
            cedulaPalmera = datos.getString(12);
            activo = datos.getString(13);
            System.out.println("DATOS ------> "+nombre+",  "+apellido+", "+direccion);
        }
        tipoDocumento = new tipoDocumento_DAO().consultarTipoDocumento(tipoDocumento);
        departamento = new Departamento_DAO().consultarNombreDepartamentoPorMunicipio(municipio);
        municipio = new Municipio_DAO().consultarMunicipioPorNombre(municipio);
        String sexo = "";
        if(genero.equals("1")){
            sexo = "Masculino";
        }else{
            sexo = "Femenino";
        }
        
        String estado="";
        if(activo.equals("1")){
            estado = "activo";
        }else{
            estado = "inactivo";
        }
        
        String infoPropietario = tipoDocumento +"¬"+ numeroDocumento +"¬"+ nombre +"¬"+ apellido +"¬"+ sexo +"¬"+ direccion +"¬"+ departamento +"¬"+ municipio +"¬"+ telefono +"¬"+ celular +"¬"+ email +"¬"+ zonaPalmera +"¬"+ cedulaPalmera +"¬"+ estado;
        
        return infoPropietario;     
    }
    
    
    /**
     * método que desactiva la cuenta del propietario (el mismo se desactiva), 1 = Activo 0 = inactivo
     * @param idPropietario
     * @return verdadero en caso de realizar el cambio con éxito (se cambia el estado activo ---> inactivo)
     */
    public boolean desactivarCuentaPropietario(String idPropietario, String clave) throws SQLException{
        if(this.validaSesion(idPropietario, clave)){
            BaseDeDatos.conectar();
            String actualizacion = "UPDATE persona SET activo = ? WHERE numero_documento = ?";
            Object param[] = {0, idPropietario};
        
            return BaseDeDatos.ejecutarActualizacionSQL(actualizacion, param);
        }
        return false;        
    }
    
    /**
     * Metodo para Eliminar la informacin relacionada con un propietario para su posterior actualización
     * @param idPropietario id del propietario
     * @return true si se elimino
     */
    public boolean eliminarPropietario(String idPropietario)
    {
        String consulta = "DELETE FROM propietario WHERE id = ?";
        Object param[] = {idPropietario};
        return BaseDeDatos.ejecutarActualizacionSQL(consulta, param);
    }
    
    public String zonaPalmeraPorDepartamento(String departamento){
        int depart = Integer.parseInt(departamento);
        if(depart > 0 && depart <= 8){
            return "sur occidental";
        }
        if(depart >8 && depart <= 16){
            return "oriental";
        }
        if(depart >16 && depart <= 24){
            return "central";
        }
        if(depart >24 && depart <= 32){
            return "norte";
        }
        return "error";
    }
    
    public String consultarNombrePropietario(String idPropietario) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT nombre, apellidos FROM persona WHERE numero_documento = ?";
        Object param[] = {idPropietario};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String nombreProp = "";
        while(datos.next()){
            nombreProp = datos.getString("nombre") +" "+ datos.getString("apellidos");
        }
        return nombreProp;
    }
}
