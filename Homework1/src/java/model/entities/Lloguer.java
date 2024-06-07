/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

/**
 * 
 * @author Dounia
 * @author Pol
 */
@Entity
@XmlRootElement
public class Lloguer implements Serializable {
    private static final long serialVersionUID = 1L; 
    @Id
    @SequenceGenerator(name="Lloguer_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Lloguer_Gen") 
    private Long id;
    private LocalDate dia; 
    private Float PreuTotal;
            
    @ManyToOne
    @JoinColumn(name = "usuari_id")
    private Usuari usuari; 
    
    @ManyToMany
    @JoinTable(name="LLOGUER_JOC", 
            joinColumns= @JoinColumn(name="LLOGUER_ID"),
            inverseJoinColumns= @JoinColumn(name="JOC_ID"))
    private Collection<Joc> jocs;
    
    
    // Getters y Setters
  
    public Collection<Joc> getJocs(){
        return jocs;
    }
    public  void setJocs(Collection<Joc> jocs){
        this.jocs= jocs;
    }
    // Getters y Setters
    
    public LocalDate getDia(){
        return dia; 
    }
    
    public void setDia(LocalDate dia){
        this.dia = dia; 
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuari getUsuari(){
        return usuari; 
    }
    
    public void setUsuari(Usuari usuari){
        this.usuari=usuari; 
    }
 public Float getPreuTotal() {
        return PreuTotal;
    }

    public void setPreuTotal(Float preu) {
        this.PreuTotal = preu;
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
        if (!(object instanceof Lloguer)) {
            return false;
        }
        Lloguer other = (Lloguer) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

        @Override
    public String toString() {
        return "model.entities.Lloguers[ id=" + id + " data=" + dia + "]";
    }
    
}
