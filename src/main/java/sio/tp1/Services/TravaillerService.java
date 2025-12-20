package sio.tp1.Services;

import jakarta.persistence.EntityManager;


import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sio.tp1.Entity.*;
import sio.tp1.Repository.TravaillerRepository;

import java.time.LocalDate;

@Service
public class TravaillerService {
    private TravaillerRepository travaillerRepository;

    public TravaillerService(TravaillerRepository travaillerRepository) {
        this.travaillerRepository = travaillerRepository;
    }

    public void ajouterTravailler(Employe employeSelected, Rayon rayonSelected, LocalDate date, String nbHeures) {

        //recupere les id dans travaillerId
        TravaillerId id = new TravaillerId();
        id.setCodeEmploye(employeSelected.getId());
        id.setCodeRayon(rayonSelected.getId());
        id.setDate(date);

        //on creer une entity travailler que l'on envoie en bdd
        Travailler travailler = new Travailler();
        travailler.setId(id);
        travailler.setCodeEmploye(employeSelected);
        travailler.setCodeRayon(rayonSelected);
        travailler.setTemps(Integer.parseInt(nbHeures));

        travaillerRepository.saveAndFlush(travailler);

    }

    public int getTempsTravaillerParSecteur(Secteur secteur){
        Integer tempsTravailler = travaillerRepository.getTempsTravaillerParSecteur(secteur.getId());

        if(tempsTravailler == null){
            return 0;
        }else return tempsTravailler;
    }

    public int getTempsTravaillerParRayon(Rayon rayon){
        Integer tempsTravailler = travaillerRepository.getTempsTravaillerParRayon(rayon.getId());
        if(tempsTravailler == null){
            return 0;
        }else return tempsTravailler;
    }

}
