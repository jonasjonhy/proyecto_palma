/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Dao.CostoAdministracion_DAO;
import Dao.CostoTierra_DAO;
import Dao.Lote_DAO;
import Dao.nivelacionNutrientes_DAO;
import Dao.ActividadAdministracion_DAO;
import Dto.ActividadAdministracion;
import Dto.CostoAdministracion;
import Dto.CostoTierra;
import Dto.Hacienda;
import Dto.Lote;
import Dto.NivelacionNutriente;
import java.util.ArrayList;
import util.Fecha;

/**
 *
 * @author Rosemberg
 */
public class PalmaRosemberg {

    public PalmaRosemberg() {
    }

    public String TablaCostosTierra(String idHacienda) {
        
            //AQUI TRAIGO TODAS LAS NIVELACIONES EN UNA LISTA
            ArrayList<CostoTierra> costos = new CostoTierra_DAO().consultarCostosTierraHacienda(idHacienda);
        
             if(!costos.isEmpty()){
                 String tabla="<table id=\"tablaCostosTierra\" class=\"table table-bordered tablaP\">\n" +
                                "<tr>\n" +
                                "<th colspan=\"9\">Costos Fijos Tierra\n" +
                                "</th>\n" +
                                "</tr>\n" +
                                "<tr class=\"negra\">\n" +
                                "<td>Fecha</td>\n" +
                                "<td>Area Lotes</td>\n" +
                                "<td>Precio Arriendo x Hectarea</td>\n" +
                                "<td>Subtotal</td>\n" +
                                "<td>Acciones</td>\n" +
                                "\n" +"</tr>";
             
                 for(CostoTierra c:costos){
                     String fila =       "<tr>\n" +
                                    "<td><center>@</center></td>\n" +
                                    "<td><center>¬</center></td>\n" +
                                    "<td><center>~</center></td>\n" +
                                    "<td><center>°</center></td>\n" +
                                        "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto' onclick=\"FormEditarCostoTierra('"+c.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarCostoTierra.jsp?idCosto="+c.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
                fila = fila.replaceAll("@", c.getFecha());
                fila = fila.replaceAll("¬", c.getAreaLotes());
                fila = fila.replaceAll("~", c.getPrecioArriendo());//aqui traigo es el nombre del fertilizante
                fila = fila.replaceAll("°", c.getSubtotal());
                //Aqui traigo es el nombre de la unidad
                tabla+=fila;
                
                 }
                return tabla + "</table>";
            }else{
                return "<label>NO HAY COSTOS DE TIERRA AUN EN LA HACIENDA</label>";
            }
    }

    public String FormularioEditarCostoTierra(String idCosto) {
       
        CostoTierra c=new CostoTierra_DAO().consultarCostoTierraHacienda(idCosto);
        
        String form="<form action=\"validarEditarCostoTierra.jsp\" method=\"post\">\n" +
        "<div class=\"recuadro row blanco\">  \n" +
        "\n" +
        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n" +
        "<span aria-hidden=\"true\">&times;</span>\n" +
        "<span class=\"sr-only\">Close</span></button>\n" +
        "<center><h2 class=\"verde\">Editar costo</h2></center>\n" +
        "<br>\n" +
        "<div id=\"edicionCosto\">\n" +
        "<div class=\"col-sm-8 col-sm-offset-2\">\n" +
        "<label>Fecha<label class=\"requerido\"></label></label>\n" +
        "<input type=\"date\" id=\"fecha\" name=\"fecha\" class=\"form-control\" value='"+c.getFecha()+"' required><br>\n" +
        "\n" +
        "<label>Area Lotes<label class=\"requerido\">*</label></label>\n" +
        "<input type=\"text\" id=\"area\" name=\"area\" class=\"form-control\" value="+c.getAreaLotes()+" placeholder=\"\" readonly>\n" +
        "<br>\n" +
        "\n" +
                
        "<label>Precio Arriendo x Hectarea</label>\n" +
        "<input type=\"text\" id=\"arriendo\" name=\"arriendo\" class=\"form-control\" placeholder=\"otra\" required><br>\n" +
        "<label>Subtotal</label>\n" +
        "<input type=\"text\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" value="+c.getSubtotal()+" placeholder=\"\" readonly><br>\n" +
        "<center><button class=\"btn btn-success\" type=\"submit\">Guardar <span class=\"glyphicon glyphicon-save\" aria-hidden=\"true\"></span></button><br></center>\n" +
         "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+c.getId()+"' /></label>"+
                "</div>\n" +
        "</div> \n" +
        "</div>\n" +
        "</form>";
        
        return form;
    }


