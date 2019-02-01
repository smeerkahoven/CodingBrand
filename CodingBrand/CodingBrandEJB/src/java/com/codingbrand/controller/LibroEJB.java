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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xeio
 */
@Stateless
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

        String q = "select idLibro, titulo, fechaEdicion, numeroAutores \n"
                + "          from ( \n"
                + "              select distinct l.id_libro idLibro, \n"
                + "                  l.titulo,\n"
                + "                  l.fecha_edicion fechaEdicion,\n"
                + "                  count(*) numeroAutores \n"
                + "              from libro l \n"
                + "              inner join autor_libro al on l.id_libro = al.id_libro \n"
                + "              group by l.id_libro, l.titulo, l.fecha_edicion \n"
                + "             ) A \n"
                + " WHERE 1=1  ";

        if (search != null) {
            if (search.getTitulo() != null) {
                q += " and A.titulo =?1 ";
            }

            if (search.getFecha() != null) {
                q += " and A.fecha=?2 ";
            }

            if (search.getNumeroAutores() != null) {
                q += " and A.numeroAutores = ?3";
            }
        }

        q += " order by A.titulo, A.fechaEdicion, A.numeroAutores ";

        Query query = em.createNativeQuery(q);

        if (search != null) {
            if (search.getTitulo() != null) {
                query.setParameter("1", search.getTitulo());
            }

            if (search.getFecha() != null) {
                query.setParameter("2", DateUtil.toLatinAmericaDateFormat(search.getFecha()));
            }

            if (search.getNumeroAutores() != null) {
                query.setParameter("3", search.getNumeroAutores());
            }

        }

        List<Object[]> l = query.getResultList();

        List<LibroTable> r = new LinkedList<LibroTable>();

        if (!l.isEmpty()) {
            for (Object[] o : l) {
                LibroTable libroTable = new LibroTable();
                libroTable.setIdLibro((Integer) o[0]);
                libroTable.setTitulo((String) o[1]);
                libroTable.setFechaEdicion(DateUtil.toStringDate((Date) o[2]));
                libroTable.setNumeroAutores(((Long) o[3]).intValue());

                r.add(libroTable);
            }
        }

        return r;

    }

}
