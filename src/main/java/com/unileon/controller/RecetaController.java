/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.InventarioFacadeLocal;
import com.unileon.EJB.RecetaFacadeLocal;
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
public class RecetaController implements Serializable {

    private List<Receta> listaRecetas;
    private Receta nuevo;
    
    @EJB
    private RecetaFacadeLocal recetaEJB;

    @PostConstruct
    public void init() {
        nuevo = new Receta();
        listaRecetas = recetaEJB.findAll();
        
        
    }
    
    public void guardarNuevaReceta(){
        try{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nuevo.setUsuario(us);
            recetaEJB.create(nuevo);
            System.out.println("Nueva receta");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
            
        }catch(Exception e){
            System.err.println("Error al insertar receta");
        }
        System.out.println(nuevo.getNombreMedicamento());
        //return "/privado/medico/inicioMedico?faces-redirect=true";
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public Receta getNuevo() {
        return nuevo;
    }

    public void setNuevo(Receta nuevo) {
        this.nuevo = nuevo;
    }

}
