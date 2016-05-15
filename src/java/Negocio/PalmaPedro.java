/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Dao.CorreccionSuelo_DAO;
import Dao.Enmienda_DAO;
import Dao.Fertilizante_DAO;
import Dao.Insumo_DAO;
import Dao.Item_DAO;
import Dao.Lote_DAO;
import Dao.Otro_DAO;
import Dao.SiembraCobertura_DAO;
import Dao.Unidad_DAO;
import Dao.nivelacionNutrientes_DAO;
import Dto.Correccion_suelo;
import Dto.Enmienda;
import Dto.Fertilizante;
import Dto.Insumo;
import Dto.Item;
import Dto.NivelacionNutriente;
import Dto.Otro;
import Dto.Siembra_cobertura;
import Dto.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import util.BaseDeDatos;

/**
 *
 * @author Pedro
 */
public class PalmaPedro {
   
   //******************************************** ----- Correccion Suelo  ----- **************************************************
 

   public String TablaCorreccionSuelos(String idLote) {

      ArrayList<Correccion_suelo> correcciones = new CorreccionSuelo_DAO().consultarCorreccionesDeUnLote(idLote);
      if (!correcciones.isEmpty()) {
	 String tabla = "<table id='tablaCorreccion' class='table table-bordered tablaP'>\n"
		 + "<tr>\n"
		 + "<th colspan='9'>Correccion de suelos\n"
		 + "</th>\n"
		 + "</tr>\n"
		 + "<tr class='negra'>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;&nbsp;Fecha&nbsp;&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Area&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;Lote&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;Enmienda&nbsp;</center></td>\n"
		 + "<td><center>Cant x Hectarea</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Unidad&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Precio&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;&nbsp;Subtotal&nbsp;&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>Costo &nbsp;&nbsp;Aplicacion&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Acciones&nbsp;&nbsp;&nbsp;</center></td>\n";
	 
	 for (Correccion_suelo c : correcciones) {
             
            String fila = "</tr>\n"
		 + "<tr>\n"
		 + "<td><center>@</center></td>\n"
		 + "<td><center>¬</center></td>\n"
		 + "<td><center>~</center></td>\n"
		 + "<td><center>%</center></td>\n"
		 + "<td><center>°</center></td>\n"
		 + "<td><center>$ £</center></td>\n"
		 + "<td><center>$ Ȼ</center></td>\n"
		 + "<td><center>$ §</center></td>\n"
		 +"<td><center><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarCorreccionSuelo('"+c.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarCorreccionSuelo.jsp?idCorreccion="+c.getId()+"'><img src='../images/x.PNG'></a></center></td>\n" 
		 + "</tr>\n";
             
	    fila = fila.replaceAll("@", c.getFecha_aplicacion());
	    fila = fila.replaceAll("¬", c.getArea_aplicacion());
	    fila = fila.replaceAll("~", c.getIdEnmienda());//aqui traigo el nombre del fertilizante
	    fila = fila.replaceAll("%", c.getCantidad());
	    fila = fila.replaceAll("°", c.getIdUnidad());
	    fila = fila.replaceAll("£", c.getPrecio_enmienda());
	    fila = fila.replaceAll("Ȼ", c.getSubtotal());
	    fila = fila.replaceAll("§", c.getPrecio_aplic_enmienda());
	    //fila = fila.replaceAll("#", c.getId());

	    tabla += fila;
	 }
	 return tabla + "</table>";
      } else {
	 return "<label>NO HAY CORRECCIONES REGISTRADAS AUN EN EL LOTE</label>";
      }
   }

