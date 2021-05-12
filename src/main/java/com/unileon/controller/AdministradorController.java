/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class AdministradorController implements Serializable{
    @PostConstruct
    public void init(){
     
        
    }
    
    public String crearUsuario(){
        System.out.println("Crear nuevo usuario");
         
        return "/publico/altausuario?faces-redirect=true";
    }
    
    public String verInventario(){
         
        return "/privado/inventarioVista?faces-redirect=true";
    }
}
