package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService () {
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.create(joueur);
            tx.commit();
            System.out.println("Joueur lu");

        }
        catch(Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }


        finally {
            if(session!=null) { session.close();}
        }



    }

    public Joueur getJoueur(Long id) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);
            tx.commit();
            System.out.println("Joueur lu");

        }
        catch(Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }


        finally {
            if(session!=null) { session.close();}
        }


        return joueurRepository.getById(id);

    }

    public void renomme(Long id, String nouveauNom) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Joueur joueur = session.get(Joueur.class, id);
            joueur.setNom(nouveauNom);
            tx.commit();
            System.out.println("Joueur lu");

        }
        catch(Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }


        finally {
            if(session!=null) { session.close();}
        }
    }

}
