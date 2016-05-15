/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Dao.LaborSiembra_DAO;
import Dao.PreparacionSuelo_DAO;
import Dto.LaborSiembra;
import Dto.PreparacionSuelo;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.crearPDF;

/**
 *
 * @author brial
 */
public class PalmaBrian {

    public PalmaBrian() {

    }

    //<editor-fold desc="PREPARACION DE SUELO">
    /**
     * Metodo para registrar la preparacion del suelo de un Lote
     *
     * @param ps Object de la clase PreparacionSuelo que contiene la informacion
     * a registrar
     * @return true si el registro es exitoso
     */
    public boolean registrarPreparacionSuelo(PreparacionSuelo ps) {
        return (new PreparacionSuelo_DAO().registrarPreparacionSuelo(ps));
    }

    /**
     * Metodo para actualizar los datos de una preparacion de suelo de un lote
     *
     * @param ps Object con la informacion actualizada
     * @return true si la actualizacion es exitosa
     */
    public boolean actualizarPreparacionSuelo(PreparacionSuelo ps) {
        return (new PreparacionSuelo_DAO().actualizarPreparacionSuelo(ps));
    }

    /**
     * Metodo para eliminar registro de preparacion de suelo de un lote dado su
     * id
     *
     * @param id identificador de la preparacion de suelo
     * @return true si la eliminacion fue exitosa
     */
    public boolean eliminarPreparacionSuelo(String id) {
        return (new PreparacionSuelo_DAO().eliminarPreparacionSuelo(id));
    }

    /**
     * Metodo para mostrar los datos de la preparacion fisica de suelo de un
     * lote
     *
     * @param idLote
     * @param tipo s es por area (a) ó por metros lneales (m)
     * @return codigo html con la inforcaion (si hay registrada) de la
     * preparacion de suelos
     */
    public String vistaPreparacionSuelo(String idLote, String tipo) {

        ArrayList<PreparacionSuelo> pss = new PreparacionSuelo_DAO().getPreparacionSuelo(idLote, tipo);
        String html = "";
        if (pss.size() != 0) {
            for (PreparacionSuelo p : pss) {
                String tr = "<tr>\n"
                        + "<td>" + p.getFechaPreparacion() + "</td>\n"
                        + "<td>" + p.getLabor() + "</td>\n"
                        + "<td>" + p.getCantidadPreparado() + "</td>\n"
                        + "<td>" + p.getPrecio() + "</td>\n"
                        + "<td>" + p.getSubtotal() + "</td>\n"
                        + "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarCosto'  onclick=\"editarPreparacionSuelo('"+p.getId()+"')\"><img src='../images/lapiz.PNG'></button> <a herf='validarEliminarPreparacionSuelo.sjp?id=" + p.getId() + "'><img src='../images/x.png'></a></td>\n"
                        + "</tr>\n";
                html += tr;
            }

            return html;
        } else {
            return ("<tr><td colspan='6'> No se encuentra registrada aún preparación del suelo </td></tr>");
        }
    }

