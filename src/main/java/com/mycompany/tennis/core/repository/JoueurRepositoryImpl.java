package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {
    public void create(Joueur joueur) {
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setInitialSize(5);
            dataSource.setUsername("EURKODEV");
            dataSource.setPassword("Bouftou80@");

            conn = dataSource.getConnection();


            PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");

            prepareStatement.setString(1, joueur.getNom());
            prepareStatement.setString(2, joueur.getPrenom());
            prepareStatement.setString(3, joueur.getSexe().toString());
            int nbEnregistrementModifies = prepareStatement.executeUpdate();


            conn.commit();
            System.out.println("Joueur créé");
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
    public void upadate(Joueur joueur) {
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setInitialSize(5);
            dataSource.setUsername("EURKODEV");
            dataSource.setPassword("Bouftou80@");

            conn = dataSource.getConnection();


            PreparedStatement prepareStatement = conn.prepareStatement("UPDATE JOUEUR SET (NOM=?, PRENOM=?, SEXE=? WHERE ID=?)");

            prepareStatement.setString(1, joueur.getNom());
            prepareStatement.setString(2, joueur.getPrenom());
            prepareStatement.setString(3, joueur.getSexe().toString());
            prepareStatement.setLong(4, joueur.getId());


            int nbEnregistrementModifies = prepareStatement.executeUpdate();


            conn.commit();
            System.out.println("Joueur modifié");
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
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setInitialSize(5);
            dataSource.setUsername("EURKODEV");
            dataSource.setPassword("Bouftou80@");

            conn = dataSource.getConnection();


            PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?)");


            prepareStatement.setLong(4, id);


            int nbEnregistrementModifies = prepareStatement.executeUpdate();


            conn.commit();
            System.out.println("Joueur supprimé");
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
    public Joueur getById(Long id) {
        Connection conn = null;
        Joueur joueur = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setInitialSize(5);
            dataSource.setUsername("EURKODEV");
            dataSource.setPassword("Bouftou80@");

            conn = dataSource.getConnection();


            PreparedStatement prepareStatement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");


            prepareStatement.setLong(4, id);


            ResultSet rs = prepareStatement.executeQuery();
            if(rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("NOM").charAt(0));

            }

            conn.commit();
            System.out.println("Joueur lu");
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
        return joueur;
    }

    public List<Joueur> list (Long id) {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setInitialSize(5);
            dataSource.setUsername("EURKODEV");
            dataSource.setPassword("Bouftou80@");

            conn = dataSource.getConnection();

            PreparedStatement prepareStatement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR");

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




