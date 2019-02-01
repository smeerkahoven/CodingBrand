/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author xeio
 */
@Entity
@Table(name = "autor_libro")
@NamedQueries({
    @NamedQuery(name = "AutorLibro.findAll", query = "SELECT a FROM AutorLibro a")})
public class AutorLibro implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor")
    private Autor idPromotor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro")
    private Autor idLibro;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id_autor_libro")
    private Integer idAutorLibro;

    public Autor getIdPromotor() {
        return idPromotor;
    }

    public void setIdPromotor(Autor idPromotor) {
        this.idPromotor = idPromotor;
    }

    public Autor getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Autor idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getIdAutorLibro() {
        return idAutorLibro;
    }

    public void setIdAutorLibro(Integer idAutorLibro) {
        this.idAutorLibro = idAutorLibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutorLibro != null ? idAutorLibro.hashCode() : 0);
        return hash;
    }

        @Override
    public String toString() {
        return "com.codingbrand.model.entities.AutorLibro[ idAutorLibro=" + idAutorLibro + " ]";
    }
}
