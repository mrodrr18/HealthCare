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
public class EditarProductoController implements Serializable{
    
    private List<Inventario> listaProductos;
    
    private List<Inventario> selectedProducts;
    
    private List<String> nombres;
    
    private Inventario selectedProduct;
    
    private Inventario nuevo;
    
    private Inventario editar;
    
    private String nombreEditar;
    
    private Inventario borrar;
    
    @EJB
    private InventarioFacadeLocal inventarioEJB;
    
    @PostConstruct
    public void init(){
       listaProductos = inventarioEJB.findAll();
       editar = this.recogerEditar();
    }
    
    public Inventario recogerEditar(){
        Inventario i = (Inventario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editar");
        return i;
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
    
    public String irAlInicio(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(us.getTipo() == 3) return "/privado/administrador/inicioAdministrador?faces-redirect=true";
        else if(us.getTipo() == 1) return "/privado/auxiliar/inicioAuxiliar?faces-redirect=true";
        else return "/publico/sinPrivilegios?faces-redirect=true";
    }
   
    public String borrarProducto() {
        //this.inventarioEJB.remove(nuevo);
        System.out.println("Borrar Producto");
        return "/privado/formularioBorrarProducto?faces-redirect=true";
    }
    
    public String guardarProductoEditado(){
        
        if(editar == null) {
            System.out.println("El producto a editar es null");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error."," Debes escribir el nombre del producto que quieres editar."));
        }
        else{
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            Inventario i = inventarioEJB.consultarInventario(editar.getNombre());
            if (i == null){ 
                System.out.println("El producto no está registrado");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.","El producto no está registrado"));
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
    
    
    
    public String editarProducto(){
        System.out.println("HOLA SOY EDITAR");
        return "/privado/escogerProductoEditar?faces-redirect=true";
    }
    
    
    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Eliminado";
    }
    
    public String siguienteEditar(){
        Inventario e = inventarioEJB.consultarInventario(nombreEditar);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("editar", e);
        return "/privado/formularioEditarProducto?faces-redirect=true";

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

    public List<String> getNombres() {
        return nombres;
    }

    public void setNombres(List<String> nombres) {
        this.nombres = nombres;
    }

    public String getNombreEditar() {
        return nombreEditar;
    }

    public void setNombreEditar(String nombreEditar) {
        this.nombreEditar = nombreEditar;
    }

    
    
    
    
    
}
