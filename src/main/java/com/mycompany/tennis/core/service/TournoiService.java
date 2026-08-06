package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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


public void deleteTournoi(Long id) {
    Session session = null;
    Transaction tx = null;
    Tournoi tournoi = null;

    try {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
        tournoiRepository.delete(id);
        tx.commit();

    }

    catch(Exception e) {
        if(tx!=null) tx.rollback();

        e.printStackTrace();
    }

    finally {
        if(session!=null) session.close();

        }
    }
}