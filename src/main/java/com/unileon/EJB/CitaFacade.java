/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cita;
import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> implements CitaFacadeLocal {

    @PersistenceContext(unitName = "HealthCarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
    
    @Override
    public List<Cita> buscarCitasMedico(Usuario medico){
        String consulta = "FROM Cita c WHERE c.medico=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", medico);
        
        List<Cita> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
    @Override
    public List<Cita> buscarCitasPaciente(Usuario paciente){
        String consulta = "FROM Cita c WHERE c.usuario=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", paciente);
        
        List<Cita> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
}
