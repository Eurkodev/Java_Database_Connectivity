package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true","EURKODEV","Bouftou80@");
            long identifiant = 128L;
            PreparedStatement prepareStatement = conn.prepareStatement("SELECT ID, NOM, PRENOM FROM JOUEUR WHERE ID =? ");
            prepareStatement.setLong(1, identifiant);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                final String nom=rs.getString("NOM");
                final String prenom=rs.getString("PRENOM");
                final Long id = rs.getLong("ID");
                System.out.println("Le joueur / la joueuse représenté(e) par le numéro " + id + " est " + prenom + " " + nom );
            }
            prepareStatement.setLong(1,129);
            ResultSet rs2=prepareStatement.executeQuery();
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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
