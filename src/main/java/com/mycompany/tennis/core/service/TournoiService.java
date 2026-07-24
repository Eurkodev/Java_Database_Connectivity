package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public Tournoi getTournoi(long idTournoi) {
        Tournoi rs = tournoiRepository.getById(idTournoi);
        return rs;
    }

    public void createTournoi(Tournoi tournoi) {
            tournoiRepository.create(tournoi);
    }
}
