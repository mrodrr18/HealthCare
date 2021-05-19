/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.HistorialFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Historial;
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
    
    @EJB
    private HistorialFacadeLocal historialEJB;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        nuevo = new Historial();
        this.listarHistoriales();
      
    }
    
    public void listarHistoriales(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        this.listaHistorial = historialEJB.listarDiagnosticos(us);
    }
    
    public void guardarNuevo(int idUs){
        
        /*try{
            Usuario us = usuarioEJB.buscarId(idUs);
            nuevo.setUsuario(us);
            nuevo.setFechaModificacion(new Date());
            historialEJB.create(nuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
            System.out.println(idUs);
            
        }catch(Exception e){
            System.err.println("Error al insertar diagnostico");
        }*/
        System.out.println("Nuevo diagnóstico en el historial");
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
    
    
    
}
