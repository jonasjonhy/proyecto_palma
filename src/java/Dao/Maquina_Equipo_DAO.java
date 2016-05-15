/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Dto.Maquina_Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.BaseDeDatos;

/**
 *
 * @author Oscar Torres
 */
public class Maquina_Equipo_DAO {
    
    public ArrayList<Maquina_Equipo> consultarMaquina() throws SQLException{
       BaseDeDatos.conectar();
       String consulta = "SELECT id, maquina_equipo, fecha_creacion, persona_numero_documento FROM maquina_equipo";
       
       ArrayList<Maquina_Equipo> M_E = new ArrayList<>();
       ResultSet datos = BaseDeDatos.ejecutarSQL(consulta);
       //BaseDeDatos.desconectar();
       
       while(datos.next()){
           Maquina_Equipo m_e = new Maquina_Equipo();
           m_e.setId(Integer.parseInt(datos.getString("id")));
           m_e.setNombre(datos.getString("maquina_equipo"));
           m_e.setFechaCreacion(datos.getString("fecha_creacion"));
           m_e.setDocPersonaCreador(datos.getString("persona_numero_documento"));
           M_E.add(m_e);
       }
       return M_E;
    }
    
    public boolean registrarMaquina(Maquina_Equipo m_e){
        System.out.println("####################%%%%%%%%%&&&&&&&&&&&&////////////");
        BaseDeDatos.conectar();
        String registro = "INSERT INTO maquina_equipo(maquina_equipo, fecha_creacion, persona_numero_documento) VALUES(?, ?, ?)";
        Object param [] = {m_e.getNombre(), m_e.getFechaCreacion(), m_e.getDocPersonaCreador()};
        
        return BaseDeDatos.ejecutarActualizacionSQL(registro, param);
    }
    
}
