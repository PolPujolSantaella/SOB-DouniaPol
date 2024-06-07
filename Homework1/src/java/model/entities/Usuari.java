/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author Dounia
 * @author Pol
 */

@Entity
@XmlRootElement
public class Usuari implements Serializable {
    private static final long serialVersionUID = 1L; 
    @Id
    @SequenceGenerator(name="Usuari_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuari_Gen") 
    private Long id;
     @Column(unique=true)
     private String nomUsuari;
     private String contrasenya; 
     
    @JsonbTransient
    @OneToMany(mappedBy="usuari")
    private Set<Lloguer> lloguers; 
    
    // Getters y Setters

    public Set<Lloguer> getLloguers(){
        return lloguers;
    }
    public void setLloguers(Set<Lloguer> lloguers){
        this.lloguers= lloguers;
    }
    
    public void setContrasenya(String contrasenya){
       this.contrasenya=contrasenya; 
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }
     public String getContrasenya() {
        return contrasenya;
    }

   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuari)) {
            return false;
        }
        Usuari other = (Usuari) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

@Override
    public String toString() {
        return "model.entities.Usuaris[ id=" + id + " nomUsuari="+ nomUsuari + "]";
    }
}

