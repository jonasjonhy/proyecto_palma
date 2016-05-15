/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Facade;


import Dto.*;

import Negocio.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



/**
 *
 * @author mauricio uribe
 */
public class FacadeBrian {

    public FacadeBrian() {
    }
    
    
    
    //***************************************** -- MODULO 2 PROPIETARIO -- ********************************************
    
    //<editor-fold desc="PREPARACION SUELO">
    
    /**
     * Metodo para registrar la preparacion del suelo de un Lote
     * @param ps Object de la clase PreparacionSuelo que contiene la informacion a registrar
     * @return true si el registro es exitoso
     */
    public boolean registrarPreparacionSuelo(PreparacionSuelo ps)
    {
		return (new PalmaBrian().registrarPreparacionSuelo(ps));
	}
	
	/**
     * Metodo para actualizar los datos de una preparacion de suelo de un lote
     * @param ps Object con la informacion actualizada
     * @return true si la actualizacion es exitosa
     */
    public boolean actualizarPreparacionSuelo(PreparacionSuelo ps) {
        return (new PalmaBrian().actualizarPreparacionSuelo(ps));
    }

    /**
     * Metodo para eliminar registro de preparacion de suelo de un lote dado su
     * id
     * @param id identificador de la preparacion de suelo
     * @return true si la eliminacion fue exitosa
     */
    public boolean eliminarPreparacionSuelo(String id) {
        return (new PalmaBrian().eliminarPreparacionSuelo(id));
    }
    
    /**
     * Metodo para mostrar los datos de la preparacion fisica de suelo de un
     * lote
     * @param idLote lote donde estoy situado
     * @param tipo si es por a o por m
     * @return codigo html con la inforcaion (si hay registrada) de la preparacion de suelos
     */
    public String vistaPreparacionSuelo(String idLote,String tipo) {
		return (new PalmaBrian().vistaPreparacionSuelo(idLote,tipo));
	}
    
    /**
     * Metodo para editar la preparacion de suelo de un lote
     * @param id de la preparacion suelo
     * @return html
     */
    public String editarPreparacionSuelo (String id)
    {
        return (new PalmaBrian().editarPreparacionSuelo(id));
    }
    //</editor-fold>
    
    public static void main(String a [])
    {
        System.out.println(new FacadeBrian().vistaPreparacionSuelo("5", "m"));
    }
    
    //<editor-fold desc="LABOR SIEMBRA">
    
    
    /**
     * Metodo para registrar la preparacion del suelo de un Lote     *
     * @param ls Object de la clase PreparacionSuelo que contiene la informacion a registrar
     * @param idPropi id del palmicultor o propietario
     * @return true si el registro es exitoso
     */
    public boolean registrarLaborSiembra(LaborSiembra ls,String idPropi) {
        return (new PalmaBrian().registrarLaborSiembra(ls,idPropi));
    }

    /**
     * Metodo para actualizar los datos de una preparacion de suelo de un lote     *
     * @param ls Object con la informacion actualizada
     * @return true si la actualizacion es exitosa
     */
    public boolean actualizarLaborSiembra(LaborSiembra ls) {
        return (new PalmaBrian().actualizarLaborSiembra(ls));
    }

    /**
     * Metodo para eliminar registro de preparacion de suelo de un lote dado su id     *
     * @param id identificador de la preparacion de suelo
     * @return true si la eliminacion fue exitosa
     */
    public boolean eliminarLaborSiembra(String id) {
        return (new PalmaBrian().eliminarLaborSiembra(id));
    }

    /**
     * Metodo para obtener las labores de siembra registradas en un Lote     *
     * @param id identificacion del Lote
     * @return codigo HTML con la información solicitada
     */
    public String vistaLaborSiembra(String id) {
        return (new PalmaBrian().vistaLaborSiembra(id));
    }
    
    
    public String getOptionLabores()
    {
        return (new PalmaBrian().getSelectLabores());
    }

   //</editor-fold>
    
    
    
    public void generarReportePDF(String id, String h,String tipo,Document docum, String ruta, PdfWriter fd)
    {
       new PalmaBrian().generarPDF(id,h,tipo,docum, ruta, fd);
    }
    
}

