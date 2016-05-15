/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dto;

/**
 *
 * @author mauricio uribe
 */
public class PalmaSiembra {
    
    private String id;
    private String fechaRegistro;
    private String fechaSiembra;
    private String cantidadPlantulas;
    private String precioPlantula;
    private String subtotal;
    private String idLote;
    private String precioAplic;
    private String areaLote;
    
    

    public PalmaSiembra() {
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

    /**
     * @return the fechaRegistro
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaSiembra
     */
    public String getFechaSiembra() {
        return fechaSiembra;
    }

    /**
     * @param fechaSiembra the fechaSiembra to set
     */
    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    /**
     * @return the cantidadPlantulas
     */
    public String getCantidadPlantulas() {
        return cantidadPlantulas;
    }

    /**
     * @param cantidadPlantulas the cantidadPlantulas to set
     */
    public void setCantidadPlantulas(String cantidadPlantulas) {
        this.cantidadPlantulas = cantidadPlantulas;
    }

    /**
     * @return the precioPlantula
     */
    public String getPrecioPlantula() {
        return precioPlantula;
    }

    /**
     * @param precioPlantula the precioPlantula to set
     */
    public void setPrecioPlantula(String precioPlantula) {
        this.precioPlantula = precioPlantula;
    }

    /**
     * @return the subtotal
     */
    public String getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the idLote
     */
    public String getIdLote() {
        return idLote;
    }

    /**
     * @param idLote the idLote to set
     */
    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    /**
     * @return the areaLote
     */
    public String getAreaLote() {
        return areaLote;
    }

    /**
     * @param areaLote the areaLote to set
     */
    public void setAreaLote(String areaLote) {
        this.areaLote = areaLote;
    }

    /**
     * @return the precio_aplic
     */
    public String getPrecioAplic() {
        return precioAplic;
    }

    /**
     * @param precioAplic the precio_aplic to set
     */
    public void setPrecioAplic(String precioAplic) {
        this.precioAplic = precioAplic;
    }
    
    
    
    
}
