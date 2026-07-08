package com.mycompany.tennis.core.entity;

public class Tournoi {
    private Long ID;
    private String NOM;
    private String CODE;

    public String getNom() {
        return NOM;
    }

    public Long getId() {
        return ID;
    }

    public String getCode() {
        return CODE;
    }

    public void setId(Long id) {
        this.ID = id;
    }
    public void setNom(String nom) {
        this.NOM = nom;
    }

    public void setCode(String code) {
        this.CODE = code;
    }
}
