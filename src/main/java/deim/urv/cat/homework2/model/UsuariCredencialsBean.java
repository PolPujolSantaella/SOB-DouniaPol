package deim.urv.cat.homework2.model;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@SessionScoped
@Named("UsuariCredencialsBean")
public class UsuariCredencialsBean implements Serializable {

    @NotBlank
    @MvcBinding
    private String username = ""; // Inicializar con valor por defecto

    @NotBlank
    @MvcBinding
    private String password = ""; // Inicializar con valor por defecto
    
    private Long id ;
    private boolean auth = false; 

    public UsuariCredencialsBean() {
      
    }

    @PostConstruct
    public void init() {
        this.username = "";
        this.password = "";
        this.id=null;
        this.auth=false;
    }

    public void setCredencials(Long id,String username, String password) {
        this.username = username;
        this.password = password;
        this.id=id;
        this.auth = true; 
    }
    
    public boolean getAuth(){
        return auth; 
    }
    
    public void setAuth(Boolean auth){
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }
  public Long getId() {
        return id;
    }
 public void setId(Long id) {
        this.id =id;
    }

    public void setUsername(String username) {
        this.username = (username == null) ? "" : username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (password == null) ? "" : password;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }
}
