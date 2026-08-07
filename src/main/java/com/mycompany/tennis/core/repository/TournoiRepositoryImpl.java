package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {


    public Tournoi create(Tournoi tournoi) {
        Transaction tx = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.persist(tournoi);
            session.beginTransaction();
            tx = session.getTransaction();
            tx.commit();
            System.out.println("Le tournoi a été créé");

        } catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();

        } finally {
          if(session!=null) session.close();

        }

        return tournoi;
    }

    public void update(Tournoi tournoi) {
        Connection conn = null;
        try {

            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement prepareStatement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?");

            prepareStatement.setString(1, tournoi.getNom());
            prepareStatement.setString(2, tournoi.getCode());
            prepareStatement.setLong(3, tournoi.getId());




            prepareStatement.executeUpdate();

            conn.commit();
            System.out.println("Tournoi modifié");
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

    }

    public void delete(Long id) {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.delete(tournoi);
    }
    public Tournoi getById(Long id) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Tournoi tournoi = session.get(Tournoi.class, id);
            System.out.println("Tournoi lu");
            return tournoi;
        }


    public List<Tournoi> list() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ID, NOM, CODE  FROM TOURNOI");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }
            conn.commit();

        }
        catch(SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
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
        return tournois;
    }
}