package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;


    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();

    }

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDto matchDto = null;
        JoueurDto joueurDto = null;
        EpreuveFullDto epreuveFullDto = null;
        JoueurDto joueurVainqueurDto = null;
        JoueurDto joueurFinalisteDto = null;
        TournoiDto tournoiDto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            matchDto = new MatchDto();
            match = matchRepository.getById(id);
            matchDto.setId(match.getId());

            joueurVainqueurDto = new JoueurDto();
            joueurFinalisteDto = new JoueurDto();

            joueurVainqueurDto.setId(match.getVainqueur().getId());
            joueurVainqueurDto.setNom(match.getVainqueur().getNom());
            joueurVainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            joueurVainqueurDto.setSexe(match.getVainqueur().getSexe());

            joueurFinalisteDto.setId(match.getVainqueur().getId());
            joueurFinalisteDto.setNom(match.getVainqueur().getNom());
            joueurFinalisteDto.setPrenom(match.getVainqueur().getPrenom());
            joueurFinalisteDto.setSexe(match.getVainqueur().getSexe());
            matchDto.setVainqueur(joueurVainqueurDto);
            matchDto.setFinaliste(joueurFinalisteDto);

            epreuveFullDto = new EpreuveFullDto();
            Epreuve epreuve = new Epreuve();
            tournoiDto = new TournoiDto();
            matchDto.setEpreuve(epreuveFullDto);

            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            epreuveFullDto.setId(match.getEpreuve().getId());
            epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            epreuveFullDto.setTournoi(tournoiDto);

            ScoreFullDto scoreDto = new ScoreFullDto();
            scoreDto.setId(match.getScore().getId());
            scoreDto.setSet1(match.getScore().getSet1());
            scoreDto.setSet2(match.getScore().getSet2());
            scoreDto.setSet3(match.getScore().getSet3());
            scoreDto.setSet4(match.getScore().getSet4());
            scoreDto.setSet5(match.getScore().getSet4());

            matchDto.setScoreDto(scoreDto);
            scoreDto.setMatch(matchDto);

            System.out.println("L'identifiant du match demandé est : " + matchDto.getId() + " et le vainqueur est " + matchDto.getVainqueur().getNom() + " et le finaliste est " + match.getFinaliste().getNom());
            tx.commit();
            System.out.println("Joueur lu");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            return matchDto;
        }
    }

    public void createMatch(MatchDto matchDto) {
        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = new Match();
            match.setEpreuve(epreuveRepository.getById(matchDto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.getById(matchDto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(matchDto.getFinaliste().getId()));
            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(matchDto.getScoreDto().getSet1());
            score.setSet2(matchDto.getScoreDto().getSet2());
            score.setSet3(matchDto.getScoreDto().getSet3());
            score.setSet4(matchDto.getScoreDto().getSet4());
            score.setSet5(matchDto.getScoreDto().getSet5());

            matchRepository.create(match);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

    public void deleteMatch(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            matchRepository.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public void tapisVert(Long id) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte) 0);
            match.getScore().setSet2((byte) 0);
            match.getScore().setSet3((byte) 0);
            match.getScore().setSet4((byte) 0);
            match.getScore().setSet5((byte) 0);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

   
}