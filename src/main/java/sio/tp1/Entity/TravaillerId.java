package sio.tp1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class TravaillerId implements java.io.Serializable {
    private static final long serialVersionUID = -5115246405973271946L;
    @Column(name = "codeEmploye", nullable = false)
    private Integer codeEmploye;

    @Column(name = "codeRayon", nullable = false)
    private Integer codeRayon;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Integer getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(Integer codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

    public Integer getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(Integer codeRayon) {
        this.codeRayon = codeRayon;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TravaillerId entity = (TravaillerId) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.codeEmploye, entity.codeEmploye) &&
                Objects.equals(this.codeRayon, entity.codeRayon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, codeEmploye, codeRayon);
    }

}