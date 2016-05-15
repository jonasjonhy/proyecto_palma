/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dto;

/**
 *
 * @author Oscar Torres
 */
public class Animal {
    private int id;
    private String animal;
    private String fecha_creacion;
    private String persona_numero_documento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
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
