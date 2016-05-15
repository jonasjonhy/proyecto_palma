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
public class tipoDocumento_DAO {

    public tipoDocumento_DAO() {
    }
    
    /**
     * Método que busca el nombre del documento de acuerdo al id de tipo
     * @param idDocumento
     * @return el nombre del tipo de documento
     */
    public String consultarTipoDocumento(String idDocumento) throws SQLException{
        BaseDeDatos.conectar();
        String consulta = "SELECT tipo FROM tipo_documento WHERE id = ?";
        Object param[] = {idDocumento};
        String tipoDoc = "";
        ResultSet datos = BaseDeDatos.ejecutarSQL(consulta, param);
        
        while(datos.next()){
            tipoDoc = datos.getString("tipo");
        }
        return tipoDoc;
    }
    
}
