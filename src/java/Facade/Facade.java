/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Dto.Hacienda;
import Dto.Lote;
import Dto.NivelacionNutriente;
import Dto.Propietario;
import Negocio.Palma;
import java.sql.SQLException;

/**
 *
 * @author mauricio uribe
 */
public class Facade {

    public Facade() {
    }

    //******************************************** ----- ADMINISTRADOR----- **************************************************
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
        return (new Palma().consultarPorCriterio(criterio, palabra, dpto, mun, tipo));
    }

    public static void main(String a[]) {
        //System.out.println(new Facade().consultarPorCriterio(0, "Altamira", null, null, "h"));
        System.out.println(new Palma().obtenerFechaDelSistema());
    }

    public String siHayHaciendas() {
        return (new Palma().sihayHaciendas());
    }

    public boolean actualizarEdicionHacienda(Hacienda h) {
        return new Palma().actualizarEdicionHacienda(h);
    }

    public boolean actualizarEdicionPropietario(Propietario p, String id) {
        return (new Palma().actualizarEdicionPalmicultor(p, id));
    }

    //******************************************** ----- PROPIETARIO ----- **************************************************
    /**
     * metodo que valida si el ID y la clave digitada coincide con la de la base
     * de datos de la persona
     *
     * @param id
     * @param clave
     * @return la url de la vista del usuario correspondiente si el loggeo fue
     * exitoso
     */
    public String iniciarSesion(String id, String clave) throws SQLException {
        Palma p = new Palma();
        return p.iniciarSesion(id, clave);
    }

    public boolean registrarPropietario(Propietario p) throws SQLException {

        return new Palma().registrarPropietario(p);
    }

    public String consultarInformacionPropietario(String id) throws SQLException {

        return new Palma().consultarInformacionPropietario(id);
    }

    /**
     * método que desactiva la cuenta del propietario (el mismo se desactiva), 1
     * = Activo 0 = inactivo
     *
     * @param idPropietario
     * @return verdadero en caso de realizar el cambio con éxito (se cambia el
     * estado activo ---> inactivo)
     */
    public boolean desactivarCuentaPropietario(String idPropietario, String clave) throws SQLException {
        Palma p = new Palma();
        return p.desactivarCuentaPropietario(idPropietario, clave);
    }

    public String consultarNombrePropietario(String idPropietario) throws SQLException {

        return new Palma().consultarNombrePropietario(idPropietario);
    }

    public String formularioEditarPropietario(String idPropietario) throws SQLException {

        return new Palma().formularioEditarPropietario(idPropietario);
    }

    public boolean actualizarInformacionPropietario(Propietario P) throws SQLException {

        return new Palma().actualizarInformacionPropietario(P);
    }

    public String detallePalimucultor(String id) {
        return (new Palma().detallePalmicultor(id));
    }

    public String detalleHacienda(String id) {
        return (new Palma().detalleHacienda(id));
    }

    public String detalleEditarPalmicultor(String id) {
        return (new Palma().detalleEditarPalmicultor(id));
    }

    public String detalleEditarHacienda(String id) {
        return (new Palma().detalleEditarHacienda(id));
    }

    //***************************************** -- MODULO 2 PROPIETARIO -- ********************************************
    public boolean registrarNivelacion(NivelacionNutriente n, String nuevoFertilizante, String nuevaUnidad, String docPropietario) throws SQLException {

        return new Palma().registrarNivelacion(n, nuevoFertilizante, nuevaUnidad, docPropietario);
    }

    //******************************************** ----- GENERALES ----- **************************************************
    /**
     * método que carga el nombre de los departamentos existentes en la Base de
     * Datos en un <select>
     *
     * @return un select con el nombre e id de los departamentos existentes
     * @throws SQLException
     */
    public String cargarDepartamentos() throws SQLException {

        return new Palma().cargarDepartamentos();
    }

    /**
     * método que carga los nombres de los municipios de acuerdo al departamento
     * que se pasa como parámetro
     *
     * @param departamento
     * @return un select con el nombre e id de todos los municipios del
     * departamento que se pasa como parámetro
     * @throws SQLException
     */
    public String cargarMunicipiosPorDepartamento(String departamento) throws SQLException {

        return new Palma().cargarMunicipiosPorDepartamento(departamento);
    }

     //<editor-fold desc="HACIENDA">
    /**
     * Metodo para registrar Hacienda h
     *
     * @param h Object de la clase Hacienda
     * @return True en caso de realizar el registro exitoso
     */
    public boolean registarHacienda(Hacienda h) {
        System.out.println("DATOS FACADE --> " + h.getCedCatastral() + " " + h.getNombre() + " " + h.getNucleo() + "  " + h.getPropietario_cedula());
        System.out.print(h.toString());
        return (new Palma().registrarHacienda(h));
    }

    /**
     * Metodo para listar Hacienda
     *
     * @param id numero del documento del palmicultor
     * @return Una cadena String en lenguaje HTML con los id y nombre de las
     * haciendas
     */
    public String listarHaciendas(String id) {
        Propietario p = new Propietario();
        p.setNumDocumento(id);
        return (new Palma().listarHaciendas(p));
    }

    /**
     * Metodo para consultar informacion de una Hacienda
     *
     * @param id de la hacienda a consultar
     * @return String HTML con la información de la hacienda consultada
     */
    public String consultarInformacionHacienda(String id) {
        System.out.println(id);
        return (new Palma().consultarInformacionHacienda(id));
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
        return (new Palma().editarDatosHacienda(id));
    }

    /**
     * Metodo para actualizar informacion de una Hacienda
     *
     * @param h Objeto de la clase Hacienda con la informacion nueva
     * @return true si la actualización se efectuó satisfactoriamente
     */
    public boolean actualizarHacienda(Hacienda h) {
        return (new Palma().actualizarHacienda(h));
    }

    /**
     * Metodo que consulta el nombre de una hacienda
     *
     * @param id de la hacienda
     * @return nombre de la hacienda
     */
    public String consultarNombreHacienda(String id) {
        return (new Palma().consultarNombreHacienda(id));
    }

    /**
     * Metodo para desactivar una Hacienda
     *
     * @param id identificación de la Hacienda
     * @param idP num documento del propietario
     * @param pass contraseña del propietario
     * @return true si permitio descativarla
     */
    public boolean desactivarHacienta(String id, String idP, String pass) {
        try {
            if (this.iniciarSesion(idP, pass).equalsIgnoreCase("../jsp/informacionPalmicultor.jsp")) {
                return (new Palma().desactivarHacienda(id));
            }
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }

        return false;
    }

