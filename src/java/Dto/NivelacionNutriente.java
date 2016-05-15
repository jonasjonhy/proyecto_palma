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
public class NivelacionNutriente {
    
    private String id;
    private String areaLote;
    private String fechaRegistro;
    private String fechaAplicacion;
    private String cantidadFertilizante; //nutriente o fertilizante
    private String precioFertilizante;
    private String precioAplicacion; // precio de mano de obra de quien aplico el fertilizante
    private String idUnidad;
    private String idLote;
    private String idFertilizante;
    private String subtotal;

    public NivelacionNutriente() {
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
     * @return the fechaAplicacion
     */
    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    /**
     * @param fechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    /**
     * @return the cantidadFertilizante
     */
    public String getCantidadFertilizante() {
        return cantidadFertilizante;
    }

    /**
     * @param cantidadFertilizante the cantidadFertilizante to set
     */
    public void setCantidadFertilizante(String cantidadFertilizante) {
        this.cantidadFertilizante = cantidadFertilizante;
    }

    /**
     * @return the precioFertilizante
     */
    public String getPrecioFertilizante() {
        return precioFertilizante;
    }

    /**
     * @param precioFertilizante the precioFertilizante to set
     */
    public void setPrecioFertilizante(String precioFertilizante) {
        this.precioFertilizante = precioFertilizante;
    }

    /**
     * @return the precionAplicacion
     */
    public String getPrecioAplicacion() {
        return precioAplicacion;
    }

    /**
     * @param precionAplicacion the precionAplicacion to set
     */
    public void setPrecioAplicacion(String precionAplicacion) {
        this.precioAplicacion = precionAplicacion;
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

    /**
     * @return the idFertilizante
     */
    public String getIdFertilizante() {
        return idFertilizante;
    }

    /**
     * @param idFertilizante the idFertilizante to set
     */
    public void setIdFertilizante(String idFertilizante) {
        this.idFertilizante = idFertilizante;
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
    
    
    
}
