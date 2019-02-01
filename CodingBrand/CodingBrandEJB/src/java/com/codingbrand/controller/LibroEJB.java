/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.controller;

import com.codingbrand.exceptions.LibroException;
import com.codingbrand.model.entities.Autor;
import com.codingbrand.model.entities.AutorLibro;
import com.codingbrand.model.entities.Libro;
import com.codingbrand.model.entities.output.LibroTable;
import com.codingbrand.model.search.LibroSearch;
import com.codingbrand.remote.LibroRemote;
import com.codingbrand.utils.DateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xeio
 */
public class LibroEJB implements LibroRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertar(Libro libro, List<Autor> autores) throws LibroException {

        em.persist(libro);

        em.flush();

        for (Autor a : autores) {
            AutorLibro al = new AutorLibro();
            al.setIdAutor(a);
            al.setIdLibro(libro);

            em.persist(al);
        }

    }

    @Override
    public void editar(Libro libro, List<AutorLibro> remove, List<Autor> add) throws LibroException {

        Libro fromDb = em.find(Libro.class, libro.getIdLibro());

        if (fromDb != null) {

            fromDb.setFechaEdicion(libro.getFechaEdicion());
            fromDb.setTitulo(libro.getTitulo());

            em.merge(libro);
        }

        //elimina las relacion autor libro
        if (!remove.isEmpty()) {
            for (AutorLibro a : remove) {
                em.remove(a);
            }
        }

        //crea una relacion entre autor y libro
        if (!add.isEmpty()) {
            for (Autor a : add) {
                AutorLibro al = new AutorLibro();
                al.setIdAutor(a);
                al.setIdLibro(fromDb);

                em.persist(al);
            }
        }

        em.flush();
    }

    @Override
    public List<LibroTable> listar() throws LibroException {
        Query query = em.createNamedQuery("Libro.listLibros");

        List<LibroTable> l = query.getResultList();

        return l;
    }


    @Override
    public List<LibroTable> buscar(LibroSearch search) throws LibroException {

        String q = "select idLibro, titulo, fechaEdicion, autores \n"
                + "          from ( \n"
                + "              select distinct l.id_libro idLibro, \n"
                + "                  l.titulo,\n"
                + "                  l.fecha_edicion fechaEdicion,\n"
                + "                  count(*) autores \n"
                + "              from libro l \n"
                + "              inner join autor_libro al on l.id_libro = al.id_libro \n"
                + "              group by l.id_libro, l.titulo, l.fecha_edicion\n"
                + ") A \n" + " WHERE 1=1  ";

        if (search.getTitulo() != null) {
            q += " and A.titulo =?1 \n";
        }

        if (search.getFecha() != null) {
            q += " and A.fecha=?2 \n";
        }

        if (search.getNumeroAutores() != null) {
            q += " and A.numeroAutores =?3";
        }

        q += " orde by A.titulo, A.fechaEdicion, A.autores ";

        Query query = em.createQuery(q, LibroTable.class);

        if (search.getTitulo() != null) {
            query.setParameter("1", search.getTitulo());
        }

        if (search.getFecha() != null) {

            query.setParameter("2", DateUtil.toLatinAmericaDateFormat(search.getFecha()));
        }
        
        return query.getResultList() ;
        

    }

}
