/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facade;

import Dto.CFMaquinaria;
import Negocio.PalmaOscar;
import java.sql.SQLException;

/**
 *
 * @author Oscar Torres
 */
public class FacadeOscar {
    
    
       public String tablaCFMaquinaria(int hacienda){
        
        return new PalmaOscar().TablaCFMaquinaria(hacienda);
    }
       public String tablaCFAnimales(int hacienda){
       return new PalmaOscar().TablaCFAnimales(hacienda);
       }
       
       public String editarCFMaquinaria(String id) throws SQLException{
       return new PalmaOscar().editarCFMaquinaria(id);
       }
       
       public String tablaCFHerramientas(int hacienda){
       return new PalmaOscar().TablaCFHerramientas(hacienda);
       }
       
       
       public String getSelectMaquina() throws SQLException{
       return new PalmaOscar().getSelectMaquina();
       }
       
       public boolean registrarCFMaquina(CFMaquinaria CFM, String otraM, String idPropietario) throws SQLException{
       return new PalmaOscar().registrarCFMaquina(CFM,otraM,idPropietario);
       }
       
       
}
