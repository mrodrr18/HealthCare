/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

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
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class PacienteController implements Serializable{
    
    private List <Usuario> listaPacientes;
    
    private List <String>  nombres;
    
    private String nombreCompleto;
    
    private String nombreUsuario;
    
    private String n;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        
        
        n = new String();
        listaPacientes = usuarioEJB.listarPacientes();
        nombres = new ArrayList<String>();
        nombres = this.listarNombres();
        
    }
    
    public List<String> listarNombres(){
        for(int i = 0; i < listaPacientes.size(); i++){
            nombres.add(listaPacientes.get(i).getPersona().getNombre() + " " + listaPacientes.get(i).getPersona().getApellido1() + " " + listaPacientes.get(i).getPersona().getApellido2());
        }
        return nombres;
    }
    
    
    
    public String buscar(){
        System.out.println("Hola");
        String[] partes = nombreUsuario.split(" ");
        System.out.println("El nombre es " + this.nombreUsuario );
        Usuario paciente = usuarioEJB.buscarApellido2(partes[0], partes[1], partes[2]).get(0);
        if (paciente == null || paciente.getTipo() != 2){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error.","El nombre de usuario introducido no pertenece a un paciente."));
            
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso.","El nombre de usuario introducido es correcto."));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("paciente", paciente);
            return "/privado/medico/verPaciente?faces-redirect=true";            
        }
        return null;
        

    }
    
    public String irAlInicio(){
        return "/privado/paciente/inicioPaciente?faces-redirect=true";
    }
    public String solicitarCita(){
        return "/privado/paciente/solicitarCita?faces-redirect=true";
    }
     public String cancelarCita(){
        return "/privado/paciente/cancelarCita?faces-redirect=true";
    }
    public String verMedicos(){
        return "/privado/paciente/listaMedicos?faces-redirect=true";
    }
    public String verCitas(){
         
        return "/privado/paciente/citasPaciente?faces-redirect=true";
    }
    public String verHistorial(){
         
        return "/privado/paciente/historialPaciente?faces-redirect=true";
    }
    
    public String verRecetas(){
         
        return "/privado/paciente/recetasPaciente?faces-redirect=true";
    }

    public List<Usuario> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Usuario> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<String> getNombres() {
        return nombres;
    }

    public void setNombres(List<String> nombres) {
        this.nombres = nombres;
    }
    
    
    
    
    
}