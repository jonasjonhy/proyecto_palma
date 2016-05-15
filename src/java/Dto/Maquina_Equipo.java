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
public class Maquina_Equipo {
    private int id;
    private String nombre;
    private String fechaCreacion;
    private String docPersonaCreador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDocPersonaCreador() {
        return docPersonaCreador;
    }

    public void setDocPersonaCreador(String docPersonaCreador) {
        this.docPersonaCreador = docPersonaCreador;
    }
    
}
