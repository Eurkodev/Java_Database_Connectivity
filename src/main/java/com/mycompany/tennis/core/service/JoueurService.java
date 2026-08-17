package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
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

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        return joueur;

    }

    public void renomme(Long id, String nouveauNom) {
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setNom(nouveauNom);
           Joueur joueur2 = (Joueur) session.merge(joueur);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

    public void changementSexe(Character nouveauSexe, Long id) {
        Joueur joueur = getJoueur(id);
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur.setSexe(nouveauSexe);
            Joueur joueur2 = (Joueur) session.merge(joueur);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }


        }
    }
    public void deleteJoueur(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }


        }
    }

    public List<JoueurDto> getListeJoueurs(char sexe) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Match match = null;
        List<JoueurDto> dtos = new ArrayList<>();
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Joueur> joueurs = joueurRepository.list(sexe);
            for(Joueur j : joueurs) {
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(j.getId());
                joueurDto.setNom(j.getNom());
                joueurDto.setPrenom(j.getPrenom());
                joueurDto.setSexe(j.getSexe());
                dtos.add(joueurDto);
            }
            tx.commit();


        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            return dtos;
        }
    }
}