    /**
     * Metodo para ditar una preparacion suelo
     *
     * @param id codigo de la preparacion de suelo
     * @return html
     */
    public String editarPreparacionSuelo(String id) {
        PreparacionSuelo ps = new PreparacionSuelo_DAO().getPreparacionSuelo(id);

        String html = "";
        if (ps != null && ps.getTipoUso().equalsIgnoreCase("m")) {
            html = "<form action=\"validarEditarPreparacionSuelo.jsp\" method=\"post\">\n"
                    + "<div class=\"recuadro row blanco\">  \n"
                    + "   <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n"
                    + "       <span aria-hidden=\"true\">&times;</span>\n"
                    + "       <span class=\"sr-only\">Close</span></button>\n"
                    + "   <center><h2 class=\"verde\">Editar costo</h2></center>\n"
                    + "   <br>\n"
                    + "   <div class=\"col-sm-6\">\n"
                    + "        <label>Fecha<label class=\"requerido\"></label></label>\n"
                    + "        <input type=\"text\" id=\"cedulaCatastral\" name=\"fecha\" class=\"form-control\" value=\"" + ps.getFechaPreparacion() + "\" required><br>\n"
                    + "        <label>Labor<label class=\"requerido\">*</label></label>\n"
                    + "        <select id=\"labor\" name=\"labor\" class=\"form-control\"  onchange=\" validarOptionLabor()\">\n"
                    + this.getSelectLabores()
                    + "        </select><br>\n"
                    + "        <label>¿Otro? indicanos cual</label>\n"
                    + "        <input type=\"text\" id=\"otra\" name=\"otra\" class=\"form-control\" placeholder=\"otra\" required disabled='disabled'><br>\n"
                    + "   </div>\n"
                    + "   <div class=\"col-sm-6 \">\n"
                    + "        <label>Metros Líneales</label>\n"
                    + "        <input type=\"text\" id=\"metros\" name=\"Metros\" class=\"form-control\" value=\"" + ps.getTipoUso() + "\" onchange=\"subtotalBrian(this,'#precio')\" required><br> \n"
                    + "        <label>Precio por hectarea adecuada</label>\n"
                    + "        <input type=\"text\" id=\"precio\" name=\"precio\" class=\"form-control\" value=\"" + ps.getPrecio() + "\"  onchange=\"subtotalBrian(this,'#metros')\" ><br>\n"
                    + "        <label>Subtotal</label>\n"
                    + "        <input type=\"text\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" value=\"" + ps.getSubtotal() + "\" readonly><br>\n"
                    + "    </div>\n"
                    + "    <div class=\"col-sm-12\">\n"
                    + "         <center><input class=\"btn btn-success\" type=\"submit\" value=\"Guardar\"><br></center>\n"
                    + "    </div>\n"
                    + "</div> \n"
                    + "</form>";

            return html;
        } else if (ps != null && ps.getTipoUso().equalsIgnoreCase("a")) {
            html = "<form action=\"validarEditarPreparacionSuelo.jsp\" method=\"post\">\n"
                    + "<div class=\"recuadro row blanco\">  \n"
                    + "   <button type=\"button\" class=\"close\" data-dismiss=\"modal\">\n"
                    + "       <span aria-hidden=\"true\">&times;</span>\n"
                    + "       <span class=\"sr-only\">Close</span></button>\n"
                    + "   <center><h2 class=\"verde\">Editar costo</h2></center>\n"
                    + "   <br>\n"
                    + "   <div class=\"col-sm-6\">\n"
                    + "                                <label>Fecha<label class=\"requerido\">*</label></label>\n"
                    + "                                <input type=\"text\" id=\"fechaPreparacion\" name=\"fecha\" class=\"form-control\" required><br>\n"
                    + "\n"
                    + "                                <label>Labor<label class=\"requerido\">*</label></label>\n"
                    + "                                <select id=\"idLabor\" name=\"idLabor\" onchange=\"validarOptionLabor()\"  class=\"form-control\">\n"
                    + this.getSelectLabores()
                    + "                                </select><br>\n"
                    + "\n"
                    + "                                <label>¿Otro? indicanos cual</label>\n"
                    + "                                <input type=\"text\" id=\"otro\" name=\"otro\" class=\"form-control\" ><br>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"col-sm-6 \">\n"
                    + "                                <label>Metros lineales<label class=\"requerido\">*</label></label>\n"
                    + "                                <input type=\"number\" id=\"cantidadPreparado\" name=\"cantidadPreparado\" class=\"form-control\" placeholder=\"\" min=\"0\"  required><br>\n"
                    + "                                <label>Precio por metros<label class=\"requerido\">*</label></label>\n"
                    + "                                <input type=\"number\" id=\"precio\" name=\"precio\" class=\"form-control\" min=\"0\"  required><br>\n"
                    + "                                <label>Subtotal<label class=\"requerido\">*</label></label>\n"
                    + "                                <input type=\"number\" id=\"subtotal\" name=\"subtotal\" class=\"form-control\" min=\"0\" required><br>\n"
                    + "                            </div>"
                    + "    <div class=\"col-sm-12\">\n"
                    + "         <center><input class=\"btn btn-success\" type=\"submit\" value=\"Guardar\"><br></center>\n"
                    + "    </div>\n"
                    + "</div> \n"
                    + "</form>";

            return html;
        } else {
            return "ERROR:::";
        }
    }

