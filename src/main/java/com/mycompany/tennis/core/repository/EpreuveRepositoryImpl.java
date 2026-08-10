package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {

    public Epreuve getById(Long id) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Epreuve epreuve = session.get(Epreuve.class, id);
            System.out.println("Epreuve lu");
            return epreuve;
        }


}