package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    /*public void renomme(Long id, String nouveauNom) {
        Joueur joueur = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueur = session.get(Joueur.class, id);
            joueur.setNom(nouveauNom);
            tx.commit();
            System.out.println("Nom du joueur modifié " + joueur.getNom());

        }

        catch(Exception t) {
            if(tx!=null) tx.rollback();
            t.printStackTrace();
        }


        finally {
            if(session!=null) { session.close();}
        }

    }*/

    public void create(Joueur joueur) {
           Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);
            System.out.println("Joueur créé");

        }





    public void delete(Long id) {
        Joueur joueur = getById(id);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur.setId(id);
        session.delete(joueur);

        }



    public Joueur getById(Long id) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Joueur joueur = session.get(Joueur.class, id);

            System.out.println("Joueur lu");

        return joueur;
    }

    public List<Joueur> list (char sexe ) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Joueur> query = em.createNamedQuery("given sexe",Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueursList = query.getResultList();
        return joueursList;
    }
}




