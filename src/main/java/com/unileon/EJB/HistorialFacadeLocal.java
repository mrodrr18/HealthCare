/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Historial;
import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author usuario
 */
@Local
public interface HistorialFacadeLocal {

    void create(Historial historial);

    void edit(Historial historial);

    void remove(Historial historial);

    Historial find(Object id);

    List<Historial> findAll();

    List<Historial> findRange(int[] range);

    int count();
    
    List<Historial> listarDiagnosticos(Usuario us);
    
}