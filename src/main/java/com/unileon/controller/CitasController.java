/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.CitaFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Cita;
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
public class CitasController implements Serializable{
    
    private List <Cita> listaCitas;
    private Cita nuevo; 
    private String especialidad;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private CitaFacadeLocal citaEJB;
    
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
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            if(us == null) FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            else if(us.getTipo() == 2){
                nuevo.setUsuario(us);
                List<Usuario> medicos = usuarioEJB.buscarTipo(0, especialidad);
                //Hacer metodo asignar medico, mirar lo de la fecha y la hora
                Usuario medico = this.asignarMedico(medicos);
                if(medico == null) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","No hay médicos disponibles para la fecha solicitada."));
                else{
                    nuevo.setMedico(medico);
                    citaEJB.create(nuevo);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita añadida correctamente"));
                } 
            }
            else if(us.getTipo() == 0){
                Usuario paciente = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
                nuevo.setUsuario(paciente);
                List<Usuario> medicos = usuarioEJB.buscarTipo(0, especialidad);
                //Hacer metodo asignar medico, mirar lo de la fecha y la hora
                Usuario medico = this.asignarMedico(medicos);
                if(medico == null) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","No hay médicos disponibles para la fecha solicitada."));
                else{
                    nuevo.setMedico(medico);
                    citaEJB.create(nuevo);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita añadida correctamente"));
                } 
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            }
        
            System.out.println("Nueva cita guardada");
        }catch(Exception e){}
    }
     
    public Usuario asignarMedico(List<Usuario> med){
        for(int i = 0; i < med.size(); i++){
            List <Cita> citas = citaEJB.buscarCitasMedico(med.get(i));
            
        }
        return null;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    
}