    /**
     * Metodo para opbtener option con los datos de las labores de una hacienda
     *
     * @return option del select Labor
     */
    public String getSelectLabores() {
        ArrayList<LaborSiembra> lab = new LaborSiembra_DAO().getLabores();

        String op = "<option value=\"-1\">Seleccione</option>\n"
                + "<option value=\"0\">otra</option>\n";

        if (lab == null) {
            return op;
        } else {
            for (LaborSiembra l : lab) {
                op += "<option value=\"" + l.getIdLabor() + "\">" + l.getLabor() + "</option>\n";
            }
            return op;
        }
    }
//</editor-fold>

    //<editor-fold desc="LABOR SIEMBRA">
    /**
     * Metodo para registrar la preparacion del suelo de un Lote
     *
     *
     * @param ls Object de la clase PreparacionSuelo que contiene la informacion
     * a registrar
     * @param idPropi
     * @return true si el registro es exitoso
     */
    public boolean registrarLaborSiembra(LaborSiembra ls, String idPropi) {
        return (new LaborSiembra_DAO().registrarLaborSiembra(ls, idPropi));
    }

    /**
     * Metodo para actualizar los datos de una preparacion de suelo de un lote
     *
     *
     * @param ls Object con la informacion actualizada
     * @return true si la actualizacion es exitosa
     */
    public boolean actualizarLaborSiembra(LaborSiembra ls) {
        return (new LaborSiembra_DAO().actualizarLaborSiembra(ls));
    }

    /**
     * Metodo para eliminar registro de preparacion de suelo de un lote dado su
     * * id
     *
     * @param id identificador de la preparacion de suelo
     * @return true si la eliminacion fue exitosa
     */
    public boolean eliminarLaborSiembra(String id) {
        return (new LaborSiembra_DAO().eliminarLaborSiembra(id));
    }

    /**
     * Metodo para obtener las labores de siembra registradas en un Lote
     *
     * @param id identificacion del Lote
     * @return codigo HTML con la información solicitada
     */
    public String vistaLaborSiembra(String id) {
        try {
            ArrayList<LaborSiembra> lss = new LaborSiembra_DAO().getLaboresSiembra(id);
            if (lss.size() != 0) {
                String html = "";
                for (LaborSiembra ls : lss) {
                    String tr = "<tr>\n"
                            + "<td>" + ls.getFechaLabor() + "</td>\n"
                            + "<td>" + ls.getAreaLoteLabor() + "</td>\n"
                            + "<td>" + ls.getLabor() + "</td>\n"
                            + "<td>" + ls.getCantidadLabor() + "</td>\n"
                            + "<td>" + ls.getUnidad() + "</td>\n"
                            + "<td>" + ls.getPrecioLabor() + "</td>\n"
                            + "<td>" + ls.getSubtotal() + "</td>\n"
                            + "<td><button type='button' class='btn btn-default btn-sm' data-toggle='modal' data-target='#editarsuelo'  onclick=\"editarPreparacionSuelo('" + ls.getId() + "')\"><img src='../images/lapiz.png'></button> <a herf='validarEliminarPreparacionSuelo.sjp?id=" + ls.getId() + "'><img src='../images/x.png'></a></td>\n"
                            + "</tr>\n";
                    html += tr;
                }
                return html;
            } else {
                return ("<tr><td colspan='8'>No hay Labores Regstradas en este Lote</td></tr>");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return ("Error En La BD");
        }
    }

    /**
     * Metodo para obtener la informacion de una labor siembra dado su id
     *
     * @param id id de la labor siembra
     * @return object con la informacion almacenada
     */
    public String vistaEditarLaborSiembra(String id) {
        try {
            LaborSiembra ls = new LaborSiembra_DAO().getLaborSiembra(id);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return "";
    }

    //</editor-fold>
    public static void main(String a[]) {
        System.out.println(new PalmaBrian().vistaPreparacionSuelo("1", "a"));
    }

    public void generarPDF(String idL, String idH,String tipo, Document d, String ruta, PdfWriter fd) {
        crearPDF crearPDF = new crearPDF();
        if(tipo.equalsIgnoreCase("lot"))
        {
            crearPDF.setId_pdf(idL);
        }else{
            crearPDF.setId_pdf(idH);
        }
        crearPDF.setTipo(tipo);
        crearPDF.correr(d, ruta, fd);
    }
}
