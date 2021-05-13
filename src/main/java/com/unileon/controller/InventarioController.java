/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Inventario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class InventarioController implements Serializable{
    private List<Inventario> listaProductos;
    private List<Inventario> selectedProducts;
    
    @PostConstruct
    public void init(){
       listaProductos = new ArrayList <Inventario>();
       selectedProducts = new ArrayList <Inventario>();
       Inventario producto = new Inventario();
       producto.setNombre("Jeringuilla");
       producto.setDescripcion("MARCA TAL");
       producto.setUnidades(2);
       listaProductos.add(producto);
       
       producto = new Inventario();
       producto.setNombre("Anestesia");
       producto.setDescripcion("MARCA TAL");
       producto.setUnidades(8);
       listaProductos.add(producto);
    }
    
    /*public void insertarProducto(){
        
        try{
            
            System.out.println("Producto  insertado");
                    
        }catch(Exception e){
            System.err.println("Error al insertar usuario");
        }
        
    }*/
    
    /*public void borrarProducto(){
        
        try{
            
            System.out.println("Producto  insertado");
                    
        }catch(Exception e){
            System.err.println("Error al insertar usuario");
        }
        
    }*/

    public List<Inventario> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Inventario> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Inventario> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Inventario> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    
    
}
