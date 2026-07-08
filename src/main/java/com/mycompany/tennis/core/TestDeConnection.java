package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args) {
        TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();
        for (Tournoi tr : tournoiRepository.list()) {
            System.out.println(tr.getId() + " " + tr.getNom());
        }

    }
    }

