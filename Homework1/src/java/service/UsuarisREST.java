/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import model.entities.Usuari;
import org.apache.commons.codec.binary.Base64;
/**
 * 
 * @author Dounia
 * @author Pol
 */

@Stateless
@Path("rest/api/v1/customer")
public class UsuarisREST extends AbstractFacade<Usuari>{
     
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em; 
     
    public UsuarisREST(){
        super(Usuari.class); 
    }
     
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuari> findAll(){
         return super.findAll(); 
    }
    
    


@GET
@Path("{nomUsu}")
@Produces(MediaType.APPLICATION_JSON)
public Response findIdByNomUsu(@PathParam("nomUsu") String nomUsu) {
    Usuari usuario = super.findByUsername(nomUsu);
    if (usuario != null) {
        Long idUsuario = usuario.getId();
        return Response.ok().entity(idUsuario).build();
    } else {
        return Response.status(Response.Status.BAD_REQUEST).entity("El usuario no existe").build();
    }
} 
  /*  @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id){
        if (ExisteixUsuari(id)){
            return Response.ok().entity(super.find(id)).build();   
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("L'usuari no existeix").build();
        }
    }
    */
    @PUT
    @Secured
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Usuari entity) {
        super.edit(entity);
    }
    
    
    @POST
    @Secured
    @Path("userLogin")
    @Consumes ( MediaType.APPLICATION_JSON )
    public Response userLogin (@HeaderParam("Authorization") String authorizationHeader) {
            
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.decodeBase64(base64Credentials.getBytes()));

        // Separar las credenciales en nombre de usuario y contraseña
        final String[] values = credentials.split(":", 2);
        final String nomUsuari = values[0];
        final String contrasenya = values[1];
            
            
        if (verificarCredencial(nomUsuari, contrasenya)) {
            return Response.ok().build(); 
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Autentificació fallada").build(); 
        }
    }
    
    public boolean verificarCredencial(String nom, String contrasenya){
        TypedQuery<Usuari> query = em.createQuery("SELECT u FROM Usuari u WHERE u.nomUsuari = :nom", Usuari.class);
        query.setParameter("nom", nom);

        try {
            Usuari storageCredentials = query.getSingleResult();
            return storageCredentials.getContrasenya().equals(contrasenya);
        } catch (NoResultException e) {
            return false; // Usuario no encontrado
        }
           
    }
     private boolean ExisteixUsuariNom(String nomUsuari) {
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Usuari g WHERE g.nomUsuari = :nomUsuari");
        query.setParameter("nomUsuari", nomUsuari);
    
        long count = (long) query.getSingleResult();
    
        return count > 0;
        
    }
   
    
    
    private boolean ExisteixUsuari(Long id) {
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Usuari g WHERE g.id = :id");
        query.setParameter("id", id);
    
        long count = (long) query.getSingleResult();
    
        return count > 0;
        
    }
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
}