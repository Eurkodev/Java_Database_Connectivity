package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class ScoreService {
    private MatchRepositoryImpl scoreRepository;

    public ScoreService () {
        this.scoreRepository = new ScoreRepositoryImpl();
    }
    public void createJoueur(Score score) {
        scoreRepository.create(score);
    }
    public Match getMatch(long score) {
        Score s =  scoreRepository.getById(score);
        return s;
    }

}