    public boolean editarCostoTierra(CostoTierra n, String h) {
        
        CostoTierra_DAO c=new CostoTierra_DAO();
        if(isFechaValida(n, h, 1))
        return c.editarCostoTierra(n);
        else
        return false;
    }

    public boolean registrarCostoTierra(CostoTierra c,String codHacienda) {
        
        if(!isFechaValida(c, codHacienda,0))
            return false;
        Hacienda h=new Hacienda();
        h.setId(codHacienda);
        int suma= new Lote_DAO().getSumatoriaLotesDeHacienda(h);
        
        boolean valor= new CostoTierra_DAO().registrarCostoTierra(c,suma, h);
    
        return valor;
    }
    
    private boolean isFechaValida(CostoTierra c,String hacienda, int tipo){
       
        ArrayList<CostoTierra>costos =new CostoTierra_DAO().consultarCostosTierraHacienda(hacienda);
        for(CostoTierra tierra:costos){
            if(tipo==1 && tierra.getFecha().split("-")[0].equals(c.getFecha().split("-")[0]) && (!tierra.getId().equals(c.getId()))){
             return false;
            }
            
            else if(tipo==0 &&tierra.getFecha().split("-")[0].equals(c.getFecha().split("-")[0])){
                return false;
            }
        }
        return true;
        
    }

    public boolean eliminarCostoTierra(String idCosto) {
        return new CostoTierra_DAO().eliminarCostoTierra(idCosto);
    }

    public String TablaSubtotalCostoTierra(String idHacienda) {
        
        double sumatoriaSubtotales=new CostoTierra_DAO().getSumatoriaSubtotales(idHacienda);
        
        String tabla = "<table id='subtotales' class='table table-bordered tablaP'>\n"+
                                 "<tr>\n"+
                                     "<td class='negra'>Subtotal Costos Fijos de Tierra</td>\n"+
                                     "<td>&nbsp;$&nbsp;"+ sumatoriaSubtotales+"</td>\n"+
                                 "</tr>\n"+
                                 "</table>";
        return tabla;
    }
        public String TablaSubtotalAdministracion(String idHacienda) {
        
       // double sumatoriaSubtotales=new CostoTierra_DAO().getSumatoriaSubtotales(idHacienda);
        double sumatoriaSubtotales=new CostoAdministracion_DAO().getSumatoriaSubtotales(idHacienda);
        
        String tabla = "<table id='subtotales' class='table table-bordered tablaP'>\n"+
                                 "<tr>\n"+
                                     "<td class='negra'>Costos Fijos de Administracion</td>\n"+
                                     "<td>&nbsp;$&nbsp;"+ sumatoriaSubtotales+"</td>\n"+
                                 "</tr>\n"+
                                 "</table>";
        return tabla;
    }
    
    public String TablaAdministracion(String idHacienda) {
        
            //AQUI TRAIGO TODAS LAS NIVELACIONES EN UNA LISTA
           
            ArrayList<CostoAdministracion> costos= new CostoAdministracion_DAO().consultarCostosAdministracionHacienda(idHacienda);
             
            if(!costos.isEmpty()){
                 String tabla="<table id=\"tablaAdministracion\" class=\"table table-bordered tablaP\">\n" +
                                "<tr>\n" +
                                "<th colspan=\"9\">Costos Fijos de Administracion\n" +
                                "</th>\n" +
                                "</tr>\n" +
                                "<tr class=\"negra\">\n" +
                                "<td>Fecha</td>\n" +
                                "<td>Tipo Actividad</td>\n" +
                                "<td>Tiempo Empleado</td>\n" +
                                "<td>Precio Jornal</td>\n" +
                                "<td>Subtotal</td>\n" +
                                "<td>Acciones</td>\n" +
                                "\n" +"</tr>";
             
                 for(CostoAdministracion c:costos){
                     String fila =       "<tr>\n" +
                                    "<td><center>@</center></td>\n" +
                                    "<td><center>¬</center></td>\n" +
                                    "<td><center>~</center></td>\n" +
                                    "<td><center>°</center></td>\n" +
                                    "<td><center>%</center></td>\n" +
                                        "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto' onclick=\"FormEditarCostoAdministracion('"+c.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarCostoAdministracion.jsp?idCosto="+c.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
                fila = fila.replaceAll("@", c.getFecha_registro());
                     System.out.println(c.getTipo_actividad_id()+"ssss");
                fila = fila.replaceAll("¬", new ActividadAdministracion_DAO().getNombreActividad(c.getTipo_actividad_id()));
                fila = fila.replaceAll("~", c.getDias_laborados());//aqui traigo es el nombre del fertilizante
                fila = fila.replaceAll("°", c.getCosto_jornal());
                fila = fila.replaceAll("%", c.getSubtotal());
                
                //Aqui traigo es el nombre de la unidad
                tabla+=fila;
                
                 }
                return tabla + "</table>";
            }else{
                return "<label>NO HAY COSTOS DE ADMINISTRACION AUN REGISTRADOS</label>";
            }
    }
    
