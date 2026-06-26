package com.mycompany.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector

            MysqlDataSource dataSource = new MysqlDataSource();
            //dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("tennis");
            dataSource.setUser("EURKODEV");
            dataSource.setPassword("Bouftou80@");
            dataSource.setUseSSL(false);
            dataSource.getAllowPublicKeyRetrieval();
            dataSource.setServerTimezone("Europe/Paris");
            conn = dataSource.getConnection();

           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true","EURKODEV","Bouftou80@");

            conn.setAutoCommit(false);
            PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";
            prepareStatement.setString(1, nom);
            prepareStatement.setString(2, prenom);
            prepareStatement.setString(3, sexe);
            int nbEnregistrementModifies = prepareStatement.executeUpdate();

            conn.commit();

            nom = "Johannson";
            prenom = "Thomas";
            sexe = "H";

            prepareStatement.setString(1, nom);
            prepareStatement.setString(2, prenom);
            prepareStatement.setString(3, sexe);
            prepareStatement.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(conn!=null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