//    public String ubicacionHacienda(String idHacienda) throws SQLException{
//        return new Palma().ubicacionHacienda(idHacienda);
//    }
    //</editor-fold>
    //<editor-fold desc="LOTES">
    /**
     * Metodo para listar los lotes registrados en una Hacienda
     *
     * @param id hacienda de los lotes
     * @return cod html con la informacion de los lotes registrados
     */
    public String listarLotesHacienda(String id) {
        Hacienda h = new Hacienda();
        h.setId(id);
        System.out.println("CODIGO PARA MOSTRAR LOTES : " + id);
        return (new Palma().listarLotes(h));
    }

    /**
     * Metodo para registrar lote
     *
     * @param l lote
     * @return true si se registra
     */
    public boolean registrarLote(Lote l) {
        System.out.println("DATOS LOTE ----> " + l.getFechaPlantacion() + " " + l.getHaciendaId() + " " + l.getArea() + " " + l.getCapacidad() + " " + l.getCodigoZona());
        return (new Palma().registrarLote(l));
    }

    /**
     * Metodo para consultar la informacion de un lote dado su id
     *
     * @param id codigo id del lote a consultar
     * @return hmtl con la informacion consultada
     */
    public String consultarInformacionLote(String id) {
        return (new Palma().consultarInformacionLote(id));
    }

    /**
     * Metodo que permite mostrar Datos de lote en particular para su posterior
     * modificacion
     *
     * @param id numero de identificacion del lote
     * @return codigo html con los datos del lote para su posterior modificacion
     */
    public String editarDatosLote(String id) {
        return (new Palma().editarDatosLote(id));
    }

    /**
     * Proximamente actualizar información de un lote
     *
     * @param l Object de la clase Lote con la información actualizada
     * @return true si la actualización se efectuó satisfactoriamente
     */
    public boolean actulizarLote(Lote l) {
        return (new Palma().actulizarLote(l));
    }

    /**
     * Metodo para obtener el area de un lote
     *
     * @param id numero del lote en la BD
     * @return String con el dato del area
     */
    public String obtenerAreaLote(String id) {
        return (new Palma().obtenerAreaLote(id));
    }

    /**
     * Metodo para obtener el codigo del lote dado un id
     *
     * @param id del lote
     * @return String con el codigo del lote
     */
    public String obtenerCodigoLote(String id) {
        return (new Palma().obtenerCodigoLote(id));
    }

    /**
     * Metodo para desactivar un Lote
     *
     * @param id identificación dl Lote
     * @param idP num documento del propietario
     * @param pass contraseña del propietario
     * @return true si permitio descativarla
     */
    public boolean desactivarLote(String id, String idP, String pass) {
        try {
            if (this.iniciarSesion(idP, pass).equalsIgnoreCase("../jsp/informacionPalmicultor.jsp")) {
                return (new Palma().desactivarLote(id));
            }
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }

        return false;
    }

    public String nombreHaciendaDelLote(String idLote) throws SQLException {
        return new Palma().nombreHaciendaDelLote(idLote);
    }
    //</editor-fold>

}
