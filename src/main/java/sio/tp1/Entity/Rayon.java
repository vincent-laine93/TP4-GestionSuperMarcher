package sio.tp1.Entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rayon")
public class Rayon {
    @Id
    @Column(name = "idRayon", nullable = false)
    private Integer id;

    @Column(name = "nomRayon", nullable = false, length = 20)
    private String nomRayon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numSecteur", nullable = false)
    private sio.tp1.Entity.Secteur numSecteur;

    @OneToMany(mappedBy = "codeRayon")
    private Set<sio.tp1.Entity.Travailler> travaillers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRayon() {
        return nomRayon;
    }

    public void setNomRayon(String nomRayon) {
        this.nomRayon = nomRayon;
    }

    public sio.tp1.Entity.Secteur getNumSecteur() {
        return numSecteur;
    }

    public void setNumSecteur(sio.tp1.Entity.Secteur numSecteur) {
        this.numSecteur = numSecteur;
    }

    public Set<sio.tp1.Entity.Travailler> getTravaillers() {
        return travaillers;
    }

    public void setTravaillers(Set<sio.tp1.Entity.Travailler> travaillers) {
        this.travaillers = travaillers;
    }

}