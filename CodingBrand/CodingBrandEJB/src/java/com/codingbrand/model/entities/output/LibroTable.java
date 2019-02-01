/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.model.entities.output;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xeio
 */
public class LibroTable implements Serializable {

    private Integer idLibro;
    private String titulo;
    private String fechaEdicion;
    private Integer numeroAutores;

    public LibroTable(Integer idLibro, String titulo, String fechaEdicion, Integer numeroAutores) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.fechaEdicion = fechaEdicion;
        this.numeroAutores = numeroAutores;
    }

    public LibroTable() {
    }
    
    

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(String fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }



    public Integer getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(Integer numeroAutores) {
        this.numeroAutores = numeroAutores;
    }

}
