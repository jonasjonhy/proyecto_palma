/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author mauricio uribe
 */
public class Administrador_DAO {

    public Administrador_DAO() {
        if (!BaseDeDatos.hayConexion()) {
            BaseDeDatos.conectar();
        }
    }

    /**
     * Metodo para realizar consultas de propietario por Criterios
     *
     * @param criterio criterio de busqueda
     * @param palabra valor a consultar
     * @param dpto valor a consultar
     * @param mun valor a consultar
     * @return Arreglo de datos con la informacion solicitada
     * @throws SQLException
     */
    public ArrayList<String> consultarPropietariosPorCriterio(String consulta){
        try{
            //String consulta = this.preguntaDeConsulta(criterio, palabra,dpto,mun);
        return (BaseDeDatos.getConsultaSQL(consulta));
        }catch(Exception e){
            ArrayList<String> l = new ArrayList<>();
            String nada = "nada";
            l.add(nada);
            return l;
        }
    }
    
    /**
     * Metodo para generar la consuta SQL para realizar la busqueda de palmicultor
     * dado un criterio
     * @param criterio tipo de busqueda
     * @param palabra valor a consultar
     * @param dpto valor a consultar
     * @param mun valor a consultar
     * @return cadena con la consulta SQL
     */
    public ArrayList<String> preguntaDeConsulta(int criterio,String palabra, String dpto, String mun) {
        System.out.println("ENTRA A PREGUNTAR   "+palabra);
        String consulta = "";
        try{
            String datos[] = palabra.split("_");
            palabra = datos[0] + " " + datos[1];
        }catch(Exception e){
            System.out.println("ENTRA A PREGUNTAR   "+palabra);
        switch (criterio) {
            case 0: //Nombre palmicultor
                consulta = "SELECT numero_documento,nombre,apellidos,municipio_id,activo FROM persona WHERE tipo_persona_id=1 AND nombre='" + palabra + "'";
                break;

            case 1://Numero documento
                consulta = "SELECT numero_documento,nombre,apellidos,municipio_id,activo FROM persona WHERE tipo_persona_id=1 AND numero_documento=" + palabra;
                break;

            case 2://Departamento
                consulta = "SELECT numero_documento,nombre,apellidos,municipio_id,activo FROM persona WHERE tipo_persona_id=1 "
                        + "AND municipio_id IN(SELECT id FORM municipio WHERE departamento_id = " + dpto + ")";
                break;

            case 3://municipio
                consulta = "SELECT numero_documento,nombre,apellidos,municipio_id,activo FROM persona WHERE tipo_persona_id=1 "
                        + "AND municipio_id =" + mun;
                break;

            case 4://Nombre hacienda
                consulta = "SELECT p.numero_documento, p.nombre, p.apellidos, p.municipio_id, p.activo FROM persona AS p, hacienda AS h\n"
                        + "WHERE p.tipo_persona_id =1 AND p.numero_documento = h.propietario_persona_numero_documento"
                        + "AND h.nombre =" + palabra;
                break;
        }
        System.out.println("CONSULT ---> "+consulta);
        
        }
        System.out.println("CONSULTA MALAAA");
    return this.consultarPropietariosPorCriterio(consulta);
    }

    /**
     * Metodo para realizar consultas por criterios de busqueda
     *
     * @param criterio tipo de busqueda de la hacienda
     * @param palabra valor a consultar
     * @param dpto valor a consultar
     * @param mun valor a consultar
     * @return Cadena de datos con la infomacion solicitada
     * @throws SQLException
     */
    public ArrayList<String> consultarHaciendaPorCriterio(int criterio, String palabra , String dpto, String mun){
        String consulta = "";
//        String datos[] = palabra.split("_");
//        palabra= datos[0] + " " + datos[1];

        switch (criterio) {
            case 0://nombre hacienda
                consulta = "SELECT cedula_catastral,id,nombre,"
                        + "municipio_id,nucleo_palmero,propietario_persona_numero_documento,"
                        + "activo FROM hacienda WHERE nombre= '" + palabra + "'";
                
                break;

            case 1://cedula catastral
                consulta = "SELECT cedula_catastral,id,nombre,"
                        + "municipio_id,nucleo_palmero,propietario_persona_numero_documento,"
                        + "activo FROM hacienda WHERE cedula_catastral= '" + palabra + "'";
                break;

            case 2://nucleo palmero
                consulta = "SELECT * FROM hacienda WHERE nucleo_palmero= '" + palabra + "'";
                break;

            case 3:// departamento
                consulta = "SELECT h.cedula_catastral,h.id,h.nombre,"
                        + "h.municipio_id,h.nucleo_palmero,h.propietario_persona_numero_documento,"
                        + "h.activo FROM hacienda h, municipio m WHERE m.departamento_id = " + dpto + " AND h.municipio_id = m.id";
                System.out.println(dpto);
                break;

            case 4://municipio
                consulta = "SELECT cedula_catastral,id,nombre,"
                        + "municipio_id,nucleo_palmero,propietario_persona_numero_documento,"
                        + "activo FROM hacienda WHERE municipio_id =" + mun;
                break;

            case 5:// nombre propietario o palmicultor
                consulta = "SELECT cedula_catastral,id,nombre,"
                        + "municipio_id,nucleo_palmero,propietario_persona_numero_documento,"
                        + "activo FROM hacienda WHERE propietario_persona_numero_documento"
                        + "IN(SELECT numero_documento FROM persona WHERE nombre = '" + palabra + "'";
                break;

            case 6://numero documento propietario o palmicultor
                consulta = "SELECT cedula_catastral,id,nombre,"
                        + "municipio_id,nucleo_palmero,propietario_persona_numero_documento,"
                        + "activo FROM hacienda WHERE propietario_persona_numero_documento= " + palabra;
                break;
        }

        return consultarDatos(consulta);
    }

    /**
     * Metodo para relaizar la consulta por criterios de hacienda en BD
     *
     * @param consulta la consulta sql
     * @param param el dato a buscar
     * @return cadena con la infomacion solicitado
     * @throws SQLException
     */
    private ArrayList<String> consultarDatos(String consulta) {
        try{
            ArrayList<String> consultaSQL = BaseDeDatos.getConsultaSQL(consulta);
            System.out.println(consultaSQL.get(0).toString());
            return consultaSQL;
        }catch(Exception e){
            ArrayList<String> l= new ArrayList<>();
            String nada = "nada";
            l.add(nada);
            return l;
        }
        
    }

    /**
     * Metodo para activar o desactivar un Palmicultor desde Administrador
     * @param id  numero de documento palmi
     * @param tipo activacion o desactivacion
     * @return true de la actualizacion
     */
    public boolean activarPalmicultor(String id, String tipo) {
        if(tipo.equalsIgnoreCase("true"))
        {   
            String consulta="UPDATE persona SET activo =1 WHERE numero_documento="+id;
            return (BaseDeDatos.ejecutarActualizacionSQL(consulta));
        }
        else
        {
            String consulta="UPDATE persona SET activo =0 WHERE numero_documento="+id;
            return (BaseDeDatos.ejecutarActualizacionSQL(consulta));
        }
    }

}
