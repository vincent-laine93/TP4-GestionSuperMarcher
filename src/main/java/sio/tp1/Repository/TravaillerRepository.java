package sio.tp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sio.tp1.Entity.Travailler;

@Repository
public interface TravaillerRepository extends JpaRepository<Travailler, Integer> {
    @Override
    <S extends Travailler> S saveAndFlush(S entity);

    @Query("""

    SELECT SUM(t.temps)
    FROM Travailler t
    JOIN t.codeRayon r
    WHERE r.numSecteur.id = :idSecteur
""")
    Integer getTempsTravaillerParSecteur(@Param("idSecteur") int idSecteur);

    @Query(
            "SELECT SUM(t.temps) " +
            "from Travailler t " +
            "where t.codeRayon.id = :idRayon"
    )
    Integer getTempsTravaillerParRayon(@Param("idRayon") int idRayon);
}
