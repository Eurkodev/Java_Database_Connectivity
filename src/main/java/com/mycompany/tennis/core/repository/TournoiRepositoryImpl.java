package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {


    public Tournoi create(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSoure = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSoure.getConnection();

            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO TOURNOI (NOM, CODE) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.executeUpdate();

            ResultSet rs=preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                tournoi.setId(rs.getLong(1));
            }

            System.out.println("Tournoi créé");

            conn.commit();

        } catch (SQLException e) {
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
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");

            prepareStatement.setLong(1, id);


            prepareStatement.executeUpdate();


            conn.commit();
            System.out.println("Tournoi supprimé");
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
        public Tournoi getById(Long id) {
            Connection conn = null;
            Tournoi tournoi = null;
            try {

                DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

                conn = dataSource.getConnection();

                conn.setAutoCommit(false);


                PreparedStatement prepareStatement = conn.prepareStatement("SELECT NOM, CODE FROM TOURNOI WHERE ID=?");


                prepareStatement.setLong(1, id);


                ResultSet rs = prepareStatement.executeQuery();
                if(rs.next()) {
                    tournoi = new Tournoi();
                    tournoi.setId(id);
                    tournoi.setNom(rs.getString("NOM"));
                    tournoi.setCode(rs.getString("CODE"));

                }

                conn.commit();
                    System.out.println("Tournoi lu");
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