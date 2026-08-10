package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuve(Long id) {
        Transaction tx = null;
        Session session = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            System.out.println("L'épreuve sélectionnée se déroule en : " + epreuve.getAnnee() + " et il s'agit du tournoi " + epreuve.getTournoi().getNom());
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return epreuve ;
    }

}