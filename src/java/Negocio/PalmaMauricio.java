/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Dao.Fertilizante_DAO;
import Dao.Lote_DAO;
import Dao.PalmaSiembra_DAO;
import Dao.Unidad_DAO;
import Dao.nivelacionNutrientes_DAO;
import Dto.Fertilizante;
import Dto.NivelacionNutriente;
import Dto.PalmaSiembra;
import Dto.Unidad;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauricio uribe
 */
public class PalmaMauricio {
    
    public String consultarCodigoLote(String idLote) throws SQLException{
        
        return new Lote_DAO().consultarCodigoLote(idLote);
    }
    
    public String TablaNivelacionNutrientes(String idLote){
        
            //AQUI TRAIGO TODAS LAS NIVELACIONES EN UNA LISTA
            ArrayList<NivelacionNutriente> nivelaciones = new nivelacionNutrientes_DAO().consultarNivelacionesDeUnLote(idLote);
            //System.out.println("IMPRIMEE --> "+nivelaciones.get(0).getSubtotal());
            if(!nivelaciones.isEmpty()){
          
                //ESTE ES EL ENCABEZADO DE LA TABLA
            String tabla = "<table id=\"tablaNivelacion\" class='table table-bordered tablaP'>\n" +
                                "<tr>\n"+
                                    "<th colspan='9'><center>Nivelacion Nutrientes\n" +
                                    "</center></th>\n" +
                                "</tr>\n" +
                                "<tr class='negra'>\n" +
                                    "<td>&nbspFecha &nbsp;&nbsp;&nbsp;Aplicacion&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Area&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;Lote&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>Fertilizante</td>\n" +
                                    "<td>Cant x Hectarea</td>\n" +
                                    "<td>Unidad</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Precio&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Subtotal&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Costo Aplicacion&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</td>\n"
                    + "</tr>";

            //recorro todas las nivelaciones existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
            for(NivelacionNutriente n:nivelaciones){
                
                //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
            String fila =       "<tr>\n" +
                                    "<td><center>@</center></td>\n" +
                                    "<td><center>¬ Ha</center></td>\n" +
                                    "<td><center>~</center></td>\n" +
                                    "<td><center>%</center></td>\n" +
                                    "<td><center>°</center></td>\n" +
                                    "<td><center>$ £</center></td>\n" +
                                    "<td><center>$ Ȼ</center></td>\n" +
                                    "<td><center>$ §</center></td>\n" +
                                    "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarNivelacionNutriente('"+n.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarNivelacionNutriente.jsp?idNivelacion="+n.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
            
                fila = fila.replaceAll("@", n.getFechaAplicacion());
                fila = fila.replaceAll("¬", n.getAreaLote());
                fila = fila.replaceAll("~", n.getIdFertilizante());//aqui traigo es el nombre del fertilizante
                fila = fila.replaceAll("%", n.getCantidadFertilizante());
                fila = fila.replaceAll("°", n.getIdUnidad());//Aqui traigo es el nombre de la unidad
                fila = fila.replaceAll("£", n.getPrecioFertilizante());
                fila = fila.replaceAll("Ȼ", n.getSubtotal());
                fila = fila.replaceAll("§", n.getPrecioAplicacion());
                //fila = fila.replaceAll("¥", n.getId());
                
                tabla+=fila;
            }
            return tabla + "</table>";
            }else{
                return "<label>NO HAY NIVELACIONES REGISTRADAS AUN EN EL LOTE</label>";
            }
    }
    
    public String tablaSubtotalesNivelacionNutriente(String idLote){
        
        double subtotales = new nivelacionNutrientes_DAO().subtotalNivelacionesDeLote(idLote);
        double costosApli = new nivelacionNutrientes_DAO().costosAplicacionNivelacionLote(idLote);
        double totalNivelacion = subtotales + costosApli;
        
        String tabla = "<table id='subtotales' class='table table-bordered tablaP'>\n"+
                                 "<tr>\n"+
                                     "<td class='negra'>Subtotal Nivelación Nutrientes</td>\n"+
                                     "<td>&nbsp;$&nbsp;"+subtotales+"</td>\n"+
                                 "</tr>\n"+
                                 "<tr>\n"+
                                     "<td class='negra'>Subtotal Costos Aplicacion</td>\n"+
                                     "<td>&nbsp;$&nbsp;"+costosApli+"</td>\n"+
                                 "</tr>\n"+
                                 "<tr>\n"+
                                     "<td class='negra'>Total Nivelación suelos</td>\n"+
                                     "<td>&nbsp;$&nbsp;"+totalNivelacion+"</td>\n"+
                                 "</tr>\n"+
                             "</table>";
      
        return tabla;
    }
    