    public String botonAgregarCostoAdministracion(){
        
        ArrayList<ActividadAdministracion>actividades=new ActividadAdministracion_DAO().getActividades();
        String act="";
        for(ActividadAdministracion a:actividades){
            act+="<option value='"+a.getId()+"'>"+a.getActividad()+"</option>\n";
        }
        act+="<option value=\"0|\">otra</option>\n";
        String cadena="<div class=\"modal fade\" id=\"agregarCosto\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
"  <div class=\"modal-dialog\">\n" +
"    \n" +
"      \n" +
"      <div class=\"modal-body\">\n" +
"                      <form action=\"validarRegistroCostoAdministracion.jsp\" method=\"post\">\n" +
"                                <div class=\"recuadro row blanco\">  \n" +
"\n" +
"                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n" +
"                                    <span aria-hidden=\"true\">&times;</span>\n" +
"                                    <span class=\"sr-only\">Close</span></button>\n" +
"                                    <center><h2 class=\"verde\">Nuevo costo</h2></center>\n" +
"                                    <br>\n" +
"                                    <div class=\"col-sm-8 col-sm-offset-2\">\n" +
"                                    <label>Fecha<label class=\"requerido\"></label></label>\n" +
"                                        <input type=\"date\" id=\"fecha\" name=\"fecha\" class=\"form-control\" placeholder=\"\" required><br>\n" +
"                                    <label>Tipo Actividad<label class=\"requerido\">*</label></label>\n" +
"                                        <select id=\"actividad\" name=\"actividad\" class=\"form-control\">\n" +
"                                                \n" +
                                                        act+
"                                            </select>\n" +
"                                        <br>\n" +
"                                    <div class=\"otra\">        \n" +
"                                       <label>¿Otra? indicanos cual</label>\n" +
"                                          <div class=\"otraE\">\n" +
"                                            <input type=\"text\" id=\"nombre\" name=\"OtraEnmienda\" class=\"form-control\" placeholder=\"otra\">\n" +
"                                          </div>  \n" +
"                                    </div>         \n" +
"                                        <br><label>Tiempo Empleado<label class=\"requerido\">*</label></label>\n" +
"                                        <input type=\"number\" min=\"0\" id=\"tiempo\" name=\"tiempo\" class=\"form-control\" placeholder=\"\"required >\n" +
"                                        <br>\n" +
"                                    \n" +
"                                        <label>Costo Jornal</label>\n" +
"                                            <input type=\"number\" id=\"costo\" name=\"costo\" class=\"form-control\" placeholder=\"otra\"required><br>\n" +
"                                        <label>Subtotal</label>\n" +
"                                            <input type=\"text\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" placeholder=\"\" readonly><br>\n" +
"                                            <center><button class=\"btn btn-success\" type=\"submit\">Guardar <span class=\"glyphicon glyphicon-save\" aria-hidden=\"true\"></span></button><br></center>\n" +
"                                    </div>\n" +
"                                </div> \n" +
"                            </form>\n" +
"      </div>\n" +
"      \n" +
"    </div>\n" +
"  </div>";
        
        return cadena;
        
    }

