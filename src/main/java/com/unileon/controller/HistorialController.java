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
public class HistorialController implements Serializable{
    
    private List <Historial> listaHistorial;
    
    private Historial nuevo; 
    
    private Receta nuevaReceta;
    
    private boolean activaReceta;
    
    private String pacienteDiag;
    
    @EJB
    private HistorialFacadeLocal historialEJB;

    @EJB
    private RecetaFacadeLocal recetaEJB;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        nuevo = new Historial();
        nuevaReceta = new Receta();
        this.listarHistoriales();
      
    }
    
    public void listarHistoriales(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        this.listaHistorial = historialEJB.listarDiagnosticos(us);
    }
    
    public void guardarNuevo(){
        
        try{
            //Guardo el historial
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
            if(us.getTipo() != 2 || us == null) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: El usuario introducido no exite o no es un paciente.","Error"));
            else{
                nuevo.setUsuario(us);
                nuevo.setFechaModificacion(new Date());
                historialEJB.create(nuevo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro del diagnóstico completado","Aviso"));
            }
                      
        }catch(Exception e){
            System.err.println("Error al insertar diagnostico" + nuevo.getDiagnostico());
        }
        
        
    }
    public String irANuevoDiagnostico(){
        return "/privado/medico/nuevoDiagnostico?faces-redirect=true";
    }
    public String irAnuevaReceta(){
        return "/privado/medico/nuevaReceta?faces-redirect=true";
    }
    
    
    public Historial getNuevo() {
        return nuevo;
    }

    public void setNuevo(Historial nuevo) {
        this.nuevo = nuevo;
    }
    
    public List<Historial> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<Historial> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public HistorialFacadeLocal getHistorialEJB() {
        return historialEJB;
    }

    public void setHistorialEJB(HistorialFacadeLocal historialEJB) {
        this.historialEJB = historialEJB;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public Receta getNuevaReceta() {
        return nuevaReceta;
    }

    public void setNuevaReceta(Receta nuevaReceta) {
        this.nuevaReceta = nuevaReceta;
    }

    public RecetaFacadeLocal getRecetaEJB() {
        return recetaEJB;
    }

    public void setRecetaEJB(RecetaFacadeLocal recetaEJB) {
        this.recetaEJB = recetaEJB;
    }

    public boolean isActivaReceta() {
        return activaReceta;
    }

    public void setActivaReceta(boolean activaReceta) {
        this.activaReceta = activaReceta;
    }

    public String getPacienteDiag() {
        return pacienteDiag;
    }

    public void setPacienteDiag(String pacienteDiag) {
        this.pacienteDiag = pacienteDiag;
    }
    
    
    
}
