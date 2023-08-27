    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todosalau.Libreria.Modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Karen
 */
@Table("libreria")
public class Libreria {
   
    @Id
    @Column("idLibreria")
    private Long idLibreria;

    @Column("LibreriaNombre")
    private String LibreriaNombre;

    @Column("LibreriaPrecio")
    private Long LibreriaPrecio;
    
    @Column("LibreriaCantidad")
    private Long LibreriaCantidad;

    public Libreria(Long idLibreria, String LibreriaNombre, Long LibreriaPrecio, Long LibreriaCantidad ) {
        this.idLibreria = idLibreria;
        this.LibreriaNombre = LibreriaNombre;
        this.LibreriaPrecio = LibreriaPrecio;
        this.LibreriaCantidad = LibreriaCantidad;
    }    
    public Long getIdLibreria() {
        return idLibreria;
    }

    public void setIdLibreria(Long idLibreria) {
        this.idLibreria = idLibreria;
    }

    public String getLibreriaNombre() {
        return LibreriaNombre;
    }

    public void setLibreriaNombre(String LibreriaNombre) {
        this.LibreriaNombre = LibreriaNombre;
    }

    public Long getLibreriaPrecio() {
        return LibreriaPrecio;
    }

    public void setLibreriaPrecio(Long LibreriaPrecio) {
        this.LibreriaPrecio = LibreriaPrecio;
    }

    public Long getLibreriaCantidad() {
        return LibreriaCantidad;
    }

    public void setLibreriaCantidad(Long LibreriaCantidad) {
        this.LibreriaCantidad = LibreriaCantidad;
    }
    
    // un ejemplo de sobreescritura de metodos
    public static Libreria CrearLibreria(Long idLibreria,String LibreriaNombre,Long LibreriaPrecio,Long LibreriaCantidad){
      return new Libreria (idLibreria,LibreriaNombre,LibreriaPrecio,LibreriaCantidad);   
    } 
    
    public static Libreria CrearLibreria(String LibreriaNombre,Long LibreriaPrecio,Long LibreriaCantidad){
      return new Libreria (null,LibreriaNombre,LibreriaPrecio,LibreriaCantidad);   
    }
    
    public static Libreria CrearLibreria(){
      return new Libreria (null,null,null,null);   
    }    
}

