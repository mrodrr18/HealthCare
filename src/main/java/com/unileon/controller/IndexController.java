/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
import com.unileon.EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author usuario
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
        Usuario resultado = usuarioEJB.consultarUsuario(usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", resultado);
        System.out.println("ANTES DEL IF");
        if(resultado == null) return "/publico/permisosInsuficientes?faces-redirect=true";
        else{ 
            System.out.println("PRINT DEL ELSE");
            return "/privado/administrador/inicioAdministrador?faces-redirect=true";
            //return "/privado/medico/inicioMedico?faces-redirect=true";
            //return "/privado/auxiliarinicioAuxiliar?faces-redirect=true";
            //return "/privado/paciente/inicioPaciente?faces-redirect=true";
        
        }
    }
    
    public String registrar(){
        return "/publico/altausuario?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }    
    
    
}
