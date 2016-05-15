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
//--- parametros de enmienda ---//
public class Enmienda {
   private String id; // identificador autoincremental
   private String enmienda; // nombre de la enmienda
   private String fecha; // fecha de registro
   private String documentoPersona;

public Enmienda(){
   
}
// ---- constructor con parametros----//
public Enmienda(String id,String enmienda, String fecha,String documentoPersona){
   this.id=id;
   this.enmienda=enmienda;
   this.fecha=fecha;
   this.documentoPersona=documentoPersona;
}
//--------------- GETTER & SETTER--------------------------//
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getEnmienda() {
      return enmienda;
   }

   public void setEnmienda(String enmienda) {
      this.enmienda = enmienda;
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
