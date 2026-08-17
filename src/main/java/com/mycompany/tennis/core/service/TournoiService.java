package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public TournoiDto getTournoi(Long idTournoi) {
        EntityTransaction tx = null;
        //Session session = null;
        EntityManager em=null;
        Tournoi trn = null;
        TournoiDto tournoiDto = null;
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
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
            if (em != null) em.close();
        }
        return tournoiDto;
    }
    public void createTournoi(TournoiDto tournoiDto) {
        EntityTransaction tx = null;
        //Session session = null;
        EntityManager em=null;
        try {
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
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
            if (em != null) em.close();
        }





    }


public void deleteTournoi(Long id) {
    //Session session = null;
    EntityManager em = EntityManagerHolder.getCurrentEntityManager();
    EntityTransaction tx = null;
    Tournoi tournoi = null;

    try {
         em = EntityManagerHolder.getCurrentEntityManager();
        //session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = em.getTransaction();
        tx.begin();
        tournoiRepository.delete(id);
        tx.commit();

    }

    catch(Exception e) {
        if(tx!=null) tx.rollback();

        e.printStackTrace();
    }

    finally {
        if(em!=null) em.close();

        }
    }
}