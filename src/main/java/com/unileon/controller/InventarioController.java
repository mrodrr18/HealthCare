/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.InventarioFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Inventario;
import com.unileon.modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author mrrtr
 */
@Named
@ViewScoped
public class InventarioController implements Serializable{
    
    private List<Inventario> listaProductos;
    
    private List<Inventario> selectedProducts;
    
    private Inventario selectedProduct;
    
    private Inventario nuevo;
    
    private Inventario editar;
    
    private Inventario borrar;
    
    @EJB
    private InventarioFacadeLocal inventarioEJB;
    
    @PostConstruct
    public void init(){
       listaProductos = inventarioEJB.findAll();
       nuevo = new Inventario();
       editar = new Inventario();
       borrar = new Inventario();
    }
    
    public int numeroDeProductos(){
        return listaProductos.size();
    }
    
    public void guardarProducto() {
        if (this.selectedProduct.getNombre() == null) {
            System.out.println("Producto a null");
            //this.listaProductos.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto añadido"));
        }
        else {
            System.out.println("Producto " + this.selectedProduct.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    
    public void cambiarProductoEditar(Inventario i){
        System.out.println("Cambiar editar Producto");
        editar = i;
    }
   
    public String borrarProducto() {
        //this.inventarioEJB.remove(nuevo);
        System.out.println("Borrar Producto");
        return "/privado/formularioBorrarProducto?faces-redirect=true";
    }
    
    public String guardarProductoEditado(){
        
        if(editar == null) System.out.println("El producto a editar es null");
        else{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            Inventario i = inventarioEJB.consultarInventario(editar.getNombre());
            if (i == null){ System.out.println("El producto no está registrado");
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","El producto no está registrado"));
            }else{
                editar.setIdProducto(i.getIdProducto());
                editar.setUsuario(us);
                inventarioEJB.edit(editar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","El producto se actualizó correctamente"));
            }
            
            //System.out.println("He editado el producto" + i.getNombre());
        }
       
        return null;
    }
    public String irAlInventario(){
         
        return "/privado/inventarioVista?faces-redirect=true";
    }
    
    public String eliminarProductoInventario(){
        System.out.println("He eliminado el producto del inventario" + borrar.getNombre());
        try{
            if(borrar == null){
                System.out.println("El producto a borrar es null");
            }
            else{
                Inventario i = inventarioEJB.consultarInventario(borrar.getNombre());
                if (i == null){
                    System.out.println("El producto no está registrado");
                }
                else{
                    inventarioEJB.remove(i);
                }

                //System.out.println("He editado el producto" + i.getNombre());
            }
        }catch(Exception e){
            System.err.println("Error al borrar producto");
        }
        
        return "/privado/inventarioVista?faces-redirect=true";
    }
    
    public String nuevoProducto(){
        System.out.println("HOLA");
        try{
            System.out.println("Entro a nuevo producto");
            Inventario i = inventarioEJB.consultarInventario(nuevo.getNombre());
            if (i == null){
                Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
                nuevo.setUsuario(us);
                inventarioEJB.create(nuevo);
            }
            else{
                System.out.println("El producto ya existe");
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso: Registro Completado","Aviso"));
            
        }catch(Exception e){
            System.err.println("Error al insertar producto");
        }
        return "/privado/inventarioVista?faces-redirect=true";
        
    }
    public String editarProducto(){
        System.out.println("HOLA SOY EDITAR");
        return "/privado/formularioEditarProducto?faces-redirect=true";
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
    
    public void openNew() {
        this.selectedProduct = new Inventario();
        
       System.out.println(listaProductos.size());
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

    public Inventario getNuevo() {
        return nuevo;
    }

    public void setNuevo(Inventario nuevo) {
        this.nuevo = nuevo;
    }

    public Inventario getEditar() {
        return editar;
    }

    public void setEditar(Inventario editar) {
        this.editar = editar;
    }

    public InventarioFacadeLocal getInventarioEJB() {
        return inventarioEJB;
    }

    public void setInventarioEJB(InventarioFacadeLocal inventarioEJB) {
        this.inventarioEJB = inventarioEJB;
    }

    public Inventario getBorrar() {
        return borrar;
    }

    public void setBorrar(Inventario borrar) {
        this.borrar = borrar;
    }

    
    
    
    
    
}
