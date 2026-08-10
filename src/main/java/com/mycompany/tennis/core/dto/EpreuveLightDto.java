package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Tournoi;

public class EpreuveLightDto {
    private Long id;
    private short annee;

    private Character typeEpreuve;
    public Long getId() {
        return id;
    }

    public short getAnnee() {
        return annee;
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

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

}
