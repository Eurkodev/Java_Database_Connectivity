package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {
    public void create(Match match) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match créé");
    }

    public Match getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, id);
        System.out.println("Match lu");
        return match;
    }
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, id);
        session.delete(match);
        System.out.println("Match supprimé");
    }

}




