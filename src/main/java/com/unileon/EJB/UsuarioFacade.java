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
    
    @Override
    public List<Usuario> listarPacientes(){
        String consulta = "FROM Usuario u WHERE u.tipo=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", 2);
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
    @Override
    public List<Usuario> buscarNombre(String nombre){
        String consulta = "FROM Usuario u WHERE u.persona.nombre=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", nombre);
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
    @Override
    public Usuario buscarId(int id){
        String consulta = "FROM Usuario u WHERE u.id=:param1";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", id);
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado.get(0);
    }
    
    @Override
    public List<Usuario> buscarApellido1(String nombre, String apellido1){
        String consulta = "FROM Usuario u WHERE u.persona.nombre=:param1 and u.persona.apellido1=:param2";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", nombre);
        
        query.setParameter("param2", apellido1);
        
        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
    @Override
    public List<Usuario> buscarApellido2(String nombre, String apellido1, String apellido2){
        String consulta = "FROM Usuario u WHERE u.persona.nombre=:param1 and u.persona.apellido1=:param2 and u.persona.apellido2=:param3";
        
        Query query = em.createQuery(consulta);
        
        query.setParameter("param1", nombre);
        
        query.setParameter("param2", apellido1);

        query.setParameter("param3", apellido2);

        List<Usuario> resultado = query.getResultList();
        
        if(resultado.isEmpty()) return null;
        else return resultado;
    }
    
}