    public boolean registrarNivelacion(NivelacionNutriente n, String nuevoFertilizante, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(n.getAreaLote(), n.getIdLote())){
            
        
        if(n.getIdFertilizante().equals("0")){
            Fertilizante f = new Fertilizante();
            f.setDocumentoPersona(docPropietario);
            f.setNombreFertilizante(nuevoFertilizante);
            f.setFechaCreacion(this.obtenerFechaDelSistema());
            new Fertilizante_DAO().registrarNuevoFertilizante(f);
            
            Fertilizante reg = new Fertilizante_DAO().consultarFertilizantePorNombre(nuevoFertilizante);
            n.setIdFertilizante(reg.getId());
        }
        
        if(n.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            n.setIdUnidad(uni.getId());
        }
        n.setFechaRegistro(this.obtenerFechaDelSistema());
        
        return new nivelacionNutrientes_DAO().registrarNivelacion(n);
        }
        return false;
    }
    
    public String FormularioEditarNivelacionNutriente(String idNivelacion) throws SQLException{
        
        NivelacionNutriente n = new nivelacionNutrientes_DAO().consultarNivelacion(idNivelacion);
        
        String formulario = "<form action='../jsp/validarEditarNivelacionNutriente.jsp' method='post'>\n"+
                                "<div class='recuadro row blanco'>\n"+
                                    "<button type='button' class='close' data-dismiss='modal'>\n"+
                                    "<span aria-hidden='true'>&times;</span>\n"+
                                    "<span class='sr-only'>Close</span></button>\n"+
                                    "<center><h2 class='verde'>Actualizar Nivelacion de Nutriente</h2></center>\n"+
                                    "<br>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Fecha<label class='requerido'></label></label>\n"+
                                        "<input type='date' id='fechaAplicacion' name='fechaAplicacion' class='form-control' placeholder='' value='"+n.getFechaAplicacion()+"'required><br>\n"+
                                        "<label>Area de Lote<label class=\"requerido\"></label></label>\n"+
                                        "<input type='text' id='areaLote_e' name='areaLote_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#cantidadFertilizante_e', '#precioFertilizante_e')\" value='"+n.getAreaLote()+"'required><br>\n"+
                                        "<label>Fertilizante<label class='requerido'>*</label></label>\n"+
                                        "<SELECT name=\"idF\" id=\"idF\" class=\"form-control\" onchange=\"habilitarCampoOtroF_editar()\" >\n"+
                                        this.selectFertilizantes()+
                                        "<br>\n"+
                                    "<div class='otroFer'>\n"+
                                        "<label>¿Otro? indicanos cual</label>\n"+
                                            "<input type='text' id='otroFer' name='otroFer' disabled = 'disabled' class='form-control' placeholder='otro fertilizante' required><br>\n"+
                                     "</div>\n"+
                                "</div>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Cantidad por hectarea</label>\n"+
                                            "<input type='text' id='cantidadFertilizante_e' name='cantidadFertilizante_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#areaLote_e', '#precioFertilizante_e')\" value='"+n.getCantidadFertilizante()+"' required><br>\n"+
                                        "<label>Unidad<label class='requerido'>*</label></label>\n"+
                                        "<SELECT onchange=\"habilitarCampoOtroU_editar()\" name=\"idU\" id=\"idU\" class=\"form-control\">\n"+
                                        this.selectUnidades("peso")+
                                        "<br>\n"+
                                    "<div class=otraUni'>\n"+
                                        "<label>¿Otra? indicanos cual</label>\n"+
                                            "<input type='text' id='otroUni' name='otroUni' disabled = 'disabled' class='form-control' placeholder='otra unidad' required><br>\n"+
                                    "</div>\n"+
                                        "<label>Precio por unidad del Fertilizante (En pesos)</label>\n"+
                                        "<input type='text' id='precioFertilizante_e' name='precioFertilizante_e' class='form-control' onchange=\"cargarSubtotal3_editar(this, '#cantidadFertilizante_e', '#areaLote_e')\" value='"+n.getPrecioFertilizante()+"' placeholder=''><br>\n"+
                                        "<label>Subtotal</label>\n"+
                                         "<input type='text' id='subtotal_e' name='subtotal_e' class='form-control' placeholder=''  value='"+n.getSubtotal()+"' readonly><br>\n"+
                                          "<label>Costo Aplicacion</label>\n"+
                                         "<input type='text' id='precioAplicacion_e' name='precioAplicacion_e' class='form-control' onclick=\"cargarCostoAplicacion_editar()\" value='"+n.getPrecioAplicacion()+"' placeholder=''><br>\n"+
                                         "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+n.getId()+"' /></label>"+
                                "</div>\n"+
                                "<div class='col-sm-12'>\n"+
                                "<center><input class='btn btn-success' type='submit' onclick=\"loading(load)\" value='Guardar'><br></center><br>\n"+
                                 "</div>\n"+
                                "</div> \n"+
                            "</form>";
        
        return formulario;
    }
    
    public boolean editarNivelacionNutriente(NivelacionNutriente n, String nuevoFertilizante, String nuevaUnidad, String docPropi) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(n.getAreaLote(), n.getIdLote())){
            
        
        if(n.getIdFertilizante().equals("0")){
            Fertilizante f = new Fertilizante();
            f.setDocumentoPersona(docPropi);
            f.setNombreFertilizante(nuevoFertilizante);
            f.setFechaCreacion(this.obtenerFechaDelSistema());
            new Fertilizante_DAO().registrarNuevoFertilizante(f);
            
            Fertilizante reg = new Fertilizante_DAO().consultarFertilizantePorNombre(nuevoFertilizante);
            System.out.println("nuevo fer ---> "+reg.getId());
            n.setIdFertilizante(reg.getId());
        }
        
        if(n.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropi);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropi);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            System.out.println("nuevo fer ---> "+uni.getId());
            n.setIdUnidad(uni.getId());
        }
        n.setFechaRegistro(this.obtenerFechaDelSistema());
        
        return new nivelacionNutrientes_DAO().editarNivelacion(n);
        }
        return false;
    }
    
    public boolean eliminarNivelacionNutriente(String idNivelacion){
        
        return new nivelacionNutrientes_DAO().eliminarNivelacion(idNivelacion);
    }
    
    
    
    public String TablaPalmaSiembra(String idLote){
        
            //AQUI TRAIGO TODAS LAS SIEMBRAS EN UNA LISTA
            ArrayList<PalmaSiembra> siembras = new PalmaSiembra_DAO().consultarSiembras(idLote);
            
            if(!siembras.isEmpty()){
                
                //ESTE ES EL ENCABEZADO DE LA TABLA
            String tabla = "<table id=\"tablaSiembra\" class='table table-bordered tablaP'>\n" +
                                "<tr>\n"+
                                    "<th colspan='6'><center>Palmas Para la Siembra\n" +
                                    "</center></th>\n" +
                                "</tr>\n" +
                                "<tr class='negra'>\n" +
                                    "<td>&nbspFecha &nbsp;&nbsp;&nbsp;Siembra&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Area&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;Lote&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>Cant x Hectarea</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Precio X Unidad&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Subtotal&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</td>\n"
                    + "</tr>";

            //recorro todas las siembras existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
            for(PalmaSiembra s:siembras){
                
                //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
            String fila =       "<tr>\n" +
                                    "<td><center>@</center></td>\n" +
                                    "<td><center>¬ Ha</center></td>\n" +
                                    "<td><center>~</center></td>\n" +
                                    "<td><center>%</center></td>\n" +
                                    "<td><center>°</center></td>\n" +
                                    "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarSiembra'  onclick=\"FormEditarPalmaSiembra('"+s.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarNivelacionNutriente.jsp?idNivelacion="+s.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
            
                fila = fila.replaceAll("@", s.getFechaSiembra());
                fila = fila.replaceAll("¬", s.getAreaLote());
                fila = fila.replaceAll("~", s.getCantidadPlantulas());//aqui traigo es el nombre del fertilizante
                fila = fila.replaceAll("%", s.getPrecioPlantula());
                fila = fila.replaceAll("°", s.getSubtotal());//Aqui traigo es el nombre de la unidad
                
                tabla+=fila;
            }
            
            return tabla + "</table>";
            }else{
                return "<label>NO HAY SIEMBRAS DE PALMA AUN REGISTRADAS EN EL LOTE</label>";
            }
    }
    
    public String tablaSubtotalesSiembraPalma(String idLote){
        
        double subtotales = new PalmaSiembra_DAO().subtotalesPalmaSiembraDeLote(idLote);
        
        String tabla = " <table id='subtotales' class=\"table table-bordered tablaP\">\n" +
"                                    <td class=\"negra\">Total Siembra de Palma</td>\n" +
"                                    <td>&nbsp;$&nbsp;"+subtotales+"</td>\n" +
"                                </tr>\n" +
"                            </table>";
        return tabla;
    }
    
    public boolean registrarPalmaSiembra(PalmaSiembra s) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(s.getAreaLote(), s.getIdLote())){
            
        s.setFechaRegistro(this.obtenerFechaDelSistema());
        
        return new PalmaSiembra_DAO().registroPalmaSiembra(s);
        }
        return false;
    }
    
    
     public String FormularioEditarPalmaSiembra(String idSiembra) throws SQLException{
        
        PalmaSiembra s = new PalmaSiembra_DAO().consultarSiembra(idSiembra);
        
        String formulario = "<form action=\"../jsp/validarEditarPalmaSiembra.jsp\" method=\"post\">\n" +
"                                <div class=\"recuadro row blanco\">  \n" +
"                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n" +
"                                    <span aria-hidden=\"true\">&times;</span>\n" +
"                                    <span class=\"sr-only\">Close</span></button>\n" +
"                                    <center><h2 class=\"verde\">Actualizar Siembra de Palma</h2></center><br>\n" +
"                                    <div class=\"col-sm-6\">\n" +
"                                        <label>Fecha<label class=\"requerido\"></label></label>\n" +
"                                        <input type=\"date\" id=\"fechaSiembra\" name=\"fechaSiembra\" class=\"form-control\" placeholder=\"\" required value='"+s.getFechaSiembra()+"'><br>\n" +
"                                        <label>Area de Lote<label class=\"requerido\"></label></label>\n" +
"                                        <input type=\"text\" id=\"areaLote_e\" name=\"areaLote_e\" class=\"form-control\" placeholder=\"\" onchange=\"cargarSubtotal3_editar(this, '#cantidadPlantulas_e', '#precioPlantula_e')\" value='"+s.getAreaLote()+"'required><br>\n" +
"                                        <label>Cantidad por hectarea</label>\n" +
"                                            <input type=\"text\" id=\"cantidadPlantulas_e\" name=\"cantidadPlantulas_e\" class=\"form-control\" onchange=\"cargarSubtotal3_editar(this, '#areaLote_e', '#precioPlantula_e')\" value='"+s.getCantidadPlantulas()+"' placeholder=\"\" required><br> \n" +
"                                </div>\n" +
"                                <div class=\"col-sm-6 \">\n" +
"                                        \n" +
"                                       \n" +
"                                        <label>Precio por unidad (en pesos)</label>\n" +
"                                        <input type=\"text\" id=\"precioPlantula_e\" name=\"precioPlantula_e\" class=\"form-control\" onchange=\"cargarSubtotal3_editar(this, '#areaLote_e', '#cantidadPlantulas_e')\" value='"+s.getPrecioPlantula()+"' placeholder=\"\" required><br>\n" +
"                                        <label>Subtotal</label>\n" +
"                                         <input type=\"text\" id=\"subtotal_e\" name=\"subtotal_e\" class=\"form-control\" placeholder=\"\" value='"+s.getSubtotal()+"' readonly><br>\n" +
                                        "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+s.getId()+"' /></label>"+
"                                 </div>\n" +
"                                 <div class=\"col-sm-12\"><center><button class=\"btn btn-success\" type=\"submit\" onclick=\"loading(load)\">  Guardar</button><br></center></div>\n" +
"                                </div> \n" +
"                          \n" +
"                            </form>";
        
        
        
        System.out.println(formulario);
        
        return formulario;
    }
    
    
    public boolean editarPalmaSiembra(PalmaSiembra s) throws SQLException{
        if(new Lote_DAO().esMenorElAreaNueva(s.getAreaLote(), s.getIdLote())){
              
        s.setFechaRegistro(this.obtenerFechaDelSistema());
        
        return new PalmaSiembra_DAO().editarPalmaSiembra(s);
        }
        return false;
    }
    
    public boolean eliminarPalmaSiembra(String idSiembra){
        
        return new PalmaSiembra_DAO().eliminarPalmaSiembra(idSiembra);
    }
    
   
    
    
    
    
    
    
    
    /**
     * Obtiene la fecha actual del sistema, se usa para registros de fecha de registros de costos
     * @return 
     */
    public String obtenerFechaDelSistema(){
        
        Calendar c = GregorianCalendar.getInstance();
        SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = form.format(c.getTime());
        
        return fecha;
    }
    
    /**
     * crea un select HTML con las unidades de acuerdo al tipo requerido
     * @param tipoUnidad el tipo de unidad requerido
     * @return HTML del SELECT en formato cadena
     */
     public String selectUnidades(String tipoUnidad) throws SQLException{
        
        ArrayList<Unidad> unidades= new Unidad_DAO().consultarUnidades(tipoUnidad);
        
        String select = "\n<option value = 'n'>Seleccione</option>\n"
                + "<option value = '0'>Otra</option>";
        
        for(Unidad u:unidades){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(u.getId()));
            option = option.replaceAll("&", u.getUnidad());
            
            select+= option;
        }
        return select+"\n</select>";
    }
    
    /**
     * crea un select HTML con todos los fertilizantes de la BD
     * @return HTML del SELECT en formato cadena
     */
    public String selectFertilizantes() throws SQLException{
        
        ArrayList<Fertilizante> fertilizantes= new Fertilizante_DAO().consultarFertilizantes();
        
        String select = "<option value = 'n'>Seleccione</option>"
                + "<option value = '0'>Otro</option>";
        
        for(Fertilizante f : fertilizantes){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", f.getId());
            option = option.replaceAll("&", f.getNombreFertilizante());
            
            select+= option;
        }
        return select+"\n</select>";
    }
    
    
}
