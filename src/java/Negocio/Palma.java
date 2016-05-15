/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dao.*;
import Dto.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import ufps.util.JCrypt;

/**
 *
 * @author mauricio uribe
 */
public class Palma {

   public Palma() {
   }

//------------1° MODULO-----------------//
   // <editor-fold desc="ADMINISTRADOR">
   /**
    * Metodo para consultar por un criterio dado por el Administrador del
    * sistema
    *
    * @param criterio tipo de busqueda
    * @param palabra dato a consultar
    * @param dpto dato a consultar
    * @param mun dato a consultar
    * @param tipo si es busqueda por palmicultor o hacienda
    * @return codigo HMTL con el resultado de las consulta
    */
   public String consultarPorCriterio(int criterio, String palabra, String dpto, String mun, String tipo) {
      if (tipo.equals("pal")) {

	 return (this.consultarPalmicultorPorCriterio(criterio, palabra, dpto, mun));
      } else {
	 return (this.consultarHaciendaPorCriterio(criterio, palabra, dpto, mun));
      }
   }

   /**
    * Metodo para realizar consultas de palmicultor por criterios de busqueda
    * hechas por el Administrador del sistema
    *
    * @param criterio tipo de busqueda de la hacienda
    * @param palabra valor a consultar
    * @param dpto valor a consultar
    * @param mun valor a consultar
    * @return Codigo HTML con la infomacion solicitada
    */
   private String consultarPalmicultorPorCriterio(int criterio, String palabra, String dpto, String mun) {

      ArrayList<String> datos = new Administrador_DAO().preguntaDeConsulta(criterio, palabra, dpto, mun);
      System.out.println("NADAAA " + datos.get(0).toString());
      if (!datos.get(0).equals("nada")) {
	 System.out.println("HOLA MUNFO");
	 String th = "<table class='table table-bordered tablaP'>\n<tr>\n"
		 + "<th><label>Documento</label></th>\n"
		 + "<th><label>Nombre</label></th>\n"
		 + "<th><label>&nbsp;Municipio&nbsp;</label></th>\n"
		 + "<th><label>&nbsp;Departamento&nbsp;</label></th>\n"
		 + "<th><label>&nbsp;Estado&nbsp;</label></th>\n"
		 + "</tr>";
	 String td = "\n<tr>\n";

	 //DATOS CONSULTA (0-numero_documento,1-nombre,2-apellidos,3-municipio_id,4-activo)
	 for (String palmi : datos) {

	    String info[] = palmi.split("&");
	    String ced = "<td><label>" + info[0] + "</label></td>\n";
	    String nom = "<td><lable><a href=\"detallePalmicultor.jsp?id=" + info[0] + "\"> " + info[1] + " " + info[2] + "</a></label></td>\n";
	    String muni = "<td><label>" + this.obtenerNombre(1, info[3]) + "</label></td>\n";
	    String depto = "<td><label>" + this.obtenerNombre(0, info[3]) + "</label></td>\n";
	    String est = "";
	    if (info[4].equalsIgnoreCase("1")) {
	       est = "<td><label>Activo</label></td>\n";
	    } else {
	       est = "<td><label>Desactivado</label></td>\n";
	    }

	    td += ced + nom + muni + depto + est + "\n</tr>";
	 }
	 System.out.println(th + td + "\n</table>");
	 return (th + td + "\n</table>");
      } else {
	 System.out.println("HOLA NADAAAA");
	 return "<center><label> NO SE HAN ENCONTRADO DATOS DE PROPIETARIOS REFERENTES A LA CONSULTA.. VERIFIQUE Y VUELVA INTENTAR</label></center>";
      }
   }

   /**
    * Metodo para realizar consultas por criterios de busqueda
    *
    * @param criterio tipo de busqueda de la hacienda
    * @param palabra valor a consultar
    * @param dpto valor a consultar
    * @param mun valor a consultar
    * @return Cadena de datos con la infomacion solicitada
    */
   private String consultarHaciendaPorCriterio(int criterio, String palabra, String dpto, String mun) {
      System.out.println(criterio + "-" + palabra + "-" + dpto + "-" + mun);

      ArrayList<String> datos = new Administrador_DAO().consultarHaciendaPorCriterio(criterio, palabra, dpto, mun);

      if (!datos.get(0).equals("nada")) {
	 String table = "<table class = 'table table-bordered tablaP'>\n<thead>\n<tr>\n"
		 + "<th><label>Cédula Catastral</label></th>\n"
		 + "<th><label>&nbsp;Hacienda&nbsp;</label></th>\n"
		 + "<th><label>&nbsp;Municipio&nbsp;</label></th>\n"
		 + "<th><label>&nbsp;Departamento&nbsp;</label></th>\n"
		 + "<th><label>Nucleo Palmero</label></th>\n"
		 + "<th><label>&nbsp;Palmicultor&nbsp;</label></th>\n"
		 + "<th><label>Documento Palmicultor</label></th>\n"
		 + "<th><label>Estado Hacienda</label></th>\n"
		 + "</tr>\n</thead>\n";

	 //DATOS CONSULTA (0-cedula_catastral,1-id,2-nombre,3-municipio_id,4-nucleo_palmero,5-propietario_persona_numero_documento,6-activo)
	 for (String hacienda : datos) {
	    String td = "<tr>\n";
	    String info[] = hacienda.split("&");
	    String ced = "<td><label>" + info[0] + "</label></td>\n";
	    String hac = "<td><lable><a href=\"detalleHacienda.jsp?hacienda=" + info[1] + "\"> " + info[2] + "</a></label></td>\n";
	    String muni = "<td><label>" + this.obtenerNombre(1, info[3]) + "</label></td>\n";
	    String depto = "<td><label>" + this.obtenerNombre(0, info[3]) + "</label></td>\n";
	    String nuc = "<td><label>" + info[4] + "</label></td>\n";
	    String npal = "<td><label>" + this.obtenerNombre(2, info[5]) + "</label></td>\n";
	    String dpal = "<td><label>" + info[5] + "</label></td>\n";
	    String est = "";
	    if (info[6].equalsIgnoreCase("1")) {
	       est = "<td><label>Activo</label></td>";
	    } else {
	       est = "<td><label>Desactivado</label></td>";
	    }

	    td += ced + hac + muni + depto + nuc + npal + dpal + est + "\n</tr>";
	    table += td;
	 }

	 table += "\n</table>";

	 return (table);
      } else {

	 return "<center><label> NO SE HAN ENCONTRADO DATOS DE HACIENDA REFERENTES A LA CONSULTA.. INTENTE NUEVAMENTE</label></center>";
      }
   }

