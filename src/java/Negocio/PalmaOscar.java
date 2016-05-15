package Negocio;

import Dao.CFAnimales_DAO;
import Dao.CFHerramientas_DAO;
import Dao.CFMaquinaria_DAO;
import Dao.Hacienda_DAO;
import Dao.Herramienta_DAO;
import Dao.Maquina_Equipo_DAO;
import Dto.CFAnimales;
import Dto.CFHerramientas;
import Dto.CFMaquinaria;
import Dto.Herramienta;
import Dto.Maquina_Equipo;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar Torres
 */
public class PalmaOscar {
    public String TablaCFMaquinaria(int idHacienda){
        
            //AQUI TRAIGO TODOS CFMAQUINARIA POR HACIENDA
            ArrayList<CFMaquinaria> cfmaq = new CFMaquinaria_DAO().consultarCFMaquinaria(idHacienda);
            //System.out.println("IMPRIMEE --> "+nivelaciones.get(0).getSubtotal());
            if(!cfmaq.isEmpty()){
          
                //ESTE ES EL ENCABEZADO DE LA TABLA
            String tabla = "<table id=\"tablaCFMaquinaria\" class='table table-bordered tablaP'>\n" +
                                "<tr>\n"+
                                    "<th colspan='12'><center>COSTOS FIJOS MAQUINARIA\n" +
                                    "</center></th>\n" +
                                "</tr>\n" +
                                "<tr class='negra'>\n" +
                                    "<td>&nbspFecha &nbsp;&nbsp;&nbsp;Registro&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Maquinaria o Equipo&nbsp;&nbsp;&nbsp; &nbsp;</td>\n" +
                                    "<td>Cantidad</td>\n" +
                                    "<td>Precio de Compra</td>\n" +
                                    "<td>Total Compra</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Vida Util&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Precio Por Año Vida Util&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp; Area Servicio&nbsp;&nbsp;</td>\n" +
                                    "<td>Costo por Hectarea Año</td>\n"+
                                    "<td>Area Total Lotes</td>\n"+
                                    "<td>Subtotal Compra Maq & Equipos</td>\n"+
                                    "<td>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</td>\n"
                    + "</tr>";

            //recorro todas las nivelaciones existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
            for(CFMaquinaria m:cfmaq){
                
                //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
            String fila =       "<tr>\n" +
                                    "<td><center>"+m.getFechaRegistro()+"</center></td>\n" +
                                    "<td><center>"+new CFMaquinaria_DAO().consultarMaquina(m.getIdMaquina())+"</center></td>\n" +
                                    "<td><center>"+m.getCantidad()+"</center></td>\n" +
                                    "<td><center>"+m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getCantidad()*m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getVidaUtil()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/(m.getVidaUtil()))+"</center></td>\n" +
                                    "<td><center>"+m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><center>"+(((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio())*m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarCFMaquinaria('"+m.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarNivelacionNutriente.jsp?idNivelacion="+m.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
            

                tabla+=fila;
            }
            return tabla + "</table>";
            }else{
                return "<label>NO HAY COSTOS FIJOS DE MAQUINA EN LA HACIENDA</label>";
            }
    }
    
     public String TablaCFHerramientas(int idHacienda){
        
            //AQUI TRAIGO TODOS CFMAQUINARIA POR HACIENDA
            ArrayList<CFHerramientas> cfherra = new CFHerramientas_DAO().consultarCFHerramientas(idHacienda);
            //System.out.println("IMPRIMEE --> "+nivelaciones.get(0).getSubtotal());
            if(!cfherra.isEmpty()){
          
                //ESTE ES EL ENCABEZADO DE LA TABLA
            String tabla = "<table id=\"tablaCFHerramientas\" class='table table-bordered tablaP'>\n" +
                                "<tr>\n"+
                                    "<th colspan='12'><center>COSTOS FIJOS HERRAMIENTAS\n" +
                                    "</center></th>\n" +
                                "</tr>\n" +
                                "<tr class='negra'>\n" +
                                    "<td>&nbspFecha &nbsp;&nbsp;&nbsp;Registro&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Herramienta&nbsp;&nbsp;&nbsp; &nbsp;</td>\n" +
                                    "<td>Cantidad</td>\n" +
                                    "<td>Precio de Compra</td>\n" +
                                    "<td>Total Compra</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Vida Util&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Precio Por Año Vida Util&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp; Area Servicio&nbsp;&nbsp;</td>\n" +
                                    "<td>Costo por Hectarea Año</td>\n"+
                                    "<td>Area Total Lotes</td>\n"+
                                    "<td>Subtotal Compra Herramienta</td>\n"+
                                    "<td>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</td>\n"
                    + "</tr>";

            //recorro todas las nivelaciones existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
            for(CFHerramientas m:cfherra){
                
                //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
            String fila =       "<tr>\n" +
                                    "<td><center>"+m.getRegistro()+"</center></td>\n" +
                                    "<td><center>"+new CFHerramientas_DAO().consultarHerramienta(m.getIdHerramienta())+"</enter></td>\n" +
                                    "<td><center>"+m.getCantidad()+"</center></td>\n" +
                                    "<td><center>"+m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getCantidad()*m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getVidaUtil()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/(m.getVidaUtil()))+"</center></td>\n" +
                                    "<td><center>"+m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><center>"+(((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio())*m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarNivelacionNutriente('"+m.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarNivelacionNutriente.jsp?idNivelacion="+m.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
            

                tabla+=fila;
            }
            return tabla + "</table>";
            }else{
                return "<label>NO HAY COSTOS DE HERRAMIENTAS REGISTRADAS</label>";
            }
    }
    
      public String TablaCFAnimales(int idHacienda){
        
            //AQUI TRAIGO TODOS CFMAQUINARIA POR HACIENDA
            ArrayList<CFAnimales> cfani = new CFAnimales_DAO().consultarCFAnimales(idHacienda);
            //System.out.println("IMPRIMEE --> "+nivelaciones.get(0).getSubtotal());
            if(!cfani.isEmpty()){
          
                //ESTE ES EL ENCABEZADO DE LA TABLA
            String tabla = "<table id=\"tablaCFMaquinaria\" class='table table-bordered tablaP'>\n" +
                                "<tr>\n"+
                                    "<th colspan='12'><center>COSTOS FIJOS MAQUINARIA\n" +
                                    "</center></th>\n" +
                                "</tr>\n" +
                                "<tr class='negra'>\n" +
                                    "<td>&nbspFecha &nbsp;&nbsp;&nbsp;Registro&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Maquinaria o Equipo&nbsp;&nbsp;&nbsp; &nbsp;</td>\n" +
                                    "<td>Cantidad</td>\n" +
                                    "<td>Precio de Compra</td>\n" +
                                    "<td>Total Compra</td>\n" +
                                    "<td>&nbsp;&nbsp;&nbsp;Vida Util&nbsp;&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp;Precio Por Año Vida Util&nbsp;&nbsp;</td>\n" +
                                    "<td>&nbsp;&nbsp; Area Servicio&nbsp;&nbsp;</td>\n" +
                                    "<td>Costo por Hectarea Año</td>\n"+
                                    "<td>Area Total Lotes</td>\n"+
                                    "<td>Subtotal Compra Maq & Equipos</td>\n"+
                                    "<td>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</td>\n"
                    + "</tr>";

            //recorro todas las nivelaciones existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
            for(CFAnimales m:cfani){
                
                //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
            String fila =       "<tr>\n" +
                                    "<td><center>"+m.getFechaRegistro()+"</center></td>\n" +
                                    "<td><center>"+new CFAnimales_DAO().consultarAnimal(m.getIdAnimal())+"</enter></td>\n" +
                                    "<td><center>"+m.getCantidad()+"</center></td>\n" +
                                    "<td><center>"+m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getCantidad()*m.getPrecioUnd()+"</center></td>\n" +
                                    "<td><center>"+m.getVidaUtil()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/(m.getVidaUtil()))+"</center></td>\n" +
                                    "<td><center>"+m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio()+"</center></td>\n" +
                                    "<td><center>"+m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><center>"+(((m.getCantidad()*m.getPrecioUnd())/m.getVidaUtil())/m.getAreaServicio())*m.getAreal_lotes()+"</center></td>\n" +
                                    "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarNivelacionNutriente('"+m.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarNivelacionNutriente.jsp?idNivelacion="+m.getId()+"'><img src='../images/x.PNG'></a></td>\n" +
                                "</tr>\n";
            

                tabla+=fila;
            }
            return tabla + "</table>";
            }else{
                return "<label>NO HAY COSTOS FIJOS DE ANIMALES EN LA HACIENDA</label>";
            }
    }
     
     public String getSelectHerramienta() throws SQLException{
         ArrayList<Herramienta> h= new Herramienta_DAO().consultarHerramienta();
        
        String select = "<option value = '-1'>Seleccione</option>"
                + "<option value = '0'>Otro</option>";
        
        for(Herramienta he : h){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(he.getId()));
            option = option.replaceAll("&", he.getHerramienta());
            
            select+= option;
        }
        return select+"\n</select>";
     }
     
    public String getSelectMaquina() throws SQLException{
         ArrayList<Maquina_Equipo> M_E= new Maquina_Equipo_DAO().consultarMaquina();
        
        String select = "<option value = '-1'>Seleccione</option>"
                + "<option value = '0'>Otro</option>";
        
        for(Maquina_Equipo m_e : M_E){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(m_e.getId()));
            option = option.replaceAll("&", m_e.getNombre());
            
            select+= option;
        }
        return select+"\n</select>";
    
    }
    
     public String obtenerFechaDelSistema(){
        
        Calendar c = GregorianCalendar.getInstance();
        SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = form.format(c.getTime());
        
        return fecha;
    }
    
    public boolean registrarCFMaquina(CFMaquinaria CFM,String otraM, String idPropietario) throws SQLException{
      
        if(CFM.getIdMaquina()==0){
            Maquina_Equipo m_e = new Maquina_Equipo();
            m_e.setDocPersonaCreador(idPropietario);
            m_e.setNombre(otraM);
            m_e.setFechaCreacion(this.obtenerFechaDelSistema());
            new Maquina_Equipo_DAO().registrarMaquina(m_e);
            
        }  
     
        return new CFMaquinaria_DAO().registrarCFMaquina(CFM);
       
    }
    
    public String editarCFMaquinaria(String idMaquinaria) throws SQLException{
        
        CFMaquinaria CFM=null;
        //= new CFMaquinaria_DAO().consultaCFMaquinaria(idMaquinaria);
        
        String formulario="<form action=\"validarEditarCFMaquinaria.jsp\" method=\"post\">\n" +
"                                <div class=\"recuadro row blanco\">  \n" +
"\n" +
"                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n" +
"                                    <span aria-hidden=\"true\">&times;</span>\n" +
"                                    <span class=\"sr-only\">Close</span></button>\n" +
"                                    <center><h2 class=\"verde\">Editar costo</h2></center>\n" +
"                                    <br>\n" +
"                            <div class=\"col-sm-8 col-sm-offset-2\">\n" +
"                                <label>Fecha<label class=\"requerido\"></label></label>\n" +
"                                        <input type='date' id='fecha' name='fecha' class=\"form-control\" placeholder='' value='"+CFM.getFechaRegistro()+"' required><br>\n" +
"                                        \n" +
"                                        <label>Maquina o Equipo<label class=\"requerido\">*</label></label>\n" +
"                                        <select id=\"maquina\" name=\"maquina\" class=\"form-control\"onchange=\"habilitarCampoOtraM_editar()\">\n" +
"                                                \n" + this.getSelectMaquina()+
"                                    <div class=\"otraM\"> \n" +
"                                       <label>¿Otra? indicanos cual</label>\n" +
"                                          <div class=\"otraE\">\n" +
"                                            <input type=\"text\" id=\"otraM\" name=\"OtraM\" disable = \"disabled\" class=\"form-control\" placeholder=\"otra Maquina\"required>\n" +
"                                          </div>  \n" +
"                                    </div>     \n" +
"                                        <label>Cantidad</label>\n" +
"                                            <input type=\"text\" id=\"cantidad_e\" name=\"cantidad_e\" class=\"form-control\" placeholder=\"\" onchange=\"cargarSubtotalPreciXUnd_editar(this, '#areaLote_e', '#precioFertilizante_e')\" value = '" +CFM.getCantidad()+ "'required><br>\n" +
"                                        <label>Precio x Unidad</label>\n" +
"                                            <input type=\"number\" id=\"precio_e\" name=\"precio_e\" min=\"0\" class=\"form-control\" placeholder=\"\" required value='"+CFM.getPrecioUnd()+"'><br>\n" +
"                                        <label>Total Compra</label>\n" +
"                                            <input type=\"number\" id=\"total_e\" name=\"total_e\" min=\"0\" class=\"form-control\" placeholder=\"\" readonly value='"+CFM.getSubtotalCompra()+"'><br>\n" +
"                                        <label>Vida Util</label>        \n" +
"                                            <input type=\"number\" id=\"vida\" name=\"vida\" min=\"0\" class=\"form-control\" placeholder=\"\" value='"+CFM.getVidaUtil()+"'><br>\n" +
"                                        <label>Precio x Año de Vida Util</label>\n" +
"                                            <input type=\"number\" id=\"precioVida\" name=\"precioVida\" min=\"0\" class=\"form-control\" placeholder=\"\"readonly value='"+CFM.getPrecioAñoUtil()+"'><br>\n" +
"                                        <label>Area de Servicio</label>\n" +
"                                            <input type=\"text\" id=\"areaServicio\" name=\"areaServicio\"  class=\"form-control\" placeholder=\"\"readonly value='"+CFM.getAreaServicio()+"'><br>\n" +
"                                        <label>Costo por Hectarea al Año</label>\n" +
"                                            <input type=\"number\" id=\"costoAño\" name=\"costoAño\" min=\"0\" class=\"form-control\" placeholder=\"\"readonly value='"+CFM.getCostoHectarea()+"'><br>\n" +
"                                        <label>Area total de Lotes</label>\n" +
"                                            <input type=\"number\" id=\"areaTotal\" name=\"areaTotal\" min=\"0\" class=\"form-control\" placeholder=\"\"readonly value='"+CFM.getAreal_lotes()+"'><br>\n" +
"                                        <label>Subtotal maquina</label>\n" +
"                                            <input type=\"text\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" placeholder=\"\" readonly value='"+CFM.getSubtotalMaquinaria()+"'><br>\n" +
"                                            <center><button class=\"btn btn-success\" type=\"submit\">Guardar <span class=\"glyphicon glyphicon-save\" aria-hidden=\"true\"></span></button><br></center>\n" +
"                                    </div> \n" +
"                            </form>";
        
        
        
        return formulario;
    }
    
    
}
