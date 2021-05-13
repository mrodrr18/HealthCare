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
import org.primefaces.PrimeFaces;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class InventarioController implements Serializable{
    private List<Inventario> listaProductos;
    private List<Inventario> selectedProducts;
    private Inventario selectedProduct; //Igual habria que tener una clase Producto?
    
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
    
    public int numeroDeProductos(){
        return listaProductos.size();
    }
    public void nuevoProducto() {
        this.selectedProduct = new Inventario();
    }
    
    public void guardarProducto() {
        if (this.selectedProduct.getNombre() == null) {
            
            this.listaProductos.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto añadido"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public void borrarProducto() {
        this.listaProductos.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Eliminado";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        System.out.println("Tamaño seleccionados: "+selectedProducts.size());
        this.listaProductos.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos eliminados"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

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

    public Inventario getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Inventario selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    
    
    
}
