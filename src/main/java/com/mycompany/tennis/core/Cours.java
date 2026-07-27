package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.MatchService;

public class Cours {
    public static void main(String... args) {

        MatchService matchService = new MatchService();
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);
        score.setMatch(match);
        match.setScore(score);
        Joueur federer = new Joueur();
        federer.setId(32L);
        Joueur murray = new Joueur();
        murray.setId(34L);

        match.setVainqueur(federer);
        match.setFinaliste(murray);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);


        matchService.enregistrerNouveauMatch(match);
        System.out.println("L'identifiant du match créé est "+match.getId());
    }

   }


