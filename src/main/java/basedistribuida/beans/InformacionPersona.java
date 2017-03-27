/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedistribuida.beans;

import basedistribuida.model.Direccion;
import basedistribuida.model.Persona;

/**
 *
 * @author USUARIO
 */
public class InformacionPersona {

    private Persona persona;
    private Direccion direccion;
    
    public InformacionPersona(Persona persona, Direccion direccion) {
        this.persona = persona;
        this.direccion = direccion;
    }
    
    public InformacionPersona() {}

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "InformacionPersona{" + "persona=" + persona + ", direccion=" + direccion + '}';
    }
    
}
