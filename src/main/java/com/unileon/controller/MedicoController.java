/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Persona;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MedicoController implements Serializable{
    private List <Usuario> listaMedicos;
    
    @PostConstruct
    public void init(){
        listaMedicos = new ArrayList <Usuario> ();
        
        Usuario m = new Usuario();
        Persona p = new Persona();
        p.setNombre("Pablo");
        m.setPersona(p);
        listaMedicos.add(m);
        
        m=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        m.setPersona(p);
        listaMedicos.add(m);
        
        m=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        m.setPersona(p);
        listaMedicos.add(m);
        
        m=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        m.setPersona(p);
        listaMedicos.add(m);
        
        m=new Usuario();
        p= new Persona();
        
        p.setNombre("Lucía");
        p.setApellido1("Martínez");
        m.setPersona(p);
        listaMedicos.add(m);
       
        
    }
    
    public String solicitarCita(){
        return "/privado/medico/solicitarCitaMedico?faces-redirect=true";
    }
     public String cancelarCita(){
        return "/privado/medico/cancelarCitaMedico?faces-redirect=true";
    }
     
    public String verPacientes(){
        return "/privado/medico/listadoPacientes?faces-redirect=true";
    }
    public String verCitas(){
         
        return "/privado/medico/citasMedico?faces-redirect=true";
    }
    
    public String verRecetas(){
         
        return "/privado/paciente/recetasPaciente?faces-redirect=true";
    }

    public List<Usuario> getListaMedicos() {
        return listaMedicos;
    }

    public void setListaMedicos(List<Usuario> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }
    
}
