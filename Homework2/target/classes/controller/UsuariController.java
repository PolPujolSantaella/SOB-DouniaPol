package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.UsuariCredencials;
import deim.urv.cat.homework2.model.UsuariCredencialsBean;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.security.CsrfProtected;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Controller
@Path("LogIn")
public class UsuariController {

    @Inject
    private UsuariCredencials service;

    @Inject
    private UsuariCredencialsBean credentials;

    @Inject
    private Models models;

    @GET
    public String showForm() {
        models.put("error", null);
        return "login-form.jsp"; 
    } 
    
    @POST
    @UriRef("log-in")
    @CsrfProtected
    public String login(@FormParam("username") String username, @FormParam("password") String password) {
        boolean authenticated = service.login(username, password);
        
        
        if (authenticated) {
            // Si la autenticación es correcta, guardar las credenciales en la clase Credentials
            Long id=  service.findUser(username);
            credentials.setCredencials(id, username, password);
            
            return "login-succes.jsp";
        } else {
            // Si la autenticación falla, mostrar un mensaje de error
            credentials.init();
            models.put("error", "Credenciales incorrectas");
            return "login-form.jsp";
        }
    }
    
    @POST
    @Path("LogOut")
    public void logout() {
      credentials.init();
 
    }
}
