/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.resources.services;

import com.codingbrand.exceptions.LibroException;
import com.codingbrand.model.entities.output.LibroTable;
import com.codingbrand.model.search.LibroSearch;
import com.codingbrand.remote.LibroRemote;
import com.codingbrand.resources.request.LibroRequest;
import com.codingbrand.resources.response.LibroResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author xeio
 */
@Path("libro")
@RequestScoped
public class LibroResource {

    @EJB
    private LibroRemote ejbLibro ;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LibroResource
     */
    public LibroResource() {
    }

    /**
     * Retrieves representation of an instance of com.codingbrand.resources.services.LibroResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of LibroResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("{idLibro}")
    @Produces(MediaType.APPLICATION_JSON)
    public LibroResponse getAutoresDeLibro (@PathParam("idLibro") Integer idLibro) {
        
        return null;
    }
    
    
    @PUT
    @Produces (MediaType.APPLICATION_JSON)
    public LibroResponse updateLibro(final LibroRequest request){
        
        LibroResponse response = new LibroResponse();
        
        
        
        return response ;
    }
    
    
    // POST porque se manda datos para busqueda, el titulo es muy largo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LibroResponse buscarLibro(final LibroRequest request){
        LibroResponse response = new LibroResponse();
        try {
            
            
            LibroSearch search = request.getSearch() ;
            //validar si el search es null
            List<LibroTable> l = ejbLibro.buscar(search);
            
            if (l.isEmpty()){
                response.setMessage("La lista se encuentra vacia");
                response.setData(new LinkedList<>());
            }else {
                response.setMessage("Datos Encontrados");
                response.setData(l);
            }
            
            return response ;
        } catch (LibroException ex) {
            Logger.getLogger(LibroResource.class.getName()).log(Level.SEVERE, null, ex);
            response.setMessage(ex.getMessage());
        }
        
        return response ;
    }
}