   /**
    *
    * @param num_documento
    * @return
    */
   public String detallePalmicultor(String num_documento) {
      try {

	 String infoPropietario = new Propietario_DAO().consultarInformacionPropietario(num_documento);
	 System.out.println("INFO PRO ---> " + infoPropietario);
	 String datos[] = infoPropietario.split("¬");
	 System.out.println(datos[0]);
	 String html = "<div class=\"col-sm-4\">\n"
		 + "<label>Tipo Documento</label>\n"
		 + "<input type=\"text\" id=\"tipo_d\" name=\"tipo_d\" class=\"form-control\" value='" + datos[0] + "' readonly>\n"
		 + "<label>Número Documento</label>\n"
		 + "<input type=\"text\" id=\"numero\" name=\"numero\" class=\"form-control\" value='" + datos[1] + "' readonly>\n"
		 + "<label>Nombre</label>\n"
		 + "<input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\" value='" + datos[2] + "' readonly>\n"
		 + "<label>Apellido</label>\n"
		 + "<input type=\"text\" id=\"apellidos\" name=\"apellidos\" class=\"form-control\" value='" + datos[3] + "' readonly>\n"
		 + "<label>Género</label>\n"
		 + "<input type=\"text\" id=\"genero\" name=\"genero\" class=\"form-control\" value='" + datos[4] + "' readonly>\n"
		 + "<label>Direccion</label>\n"
		 + "<input type=\"text\" id=\"direccion\" name=\"direccion\" class=\"form-control\" value='" + datos[5] + "' readonly>\n"
		 + "<label>Departamento</label>\n"
		 + "<input type=\"text\" id=\"departamento\" name=\"departamento\" class=\"form-control\" value='" + datos[6] + "' readonly>\n"
		 + "</div>"
		 + "<div class=\"col-sm-4\">\n"
		 + "<label>Municipio</label>\n"
		 + "<input type=\"text\" id=\"municipio\" name=\"municipio\" class=\"form-control\" value='" + datos[7] + "' readonly>\n"
		 + "<label>Celular</label>\n"
		 + "<input type=\"number\" id=\"celular\" name=\"celular\" class=\"form-control\" value='" + datos[9] + "' readonly>\n"
		 + "<label>Telefono</label>\n"
		 + "<input type=\"number\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value='" + datos[8] + "' readonly>\n"
		 + "<label>Correo Electronico</label>\n"
		 + "<input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" value='" + datos[10] + "' readonly>\n"
		 + "<label>Zona Palmera</label>\n"
		 + "<input type=\"text\" id=\"zonaPalmera\" name=\"zonaPalmera\" class=\"form-control\" value='" + datos[11] + "' readonly>\n"
		 + "<label>Cedula Palmera</label>\n"
		 + "<input type=\"text\" id=\"cedulaPalmera\" name=\"cedulaPalmera\" class=\"form-control\" value='" + datos[12] + "' readonly>\n"
		 + "<label>Estado</label>\n"
		 + "<input type=\"text\" id=\"estado\" name=\"estado\" class=\"form-control\" value='" + datos[13] + "' readonly>\n"
		 + "</div><br/>"
		 + "<div class='col-xs-3'></div>";

	 if (datos[13].equalsIgnoreCase("activo")) {
	    html += "<div class=\"col-xs-9\"><br>\n"
		    + "<div class='col-sm-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = 'detalleEditarPalmi.jsp?id=" + datos[1] + "'\">Editar</button></center></div>"
		    + "<div class='col-md-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = '#'\" disabled>Reactivar Palmicultor</button></center></div>"
		    + "<div class='col-md-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = 'activarPalmi.sjp?id=" + datos[1] + "'&tipo='false'\" >Desactivar Palmicultor</button></center></div>"
		    + "</div>\n";
	 } else {
	    html += "<div class=\"col-xs-9\"><br>\n"
		    + "<div class='col-sm-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = 'detalleEditarPalmi.jsp?id=" + datos[1] + "'\">Editar</button></center></div>"
		    + "<div class='col-md-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = 'activarPalmi.jsp?id=" + datos[1] + "&tipo='true'\">Reactivar Palmicultor</button></center></div>"
		    + "<div class='col-md-3'><center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = ''\" disabled>Desactivar Palmicultor</button></center></div>"
		    + "</div>\n";
	 }

	 return html;

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return "";
   }

   /**
    * Metodo para activar o desactivar un Palmicultor desde Administrador
    *
    * @param id numero de documento palmi
    * @param tipo activacion o desactivacion
    * @return true de la actualizacion
    */
   public boolean activarPalmicultor(String id, String tipo) {
      return (new Administrador_DAO().activarPalmicultor(id, tipo));
   }

   /**
    * Metodo para editar informacion en detalle de un palmicultor
    *
    * @param num_documento numero del docuemnto del palmicultor
    * @return codigo HTML con la informacion
    */
   public String detalleEditarPalmicultor(String num_documento) {
      try {
	 //tipoDocumento +"¬"+ numeroDocumento +"¬"+ nombre +"¬"+ apellido +"¬"+ sexo +"¬"+ direccion +"¬"+ departamento +"¬"+ municipio +"¬"+ telefono +"¬"+ celular +"¬"+ email +"¬"+ zonaPalmera +"¬"+ cedulaPalmera;
	 String infoPropietario = new Propietario_DAO().consultarInformacionPropietario(num_documento);
	 String datos[] = infoPropietario.split("¬");

	 String html = "<form  action=\"validarEditarPropietario.jsp\" method=\"POST\">\n<div class=\"col-md-5\">\n"
		 + "<label>Tipo Documento</label>\n"
		 + "<input type=\"text\" id=\"tipo_d\" name=\"tipo_d\" class=\"form-control\" value=" + datos[0] + ">\n"
		 + "<input type=\"hidden\" id=\"idViejo\" name=\"idViejo\" class=\"form-control\" value=" + datos[1] + "><br>\n"
		 + "<label>Número Documento</label>\n"
		 + "<input type=\"text\" id=\"numero\" name=\"numero\" class=\"form-control\" value=" + datos[1] + "><br>\n"
		 + "<label>Nombre</label>\n"
		 + "<input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\" value=" + datos[2] + "><br>\n"
		 + "<label>Apellido</label>\n"
		 + "<input type=\"text\" id=\"apellidos\" name=\"apellidos\" class=\"form-control\" value=" + datos[3] + " ><br>\n"
		 + "<label>Género</label>\n"
		 + "<input type=\"text\" id=\"genero\" name=\"genero\" class=\"form-control\" value=" + datos[4] + " ><br>"
		 + "<label>Direccion</label>\n"
		 + "<input type=\"text\" id=\"direccion\" name=\"direccion\" class=\"form-control\" value=" + datos[5] + " ><br>\n"
		 + "<div id=\"demo\">\n"
		 + "<label>Departamento</label>\n"
		 + "<select name=\"departamento\" id=\"departamento\" class=\"form-control\" onChange=\"cargaContenido(this.id)\" >\n"
		 + this.cargarDepartamentos() + "\n</select>\n"
		 + "<label>Municipio</label>\n"
		 + "<div id=\"demoDer\">\n"
		 + "<select disabled=\"disabled\" name=\"municipio\" id=\"municipio\" class=\"form-control\" onChange=\"cargaContenido(this.id)\">\n"
		 + "<option value=\"0\">Selecciona opci&oacute;n...</option>\n"
		 + "</select>\n"
		 + "</div>\n"
		 + "</div>\n"
		 + "</div>\n"
		 + "<div class=\"col-sm-5\">\n"
		 + "<label>Celular</label>\n"
		 + "<input type=\"number\" id=\"celular\" name=\"celular\" class=\"form-control\" value=" + datos[9] + " ><br>\n"
		 + "<label>Telefono</label>\n"
		 + "<input type=\"number\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value=" + datos[8] + " ><br>\n"
		 + "<label>Correo Electronico</label>\n"
		 + "<input type=\"email\" id=\"email\" name=\"email\" class=\"form-control\" value=" + datos[10] + " ><br>\n"
		 + "<label>Zona Palmera</label>\n"
		 + "<input type=\"text\" id=\"zonaPalmera\" name=\"zonaPalmera\" class=\"form-control\" value=" + datos[11] + "><br>\n"
		 + "<label>Cedula Palmera</label>\n"
		 + "<input type=\"text\" id=\"cedulaPalmera\" name=\"cedulaPalmera\" class=\"form-control\" value=" + datos[12] + "><br>\n"
		 + "<label>Estado</label>\n"
		 + "<select name = 'estado' id = 'estado' class=\"form-control\"><option value = '1'>Activo</option><option value = '0'>Inactivo</option></select><br>\n"
		 + "</div>\n"
		 + "<div class=\"col-xs-6\">\n"
		 + "<center><input type=\"submit\" class=\"btn btn-success\" value='GUARDAR DATOS' ></center><br>\n"
		 + "</div>\n</form>";

	 return html;

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return "";
   }

   /**
    * Metodo para mostrar en detalle de la hacienda
    *
    * @param hacienda codigo de la hacienda
    * @return codigo HTML con la informacion
    */
   public String detalleHacienda(String hacienda) {

      try {
	 Hacienda h = new Hacienda_DAO().consultarHacienda(hacienda);

	 String html = "<div class=\"col-md-4\">\n"
		 + "<label>Cedula Catastral</label>\n"
		 + "<input type=\"text\" id=\"cedCatastral\" name=\"cedCatastral\" class=\"form-control\" value=" + h.getCedCatastral() + " readonly><br>\n"
		 + "<label>Nombre Hacienda</label>\n"
		 + "<input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\" value=" + h.getNombre() + " readonly><br>\n"
		 + "<label>Direccion de la Hacienda</label>\n"
		 + "<input type=\"text\" id=\"direccion\" name=\"direccion\" class=\"form-control\" value=" + h.getDireccion() + " readonly><br>"
		 + "<label>Municipio</label>\n"
		 + "<input type=\"text\" id=\"municipio\" name=\"municipio\" class=\"form-control\" value=" + h.getNombreMun() + " readonly><br>\n"
		 + "</div>\n"
		 + "<div class=\"col-md-4\">\n"
		 + "<label>Nucleo Palmero</label>\n"
		 + "<input type=\"text\" id=\"nucleo\" name=\"nucleo\" class=\"form-control\" value=" + h.getNucleo() + " readonly><br>\n"
		 + "<label>Area total en hectareas<label class=\"requerido\">*</label></label>\n"
		 + "<input type=\"number\" id=\"area\" name=\"area\" class=\"form-control\" value=" + h.getArea() + " readonly><br>\n"
		 + "<label>Telefono</label>\n"
		 + "<input type=\"number\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value=" + h.getTelefono() + " readonly><br>\n"
		 + "<label>Descripcion</label>\n"
		 + "<textarea  id=\"descripcion\" name=\"descripcion\" class=\"form-control\" readonly>" + h.getDescripcion() + "</textarea><br>\n"
		 + "<center><button type=\"button\" class=\"btn btn-success\" onclick=\"location.href = 'detalleEditarHacienda.jsp?id=" + hacienda + "'\">Editar</button></center>"
		 + "\n</div>";

	 return html;

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return ("Error");

   }

   /**
    * Metodo para enviar los campos editables de la informacion de una Hacienda
    *
    * @param hacienda codigo de la hacienda que se va editar
    * @return codigo HTML con los datos editables
    */
   public String detalleEditarHacienda(String hacienda) {
      try {
	 Hacienda h = new Hacienda_DAO().consultarHacienda(hacienda);

	 String html = "<form  action='validarEditarHacienda.jsp?hacienda=" + h.getId() + "' method=\"POST\">\n<div class=\"col-md-4\">\n"
		 + "<label>Cedula Catastral</label>\n"
		 + "<input type=\"text\" id=\"cedCatastral\" name=\"cedCatastral\" class=\"form-control\" value=" + h.getCedCatastral() + "><br>\n"
		 + "<label>Nombre Hacienda</label>\n"
		 + "<input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\" value=" + h.getNombre() + "><br>\n"
		 + "<label>Direccion de la Hacienda</label>\n"
		 + "<input type=\"text\" id=\"direccion\" name=\"direccion\" class=\"form-control\" value=" + h.getDireccion() + "><br>\n"
		 + "<div id=\"demo\">\n"
		 + "<label>Departamento</label>\n"
		 + "<select name=\"departamento\" id=\"departamento\" class=\"form-control\" onChange=\"cargaContenido(this.id)\" >\n"
		 + this.cargarDepartamentos() + "\n</select><br>\n"
		 + "<label>Municipio</label>\n"
		 + "<div id=\"demoDer\">\n"
		 + "<select disabled=\"disabled\" name=\"municipio\" id=\"municipio\" class=\"form-control\" onChange=\"cargaContenido(this.id)\">\n"
		 + "<option value=\"0\">Selecciona opci&oacute;n...</option>\n"
		 + "</select>\n"
		 + "</div>\n"
		 + "</div>\n"
		 + "</div>\n"
		 + "<div class=\"col-md-4\">\n"
		 + "<label>Nucleo Palmero</label>\n"
		 + "<input type=\"text\" id=\"nucleo\" name=\"nucleo\" class=\"form-control\" value=" + h.getNucleo() + "><br>\n"
		 + "<label>Area total en hectareas</label>\n"
		 + "<input type=\"number\" id=\"area\" name=\"area\" class=\"form-control\" value=" + h.getArea() + "><br>\n"
		 + "<label>Telefono</label>\n"
		 + "<input type=\"number\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value=" + h.getTelefono() + "><br>\n"
		 + "<label>Descripcion</label>\n"
		 + "<textarea  id=\"descripcion\" name=\"descripcion\" class=\"form-control\">" + h.getDescripcion() + "</textarea><br>\n"
		 + "<label>Estado</label>\n"
		 + "<select name = 'activo' id = 'activo' class=\"form-control\"><option value = '1'>Activo</option><option value = '0'>Inactivo</option></select><br>\n"
		 + "</div><br>"
		 + "<div class='col-xs-12'><center><input type=\"submit\" class=\"btn btn-success\"/ value=\"GUARDAR CAMBIOS\"></center></div>\n</form>";

	 return html;

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return ("Error");
   }

   /**
    * Metodo para actualizar informacion Hacienda por parte del Administador del
    * sistema
    *
    * @param h infomacion actualizada de la hacienda
    * @return true si actualizo
    */
   public boolean actualizarEdicionHacienda(Hacienda h) {
      return (new Hacienda_DAO().actualizarEdicionHaciendaAdmin(h));
   }

   /**
    * método que actualiza en la base de datos la informacion referente de un
    * propietario
    *
    * @param p informacion actualizada del propietario
    * @return true si actializa propietario en la base de datos
    */
   public boolean actualizarEdicionPalmicultor(Propietario p, String id) {
      return (new Persona_DAO().actualizarInformacionPropietario(p, id));
   }
    // </editor-fold>

   // <editor-fold desc="PROPIETARIO">
   /**
    * metodo que valida si el id y la clave digitada coincide con la de la base
    * de datos del usuario
    *
    * @param id
    * @param clave
    * @return la url de la vista del usuario correspondiente si el loggeo fue
    * exitoso
    */
   public String iniciarSesion(String id, String clave) {
      Propietario_DAO propietario = new Propietario_DAO();
      return propietario.validarSesion(id, clave);
   }

   /**
    * metodo que registra el propietario de la hacienda
    *
    * @param p
    * @return mensaje con el registro exitoso del propietario o error si por
    * algun motivo este no se ha podido registrar o ya se encontraba registrado
    */
   public boolean registrarPropietario(Propietario p) throws SQLException {

      p.setTipoPersona("1");
      p.setNivelAcceso("1");
      p.setEstado("1");
      String salto = this.salto();
      p.setPassword(JCrypt.crypt(salto, p.getPassword()));

      return new Propietario_DAO().registrarPropietario(p);

   }

   /**
    *
    * @return
    */
   public String sihayHaciendas() {
      if (new Hacienda_DAO().hayHaciendas()) {
	 return ("");
      } else {
	 return ("<label>NO HAY HACIENDAS REGISTRADAS</label>");
      }
   }

   /**
    * Metodo para descativar un propietario
    *
    * @param idPropietario muero de documento del propietario
    * @return true si la desactivazion se realizo
    */
   public boolean desactivarCuentaPropietario(String idPropietario, String clave) throws SQLException {
      System.out.println("JAJAJAJAJAJ  " + idPropietario + " jijijijij  " + clave);
      return new Propietario_DAO().desactivarCuentaPropietario(idPropietario, clave);
   }

   /**
    *
    * @param idPropietario
    * @return
    * @throws SQLException
    */
   public String consultarInformacionPropietario(String idPropietario) throws SQLException {

      String info = new Propietario_DAO().consultarInformacionPropietario(idPropietario);
      String datos[] = info.split("¬");
      String tablaInfo = "<table class='table table-striped'>"
	      + "<tr>\n"
	      + "<th> <label class='labelTabla verde'>NOMBRE</label>\n"
	      + "<label>" + datos[2] + " " + datos[3] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>DOCUMENTO</label>\n"
	      + "<label>" + datos[0] + " " + datos[1] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>GENERO</label>\n"
	      + "<label>" + datos[4] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>DIRECCION</label>\n"
	      + "<label>" + datos[5] + "<br> " + datos[7] + ", " + datos[6] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>TELEFONO - CELULAR</label>\n"
	      + "<label>" + datos[8] + " - " + datos[9] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>E-MAIL</label>\n"
	      + "<label> " + datos[10] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>ZONA PALMERA</label>\n"
	      + "<label> " + datos[11] + "</label></th>\n"
	      + "</tr>\n"
	      + "<tr>\n"
	      + "<th><label class='labelTabla verde'>CEDULA PALMERA</label>\n"
	      + "<label> " + datos[12] + "</th>\n"
	      + "</tr>\n"
	      + "</table>";

      return tablaInfo;
   }

   /**
    *
    * @param idPalmicultor
    * @return
    * @throws SQLException
    */
   public String formularioEditarPropietario(String idPalmicultor) throws SQLException {
      String infoPropietario = new Propietario_DAO().consultarInformacionPropietario(idPalmicultor);
      String datos[] = infoPropietario.split("¬");

      String formularioEdicion = "<form action='validarEdicionPropietario.jsp' method='post'>\n"
	      + "<div class='col-sm-6'>\n"
	      + "<label>Nombre</label>\n"
	      + "<input type='text' id='nombre' name='nombre' class='form-control' value='" + datos[2] + "' readonly><br>\n"
	      + "<label>Apellido</label>\n"
	      + "<input type='text' id='apellidos' name='apellidos' class='form-control' value = '" + datos[3] + "' readonly><br>\n"
	      + "<label>Tipo documento</label>\n"
	      + "<input type='text' id='tipoDocumento' name='tipoDocumento' class='form-control' value='" + datos[0] + "' readonly><br>\n"
	      + "<label>Documento</label>\n"
	      + "<input type='text' id='numDocumento' name='numDocumento' class='form-control' value = '" + datos[1] + "' readonly><br>\n"
	      + "<label>Genero</label>\n"
	      + "<select id='genero' name='genero' class='form-control'>\n"
	      + "<option value='0'>Femenino</option>\n"
	      + "<option value='1'>Masculino</option>\n"
	      + "</select><br>\n"
	      + "<label>Direccion de Residencia<label class='requerido'>*</label></label>\n"
	      + "<input type='text' id='direccionResidencia' name='direccionResidencia' class='form-control' value='" + datos[5] + "' required><br>\n"
	      + "<label>Departamento</label>\n"
	      + "<div id='demo'>"
	      + "<select name=\"departamento\" id=\"departamento\" class=\"form-control\" onChange=\"cargaContenido(this.id)\" >"
	      + cargarDepartamentos()
	      + "</select>"
	      + "<br>\n"
	      + "<label>Municipio</label>\n"
	      + "<div id='demoDer'>"
	      + "<select disabled='disabled' name='municipio' id='municipio' class='form-control' onChange='cargaContenido(this.id)'>"
	      + "<option value='0'>Selecciona opci&oacute;n...</option>"
	      + "</select>"
	      + "</div>"
	      + "</div>"
	      + "<br>\n"
	      + "</div>\n"
	      + "<div class='col-sm-6 '>\n"
	      + "<label>Telefono de Residencia</label>\n"
	      + "<input type='number' id='telefono' name='telefono' class='form-control' value = '" + datos[8] + "'><br>\n"
	      + "<label>Celular</label>\n"
	      + "<input type='number' id='celular' name='celular' class='form-control' value = '" + datos[9] + "'><br>\n"
	      + "<label>Correo Electronico<label class='requerido'>*</label>\n"
	      + "<input type='email' id='email' name='email' class='form-control' value = '" + datos[10] + "' required><br>\n"
	      + "<label>Zona Palmera</label>\n"
	      + "<input type='text' id='zonaPalmera' name='zonaPalmera' class='form-control' value = '" + datos[11] + "' readonly><br>\n"
	      + "<label>Cedula Palmera</label>\n"
	      + "<input type='text' id='cedulaPalmera' name='cedulaPalmera' class='form-control' value = '" + datos[12] + "'><br>\n"
	      + "<center><input class='btn btn-success' type='submit' value = 'Finalizar Edición'/><br></center>\n"
	      + "</div>\n"
	      + "</form>\n";
      return formularioEdicion;
   }

   /**
    *
    * @param p
    * @return
    * @throws SQLException
    */
   public boolean actualizarInformacionPropietario(Propietario p) throws SQLException {
      return false;
//        return new Propietario_DAO().actualizarInformacionPropietario(p);
   }

   /**
    *
    * @param idPropietario
    * @return
    * @throws SQLException
    */
   public String consultarNombrePropietario(String idPropietario) throws SQLException {

      return new Propietario_DAO().consultarNombrePropietario(idPropietario);
   }
    // </editor-fold>

   // <editor-fold desc="GENERALES">
   /**
    * metodo para encriptar la contraseña del usuario al momento de registrar o
    * verificar
    *
    * @return cadena con el salto correspondiente
    */
   private String salto() {
      int dato1 = (int) (Math.random() * 30 + 60);
      int dato2 = (int) (Math.random() * 30 + 60);
      return ((char) dato1 + (char) dato2 + "");
   }

   String consultarNombreMunicipio(String idMunicipio) throws SQLException {
      return new Municipio_DAO().consultarMunicipioPorNombre(idMunicipio);
   }

   public String cargarMunicipiosPorDepartamento(String departamento) throws SQLException {

      String consultaMunicipios = new Municipio_DAO().cargarMunicipiosPorDepartamento(departamento);
      String municipios[] = consultaMunicipios.split("/");
      String select = "<select id=\"municipio\" name=\"municipio\" class=\"form-control\" onChage=\"cargarContenido(this.id)\">\n";

      for (String m : municipios) {
	 String datos[] = m.split("-");
	 String opcion = "<option value='¬'>@</option>\n";
	 opcion = opcion.replaceAll("¬", datos[0]);
	 opcion = opcion.replaceAll("@", datos[1]);
	 select += opcion;
      }
      return select + "</select>";
   }

   public String cargarDepartamentos() {

      try {
	 String consultarDepartamentos = new Departamento_DAO().cargarDepartamentos();
	 String departamentos[] = consultarDepartamentos.split("/");
	 String select = "";//"<select name=\"departamento\" id=\"departamento\" class=\"form-control\" onChange=\"cargaContenido(this.id)\">\n";
	 String opcion = "\t\t\t\t<option value='0'>Elige</option>\n";
	 select += opcion;
	 for (String d : departamentos) {
	    String datos[] = d.split("-");
	    opcion = "\t\t\t\t<option value='¬'>@</option>\n";
	    opcion = opcion.replaceAll("¬", datos[0]);
	    opcion = opcion.replaceAll("@", datos[1]);
	    select += opcion;
	 }
	 return select;// + "\t\t\t</select>";
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
	 return ("");
      }
   }

   /**
    * Metodo para obtener nombre de un municipio o departamento
    *
    * @param codigo 1 municipio ó 0 departamento
    * @param id de municipio o departamento
    * @return
    */
   private String obtenerNombre(int codigo, String id) {
      String dato = "";
      try {

	 switch (codigo) {
	    case 0:// Departamento
	       dato = new Departamento_DAO().consultarNombreDepartamentoPorMunicipio(id);
	       break;
	    case 1://Municipio
	       dato = new Municipio_DAO().consultarMunicipioPorNombre(id);
	       break;
	    case 2://palmicultor
	       dato = new Propietario_DAO().consultarNombrePropietario(id);
	       break;
	 }
	 return dato;
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return dato;
   }

   public String obtenerFechaDelSistema() {

      Calendar c = GregorianCalendar.getInstance();
      SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd");
      String fecha = form.format(c.getTime());

      return fecha;
   }
    // </editor-fold>

   //<editor-fold desc="HACIENDA">
   /**
    * Metodo para registrar Hacienda h
    *
    * @param h Object de la clase Hacienda
    * @return True en caso de realizar el registro exitoso
    */
   public boolean registrarHacienda(Hacienda h) {
      return (new Hacienda_DAO().registrarHacienda(h));
   }

   /**
    * Metodo para listar Hacienda
    *
    * @param p propietario de las haciendas
    * @return Una cadena String con los id y nombre de las haciendas
    */
   public String listarHaciendas(Propietario p) {

      try {
	 String hacienda = new Hacienda_DAO().listarHacienda(p);

	 if (!hacienda.isEmpty()) {
	    String haciendas[] = hacienda.split("\n");
	    String celda = "<tr>\n<td>\n"
		    + "<label class=\"labelTabla verde\">NOMBRE</label>"
		    + "\n<input type=\"hidden\" name=\"id\" value='¬'/>\n<label class=\"labelTabla verde\">UBICACION</label>\n</td>\n</tr>\n";
	    for (String h : haciendas) {
	       String datos[] = h.split("-");
	       String td = "<tr>\n<td>\n<a href=\"../jsp/informacionHacienda.jsp?id_hacienda=¬\">"
		       + "<label class=\"labelTabla verde\">Hacienda @</label>"
		       + "</a>\n<input type=\"hidden\" name=\"id\" value='¬'/>\n<label>°, &</label>\n</td>\n</tr>";
	       td = td.replaceAll("@", datos[1]);
	       td = td.replaceAll("¬", datos[0]);
	       td = td.replaceAll("°", datos[2]);
	       td = td.replaceAll("&", datos[3]);
	       celda += td;
	    }
	    return celda;
	 }

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());

      }
      return ("<tr><td><b>No hay Haciendas registradas<b></td></tr>");
   }

   /**
    * Metodo para consultar informacion de una Hacienda
    *
    * @param id de la hacienda a consultar
    * @return String HTML con la información de la hacienda consultada
    */
   public String consultarInformacionHacienda(String id) {
      try {
	 Hacienda h = new Hacienda_DAO().consultarHacienda(id);
	 System.out.println("DATOS DE LA HACIENDA PARA CONSULTA --->" + h);
	 String nombre = "<tr>\n<td >\n<label class=\"labelTabla verde\">Nombre</label>\n"
		 + "<label>" + h.getNombre() + "</label>\n</td>\n</tr>";
	 String dir = "\n<tr>\n<td >\n<label class=\"labelTabla verde\">Diercción</label>\n"
		 + "<label>" + h.getDireccion() + "</label>\n</td>\n</tr>";
	 String area = "\n<tr>\n<td >\n<label class=\"labelTabla verde\">Area total</label>\n"
		 + "<label>" + h.getArea() + " Hectareas</label>\n</td>\n</tr>";
	 String muni = "\n<tr>\n<td >\n<label class=\"labelTabla verde\">Ubicacion</label>\n"
		 + "<label>" + h.getNombreMun() + ", " + this.obtenerNombre(1, h.getMunicipio()) + "</label>\n</td>\n</tr>";
	 return (nombre + area + dir + muni);
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return "<tr>\n<td>\n<label>No se encontro Hacienda</label></td>\n</tr>";
   }

   /**
    * Metodo que permite mostrar Datos de una hacienda en particular para su
    * posterior modificacion
    *
    * @param id numero de identificacion de la hacienda
    * @return codigo html con los datos de la hacienda para su posterior
    * modificacion
    */
   public String editarDatosHacienda(String id) {
      try {
	 Hacienda h = new Hacienda_DAO().consultarHacienda(id);

	 String divs = "<div class=\"col-sm-6\">\n"
		 + "<label>Cedula Catastral</label>\n"
		 + "<input type=\"text\" id=\"cedulaCatastral\" name=\"cedulaCatastral\" class=\"form-control\" value=\"" + h.getCedCatastral() + "\" readonly><br>\n"
		 + "<label>Nombre Hacienda<label class=\"requerido\">*</label></label>\n"
		 + "<input type=\"text\" id=\"nombre\" name=\"nombre\" class=\"form-control\"  value='" + h.getNombre() + "' disable><br>\n"
		 + "\n"
		 + "<label>Direccion de la Hacienda<label class=\"requerido\">*</label></label>\n"
		 + "<input type=\"text\" id=\"residencia\" name=\"residencia\" class=\"form-control\" value=\"" + h.getDireccion() + "\" readonly><br>\n"
		 + "\n"
		 + "<label>Municipio</label>\n"
		 + "<input type=\"text\" id=\"residencia\" name=\"residencia\" class=\"form-control\" value=\"" + h.getNombreMun() + "\" readonly><br>\n"
		 + "</div>\n"
		 + "<div class=\"col-sm-6 \">\n"
		 + "<label>Nucleo Palmero<label class=\"requerido\">*</label></label>\n"
		 + "<input type=\"text\" id=\"nucleo\" name=\"nucleo\" class=\"form-control\" value=\"" + h.getNucleo() + " \" required><br>\n"
		 + "<label>Area total en hectareas<label class=\"requerido\">*</label></label>\n"
		 + "<input type=\"number\" id=\"area\" name=\"area\" class=\"form-control\" value=\"" + h.getArea() + "\" required><br>\n"
		 + "<label>Telefono</label>\n"
		 + "<input type=\"number\" id=\"telefono\" name=\"telefono\" class=\"form-control\" value=\"" + h.getTelefono() + "\"><br>\n"
		 + "<label>Descripcion</label>\n"
		 + "<textarea  id=\"descripcion\" name=\"descripcion\" class=\"form-control\">" + h.getDescripcion() + "</textarea><br>\n"
		 + "<center><input class=\"btn btn-success\" type=\"submit\" value=\"Guardar Cambios\" /></center>\n"
		 + "</div>";

	 return divs;
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }

      return ("error al cargar el formulario");
   }

   /**
    * Metodo para actualizar informacion de una Hacienda
    *
    * @param h Objeto de la clase Hacienda con la informacion nueva
    * @return true si la actualización se efectuó satisfactoriamente
    */
   public boolean actualizarHacienda(Hacienda h) {
      return (new Hacienda_DAO().actualizarHacienda(h));
   }

   /**
    * Metodo que consulta el nombre de una hacienda
    *
    * @param id de la hacienda
    * @return nombre de la hacienda
    */
   public String consultarNombreHacienda(String id) {
      try {
	 return (new Hacienda_DAO().consultarNombreHacienda(id));
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
	 return ("Error al procesar consulta en BD");
      }
   }

//    public String ubicacionHacienda(String idHacienda) throws SQLException {
//        String muniHacienda = new Hacienda_DAO().ubicacionHaciendaIdMunicipio(idHacienda);
//        System.out.println("MuniHacienda ---> " + muniHacienda);
//        return new Municipio_DAO().consultarMunicipioPorNombre(muniHacienda);
//    }
   /**
    * Metodo para desactivar una Hacienda
    *
    * @param id identificación de la Hacienda
    * @return true si permitio descativarla
    */
   public boolean desactivarHacienda(String id) {
      return (new Hacienda_DAO().desactivarHacienda(id));
   }
    //</editor-fold>

   //<editor-fold desc="LOTES">
   /**
    * Metodo para listar los lotes registrados en una Hacienda
    *
    * @param h hacienda de los lotes
    * @return cod html con la informacion de los lotes registrados
    */
   public String listarLotes(Hacienda h) {
      try {
	 String listaLotes = new Lote_DAO().listarLotes(h);
	 System.out.println("CONSULTA " + listaLotes);
	 if (listaLotes.isEmpty()) {
	    return ("<tr><td><b>No hay Lotes registrados<b></td></tr>");
	 }

	 String lotes[] = listaLotes.split("¬");
	 String celda = "";
	 for (String l : lotes) {
	    String datos[] = l.split("&");
	    String td = "<tr>\n<td>\n<a href=\"informacionLote.jsp?idLote=" + datos[0] + "\">"
		    + "\n<label class=\"labelTabla verde\">Lote " + datos[1] + "</label>"
		    + "</a>\n<input type=\"hidden\" name=\"idLote\" value=" + datos[0] + "/>"
		    + "\n<label>Area : </label> <label>  " + datos[2] + " Ha" + "</label>"
		    + "\n&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<label>\nFecha de plantación :  </label>&nbsp<label>" + " " + datos[3] + "</label>"
		    + "\n</td>\n</tr>";
	    //String td="HOLA MUNDO";
	    celda += td;
	 }
	 return celda;
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }

      return ("<tr><td><b>No hay Lotes registrados<b></td></tr>");
   }

   /**
    * Metodo para registrar lote
    *
    * @param l lote
    * @return true si se registra
    */
   public boolean registrarLote(Lote l) {
      return (new Lote_DAO().registrarLote(l));
   }

   /**
    * Metodo para consultar la informacion de un lote dado su id
    *
    * @param id codigo id del lote a consultar
    * @return hmtl con la informacion consultada
    */
   public String consultarInformacionLote(String id) {
      try {
	 Lote l = new Lote_DAO().consultarLote(id);

	 String codigo = "<tr>\n<td>\n<label class=\"verde labelTabla\">Codigo</label>\n<label>" + l.getCodigoZona() + "</label>\n</td>\n<tr>\n";
	 String area = "<td><label class=\"verde labelTabla\">Hectareas</label>\n<label>" + l.getArea() + "</label>\n</td>\n</tr>\n";
	 String capa = "<tr>\n<td><label class=\"verde labelTabla\">Capacidad</label>\n<label>" + l.getCapacidad() + " Palmas</label></td>\n</tr>\n";
	 String fechaPlan = "<td><label class=\"verde labelTabla\">Fecha Plantacion</label>\n<label>" + l.getFechaPlantacion() + "</label>\n</td>\n</tr>\n";

	 return (codigo + area + capa + fechaPlan);
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return ("Error en la consulta\n VUELVA A INTENTAR");
   }

   /**
    * Metodo que permite mostrar Datos de lote en particular para su posterior
    * modificacion
    *
    * @param id numero de identificacion del lote
    * @return codigo html con los datos del lote para su posterior modificacion
    */
   public String editarDatosLote(String id) {
      try {
	 Lote l = new Lote_DAO().consultarLote(id);
	 String divs = "<div class=\"col-sm-6\">\n"
		 + "<label>Area del lote</label>\n"
		 + "<input type=\"number\" id=\"area\" name=\"area\" class=\"form-control\" value=\"" + l.getArea() + "\" required><br>\n"
		 + "</div>"
		 + "<div class=\"col-sm-6 \">\n"
		 + "<label>Capacidad de palmas</label>\n"
		 + "<input type=\"number\" id=\"capacidad\" name=\"capacidad\" class=\"form-control\" value=\"" + l.getCapacidad() + "\" required><br>\n"
		 + "</div>";
	 return divs;

      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }

      return ("error al cargar el formulario");
   }

   /**
    * Proximamente actualizar información de un lote
    *
    * @param l Object de la clase Lote con la información actualizada
    * @return true si la actualización se efectuó satisfactoriamente
    */
   public boolean actulizarLote(Lote l) {
      return (new Lote_DAO().actulizarLote(l));
   }

   /**
    * Metodo para obtener el area de un lote
    *
    * @param id numero del lote en la BD
    * @return String con el dato del area
    */
   public String obtenerAreaLote(String id) {
      try {
	 return (new Lote_DAO().obtenerAreaLote(id));
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return ("Error al consultar");
   }

   /**
    * Metodo para obtener el codigo del lote dado un id
    *
    * @param id del lote
    * @return String con el codigo del lote
    */
   public String obtenerCodigoLote(String id) {
      try {
	 return (new Lote_DAO().obtenerCodigoLote(id));
      } catch (SQLException ex) {
	 System.err.println(ex.getMessage());
      }
      return ("Error al consultar");
   }

   /**
    * Metodo para desactivar un lote de una hacienda
    *
    * @param id identificacion del lote
    * @return true si la desactivación se efectuó satisfactoriamente
    */
   public boolean desactivarLote(String id) {
      return (new Lote_DAO().desactivarLote(id));
   }

   /**
    *
    * @param idLote
    * @return
    * @throws SQLException
    */
   public String nombreHaciendaDelLote(String idLote) throws SQLException {
      return new Lote_DAO().nombreHaciendaDelLote(idLote);
   }

  
//------------ FIN 1° MODULO-----------------//   
//------------2° MODULO-----------------//
      
   //<editor-fold desc="NIVELACION SUELO">
   /**
    * hace un nuevo registro de nivelacion de nutriente. Si se ha seleccionado
    * la opcion "otro fertilizante" se hace un nuevo registro, de lo contrario
    * se dejará la opción escogida. Si se ha seleccionado "Otra unidad" se hará
    * el registro de esta, sino continuará el registro de la nivelación
    * normalmente
    *
    * @param n nuevo registro de nivelacíon
    * @param nuevoFertilizante si es un nuevo fertilizante
    * @param nuevaUnidad si es una nueva unidad
    * @param docPropietario documento del propietario y/o encargado de la
    * hacienda
    * @return VERDADERO si el registro en la BD se llevo a cabo con Exito
    * @throws SQLException
    */
   public boolean registrarNivelacion(NivelacionNutriente n, String nuevoFertilizante, String nuevaUnidad, String docPropietario) throws SQLException {

      if (n.getIdFertilizante().equals("0")) {
	 Fertilizante f = new Fertilizante();
	 f.setDocumentoPersona(docPropietario);
	 f.setNombreFertilizante(nuevoFertilizante);
	 f.setFechaCreacion(this.obtenerFechaDelSistema());
	 new Fertilizante_DAO().registrarNuevoFertilizante(f);

	 Fertilizante reg = new Fertilizante_DAO().consultarFertilizantePorNombre(nuevoFertilizante);
	 n.setIdFertilizante(reg.getId());
      }

      if (n.getIdUnidad().equals("0")) {
	 Unidad u = new Unidad();
	 u.setTipo("peso");
	 u.setUnidad(nuevaUnidad);
	 u.setDocumentoPersona(docPropietario);
	 new Unidad_DAO().registrarNuevaUnidad(u, docPropietario);

	 Unidad uni = new Unidad_DAO().consultarUnidadPorNombre(nuevaUnidad);
	 n.setIdUnidad(uni.getUnidad());
      }
      n.setFechaRegistro(this.obtenerFechaDelSistema());

      return new nivelacionNutrientes_DAO().registrarNivelacion(n);
   }
    //</editor-fold>
  
//------------FIN 2° MODULO-----------------//

}
