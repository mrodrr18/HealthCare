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
import javax.faces.bean.RequestScoped;
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
    
    private String nombreCompleto;
    
    private String n;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        
        
        n = new String();
        listaPacientes = usuarioEJB.listarPacientes();
        
    }
    
    public void buscar(){
        //String[] partes = this.nombreCompleto.split(" ");
        System.out.println("El nombre es " + this.nombreCompleto );
        this.listaPacientes = usuarioEJB.buscarNombre(this.nombreCompleto);
        System.out.println("Tam: "+this.listaPacientes.size());
        /*if(partes.length == 0) this.listaPacientes = usuarioEJB.buscarNombre(partes[0]);
        
        else if(partes.length == 2) this.listaPacientes = usuarioEJB.buscarApellido1(partes[0], partes[1]);
        
        else if(partes.length == 3) this.listaPacientes = usuarioEJB.buscarApellido2(partes[0], partes[1], partes[2]);
        
        else if(partes.length > 3) this.listaPacientes = null;
        
        System.out.println("Entro a buscar " + partes.length + nombreCompleto);
        if(partes.length==0){
           this.listaPacientes = usuarioEJB.listarPacientes();
        }*/
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
    
    

    
    
}