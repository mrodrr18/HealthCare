/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.HistorialFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Historial;
import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class VerPacienteController implements Serializable{
    
    private List <Historial> listaHistorial;
    
    private Usuario paciente;
    
    @EJB
    private HistorialFacadeLocal historialEJB;
    
    @PostConstruct
    public void init(){
        paciente = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
        this.asignarHistoriales();
    }
    
    public void asignarHistoriales(){
        this.listaHistorial = historialEJB.listarDiagnosticos(paciente);
    }
    
    public String irAlInicio(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paciente", null);
        return "/privado/medico/inicioMedico?faces-redirect=true";

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

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }
    
    
    
}