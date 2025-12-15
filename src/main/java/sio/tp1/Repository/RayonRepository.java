package sio.tp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sio.tp1.Entity.Rayon;
import sio.tp1.Entity.Secteur;

import java.util.List;

@Repository
public interface RayonRepository extends JpaRepository<Rayon, Integer> {
    @Override
    List<Rayon> findAllById(Iterable<Integer> idSecteur);


    List<Rayon> findByNumSecteur(Secteur numSecteur);
}
