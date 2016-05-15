/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dto;

/**
 *
 * @author Rosemberg
 */
public class ActividadAdministracion {
    private String id;
    private String actividad;
    private String fecha_creacion;
    private String persona_numero_documento;

    public ActividadAdministracion() {
    }

    public ActividadAdministracion(String id, String actividad, String fecha_creacion) {
        this.id = id;
        this.actividad = actividad;
        this.fecha_creacion = fecha_creacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getPersona_numero_documento() {
        return persona_numero_documento;
    }

    public void setPersona_numero_documento(String persona_numero_documento) {
        this.persona_numero_documento = persona_numero_documento;
    }
    
    
}

