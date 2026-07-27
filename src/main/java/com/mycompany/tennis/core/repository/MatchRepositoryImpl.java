package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {
    public void create(Match match) {
        Connection conn = null;
        try {

            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID, ANNEE , TYPE_EPREUVE, ID_TOURNOI) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            prepareStatement.setLong(1, match.getEpreuve().getId());
            prepareStatement.setLong(2, match.getVainqueur().getId());
            prepareStatement.setLong(3, match.getFinaliste().getId());
            int nbEnregistrementModifies = prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();

            if(rs.next()) {
                match.setId(rs.getLong(1));
            }

            conn.commit();
            System.out.println("Match créé");
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

}




