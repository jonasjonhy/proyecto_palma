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
public class Fertilizante {
    
    private String id;
    private String nombreFertilizante;
    private String fechaCreacion;
    private String documentoPersona; //documento que identifica a la persona que creo el nuevo registro de fertilizante

    public Fertilizante() {
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
     * @return the nombreFertilizante
     */
    public String getNombreFertilizante() {
        return nombreFertilizante;
    }

    /**
     * @param nombreFertilizante the nombreFertilizante to set
     */
    public void setNombreFertilizante(String nombreFertilizante) {
        this.nombreFertilizante = nombreFertilizante;
    }

    /**
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the documentoPersona
     */
    public String getDocumentoPersona() {
        return documentoPersona;
    }

    /**
     * @param documentoPersona the documentoPersona to set
     */
    public void setDocumentoPersona(String documentoPersona) {
        this.documentoPersona = documentoPersona;
    }
    
    
    
}
