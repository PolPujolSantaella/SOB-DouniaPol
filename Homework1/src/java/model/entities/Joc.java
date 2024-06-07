/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author Dounia
 * @author Pol
 */

@Entity
@XmlRootElement
public class Joc implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    @Id
    @SequenceGenerator(name="Joc_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Joc_Gen") 
    private Long id;
    private String nom;
    private String descripcio;
    private String adress;
    private boolean disponibilitat;
    private Float preu;
    
    @ManyToOne 
    @JoinColumn(name = "consola_id")
    private Consola consola; 
    
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    
    @JsonbTransient
    @ManyToMany(mappedBy="jocs")
    private Collection<Lloguer> lloguers;
    
    
    // Getters y Setters
    
    public Collection<Lloguer> getLloguers(){
        return lloguers;
    }
    public void setLloguers(Collection<Lloguer> lloguers){
        this.lloguers= lloguers;
    }
      public String getAdress(){
        return adress;
    }

    public void setAdreça(String adress) {
        this.adress = adress;
    }
    
    public Float getPreu() {
        return preu;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }
    
    public boolean getDisponibilitat(){
        return disponibilitat;
    }
    
    public void setDisponibilitat(boolean disponibilitat) {
        this.disponibilitat = disponibilitat;
    }
    public Consola getConsola(){
        return consola; 
    }
    
    public void setConsola(Consola consola){
        this.consola = consola; 
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
        if (!(object instanceof Joc)) {
            return false;
        }
        Joc other = (Joc) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

        @Override
    public String toString() {
        return "model.entities.Jocs[ id=" + id + " nom "+ nom + " consola "+ consola + " topic="+ topic + "descripcio=" + descripcio + 
                " disponibilitat=" + disponibilitat +" preu="+ preu + "adresça=" + adress +"]";
    } 
   
    
}
