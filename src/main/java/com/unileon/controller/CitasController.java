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
    private List<Character> items;
    private Cita nuevo; 
    private String especialidad;
    private String nombrePac;
    private List<Date> fechas;
    private Cita cancelar;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private CitaFacadeLocal citaEJB;
    
    @PostConstruct
    public void init(){
        nuevo = new Cita();
        nombrePac = new String();
        listaCitasMedico = new ArrayList<Cita>();
        listaCitasPaciente = new ArrayList<Cita>();
        fechas = new ArrayList<Date>();
        items = new ArrayList<Character>();
        this.listarCitas();
        this.listarFechas();
        //this.listarPacientes();
        
    }
    
    public void listarFechas(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us.getTipo() == 0){
            for (int i = 0; i < listaCitasMedico.size(); i++) {
                fechas.add(listaCitasMedico.get(i).getFecha());
            }
        }
        else if(us.getTipo() == 2){
            for (int i = 0; i < listaCitasPaciente.size(); i++) {
                fechas.add(listaCitasPaciente.get(i).getFecha());
            }        
        }
    }
    
    public void listarCitas(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us.getTipo() == 0) listaCitasMedico = citaEJB.buscarCitasMedico(us);
        else if(us.getTipo() == 2) listaCitasPaciente = citaEJB.buscarCitasPaciente(us);
    }
    
    public boolean comprobarCita(Date fecha){
        for(int i = 0; i < listaCitasPaciente.size(); i++){
            if(listaCitasPaciente.get(i).getFecha().equals(fecha)) return false;
        }
        return true;
    }
    
     public void guardarNuevacita(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            if(us == null) FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            else if(us.getTipo() == 2){
                listaCitasPaciente = citaEJB.buscarCitasPaciente(us);
                boolean comprobar = comprobarCita(nuevo.getFecha());
                if(!comprobar) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El paciente ya tiene una cita a esa hora."));
                else{
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
                
            }
            else if(us.getTipo() == 0){
                Usuario paciente = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("paciente");
                listaCitasPaciente = citaEJB.buscarCitasPaciente(paciente);
                boolean comprobar = comprobarCita(nuevo.getFecha());
                if(!comprobar) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","El paciente ya tiene una cita a esa hora."));
                else{
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
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            }
        
            System.out.println("Nueva cita guardada");
        }catch(Exception e){}
    }
     
    public void guardarNuevaCitaMedico(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try{
            if(us == null) FacesContext.getCurrentInstance().getExternalContext().redirect("/HealthCare/publico/sinPrivilegios.healthcare");
            else {
                String [] partes = nombrePac.split(" ");
                System.out.println(partes.length);
                Usuario paciente = usuarioEJB.buscarApellido2(partes[0], partes[1], partes[2]).get(0);
                System.out.println(paciente.getUser());
                nuevo.setUsuario(paciente);
                List<Usuario> medicos = new ArrayList<Usuario>();
                medicos.add(us);
                System.out.println(nuevo.getCausa() + medicos.size());
                //nuevo.setFecha(sdf);
                //Hacer metodo asignar medico, mirar lo de la fecha y la hora
                Usuario medico = this.asignarMedico(medicos, nuevo.getFecha());
                System.out.println(nuevo.getFecha());
                if(medico == null) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","No hay diponibilidad horaria para esa cita."));
                else{
                    nuevo.setMedico(medico);
                    citaEJB.create(nuevo);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita añadida correctamente"));
                } 
            }
            
        
            System.out.println("Nueva cita guardada");
        }catch(Exception e){}
    }
     
    public Usuario asignarMedico(List<Usuario> med, Date fecha){
        System.out.println(med.size());
        int totalMin = fecha.getMinutes() + fecha.getHours()*60;
        for(int i = 0; i < med.size(); i++){
            List <Cita> citas = citaEJB.buscarCitasMedico(med.get(i));
            if(citas == null || citas.isEmpty()){
                return med.get(i);
            }
            citas = this.filtrarDia(citas, fecha);
            if(citas == null || citas.isEmpty()){
                return med.get(i);
            }
            citas = this.ordenarCitas(citas);
            System.out.println(citas.size());
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
        /*Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Cita cancelarCita = null;
        if(us.getTipo() == 0){
            for (int i = 0; i < listaCitasMedico.size(); i++) {
                if(listaCitasMedico.get(i).getFecha().equals(cancelar)) cancelarCita = listaCitasMedico.get(i);
            }
        }
        else if(us.getTipo() == 2){
            for (int i = 0; i < listaCitasPaciente.size(); i++) {
                if(listaCitasPaciente.get(i).getFecha().equals(cancelar)) cancelarCita = listaCitasPaciente.get(i);
            }    
        }
        citaEJB.remove(cancelarCita);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Cita cancelada"))*/
        System.out.println(cancelar.getFecha());
    }
    
    public void listarPacientes(){
        List<Usuario> lista = usuarioEJB.listarPacientes();

        for(int i = 0; i < lista.size(); i++){
            String nombre = lista.get(i).getPersona().getNombre() + " " + lista.get(i).getPersona().getApellido1() + " " + lista.get(i).getPersona().getApellido2();
            this.items.add(nombre.charAt(0));
        }
        
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

    public String getNombrePac() {
        return nombrePac;
    }

    public void setNombrePac(String nombrePac) {
        this.nombrePac = nombrePac;
    }

    public List<Character> getItems() {
        return items;
    }

    public void setItems(List<Character> items) {
        this.items = items;
    }

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
    }

    public Cita getCancelar() {
        return cancelar;
    }

    public void setCancelar(Cita cancelar) {
        this.cancelar = cancelar;
    }
    
    
    
}
