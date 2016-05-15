/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

/**
 *
 * @author brialxsf
 */
public class Hacienda 
{
        //Atributos de la clase Hacienda
    private String nombre; // nombre Hacienda
    private String direccion;// direccion de la hacienda NO EDITABLE
    private String telefono;// telefeno de contacto de la hacienda
    private String cedCatastral;//cedula catastrral de la hacienda NO EDITABLE
    private String nucleo;//nucleo Palmero al cual pertenede la hacienda
    private String area;// area total del terreno de la hacienda
    private String propietario_cedula;//cedula del propietario de la hacienda
    private String municipio;// id del municipio donde se encuentra registrado la hacienda NO EDITABLE
    private String descripcion;//descripcion de la hacienda
    //----Extras-----
    private String nombreMun;//nombre del municipio donde se encuentyra ubicado la hacienda (no es primordial)
    private String id;//id generado en la BD de la Hacienda (no es primordial)
    private String activo;// codigo para saber si una Haciendta esta activa o no

    public Hacienda() {
    }

    public Hacienda(String nombre, String direccion, String telefono, String cedCatastral, String nucleo, String area, String propietario_cedula, String municipio, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cedCatastral = cedCatastral;
        this.nucleo = nucleo;
        this.area = area;
        this.propietario_cedula = propietario_cedula;
        this.municipio = municipio;
        this.descripcion = descripcion;
        this.activo="1";
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedCatastral() {
        return cedCatastral;
    }

    public void setCedCatastral(String cedCatastral) {
        this.cedCatastral = cedCatastral;
    }

    public String getNucleo() {
        return nucleo;
    }

    public void setNucleo(String nucleo) {
        this.nucleo = nucleo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPropietario_cedula() {
        return propietario_cedula;
    }

    public void setPropietario_cedula(String propietario_cedula) {
        this.propietario_cedula = propietario_cedula;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreMun() {
        return nombreMun;
    }

    public void setNombreMun(String nombreMun) {
        this.nombreMun = nombreMun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hacienda{" + "nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", cedCatastral=" + cedCatastral + ", nucleo=" + nucleo + ", area=" + area + ", propietario_cedula=" + propietario_cedula + ", municipio=" + municipio + ", descripcion=" + descripcion + '}';
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    

   

    
    
    
    
    
    
    
}