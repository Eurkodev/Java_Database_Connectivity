package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
      Session session = null;
      Transaction tx = null; 
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);
            System.out.println("Joueur créé");

        }
        catch(Exception t) {
            if(tx!=null) tx.rollback();
            t.printStackTrace();
        }


        finally {
            if(session!=null) { session.close();}
        }
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

    public List<Joueur> list () {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);


            PreparedStatement prepareStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");

            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("NOM").charAt(0));
                joueurs.add(joueur);
            }

            conn.commit();
            System.out.println("Joueurs lus");
        } catch (
                SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }
}




