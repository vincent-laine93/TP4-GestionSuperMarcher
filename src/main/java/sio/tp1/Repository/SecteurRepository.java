package sio.tp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sio.tp1.Entity.Secteur;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Integer> {
    @Override
    List<Secteur> findAll();

    
    @Override
    Optional<Secteur> findById(Integer integer);
}
