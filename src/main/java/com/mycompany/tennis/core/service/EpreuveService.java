package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuveAvecTournoi(Long id) {
        Transaction tx = null;
        Session session = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            System.out.println("La classe de ma propriété torunoi est : " + epreuve.getTournoi().getClass().getName());
            System.out.println("L'identifiant du tournoi est : " + epreuve.getTournoi().getId());
            Hibernate.initialize(epreuve.getTournoi());
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return epreuve;
    }
    public Epreuve getEpreuveSansTournoi(Long id) {
        Transaction tx = null;
        Session session = null;
        Epreuve epreuve = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return epreuve;
    }
}