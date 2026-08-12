package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public TournoiDto getTournoi(Long idTournoi) {
        Transaction tx = null;
        Session session = null;
        Tournoi trn = null;
        TournoiDto tournoiDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            trn = tournoiRepository.getById(idTournoi);
            tournoiDto = new TournoiDto();
            tournoiDto.setId(trn.getId());
            tournoiDto.setNom(trn.getNom());
            tournoiDto.setCode(trn.getCode());

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return tournoiDto;
    }
    public void createTournoi(TournoiDto tournoiDto) {
        Transaction tx = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setCode(tournoiDto.getCode());
            tournoi.setNom(tournoiDto.getNom());
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