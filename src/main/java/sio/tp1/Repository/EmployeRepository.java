package sio.tp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sio.tp1.Entity.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    @Override
    List<Employe> findAll();
}
