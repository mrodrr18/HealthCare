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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author usuario
 */

@Entity
@Table(name="Recetas")

public class Receta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReceta;
    
    @Column(name="nombreMedicamento")
    private String nombreMedicamento;
    
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name="dias")
    private int dias;
    
    @Column(name="vecesPorDia")
    private int vecesPorDia;
    
    @Column(name="tiempoTomas")
    private int tiempoTomas;
    
    @JoinColumn(name="idUsuario")
    @ManyToOne
    private Usuario usuario;
    
    @JoinColumn(name="idHistorial")
    @ManyToOne
    private Historial historial;

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getVecesPorDia() {
        return vecesPorDia;
    }

    public void setVecesPorDia(int vecesPorDia) {
        this.vecesPorDia = vecesPorDia;
    }

    public int getTiempoTomas() {
        return tiempoTomas;
    }

    public void setTiempoTomas(int tiempoTomas) {
        this.tiempoTomas = tiempoTomas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idReceta;
        hash = 79 * hash + Objects.hashCode(this.nombreMedicamento);
        hash = 79 * hash + Objects.hashCode(this.fecha);
        hash = 79 * hash + this.dias;
        hash = 79 * hash + this.vecesPorDia;
        hash = 79 * hash + this.tiempoTomas;
        hash = 79 * hash + Objects.hashCode(this.usuario);
        hash = 79 * hash + Objects.hashCode(this.historial);
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
        final Receta other = (Receta) obj;
        if (this.idReceta != other.idReceta) {
            return false;
        }
        if (this.dias != other.dias) {
            return false;
        }
        if (this.vecesPorDia != other.vecesPorDia) {
            return false;
        }
        if (this.tiempoTomas != other.tiempoTomas) {
            return false;
        }
        if (!Objects.equals(this.nombreMedicamento, other.nombreMedicamento)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.historial, other.historial)) {
            return false;
        }
        return true;
    }

    
    
    
}
