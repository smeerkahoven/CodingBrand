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
    
    private Integer idLibro ;
    private String titulo ;
    private Date edicion ;
    private Integer numeroAutores ;

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

    public Date getEdicion() {
        return edicion;
    }

    public void setEdicion(Date edicion) {
        this.edicion = edicion;
    }

    public Integer getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(Integer numeroAutores) {
        this.numeroAutores = numeroAutores;
    }


    
    
}
