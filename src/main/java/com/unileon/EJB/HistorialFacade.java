/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Historial;
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
public class HistorialFacade extends AbstractFacade<Historial> implements HistorialFacadeLocal {

    @PersistenceContext(unitName = "HealthCarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialFacade() {
        super(Historial.class);
    }
    
    @Override
    public List<Historial> listarDiagnosticos(Usuario us){
        String consulta = "FROM Historial h WHERE h.usuario=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", us);
        
        List<Historial> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
    
    
}
