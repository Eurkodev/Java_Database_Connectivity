package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

public class TravauxPratiques {
    public static void main(String... args) {
        TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();
        JoueurService joueurService = new JoueurService();
        Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannick");
        noah.setSexe('H');
        joueurService.createJoueur(noah);

        System.out.println("L'identifiant du joueur créé est " + noah.getId());

        long idJoueur = 27L;
        Joueur rs = joueurService.getJoueur(27L);
        System.out.println("Le joueur demande est " + rs.getNom() + " " + rs.getPrenom());

        TournoiService tournoiService = new TournoiService();

        Tournoi tournoi = new Tournoi();
        tournoi.setNom("Rabat");
        tournoi.setCode("MR");

        tournoiService.createTournoi(tournoi);
    }
 }
