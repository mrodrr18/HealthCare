/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Receta;
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
public class RecetaController implements Serializable {

    private List<Receta> listaRecetas;
    private Receta nuevo;

    @PostConstruct
    public void init() {
        nuevo = new Receta();
        listaRecetas = new ArrayList<Receta>();

        Receta r = new Receta();
        r.setDias(6);
        r.setFecha(new Date());
        r.setNombreMedicamento("Ibuprofeno");
        r.setTiempoTomas(8);
        r.setVecesPorDia(2);
        listaRecetas.add(r);
        
        r = new Receta();
        r.setDias(6);
        r.setFecha(new Date());
        r.setNombreMedicamento("Paracetamol");
        r.setTiempoTomas(8);
        r.setVecesPorDia(2);
        listaRecetas.add(r);
        
        
    }
    
    public void guardarNuevaReceta(){
        System.out.println("Nueva receta guardada");
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
