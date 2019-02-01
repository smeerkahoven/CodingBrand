/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.model.entities;

import com.codingbrand.model.entities.output.LibroTable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author xeio
 */
@Entity
@Table(name = "libro")
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")})

@NamedNativeQuery(
        name = "Libro.listLibros",
        query = "select idLibro, titulo, fechaEdicion, autores \n"
        + "from (\n"
        + "	select distinct l.id_libro idLibro, \n"
        + "			l.titulo,\n"
        + "			l.fecha_edicion fechaEdicion,\n"
        + "			count(*) numeroAutores \n"
        + "	 from libro l\n"
        + "	inner join autor_libro al on l.id_libro = al.id_libro\n"
        + "	group by l.id_libro, l.titulo, l.fecha_edicion\n"
        + ") A\n"
        + "order by A.titulo, A.fechaEdicion, A.numeroAutores",
        resultSetMapping = "ListarLibros"
)

@SqlResultSetMapping(
        name = "ListarLibros",
        classes = @ConstructorResult(
                targetClass = LibroTable.class,
                columns = {
                    @ColumnResult(name = "idLibro", type = Integer.class)
                    ,@ColumnResult(name = "titulo", type = String.class)
                    ,@ColumnResult(name = "edicion", type = Date.class)
                    ,@ColumnResult(name = "numeroAutores", type = Integer.class),}
        )
)
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Size(max = 64)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "fecha_edicion")
    @Temporal(TemporalType.DATE)
    private Date fechaEdicion;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
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

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.codingbrand.model.entities.Libro[ idLibro=" + idLibro + " ]";
    }

}
