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
import java.text.SimpleDateFormat;
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
    
    private List <Cita> listaCitasMedico;
    private List <Cita> listaCitasPaciente;
    private Cita nuevo; 
    private String especialidad;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private CitaFacadeLocal citaEJB;
    
    @PostConstruct
    public void init(){
        nuevo = new Cita();
        listaCitasMedico = new ArrayList<Cita>();
        listaCitasPaciente = new ArrayList<Cita>();
        this.listarCitas();
        
    }
    
    public void listarCitas(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us.getTipo() == 0) listaCitasMedico = citaEJB.buscarCitasMedico(us);
        else if(us.getTipo() == 2) listaCitasPaciente = citaEJB.buscarCitasPaciente(us);
    }
    
     public void guardarNuevacita(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            if(us == null) FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            else if(us.getTipo() == 2){
                
                nuevo.setUsuario(us);
                List<Usuario> medicos = usuarioEJB.buscarTipo(0, especialidad);
                System.out.println(nuevo.getCausa() + medicos.size());
                //nuevo.setFecha(sdf);
                //Hacer metodo asignar medico, mirar lo de la fecha y la hora
                Usuario medico = this.asignarMedico(medicos, nuevo.getFecha());
                System.out.println(nuevo.getFecha());
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
                Usuario medico = this.asignarMedico(medicos, nuevo.getFecha());
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
        System.out.println(especialidad + nuevo.getFecha());
    }
     
    public Usuario asignarMedico(List<Usuario> med, Date fecha){
        System.out.println(med.size());
        int totalMin = fecha.getMinutes() + fecha.getHours()*60;
        for(int i = 0; i < med.size(); i++){
            List <Cita> citas = citaEJB.buscarCitasMedico(med.get(i));
            citas = this.filtrarDia(citas, fecha);
            citas = this.ordenarCitas(citas);
            for(int j = 0; j < citas.size(); j++){
                int hora = citas.get(j).getFecha().getHours();
                int minutos = citas.get(j).getFecha().getMinutes() + hora*60;
                if(j != citas.size()-1){
                    int siguientes = citas.get(j+1).getFecha().getMinutes() + citas.get(j+1).getFecha().getHours()*60;
                    if(totalMin == minutos) break;
                    else if(totalMin > minutos && totalMin < siguientes){
                        if((totalMin - minutos) >= 15 && (siguientes - totalMin) >= 15) return med.get(i);
                        else break;
                    }
                }
                else{
                    if(totalMin > minutos && (totalMin - minutos) >= 15) return med.get(i);
                }
            }
            
        }
        System.out.println(med.size());
        return null;
    }
    
    public List<Cita> filtrarDia(List<Cita> citas, Date dia){
        List<Cita> resultado = new ArrayList<Cita>();
        for(int i = 0; i < citas.size(); i++){
            int y = citas.get(i).getFecha().getYear();
            int m = citas.get(i).getFecha().getMonth();
            int d = citas.get(i).getFecha().getDay();
            if(dia.getDay() == d && dia.getMonth() == m && dia.getYear() == y){
                resultado.add(citas.get(i));
            }
        }
        return resultado;
    }
    
    public List<Cita> ordenarCitas (List<Cita> lista){
        for(int i=0;i<(lista.size()-1);i++){
            for(int j=i+1;j<lista.size();j++){
                if(lista.get(i).getFecha().after(lista.get(j).getFecha())){
                    //Intercambiamos valores
                    Cita aux =lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
 
                }
            }
        }
        return lista;
    }
    
    public void borrarcita(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita cancelada"));
        System.out.println("Cita cancelada: "+nuevo.getFecha());
    }

    public List<Cita> getListaCitasMedico() {
        return listaCitasMedico;
    }

    public void setListaCitasMedico(List<Cita> listaCitasMedico) {
        this.listaCitasMedico = listaCitasMedico;
    }

    public List<Cita> getListaCitasPaciente() {
        return listaCitasPaciente;
    }

    public void setListaCitasPaciente(List<Cita> listaCitasPaciente) {
        this.listaCitasPaciente = listaCitasPaciente;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public CitaFacadeLocal getCitaEJB() {
        return citaEJB;
    }

    public void setCitaEJB(CitaFacadeLocal citaEJB) {
        this.citaEJB = citaEJB;
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
