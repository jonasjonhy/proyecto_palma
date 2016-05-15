/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import java.sql.Date;

/**
 *
 * @author brialxsf
 */
public class Lote 
{
    private String id;
    private int area;//area del lote
    private int capacidad;//capacidad de palmas para plantar
    private String fechaCreacion;//fecha que se crea por defecto en el mometo de registro
    private String fechaPlantacion;//fecha en la que se inica sembrar las palmas en el lote
    private String codigoZona;// codigo que el  propietario le asigne al lote
    private String haciendaId;//Id de la hacienda donde se registra el lote
    private String nombreHacienda;

    public Lote() {
    }

    

    /**
     * Constructor por parametros
     * @param area
     * @param capacidad
     * @param fechaCreacion
     * @param fechaPlantacion
     * @param codigoZona
     * @param haciendaId 
     */
   public Lote(String id, int area, int capacidad, String fechaCreacion, String fechaPlantacion, String codigoZona, String haciendaId) {
        this.id = id;
        this.area = area;
        this.capacidad = capacidad;
        this.fechaCreacion = fechaCreacion;
        this.fechaPlantacion = fechaPlantacion;
        this.codigoZona = codigoZona;
        this.haciendaId = haciendaId;
    }
    
    //--------------- GETTER & SETTER--------------------------//

    
   
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFechaPlantacion() {
        return fechaPlantacion;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaPlantacion(String fechaPlantacion) {
        this.fechaPlantacion = fechaPlantacion;
    }

    
    public String getNombreHacienda() {
        return nombreHacienda;
    }

    public void setNombreHacienda(String nombreHacienda) {
        this.nombreHacienda = nombreHacienda;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getHaciendaId() {
        return haciendaId;
    }

    public void setHaciendaId(String haciendaId) {
        this.haciendaId = haciendaId;
    }

    @Override
    public String toString() {
        return "Lote{" + "area=" + area + ", capacidad=" + capacidad + ", fechaCreacion=" + fechaCreacion + ", fechaPlantacion=" + fechaPlantacion + ", codigoZona=" + codigoZona + ", haciendaId=" + haciendaId + '}';
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    
    
    
    
}