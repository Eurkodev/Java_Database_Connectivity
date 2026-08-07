package com.mycompany.tennis.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Epreuve {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private short annee;
    private Tournoi tournoi;
    private Character typeEpreuve;
    public Long getId() {
        return id;
    }

    public short getAnnee() {
        return annee;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnee(short annee) {
        this.annee = annee;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
