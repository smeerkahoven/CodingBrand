/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.model.search;

import java.io.Serializable;

/**
 *
 * @author xeio
 */
public class LibroSearch implements Serializable{
    
    private String titulo ;
    private String fecha ;
    private Integer numeroAutores ;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroAutores() {
        return numeroAutores;
    }

    public void setNumeroAutores(Integer numeroAutores) {
        this.numeroAutores = numeroAutores;
    }
    
    
    
}
