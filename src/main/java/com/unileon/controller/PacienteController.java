/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class PacienteController implements Serializable{
    @PostConstruct
    public void init(){
     
        
    }
    public String verMedicos(){
        return "/privado/paciente/solicitarCita?faces-redirect=true";
    }
    public String verHistorial(){
         
        return "/privado/paciente/historialPaciente?faces-redirect=true";
    }
    
    public String verRecetas(){
         
        return "/privado/paciente/recetasPaciente?faces-redirect=true";
    }
}
