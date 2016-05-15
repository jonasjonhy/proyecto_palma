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
public class Item {
   private String id;
   private String nombre;
   private String fecha;
   private String documentoPersona;

public Item(){
   
}
public Item(String id,String nombre,String fecha,String documentoPersona){
   this.id=id;
   this.nombre=nombre;
   this.fecha=fecha;
   this.documentoPersona=documentoPersona;
}

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getFecha() {
      return fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getDocumentoPersona() {
      return documentoPersona;
   }

   public void setDocumentoPersona(String documentoPersona) {
      this.documentoPersona = documentoPersona;
   }

}
