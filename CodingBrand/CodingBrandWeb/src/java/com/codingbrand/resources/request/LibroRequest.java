/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.resources.request;

import com.codingbrand.model.search.LibroSearch;
import java.io.Serializable;

/**
 *
 * @author xeio
 */
public class LibroRequest implements Serializable {
    private LibroSearch search ;

    public LibroSearch getSearch() {
        return search;
    }

    public void setSearch(LibroSearch search) {
        this.search = search;
    }
    
    
}
