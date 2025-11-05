package sio.tp1.Services;

import org.springframework.stereotype.Service;
import sio.tp1.Entity.Secteur;
import sio.tp1.Repository.SecteurRepository;

import java.util.List;

@Service
public class SecteurService {
    private SecteurRepository secteurRepository;

    public SecteurService(SecteurRepository secteurRepository) {
        this.secteurRepository = secteurRepository;
    }

    public List<Secteur> getAllSecteur(){
        return secteurRepository.findAll();
    }
}
