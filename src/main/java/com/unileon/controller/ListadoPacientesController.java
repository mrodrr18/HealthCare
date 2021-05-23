/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.HistorialFacadeLocal;
import com.unileon.EJB.RecetaFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Historial;
import com.unileon.modelo.Receta;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class ListadoPacientesController implements Serializable{
    
    
    @PostConstruct
    public void init(){
        
      
    }
    
    
    public String irANuevoDiagnostico(){
        return "/privado/medico/nuevoDiagnostico?faces-redirect=true";
    }
    public String irAnuevaReceta(){
        return "/privado/medico/nuevaReceta?faces-redirect=true";
    }
  
    
    
    
}
