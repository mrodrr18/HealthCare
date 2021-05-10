/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "HealthCarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override 
    public Usuario consultarUsuario(Usuario us){
        String consulta = "FROM Usuario u WHERE u.user=:param1 and u.password=:param2";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", us.getUser());
        query.setParameter("param2", us.getPassword());
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado.get(0);
    }
    
}
