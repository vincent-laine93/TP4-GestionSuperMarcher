package sio.tp1.Services;

import org.springframework.stereotype.Service;
import sio.tp1.Entity.Rayon;
import sio.tp1.Entity.Secteur;
import sio.tp1.Repository.RayonRepository;
import sio.tp1.Repository.SecteurRepository;

import java.util.List;

@Service
public class RayonService {

    private RayonRepository rayonRepository;
    private SecteurRepository secteurRepository;

    public RayonService(RayonRepository rayonRepository, SecteurRepository secteurRepository) {
        this.rayonRepository = rayonRepository;
        this.secteurRepository = secteurRepository;
    }

    public List<Rayon> getAllRayonsByIdSecteur(int idSecteur) {
        Secteur leSecteur = secteurRepository.findById(idSecteur).get();
        return rayonRepository.findByNumSecteur(leSecteur);
    }
}
