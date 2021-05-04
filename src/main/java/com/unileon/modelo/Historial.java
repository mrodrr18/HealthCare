/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author usuario
 */

@Entity
@Table(name="Historial")

public class Historial implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorial;
    
    @Column(name="sintomas")
    private String sintomas;
    
    @Column(name="diagnostico")
    private String diagnostico;
    
    @Column(name="tratamiento")
    private String tratamiento;
    
    @Column(name="fechaModificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @JoinColumn(name="idUsuario")
    @OneToOne
    private Usuario usuario;

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idHistorial;
        hash = 97 * hash + Objects.hashCode(this.sintomas);
        hash = 97 * hash + Objects.hashCode(this.diagnostico);
        hash = 97 * hash + Objects.hashCode(this.tratamiento);
        hash = 97 * hash + Objects.hashCode(this.fechaModificacion);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Historial other = (Historial) obj;
        if (this.idHistorial != other.idHistorial) {
            return false;
        }
        if (!Objects.equals(this.sintomas, other.sintomas)) {
            return false;
        }
        if (!Objects.equals(this.diagnostico, other.diagnostico)) {
            return false;
        }
        if (!Objects.equals(this.tratamiento, other.tratamiento)) {
            return false;
        }
        if (!Objects.equals(this.fechaModificacion, other.fechaModificacion)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
    
}
