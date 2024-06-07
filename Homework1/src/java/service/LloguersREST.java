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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import authn.Secured;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.entities.Joc;
import model.entities.Lloguer;
import model.entities.Usuari;

/**
 * 
 * @author Dounia
 * @author Pol
 */

@Stateless
@Path("rest/api/v1/rental")
public class LloguersREST extends AbstractFacade<Lloguer>{
     
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em; 
     
    public LloguersREST (){
        super(Lloguer.class); 
    }
    
@POST
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response Lloguers(Lloguer entity) {
    if (entity == null) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("El objeto de entrada está vacío")
                .build();
    }
    
    if (!ExisteixUsuari(entity.getUsuari())) {
        return Response.status(Response.Status.BAD_REQUEST).entity("L'usuari no existeix").build();
    }

    if (entity.getDia().isBefore(LocalDate.now()) || entity.getDia() == null) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Data no valida")
                .build();
    }

    float preuTotal = 0.0f;  // Inicializa el precio total en cada solicitud
    
    Collection<String> joc=new ArrayList<>();

   for (Joc jocSolicitud : entity.getJocs()) {
        Long idJoc = jocSolicitud.getId();
        Joc retrievedJoc = ExisteixJoc(idJoc);
        joc.add("\n" +retrievedJoc.toString());
        if (retrievedJoc != null && retrievedJoc.getDisponibilitat()) {
            retrievedJoc.setDisponibilitat(false);
            preuTotal += retrievedJoc.getPreu();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("El videojoc no está disponible").build();
        }
    }
    entity.setPreuTotal(preuTotal);
    super.create(entity);

    List<String> list = new ArrayList<>();
    list.add("Id rebut lloguer: " + entity.getId());
    list.add("Dia de retorn: " + entity.getDia());
    list.add("Preu total lloguers: " + entity.getPreuTotal());
    list.add("Jocs:"+joc);

    return Response.status(Response.Status.OK)
            .entity(list)
            .build();
}

    @GET
    @Secured
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        return Response.ok().entity(super.find(id)).build();
    }
    
    private boolean ExisteixUsuari(Usuari entity) {
        String nomUsuari = entity.getNomUsuari();
        
        Query query = em.createQuery("SELECT COUNT(g) FROM Usuari g WHERE g.nomUsuari = :nomUsuari");
        query.setParameter("nomUsuari", nomUsuari);
    
        long count = (long) query.getSingleResult();
    
        return count > 0;
        
    }
    private Joc ExisteixJoc(Long id) {
    Query query = em.createQuery("SELECT j FROM Joc j WHERE j.id = :id");
    query.setParameter("id", id);
 
    try {
        return (Joc) query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}   