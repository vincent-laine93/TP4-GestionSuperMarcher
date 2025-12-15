package sio.tp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sio.tp1.Entity.Employe;
import sio.tp1.dto.EmployeRayon;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    @Override
    List<Employe> findAll();

    @Query("select new sio.tp1.dto.EmployeRayon(e.id,e.nomEmploye,t.id.date,t.temps) from Employe e join Travailler t on e.id = t.codeEmploye.id and t.codeRayon.id =:idRayon")
    List<EmployeRayon> getAllEmployesByIdRayon(@Param("idRayon")int idRayon);



}
