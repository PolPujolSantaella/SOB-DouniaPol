/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import jakarta.persistence.Query;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import model.entities.Joc;

/**
 * 
 * @author Dounia
 * @author Pol
 */

@Stateless
 @Path("rest/api/v1/game")
public class VideoJocsREST extends AbstractFacade<Joc>{
     
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em; 
     
    public VideoJocsREST (){
        super(Joc.class); 
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@QueryParam("type") String type, @QueryParam("console") String console){
        
        if ((type != null && !ValidType(type)) ||  (console != null && !ValidConsole(console))){
            return Response.status(Response.Status.BAD_REQUEST).entity("NOT FOUND").build(); 
        }
        else {
            return Response.ok().entity(super.findByTypeOrConsole(type, console)).build(); 
        }
    }
    
    
    @POST
   // @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response crearJoc(Joc entity){
        if (ExisteixJoc(entity)){
            return Response.status(Response.Status.CONFLICT).entity("CONFLICT").build(); 
        }
        else {
            super.create(entity);
            return Response.status(Response.Status.CREATED).entity("CREAT").build(); 
        } 
    }
  

    private boolean ValidType (String tipo){
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Joc g JOIN g.topic t WHERE t.name = :tipo"); 
        query.setParameter("tipo", tipo); 
        long count = (long) query.getSingleResult(); 
        
        return count > 0; 
    }
    
    private boolean ValidConsole (String console){
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Joc g JOIN g.consola t WHERE t.name = :consola"); 
        query.setParameter("consola", console); 
        long count = (long) query.getSingleResult(); 
        
        return count > 0; 
    }
    
    private boolean ExisteixJoc(Joc entity) {
        String nom = entity.getNom(); 
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Joc g WHERE g.nom = :nom");
        query.setParameter("nom", nom);
    
        long count = (long) query.getSingleResult();
    
        return count > 0;
        
    }

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}