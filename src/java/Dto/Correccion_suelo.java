/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author Pedro Rico
 * Primera clase construida, las clases de dto del modulo 2 hablando con victor y edward se acordo que se llamen igual que sus procesos y
 * su representacion en la base de datos. Es decir aunque coreccion de suelos y preparacion de suelos usan el "suelo" son procesos distintos por lo que
 * no se tenra una clase suelo que haga ambas cosas sino clases separada nombradas por su proceso como en este caso "correccion_suelo.java"
 *
 */
 //-------Atributos segun el diccionario de datos ---- //
public class Correccion_suelo {
    private String id; 
    private String area_aplicacion; 
    private String fecha_registro;  
    private String fecha_aplicacion;
    private String cantidad;
    private String precio_enmienda;
    private String subtotal;
    private String precio_aplic_enmienda;
    private String idUnidad;
    private String idLote;
    private String idEnmienda;
    
    public Correccion_suelo(){
        
    }
    // ---- constructor con parametros----//
    public Correccion_suelo(String id, String area_aplicacion,String fecha_registro,String fecha_aplicacion, String cantidad,String precio_enmienda,String subtotal,String precio_aplic_enmienda,String idUnidad,String idLote,String idEnmienda){
        this.id=id;
        this.area_aplicacion=area_aplicacion;
        this.fecha_registro=fecha_registro;
        this.fecha_aplicacion=fecha_aplicacion;
        this.cantidad=cantidad;
	this.precio_enmienda=precio_enmienda;
	this.subtotal=subtotal;
	this.precio_aplic_enmienda=precio_aplic_enmienda;
	this.idUnidad=idUnidad;
	this.idLote=idLote;
	this.idEnmienda=idEnmienda;
    }

   //--------------- GETTER & SETTER--------------------------//
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getArea_aplicacion() {
      return area_aplicacion;
   }

   public void setArea_aplicacion(String area_aplicacion) {
      this.area_aplicacion = area_aplicacion;
   }

   public String getFecha_registro() {
      return fecha_registro;
   }

   public void setFecha_registro(String fecha_registro) {
      this.fecha_registro = fecha_registro;
   }

   public String getFecha_aplicacion() {
      return fecha_aplicacion;
   }

   public void setFecha_aplicacion(String fecha_aplicacion) {
      this.fecha_aplicacion = fecha_aplicacion;
   }

   public String getCantidad() {
      return cantidad;
   }

   public void setCantidad(String cantidad) {
      this.cantidad = cantidad;
   }

   public String getPrecio_enmienda() {
      return precio_enmienda;
   }

   public void setPrecio_enmienda(String precio_enmienda) {
      this.precio_enmienda = precio_enmienda;
   }

   public String getSubtotal() {
      return subtotal;
   }

   public void setSubtotal(String subtotal) {
      this.subtotal = subtotal;
   }

   public String getPrecio_aplic_enmienda() {
      return precio_aplic_enmienda;
   }

   public void setPrecio_aplic_enmienda(String precio_aplic_enmienda) {
      this.precio_aplic_enmienda = precio_aplic_enmienda;
   }

   public String getIdUnidad() {
      return idUnidad;
   }

   public void setIdUnidad(String idUnidad) {
      this.idUnidad = idUnidad;
   }

   public String getIdLote() {
      return idLote;
   }

   public void setIdLote(String idLote) {
      this.idLote = idLote;
   }

   public String getIdEnmienda() {
      return idEnmienda;
   }

   public void setIdEnmienda(String idEnmienda) {
      this.idEnmienda = idEnmienda;
   }
 
    
    
}
