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
public class LaborSiembra {
    
    private String id;
    private String areaLoteLabor;
    private String fechaRegistro;
    private String fechaLabor;
    private String cantidadLabor; //de tiempo
    private String precioLabor;
    private String idLabor;
    private String idUnidad;
    private String idLote;
    private String subtotal;
    
    //Adicionales
    private String labor;//nombre de la labor
    private String unidad;//nombre de la unidad

    public LaborSiembra() {
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
     * @return the areaLoteLabor
     */
    public String getAreaLoteLabor() {
        return areaLoteLabor;
    }

    /**
     * @param areaLoteLabor the areaLoteLabor to set
     */
    public void setAreaLoteLabor(String areaLoteLabor) {
        this.areaLoteLabor = areaLoteLabor;
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
     * @return the fechaLabor
     */
    public String getFechaLabor() {
        return fechaLabor;
    }

    /**
     * @param fechaLabor the fechaLabor to set
     */
    public void setFechaLabor(String fechaLabor) {
        this.fechaLabor = fechaLabor;
    }

    /**
     * @return the cantidadLabor
     */
    public String getCantidadLabor() {
        return cantidadLabor;
    }

    /**
     * @param cantidadLabor the cantidadLabor to set
     */
    public void setCantidadLabor(String cantidadLabor) {
        this.cantidadLabor = cantidadLabor;
    }

    /**
     * @return the precioLabor
     */
    public String getPrecioLabor() {
        return precioLabor;
    }

    /**
     * @param precioLabor the precioLabor to set
     */
    public void setPrecioLabor(String precioLabor) {
        this.precioLabor = precioLabor;
    }

    /**
     * @return the idLabor
     */
    public String getIdLabor() {
        return idLabor;
    }

    /**
     * @param idLabor the idLabor to set
     */
    public void setIdLabor(String idLabor) {
        this.idLabor = idLabor;
    }

    /**
     * @return the idUnidad
     */
    public String getIdUnidad() {
        return idUnidad;
    }

    /**
     * @param idUnidad the idUnidad to set
     */
    public void setIdUnidad(String idUnidad) {
        this.idUnidad = idUnidad;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
        this.labor = labor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    
    
    
}
