package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Tournoi;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EpreuveFullDto {
    private Long id;
    private short annee;
    private TournoiDto tournoiDto;
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

    public TournoiDto getTournoi() {
        return tournoiDto;
    }

    public void setTournoi(TournoiDto tournoiDto) {
        this.tournoiDto = tournoiDto;
    }
}
