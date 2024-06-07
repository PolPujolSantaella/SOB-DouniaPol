/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;


import jakarta.ws.rs.core.Response;
import java.util.Base64;

/**
 *
 * @author douni
 */
public class UsuariCredencialsImpl implements UsuariCredencials {
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources/rest/api/v1/customer";
    
    public UsuariCredencialsImpl() {
       this.client = ClientBuilder.newClient();
    }
    
    
    @Override
    public boolean login(String username, String password)  {
        // Construir la URL del endpoint de inicio de sesión
        String loginUrl = BASE_URI + "/userLogin";

        // Codificar las credenciales en formato Base64
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        boolean loggedIn;
        // Comprobar si la solicitud fue satisfactoria (código de estado 200)
        try ( Response response = client.target(loginUrl)
                        .request()
                        .header("Authorization", "Basic " + base64Credentials)
                        .post(Entity.json(""))) {
            // Comprobar si la solicitud fue satisfactoria (código de estado 200)
            loggedIn = response.getStatus() == 200;
        } 
        

        return loggedIn;
    }
    @Override

public Long findUser(String usu) {
    String loginUrl = BASE_URI + "/" + usu; // Agrega una barra antes del nombre de usuario
    Response response = client.target(loginUrl)
                             .request(MediaType.APPLICATION_JSON)
                             .get();

    if (response.getStatus() == 200) {
        // Lee el ID del usuario desde la respuesta
        Long idUsuario = response.readEntity(Long.class);
        return idUsuario;
    }
    return null; // Devuelve null si la respuesta no fue exitosa o si el usuario no existe
}

}