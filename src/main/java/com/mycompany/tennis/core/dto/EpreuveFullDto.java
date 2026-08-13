package com.mycompany.tennis.core.dto;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;

public class EpreuveFullDto {
    private Long id;
    private short annee;
    private TournoiDto tournoiDto;
    private Character typeEpreuve;
    private Set<JoueurDto> participants;

    public Set<JoueurDto> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<JoueurDto> participants) {
        this.participants = participants;
    }

    public TournoiDto getTournoiDto() {
        return tournoiDto;
    }

    public void setTournoiDto(TournoiDto tournoiDto) {
        this.tournoiDto = tournoiDto;
    }

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
