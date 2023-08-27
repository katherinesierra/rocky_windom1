/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todosalau.Libreria.Controlador;

import com.todosalau.Libreria.Modelo.Libreria;
import com.todosalau.Libreria.Modelo.RepositorioLibreria;
import com.todosalau.Libreria.Vista.BibliotecaLibros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karen
 */
public class Controlador implements ActionListener {

    BibliotecaLibros vista; // variable global de una clase
    RepositorioLibreria repositorioLibreria; // variable global de una clase
    Libreria libreria; // variable global de una clase
    DefaultTableModel Modelo = new DefaultTableModel();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getListarjButton()) {
            ListarClick(e);
        }
        if (e.getSource() == vista.getAgregarButton()) {
            AgregarLibroClick(e);
        }
        if (e.getSource() == vista.getActualizarButton()) {
            ActualizarClick(e);
        }
        if (e.getSource() == vista.getBorrarButton()) {
            BorrarClick(e);
        }
         if (e.getSource() == vista.getAuditoriaButton()) {
            AuditoriaClick(e);
        }
    }

    public Controlador() {
        super();
    }

    public Controlador(RepositorioLibreria repositorioLibreria, BibliotecaLibros vista) {
        super();
        this.vista = vista;
        this.repositorioLibreria = repositorioLibreria;
        vista.setVisible(true);
        AgregarEvento();
    }

    public void AgregarEvento() {
        this.vista.getActualizarButton().addActionListener(this);
        this.vista.getAgregarButton().addActionListener(this);
        this.vista.getAuditoriaButton().addActionListener(this);
        this.vista.getBorrarButton().addActionListener(this);
        this.vista.getListarjButton().addActionListener(this);
    }

    public void ListarClick(ActionEvent e) {
        JTable Tabla = vista.getTabla();
        List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll();
        int cont = 0;
        for (Libreria s : listaLibreria) {
            Tabla.setValueAt(s.getIdLibreria(), cont, 0);
            Tabla.setValueAt(s.getLibreriaNombre(), cont, 1);
            Tabla.setValueAt(s.getLibreriaCantidad(), cont, 3);
            Tabla.setValueAt(s.getLibreriaPrecio(), cont, 2);
            cont++;
        }
        Tabla.setValueAt("", cont, 0);
        Tabla.setValueAt("", cont, 1);
        Tabla.setValueAt("", cont, 2);
        Tabla.setValueAt("", cont, 3);
    }

    public void AgregarLibroClick(ActionEvent e) {

        JTextField jTextField2 = vista.getjTextField2();
        JTextField jTextField3 = vista.getjTextField3();
        JTextField jTextField4 = vista.getjTextField4();
        if (jTextField2.getText().isEmpty() 
                || jTextField3.getText().isEmpty() ||
                jTextField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(vista,"Todos los campos son obligatorios"
                    ," Pendiente",  JOptionPane.WARNING_MESSAGE);       
        }
        else {
         
        String LibreriaNombreBusqueda = jTextField2.getText();
        String LibreriaPrecioBusqueda = jTextField3.getText();
        String LibreriaCantidadBusqueda = jTextField4.getText();

        libreria = Libreria.CrearLibreria(
                LibreriaNombreBusqueda,
                Long.valueOf(LibreriaPrecioBusqueda),
                Long.valueOf(LibreriaCantidadBusqueda)
        );
        repositorioLibreria.save(libreria);
        ListarClick(e);
            }
    }

    public void ActualizarClick(ActionEvent e) {
       
        JTextField jTextField1 = vista.getjTextField1();
        JTextField jTextField2 = vista.getjTextField2();
        JTextField jTextField3 = vista.getjTextField3();
        JTextField jTextField4 = vista.getjTextField4();
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty() 
                || jTextField3.getText().isEmpty() ||
                jTextField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(vista,"todos los campos son obligatorios"
                    ," Pendiente",  JOptionPane.WARNING_MESSAGE);
            
        }
        else {
      
        Long idBusqueda = Long.valueOf(jTextField1.getText());
        String LibreriaNombreParaGuardar = jTextField2.getText();
        String LibreriaPrecioParaGuardar = jTextField3.getText();
        String LibreriaCantidadParaGuardar = jTextField4.getText();

        libreria = repositorioLibreria.findById(idBusqueda).get();

        libreria.setLibreriaNombre(LibreriaNombreParaGuardar);
        libreria.setLibreriaPrecio(Long.valueOf(LibreriaPrecioParaGuardar));
        libreria.setLibreriaCantidad(Long.valueOf(LibreriaCantidadParaGuardar));

        repositorioLibreria.save(libreria);
        ListarClick(e);
       }
    }

    public void BorrarClick(ActionEvent e) {
        JTextField jTextField1 = vista.getjTextField1();
        Long idBusqueda = Long.valueOf(jTextField1.getText());
        if (repositorioLibreria.existsById(idBusqueda)) {
            repositorioLibreria.deleteById(idBusqueda);
        }
        ListarClick(e);
    }

    public void AuditoriaClick(ActionEvent e) {
        int Cantidad = ContarLibros();
        String LibroMenor = MenorCantida(Long.valueOf(Cantidad));
        int TotalDinero = TotalDinero();
        String Libromayor = MayorCantidad();
        String LibroCostoso = LibroMasCostoso(Long.valueOf(Cantidad));
        String LibroEconomico = LibroMasEconomico(Long.valueOf(TotalDinero));
        JOptionPane.showMessageDialog(vista,
                "La cantidad de libros es :"+ Integer.toString(Cantidad)
                +"\nLibro de menor cantidad es :"+ LibroMenor
                +"\ntotal dinero es :" + TotalDinero+
                "\nel libro mayor cantidad es :" + Libromayor
                +"\nEl libro mas costoso es :"+LibroCostoso
                +"\nEL Libro Economico :"+LibroEconomico );
        
      
    }

    public int ContarLibros(){
     List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     int Cantidad = 0;
        for (Libreria s : listaLibreria) {
            
            Cantidad += s.getLibreriaCantidad();
            cont++;
        }
        return Cantidad;
    }
    
    public String MenorCantida(Long cant){
     List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     Long Cantidad = cant;
     String Nombre = "";
        for (Libreria s : listaLibreria) {
           if (s.getLibreriaCantidad()< Cantidad){
               Cantidad=s.getLibreriaCantidad();
               Nombre = s.getLibreriaNombre();
               cont++; 
           } 
            
        }
        return Nombre;
    }
    
    public int TotalDinero (){
     List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     int TotalDinero = 0;
        for (Libreria s : listaLibreria) {
            
            TotalDinero += (s.getLibreriaPrecio()*s.getLibreriaCantidad());
            cont++;
        }
        return TotalDinero;
    }
    
    public String MayorCantidad(){
       List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     Long Cantidad = 0L;
     String Nombre = "";
        for (Libreria s : listaLibreria) {
           if (s.getLibreriaCantidad()> Cantidad){
               Cantidad=s.getLibreriaCantidad();
               Nombre = s.getLibreriaNombre();
               cont++; 
           } 
            
        }
        return Nombre;
    }
    
    public String LibroMasCostoso(Long cant){
     List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     long Cantidad = cant;
     String Nombre = "";
        for (Libreria s : listaLibreria) {
           if (s.getLibreriaPrecio()> Cantidad){
               Cantidad =s.getLibreriaPrecio();
               Nombre = s.getLibreriaNombre();
               cont++; 
           } 
            
        }
        return Nombre;
    }
    public String LibroMasEconomico(Long cant){
     List<Libreria> listaLibreria = (List<Libreria>) repositorioLibreria.findAll(); 
     int cont = 0;
     Long Cantidad = cant;
     String Nombre = "";
        for (Libreria s : listaLibreria) {
           if (s.getLibreriaPrecio()< Cantidad){
               Cantidad=s.getLibreriaPrecio();
               Nombre = s.getLibreriaNombre();
               cont++; 
           } 
            
        }
        return Nombre;
    }
}

 
