package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }

    public ScoreFullDto getScore(Long idScore) {
        Session session = null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDto scoreFullDto = new ScoreFullDto();
        MatchDto matchDto = new MatchDto();
        EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
        TournoiDto tournoiDto = new TournoiDto();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getById(idScore);

            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());
            scoreFullDto.setMatch(matchDto);


            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());

            epreuveFullDto.setId(score.getMatch().getEpreuve().getId());
            epreuveFullDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            epreuveFullDto.setTournoi(tournoiDto);

            matchDto.setId(scoreFullDto.getMatch().getId());
            matchDto.setVainqueur(scoreFullDto.getMatch().getVainqueur());
            matchDto.setFinaliste(scoreFullDto.getMatch().getFinaliste());
            matchDto.setEpreuve(epreuveFullDto);

        }

        catch (Exception e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();

        }

        finally {
            if(session!=null) session.close();
        }
return scoreFullDto;
    }

    public void deleteScore(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            scoreRepository.delete(id);
            tx.commit();
        }
        catch(Exception e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally{
            if (session != null) {
                session.close();
            }
        }
    }

}
