/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.HistorialFacadeLocal;
import com.unileon.EJB.InventarioFacadeLocal;
import com.unileon.EJB.RecetaFacadeLocal;
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
public class RecetaController implements Serializable {

    private List<Receta> listaRecetas;
    private Receta nuevo;
    
    @EJB
    private RecetaFacadeLocal recetaEJB;
    
    @EJB
    private HistorialFacadeLocal historialEJB;

    @PostConstruct
    public void init() {
        nuevo = new Receta();
        listaRecetas = this.listarRecetas();
        
        
    }
    
    public void guardarNuevaReceta(){
        try{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            nuevo.setUsuario(us);
            //Hay que meter el historial
            recetaEJB.create(nuevo);
            System.out.println("Nueva receta");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
            
        }catch(Exception e){
            System.err.println("Error al insertar receta");
        }
        System.out.println(nuevo.getNombreMedicamento());
        //return "/privado/medico/inicioMedico?faces-redirect=true";
    }
    
    public List<Receta> listarRecetas(){
        try{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            //Todas las recetas
            List<Receta> lista = recetaEJB.findAll();
            
            //Todos los diagnosticos almacenados a ese paciente
            List<Historial> listaHistoriales = historialEJB.listarDiagnosticos(us);
            
            //Elimino las recetas que no sean de ese paciente
            for(int i = 0; i <lista.size(); i++){
                boolean encontrado = false;
                int j = 0;
                while(j < listaHistoriales.size() && !encontrado){
                    if(lista.get(i).getHistorial().equals(listaHistoriales.get(j))) encontrado = true;
                    j++;
                }
                if (!encontrado) lista.remove(i);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Recetas listadas","Aviso"));
            
            return lista;
            
        }catch(Exception e){
            System.err.println("Error al listas recetas");
        }
        return null;
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

    public RecetaFacadeLocal getRecetaEJB() {
        return recetaEJB;
    }

    public void setRecetaEJB(RecetaFacadeLocal recetaEJB) {
        this.recetaEJB = recetaEJB;
    }

    public HistorialFacadeLocal getHistorialEJB() {
        return historialEJB;
    }

    public void setHistorialEJB(HistorialFacadeLocal historialEJB) {
        this.historialEJB = historialEJB;
    }

    
}
