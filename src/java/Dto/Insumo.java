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
public class Insumo {
  private  String id;// identificador autoincremental
  private  String insumo; // nombre del insumo
  private  String fecha_creacion; // fecha de creacion del insumo
  private String documentoPersona;

public Insumo(){
   
}
//---constructor con parametros---//
public Insumo(String id,String insumo,String fecha_creacion,String documentoPersona){
   this.id=id;
   this.insumo=insumo;
   this.fecha_creacion=fecha_creacion;
   this.documentoPersona=documentoPersona;
}
//--------------- GETTER & SETTER--------------------------//
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getInsumo() {
      return insumo;
   }

   public void setInsumo(String insumo) {
      this.insumo = insumo;
   }

   public String getFecha_creacion() {
      return fecha_creacion;
   }

   public void setFecha_creacion(String fecha_creacion) {
      this.fecha_creacion = fecha_creacion;
   }

   public String getDocumentoPersona() {
      return documentoPersona;
   }

   public void setDocumentoPersona(String documentoPersona) {
      this.documentoPersona = documentoPersona;
   }

}
