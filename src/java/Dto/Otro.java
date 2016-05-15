/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author Pedro
 */
public class Otro {

   private String id;
   private String fecha_registro;
   private String fecha_activacion;
   private String cantidadItem;
   private String precioItem;
   private String precioAplicacion;
   private String subtotal;
   private String idLote;
   private String idItem;
   private String idUnidad;
   private String areaLote;

   public Otro() {

   }

   public Otro(String id, String fecha_registro, String fecha_activacion, String cantidadItem, String precioItem, String precioAplicacion, String subtotal, String idLote, String idItem,String idUnidad,String areaLote) {
      this.id = id;
      this.fecha_registro = fecha_registro;
      this.fecha_activacion = fecha_activacion;
      this.cantidadItem = cantidadItem;
      this.precioItem = precioItem;
      this.precioAplicacion=precioAplicacion;
      this.subtotal = subtotal;
      this.idItem=idItem;
      this.idUnidad=idUnidad;
      this.areaLote=areaLote;
   }

   public String getAreaLote() {
      return areaLote;
   }

   public String getPrecioAplicacion() {
      return precioAplicacion;
   }

   public void setPrecioAplicacion(String precioAplicacion) {
      this.precioAplicacion = precioAplicacion;
   }

   public void setAreaLote(String areaLote) {
      this.areaLote = areaLote;
   }

   public String getId() {
      return id;
   }

   public String getIdItem() {
      return idItem;
   }

   public void setIdItem(String idItem) {
      this.idItem = idItem;
   }

   public String getIdUnidad() {
      return idUnidad;
   }

   public void setIdUnidad(String idUnidad) {
      this.idUnidad = idUnidad;
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

   public String getFecha_activacion() {
      return fecha_activacion;
   }

   public void setFecha_activacion(String fecha_activacion) {
      this.fecha_activacion = fecha_activacion;
   }

   public String getCantidadItem() {
      return cantidadItem;
   }

   public void setCantidadItem(String cantidadItem) {
      this.cantidadItem = cantidadItem;
   }

   public String getPrecioItem() {
      return precioItem;
   }

   public void setPrecioItem(String precioItem) {
      this.precioItem = precioItem;
   }

   public String getSubtotal() {
      return subtotal;
   }

   public void setSubtotal(String subtotal) {
      this.subtotal = subtotal;
   }

   public String getIdLote() {
      return idLote;
   }

   public void setIdLote(String idLote) {
      this.idLote = idLote;
   }

}
