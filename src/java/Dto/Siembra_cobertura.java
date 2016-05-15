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
//-------Atributos segun el diccionario de datos ---- //
public class Siembra_cobertura {

   private String id;
   private String area_lote;
   private String fecha_registro;
   private String fecha_siembra;
   private String cantidad;
   private String precioUnidad;
   private String precioAplic;
   private String subtotal;
   private String idInsumo;
   private String idUnidad;
   private String idLote;

   public Siembra_cobertura() {

   }
// ---- constructor con parametros----//

   public Siembra_cobertura(String id, String area_lote, String fecha_registro, String fecha_siembra, String cantidad, String precioUnidad,String precioAplic, String subtotal,String idInsumo,String idUnidad, String idLote) {
      this.id = id;
      this.area_lote = area_lote;
      this.fecha_registro = fecha_registro;
      this.fecha_siembra = fecha_siembra;
      this.cantidad = cantidad;
      this.precioUnidad = precioUnidad;
      this.precioAplic = precioAplic;
      this.idInsumo=idInsumo;
      this.idUnidad=idUnidad;
      this.subtotal = subtotal;

   }

//--------------- GETTER & SETTER--------------------------//
   public String getId() {
      return id;
   }

   public String getPrecioAplic() {
      return precioAplic;
   }

   public void setPrecioAplic(String precioAplic) {
      this.precioAplic = precioAplic;
   }

   public String getIdInsumo() {
      return idInsumo;
   }

   public void setIdInsumo(String idInsumo) {
      this.idInsumo = idInsumo;
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

   public String getArea_lote() {
      return area_lote;
   }

   public void setArea_lote(String area_lote) {
      this.area_lote = area_lote;
   }

   public String getFecha_registro() {
      return fecha_registro;
   }

   public void setFecha_registro(String fecha_registro) {
      this.fecha_registro = fecha_registro;
   }

   public String getFecha_siembra() {
      return fecha_siembra;
   }

   public void setFecha_siembra(String fecha_siembra) {
      this.fecha_siembra = fecha_siembra;
   }

   public String getCantidad() {
      return cantidad;
   }

   public void setCantidad(String cantidad) {
      this.cantidad = cantidad;
   }

   public String getPrecioUnidad() {
      return precioUnidad;
   }

   public void setPrecioUnidad(String precioUnidad) {
      this.precioUnidad = precioUnidad;
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
