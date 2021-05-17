/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Historial;
import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
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
public class PacienteController implements Serializable{
    private List <Usuario> listaPacientes;
    @PostConstruct
    public void init(){
     listaPacientes = new ArrayList <Usuario> ();
        
        Usuario paciente = new Usuario();
        Persona p = new Persona();
        p.setNombre("Pablo");
        p.setSexo("M");
        paciente.setPersona(p);
        listaPacientes.add(paciente);
        
        paciente=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        paciente.setPersona(p);
        listaPacientes.add(paciente);
        
        paciente=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        paciente.setPersona(p);
        listaPacientes.add(paciente);
        paciente=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        paciente.setPersona(p);
        listaPacientes.add(paciente);
        
        paciente=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        paciente.setPersona(p);
        listaPacientes.add(paciente);
        
        
    }
    public String verMedicos(){
        return "/privado/paciente/solicitarCita?faces-redirect=true";
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

    
    
}
