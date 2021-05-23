/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author usuario
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario consultarUsuario(Usuario us);
    
    List<Usuario> listarPacientes();
    
    Usuario buscarId(int id);
    
    List<Usuario> buscarNombre(String nombre);
    
    List<Usuario> buscarApellido1(String nombre, String apellido1);
    
    List<Usuario> buscarApellido2(String nombre, String apellido1, String apellido2);
    
    Usuario buscarUser(String user);
    
}
