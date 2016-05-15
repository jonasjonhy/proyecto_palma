/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Hacienda;
import Dto.Lote;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BaseDeDatos;

/**
 *
 * @author brialxsf
 */
public class Lote_DAO 
{
    /**
     * Constructor sin parametros
     * Permite realizar la coneccion a la BD
     */
    public Lote_DAO() {
        if (!BaseDeDatos.hayConexion())
            BaseDeDatos.conectar();
    }
    
    
    public String consultarCodigoLote(String idLote) throws SQLException{
        BaseDeDatos.conectar();
        String consulta="SELECT codigo_zona FROM lote_zona WHERE id = ?";
        Object param[] = {idLote};
        
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String codigo = "";
        
        while(datos.next()){
            codigo = datos.getString("codigo_zona");
        }
        return codigo;
    }
    
    /**
     * Metodo para registrar Lote
     * @param l Object de la clase Lote
     * @return True en caso de realizar el registro exitoso
     */
    public boolean registrarLote(Lote l) 
    {
        try {
            if(!this.estaRegistrado(l.getCodigoZona(),l.getHaciendaId())) //Consulto en BD si el lote ya existe en el municipio
            {   
             
                String consulta2 = "INSERT INTO lote_zona(area, capacidad_palma, fecha_creacion, fecha_plantacion, codigo_zona, hacienda_id, activo) VALUES(?, ?, ?, ?, ?, ?, ?)";
                Object param[] = {l.getArea(), l.getCapacidad(), l.getFechaPlantacion(), l.getFechaPlantacion(), l.getCodigoZona(), l.getHaciendaId(), "1"};
                //codigo para que el Server BD tome la instrucion a ejecutar
                /*String consulta="INSERT INTO hacienda VALUES('"+l.getArea()+"','"+l.getCapacidad()
                        +"',  CURDATE () ,'"+l.getFechaPlantacion()+"','"+l.getCodigoZona()
                        +"','"+l.getHaciendaId()+"','1')";*/
              
                //Ejecuta la actualizacion si todo esta bien registra el lote
                return (BaseDeDatos.ejecutarActualizacionSQL(consulta2, param));
            }
            
        } catch (SQLException e) 
            {
                //captura error si se presenta cuando se ejecuta la actualizacion
                System.err.println("ERROR LOTE : "+e.getMessage());
            }
        return false;
    }
    
    
    /**Metodo que verifica si un lote se encuentra registrado     * 
     * @param codigoZona codigo asignado por el palmicultor o propietario del lote
     * @param haciendaId codigo de la hacienda donde se relaiza el registro
     * @return  true si no se encuentra en la BD y se puede realizar el registro
     * @throws SQLException 
     */
    private boolean estaRegistrado(String codigoZona, String haciendaId) throws SQLException 
    {
        //Instruccion para la BD y consultar
        String consulta="SELECT id FROM lote_zona WHERE hacienda_id = ? AND codigo_zona = ?";
        Object param[] = {haciendaId, codigoZona};
        //Obtiene todos los valores generados por la consulta
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta, param);
        if(!dat.next()){
            return false;
        }
        return true;
        
    }
   
    /**
     * Metodo para listar lotes
     * @param h objecto de la clase Hacienda con el id de la haciendo de los lotes a listar
     * @return cadena de los id y codigo de los lotes de esa hacienda
     * @throws SQLException posible error en la consulta
     */
    public String listarLotes(Hacienda h) throws SQLException
    {
        String listaLotes="";
        String consulta2 = "SELECT id, codigo_zona, area, fecha_plantacion FROM lote_zona WHERE hacienda_id = ? AND activo = ?";
        Object param[] = {h.getId(), "1"};
        String consulta="SELECT id,codigo_zona,area,fecha_plantacion FROM lote WHERE hacienda_id="+h.getId();
        
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta2, param);
        
        
        while(dat.next())
        {
           String id=dat.getString("id");
           String codZ=dat.getString("codigo_zona");
           String area=dat.getString("area");
           String fp=dat.getString("fecha_plantacion");
           listaLotes+=id+"&"+codZ+"&"+area+"&"+fp+"¬";
           System.out.println("LOTES BD BD ::: "+ listaLotes);
        }
        System.out.println("EL RETORNO DE LA CONSULTA DE LOTES "+ listaLotes);
        return listaLotes;
    }
    
    /**
     * Metodo para consultar la informacion de un lote dado su id
     * @param id codigo id del lote a consultar
     * @return Object de la clase Lote con la informacion consultada
     * @throws SQLException posible error de la consulta
     */
    public Lote consultarLote(String id) throws SQLException
    {
        Lote nuevo=new Lote();
        String consulta="SELECT * FROM lote_zona WHERE id= ?";
        Object param []={id};
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta,param);
        while (dat.next())
        {
            String cod=dat.getNString("codigo_zona");
            int area=dat.getInt("area");
            int capa=dat.getInt("capacidad_palma");
            String fc=dat.getString("fecha_creacion");
            String fp=dat.getString("fecha_plantacion");
            
            nuevo.setArea(area);
            nuevo.setCapacidad(capa);
            nuevo.setCodigoZona(cod);
            nuevo.setFechaCreacion(fc);
            nuevo.setFechaPlantacion(fp);
            
        }
        return nuevo;
    }

    
    
    /**
     * Proximamente actualizar información de un lote
     * @param l Object de la clase Lote con la información actualizada
     * @return true si la actualización se efectuó satisfactoriamente 
     */
    public boolean actulizarLote(Lote l)
    {
        String consulta="UPDATE lote_zona SET area = ?, capacidad_palma = ? WHERE id = ?";
        Object param[] = {l.getArea(), l.getCapacidad(), l.getId()};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }
    
    /**
     * Metodo para desactivar un lote de una hacienda
     * @param id identificacion del lote 
     * @return true si la desactivación se efectuó satisfactoriamente 
     */
    public boolean desactivarLote(String id)
    {
        System.out.println("LOTE DESAC ---> "+id);
        String consulta="UPDATE lote_zona SET activo = ? WHERE id = ?";//donde 1 es activo y 0 es inactivo
        Object param[] = {"0", id};
        return (BaseDeDatos.ejecutarActualizacionSQL(consulta, param));
    }
    
    /**
     * Metodo para obtener el Area de un lote
     * @param id numero que identifica al lote en la BD
     * @return String con el dato consultado
     */
    public String obtenerAreaLote(String id) throws SQLException
    {
        String nombre="";
        String consulta="SELECT area FROM lote_zona WHERE id="+id;
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta);
        while (dat.next())
        {
            nombre=dat.getNString("area");
        }
        return nombre;
    }
    
    /**
     * Metodo para obtener el codigo del lote dado un id
     * @param id del lote
     * @return String con el codigo del lote
     * @throws SQLException 
     */
    public String obtenerCodigoLote(String id) throws SQLException
    {
        String nombre="";
        String consulta="SELECT codigo_zona FROM lote_zona WHERE id="+id;
        ResultSet dat=BaseDeDatos.ejecutarSQL(consulta);
        while (dat.next())
        {
            nombre=dat.getNString("codigo_zona");
        }
        return nombre;
    }
    
    public String nombreHaciendaDelLote(String idLote) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT h.nombre FROM hacienda h, lote_zona l WHERE l.id = ? AND l.hacienda_id = h.id";
        Object param[] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        String nombreHac = ""; 
        
        while(datos.next()){
            nombreHac = datos.getString(1);
        }
        return nombreHac;
    }
    
    /**
     * verifica si el area destinada al costo es menor.. si es asi continúa el registro, de lo contrario Error
     * @param areaNueva area que se va a registrar en el costo
     * @param idLote id del lote
     * @return verdadero si el area a registrar en el costo es menor
     * @throws SQLException 
     */
    public boolean esMenorElAreaNueva(String areaNueva, String idLote) throws SQLException{
        BaseDeDatos.conectar();
        System.out.println("datos ----> "+areaNueva+"  "+idLote);
        String consulta = "SELECT area FROM lote_zona WHERE id = ?";
        Object param [] = {idLote};
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        
        String areaLote = ""; 
        while(datos.next()){
            areaLote = datos.getString("area");
            System.out.println("AREAAAAA LOTEEEEEE -->"+areaLote);
            System.out.println("AREAAAAA Nuevaaaaa -->"+areaNueva);
        }
        if(Integer.parseInt(areaLote)>Integer.parseInt(areaNueva)){
            return true;
        }
        return false;
    }

     public int getSumatoriaLotesDeHacienda(Hacienda h){
        System.out.println("SELECT SUM(area) FROM lote_zona WHERE hacienda_id='"+h.getId()+"'");
        BaseDeDatos.conectar();
        int r=0;
        String consulta="SELECT SUM(area) FROM lote_zona WHERE hacienda_id='"+h.getId()+"'";
        
        ArrayList<String>c=BaseDeDatos.getConsultaSQL(consulta);
        String cosa=c.get(0);
        cosa=cosa.replaceAll("&", "");
        System.out.println(cosa);
        return Integer.parseInt(cosa);
    }
}
