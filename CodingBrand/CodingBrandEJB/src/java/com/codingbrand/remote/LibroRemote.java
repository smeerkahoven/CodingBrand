/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.remote;

import com.codingbrand.exceptions.LibroException;
import com.codingbrand.model.entities.Autor;
import com.codingbrand.model.entities.AutorLibro;
import com.codingbrand.model.entities.Libro;
import com.codingbrand.model.entities.output.LibroTable;
import com.codingbrand.model.search.LibroSearch;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author xeio
 */
@Remote
public interface LibroRemote {
    /**
     * Agrega nuevo Libro, envia una lista de autores para relacionar
     * @param libro
     * @param autores
     * @throws LibroException 
     */
    public void insertar(Libro libro, List<Autor> autores) throws LibroException ;
    
    /**
     * Edita un libro y enviar una lista para remover  y otra para anhadir
     * @param libro
     * @param remove
     * @param add
     * @throws LibroException 
     */
    public void editar(Libro libro, List<AutorLibro> remove, List<Autor> add)  throws LibroException ;
    
    /**
     * Lista los libros de aceurdo a Nombre, Fecha, numeros autores
     * @return
     * @throws LibroException 
     */
    
    public List<LibroTable> listar() throws LibroException ;
    
    
    /**
     * Busca  Libros de acuerdo a los parametros de search
     * @param search
     * @return
     * @throws LibroException 
     */
    public List<LibroTable> buscar(LibroSearch search) throws LibroException ;
    
    
}