   public String tablaSubtotalesCorreccionSuelos(String idLote) {

      double subtotales = new CorreccionSuelo_DAO().subtotalesCorreccionSuelos(idLote);
      double costosApli = new CorreccionSuelo_DAO().costosAplicacionCorreccionSuelos(idLote);
      double totalCorreccion = subtotales + costosApli;

      String tabla = "<table class='table table-bordered tablaP'>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Subtotal Correccion Suelos</td>\n"
	      + "<td>&nbsp;$&nbsp;" + subtotales + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Subtotal Costos Aplicacion</td>\n"
	      + "<td>&nbsp;$&nbsp;" + costosApli + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Total Correccion suelos</td>\n"
	      + "<td>&nbsp;$&nbsp;" + totalCorreccion + "</td>\n"
	      + "</tr>\n"
	      + "</table>";
      return tabla;
   }
   
   public boolean registrarCorreccion(Correccion_suelo c, String nuevaEnmienda, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(c.getArea_aplicacion(), c.getIdLote())){            
        
        if(c.getIdEnmienda().equals("0")){
            Enmienda e = new Enmienda();
            e.setDocumentoPersona(docPropietario);
            e.setEnmienda(nuevaEnmienda);
            e.setFecha(this.obtenerFechaDelSistema());
            new Enmienda_DAO().registrarNuevaEnmienda(e);
            
            Enmienda reg = new Enmienda_DAO().consultarEnmiendaPorNombre(nuevaEnmienda);
            c.setIdEnmienda(reg.getId());
        }
        
        if(c.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            c.setIdUnidad(uni.getId());
        }
	c.setFecha_registro(this.obtenerFechaDelSistema());
       
        return new CorreccionSuelo_DAO().registrarCorreccion(c);
        }
        return false;
    }
      
   
     public String FormularioEditarCorreccionSuelo(String idCorreccion) throws SQLException{
        
        Correccion_suelo n = new CorreccionSuelo_DAO().consultarCorreccion(idCorreccion);
        
        String formulario = "<form action='../jsp/validarEditarCorreccionSuelo.jsp' method='post'>\n"+
                                "<div class='recuadro row blanco'>\n"+
                                    "<button type='button' class='close' data-dismiss='modal'>\n"+
                                    "<span aria-hidden='true'>&times;</span>\n"+
                                    "<span class='sr-only'>Close</span></button>\n"+
                                    "<center><h2 class='verde'>Actualizar Correccion de Suelo</h2></center>\n"+
                                    "<br>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Fecha<label class='requerido'></label></label>\n"+
                                        "<input type='date' id='fecha_aplicacion' name='fecha_aplicacion' class='form-control' placeholder='' value='"+n.getFecha_aplicacion()+"'required><br>\n"+
                                        "<label>Area Aplicacion<label class=\"requerido\"></label></label>\n"+
                                        "<input type='text' id='area_aplicacion_e' name='area_aplicacion_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#cantidad_e', '#precio_enmienda_e')\" value='"+n.getArea_aplicacion()+"'required><br>\n"+
                                        "<label>Enmienda<label class='requerido'>*</label></label>\n"+
                                        "<SELECT name=\"idF\" id=\"idF\" class=\"form-control\" onchange=\"habilitarCampoOtroF_editar()\" >\n"+
                                        this.selectEnmiendas()+
                                        "<br>\n"+
                                    "<div class='otroF'>\n"+
                                        "<label>¿Otro? indicanos cual</label>\n"+
                                            "<input type='text' id='otroFer' name='otroFer' disabled = 'disabled' class='form-control' placeholder='otra Enmienda' required><br>\n"+
                                     "</div>\n"+
                                "</div>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Cantidad por hectarea</label>\n"+
                                            "<input type='text' id='cantidad_e' name='cantidad_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#area_aplicacion_e', '#precio_enmienda_e')\" value='"+n.getCantidad()+"' required><br>\n"+
                                        "<label>Unidad<label class='requerido'>*</label></label>\n"+
                                        "<SELECT onchange=\"habilitarCampoOtroU_editar()\" name=\"idU\" id=\"idU\" class=\"form-control\">\n"+
                                        this.selectUnidades("peso")+
                                        "<br>\n"+
                                    "<div class=otraU'>\n"+
                                        "<label>¿Otra? indicanos cual</label>\n"+
                                            "<input type='text' id='otroUni' name='otroUni' disabled = 'disabled' class='form-control' placeholder='otra unidad' required><br>\n"+
                                    "</div>\n"+
                                        "<label>Precio por unidad de la Enmienda (En pesos)</label>\n"+
                                        "<input type='text' id='precio_enmienda_e' name='precio_enmienda_e' class='form-control' onchange=\"cargarSubtotal3_editar(this, '#area_aplicacion_e', '#cantidad_e')\" value='"+n.getPrecio_enmienda()+"' placeholder=''><br>\n"+
                                        "<label>Subtotal</label>\n"+
                                         "<input type='text' id='subtotal_e' name='subtotal_e' class='form-control' placeholder='' value='"+n.getSubtotal()+"' readonly><br>\n"+
                                          "<label>Costo Aplicacion</label>\n"+
                                         "<input type='text' id='precioAplicacion_e' name='precioAplicacion_e' class='form-control' onclick=\"cargarCostoAplicacion_editar()\" value='"+n.getPrecio_aplic_enmienda()+"' placeholder=''><br>\n"+
                                         "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+n.getId()+"' /></label>"+
                                "</div>\n"+
                                "<div class='col-sm-12'>\n"+
                                "<center><input class='btn btn-success' type='submit' onclick=\"loading(load)\" value='Guardar'><br></center>\n"+
                                 "</div>\n"+
                                "</div> \n"+
                            "</form>";
        
        return formulario;
    }
     
     
     public boolean editarCorreccionSuelo(Correccion_suelo n, String nuevaEnmienda, String nuevaUnidad, String docPropi) throws SQLException{
         System.out.println("OTROS!!---> "+nuevaEnmienda+" "+nuevaUnidad);
        System.out.println("DATOS --------------------------------------------> "+n.getArea_aplicacion()+" "+n.getIdLote());
        if(new Lote_DAO().esMenorElAreaNueva(n.getArea_aplicacion(), n.getIdLote())){
        
        if(n.getIdEnmienda().equals("0")){
           Enmienda f = new Enmienda();
            f.setDocumentoPersona(docPropi);
            f.setEnmienda(nuevaEnmienda);
            f.setFecha(this.obtenerFechaDelSistema());
            new Enmienda_DAO().registrarNuevaEnmienda(f);
            
            Enmienda reg = new Enmienda_DAO().consultarEnmiendaPorNombre(nuevaEnmienda);
            n.setIdEnmienda(reg.getId());
        }
        
        if(n.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropi);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropi);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            n.setIdUnidad(uni.getId());
        }
        //n.setFecha_aplicacion(this.obtenerFechaDelSistema());
        
        return new CorreccionSuelo_DAO().actualizarCorreccion(n);
        }
        return false;
    }
     
     
      public boolean eliminarCorreccionSuelo(String idCorreccion){
        
        return new CorreccionSuelo_DAO().eliminarCorreccion(idCorreccion);
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
    * crea un select HTML con todas las enmiendas de la BD
     * @return HTML del SELECT en formato cadena
     */
     public String selectEnmiendas() throws SQLException{
        
        ArrayList<Enmienda> enmiendas= new Enmienda_DAO().consultarEnmiendas();
        
        String select = "<option value = 'n'>Seleccione</option>"
                + "<option value = '0'>Otra</option>"; 
        
        for(Enmienda e: enmiendas){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(e.getId()));
            option = option.replaceAll("&",e.getEnmienda());
            
            select+= option;
        }
        return select+"\n</select>";
    }
