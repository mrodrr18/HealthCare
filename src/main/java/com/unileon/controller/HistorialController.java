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
            /*Usuario us = usuarioEJB.buscarId(idUs);
            nuevo.setUsuario(us);
            nuevo.setFechaModificacion(new Date());
            historialEJB.create(nuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
            System.out.println(idUs);
            
            this.guardarReceta();*/
            
        }catch(Exception e){
            System.err.println("Error al insertar diagnostico");
        }
        System.out.println("Nuevo diagnóstico en el historial " + nuevo.getTratamiento());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Nuevo diagnóstico en el historial"));
        
        
        
    }
    public String irANuevoDiagnostico(){
        return "/privado/medico/nuevoDiagnostico?faces-redirect=true";
    }
    public String irAnuevaReceta(){
        return "/privado/medico/nuevaReceta?faces-redirect=true";
    }
    
    public void guardarReceta(){
        //Guardo la receta
        Usuario usuarioR = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        List <Historial> diagnosticos = historialEJB.listarDiagnosticos(usuarioR);
        
        //Busco el último diagnostico
        Historial result = diagnosticos.get(0);
        for(int i = 0; i < diagnosticos.size(); i++){
            if(diagnosticos.get(i).getFechaModificacion().compareTo(result.getFechaModificacion()) >= 0) {
                result = diagnosticos.get(i);
            }
        }
        nuevaReceta.setHistorial(result);
        nuevaReceta.setUsuario(usuarioR);
        recetaEJB.create(nuevaReceta);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
        System.out.println("Receta creada");   
        
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
    
    
    
}
