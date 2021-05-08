/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
    
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
       
    }
    
     public String verificarUsuario(){
        
        //Si no es correcto
        return "/Publico/permisosInsuficientes?faces-redirect=true";
        
        //Si es un usuario correcto
        //return "/privado/inicio?faces-redirect=true";
        
    }
    
    public String registrar(){
        //EN modo produccion quitar ?faces-redirect=true
        return "/Publico/altausuario?faces-redirect=true";
    }
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
    
