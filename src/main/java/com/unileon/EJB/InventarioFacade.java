/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Inventario;
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
public class InventarioFacade extends AbstractFacade<Inventario> implements InventarioFacadeLocal {

    @PersistenceContext(unitName = "HealthCarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioFacade() {
        super(Inventario.class);
    }
    
    @Override 
    public Inventario consultarInventario(String nombre){
        String consulta = "FROM Inventario i WHERE i.nombre=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", nombre);
        
        List<Inventario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado.get(0);
    }
    
}
