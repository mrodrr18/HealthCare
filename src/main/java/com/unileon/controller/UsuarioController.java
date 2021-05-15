/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class UsuarioController implements Serializable{
    
    private Usuario usuario;
    
    private Persona persona;
    
    private String tipoAltaUsuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        
        persona = new Persona();
    }
    
    public void insertarUsuario(){
        
        try{
            
            //CODIGO DE INSERTAR EN LA BASE DE DATOS, EL TIPO SIEMPRE AL ASIGNARSE DESDE
            //ALTAUSUARIO ES 2=PACIENTES, LOS OTROS DE FORMA MANUAL
            System.out.println("Entro en el metodo");
            usuario.setPersona(persona);
            usuario.setTipo(Integer.parseInt(tipoAltaUsuario));   
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
        }catch(Exception e){
            System.err.println("Error al insertar usuario");
        }
        
    }
    
    public boolean tipoActivado(){
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) return false;
        else return true;
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public String getTipoAltaUsuario() {
        return tipoAltaUsuario;
    }

    public void setTipoAltaUsuario(String tipoAltaUsuario) {
        this.tipoAltaUsuario = tipoAltaUsuario;
    }
    
    
}
