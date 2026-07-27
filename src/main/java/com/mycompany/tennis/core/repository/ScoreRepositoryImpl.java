package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {
    public void create(Score score) {
        Connection conn = null;
        try {

            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5 ) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            prepareStatement.setLong(1, score.getMatch().getId());
            prepareStatement.setLong(2, score.getSet1());
            prepareStatement.setLong(3, score.getSet2());
            prepareStatement.setLong(4, score.getSet3());
            prepareStatement.setLong(5, score.getSet4());
            prepareStatement.setLong(6, score.getSet5());

            int nbEnregistrementModifies = prepareStatement.executeUpdate();
            ResultSet rs = prepareStatement.getGeneratedKeys();

            if(rs.next()) {
                score.setId(rs.getLong(1));
            }

            conn.commit();
            System.out.println("Score créé");
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




