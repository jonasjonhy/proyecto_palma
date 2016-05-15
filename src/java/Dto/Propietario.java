/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dto;

/**
 *
 * @author mauricio uribe
 */
public class Propietario extends Persona{
    
    private String cedulaPalmera;
    private String zonaPalmera;
    
public Propietario(){
    super();
}

    /**
     * @return the cedulaPalmera
     */
    public String getCedulaPalmera() {
        return cedulaPalmera;
    }

    /**
     * @param cedulaPalmera the cedulaPalmera to set
     */
    public void setCedulaPalmera(String cedulaPalmera) {
        this.cedulaPalmera = cedulaPalmera;
    }

    /**
     * @return the zonaPalmera
     */
    public String getZonaPalmera() {
        return zonaPalmera;
    }

    /**
     * @param zonaPalmera the zonaPalmera to set
     */
    public void setZonaPalmera(String zonaPalmera) {
        this.zonaPalmera = zonaPalmera;
    }
    
}
