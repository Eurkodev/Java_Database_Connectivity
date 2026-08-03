package com.mycompany.tennis.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "TOURNOI")
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
