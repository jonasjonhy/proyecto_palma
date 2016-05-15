/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facade;

import Dto.CostoAdministracion;
import Dto.CostoTierra;
import Negocio.PalmaRosemberg;
import java.sql.SQLException;

/**
 *
 * @author Rosemberg
 */
public class FacadeRosemberg {
    
        public FacadeRosemberg() {
    }
        
         public String tablaCostosTierra(String idLote){
             
        return new PalmaRosemberg().TablaCostosTierra(idLote);
    }
         
         
          public String formularioEditarCostoTierra(String idCosto) throws SQLException{
        
        return new PalmaRosemberg().FormularioEditarCostoTierra(idCosto);
    }
          
          public boolean editarCostoTierra(CostoTierra n,String h) throws SQLException{
        
        return new PalmaRosemberg().editarCostoTierra(n,h);
    }
          
          public boolean registrarCostoTierra(CostoTierra c, String hacienda){
          
              return new PalmaRosemberg().registrarCostoTierra(c,hacienda);
              
          }
          
          public boolean eliminarCostoTierra(String idCosto){
              return new PalmaRosemberg().eliminarCostoTierra(idCosto);
          }
          
          public String tablaSubtotalesCostoTierra(String idHacienda){
              return new PalmaRosemberg().TablaSubtotalCostoTierra(idHacienda);
          }
    
        public String tablaAdministracion(String idLote){
             
        return new PalmaRosemberg().TablaAdministracion(idLote);
    }
        public String tablaSubtotalesAdministracion(String idHacienda){
              return new PalmaRosemberg().TablaSubtotalAdministracion(idHacienda);
          }
        
        public String botonAgregarCostosAdministracion(){
            return new PalmaRosemberg().botonAgregarCostoAdministracion();
        }
        public boolean registrarCostoAdministracion(CostoAdministracion c, String hacienda){
            return new PalmaRosemberg().registrarCostoAdministracion(c, hacienda);
        }
        
        public String registrarActividad(String nombre, String idPersona,String fecha){
            return new PalmaRosemberg().registrarActividad(nombre,idPersona, fecha);
        }
        
           public boolean eliminarCostoAdministracion(String idCosto){
              return new PalmaRosemberg().eliminarCostoAdministracion(idCosto);
          }
           
       public String formularioEditarCostoAdministracion(String idCosto) throws SQLException{
        
        return new PalmaRosemberg().FormularioEditarCostoAdministracion(idCosto);
    }
       public boolean actualizarCostoAdministracion(CostoAdministracion c, String hacienda){
        return new PalmaRosemberg().editarCostoAdministracion(c, hacienda);
       }
}
