/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable{
    
    @PostConstruct
    public void init(){
     
        
    }   
    public void verificarYMostrar(){
        try{
            
            //if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) FacesContext.getCurrentInstance().getExternalContext().redirect("/AppInso2/Publico/permisosinsuficientes.swII");

        }catch(Exception e){
            System.err.println("Error en verificar y mostrar");
        }
    }
    
    public String cerrarSesion(){
        
        return "/index?faces-redirect=true";
    }
}
