package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;


    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();

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

            try {
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                matchDto = new MatchDto();
                match = matchRepository.getById(id);
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
                matchDto.setId(match.getId());
                Epreuve epreuve = new Epreuve();
                epreuveFullDto.setId(match.getEpreuve().getId());
                epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
                epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

                matchDto.setEpreuve(epreuveFullDto);

            System.out.println("L'identifiant du match demandé est : " + matchDto.getId() + " et le vainqueur est "  + matchDto.getVainqueur() + " et le finaliste est " + match.getFinaliste());
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
        public void enregistrerNouveauMatch (Match match){
            matchRepository.create(match);
            scoreRepository.create(match.getScore());
        }
    }
