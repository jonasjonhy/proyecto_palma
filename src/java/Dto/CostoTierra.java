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
public class CostoTierra {
    
    private String id;
    private String fecha;
    private String areaLotes;
    private String precioArriendo;
   
    private String subtotal;

    public CostoTierra(String fecha, String areaLotes, String precioArriendo, String subtotal) {
        this.fecha = fecha;
        this.areaLotes = areaLotes;
        this.precioArriendo = precioArriendo;
       
        this.subtotal = subtotal;
    }

    public CostoTierra() {
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAreaLotes() {
        return areaLotes;
    }

    public void setAreaLotes(String areaLotes) {
        this.areaLotes = areaLotes;
    }

    public String getPrecioArriendo() {
        return precioArriendo;
    }

    public void setPrecioArriendo(String precioArriendo) {
        this.precioArriendo = precioArriendo;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
    
}
