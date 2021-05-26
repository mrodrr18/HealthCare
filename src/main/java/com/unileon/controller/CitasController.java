/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Cita;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class CitasController implements Serializable{
    
    private List <Cita> listaCitas;
    private Cita nuevo; 
    
    @PostConstruct
    public void init(){
        nuevo = new Cita();
        listaCitas = new ArrayList <Cita>();
        
        Cita c = new Cita();
        c.setCausa("Dolor de cabeza");
        c.setUrgente(0);
        c.setFecha(new Date());
        listaCitas.add(c);
        
        c = new Cita();
        c.setCausa("Dolor de barriga");
        c.setUrgente(1);
        c.setFecha(new Date());
        listaCitas.add(c);
        
    }
    
     public void guardarNuevacita(){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita añadida correctamente"));
        System.out.println("Nueva cita guardada");
    }
     
     public void borrarcita(){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita cancelada"));
        System.out.println("Cita cancelada: "+nuevo.getFecha());
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public Cita getNuevo() {
        return nuevo;
    }

    public void setNuevo(Cita nuevo) {
        this.nuevo = nuevo;
    }
    
}
