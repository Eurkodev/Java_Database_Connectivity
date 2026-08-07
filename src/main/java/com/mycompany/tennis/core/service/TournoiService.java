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

    public Tournoi getTournoi(Long idTournoi) {
        Transaction tx = null;
        Session session = null;
        Tournoi trn = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            trn = tournoiRepository.getById(idTournoi);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return trn;
    }
    public void createTournoi(Tournoi tournoi) {
        Transaction tx = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepository.create(tournoi);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }





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