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
public class Unidad {
   private String id; // identificador autoincremental
   private String tipo; // tipo dela unidad segun la operacion
   private String unidad; // nombre de la unidad
   private String documentoPersona;
   
public Unidad(){
   
}
//---constructor con parametros---//
public Unidad(String id,String tipo,String unidad,String documentoPersona){
   this.id=id;
   this.tipo=tipo;
   this.unidad=unidad;
   this.documentoPersona=documentoPersona;
}
//--------------- GETTER & SETTER--------------------------//
   public String getTipo() {
      return tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUnidad() {
      return unidad;
   }

   public void setUnidad(String unidad) {
      this.unidad = unidad;
   }

   public String getDocumentoPersona() {
      return documentoPersona;
   }

   public void setDocumentoPersona(String documentoPersona) {
      this.documentoPersona = documentoPersona;
   }

   

}
