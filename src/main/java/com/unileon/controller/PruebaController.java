/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Persona;
import com.unileon.EJB.PersonaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author usuario
 */
@Named
@ViewScoped
public class PruebaController implements Serializable{
    
    private Persona persona;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    
    @PostConstruct
    public void init(){
        persona = new Persona();
    }
    
    public void insertarPersona(){
        personaEJB.create(persona);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    
    
    
}
