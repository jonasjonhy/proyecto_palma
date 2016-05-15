/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facade;

import Dto.NivelacionNutriente;
import Dto.PalmaSiembra;
import Negocio.PalmaMauricio;
import java.sql.SQLException;

/**
 *
 * @author mauricio uribe
 */
public class FacadeMauricio {

    public FacadeMauricio() {
        
    }
    
    public String consultarCodigoLote(String idLote) throws SQLException{
        
        return new PalmaMauricio().consultarCodigoLote(idLote);
    }
    
    public boolean registrarNivelacion(NivelacionNutriente nivelacion, String nuevoFertilizante, String nuevaUnidad, String docPropietario) throws SQLException{
        System.out.println("ID LOTE --> "+nivelacion.getIdLote());
        return new PalmaMauricio().registrarNivelacion(nivelacion, nuevoFertilizante, nuevaUnidad, docPropietario);
    }
    
    public String FormularioEditarNivelacionNutriente(String idNivelacion) throws SQLException{
        
        return new PalmaMauricio().FormularioEditarNivelacionNutriente(idNivelacion);
    }
    
    public boolean editarNivelacionNutriente(NivelacionNutriente n, String otroF, String otroU, String docPropi) throws SQLException{
        
        return new PalmaMauricio().editarNivelacionNutriente(n, otroF, otroU, docPropi);
    }
    
    public String tablaNivelacionNutrientes(String idLote){
        
        return new PalmaMauricio().TablaNivelacionNutrientes(idLote);
    }
    
    public boolean eliminarNivelacionNutriente(String idNivelacion){
        
        return new PalmaMauricio().eliminarNivelacionNutriente(idNivelacion);
    }
    
    public String tablaSubtotalesNivelacionNutriente(String idLote){
        
        return new PalmaMauricio().tablaSubtotalesNivelacionNutriente(idLote);
    }
    
    
    public String tablaPalmaSiembra(String idLote){
        
        return new PalmaMauricio().TablaPalmaSiembra(idLote);
    }
    
    public boolean registrarPalmaSiembra(PalmaSiembra siembra) throws SQLException{
        
        return new PalmaMauricio().registrarPalmaSiembra(siembra);
    }
    
    public String formulacioEditarPalmaSiembra(String idSiembra) throws SQLException{
        
        return new PalmaMauricio().FormularioEditarPalmaSiembra(idSiembra);
    }
    
    public boolean editarPalmaSiembra(PalmaSiembra siembra) throws SQLException{
        
        return new PalmaMauricio().editarPalmaSiembra(siembra);
    }
    
    public boolean eliminarPalmaSiembra(String idsiembra){
        
        return new PalmaMauricio().eliminarPalmaSiembra(idsiembra);
    }
    
    public String tablaSubtotalesPalmaSiembra(String idLote){
        
        return new PalmaMauricio().tablaSubtotalesSiembraPalma(idLote);
    }
    
    
    public String selectFertilizantes() throws SQLException{
        
        return new PalmaMauricio().selectFertilizantes();
    }
    
    public String selectUnidades(String tipoU) throws SQLException{
        
        return new PalmaMauricio().selectUnidades(tipoU);
    }
}
