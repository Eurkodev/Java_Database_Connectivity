package com.mycompany.tennis.core.dto;

public class TournoiDto {
    private Long Id;
    private String NOM;
    private String CODE;

    public String getNom() {
        return NOM;
    }

    public Long getId() {
        return Id;
    }

    public String getCode() {
        return CODE;
    }

    public void setId(Long id) {
        this.Id = id;
    }
    public void setNom(String nom) {
        this.NOM = nom;
    }

    public void setCode(String code) {
        this.CODE = code;
    }
}


