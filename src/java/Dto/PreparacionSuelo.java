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
public class PreparacionSuelo {
    
    private String id;//Numero generado por la BD
    
    private String fechaPreparacion;//Feha elegida por el palmicultor
    private String tipoUso;//Si es por Area o por Metro
    private String cantidadPreparado;// Area del lote preparado en hectarea
    private String precio;//Precio por hectarea
    private String subtotal;//Calculado por el sistema
    private String idLabor;//tipo de labor
    private String idLote;//lote donde se prepara el suelo
    
    //Adicionales****************************
    private String codigoZonaLote;//Codgo asignado por el palicultor al lote
    private String labor;//Descripcion de la labor realizada en la preparacion del suelo en el lote

    public PreparacionSuelo() {
    }

    public PreparacionSuelo(String fechaPreparacion, String tipoUso, String cantidadPreparado, String precio, String subtotal, String idLabor, String idLote) {
        this.fechaPreparacion = fechaPreparacion;
        this.tipoUso = tipoUso;
        this.cantidadPreparado = cantidadPreparado;
        this.precio = precio;
        this.subtotal = subtotal;
        this.idLabor = idLabor;
        this.idLote = idLote;
        this.codigoZonaLote = "";
        this.labor = "";
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
     * @return the fechaPreparacion
     */
    public String getFechaPreparacion() {
        return fechaPreparacion;
    }

    /**
     * @param fechaPreparacion the fechaPreparacion to set
     */
    public void setFechaPreparacion(String fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    /**
     * @return the tipoUso
     */
    public String getTipoUso() {
        return tipoUso;
    }

    /**
     * @param tipoUso the tipoUso to set
     */
    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    /**
     * @return the cantidadPreparado
     */
    public String getCantidadPreparado() {
        return cantidadPreparado;
    }

    /**
     * @param cantidadPreparado the cantidadPreparado to set
     */
    public void setCantidadPreparado(String cantidadPreparado) {
        this.cantidadPreparado = cantidadPreparado;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
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
     * @return the codigoZonaLote 
     */
    public String getCodigoZonaLote(){
		return codigoZonaLote;
	}
    
    /**
     * @param codigoZonaLote the codigoZonaLote to set
     */ 
    public void setCodigoZonaLote(String codigoZonaLote){
		this.codigoZonaLote = codigoZonaLote;
	}

    /**
     * @return the labor
     */
    public String getLabor() {
        return labor;
    }

    /**
     * @param labor the labor to set
     */
    public void setLabor(String labor) {
        this.labor = labor;
    }
}
