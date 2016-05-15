/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dto;

import java.util.ArrayList;

/**
 *
 * @author Rosemberg
 */
public class CostoAdministracion {
    private String id;
    private String fecha_registro;
    private String dias_laborados;
    private String costo_jornal;
    private String subtotal;
    private String tipo_actividad_id;

    public CostoAdministracion() {
    }

    public CostoAdministracion(String id, String fecha_registro, String dias_laborados, String costo_jornal, String subtotal, String tipo_actividad_id) {
        this.id = id;
        this.fecha_registro = fecha_registro;
        this.dias_laborados = dias_laborados;
        this.costo_jornal = costo_jornal;
        this.subtotal = subtotal;
        this.tipo_actividad_id = tipo_actividad_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDias_laborados() {
        return dias_laborados;
    }

    public void setDias_laborados(String dias_laborados) {
        this.dias_laborados = dias_laborados;
    }

    public String getCosto_jornal() {
        return costo_jornal;
    }

    public void setCosto_jornal(String costo_jornal) {
        this.costo_jornal = costo_jornal;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTipo_actividad_id() {
        return tipo_actividad_id;
    }

    public void setTipo_actividad_id(String tipo_actividad_id) {
        this.tipo_actividad_id = tipo_actividad_id;
    }


    
}
