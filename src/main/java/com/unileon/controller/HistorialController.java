/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Historial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
    
    @PostConstruct
    public void init(){
        nuevo = new Historial();
        listaHistorial = new ArrayList <Historial>();
        
        Historial h = new Historial();
        h.setSintomas("Dolor de cabeza");
        h.setFechaModificacion(new Date(12/06/2021));
        h.setTratamiento("Paracetamol");
        h.setDiagnostico("Estres");
        listaHistorial.add(h);
        
        h=new Historial();
        h.setSintomas("Dolor en el brazo");
        h.setFechaModificacion(new Date(12/04/2021));
        h.setTratamiento("Traumatologo");
        h.setDiagnostico("Caida, rotura de radio");
        listaHistorial.add(h);
    }
    
    public void guardarNuevo(){
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
    
}
