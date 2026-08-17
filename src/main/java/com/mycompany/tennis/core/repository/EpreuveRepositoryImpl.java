package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {

    public Epreuve getById(Long id) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
            Epreuve epreuve = em.find(Epreuve.class, id);
            System.out.println("Epreuve lu");
            return epreuve;
        }
    public List<Epreuve> list (String codeTournoi) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.CODE=?0",Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuvesList = query.getResultList();
        return epreuvesList;
    }



}