    public boolean registrarCostoAdministracion(CostoAdministracion c, String hacienda) {
        
        int subtotalAdministracion=(Integer.parseInt(c.getDias_laborados()))*(Integer.parseInt(c.getCosto_jornal()));
        System.out.println(subtotalAdministracion+"Subtotal");
        return new CostoAdministracion_DAO().registrarCostoAdministracion(c,subtotalAdministracion, hacienda);
        
    }

    public String registrarActividad(String nombre, String idPersona, String fecha) {
        System.out.println("RegistrarActividad");
        ActividadAdministracion ac=new ActividadAdministracion();
        ac.setActividad(nombre);
        ac.setPersona_numero_documento(idPersona);
        ac.setFecha_creacion(fecha);
        
        return new ActividadAdministracion_DAO().registrarActividad(ac);
        
    }

    public boolean eliminarCostoAdministracion(String idCosto) {
        return new CostoAdministracion_DAO().eliminarCostoAdministracion(idCosto);
    }

    public String FormularioEditarCostoAdministracion(String idCosto) {
  
    CostoAdministracion c=new CostoAdministracion_DAO().consultarCostoAdministracionHacienda(idCosto);
        System.out.println("entra a editar costos");
   ArrayList<ActividadAdministracion>actividades=new ActividadAdministracion_DAO().getActividades();
   String act="";
        for(ActividadAdministracion a:actividades){
            act+="<option value='"+a.getId()+"'>"+a.getActividad()+"</option>\n";
        }
        act+="<option value=\"0|\">otra</option>\n";
        String cadena="<form action=\"validarEditarCostoAdministracion.jsp\" method=\"post\">\n" +
"                                <div class=\"recuadro row blanco\">  \n" +
"\n" +
"                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n" +
"                                    <span aria-hidden=\"true\">&times;</span>\n" +
"                                    <span class=\"sr-only\">Close</span></button>\n" +
"                                    <center><h2 class=\"verde\">Nuevo costo</h2></center>\n" +
"                                    <br>\n" +
"                                    <div class=\"col-sm-8 col-sm-offset-2\">\n" +
"                                    <label>Fecha<label class=\"requerido\"></label></label>\n" +
"                                        <input type=\"date\" id=\"fecha\" name=\"fecha\" class=\"form-control\" value='"+c.getFecha_registro()+"' placeholder=\"\" required><br>\n" +
"                                    <label>Tipo Actividad<label class=\"requerido\">*</label></label>\n" +
"                                        <select id=\"actividad\" name=\"actividad\" class=\"form-control\">\n" +
"                                                \n" +
                                                        act+
"                                            </select>\n" +
"                                        <br>\n" +
"                                    <div class=\"otra\">        \n" +
"                                       <label>¿Otra? indicanos cual</label>\n" +
"                                          <div class=\"otraE\">\n" +
"                                            <input type=\"text\" id=\"nombre\" name=\"OtraEnmienda\" class=\"form-control\" placeholder=\"otra\">\n" +
"                                          </div>  \n" +
"                                    </div>         \n" +
"                                        <br><label>Tiempo Empleado<label class=\"requerido\">*</label></label>\n" +
"                                        <input type=\"number\" min=\"0\" id=\"tiempo\" name=\"tiempo\" value='"+c.getDias_laborados()+"' class=\"form-control\" placeholder=\"\"required >\n" +
"                                        <br>\n" +
"                                    \n" +
"                                        <label>Costo Jornal</label>\n" +
"                                            <input type=\"number\" id=\"costo\" name=\"costo\" class=\"form-control\" value='"+c.getCosto_jornal()+"' placeholder=\"otra\"required><br>\n" +
"                                        <label>Subtotal</label>\n" +
"                                            <input type=\"text\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" value='"+c.getSubtotal()+"' placeholder=\"\" readonly><br>\n" +
"                                            <center><button class=\"btn btn-success\" type=\"submit\">Guardar <span class=\"glyphicon glyphicon-save\" aria-hidden=\"true\"></span></button><br></center>\n" +
"<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+c.getId()+"' /></label>"
                + "</div>\n" +
"                                </div> \n" +
"                            </form>\n";

        return cadena;
        
    }

    public boolean editarCostoAdministracion(CostoAdministracion c, String hacienda) {
        
        return new CostoAdministracion_DAO().editarCostoAdministracion(c);
        
    }

}