//******************************************** ----- Siembra Cobertura  ----- **************************************************


   public String TablaSiembraCoberturas(String idLote) {

      ArrayList<Siembra_cobertura> siembras = new SiembraCobertura_DAO().consultarCoberturas(idLote);
      if (!siembras.isEmpty()) {
	 String tabla = "<table id='tablaCobertura'class='table table-bordered tablaP'>\n"
		 + "<tr>\n"
		 + "<th colspan='9'>Siembra de Cobertura\n"
		 + "</th>\n"
		 + "</tr>\n"
		 + "<tr class='negra'>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;&nbsp;Fecha&nbsp;&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;Area&nbsp; &nbsp;Lote&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Insumo&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>Cant x Hectarea</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;&nbsp;Unidad&nbsp;&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>Precio insumo x unidad</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;&nbsp;Subtotal&nbsp;&nbsp;&nbsp;</center></td>\n"
		 + "<td><center>&nbsp;&nbsp;Acciones&nbsp;&nbsp;</center></td>\n";

	 for (Siembra_cobertura s : siembras) {
             
              String fila = "</tr>\n"
		 + "<tr>\n"
		 + "<td><center>@</center></td>\n"
		 + "<td><center>¬</center></td>\n"
		 + "<td><center>~</center></td>\n"
		 + "<td><center>%</center></td>\n"
		 + "<td><center>°</center></td>\n"
		 + "<td><center>$ £</center></td>\n"
		 + "<td><center>$ Ȼ</center></td>\n"
		 +"<td><center><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarSiembraCobertura('"+s.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarSiembraCobertura.jsp?idCobertura="+s.getId()+"'>&nbsp;<img src='../images/x.PNG'></a></center></td>\n" 
		 + "</tr>\n";
             
	    fila = fila.replaceAll("@", s.getFecha_siembra());
	    fila = fila.replaceAll("¬", s.getArea_lote());
	    fila = fila.replaceAll("~", s.getIdInsumo());//aqui traigo el nombre del fertilizante
	    fila = fila.replaceAll("%", s.getCantidad());
	    fila = fila.replaceAll("°", s.getIdUnidad());
	    fila = fila.replaceAll("£", s.getPrecioUnidad());
	    fila = fila.replaceAll("Ȼ", s.getSubtotal());
	    //fila = fila.replaceAll("#", s.getId());

	    tabla += fila;
	 }
	 return tabla + "</table>";
      } else {
	 return "<label>NO HAY COBERTURAS REGISTRADAS AUN EN EL LOTE</label>";
      }

   }
    public String tablaSubtotalesSiembraCobertura(String idLote) {

      double subtotales = new SiembraCobertura_DAO().subtotalesSiembraCobertura(idLote);
      double costosApli = new SiembraCobertura_DAO().costosAplicacionSiembraCobertura(idLote);
      double totalCobertura = subtotales + costosApli;

      String tabla = "<table class='table table-bordered tablaP'>\n"
	      + "<tr>\n"
	      + "<td class='negra'><center>Subtotal Siembra de coberturas</center></td>\n"
	      + "<td>&nbsp;$&nbsp;" + subtotales + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'><center>Subtotal Costos Aplicacion</center></td>\n"
	      + "<td>&nbsp;$&nbsp;" + costosApli + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'><center>Total Siembra Coberturas</center></td>\n"
	      + "<td>&nbsp;$&nbsp;" + totalCobertura + "</td>\n"
	      + "</tr>\n"
	      + "</table>";
      return tabla;
   }
      public boolean registrarCobertura(Siembra_cobertura c, String nuevoInsumo, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(c.getArea_lote(), c.getIdLote())){
            
        
        if(c.getIdInsumo().equals("0")){
            Insumo e = new Insumo();
            e.setDocumentoPersona(docPropietario);
            e.setInsumo(nuevoInsumo);
            e.setFecha_creacion(this.obtenerFechaDelSistema());
            new Insumo_DAO().registrarNuevoInsumo(e);
            
            Insumo reg = new Insumo_DAO().consultarInsumoPorNombre(nuevoInsumo);
            c.setIdInsumo(reg.getId());
        }
        
        if(c.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            c.setIdUnidad(uni.getId());
        }
	c.setFecha_registro(this.obtenerFechaDelSistema());
      
        return new SiembraCobertura_DAO().registrarCobertura(c);
        }
        return false;
        
    }
        public boolean editarCobertura(Siembra_cobertura c, String nuevoInsumo, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(c.getArea_lote(), c.getIdLote())){
            
        
        if(c.getIdInsumo().equals("0")){
            Insumo e = new Insumo();
            e.setDocumentoPersona(docPropietario);
            e.setInsumo(nuevoInsumo);
            e.setFecha_creacion(this.obtenerFechaDelSistema());
            new Insumo_DAO().registrarNuevoInsumo(e);
            
            Insumo reg = new Insumo_DAO().consultarInsumoPorNombre(nuevoInsumo);
            c.setIdInsumo(reg.getId());
        }
        
        if(c.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            c.setIdUnidad(uni.getId());
        }
	c.setFecha_registro(this.obtenerFechaDelSistema());
      
        
        return new SiembraCobertura_DAO().actualizarCobertura(c);
        }
        return false;
    }
       public String FormularioEditarSiembraCobertura(String idCobertura) throws SQLException{
        
        Siembra_cobertura n = new SiembraCobertura_DAO().consultarCobertura(idCobertura);
        
        String formulario = "<form action='../jsp/validarEditarSiembraCobertura.jsp' method='post'>\n"+
                                "<div class='recuadro row blanco'>\n"+
                                    "<button type='button' class='close' data-dismiss='modal'>\n"+
                                    "<span aria-hidden='true'>&times;</span>\n"+
                                    "<span class='sr-only'>Close</span></button>\n"+
                                    "<center><h2 class='verde'>Actualizar Siembra de Cobertura</h2></center>\n"+
                                    "<br>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Fecha<label class='requerido'></label></label>\n"+
                                        "<input type='date' id='fecha_siembra' name='fecha_siembra' class='form-control' placeholder='' value='"+n.getFecha_siembra()+"'required><br>\n"+
                                        "<label>Area Siembra<label class=\"requerido\"></label></label>\n"+
                                        "<input type='text' id='area_lote_e' name='area_lote_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#cantidad_e', '#precioUnidad_e')\" value='"+n.getArea_lote()+"'required><br>\n"+
                                        "<label>Insumo<label class='requerido'>*</label></label>\n"+
                                        "<SELECT name=\"idF\" id=\"idF\" class=\"form-control\" onchange=\"habilitarCampoOtroF_editar()\" >\n"+
                                        this.selectinsumos()+
                                        "<br>\n"+
                                    "<div class='otroF'>\n"+
                                        "<label>¿Otro? indicanos cual</label>\n"+
                                            "<input type='text' id='otroF' name='otroF' disabled = 'disabled' class='form-control' placeholder='otro insumo' required><br>\n"+
                                     "</div>\n"+
                                "</div>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Cantidad por hectarea</label>\n"+
                                            "<input type='text' id='cantidad_e' name='cantidad_e' onchange=\"cargarSubtotal3_editar(this, '#area_lote_e', '#precioUnidad_e')\" class='form-control' placeholder='' value='"+n.getCantidad()+"' required><br>\n"+
                                        "<label>Unidad<label class='requerido'>*</label></label>\n"+
                                        "<SELECT onchange=\"habilitarCampoOtroU_editar()\" name=\"idU\" id=\"idU\" class=\"form-control\">\n"+
                                        this.selectUnidades("peso")+
                                        "<br>\n"+
                                    "<div class=otraU'>\n"+
                                        "<label>¿Otra? indicanos cual</label>\n"+
                                            "<input type='text' id='otroU' name='otroU' disabled = 'disabled' class='form-control' placeholder='otra unidad' required><br>\n"+
                                    "</div>\n"+
                                        "<label>Precio por unidad del Insumo (En pesos)</label>\n"+
                                        "<input type='text' id='precioUnidad_e' name='precioUnidad_e' class='form-control' value='"+n.getPrecioUnidad()+"' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#area_lote_e', '#cantidad_e')\" required><br>\n"+
                                        "<label>Subtotal</label>\n"+
                                         "<input type='text' id='subtotal_e' name='subtotal_e' class='form-control' placeholder='' value='"+n.getSubtotal()+"' readonly required><br>\n"+
                                          "<label>Costo Aplicacion</label>\n"+
                                         "<input type='text' id='precioAplicacion_e' name='precioAplicacion_e' class='form-control' onclick=\"cargarCostoAplicacion_editar()\" value='"+n.getPrecioAplic()+"' placeholder='' required><br>\n"+
                                         "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+n.getId()+"' /></label>"+
                                "</div>\n"+
                                "<div class='col-sm-12'>\n"+
                                "<center><input class='btn btn-success' type='submit' onclick=\"loading(load)\" value='Guardar'><br></center>\n"+
                                 "</div>\n"+
                                "</div> \n"+
                            "</form>";
        
        return formulario;
    }
         public boolean eliminarSiembraCobertura(String idCobertura){
        
        return new SiembraCobertura_DAO().eliminarCobertura(idCobertura);
    }
      /**
    * crea un select HTML con todas los insumos de la BD
     * @return HTML del SELECT en formato cadena
     */
     public String selectinsumos() throws SQLException{
        
        ArrayList<Insumo> insumos= new Insumo_DAO().consultarInsumos();
        
        String select = "<option value = 'n'>Seleccione</option>"
                + "<option value = '0'>Otro</option>"; 
        
        for(Insumo i: insumos){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(i.getId()));
            option = option.replaceAll("&",i.getInsumo());
            
            select+= option;
        }
        return select+"\n</select>";
    }
   //******************************************** ----- Otro  ----- **************************************************
  

   public String TablaOtros(String idLote) {

      //AQUI TRAIGO TODAS LAS NIVELACIONES EN UNA LISTA
      ArrayList<Otro> otros = new Otro_DAO().consultarOtros(idLote);
      if (!otros.isEmpty()) {
	 String tabla = "<table class='table table-bordered tablaP'>\n"
		 + "<tr>\n"
		 + "<th colspan='9'>Otros Costos\n"
		 + "</th>\n"
		 + "</tr>\n"
		 + "<tr class='negra'>\n"
		 + "<td><center>Fecha</center></td>\n"
		 + "<td><center>Area Lote</center></td>\n"
		 + "<td><center>Item</center></td>\n"
		 + "<td><center>Cant x Hectarea</center></td>\n"
		 + "<td><center>Unidad</center></td>\n"
		 + "<td><center>Precio</center></td>\n"
		 + "<td><center>Subtotal</center></td>\n"
		 + "<td><center>Acciones</center></td>\n";

	 

	 //recorro todas las nivelaciones existentes y las reemplazo por los valores correspondientes, para eso empleo el metodo q proporciona la clase String (replaceAll)
	 for (Otro o : otros) {
             
             //en esta cadena se va a hacer el reemplazo.. cada nueva nivelacion q se traiga cuando se recorra en el for de abajo, se va a reemplazar por cada simbolo
	 String fila = "</tr>\n"
		 + "<tr>\n"
		 + "<td><center>@</center></td>\n"
		 + "<td><center>¬</center></td>\n"
		 + "<td><center>~</center></td>\n"
		 + "<td><center>%</center></td>\n"
		 + "<td><center>°</center></td>\n"
		 + "<td><center>$ £</center></td>\n"
		 + "<td><center>$ Ȼ</center></td>\n"
		 +"<td><center><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"FormEditarOtros('"+o.getId()+"')\"><img src='../images/lapiz.PNG'></button><a href='../jsp/validarEliminarOtros.jsp?idOtro="+o.getId()+"'>&nbsp;<img src='../images/x.PNG'></a></center></td>\n" 
		 + "</tr>\n";
             
	    fila = fila.replaceAll("@", o.getFecha_activacion());
	    fila = fila.replaceAll("¬", o.getAreaLote());
	    fila = fila.replaceAll("~", o.getIdItem());//aqui traigo el nombre del fertilizante
	    fila = fila.replaceAll("%", o.getCantidadItem());
	    fila = fila.replaceAll("°", o.getIdUnidad());
	    fila = fila.replaceAll("£", o.getPrecioItem());
	    fila = fila.replaceAll("Ȼ", o.getSubtotal());

	    //fila = fila.replaceAll("#", o.getId());

	    tabla += fila;
	 }
	 return tabla + "</table>";
      } else {
	 return "<label>NO HAY OTROS COSTOS REGISTRADOS AUN EN EL LOTE</label>";
      }

   }
 public String tablaSubtotalesOtros(String idLote) {

      double subtotales = new Otro_DAO().subtotalesOtro(idLote);
      double costosApli = new Otro_DAO().costosAplicacionOtro(idLote);
      double totalOtros = subtotales + costosApli;

      String tabla = "<table class='table table-bordered tablaP'>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Subtotal Otros</td>\n"
	      + "<td>&nbsp;$&nbsp;" + subtotales + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Subtotal Costos Aplicacion</td>\n"
	      + "<td>&nbsp;$&nbsp;" + costosApli + "</td>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<td class='negra'>Total Otros</td>\n"
	      + "<td>&nbsp;$&nbsp;" + totalOtros + "</td>\n"
	      + "</tr>\n"
	      + "</table>";
      return tabla;
   }
  public boolean registrarOtro(Otro c, String nuevoItem, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(c.getAreaLote(), c.getIdLote())){
            
        if(c.getIdItem().equals("0")){
            Item e = new Item();
            e.setDocumentoPersona(docPropietario);
            e.setNombre(nuevoItem);
            e.setFecha(this.obtenerFechaDelSistema());
            new Item_DAO().registrarNuevoItem(e);
            
            Item reg = new Item_DAO().consultarItemPorNombre(nuevoItem);
            c.setIdItem(reg.getId());
        }
        
        if(c.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            c.setIdUnidad(uni.getId());
        }
	c.setFecha_registro(this.obtenerFechaDelSistema());
      
        
        return new Otro_DAO().registrarOtro(c);
        }
        return false;
    }
  public boolean editarOtro(Otro c, String nuevoItem, String nuevaUnidad, String docPropietario) throws SQLException{
        
        if(new Lote_DAO().esMenorElAreaNueva(c.getAreaLote(), c.getIdLote())){
            
        
        if(c.getIdItem().equals("0")){
            Item e = new Item();
            e.setDocumentoPersona(docPropietario);
            e.setNombre(nuevoItem);
            e.setFecha(this.obtenerFechaDelSistema());
            new Item_DAO().registrarNuevoItem(e);
            
            Item reg = new Item_DAO().consultarItemPorNombre(nuevoItem);
            c.setIdItem(reg.getId());
        }
        
        if(c.getIdUnidad().equals("0")){
            Unidad u = new Unidad();
            u.setTipo("peso");
            u.setUnidad(nuevaUnidad);
            u.setDocumentoPersona(docPropietario);
            new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);
            
            Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
            c.setIdUnidad(uni.getId());
        }
	c.setFecha_registro(this.obtenerFechaDelSistema());
      
        
        return new Otro_DAO().actualizarOtro(c);
        }
        return false;
    }
    public String FormularioEditarOtros(String idOtro) throws SQLException{
        
        Otro n = new Otro_DAO().consultarOtro(idOtro);
        
        String formulario = "<form action='../jsp/validarEditarOtros.jsp' method='post'>\n"+
                                "<div class='recuadro row blanco'>\n"+
                                    "<button type='button' class='close' data-dismiss='modal'>\n"+
                                    "<span aria-hidden='true'>&times;</span>\n"+
                                    "<span class='sr-only'>Close</span></button>\n"+
                                    "<center><h2 class='verde'>Actualizar Otros</h2></center>\n"+
                                    "<br>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Fecha<label class='requerido'></label></label>\n"+
                                        "<input type='date' id='fecha_activacion' name='fecha_activacion' class='form-control' placeholder='' value='"+n.getFecha_activacion()+"'required><br>\n"+
                                        "<label>Area de Lote<label class=\"requerido\"></label></label>\n"+
                                        "<input type='text' id='areaLote_e' name='areaLote_e' class='form-control' placeholder='' onchange=\"cargarSubtotal3_editar(this, '#cantidadItem_e', '#precioItem_e')\" value='"+n.getAreaLote()+"'required><br>\n"+
                                        "<label>Items<label class='requerido'>*</label></label>\n"+
                                        "<select name=\"idI\" id=\"idI\" class=\"form-control\" onchange=\"habilitarCampoOtroI_editar()\" >"+
                                        this.selectItems()+
                                        "<br>\n"+
                                    "<div class='otroF'>\n"+
                                        "<label>¿Otro? indicanos cual</label>\n"+
                                            "<input type='text' id='otroIte' name='otroIte' disabled = 'disabled' class='form-control' placeholder='otro item' required><br>\n"+
                                     "</div>\n"+
                                "</div>\n"+
                                "<div class='col-sm-6'>\n"+
                                        "<label>Cantidad por hectarea</label>\n"+
                                            "<input type='text' id='cantidadItem_e' name='cantidadItem_e' class='form-control' onchange=\"cargarSubtotal3_editar(this, '#areaLote_e', '#precioItem_e')\" placeholder='' value='"+n.getCantidadItem()+"' required><br>\n"+
                                        "<label>Unidad<label class='requerido'>*</label></label>\n"+
                                        "<SELECT name=\"idU\" id=\"idU\" class=\"form-control\" onchange=\"habilitarCampoOtroU_editar()\">"+
                                        this.selectUnidades("peso")+
                                        "<br>\n"+
                                    "<div class=otraU'>\n"+
                                        "<label>¿Otra? indicanos cual</label>\n"+
                                            "<input type='text' id='otroUni' name='otroUni' disabled = 'disabled' class='form-control' placeholder='otra unidad' required><br>\n"+
                                    "</div>\n"+
                                        "<label>Precio por unidad del item (En pesos)</label>\n"+
                                        "<input type='text' id='precioItem_e' name='precioItem_e' class='form-control' onchange=\"cargarSubtotal3_editar(this, '#areaLote_e', '#cantidadItem_e') value='"+n.getPrecioItem()+"' placeholder=''><br>\n"+
                                        "<label>Subtotal</label>\n"+
                                         "<input type='text' id='subtotal_e' name='subtotal_e' class='form-control' placeholder='' value='"+n.getSubtotal()+"' readonly><br>\n"+
                                          "<label>Costo Aplicacion</label>\n"+
                                         "<input type='text' id='precioAplicacion_e' name='precioAplicacion_e' class='form-control' onclick=\"cargarCostoAplicacion_editar()\" value='"+n.getPrecioAplicacion()+"' placeholder='' required><br>\n"+
                                         "<label><input type=\"hidden\" id=\"id\" name=\"id\" class=\"form-control\" value='"+n.getId()+"' /></label>"+
                                "</div>\n"+
                                "<div class='col-sm-12'>\n"+
                                "<center><input class='btn btn-success' type='submit' value='Guardar'><br></center>\n"+
                                 "</div>\n"+
                                "</div> \n"+
                            "</form>";
        
        return formulario;
    }
      public boolean eliminarOtro(String idOtro){
        
        return new Otro_DAO().eliminarOtro(idOtro);
    }
 
    /**
    * crea un select HTML con todas los items de la BD
     * @return HTML del SELECT en formato cadena
     */
     public String selectItems() throws SQLException{
        
        ArrayList<Item> items= new Item_DAO().consultarItems();
        
        String select = "<option value = 'n'>Seleccione</option>"
                + "<option value = '0'>Otro</option>";
        
        for(Item i: items){
            String option = "\n<option value = '¬'>&</option>";
            option = option.replaceAll("¬", String.valueOf(i.getId()));
            option = option.replaceAll("&",i.getNombre());
            
            select+= option;
        }
        return select+"\n</select>";
    }
}
