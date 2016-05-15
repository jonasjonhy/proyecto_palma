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
public class CFHerramientas {
    
    private int id;
    private String FechaRegistro;
    private int cantidad;
    private double precioUnd;
    private double subtotalCompra;
    private int vidaUtil;
    private double precioAñoUtil;
    private float areaServicio;
    private double costoHectarea;
    private float areal_lotes;
    private double subtotalHerramientas;
    private int idHacienda;
    private int idHerramienta;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Registro
     */
    public String getRegistro() {
        return FechaRegistro;
    }

    /**
     * @param Registro the Registro to set
     */
    public void setFechaRegistro(String Registro) {
        this.FechaRegistro = Registro;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnd
     */
    public double getPrecioUnd() {
        return precioUnd;
    }

    /**
     * @param precioUnd the precioUnd to set
     */
    public void setPrecioUnd(double precioUnd) {
        this.precioUnd = precioUnd;
    }

    /**
     * @return the subtotalCompra
     */
    public double getSubtotalCompra() {
        return subtotalCompra;
    }

    /**
     * @param subtotalCompra the subtotalCompra to set
     */
    public void setSubtotalCompra(double subtotalCompra) {
        this.subtotalCompra = subtotalCompra;
    }

    /**
     * @return the vidaUtil
     */
    public int getVidaUtil() {
        return vidaUtil;
    }

    /**
     * @param vidaUtil the vidaUtil to set
     */
    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    /**
     * @return the precioAñoUtil
     */
    public double getPrecioAñoUtil() {
        return precioAñoUtil;
    }

    /**
     * @param precioAñoUtil the precioAñoUtil to set
     */
    public void setPrecioAñoUtil(double precioAñoUtil) {
        this.precioAñoUtil = precioAñoUtil;
    }

    /**
     * @return the areaServicio
     */
    public float getAreaServicio() {
        return areaServicio;
    }

    /**
     * @param areaServicio the areaServicio to set
     */
    public void setAreaServicio(float areaServicio) {
        this.areaServicio = areaServicio;
    }

    /**
     * @return the costoHectarea
     */
    public double getCostoHectarea() {
        return costoHectarea;
    }

    /**
     * @param costoHectarea the costoHectarea to set
     */
    public void setCostoHectarea(double costoHectarea) {
        this.costoHectarea = costoHectarea;
    }

    /**
     * @return the areal_lotes
     */
    public float getAreal_lotes() {
        return areal_lotes;
    }

    /**
     * @param areal_lotes the areal_lotes to set
     */
    public void setAreal_lotes(float areal_lotes) {
        this.areal_lotes = areal_lotes;
    }

    /**
     * @return the subtotalMaquinaria
     */
    public double getSubtotalHerramientas() {
        return subtotalHerramientas;
    }

    /**
     * @param subtotalMaquinaria the subtotalMaquinaria to set
     */
    public void setSubtotalHerramientas(double subtotalHerramientas) {
        this.subtotalHerramientas = subtotalHerramientas;
    }

    /**
     * @return the idHacienda
     */
    public int getIdHacienda() {
        return idHacienda;
    }

    /**
     * @param idHacienda the idHacienda to set
     */
    public void setIdHacienda(int idHacienda) {
        this.idHacienda = idHacienda;
    }

    /**
     * @return the idHerramienta
     */
    public int getIdHerramienta() {
        return idHerramienta;
    }

    /**
     * @param idHerramienta the idMaquina to set
     */
    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }
}
